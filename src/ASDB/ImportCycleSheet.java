package ASDB;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * ImportCycleSheet class is used to handle receiving multipart form inputs and validate uploaded excel sheets data cell
 * by cell.
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
    private ArrayList<ArrayList<String>> sheetData;
    private final int MAX_FILE_SIZE = 5000000;
    private P_AS_Select dbs = new P_AS_Select();


    /**
     * Constructor is used to set upload file path and error message to null and upload directory to the temp file of the
     * server.
     */
    public ImportCycleSheet(){
        uploadFilePath = "";
        Error_Msg = "";
        UPLOAD_DIRECTORY = System.getProperty("java.io.tmpdir");
    }

    /**
     * sheetValidation is used to check and store all form inputs, furthermore it will check uploaded file (ex: allowed size,
     * file type)
     * @param request HttpServletRequest
     * @return indicates whether the uploaded file is valid or not.
     */
    public boolean sheetValidation(HttpServletRequest request){


        try {
            //process only if its multipart content
            if(ServletFileUpload.isMultipartContent(request)){
                try {
                    List<FileItem> items = new ServletFileUpload(new DiskFileItemFactory()).parseRequest(request);

                    for(FileItem item : items){

                        switch (item.getFieldName()){
                            case "cycle":
                                cycle = item.getString();
                                break;
                            case "term":
                                term = item.getString();
                                break;
                            case "programID":
                                programID = item.getString();
                                break;
                            case "courseCode":
                                courseCode = item.getString();
                                break;
                            case "courseName":
                                courseName = item.getString();
                                break;
                            case "section":
                                section = item.getString();
                                break;
                            case "data-type":
                                pdataType= item.getString();
                                break;
                        }


                        if(!item.isFormField()){
                            if(item.getSize() != 0) {
                                String name = new File(item.getName()).getName();

                                if (item.getSize() < MAX_FILE_SIZE) {
                                    String extension = "";
                                    int i = item.getName().lastIndexOf('.');
                                    if (i > 0) {
                                        extension = item.getName().substring(i + 1);
                                    }
                                    if (extension.equals("xls") || extension.equals("xlsx")) {
                                        item.write(new File(UPLOAD_DIRECTORY + "\\"+ File.separator + name));
                                        uploadFilePath = UPLOAD_DIRECTORY + "\\"+ name;
                                        return true;
                                    } else {
                                        Error_Msg = "The uploaded file must have one of the following extensions: xls, xlsx";
                                        return false;
                                    }
                                } else {
                                    Error_Msg = "The uploaded file's size must be less than 5 MB";
                                    return false;
                                }
                            }else{
                                uploadFilePath = null;
                                return false;
                            }
                        }
                    }
                } catch (Exception ex) {
                    Error_Msg = "File Upload Failed due to " + ex;
                    return false;
                }
            }else{
                Error_Msg = "Error! Contact the support team, CODE: NotMultipart!";
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    /**
     * studentsSheetValidation is used to check excel file contain students data which will be validated cell by cell.
     * @param sheetChecker string array of columns names that need to be checked by.
     * @return indicates whether the uploaded file is valid or not.
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public boolean studentsSheetValidation(String[] sheetChecker) throws SQLException, ClassNotFoundException {
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

            if(sheet.getPhysicalNumberOfRows() == 0){
                Error_Msg = "The selected file is not in a proper format, please follow the instructions " +
                        "that shown below";
                file.close();
                return false;
            }

            //Iterate through each rows as an ArrayList
            ArrayList<String>  dataRow;
            while(rowIterator.hasNext()) {
                dataRow = new ArrayList<String>();
                Row row = rowIterator.next();
                //while(cellIterator.hasNext()) {
                for (int j=0;j<sheetCheckerArr.length;j++){
                    //Cell cell = cellIterator.next();
                    Cell cell = row.getCell(j);
                    if(validFormHead) {
                        if (cell.getCellType() == Cell.CELL_TYPE_STRING) {
                            if (cell.getStringCellValue().equals(sheetCheckerArr[j])) {
                                System.out.print(cell.getStringCellValue() + "\t\t");
                            } else {
                                Error_Msg = "The selected file is not in a proper format, please follow the instructions " +
                                        "that shown below";
                                file.close();
                                return false;
                            }
                        } else {
                            Error_Msg = "The selected file is not in a proper format, please follow the instructions " +
                                    "that shown below";
                            file.close();
                            return false;
                        }
                    }else {
                        try {
                            cell.setCellType(Cell.CELL_TYPE_STRING);
                            if (cell.getCellType() == Cell.CELL_TYPE_STRING) {
                                switch (j){
                                    case 0:
                                        if(cell.getStringCellValue().equals("")){
                                            Error_Msg = "All students IDs are required, change it in the sheet and try " +
                                                    "upload it again, or choose another file";
                                            file.close();
                                            return false;
                                        }else {
                                            dataRow.add(cell.getStringCellValue());
                                        }
                                        break;
                                    case 1:
                                        if(cell.getStringCellValue().equals("")){
                                        Error_Msg = "All students names are required, change it in the sheet and try upload " +
                                                "it again, or choose another file";
                                        file.close();
                                        return false;
                                    }else {
                                        dataRow.add(cell.getStringCellValue());
                                    }
                                        break;
                                }
                            }else {
                                Error_Msg = "The selected file is not in a proper format, please follow the instructions " +
                                        "that shown in the import page";
                                file.close();
                                return false;
                            }
                        }catch (NullPointerException e){
                            e.fillInStackTrace();
                            switch (j){
                                case 0:
                                    Error_Msg="Some of the records are empty, change it in the sheet and try upload it " +
                                            "again, or choose another file.";
                                    file.close();
                                    return false;
                            }
                        }
                    }
                }

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

    /**
     * PIsSheetValidation is used to check excel file contain performance indicators data which will be validated cell by cell.
     * @param sheetChecker string array of columns names that need to be checked by.
     * @return indicates whether the uploaded file is valid or not.
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public boolean PIsSheetValidation(String[] sheetChecker) throws SQLException, ClassNotFoundException {
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

            if(sheet.getPhysicalNumberOfRows() == 0){
                Error_Msg = "The selected file is not in a proper format, please follow the instructions " +
                        "that shown below";
                file.close();
                return false;
            }
            //Iterate through each rows as an ArrayList
            ArrayList<String>  dataRow;
            while(rowIterator.hasNext()) {
                dataRow = new ArrayList<String>();
                Row row = rowIterator.next();
                //while(cellIterator.hasNext()) {
                for (int j=0;j<sheetCheckerArr.length;j++){
                    //Cell cell = cellIterator.next();
                    Cell cell = row.getCell(j);

                    if(validFormHead) {
                        if (cell.getCellType() == Cell.CELL_TYPE_STRING) {
                            if (cell.getStringCellValue().equals(sheetCheckerArr[j])) {
                                System.out.print(cell.getStringCellValue() + "\t\t");
                            } else {
                                Error_Msg = "The selected file is not in a proper format, please follow the instructions " +
                                                           "that shown below";
                                file.close();
                                return false;
                            }
                        } else {
                            Error_Msg = "The selected file is not in a proper format, please follow the instructions " +
                                    "that shown below";
                            file.close();
                            return false;
                        }
                    }else {
                        try {
                            switch (j){
                                case 0:
                                    if (cell.getCellType() == Cell.CELL_TYPE_STRING) {
                                        if(cell.getStringCellValue().equals("")){
                                            Error_Msg = "All performance indicator names are required, change it in the " +
                                                    "sheet and try upload it again, or choose another file";
                                            file.close();
                                            return true;
                                        }else {
                                            dataRow.add(cell.getStringCellValue());
                                        }
                                    }else {
                                        Error_Msg = "The selected file is not in the proper format, please follow the " +
                                                "instructions that shown in the import page";
                                        file.close();
                                        return false;
                                    }
                                    break;
                                case 1:
                                    if (cell.getCellType() == Cell.CELL_TYPE_NUMERIC) {
                                        if(cell.getNumericCellValue()<0||cell.getNumericCellValue()>100){
                                            Error_Msg = "All threshold values are required and must between 0 and 100, " +
                                                    "change it in the sheet and try upload it again, or choose another file";
                                            file.close();
                                            return true;
                                        }else {
                                            dataRow.add(cell.getNumericCellValue()+"");
                                        }
                                    }else {
                                        Error_Msg = "The selected file is not in the proper format, please follow the " +
                                                "instructions that shown in the import page";
                                        file.close();
                                        return false;
                                    }
                                    break;
                            }

                        }catch (NullPointerException e){
                            e.fillInStackTrace();


                            switch (j){
                                case 0:
                                    Error_Msg="Some of the records are empty, change it in the sheet and try upload it " +
                                            "again, or choose another file.";
                                    file.close();
                                    return false;
                            }
                        }
                    }
                }
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


    /**
     * send error message through session attribute and redirect the user to the same page (students import or performance
     * indicators import page).
     * @param response HttpServletResponse
     * @param request HttpServletRequest
     * @throws IOException
     */
    public void sendErrorMsg(HttpServletResponse response, HttpServletRequest request) throws IOException {

        request.getSession().setAttribute("errMsg",Error_Msg);
        String url = null;
        if(pdataType.equals("students")){
            url ="/cycle/index.jsp?cycle="+ cycle +"&term="+ term  +"&programID=" + programID + "&courseCode=" + courseCode +
                    "&courseName=" + courseName + "&section=" + section +"&cmd=upload&data="+pdataType;
        }else if(pdataType.equals("pis")){
            url ="/cycle/index.jsp?cycle="+ cycle +"&term="+ term  +"&programID=" + programID +"&cmd=upload&data="+pdataType;
        }
        response.sendRedirect(url);
    }

    /**
     * send success message through session attribute and redirect the user to the same page (students import or performance
     * indicators import page)
     * @param response HttpServletResponse
     * @throws IOException
     */
    public void sendSuccessMsg(HttpServletResponse response) throws IOException {

        String url = null;
        if(pdataType.equals("students")){
            url ="/cycle/index.jsp?cycle="+ cycle +"&term="+ term  +"&programID=" + programID + "&courseCode=" + courseCode +
                    "&courseName=" + courseName + "&section=" + section +"&cmd=confirm&data="+pdataType;
        }else if(pdataType.equals("pis")){
            url ="/cycle/index.jsp?cycle="+ cycle +"&term="+ term   +"&programID=" + programID +"&cmd=confirm&data="+pdataType;
        }
        response.sendRedirect(url);
    }

    /**
     * set request object to this
     * @param request
     */
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
