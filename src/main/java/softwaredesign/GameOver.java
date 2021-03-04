package softwaredesign;

public class GameOver {
    private boolean gameOver;

    public GameOver() {
        this.gameOver = false;
    }

    public boolean getGameOver(Sequences sequences, GameState gs) {
        this.gameOver = updateGameOver(sequences, gs);
        boolean gameOverCopy = this.gameOver;
        return gameOverCopy;
    }

    private boolean updateGameOver(Sequences sequences, GameState gs) {
        for (int i = 0; i < sequences.getNumberOfSeq(); i++) {
            if (checkCorrectSequence(sequences.getNSeq(i), gs)) {
                return true;
            }
        }
        return false;
    }

    private boolean checkCorrectSequence (String[] seq, GameState gs) {
        String bufString = "";
        for (int i = 0; i < gs.getBufferSize(); i++) {
            bufString += gs.getBufferValues()[i];
        }
        String seqString = "";
        for (int i = 0; i < seq.length; i++) {
            seqString += seq[i];
        }

        return bufString.contains(seqString);
    }
}
