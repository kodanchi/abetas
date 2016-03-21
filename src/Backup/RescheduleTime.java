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
    public RescheduleTime(){
        timerTime = new Timer();
    }
    @Override
    public void run() {

    }
    public void TimerRecschedule(int time){


        timerTime.schedule(new TimerTask() {
            @Override
            public void run() {
                TimeClass ti = new TimeClass();
                ti.cancel();


            }
        },time );

    }
}
