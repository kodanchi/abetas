package ASDB;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.Iterator;
import java.util.List;

/**
 * Created by Mojahed on 2/2/2016.
 */
@WebServlet(name = "sheetImportServlet",urlPatterns = {"/upload/users"})
public class sheetImportServlet extends HttpServlet {
    String upload = "";
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        ServletContext context = request.getServletContext();
        String UPLOAD_DIRECTORY = context.getInitParameter("file-upload");

        try {
            //process only if its multipart content
            if(ServletFileUpload.isMultipartContent(request)){
                try {
                    List<FileItem> items = new ServletFileUpload(new DiskFileItemFactory()).parseRequest(request);

                    for(FileItem item : items){
                        /*System.out.println(item.getString()+" ----item");
                        System.out.println(item.getFieldName()+" --++--item");*/

                        String name = item.getFieldName();
                        if(name.equals( "excelInput")){

                            upload = item.getString();

                        }else{
                            System.out.println("default fired!");
                        }

                        if(!item.isFormField()){

                            System.out.println("else fired!"+ item.getName());
                            if(item.getSize() != 0) {
                                name = new File(item.getName()).getName();
                                System.out.println("else name!"+ name);

                                if (item.getSize() < 2000000) {

                                    String extension = "";

                                    int i = item.getName().lastIndexOf('.');
                                    if (i > 0) {
                                        extension = item.getName().substring(i + 1);
                                        System.out.println("file ext !"+ extension);
                                    }
                                    if (extension.equals("xls")) {
                                        item.write(new File(UPLOAD_DIRECTORY + File.separator + name));
                                        upload = UPLOAD_DIRECTORY + name;
                                        //File uploaded successfully
                                        System.out.println("File Uploaded Successfully!");
                                        System.out.println("File Uploaded to " + upload);


                                        try {

                                            FileInputStream file = new FileInputStream(new File(upload));

                                            //Get the workbook instance for XLS file
                                            HSSFWorkbook workbook = new HSSFWorkbook(file);

                                            //HSSFWorkbook workbook2 = new HSSFWorkbook(file);

                                            //Get first sheet from the workbook
                                            HSSFSheet sheet = workbook.getSheetAt(0);

                                            //Set the sheet checker in Array
                                            String[] sheetChecker = {"firstname","middlename","lastname","username","email","level"};
                                            boolean validFormHead = true;


                                            //Iterate through each rows from first sheet
                                            Iterator<Row> rowIterator = sheet.iterator();
                                            while(rowIterator.hasNext()) {
                                                Row row = rowIterator.next();

                                                //For each row, iterate through each columns
                                                Iterator<Cell> cellIterator = row.cellIterator();
                                                //while(cellIterator.hasNext()) {
                                                for (int j=0;j<sheetChecker.length;j++){

                                                    Cell cell = cellIterator.next();

                                                    if(validFormHead) {
                                                        if (cell.getCellType() == Cell.CELL_TYPE_STRING) {
                                                            if (cell.getStringCellValue().equals(sheetChecker[j])) {
                                                                System.out.print(cell.getStringCellValue() + "\t\t");
                                                            } else {
                                                                System.out.print("errrrrr not same format");
                                                                System.exit(0);
                                                            }
                                                        } else {
                                                            System.out.print("errrrrr not string");
                                                            System.exit(0);
                                                        }
                                                    }else {
                                                        if (cell.getCellType() == Cell.CELL_TYPE_STRING) {
                                                            System.out.print(cell.getStringCellValue() + "\t\t");
                                                        }else {
                                                            System.out.print("errrrrr not same format");
                                                            System.exit(0);
                                                        }
                                                    }

                                                    /*switch(cell.getCellType()) {
                                                        case Cell.CELL_TYPE_BOOLEAN:
                                                            System.out.print(cell.getBooleanCellValue() + "\t\t");
                                                            break;
                                                        case Cell.CELL_TYPE_NUMERIC:
                                                            System.out.print(cell.getNumericCellValue() + "\t\t");
                                                            break;
                                                        case Cell.CELL_TYPE_STRING:
                                                            System.out.print(cell.getStringCellValue() + "\t\t");
                                                            break;
                                                    }*/

                                                }
                                                validFormHead = false;
                                                System.out.println("");
                                            }
                                            file.close();
                                            FileOutputStream pout =
                                                    new FileOutputStream(new File(upload));
                                            workbook.write(pout);
                                            pout.close();

                                        } catch (FileNotFoundException e) {
                                            e.printStackTrace();
                                        } catch (IOException e) {
                                            e.printStackTrace();
                                        }

                                        response.setStatus(HttpServletResponse.SC_TEMPORARY_REDIRECT);
                                        response.setHeader("Location","/users/index.jsp?cmd=upload&file="+upload);

                                    } else {
                                       /* out.print("<div id=\"alert\"  class=\"alert alert-danger fade in\"  role=\"alert\" >\n" +
                                                "                    <strong id=\"alertt\" >university logo must be type of PNG</strong>\n" +
                                                "                </div>");*/
                                    }
                                } else {
                                    /*out.print("<div id=\"alert\"  class=\"alert alert-danger fade in\"  role=\"alert\" >\n" +
                                            "                    <strong id=\"alertt\" >Logo image's size exceeds 2mb</strong>\n" +
                                            "                </div>");*/
                                }
                            }else{
                                upload = null;
                            }
                        }
                    }


                } catch (Exception ex) {
                    System.out.println("File Upload Failed due to " + ex);

                }

                //db.init(uname, cname, ulogo, afname, amname, alname, ausername, apassword, aemail);
                /*out.print("<br>\n" +
                        "        <p>Congratulations, ABETAS setup done successfully, now you need to add a superuser so he can enter the programs, course, create an evaluation cycle. Click on User Management to add and manage users.</p>\n" +
                        "        <br>\n" +
                        "        <div>\n" +
                        "            \n" +
                        "            <a  href=\"users.jsp\" class=\"btn btn-primary btn-lg\" >User Management</a>\n" +
                        "            <a href=\"index.jsp\" class=\"btn btn-primary btn-lg\" >Home Page</a>\n" +
                        "\n" +
                        "        </div>");*/
            }else{
                System.out.println("not multipart!");
            }


        } catch (Exception e) {
            e.printStackTrace();
        }



    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
