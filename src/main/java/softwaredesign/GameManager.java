package softwaredesign;

import java.util.Scanner;

public class GameManager {
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

    public void addElementToBuffer(int row, int col) {
        //check if move is allowed
        if (this.moves.getCurrAxis() == GameState.rowCol.ROW && this.moves.getCurrNumRowCol() != row)
            return;
        else if (this.moves.getCurrAxis() == GameState.rowCol.COL && this.moves.getCurrNumRowCol() != col)
            return;

        //execute move
        int nextRowCol;
        if (this.moves.getCurrAxis() == GameState.rowCol.ROW) {
            nextRowCol = col;
        } else {
            nextRowCol = row;
        }

        this.moves.newMove(this.puzzle.getCurrMatrixElement(row, col), nextRowCol);

        //DISPLAY BUFFER
        this.moves.printCurrGameState();

        //check for gameOver
        if (this.gameOver.getGameOver(this.puzzle, this.moves)) {
            System.out.println("You game over!!!");
        }
        //time is over (separate function)
    }

    public void undoMove() {
        this.moves.undoMove();
        this.moves.printCurrGameState();
    }

    public void printGame() {
        System.out.println(("\nSequences: "));
        System.out.println("Sequences Filler");
        System.out.println("\nMatrix: ");
        System.out.println("Matrix Filler");
        moves.printCurrGameState();
    }

    public void runConsoleGameLoop() {
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

    public int getCurrMatrixDims() {
        return this.puzzle.getCurrMatrixDims();
    }

    public int getCurrNumOfSeq() {
        return this.puzzle.getCurrNumOfSeq();
    }

    public String[] getCurrNSeq(int index) {
        return this.puzzle.getCurrNSeq(index);
    }

    public String getCurrMatrixValueAt(int row, int col) {
        return this.puzzle.getCurrMatrixElement(row, col);
    }

    public int getCurrBufferSize() {
        return this.moves.getCurrBufferSize();
    }

    public String getCurrBufferValue(int n) {
        return this.moves.getCurrBufferValues()[n];
    }

}

