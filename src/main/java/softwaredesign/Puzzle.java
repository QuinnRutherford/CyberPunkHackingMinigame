package softwaredesign;

public class Puzzle {
    private String currentFileName;
    private String bufferTxt;
    private String seqTxt;
    private String matrixTxt;

    public Puzzle(){
        this.currentFileName = "";
        this.bufferTxt = "";
        this.seqTxt = "";
        this.matrixTxt = "";
    }

    public String getBufferTxt(){
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
