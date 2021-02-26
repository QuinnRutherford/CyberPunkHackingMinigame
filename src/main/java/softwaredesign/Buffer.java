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

    //for creating a new buffer with old values + newest value
    public Buffer(Buffer prevBuffer, String newValue) {
        this.buffer = new String[prevBuffer.buffer.length];
        this.size = prevBuffer.getSize() + 1;

        if (this.buffer.length >= this.size) {
            //copy the old buffer
            for (int i=0; i < this.size - 1; i++) {
                this.buffer[i] = prevBuffer.buffer[i];
            }
            this.buffer[this.size-1] = newValue;
        } else {
            throw new java.lang.Error("The buffer is full");
        }
    }

    private int getSize() {
        int copySize = this.size;
        return copySize;
    }

    //this method is for testing only
    public void printValues() {
        System.out.println("size: " + this.size);
        for (int i=0; i < this.buffer.length; i++) {
                System.out.println(this.buffer[i]);
        }
    }
}
