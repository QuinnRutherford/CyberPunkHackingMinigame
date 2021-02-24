package softwaredesign;

public class GameManager {
    private Matrix matrix;
    private TimerClass timer;
    private GameOver gameOver;

    public void gameLoop(){

    }

    public void buttonPressed(){

    }

    private void puzzleSetUp(){

    }

    private String puzzleMatrix(String matrixTxt){
        return "";
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

    private String puzzleSequences(String sequences){
        return "";
    }

}
