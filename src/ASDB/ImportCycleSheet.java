package ASDB;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * Created by Mojahed on 2/3/2016.
 */
public class ImportCycleSheet {
    private HttpServletRequest request;
    private HttpServletResponse response;
    private String uploadFilePath;
    private String UPLOAD_DIRECTORY;
    private String Error_Msg;
    private String cycle;
    private String term;
    private String programID;
    private String courseCode;
    private String courseName;
    private String section;
    private String pdataType ;
    private String[] sheetCheckerArr;
    private ArrayList<ArrayList<String>> sheetData;
    private final int MAX_FILE_SIZE = 2000000;
    private P_AS_Select db = new P_AS_Select();


    public ImportCycleSheet(HttpServletRequest request){
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
                            case "cycle":
                                cycle = item.getString();
                                System.out.println("cycle is :"+ cycle);
                                break;
                            case "term":
                                term = item.getString();
                                System.out.println("term is :"+ term);
                                break;
                            case "programID":
                                programID = item.getString();
                                System.out.println("programID is :"+ programID);
                                break;
                            case "courseCode":
                                courseCode = item.getString();
                                System.out.println("courseCode is :"+ courseCode);
                                break;
                            case "courseName":
                                courseName = item.getString();
                                System.out.println("courseName is :"+ courseName);
                                break;
                            case "section":
                                section = item.getString();
                                System.out.println("name is :"+ section);
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
                                    if (extension.equals("xls")) {
                                        item.write(new File(UPLOAD_DIRECTORY + "\\"+ File.separator + name));
                                        uploadFilePath = UPLOAD_DIRECTORY + "\\"+ name;
                                        //File uploaded successfully
                                        System.out.println("File Uploaded Successfully!");
                                        System.out.println("File Uploaded to " + uploadFilePath);


                                        return true;



                                    } else {
                                        System.out.println("university logo must be type of PNG");
                                        Error_Msg = "Logo image's size exceeds 2mb";
                                        return false;
                                    }
                                } else {
                                    System.out.println("Logo image's size exceeds 2mb");
                                    Error_Msg = "Logo image's size exceeds 2mb";
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

    public boolean importSheetForString(String[] sheetChecker){
        try {

            FileInputStream file = new FileInputStream(new File(uploadFilePath));

            //Get the workbook instance for XLS file
            HSSFWorkbook workbook = new HSSFWorkbook(file);

            //HSSFWorkbook workbook2 = new HSSFWorkbook(file);

            //Get first sheet from the workbook
            HSSFSheet sheet = workbook.getSheetAt(0);

            //Set the sheet checker in Array
            String[] sheetCheckerArr = sheetChecker;
            boolean validFormHead = true;


            //Iterate through each rows from first sheet
            Iterator<Row> rowIterator = sheet.iterator();
            while(rowIterator.hasNext()) {
                Row row = rowIterator.next();

                //For each row, iterate through each columns
                Iterator<Cell> cellIterator = row.cellIterator();
                //while(cellIterator.hasNext()) {
                for (int j=0;j<sheetCheckerArr.length;j++){


                    Cell cell = null;
                    try {
                        cell = cellIterator.next();
                    }catch (NoSuchElementException e){
                        Error_Msg = "Some of the data are missing in the sheet or the sheet is not in the proper format, " +
                                "please add them in the sheet and try upload it again, or choose another file. ";
                        file.close();
                        return false;
                    }

                    if(validFormHead) {
                        if (cell.getCellType() == Cell.CELL_TYPE_STRING) {
                            if (cell.getStringCellValue().equals(sheetCheckerArr[j])) {
                                System.out.print(cell.getStringCellValue() + "\t\t");
                            } else {
                                System.out.print("errrrrr not same format");
                                Error_Msg = "errrrrr not same format";
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
                        if (cell.getCellType() == Cell.CELL_TYPE_STRING) {
                            System.out.print(cell.getStringCellValue() + "\t\t");
                        }else {
                            System.out.print("errrrrr not same format");
                            file.close();
                            return false;
                        }
                    }


                }
                validFormHead = false;
                System.out.println("");
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
        }
        return true;
    }

    public boolean objSheetVaildation(String[] sheetChecker) throws SQLException, ClassNotFoundException {
        try {

            FileInputStream file = new FileInputStream(new File(uploadFilePath));

            //Get the workbook instance for XLS file
            HSSFWorkbook workbook = new HSSFWorkbook(file);

            //HSSFWorkbook workbook2 = new HSSFWorkbook(file);

            //Get first sheet from the workbook
            HSSFSheet sheet = workbook.getSheetAt(0);

            //Set the sheet checker in Array
            String[] sheetCheckerArr = sheetChecker;
            boolean validFormHead = true;


            //Iterate through each rows from first sheet
            Iterator<Row> rowIterator = sheet.iterator();

            //Store the data in ArrayList
            sheetData = new ArrayList<ArrayList<String>>();

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
        }
        return true;
    }


    public boolean studentsSheetVaildation(String[] sheetChecker) throws SQLException, ClassNotFoundException {
        try {

            FileInputStream file = new FileInputStream(new File(uploadFilePath));

            //Get the workbook instance for XLS file
            HSSFWorkbook workbook = new HSSFWorkbook(file);

            //HSSFWorkbook workbook2 = new HSSFWorkbook(file);

            //Get first sheet from the workbook
            HSSFSheet sheet = workbook.getSheetAt(0);

            //Set the sheet checker in Array
            String[] sheetCheckerArr = sheetChecker;
            boolean validFormHead = true;


            //Iterate through each rows from first sheet
            Iterator<Row> rowIterator = sheet.iterator();

            //Store the data in ArrayList
            sheetData = new ArrayList<ArrayList<String>>();

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
                            cell.setCellType(Cell.CELL_TYPE_STRING);
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
                                        break;
                                    case 1:
                                        if(cell.getStringCellValue().equals("")){
                                        Error_Msg = "Some of the records are empty.";
                                        file.close();
                                        return false;
                                    }else {
                                        dataRow.add(cell.getStringCellValue());
                                    }
                                        break;
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
        }
        return true;
    }


    public boolean PIsSheetVaildation(String[] sheetChecker) throws SQLException, ClassNotFoundException {
        try {

            FileInputStream file = new FileInputStream(new File(uploadFilePath));

            //Get the workbook instance for XLS file
            HSSFWorkbook workbook = new HSSFWorkbook(file);

            //HSSFWorkbook workbook2 = new HSSFWorkbook(file);

            //Get first sheet from the workbook
            HSSFSheet sheet = workbook.getSheetAt(0);

            //Set the sheet checker in Array
            String[] sheetCheckerArr = sheetChecker;
            boolean validFormHead = true;


            //Iterate through each rows from first sheet
            Iterator<Row> rowIterator = sheet.iterator();

            //Store the data in ArrayList
            sheetData = new ArrayList<ArrayList<String>>();

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
                            cell.setCellType(Cell.CELL_TYPE_STRING);
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
                                        break;
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
        }
        return true;
    }


    public boolean outcomesSheetVaildation(String[] sheetChecker) throws SQLException, ClassNotFoundException {
        try {

            FileInputStream file = new FileInputStream(new File(uploadFilePath));

            //Get the workbook instance for XLS file
            HSSFWorkbook workbook = new HSSFWorkbook(file);

            //HSSFWorkbook workbook2 = new HSSFWorkbook(file);

            //Get first sheet from the workbook
            HSSFSheet sheet = workbook.getSheetAt(0);

            //Set the sheet checker in Array
            String[] sheetCheckerArr = sheetChecker;
            boolean validFormHead = true;


            //Iterate through each rows from first sheet
            Iterator<Row> rowIterator = sheet.iterator();

            //Store the data in ArrayList
            sheetData = new ArrayList<ArrayList<String>>();

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
        }
        return true;
    }

    public boolean coursesSheetVaildation(String[] sheetChecker) throws SQLException, ClassNotFoundException {
        try {

            FileInputStream file = new FileInputStream(new File(uploadFilePath));

            //Get the workbook instance for XLS file
            HSSFWorkbook workbook = new HSSFWorkbook(file);

            //HSSFWorkbook workbook2 = new HSSFWorkbook(file);

            //Get first sheet from the workbook
            HSSFSheet sheet = workbook.getSheetAt(0);

            //Set the sheet checker in Array
            String[] sheetCheckerArr = sheetChecker;
            boolean validFormHead = true;


            //Iterate through each rows from first sheet
            Iterator<Row> rowIterator = sheet.iterator();

            //Store the data in ArrayList
            sheetData = new ArrayList<ArrayList<String>>();

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
                                        break;
                                    case 1:
                                        if(String.valueOf(cell.getNumericCellValue()).equals("")){
                                            Error_Msg = "Some of the records are empty.";
                                            file.close();
                                            return false;
                                        }else {
                                            dataRow.add(cell.getStringCellValue());
                                        }
                                        break;
                                    case 2:
                                        if(cell.getStringCellValue().equals("")){
                                            Error_Msg = "Some of the records are empty.";
                                            file.close();
                                            return false;
                                        }else {
                                            dataRow.add(cell.getStringCellValue());
                                        }
                                        break;
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
        }
        return true;
    }


    public String getErrorMsg(){
        return Error_Msg;
    }

    public void sendErrorMsg(HttpServletResponse response, HttpServletRequest request) throws IOException {

        request.getSession().setAttribute("errMsg",Error_Msg);

        //response.sendRedirect("/users/index.jsp?cmd=upload&err="+Error_Msg);
        //response.sendRedirect("/cycle/index.jsp?cycle="+ cycle +"&term="+ term +"&cmd=upload&data="+pdataType);
        String url = null;
        if(pdataType.equals("students")){
            url ="/cycle/index.jsp?cycle="+ cycle +"&term="+ term  +"&programID=" + programID + "&courseCode=" + courseCode +
                    "&courseName=" + courseName + "&section=" + section +"&cmd=upload&data="+pdataType;
        }else if(pdataType.equals("pis")){
            url ="/cycle/index.jsp?cycle="+ cycle +"&term="+ term  +"&programID=" + programID +"&cmd=upload&data="+pdataType;
        }
        response.sendRedirect(url);
        /*RequestDispatcher rd = request.getRequestDispatcher("/program/index.jsp?name="+term+"&id="+cycle+"&cmd=upload&data="+pdataType+"&err="+Error_Msg);

        try {
            rd.forward(request, response);
        } catch (ServletException e) {
            e.printStackTrace();
        }*/
    }

    public void sendSuccessMsg(HttpServletResponse response, HttpServletRequest request) throws IOException {

        //response.sendRedirect("/users/index.jsp?cmd=upload&err="+Error_Msg);
        //RequestDispatcher rd = request.getRequestDispatcher("/cycle/index.jsp?cycle="+ term +"&term="+ cycle +"&data="+pdataType+"&cmd=confirm");
        String url = null;
        if(pdataType.equals("students")){
            url ="/cycle/index.jsp?cycle="+ cycle +"&term="+ term  +"&programID=" + programID + "&courseCode=" + courseCode +
                    "&courseName=" + courseName + "&section=" + section +"&cmd=confirm&data="+pdataType;
        }else if(pdataType.equals("pis")){
            url ="/cycle/index.jsp?cycle="+ cycle +"&term="+ term   +"&programID=" + programID +"&cmd=confirm&data="+pdataType;
        }

        response.sendRedirect(url);




        /*try {
            rd.forward(request, response);
        } catch (ServletException e) {
            e.printStackTrace();
        }*/
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
        return cycle;
    }

    public String getName() {
        return term;
    }

    public String getDataType() {
        return pdataType;
    }

}