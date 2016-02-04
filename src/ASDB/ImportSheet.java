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
public class ImportSheet {
    private HttpServletRequest request;
    private HttpServletResponse response;
    private String uploadFilePath;
    private String UPLOAD_DIRECTORY;
    private String Error_Msg;
    private String[] sheetCheckerArr;
    private ArrayList<ArrayList<String>> sheetData;
    private final int MAX_FILE_SIZE = 2000000;
    private AS_Select db = new AS_Select();


    public ImportSheet(HttpServletRequest request){
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

                        String name = item.getFieldName();
                        System.out.println("Name ="+name);



                        if(!item.isFormField()){

                            System.out.println("else fired!"+ item.getName());
                            if(item.getSize() != 0) {
                                name = new File(item.getName()).getName();
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

    public boolean UserSheetVaildation(String[] sheetChecker) throws SQLException, ClassNotFoundException {
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
                        try {
                            System.out.println("**"+j+"**"+cell.getCellType()+"**"+cell.getStringCellValue()+"**");

                            if (cell.getCellType() == Cell.CELL_TYPE_STRING) {
                                switch (j){
                                    case 1:
                                        if(cell.getStringCellValue().equals(" ")){
                                            dataRow.add(" ");
                                        }else {
                                            dataRow.add(cell.getStringCellValue());
                                        }
                                        break;
                                    case 3: //checking if the usernames already exist or not in db
                                        if(db.selectUserIfExist(cell.getStringCellValue())){
                                            Error_Msg = "The username: "+ cell.getStringCellValue()+" is already exist in the " +
                                                    "database, please change it in the sheet and try upload it again, or choose another file.";
                                            file.close();
                                            return false;
                                        }else {
                                            dataRow.add(cell.getStringCellValue());
                                        }

                                        break;
                                    case 4: //checking if the emails already exist or not in db
                                        if(db.selectEmailIfExist(cell.getStringCellValue())){
                                            Error_Msg = "The Email: "+cell.getStringCellValue()+" is already exist in the" +
                                                    "database, please change it in the sheet and try upload it again, or choose" +
                                                    " another file.";
                                            file.close();
                                            return false;
                                        }else {
                                            if(!checkEmailValidation(cell.getStringCellValue())){
                                                Error_Msg = "The Email: "+cell.getStringCellValue()+" is not in the proper " +
                                                        "format, please change it in the sheet and try upload it again, or choose " +
                                                        "another file.";
                                                file.close();
                                                return false;
                                            }else {
                                                dataRow.add(cell.getStringCellValue());
                                            }
                                        }
                                        break;
                                    case 5:
                                        if(!cell.getStringCellValue().equalsIgnoreCase("superuser") &&
                                                !cell.getStringCellValue().equalsIgnoreCase("faculty") &&
                                                !cell.getStringCellValue().equalsIgnoreCase("evaluator")){
                                            Error_Msg = "The Level: "+cell.getStringCellValue()+" is not what it must be specified " +
                                                    "in the sheet!, please go back and change then try upload it again, or choose " +
                                                    "another file.";
                                            file.close();
                                            return false;
                                        }else {
                                            dataRow.add(cell.getStringCellValue());
                                        }
                                        break;
                                    default:

                                        if(cell.getStringCellValue().equals(" ")){
                                            dataRow.add(" ");
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
                                case 1:
                                    Error_Msg="First names for all users are required, change it in the sheet and try upload it again, or choose another file.";
                                    file.close();
                                    return false;
                                case 3: //checking if the usernames already exist or not in db
                                    Error_Msg="usernames for all users are required, change it in the sheet and try upload it again, or choose another file.";
                                    file.close();
                                    return false;
                                case 4: //checking if the emails already exist or not in db
                                    Error_Msg="Emails for all users are required, change it in the sheet and try upload it again, or choose another file.";
                                    file.close();
                                    return false;
                                case 5:
                                    Error_Msg="Levels for all users are required, change it in the sheet and try upload it again, or choose another file.";
                                    file.close();
                                    return false;
                                default:

                                    System.out.println("empty cell");
                                    dataRow.add(" ");
                                    continue;


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

    public void sendErrorMsg(HttpServletResponse response,HttpServletRequest request) throws IOException {

        //response.sendRedirect("/users/index.jsp?cmd=upload&err="+Error_Msg);
        RequestDispatcher rd = request.getRequestDispatcher("/users/index.jsp?cmd=upload&err="+Error_Msg);

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
}
