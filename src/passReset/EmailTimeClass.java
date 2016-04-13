package passReset;

/**
 * http://www.in-example.com/?p=117
 * http://stackoverflow.com/questions/158336/is-there-a-way-to-run-a-method-class-only-on-tomcat-startup
Timer for Backup
 */

import Backup.BackupTool;

import java.util.Timer;
import java.util.TimerTask;

public class EmailTimeClass extends TimerTask {
    private String BACKUP_DIRECTORY = null;

    public EmailTimeClass(String bdir){
        BACKUP_DIRECTORY = bdir;
    }
    public EmailTimeClass(){
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

    }

    /**
     * timeTest method used to run Backup Class weekly
     */


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

