package softwaredesign;

import java.util.Timer;
import java.util.TimerTask;


public class TimerClass implements Runnable {

    private final Runnable endCall;
    private final Runnable call;
    private int tRemaining; //placeholder for timer per puzzle
    public TimerTask timerTask;

    public TimerClass(int time, Runnable call, Runnable endCall) {
        this.tRemaining = time;
        this.call = call;
        this.endCall = endCall;
    }

    @Override
    public void run() {
        Timer timer = new Timer();
        timerTask = new TimerTask() {

            //int tRemaining = time;

            @Override
            public void run() {
                if (tRemaining > 0) {
                    call.run();
                    System.out.println("timer1 time" + tRemaining);
                    tRemaining--;
                } else {
                    endCall.run();
                    timer.cancel();
                    timer.purge();
                }
            }

        };

        timer.scheduleAtFixedRate(timerTask, 0, 1000);
    }

}
