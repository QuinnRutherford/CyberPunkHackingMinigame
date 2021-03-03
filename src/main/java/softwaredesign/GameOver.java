package softwaredesign;

public class GameOver {
    private boolean gameOver;

    public GameOver() {
        this.gameOver = false;
    }

    public boolean getGameOver(Sequences sequences, Buffer buf) {
        this.gameOver = updateGameOver(sequences, buf);
        boolean gameOverCopy = this.gameOver;
        return gameOverCopy;
    }

    private boolean updateGameOver(Sequences sequences, Buffer buf) {
        for (int i = 0; i < sequences.getNumberOfSeq(); i++) {
            if (checkCorrectSequence(sequences.getNSeq(i), buf)) {
                return true;
            }
        }
        return false;
    }

    private boolean checkCorrectSequence (String[] seq, Buffer buf) {
        //if (buf.getSize() < seq.length) return false;
        //buffer size is >= seq length
        String bufString = "";
        for (int i = 0; i < buf.getSize(); i++) {
            bufString += buf.getBuffer()[i];
        }

        String seqString = "";
        for (int i = 0; i < seq.length; i++) {
            if (seq[i] != null) {
                seqString += seq[i];
            }
        }

        return bufString.contains(seqString);
    }
}
