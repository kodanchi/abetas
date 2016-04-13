package passReset;

/**
 * http://www.in-example.com/?p=117
 * http://stackoverflow.com/questions/158336/is-there-a-way-to-run-a-method-class-only-on-tomcat-startup
Timer for Backup
 */

import Backup.BackupTool;

import javax.naming.Context;
import javax.security.auth.message.callback.PrivateKeyCallback;
import java.util.*;

public class TimeClass extends TimerTask {
    private String BACKUP_DIRECTORY = null;
    private Timer timer;
    public TimeClass(String bdir){
        timer = new Timer();
        BACKUP_DIRECTORY = bdir;
    }
    public TimeClass(){
        timer = new Timer();
    }
    /**
     * TimeClass used to run any function period of time depend on the function and its requiremtn time
     */
    final int SECOND = 1000;
    final int MINUTE = 60 * SECOND;
    final int HOUR = 60 * MINUTE;
    final int DAY = 24 * HOUR;
    final long WEEK = 7 * DAY;
    @Override
    /**
     * run method here call backup class to be runned
     */
    public void run() {
         new Backup.BackupTool(BACKUP_DIRECTORY);
        //d.backupDB();
    }

    /**
     * timeTest method used to run Backup Class weekly
     */
    public void timeTest(){
//        TimeClass task = new TimeClass(BACKUP_DIRECTORY);

        // We use a class java.util.Timer to
        // schedule our task/job for execution

        timer.schedule(this, 0, WEEK);


    }

    public String getBACKUP_DIRECTORY() {
        return BACKUP_DIRECTORY;
    }

    public void setBACKUP_DIRECTORY(String BACKUP_DIRECTORY) {
        this.BACKUP_DIRECTORY = BACKUP_DIRECTORY;
    }
    /**
     * timeTest method used to run Backup Class weekly
     */
    public void Reschedule(long time){
        time=Integer.MAX_VALUE;

//        TimeClass task = new TimeClass(BACKUP_DIRECTORY);

        // We use a class java.util.Timer to
        // schedule our task/job for execution


        timer.schedule(this, time, time);


    }

    /**
     * timerCalculate method used to save the hash map key for
     *
     * @param email used to pass the hash key which is the email
     */
//    public void timerCalculate(String email){
//        Timer timer = new Timer();
//        timer.schedule(new TimerTask() {
//            @Override
//            /**
//             * run method here used remove the hash map key and its value withing 24 hours
//             */
//            public void run() {
//                //String email="";
//                PassCodeMap remov = new PassCodeMap();
//
//                remov.removeElement(email);
//                System.out.println("This is from rmoving email and the size is "+ PassCodeMap.getMapSize());
//
//                if (PassCodeMap.getMapSize()==0){
//                cancel();
//
//
//                }
//            }
//        },DAY,DAY);
//
//    }

}

