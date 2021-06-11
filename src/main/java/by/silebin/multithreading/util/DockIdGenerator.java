package by.silebin.multithreading.util;

public class DockIdGenerator {
    private static  long id;

    public static long generateId() {
        return ++id;
    }
}
