package softwaredesign;

public class Main {

    public static void main (String[] args){
        GameManager game = new GameManager();
        //game.runGameLoop();

        Thread gameThread = new Thread(game);
        Thread timerThread = new Thread(new TimerClass(5));
        gameThread.start();
        timerThread.start();
        if (!timerThread.isAlive()) gameThread.interrupt();

        //game.runGameLoop();
        //Test test = new Test();

        //Thread t1 = new Thread(new TimerClass(20));
        //t1.start();
        //System.exit(0);
    }
}
