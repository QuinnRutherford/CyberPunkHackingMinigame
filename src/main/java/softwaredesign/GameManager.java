package softwaredesign;

import java.util.Stack;
import java.util.Scanner;

public class GameManager {
    private Puzzle puzzle;
    private Matrix matrix;
    private Sequences sequences;
    private GameOver gameOver;
    private Stack<GameState> moveHistory = new Stack<>();

    public GameManager() {
        this.puzzle = new Puzzle();
        this.setupPuzzle();
        this.gameOver = new GameOver();
        this.runGame();
    }

    private void setupPuzzle() {
        this.puzzle.getNextPuzzle();
        moveHistory.push(new GameState(this.puzzle.getBufferLen()));
        this.matrix = new Matrix(this.puzzle.getMatrixTxt());
        this.sequences = new Sequences(this.puzzle.getSeqTxt());
    }

    public void printGame() {
        System.out.println(("\nSequences: "));
        this.sequences.printSequences();
        System.out.println("\nMatrix: ");
        this.matrix.printMatrix();
        moveHistory.peek().printGameState();
    }

    public void runGame(){
        printGame();
        /*String str = "ss\n";
        String s = null;
        Scanner scanner = new Scanner(System.in);
        while (!gameOver.getGameOver(this.sequences, this.moveHistory.peek().getBuffer())) {
            //printGame();
            System.out.print("Write something: ");
            while(scanner.hasNextLine()){
                s = scanner.nextLine();
            }
            try {
                Thread.sleep(5000);
            } catch(InterruptedException e){
                System.out.println(e.getMessage());
            }

            System.out.println("You wrote: " + s);
        }*/
    }
}

