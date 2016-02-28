package mulhim;

/**
 * Created by Mohammed on 2/6/2016.
 * http://www.in-example.com/?p=117
 * http://stackoverflow.com/questions/158336/is-there-a-way-to-run-a-method-class-only-on-tomcat-startup
Timer for Backup
 */

import java.text.SimpleDateFormat;
import java.util.*;

public class TimeClass extends TimerTask {
    final int SECOND = 1000;
    final int MINUTE = 60 * SECOND;
    final int HOUR = 60 * MINUTE;
    final int DAY = 24 * HOUR;
    final int WEEK = 7 * DAY;
    @Override
    public void run() {
        Backup d= new Backup();
        d.backupDB();
    }
   public void timeTest(){
        TimeClass task = new TimeClass();

        // We use a class java.util.Timer to
        // schedule our task/job for execution

        Timer timer = new Timer();

        timer.schedule(task, 0, WEEK);


    }
    public String timerReset() {

        String time = new SimpleDateFormat("dd-MM-yyyy").format(new Date());

        //System.out.println(currentTime);
        return time;
    }
    public void timerCalculate(String email){
Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                //String email="";
PassCodeMap remov = new PassCodeMap();

                remov.removeElement(email);
                System.out.println("This is from rmoving email and the size is "+ PassCodeMap.getMapSize());

                if (PassCodeMap.getMapSize()==0){
    cancel();


}
            }
        },MINUTE,MINUTE);

    }

}

