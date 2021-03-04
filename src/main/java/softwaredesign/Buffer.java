package softwaredesign;
import java.util.ArrayList;

public final class Buffer {
    private String[] buffer;
    private int size;

    //for creating the initial buffer
    public Buffer(int length) {
        this.buffer = new String[length];
        this.size = 0;
    }

    //copy constructor
    public Buffer(Buffer bufferToCopy) {
        this.buffer = bufferToCopy.getBufferValues();
        this.size = bufferToCopy.getSize();
    }

    //for creating a new buffer with old values + newest value
    public Buffer(Buffer prevBuffer, String newValue) {
        this.buffer = new String[prevBuffer.getBufferValues().length];
        this.size = prevBuffer.getSize() + 1;

        if (this.buffer.length >= this.size) {
            //copy the old buffer
            for (int i=0; i < this.size - 1; i++) {
                this.buffer[i] = prevBuffer.buffer[i];
            }
            this.buffer[this.size-1] = newValue;
        } else {
            throw new RuntimeException("The buffer is full");
        }
    }

    public int getSize() {
        int copySize = this.size;
        return copySize;
    }

    public String[] getBufferValues() {
        String [] bufferCopy = new String[this.buffer.length];
        System.arraycopy(this.buffer, 0, bufferCopy, 0, this.buffer.length);
        return bufferCopy;
    }

    public void printBuffer() {
        for (int i=0; i < this.buffer.length; i++) {
            if (this.buffer[i] == null) {
                System.out.print("_");
            } else {
                System.out.print(this.buffer[i]);
            }

            if (i != this.buffer.length - 1) {
                System.out.print(" ");
            } else {
                System.out.println();
            }
        }
    }
}
