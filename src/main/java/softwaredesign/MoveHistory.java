package softwaredesign;

import java.util.Stack;

public class MoveHistory {
    private Stack<GameState> moveHistory;

    public MoveHistory(int bufferLength) {
        this.moveHistory = new Stack<>();
        GameState initialGS = new GameState(bufferLength);
        this.moveHistory.push(initialGS);
    }

    public void newMove(String valueAddToBuffer, int newNumRowCol) {
        GameState newGS = new GameState(this.moveHistory.peek(), valueAddToBuffer, newNumRowCol);
        this.moveHistory.push(newGS);
    }

    public String[] getCurrBufferValues() {
        return this.moveHistory.peek().getBufferValues();
    }

    public int getCurrBufferSize() {
        return this.moveHistory.peek().getBufferSize();
    }

    public void printCurrGameState() {
        moveHistory.peek().printGameState();
    }

    public GameState.rowCol getCurrAxis() {
        return this.moveHistory.peek().getAxis();
    }

    public int getCurrNumRowCol() {
        return this.moveHistory.peek().getNumRowCol();
    }

    public boolean isCurrBufferFull(){
        if(this.getCurrBufferSize() == this.getCurrBufferValues().length) return true;
        else return false;
    }
}
