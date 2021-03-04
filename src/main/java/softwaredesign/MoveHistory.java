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

    public void printCurrGameState() {
        moveHistory.peek().printGameState();
    }

    //TODO:TRY TO ABSTRACT THIS METHOD
    public GameState getCurrGameState() {
        return new GameState(moveHistory.peek());
    }

    public int getCurrNumRowCol() {
        return this.getCurrGameState().getNumRowCol();
    }
}
