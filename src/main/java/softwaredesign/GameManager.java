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

    public void runGame() {
        Scanner scanner = new Scanner(System.in);
        while (!gameOver.getGameOver(this.sequences, this.moveHistory.peek())) {
            printGame();
            int row = 0;
            while (row <= 0 || row > matrix.getMatrixDims()) {
                try {
                    System.out.println("Choose a row:");
                    String rowStr = scanner.nextLine();
                    row = Integer.parseInt(rowStr);
                } catch (NumberFormatException e) {
                    System.out.println("Invalid format");
                }
            }

            int col = 0;
            while (col <= 0 || col > matrix.getMatrixDims()) {
                try {
                    System.out.println("Choose a column:");
                    String colStr = scanner.nextLine();
                    col = Integer.parseInt(colStr);
                } catch (NumberFormatException e) {
                    System.out.println("Invalid format");
                }
            }
        }
    }
}

