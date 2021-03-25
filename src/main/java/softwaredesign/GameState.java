package softwaredesign;

public final class GameState {
    enum rowCol {
        ROW,
        COL
    }

    private rowCol axis;
    private int numRowCol;
    private Buffer buffer;

    //for creating the initial gameState
    public GameState(int bufferLen) {
        this.axis = rowCol.ROW;
        this.numRowCol = 0;
        this.buffer = new Buffer(bufferLen);
    }

    public GameState(GameState prevGS, String valueAddToBuffer, int newNumRowCol) {
        if (prevGS.getAxis() == rowCol.ROW) {
            this.axis = rowCol.COL;
        } else {
            this.axis = rowCol.ROW;
        }

        this.buffer = new Buffer(prevGS.getBuffer(), valueAddToBuffer);
        this.numRowCol = newNumRowCol;
    }

    public rowCol getAxis() {
        rowCol copyAxis = this.axis;
        return copyAxis;
    }

    public int getNumRowCol() {
        return this.numRowCol;
    }

    public String[] getBufferValues() {
        return this.buffer.getValues();
    }

    private Buffer getBuffer() {
        return this.buffer;
    }

    public int getBufferSize() {
        return this.buffer.getSize();
    }

    //TODO: REMOVE THIS
    public void printGameState() {
        System.out.println("----Buffer---");
        buffer.printBuffer();
        System.out.println("----------------");
    }
}
