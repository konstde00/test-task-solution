package service.impl;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import org.springframework.stereotype.Service;
import service.DataReader;

@Service
public class FileReader implements DataReader {
    @Override
    public List<String> readFromDataSource(String fileName) {
        Path filePath = Paths.get(fileName);
        try {
            return Files.readAllLines(filePath);
        } catch (IOException e) {
            throw new RuntimeException("Invalid path to file: " + filePath, e);
        }
    }
}
