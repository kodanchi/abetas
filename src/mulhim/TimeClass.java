package mulhim;

/**
 * Created by Mohammed on 2/6/2016.
 * http://www.in-example.com/?p=117
 * http://stackoverflow.com/questions/158336/is-there-a-way-to-run-a-method-class-only-on-tomcat-startup
Timer for Backup
 */
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalTime;
import java.time.Period;
import java.time.Instant;
import java.util.*;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.TimeUnit;

public class TimeClass extends TimerTask {
    @Override
    public void run() {
        Backup d= new Backup();
        d.backupDB();
    }
   public void timeTest(){
        TimeClass task = new TimeClass();

        // We use a class java.util.Timer to
        // schedule our task/job for execution
        final int SECOND = 1000;
       final int MINUTE = 60 * SECOND;
        final int HOUR = 60 * MINUTE;
        final int DAY = 24 * HOUR;
       final int WEEK = 7 * DAY;
        Timer timer = new Timer();

        timer.schedule(task, 0, WEEK);


    }
    public void timerReset() throws InterruptedException {

        String currentTime = new SimpleDateFormat("dd HH:mm").format(new Date());
        String timeToCompare = "2016.02.15 16:21";
        System.out.println(currentTime);
        ArrayList<ArrayList<String>> obj = new ArrayList<ArrayList<String>>();
        ArrayList<String> in= new ArrayList<String>();
        in.add("Test 1");
        in.add("Test 2");
        obj.add(in);


//        boolean x = currentTime.equals(timeToCompare);
//        boolean y= currentTime.startsWith("1");
//        System.out.println(y);
     
    }

}

