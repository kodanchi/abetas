<%@ page import="org.apache.poi.hssf.usermodel.HSSFWorkbook" %>
<%@ page import="org.apache.poi.hssf.usermodel.HSSFSheet" %>
<%@ page import="org.apache.poi.ss.usermodel.Row" %>
<%@ page import="java.util.Iterator" %>
<%@ page import="org.apache.poi.ss.usermodel.Cell" %>
<%@ page import="java.io.*" %>
<%!
    private static class imprte {

        public void  imprtExcl(){
            try {

                FileInputStream file = new FileInputStream(new File("C:\\test.xls"));

                //Get the workbook instance for XLS file
                HSSFWorkbook workbook = new HSSFWorkbook(file);

                //Get first sheet from the workbook
                HSSFSheet sheet = workbook.getSheetAt(0);

                //Iterate through each rows from first sheet
                Iterator<Row> rowIterator = sheet.iterator();
                while(rowIterator.hasNext()) {
                    Row row = rowIterator.next();

                    //For each row, iterate through each columns
                    Iterator<Cell> cellIterator = row.cellIterator();
                    while(cellIterator.hasNext()) {

                        Cell cell = cellIterator.next();

                        switch(cell.getCellType()) {
                            case Cell.CELL_TYPE_BOOLEAN:
                                System.out.print(cell.getBooleanCellValue() + "\t\t");
                                break;
                            case Cell.CELL_TYPE_NUMERIC:
                                System.out.print(cell.getNumericCellValue() + "\t\t");
                                break;
                            case Cell.CELL_TYPE_STRING:
                                System.out.print(cell.getStringCellValue() + "\t\t");
                                break;
                        }
                    }
                    System.out.println("");
                }
                file.close();
                FileOutputStream out =
                        new FileOutputStream(new File("C:\\test.xls"));
                workbook.write(out);
                out.close();

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <%
        new imprte().imprtExcl(); %>
</head>
<body>

</body>
</html>
