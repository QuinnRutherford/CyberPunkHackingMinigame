package softwaredesign;

public class GameOver {
    private boolean gameOver;
    private boolean win;

    public GameOver() {
        this.gameOver = false;
        this.win = false;
    }

    public boolean getGameOver(Puzzle puzzle, MoveHistory currMove) {
        this.gameOver = updateGameOver(puzzle, currMove);
        return this.gameOver;
    }

    private boolean updateGameOver(Puzzle puzzle, MoveHistory currMove) {
        for (int i = 0; i < puzzle.getCurrNumberOfSeq(); i++) {
            if (checkCorrectSequence(puzzle.getCurrNSeq(i), currMove)) {
                this.win = true;
                return true;
            }
        }
        return false;
    }

    private boolean checkCorrectSequence (String[] seq, MoveHistory currMove) {
        String bufString = "";
        for (int i = 0; i < currMove.getCurrBufferSize(); i++) {
            bufString += currMove.getCurrBufferValues()[i];
        }

        String seqString = "";
        for (int i = 0; i < seq.length; i++) {
            seqString += seq[i];
        }
        return bufString.contains(seqString);
    }
    public boolean getResult(){
        return this.win;
    }
}
