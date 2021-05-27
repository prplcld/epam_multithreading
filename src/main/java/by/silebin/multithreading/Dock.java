package by.silebin.multithreading;

public class Dock implements AutoCloseable {

    private int capacity;
    private int currentAmount;

    public Dock(int capacity, int currentAmount) {
        this.capacity = capacity;
        this.currentAmount = currentAmount;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public int getCurrentAmount() {
        return currentAmount;
    }

    public void setCurrentAmount(int currentAmount) {
        this.currentAmount = currentAmount;
    }

    @Override
    public void close() {
        Port port = Port.getInstance();
        port.releaseDock(this);
    }
}
