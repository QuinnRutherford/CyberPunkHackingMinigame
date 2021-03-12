package softwaredesign;

public class Puzzle {
    private String currPuzzle;
    private int bufferLen;
    private String seqTxt;
    private String matrixTxt;

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
        final int MAX_FILE_NUM = 40;
        final int MIN_FILE_NUM = 1;
        int fileNumber = getRandomNumber(MIN_FILE_NUM, MAX_FILE_NUM);
        FileReader reader = new FileReader();
        String currentDirectory = System.getProperty("user.dir");
        String fullFilePath = currentDirectory + "\\puzzles\\" + fileNumber + ".txt";
        String puzzleTxt = reader.readFile(fullFilePath);
        return puzzleTxt;
    }

    public int getBufferLen(){
        return this.bufferLen;
    }

    public String getMatrixTxt(){
        return this.matrixTxt;
    }

    public String getSeqTxt(){
        return this.seqTxt;
    }

}
