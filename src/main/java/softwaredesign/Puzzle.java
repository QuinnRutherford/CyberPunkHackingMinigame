package softwaredesign;

public class Puzzle {
    private String currPuzzle;
    private int bufferLen;
    private String seqTxt;
    private String matrixTxt;

    public Puzzle(){

    }

    //Currently makes the same puzzle every time
    public void getNextPuzzle() {
        //Need to update this to allow for more puzzles
        this.currPuzzle = "7\n" +
                "\n" +
                "1c 55 ff bd e9\n" +
                "bd 1c e9 ff e9\n" +
                "55 bd ff 1c 1c\n" +
                "e9 bd 1c 55 55\n" +
                "55 e9 bd 55 ff\n" +
                "\n" +
                "e9 55\n" +
                "55 bd e9\n" +
                "ff 1c bd e9\n" +
                "55 1c ff 55";

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
