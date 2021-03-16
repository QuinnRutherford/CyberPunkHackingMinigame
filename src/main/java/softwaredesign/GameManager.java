package softwaredesign;

import java.util.Scanner;

public class GameManager implements Runnable {
    private Puzzle puzzle;
    private GameOver gameOver;
    private MoveHistory moves;
    private TimerClass gameTimer;

    public GameManager() {
        this.puzzle = new Puzzle();
        this.puzzle.getNextPuzzle();

        moves = new MoveHistory(this.puzzle.getBufferLen());

        this.gameOver = new GameOver();

        gameTimer = new TimerClass(10, this::lose);

    }

    public void lose() {
        System.out.println("GAME OVER");
        //Next puzzle

    }

    @Override
    public void run() {
        runGameLoop();
    }

    public void printGame() {
        System.out.println(("\nSequences: "));
        System.out.println("Sequences Filler");
        System.out.println("\nMatrix: ");
        System.out.println("Matrix Filler");
        moves.printCurrGameState();
    }

    public void runGameLoop() {
        Scanner scanner = new Scanner(System.in);
        //Core game-loop
        gameTimer.run();
        while (!gameOver.getGameOver(this.puzzle, this.moves)){
            if(this.moves.isCurrBufferFull()){
                break;
            }
            printGame();
            //get user input
            String userChoice;
            int nextRowCol;
            if (moves.getCurrAxis() == GameState.rowCol.ROW) {
                int currRow = moves.getCurrNumRowCol();
                System.out.println("The current row is: " + (currRow + 1));
                int col = 0;
                while (col <= 0 || col > this.puzzle.getCurrMatrixDims()) {
                    try {
                        System.out.println("Choose a column:");
                        String colStr = scanner.nextLine();
                        col = Integer.parseInt(colStr);
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid format");
                    }
                }
                userChoice = this.puzzle.getCurrMatrixElement(currRow, col - 1);
                nextRowCol = col - 1;
            } else {
                int currCol = moves.getCurrNumRowCol();
                System.out.println("The current column is: " + (currCol + 1));
                int row = 0;
                while (row <= 0 || row > this.puzzle.getCurrMatrixDims()) {
                    try {
                        System.out.println("Choose a row:");
                        String rowStr = scanner.nextLine();
                        row = Integer.parseInt(rowStr);
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid format");
                    }
                }
                userChoice = this.puzzle.getCurrMatrixElement(row - 1, currCol);
                nextRowCol = row - 1;
            }
            moves.newMove(userChoice, nextRowCol);
        }
        moves.printCurrGameState();
        if(gameOver.getResult()){
            System.out.println("YOU WIN!!!");
        } else {
            System.out.println("YOU LOSE!!!");
        }
        System.exit(0);
    }
}

