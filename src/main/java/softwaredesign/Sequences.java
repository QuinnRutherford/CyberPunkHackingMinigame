package softwaredesign;

import java.util.Iterator;

public final class Sequences implements Iterator<String[]>{
    //for iterator
    int iteratorIndex = 0;

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

    public int getNumOfSeq() {
        return sequences.length;
    }

    @Override
    public boolean hasNext() {
        if (this.iteratorIndex >= this.sequences.length) {
            this.iteratorIndex = 0; //reset iteratorIndex
            return false;
        }
        return true;
    }

    @Override
    public String[] next() {
        if (this.hasNext()) {
            return this.sequences[iteratorIndex++];
        }
        return null;
    }
}
