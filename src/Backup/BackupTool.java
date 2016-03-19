package Backup;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Mojahed on 3/12/2016.
 */
public class BackupTool {
    /**
     * Since the superuser only the person who will do the backup , the database configuration here done manually
     */
    String dbName = "abetasdb";
    String dbUser = "root";
    String dbPass = "abetas";

    /**
     * Constructor will create the database backup
     */
    public BackupTool(String UPLOAD_DIRECTORY) {
        String executeCmd = "";

        System.out.println(" BackupTool UPLOAD_DIRECTORY : "+UPLOAD_DIRECTORY);
        Date today = new Date();
        SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("dd-MM-yyyy-HH-mm-S");
        String date = DATE_FORMAT.format(today);
        System.out.println("Today in dd-MM-yy:HH:mm:SS : " + date);
        /**
         * the next string has the command prompt in windows that will do the backup using mysqldump program, by knowing the username, password and the database name
         * it is important to mention the directory that you want to store the backup in it after providing the database information
         */

                executeCmd = "C:\\Program Files\\MySQL\\MySQL Server 5.7\\bin\\mysqldump.exe -u " + dbUser + " -p" +
                        dbPass + " " + dbName + " -r " +"\""+UPLOAD_DIRECTORY +"\\Backup_"+date.toString()+".sql\"";

        /**
         * doing the command in windows using Process class and handle the I/O operation by catch
         */
        System.out.println("executeCmd : "+executeCmd);
        Process runtimeProcess = null;
        try {
            runtimeProcess = Runtime.getRuntime().exec(executeCmd);
        } catch (IOException e) {
            e.printStackTrace();
        }
        int processComplete = 0;
        try {
                processComplete = runtimeProcess.waitFor();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        /**
         * if the process done correctly the result of complete should be zero , otherwise it is not
         */
        if (processComplete == 0) {
            System.out.println("Backup taken successfully");
        } else {
            System.out.println("Could not take mysql backup");
        }
    }
}
