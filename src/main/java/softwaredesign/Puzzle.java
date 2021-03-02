package softwaredesign;

public class Puzzle {
    private String currPuzzle;
    private int bufferLen;
    private String seqTxt;
    private String matrixTxt;
    private final int MAX_FILE_NUM = 40;
    private final int MIN_FILE_NUM = 1;

    public Puzzle(){

    }

    public void getNextPuzzle() {
        this.currPuzzle = getPuzzleContent();
        String[] puzzleParts = currPuzzle.split("\n\n");

        //puzzleParts[0] is buffer length
        try {
            this.bufferLen = Integer.parseInt(puzzleParts[0]);
        } catch (NumberFormatException e) {
            throw new RuntimeException("This is not a valid format for buffer size.");
        }

        //puzzleParts[1] is the matrix
        this.matrixTxt = puzzleParts[1];

        //puzzleParts[2] are the sequences
        this.seqTxt = puzzleParts[2];
    }

    private int getRandomNumber(int min, int max){
        return (int)(Math.random() * (max - min + 1) + min);
    }

    private String getPuzzleContent(){
        int fileNumber = getRandomNumber(MIN_FILE_NUM, MAX_FILE_NUM);
        FileReader reader = new FileReader();
        String currentDirectory = System.getProperty("user.dir");
        String fullFilePath = currentDirectory + "\\puzzles\\" + fileNumber + ".txt";
        String puzzleTxt = reader.readFile(fullFilePath);
        return puzzleTxt;
    }

    public int getBufferLen(){
        int copyBufferLen = this.bufferLen;
        return copyBufferLen;
    }

    public String getMatrixTxt(){
        String copy = new String(this.matrixTxt);
        return copy;
    }

    public String getSeqTxt(){
        String copy = new String(this.seqTxt);
        return copy;
    }

}
