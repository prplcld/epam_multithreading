package by.silebin.multithreading.util;

public class ShipIdGenerator {
    private static  long id;

    public static long generateId() {
        return ++id;
    }
}
