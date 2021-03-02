package softwaredesign;

import java.util.Stack;

public class GameManager {
    private Puzzle puzzle;
    private Matrix matrix;
    private Sequences sequences;
    private Stack<GameState> moveHistory = new Stack<>();

    public GameManager() {
        this.puzzle = new Puzzle();
        this.setupPuzzle();
        this.runGame();
    }

    private void setupPuzzle() {
        this.puzzle.getNextPuzzle();
        moveHistory.push(new GameState(this.puzzle.getBufferLen()));
        this.matrix = new Matrix(this.puzzle.getMatrixTxt());
        this.sequences = new Sequences(this.puzzle.getSeqTxt());
    }

    public void printGame() {
        System.out.print("Buffer: ");
        moveHistory.peek().getBuffer().printBuffer();
        System.out.println("\nMatrix: ");
        this.matrix.printMatrix();
        System.out.println(("\nSequences: "));
        this.sequences.printSequences();
    }

    public void runGame(){
        printGame();
    }

}
