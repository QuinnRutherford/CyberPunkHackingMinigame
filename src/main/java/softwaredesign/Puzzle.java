package softwaredesign;

public class Puzzle {
    private final String currentFileName;
    private String bufferTxt;
    private String seqTxt;
    private String matrixTxt;

    public Puzzle(){
        this.currentFileName = choosePuzzle();
        this.bufferTxt = "";
        this.matrixTxt = "";
        this.seqTxt = "";
    }

    private String choosePuzzle(){
        return "";
    }

    public String getBufferSize(){
        String copy = new String(this.bufferTxt);
        return copy;
    }
    public String getSeqTxt(){
        String copy = new String(this.seqTxt);
        return copy;
    }
    public String getMatrixTxt(){
        String copy = new String(this.matrixTxt);
        return copy;
    }
}
