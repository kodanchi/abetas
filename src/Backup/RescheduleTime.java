package Backup;

import passReset.TimeClass;

import java.sql.Time;
import java.util.Timer;
import java.util.TimerTask;


/**
 * Created by Mohammed on 3/21/2016.
 */
public class RescheduleTime extends TimerTask {

    final int SECOND = 1000;
    final int MINUTE = 60 * SECOND;
    final int HOUR = 60 * MINUTE;
    final int DAY = 24 * HOUR;
    final int WEEK = 7 * DAY;
    private Timer timerTime;
    private String BACKUP_DIRECTORY = null;
    public RescheduleTime(){
        timerTime = new Timer();
    }
    public RescheduleTime(String bdir){
        timerTime = new Timer();
        BACKUP_DIRECTORY = bdir;
    }
    @Override
    public void run() {
        System.out.println("This is from resche");
        new BackupTool(BACKUP_DIRECTORY);
    }
//    public void TimerRecschedule(int time){
//
//
//        timerTime.schedule(this,MINUTE,time);
//    }
}
