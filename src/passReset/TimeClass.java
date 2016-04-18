package passReset;

/**
 * http://www.in-example.com/?p=117
 * http://stackoverflow.com/questions/158336/is-there-a-way-to-run-a-method-class-only-on-tomcat-startup
 * Timer for Backup
 */

import java.util.*;

public class TimeClass extends TimerTask {
    private String BACKUP_DIRECTORY = null;
    private Timer timer;
    public TimeClass(String bdir){
        timer = new Timer();
        BACKUP_DIRECTORY = bdir;
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
    }

    /**
     * timeTest method used to run Backup Class weekly
     */
    public void timeTest(){

        // We use a class java.util.Timer to
        // schedule our task/job for execution

        timer.schedule(this, 0, WEEK);


    }

    public String getBACKUP_DIRECTORY() {
        return BACKUP_DIRECTORY;
    }

    /**
     * timeTest method used to run Backup Class weekly
     */
    public void Reschedule(long time){
        time=Integer.MAX_VALUE;

        // We use a class java.util.Timer to
        // schedule our task/job for execution

        timer.schedule(this, time, time);


    }

}

