package softwaredesign;

import java.util.Stack;

//This class is to contain functions that test the other classes
public class Test {
    public void bufferTest() {
        Stack<Buffer> bufferStack = new Stack<Buffer>();

        Buffer bufferOne = new Buffer(4);
        bufferStack.push(bufferOne);
        bufferStack.peek().printBuffer();
        bufferStack.push(new Buffer(bufferStack.peek(), "A"));
        bufferStack.peek().printBuffer();
        bufferStack.push(new Buffer(bufferStack.peek(), "B"));
        bufferStack.peek().printBuffer();
        bufferStack.push(new Buffer(bufferStack.peek(), "C"));
        bufferStack.peek().printBuffer();

    }

    public void gameStateTest() {
        Stack<GameState> gsStack = new Stack<GameState>();

        GameState initial = new GameState(3);
        gsStack.push(initial);
        gsStack.peek().printGameState();
        gsStack.push(new GameState(gsStack.peek(), "A", 3));
        gsStack.peek().printGameState();
        gsStack.push(new GameState(gsStack.peek(), "B", 1));
        gsStack.peek().printGameState();
        gsStack.push(new GameState(gsStack.peek(), "C", 3));
        gsStack.peek().printGameState();

        //Uncomment this line will throw 'The buffer is full' error
        //gsStack.push(new GameState(gsStack.peek(), "error", 3));

    }

    public void matrixTest() {
        String matrixTxt = "1c bd e9 55 e9\n1c 1c 1c e9 1c\n55 55 55 e9 1c\n55 55 1c bd 55\n1c 55 1c 55 1c";

        Matrix m = new Matrix(matrixTxt);

        System.out.println(m.getMatrixElement(0, 1));

        m.printMatrix();

    }

    public void seqTest() {
        String seqTxt = "e9 1c 1c\n" +
                "1c 1c 1c\n" +
                "1c bd e9";
        Sequences seq = new Sequences(seqTxt);

        System.out.println(seq.getNSeq(1)[0]);
        seq.printSequences();
    }

    public void gameOverTest() {
        Stack<GameState> gsStack = new Stack<GameState>();
        GameOver gameOver = new GameOver();
        Sequences sequences = new Sequences("A B\nD B E\n");
        sequences.printSequences();

        GameState initial = new GameState(5);
        gsStack.push(initial);
        gsStack.peek().printGameState();

        //gsStack.push(new GameState(gsStack.peek(), "A", 3));
        gsStack.push(new GameState(gsStack.peek(), "D", 3));
        //gsStack.push(new GameState(gsStack.peek(), "Z", 3));
        gsStack.push(new GameState(gsStack.peek(), "B", 3));
        gsStack.push(new GameState(gsStack.peek(), "E", 3));

        gsStack.peek().printGameState();
        System.out.println(gameOver.getGameOver(sequences, gsStack.peek()));

    }

    public void ArrayTest() {
        Sequences sequences = new Sequences("A B\nD B E\nA B\n");
        for (int i = 0; i < sequences.getNumberOfSeq(); i++) {
            String [] temp = sequences.getNSeq(i);
            System.out.println(temp.length);
        }
    }

}
