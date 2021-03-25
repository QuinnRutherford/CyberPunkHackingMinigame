package softwaredesign;

public class GameManager {
    private final Puzzle puzzle;
    private GameOver gameOver;
    private MoveHistory moves;

    public final int timePerPuzzle = 62;

    public GameManager() {
        this.puzzle = new Puzzle();
        this.puzzle.getNextPuzzle();
        this.moves = new MoveHistory(this.puzzle.getBufferLen());
        this.gameOver = new GameOver();
    }

    public void restartGame() {
        this.puzzle.getNextPuzzle();
        this.moves = new MoveHistory(this.puzzle.getBufferLen());
        this.gameOver = new GameOver();
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
