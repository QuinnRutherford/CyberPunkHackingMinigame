package softwaredesign;

public final class GameState {
    enum rowCol {
        ROW,
        COL
    }

    //true means row must be selected, false means column
    private rowCol axis;
    private int numRowCol;
    private Buffer buffer;

    //for creating the initial gameState
    public GameState(int bufferLen) {
        this.axis = rowCol.ROW;
        this.numRowCol = 0;
        this.buffer = new Buffer(bufferLen);
    }

    //copy constructor
    public GameState(GameState gsToCopy) {
        this.buffer = gsToCopy.getBuffer();
        this.numRowCol = gsToCopy.numRowCol;
        this.axis = gsToCopy.axis;
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
        return this.buffer.getBufferValues();
    }

    private Buffer getBuffer() {
        Buffer copyBuffer = new Buffer(this.buffer);
        return copyBuffer;
    }

    public int getBufferSize() {
        return this.buffer.getSize();
    }

    public void printGameState() {
        System.out.println("----Buffer---");
        //System.out.println(this.axis);
        //System.out.println("Num Row/Col: " + this.numRowCol);
        buffer.printBuffer();
        System.out.println("----------------");
    }


}
