package softwaredesign;

public class Main {
    public static void main (String[] args){
        System.out.println("Welcome to Software Design");
        System.out.println("This is assignment 1");

        GameManager game = new GameManager();

        Thread t1 = new Thread(new TimerClass(20));
        t1.start();

    }
}
