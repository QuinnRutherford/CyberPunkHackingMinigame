package softwaredesign;

import java.util.Timer;
import java.util.TimerTask;

//TODO: function converting seconds to display format
public class TimerClass implements Runnable {

    private final int time; //placeholder for timer per puzzle
    public TimerClass(int time) {
        this.time = time;
    }

    @Override
    public void run() {
        Timer timer = new Timer();
        TimerTask timerTask = new TimerTask() {

            int tRemaining = time;
            @Override
            public void run() {
                if (tRemaining > 0) {
                    System.out.println("Time remaining: " + tRemaining);
                    tRemaining--;
                } else {
                    System.out.println("Game over!");
                    timer.cancel();
                    timer.purge();
                }
            }
        };

        timer.scheduleAtFixedRate(timerTask, 0, 1000);
    }

}
