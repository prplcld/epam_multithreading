package by.silebin.multithreading;

public class Ship extends Thread {

    private int shipId;
    private int capacity;
    private boolean loading;

    public int getShipId() {
        return shipId;
    }

    public void setShipId(int shipId) {
        this.shipId = shipId;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public boolean isLoading() {
        return loading;
    }

    public void setLoading(boolean loading) {
        this.loading = loading;
    }

    @Override
    public void run() {
        Port port = Port.getInstance();
        try(Dock dock = port.getDock()) {
            //do something
        }
    }
}
