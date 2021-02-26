package softwaredesign;

public final class GameState {
    enum rowCol {
        ROW,
        COL
    }

    //true means row must be selected, false means colum
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

    public Buffer getBuffer() {
        //to create a copy we need a copy constructor. Is this necessary?
        return buffer;
    }

    public void printValues() {
        System.out.println("----------------");
        System.out.println(this.axis);
        System.out.println("num Row/Col: " + this.numRowCol);
        buffer.printValues();
        System.out.println("----------------");
    }
}
