package softwaredesign;

import java.util.Scanner;
import java.util.Timer;

public class GameManager {
    private Puzzle puzzle;
    private GameOver gameOver;
    private MoveHistory moves;
    private TimerClass gameTimer;

    private final int timerPerPuzzle = 50;

    public GameManager() {
        this.puzzle = new Puzzle();

        this.puzzle.getNextPuzzle();
        this.moves = new MoveHistory(this.puzzle.getBufferLen());

        this.gameOver = new GameOver();

        this.gameTimer = new TimerClass(timerPerPuzzle);
    }

    public void restartGame() {
        this.puzzle.getNextPuzzle();
        this.moves = new MoveHistory(this.puzzle.getBufferLen());
        this.gameOver = new GameOver();
        this.gameTimer = new TimerClass(timerPerPuzzle);
    }

    public void addElementToBuffer(int row, int col) {
        //check if move is allowed
        if (this.moves.getCurrAxis() == GameState.rowCol.ROW && this.moves.getCurrRowCol() != row)
            return;
        else if (this.moves.getCurrAxis() == GameState.rowCol.COL && this.moves.getCurrRowCol() != col)
            return;

        //execute move
        int nextRowCol;
        if (this.moves.getCurrAxis() == GameState.rowCol.ROW) {
            nextRowCol = col;
        } else {
            nextRowCol = row;
        }

        this.moves.newMove(this.puzzle.getCurrMatrixElement(row, col), nextRowCol);

        //check for gameOver
        if (this.gameOver.getGameOver(this.puzzle, this.moves)) {
            System.out.println("You game over!!!");
        }
    }

    public void undoMove() {
        this.moves.undoMove();
        //this.moves.printCurrGameState();
    }

    public int getCurrMatrixDims() {
        return this.puzzle.getCurrMatrixDims();
    }

    public int getCurrNumOfSeq() {
        return this.puzzle.getCurrNumOfSeq();
    }

    public int getCurrNumRowCol() {
        return this.moves.getCurrNumRowCol();
    }

    public GameState.rowCol getCurrAxis() {
        return this.moves.getCurrAxis();
    }

    public String[] getCurrNSeq(int index) {
        return this.puzzle.getCurrNSeq(index);
    }

    public String getCurrMatrixValueAt(int row, int col) {
        return this.puzzle.getCurrMatrixElement(row, col);
    }

    public int getCurrBufferLength() {
        return this.moves.getCurrBufferLength();
    }

    public String getCurrBufferValue(int n) {
        return this.moves.getCurrBufferValues()[n];
    }

    public boolean isGameOver() {
        return this.gameOver.getGameOver(this.puzzle, this.moves);
    }

    public boolean getResult() {
        return this.gameOver.getResult();
    }

}

