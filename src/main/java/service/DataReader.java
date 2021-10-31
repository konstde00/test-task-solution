package service;

import java.util.List;

public interface DataReader {
    public List<String> readFromDataSource(String sourcePath);
}
