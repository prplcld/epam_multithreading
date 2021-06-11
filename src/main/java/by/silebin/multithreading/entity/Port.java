package by.silebin.multithreading.entity;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayDeque;
import java.util.Properties;
import java.util.Queue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Port {

    private static Port instance;
    private static final String PROPERTIES_FILE_NAME = "port_settings.properties";
    private static final int DEFAULT_DOCKS_AMOUNT = 2;
    private static final Lock instanceLock = new ReentrantLock();
    private static final Lock queuesLock = new ReentrantLock();
    private static final Condition emptyCondition = queuesLock.newCondition();

    private final Queue<Dock> availableDocks;

    private Port() {
        URL resource = Thread.currentThread().getContextClassLoader().getResource("");
        int docksAmount = DEFAULT_DOCKS_AMOUNT;

        if (resource != null) {
            try {
                String path = resource.getPath();
                String appConfigPath = path + PROPERTIES_FILE_NAME;

                Properties portProperties = new Properties();

                portProperties.load(new FileInputStream(appConfigPath));
                docksAmount = Integer.parseInt(portProperties.getProperty("port.docksCount"));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        availableDocks = new ArrayDeque<>(docksAmount);
        Dock dock = new Dock(2, 0);
        Dock dock1 = new Dock(2, 0);
        availableDocks.add(dock);
        availableDocks.add(dock1);
    }

    public static Port getInstance() {
        if (instance == null) {
            instanceLock.lock();

            if (instance == null) {
                instance = new Port();
            }

            instanceLock.unlock();
        }

        return instance;
    }

    public Dock getDock() throws InterruptedException {
        queuesLock.lock();
        try {
            while (availableDocks.isEmpty()) {
                emptyCondition.await();
            }
            Dock dock = availableDocks.remove();
            return dock;
        } finally {
            queuesLock.unlock();
        }
    }

    public void releaseDock(Dock dock) {
        queuesLock.lock();
        try {
            availableDocks.add(dock);
            emptyCondition.signal();
        } finally {
            queuesLock.unlock();
        }
    }
}
