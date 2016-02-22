package mulhim;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by Mohammed on 2/22/2016.
 */
public class TImerCompare {
    Timer timer;

    public TImerCompare(int seconds) {
        timer = new Timer();
        timer.schedule(new CrunchifyReminder(), seconds * 1000);
    }



    class CrunchifyReminder extends TimerTask {
        public void run() {
            System.out.format("Timer Task Finished..!%n");
            timer.cancel(); // Terminate the timer thread

        }
    }

    public  void test() {
        new TImerCompare(20);
        System.out.format("Task scheduled.. Now wait for 5 sec to see next message..%n");
    }
}

