package ASDB;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * ImportUserSheet class is used to handle receiving multipart form inputs and validate uploaded excel sheets data cell
 * by cell.
 */
public class ImportUserSheet {
    private String uploadFilePath;
    private String UPLOAD_DIRECTORY;
    private String Error_Msg;
    private ArrayList<ArrayList<String>> sheetData;

    /**
     * Constructor is used to set upload file path and error message to null and upload directory to the temp file of the
     * server.
     */
    public ImportUserSheet(){
        uploadFilePath = "";
        Error_Msg = "";
        UPLOAD_DIRECTORY = System.getProperty("java.io.tmpdir");
    }

    /**
     * used to check and store all form inputs, furthermore it will check uploaded file (ex: allowed size, file type)
     * otherwise the an error message will be sent to the user after redirecting to the import page.
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
                        if(!item.isFormField()){

                            if(item.getSize() != 0) {
                                String name = new File(item.getName()).getName();
                                int MAX_FILE_SIZE = 5000000;
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

    public boolean UserSheetValidation(String[] sheetChecker) throws SQLException, ClassNotFoundException {
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

                for (int j=0;j<sheetCheckerArr.length;j++){
                    Cell cell = row.getCell(j);
                    if(validFormHead) {
                        if (cell.getCellType() == Cell.CELL_TYPE_STRING) {
                            if (cell.getStringCellValue().equals(sheetCheckerArr[j])) {
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
                                case 1:
                                    if (cell.getCellType() == Cell.CELL_TYPE_STRING) {
                                        if(cell.getStringCellValue().equals(" ")){
                                            dataRow.add(" ");
                                        }else {
                                            dataRow.add(cell.getStringCellValue());
                                        }

                                    }else {
                                        Error_Msg = "The selected file is not in a proper format, please follow the instructions " +
                                                "that shown below";
                                        file.close();
                                        return false;
                                    }
                                    break;
                                case 3: //checking if the usernames already exist or not in db

                                    if (cell.getCellType() == Cell.CELL_TYPE_STRING) {
                                        if(cell.getStringCellValue().equals("")){
                                            Error_Msg = "The selected file is not in a proper format, please follow the instructions " +
                                                    "that shown below";
                                            return false;
                                        }else {
                                            dataRow.add(cell.getStringCellValue());
                                        }
                                    }else {
                                        Error_Msg = "The selected file is not in a proper format, please follow the instructions " +
                                                "that shown below";
                                        file.close();
                                        return false;
                                    }
                                    break;
                                case 4://checking if the emails already exist or not in db

                                    if (cell.getCellType() == Cell.CELL_TYPE_STRING) {
                                        if(!cell.getStringCellValue().equalsIgnoreCase("superuser") &&
                                                !cell.getStringCellValue().equalsIgnoreCase("faculty") &&
                                                !cell.getStringCellValue().equalsIgnoreCase("evaluator")){
                                            Error_Msg = "The Level: "+cell.getStringCellValue()+" is not what it must be specified " +
                                                    "in the sheet!, please go back and change it then try to upload it again, or choose " +
                                                    "another file.";
                                            file.close();
                                            return false;
                                        }else {
                                            dataRow.add(cell.getStringCellValue());
                                        }
                                    }else {
                                        Error_Msg = "The selected file is not in a proper format, please follow the instructions " +
                                                "that shown below";
                                        file.close();
                                        return false;
                                    }
                                    break;
                                case 5:

                                    if(row.getCell(j-1).getStringCellValue().equals("evaluator")){
                                        dataRow.add(" ");
                                    }else {
                                        if (cell.getCellType() == Cell.CELL_TYPE_STRING) {

                                            if(cell.getStringCellValue().equals("")){
                                                Error_Msg = "The selected file is not in a proper format, please follow the instructions " +
                                                        "that shown below";
                                                file.close();
                                                return false;
                                            }else {
                                                if(!checkEmailValidation(cell.getStringCellValue())){
                                                    Error_Msg = "The Email: "+cell.getStringCellValue()+" is not in a proper " +
                                                            "format, please change it in the sheet and try to upload it again, or choose " +
                                                            "another file.";
                                                    file.close();
                                                    return false;
                                                }else {
                                                    dataRow.add(cell.getStringCellValue());
                                                }
                                            }

                                        }else {
                                            Error_Msg = "The selected file is not in a proper format, please follow the instructions " +
                                                    "that shown below";
                                            file.close();
                                            return false;
                                        }
                                    }
                                    break;
                                default:

                                    if(cell.getStringCellValue().equals(" ")){
                                        dataRow.add(" ");
                                    }else {
                                        dataRow.add(cell.getStringCellValue());
                                    }
                            }
                        }catch (NullPointerException e){
                            e.fillInStackTrace();

                            switch (j){
                                case 1:
                                    Error_Msg="First names for all users are required, change it in the sheet and try to upload it again, or choose another file.";
                                    file.close();
                                    return false;
                                case 3: //checking if the usernames already exist or not in db
                                    Error_Msg="usernames for all users are required, change it in the sheet and try to upload it again, or choose another file.";
                                    file.close();
                                    return false;
                                case 4: //checking if the emails already exist or not in db
                                    Error_Msg="Emails for all users are required, change it in the sheet and try to upload it again, or choose another file.";
                                    file.close();
                                    return false;
                                case 5:
                                    Error_Msg="Levels for all users are required, change it in the sheet and try to upload it again, or choose another file.";
                                    file.close();
                                    return false;
                                default:
                                    dataRow.add(" ");
                                    continue;
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
     * send error message through session attribute and redirect the user to the same page (users import page).
     * @param response HttpServletResponse
     * @param request HttpServletRequest
     * @throws IOException
     */
    public void sendErrorMsg(HttpServletResponse response,HttpServletRequest request) throws IOException {
        RequestDispatcher rd = request.getRequestDispatcher("/users/index.jsp?cmd=upload&err="+Error_Msg);
        try {
            rd.forward(request, response);
        } catch (ServletException e) {
            e.printStackTrace();
        }
    }

    /**
     * validate the format of given email string.
     * @param email email string to be validated
     * @return whether the email is valid
     */
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

    /**
     * returns the imported data.
     * @return sheet data as an Array of an Array of strings
     */
    public ArrayList<ArrayList<String>> getSheetData(){
        return sheetData;
    }

    /**
     * used to delete file from temp folder of the server.
     */
    public void deleteFile() {
        File file = new File(uploadFilePath);
        file.delete();
    }
}
