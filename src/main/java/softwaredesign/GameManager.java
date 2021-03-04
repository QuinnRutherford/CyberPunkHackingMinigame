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
        moveHistory.peek().printGameState();
        System.out.println("\nMatrix: ");
        this.matrix.printMatrix();
        System.out.println(("\nSequences: "));
        this.sequences.printSequences();
    }

    public void runGame(){
        Scanner scanner = new Scanner(System.in);
        while (!gameOver.getGameOver(this.sequences, this.moveHistory.peek().getBuffer())) {

        }
        printGame();
    }

    private void getMove(Scanner scanner){
        System.out.println("Choose row (start at 1)");
    }

}

