package softwaredesign;

public final class Sequences {
    private final String[][] sequences;

    public Sequences(String seqTxt){
        String[] lines = seqTxt.split("\n");
        String[][] newSeq = new String[lines.length][];
        for(int i = 0; i < lines.length; i++){
            newSeq[i] = lines[i].split(" ");
        }
        sequences = newSeq;
    }

    private String[][] copySeq(String[][] original){
        String[][] copy = new String[original.length][original[0].length];
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
