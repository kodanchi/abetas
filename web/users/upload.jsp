<%@ page import="org.apache.poi.ss.usermodel.Cell" %>
<%@ page import="java.util.Iterator" %>
<%@ page import="java.io.*" %>
<%@ page import="org.apache.poi.hssf.usermodel.HSSFWorkbook" %>
<%@ page import="org.apache.poi.hssf.usermodel.HSSFSheet" %>
<%@ page import="org.apache.poi.ss.usermodel.Row" %>
<%@ page import="org.apache.commons.fileupload.servlet.ServletFileUpload" %>
<%@ page import="org.apache.commons.fileupload.FileItem" %>
<%@ page import="java.util.List" %>
<%@ page import="org.apache.commons.fileupload.disk.DiskFileItemFactory" %><%--
  Created by IntelliJ IDEA.
  User: Mojahed
  Date: 2/1/2016
  Time: 5:20 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<script src="/js/jquery-2.2.0.min.js" type="text/javascript"></script>
<div class="section">
    <div class="container">
        <!--         what is row -->
        <div class="row tim-row">
            <h2 class="text-center">User Management</h2>
            <legend></legend>
            <div class="col-md-8 col-md-offset-2">
                <p>Please confirm that the imported data are as intended, you can click re-upload to discard the current imported file and import it again.</p>
                <div class="panel panel-default">
                    <!-- Default panel contents -->

                    <!-- Table -->
                    <table class="table">
                        <tr>
                            <th>Firstname</th>
                            <th>Middlename</th>
                            <th>Lastname</th>
                            <th>Username</th>
                            <th>Email</th>
                            <th>Access level</th>
                        </tr>

<%
    String upload = "";
    ServletContext context = pageContext.getServletContext();
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

                                            out.println("<tr>");

                                            //while(cellIterator.hasNext()) {
                                            for (int j=0;j<sheetChecker.length;j++){

                                                Cell cell = cellIterator.next();

                                                if(validFormHead) {
                                                    if (cell.getCellType() == Cell.CELL_TYPE_STRING) {
                                                        if (cell.getStringCellValue().equals(sheetChecker[j])) {
                                                            //
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
                                                        out.print("<td>");
                                                        out.print(cell.getStringCellValue());
                                                        out.print("</td>");
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
                                            out.println("</tr>");
                                        }
                                        file.close();

                                    } catch (FileNotFoundException e) {
                                        e.printStackTrace();
                                    } catch (IOException e) {
                                        e.printStackTrace();
                                    }

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

        }else{
            System.out.println("not multipart!");
        }


    } catch (Exception e) {
        e.printStackTrace();
    }


%>
                    </table>
                </div>
                <a class="btn btn-success btn-fill" href="index.jsp?page=import">re-upload</a>
                <form method="post" action="/upload/users" >
                    <input name="file" value="<%=upload%>" hidden>
                    <button class="btn btn-primary"  type="submit">Upload</button>
                </form>
                <a class="btn btn-primary">Cancel</a>



                <!-- End of col -->
            </div>

            <!-- End of row -->
        </div>


    </div>
</div>

