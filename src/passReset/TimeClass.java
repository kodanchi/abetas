package passReset;

/**
 * Created by Mohammed on 2/6/2016.
 * http://www.in-example.com/?p=117
 * http://stackoverflow.com/questions/158336/is-there-a-way-to-run-a-method-class-only-on-tomcat-startup
Timer for Backup
 */

import Backup.BackupTool;

import java.util.*;

public class TimeClass extends TimerTask {
    private String BACKUP_DIRECTORY = null;

    public TimeClass(String bdir){
        BACKUP_DIRECTORY = bdir;
    }
    public TimeClass(){
    }
    /**
     * TimeClass used to run any function period of time depend on the function and its requiremtn time
     */
    final int SECOND = 1000;
    final int MINUTE = 60 * SECOND;
    final int HOUR = 60 * MINUTE;
    final int DAY = 24 * HOUR;
    final int WEEK = 7 * DAY;
    @Override
    /**
     * run method here call backup class to be runned
     */
    public void run() {
        Backup.BackupTool d= new Backup.BackupTool(BACKUP_DIRECTORY);
        //d.backupDB();
    }

    /**
     * timeTest method used to run Backup Class weekly
     */
    public void timeTest(){
        TimeClass task = new TimeClass(BACKUP_DIRECTORY);

        // We use a class java.util.Timer to
        // schedule our task/job for execution

        Timer timer = new Timer();

        timer.schedule(task, 0, WEEK);


    }

    /**
     * timerCalculate method used to save the hash map key for
     *
     * @param email used to pass the hash key which is the email
     */
    public void timerCalculate(String email){
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            /**
             * run method here used remove the hash map key and its value withing 24 hours
             */
            public void run() {
                //String email="";
                PassCodeMap remov = new PassCodeMap();

                remov.removeElement(email);
                System.out.println("This is from rmoving email and the size is "+ PassCodeMap.getMapSize());

                if (PassCodeMap.getMapSize()==0){
                cancel();


                }
            }
        },DAY,DAY);

    }

}

