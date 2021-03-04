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
        Scanner scanner = new Scanner(System.in);
        int vibecheck = scanner.nextInt();
        System.out.println(vibecheck);
        //while (!gameOver.getGameOver(this.sequences, this.moveHistory.peek().getBuffer())) {
            //printGame();

            /*
            int row = 0;
            //while (row <= 0 && row > matrix.getMatrix().length ) {
                try {
                    System.out.println("Choose a row:");
                    row = scanner.nextInt();
                    //row = Integer.parseInt(rowStr);
                } catch () {
                    System.out.println("Invalid format");
                }
            //}

            int col = 0;
            while (col <= 0 && col > matrix.getMatrix()[row].length) {
                try {
                    System.out.println("Choose a column:");
                    String colStr = scanner.nextLine();
                    col = Integer.parseInt(colStr);
                } catch (NumberFormatException E) {
                    System.out.println("Invalid format");
                }
            }

            System.out.println("Choice is: " + matrix.getMatrixElement(row, col));

             */
        //}

    }

}

