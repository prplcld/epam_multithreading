package by.silebin.multithreading;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Port {

    private static Port instance;
    private static Lock instanceLock = new ReentrantLock();
    private static Lock queuesLock = new ReentrantLock();

    private Queue<Dock> availableDocks;
    private Queue<Dock> unavailableDocks;

    private Port() {
        availableDocks = new ArrayDeque<>(10);
        unavailableDocks = new ArrayDeque<>(10);
    }

    public static Port getInstance() {
        if(instance == null) {
            instanceLock.lock();

            if(instance == null) {
                instance = new Port();
            }

            instanceLock.unlock();
        }

        return instance;
    }

    public Dock getDock() {
        queuesLock.lock();
        Dock dock = availableDocks.remove();
        unavailableDocks.add(dock);
        queuesLock.unlock();
        return  dock;
    }

    public void releaseDock(Dock dock) {
        queuesLock.lock();
        unavailableDocks.remove(dock);
        availableDocks.add(dock);
        queuesLock.unlock();
    }
}
