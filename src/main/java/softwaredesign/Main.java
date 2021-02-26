package softwaredesign;

import java.util.Stack;

public class Main {
    public static void main (String[] args){
        System.out.println("Welcome to Software Design");
        System.out.println("This is assignment 1");

        GameStateTest();

        //GameManager game = new GameManager();

        //Thread t1 = new Thread(new TimerClass(20));
        //t1.start();

    }

    //This is a small test for the Buffer class
    //remove before submission
    public static void BufferTest() {
        Stack<Buffer> bufferStack = new Stack<Buffer>();

        Buffer bufferOne = new Buffer(4);
        bufferStack.push(bufferOne);
        bufferStack.peek().printValues();
        bufferStack.push(new Buffer(bufferStack.peek(), "A"));
        bufferStack.peek().printValues();
        bufferStack.push(new Buffer(bufferStack.peek(), "B"));
        bufferStack.peek().printValues();
        bufferStack.push(new Buffer(bufferStack.peek(), "C"));
        bufferStack.peek().printValues();
    }

    public static void GameStateTest() {
        Stack<GameState> gsStack = new Stack<GameState>();

        GameState initial = new GameState(3);
        gsStack.push(initial);
        gsStack.peek().printValues();
        gsStack.push(new GameState(gsStack.peek(), "A", 3));
        gsStack.peek().printValues();
        gsStack.push(new GameState(gsStack.peek(), "B", 1));
        gsStack.peek().printValues();
        gsStack.push(new GameState(gsStack.peek(), "C", 3));
        gsStack.peek().printValues();

        //Uncomment this line will throw 'The buffer is full' error
        //gsStack.push(new GameState(gsStack.peek(), "error", 3));

    }
}
