package softwaredesign;

public class ContainsQueue {
    private final int SIZE = 5;
    private int currItem, items[];

    //enter 5 elements and remove and replace with oldest ones
    public ContainsQueue() {
        this.items = new int[SIZE];
        this.currItem = 0;
    }

    public void enQueue(int newItem) {
        if (this.currItem >= SIZE)    //at last index
            this.currItem = 0;          //go to 0
        this.items[this.currItem++] = newItem;
    }

    public boolean contains(int toFind) {
        for (int i = 0; i < SIZE; i++) {
            if (this.items[i] == toFind)
                return true;
        }
        return false;
    }
}
