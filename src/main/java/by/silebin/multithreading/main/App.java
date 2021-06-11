package by.silebin.multithreading.main;

import by.silebin.multithreading.entity.Ship;
import by.silebin.multithreading.exception.FileReaderException;
import by.silebin.multithreading.reader.DataFileReader;
import com.sun.tools.javac.Main;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class App {

    private static final String PATH = "data/ships.txt";

    public static void main( String[] args ) {
        ClassLoader classLoader = Main.class.getClassLoader();
        URL resource = classLoader.getResource(PATH);
        if (resource == null) {
            return;
        }

        String path = new File(resource.getFile()).getPath();
        try {
            List<String> rows = DataFileReader.readFile(path);
            List<Ship> ships = new ArrayList<>();

            for(String data : rows) {
                String[] split = data.split(" ");

                Ship ship = new Ship(Integer.parseInt(split[0]), Boolean.parseBoolean(split[1]), Integer.parseInt(split[2]));
                ships.add(ship);
            }

            ExecutorService executorService = Executors.newFixedThreadPool(ships.size());
            ships.forEach(executorService::execute);
            executorService.shutdown();

        } catch (FileReaderException e) {
            e.printStackTrace();
        }


    }
}
