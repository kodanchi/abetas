package ASDB;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.omg.CORBA.portable.*;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * Created by Mojahed on 2/3/2016.
 */
public class ImportProgramSheet {
    private HttpServletRequest request;
    private HttpServletResponse response;
    private String uploadFilePath;
    private String UPLOAD_DIRECTORY;
    private String Error_Msg;
    private String pid ;
    private String pname ;
    private String pdataType ;
    private String[] sheetCheckerArr;
    private ArrayList<ArrayList<String>> sheetData;
    private final int MAX_FILE_SIZE = 2000000;
    private P_AS_Select dbs = new P_AS_Select();


    public ImportProgramSheet(HttpServletRequest request){
        uploadFilePath = "";
        Error_Msg = "";
        ServletContext context = request.getServletContext();
        //UPLOAD_DIRECTORY = context.getInitParameter("file-upload");
        UPLOAD_DIRECTORY = System.getProperty("java.io.tmpdir");
    }


    public boolean sheetVaildation(HttpServletRequest request,HttpServletResponse response){



        try {
            //process only if its multipart content
            if(ServletFileUpload.isMultipartContent(request)){
                try {
                    List<FileItem> items = new ServletFileUpload(new DiskFileItemFactory()).parseRequest(request);

                    for(FileItem item : items){

                        switch (item.getFieldName()){
                            case "id":
                                pid = item.getString();
                                System.out.println("id is :"+pid);
                                break;
                            case "name":
                                pname = item.getString();
                                System.out.println("name is :"+pname);
                                break;
                            case "data-type":
                                pdataType= item.getString();
                                System.out.println("dataType is :"+pdataType);
                                break;
                        }



                        if(!item.isFormField()){

                            System.out.println("else fired!"+ item.getName());
                            if(item.getSize() != 0) {
                                String name = new File(item.getName()).getName();
                                System.out.println("else name!"+ name);

                                if (item.getSize() < MAX_FILE_SIZE) {

                                    String extension = "";

                                    int i = item.getName().lastIndexOf('.');
                                    if (i > 0) {
                                        extension = item.getName().substring(i + 1);
                                        System.out.println("file ext !"+ extension);
                                    }
                                    if (extension.equals("xls") || extension.equals("xlsx")) {
                                        item.write(new File(UPLOAD_DIRECTORY + "\\"+ File.separator + name));
                                        uploadFilePath = UPLOAD_DIRECTORY + "\\"+ name;
                                        //File uploaded successfully
                                        System.out.println("File Uploaded Successfully!");
                                        System.out.println("File Uploaded to " + uploadFilePath);


                                        return true;



                                    } else {
                                        System.out.println("File must be excel");
                                        Error_Msg = "The uploaded file must be excel of following extensions: xls or xlsx";
                                        return false;
                                    }
                                } else {
                                    System.out.println("file size must be less than 2 MB");
                                    Error_Msg = "The uploaded file's size must be less than 2 MB";
                                    return false;
                                }
                            }else{
                                uploadFilePath = null;
                                return false;
                            }
                        }
                    }


                } catch (Exception ex) {
                    System.out.println("File Upload Failed due to " + ex);
                    Error_Msg = "File Upload Failed due to " + ex;
                    return false;

                }


            }else{
                System.out.println("not multipart!");
                Error_Msg = "not multipart!";
                return false;
            }


        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

        return true;

    }

    public boolean objSheetVaildation(String[] sheetChecker) throws SQLException, ClassNotFoundException {
        try {

            InputStream file = new FileInputStream(new File(uploadFilePath));


            //Set the api to work on the file
            Workbook workbook = WorkbookFactory.create(file);

            //Get first sheet from the workbook
            Sheet sheet = workbook.getSheetAt(0);


            //Set the sheet checker in Array
            String[] sheetCheckerArr = sheetChecker;
            boolean validFormHead = true;


            //Iterate through each rows from first sheet
            Iterator<Row> rowIterator = sheet.iterator();

            //Store the data in ArrayList
            sheetData = new ArrayList<ArrayList<String>>();



            System.out.println("Sheet size : "+sheet.getPhysicalNumberOfRows());
            if(sheet.getPhysicalNumberOfRows() == 0){
                System.out.print("errrrrr not same format");
                Error_Msg = "The selected file is not in the proper format, please follow the instructions \" +\n" +
                        "                                        \"that shown in the import page";
                file.close();
                return false;
            }


            //Iterate through each rows as an ArrayList
            ArrayList<String>  dataRow;
            while(rowIterator.hasNext()) {
                dataRow = new ArrayList<String>();
                Row row = rowIterator.next();

                //For each row, iterate through each columns
                Iterator<Cell> cellIterator = row.cellIterator();
                //while(cellIterator.hasNext()) {
                for (int j=0;j<sheetCheckerArr.length;j++){

                    //Cell cell = cellIterator.next();
                    Cell cell = row.getCell(j);

                    if(validFormHead) {
                        if (cell.getCellType() == Cell.CELL_TYPE_STRING) {
                            if (cell.getStringCellValue().equals(sheetCheckerArr[j])) {
                                System.out.print(cell.getStringCellValue() + "\t\t");
                            } else {
                                System.out.print("errrrrr not same format");
                                Error_Msg = "The selected file is not in the proper format, please follow the instructions \" +\n" +
                                        "                                        \"that shown in the import page";
                                file.close();
                                return false;
                            }
                        } else {
                            System.out.print("errrrrr not string");
                            Error_Msg = "errrrrr not string";
                            file.close();
                            return false;
                        }
                    }else {
                        try {
                            System.out.println("**"+j+"**"+cell.getCellType()+"**"+cell.getStringCellValue()+"**");

                            if (cell.getCellType() == Cell.CELL_TYPE_STRING) {
                                switch (j){
                                    case 0:
                                        if(cell.getStringCellValue().equals("")){
                                            Error_Msg = "Some of the records are empty.";
                                            file.close();
                                            return false;
                                        }else {
                                            dataRow.add(cell.getStringCellValue());
                                        }
                                }
                                //System.out.print(cell.getStringCellValue() + "\t\t");
                            }else {
                                System.out.print("errrrrr not same format");
                                Error_Msg = "The selected file is not in the proper format, please follow the instructions " +
                                        "that shown in the import page";
                                file.close();
                                return false;
                            }
                        }catch (NullPointerException e){
                            e.fillInStackTrace();


                            switch (j){
                                case 0:
                                    Error_Msg="Some of the records are empty, change it in the sheet and try upload it again, or choose another file.";
                                    file.close();
                                    return false;
                            }
                        }

                    }


                }

                System.out.println("");
                if(!validFormHead) {
                    sheetData.add(dataRow);
                }
                validFormHead = false;
            }
            file.close();
            FileOutputStream pout =
                    new FileOutputStream(new File(uploadFilePath));
            workbook.write(pout);
            pout.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InvalidFormatException e) {
            e.printStackTrace();
        }
        return true;
    }

    public boolean outcomesSheetVaildation(String[] sheetChecker) throws SQLException, ClassNotFoundException {
        try {

            InputStream file = new FileInputStream(new File(uploadFilePath));


            //Set the api to work on the file
            Workbook workbook = WorkbookFactory.create(file);

            //Get first sheet from the workbook
            Sheet sheet = workbook.getSheetAt(0);

            //Set the sheet checker in Array
            String[] sheetCheckerArr = sheetChecker;
            boolean validFormHead = true;


            //Iterate through each rows from first sheet
            Iterator<Row> rowIterator = sheet.iterator();

            //Store the data in ArrayList
            sheetData = new ArrayList<ArrayList<String>>();


            System.out.println("Sheet size : "+sheet.getPhysicalNumberOfRows());
            if(sheet.getPhysicalNumberOfRows() == 0){
                System.out.print("errrrrr not same format");
                Error_Msg = "The selected file is not in the proper format, please follow the instructions \" +\n" +
                        "                                        \"that shown in the import page";
                file.close();
                return false;
            }


            //Iterate through each rows as an ArrayList
            ArrayList<String>  dataRow;
            while(rowIterator.hasNext()) {
                dataRow = new ArrayList<String>();
                Row row = rowIterator.next();

                //For each row, iterate through each columns
                Iterator<Cell> cellIterator = row.cellIterator();
                //while(cellIterator.hasNext()) {
                for (int j=0;j<sheetCheckerArr.length;j++){

                    //Cell cell = cellIterator.next();
                    Cell cell = row.getCell(j);

                    if(validFormHead) {
                        if (cell.getCellType() == Cell.CELL_TYPE_STRING) {
                            if (cell.getStringCellValue().equals(sheetCheckerArr[j])) {
                                System.out.print(cell.getStringCellValue() + "\t\t");
                            } else {
                                System.out.print("errrrrr not same format");
                                Error_Msg = "The selected file is not in the proper format, please follow the instructions \" +\n" +
                                        "                                        \"that shown in the import page";
                                file.close();
                                return false;
                            }
                        } else {
                            System.out.print("errrrrr not string");
                            Error_Msg = "errrrrr not string";
                            file.close();
                            return false;
                        }
                    }else {
                        try {
                            System.out.println("**"+j+"**"+cell.getCellType()+"**"+cell.getStringCellValue()+"**");

                            if (cell.getCellType() == Cell.CELL_TYPE_STRING) {
                                switch (j){
                                    case 0:
                                        if(cell.getStringCellValue().equals("")){
                                            Error_Msg = "Some of the records are empty.";
                                            file.close();
                                            return false;
                                        }else {
                                            dataRow.add(cell.getStringCellValue());
                                        }
                                }
                                //System.out.print(cell.getStringCellValue() + "\t\t");
                            }else {
                                System.out.print("errrrrr not same format");
                                Error_Msg = "The selected file is not in the proper format, please follow the instructions " +
                                        "that shown in the import page";
                                file.close();
                                return false;
                            }
                        }catch (NullPointerException e){
                            e.fillInStackTrace();


                            switch (j){
                                case 0:
                                    Error_Msg="Some of the records are empty, change it in the sheet and try upload it again, or choose another file.";
                                    file.close();
                                    return false;
                            }
                        }

                    }


                }

                System.out.println("");
                if(!validFormHead) {
                    sheetData.add(dataRow);
                }
                validFormHead = false;
            }
            file.close();
            FileOutputStream pout =
                    new FileOutputStream(new File(uploadFilePath));
            workbook.write(pout);
            pout.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InvalidFormatException e) {
            e.printStackTrace();
        }
        return true;
    }

    public boolean coursesSheetVaildation(String[] sheetChecker) throws SQLException, ClassNotFoundException {
        try {

            InputStream file = new FileInputStream(new File(uploadFilePath));


            //Set the api to work on the file
            Workbook workbook = WorkbookFactory.create(file);

            //Get first sheet from the workbook
            Sheet sheet = workbook.getSheetAt(0);

            //Set the sheet checker in Array
            String[] sheetCheckerArr = sheetChecker;
            boolean validFormHead = true;


            //Iterate through each rows from first sheet
            Iterator<Row> rowIterator = sheet.iterator();

            //Store the data in ArrayList
            sheetData = new ArrayList<ArrayList<String>>();


            System.out.println("Sheet size : "+sheet.getPhysicalNumberOfRows());
            if(sheet.getPhysicalNumberOfRows() == 0){
                System.out.print("errrrrr not same format");
                Error_Msg = "The selected file is not in the proper format, please follow the instructions \" +\n" +
                        "                                        \"that shown in the import page";
                file.close();
                return false;
            }


            //Iterate through each rows as an ArrayList
            ArrayList<String>  dataRow;
            while(rowIterator.hasNext()) {
                dataRow = new ArrayList<String>();
                Row row = rowIterator.next();

                //For each row, iterate through each columns
                Iterator<Cell> cellIterator = row.cellIterator();
                //while(cellIterator.hasNext()) {
                for (int j=0;j<sheetCheckerArr.length;j++){

                    //Cell cell = cellIterator.next();
                    Cell cell = row.getCell(j);

                    if(validFormHead) {
                        if (cell.getCellType() == Cell.CELL_TYPE_STRING) {
                            if (cell.getStringCellValue().equals(sheetCheckerArr[j])) {
                                System.out.print(cell.getStringCellValue() + "\t\t");
                            } else {
                                System.out.print("errrrrr not same format");
                                Error_Msg = "The selected file is not in the proper format, please follow the instructions \" +\n" +
                                        "                                        \"that shown in the import page";
                                file.close();
                                return false;
                            }
                        } else {
                            System.out.print("errrrrr not string");
                            Error_Msg = "errrrrr not string";
                            file.close();
                            return false;
                        }
                    }else {
                        try {



                            switch (j){
                                case 0:

                                    if (cell.getCellType() == Cell.CELL_TYPE_STRING) {
                                        System.out.println("**"+j+"**"+cell.getCellType()+"**"+cell.getStringCellValue()+"**");
                                        if(cell.getStringCellValue().equals("")){
                                            Error_Msg = "Some of the records are empty.";
                                            file.close();
                                            return false;
                                        }else {
                                            dataRow.add(cell.getStringCellValue());
                                        }
                                    }else {
                                        System.out.print("errrrrr not same format");
                                        Error_Msg = "The selected file is not in the proper format, please follow the instructions " +
                                                "that shown in the import page";
                                        file.close();
                                        return false;
                                    }
                                    break;
                                case 1:
                                    if (cell.getCellType() == Cell.CELL_TYPE_STRING) {
                                        System.out.println("**"+j+"**"+cell.getCellType()+"**"+cell.getStringCellValue()+"**");
                                        if(cell.getStringCellValue().equals("")){
                                            Error_Msg = "Some of course's codes, please follow the instructions " +
                                                    "that shown in the import page";
                                            file.close();
                                            return false;
                                        }else if(dbs.isCoursesCodeExist(cell.getStringCellValue())){
                                            Error_Msg = "Course code: "+cell.getStringCellValue()+" is already existed, please change it in the excel sheet and" +
                                                    " follow the instructions that shown in the import page";
                                            file.close();
                                            return false;
                                        }else {
                                            dataRow.add(cell.getStringCellValue());
                                        }
                                    }else {
                                        System.out.print("errrrrr not same format");
                                        Error_Msg = "The selected file is not in the proper format, please follow the instructions " +
                                                "that shown in the import page";
                                        file.close();
                                        return false;
                                    }
                                    break;
                                case 2:
                                    if (cell.getCellType() == Cell.CELL_TYPE_NUMERIC) {
                                        System.out.println("**"+j+"**"+cell.getCellType()+"**"+cell.getNumericCellValue()+"**");
                                        if(cell.getNumericCellValue() <= 0 || cell.getNumericCellValue() > 10){
                                            Error_Msg = "Course Level: "+cell.getCellType()+" is not in the range 1-10 ";
                                            file.close();
                                            return false;
                                        }else {
                                            String value = String.valueOf(cell.getNumericCellValue());
                                            dataRow.add(String.valueOf(value.substring(0,value.indexOf("."))));
                                        }
                                }else {
                                    System.out.print("errrrrr not same format");
                                    Error_Msg = "The selected file is not in the proper format, please follow the instructions " +
                                            "that shown in the import page";
                                    file.close();
                                    return false;
                                }
                                    break;
                            }
                            //System.out.print(cell.getStringCellValue() + "\t\t");

                        }catch (NullPointerException e){
                            e.fillInStackTrace();


                            switch (j){
                                case 0:
                                    Error_Msg="Some of the records are empty, change it in the sheet and try upload it again, or choose another file.";
                                    file.close();
                                    return false;
                            }
                        }

                    }


                }

                System.out.println("");
                if(!validFormHead) {
                    sheetData.add(dataRow);
                }
                validFormHead = false;
            }
            file.close();
            FileOutputStream pout =
                    new FileOutputStream(new File(uploadFilePath));
            workbook.write(pout);
            pout.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InvalidFormatException e) {
            e.printStackTrace();
        }
        return true;
    }


    public String getErrorMsg(){
        return Error_Msg;
    }

    public void sendErrorMsg(HttpServletResponse response, HttpServletRequest request) throws IOException {

        request.getSession().setAttribute("errMsg",Error_Msg);

        //response.sendRedirect("/users/index.jsp?cmd=upload&err="+Error_Msg);
        response.sendRedirect("/program/index.jsp?name="+pname+"&id="+pid+"&cmd=upload&data="+pdataType);
        /*RequestDispatcher rd = request.getRequestDispatcher("/program/index.jsp?name="+pname+"&id="+pid+"&cmd=upload&data="+pdataType+"&err="+Error_Msg);

        try {
            rd.forward(request, response);
        } catch (ServletException e) {
            e.printStackTrace();
        }*/
    }

    public void sendSuccessMsg(HttpServletResponse response, HttpServletRequest request) throws IOException {

        //response.sendRedirect("/users/index.jsp?cmd=upload&err="+Error_Msg);
        RequestDispatcher rd = request.getRequestDispatcher("/program/index.jsp?name="+pname+"&id="+pid+"&data="+pdataType+"&cmd=confirm");

        try {
            rd.forward(request, response);
        } catch (ServletException e) {
            e.printStackTrace();
        }
    }

    public void setRequest(HttpServletRequest request){
        this.request = request;
    }

    public void setResponse(HttpServletResponse response){
        this.response = response;
    }

    public static boolean checkEmailValidation(String email) {
        boolean result = true;
        try {
            InternetAddress emailChecker = new InternetAddress(email);
            emailChecker.validate();
        } catch (AddressException ex) {
            result = false;
        }
        return result;
    }

    public ArrayList<ArrayList<String>> getSheetData(){
        return sheetData;
    }

    public void setUploadFilePath(String uploadFilePath) { this.uploadFilePath = uploadFilePath;}

    public void deleteFile() {
        File file = new File(uploadFilePath);
        file.delete();
    }

    public String getId() {
        return pid;
    }

    public String getName() {
        return pname;
    }

    public String getDataType() {
        return pdataType;
    }

}
