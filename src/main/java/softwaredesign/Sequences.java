package softwaredesign;

public final class Sequences {
    private final String[][] sequences;

    public Sequences(String seqTxt) {
        String[] lines = seqTxt.split("\n");
        int longestSeq = 0;
        for(int i = 0; i < lines.length; i++){
            int tmpLength = lines[i].split(" ").length;
            if(tmpLength > longestSeq){
                longestSeq = tmpLength;
            }
        }
        this.sequences = new String[lines.length][longestSeq];
        for(int i = 0; i < lines.length; i++){
            this.sequences[i] = lines[i].split(" ");
        }
    }

    public String[] getNSeq(int seqIndex) {
        String[] seqCopy = new String[this.sequences[seqIndex].length];
        System.arraycopy(this.sequences[seqIndex], 0, seqCopy, 0, seqCopy.length);
        return seqCopy;
    }

    public int getNumOfSeq() {
        return sequences.length;
    }
}
