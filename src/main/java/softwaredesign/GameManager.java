package softwaredesign;

import java.util.Stack;

public class GameManager {
    private Matrix matrix;
    private TimerClass timer;
    private GameOver gameOver;
    private Stack<GameState> moveHistory = new Stack<>();

    public void gameLoop(){

    }

    public void buttonPressed(){

    }

    private void puzzleSetUp(){

    }

    private int bufferSize(String sizeString){
        int size;
        try {
            size = Integer.parseInt(sizeString);
        } catch (NumberFormatException e) {
            throw new java.lang.Error("This is not a valid format for buffer size.");
        }
        return size;
    }

}
