package softwaredesign;

public final class Sequences {
    private final String[][] sequences;

    public Sequences(String seqTxt){
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

    private String[][] copySeq(String[][] original){
        int longestSeq = 0;
        for(int i = 0; i < original.length; i++){
            int tmpLength = original[i].length;
            if(tmpLength > longestSeq){
                longestSeq = tmpLength;
            }
        }
        String[][] copy = new String[original.length][longestSeq];
        for(int i = 0; i < original.length; i++){
            for(int j = 0; j < original[i].length; j++){
                copy[i][j] = original[i][j];
            }
        }
        return copy;
    }

    public String[] getNSeq(int seqIndex) {
        String[][] seqCopy = copySeq(this.sequences);
        if(seqIndex >= seqCopy.length) throw new RuntimeException("The is no sequence for this index");
        return seqCopy[seqIndex];
    }

    public int getNumberOfSeq() {
        return sequences.length;
    }

    public void printSequences(){
        for(int i = 0; i < this.sequences.length; i++){
            System.out.print((i + 1) + ": ");
            for (int j = 0; j < this.sequences[i].length; j++){
                System.out.print(this.sequences[i][j]);
                if(j < this.sequences[i].length - 1) System.out.print(", ");
            }
            System.out.println();
        }
    }
}
