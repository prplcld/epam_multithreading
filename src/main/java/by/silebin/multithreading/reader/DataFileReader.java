package by.silebin.multithreading.reader;

import by.silebin.multithreading.exception.FileReaderException;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.util.List;

public class DataFileReader {

    public static List<String> readFile(String pathToFile) throws FileReaderException {
        try {
        Path path = Path.of(pathToFile);

            return Files.readAllLines(path);
        } catch (IOException | InvalidPathException e) {
            throw new FileReaderException();
        }
    }
}
