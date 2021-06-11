package by.silebin.multithreading.entity;

import by.silebin.multithreading.util.DockIdGenerator;

import java.util.concurrent.TimeUnit;

public class Dock implements AutoCloseable {
    private long dockId;
    private int capacity;
    private int currentAmount;

    public Dock(int capacity, int currentAmount) {
        this.dockId = DockIdGenerator.generateId();
        this.capacity = capacity;
        this.currentAmount = currentAmount;
    }

    public Dock() {
        this.dockId = DockIdGenerator.generateId();
    }

    public long getDockId() {
        return dockId;
    }

    public void setDockId(long dockId) {
        this.dockId = dockId;
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

    public int unloadFromShip(int amount) {
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if (amount + currentAmount <= capacity) {
            currentAmount += amount;
            return amount;
        }
        else if(currentAmount == capacity) {
            return 0;
        }
        else {
            int amountToUnload = capacity - currentAmount;
            currentAmount += amountToUnload;
            return amountToUnload;
        }
    }

    public int loadToShip(int shipCapacity, int currentShipAmount) {

        if(currentShipAmount + currentAmount <= shipCapacity) {
            currentAmount += currentShipAmount;
            return currentShipAmount;
        }
        else {
            int amountToLoad = shipCapacity - currentShipAmount;
            currentAmount += amountToLoad;
            return amountToLoad;
        }
    }

    @Override
    public void close() {
        Port port = Port.getInstance();
        port.releaseDock(this);
    }
}
