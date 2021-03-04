package softwaredesign;

import java.util.Scanner;

public class GameManager implements Runnable {
    private Puzzle puzzle;
    private Matrix matrix;
    private Sequences sequences;
    private GameOver gameOver;
    private MoveHistory moves;

    public GameManager() {
        this.puzzle = new Puzzle();
        this.setupPuzzle();
        moves = new MoveHistory(this.puzzle.getBufferLen());
        this.gameOver = new GameOver();
    }

    private void setupPuzzle() {
        this.puzzle.getNextPuzzle();
        //moveHistory.push(new GameState(this.puzzle.getBufferLen()));
        this.matrix = new Matrix(this.puzzle.getMatrixTxt());
        this.sequences = new Sequences(this.puzzle.getSeqTxt());
    }

    public void printGame() {
        System.out.println(("\nSequences: "));
        this.sequences.printSequences();
        System.out.println("\nMatrix: ");
        this.matrix.printMatrix();
        moves.printCurrGameState();
    }

    @Override
    public void run() {
        runGameLoop();
    }

    public void runGameLoop() {

        Scanner scanner = new Scanner(System.in);
        //Core game-loop
        while (!gameOver.getGameOver(this.sequences, moves.getCurrGameState())){
            printGame();
            //get user input
            String userChoice;
            int nextRowCol;
            if (moves.getCurrGameState().getAxis() == GameState.rowCol.ROW) {
                int currRow = moves.getCurrNumRowCol();
                System.out.println("The current row is: " + (currRow + 1));
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
                userChoice = this.matrix.getMatrixElement(currRow, col - 1);
                nextRowCol = col - 1;
            } else {
                int currCol = moves.getCurrNumRowCol();
                System.out.println("The current column is: " + (currCol + 1));
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
                userChoice = this.matrix.getMatrixElement(row - 1, currCol);
                nextRowCol = row - 1;
            }

            moves.newMove(userChoice, nextRowCol);
        }

        moves.printCurrGameState();
        System.exit(0);
    }


}

