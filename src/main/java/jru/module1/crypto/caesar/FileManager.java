package jru.module1.crypto.caesar;

import jru.module1.crypto.caesar.exception.FileManagerException;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.util.List;

public class FileManager {

    public List<String> readFile (String fileName) throws FileManagerException {
        try {
            Path path = Path.of(fileName);
            return Files.readAllLines(path);
        } catch (IOException | InvalidPathException ex) {
            throw new FileManagerException(ex.getMessage(), ex);
        }
    }

    public BufferedWriter openWriter(String fileName) {
        try {
            Path path = Path.of(fileName);
            return Files.newBufferedWriter(path, StandardCharsets.UTF_8);
        } catch (IOException | InvalidPathException ex) {
            throw new FileManagerException(ex.getMessage(), ex);
        }
    }
}
