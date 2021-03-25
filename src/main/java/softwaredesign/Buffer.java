package softwaredesign;

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
        this.buffer = new String[prevBuffer.getValues().length];
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
        return this.size;
    }

    public String[] getValues() {
        String [] bufferCopy = new String[this.buffer.length];
        System.arraycopy(this.buffer, 0, bufferCopy, 0, this.buffer.length);
        return bufferCopy;
    }

    //TODO: REMOVE AFTER COMPLETE
    public void printBuffer() {
        for (int i=0; i < this.buffer.length; i++) {
            if (this.buffer[i] == null) {
                System.out.print("_");
            } else {
                System.out.print(this.buffer[i] + " ");
            }
        }
        System.out.println();
    }
}
