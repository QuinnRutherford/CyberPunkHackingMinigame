package softwaredesign;

import java.util.Timer;
import java.util.TimerTask;


public class TimerClass implements Runnable {

    private final int time; //placeholder for timer per puzzle
    private final Runnable callBack;

    public TimerClass(int time, Runnable callBack) {
        this.time = time;
        this.callBack = callBack;
    }

    @Override
    public void run() {
        Timer timer = new Timer();
        TimerTask timerTask = new TimerTask() {

            int tRemaining = time;

            @Override
            public void run() {
                if (tRemaining > 0) {
                    tRemaining--;
                } else {
                    callBack.run();
                    timer.cancel();
                    timer.purge();
                }
            }

        };

        timer.scheduleAtFixedRate(timerTask, 0, 1000);
    }

}
