package by.silebin.multithreading.entity;

import by.silebin.multithreading.util.ShipIdGenerator;

public class Ship extends Thread {

    private long shipId;
    private int capacity;
    private boolean loading;
    private int currentAmount;

    public Ship(int capacity, boolean loading, int currentAmount) {
        this.shipId = ShipIdGenerator.generateId();
        this.capacity = capacity;
        this.loading = loading;
        this.currentAmount = currentAmount;
    }

    public Ship() {
        this.shipId = ShipIdGenerator.generateId();
    }

    public long getShipId() {
        return shipId;
    }

    public void setShipId(long shipId) {
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

    public int getCurrentAmount() {
        return currentAmount;
    }

    public void setCurrentAmount(int currentAmount) {
        this.currentAmount = currentAmount;
    }

    @Override
    public void run() {
        Port port = Port.getInstance();
        try(Dock dock = port.getDock()) {
            System.out.println(shipId);
            dock.unloadFromShip(currentAmount);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
