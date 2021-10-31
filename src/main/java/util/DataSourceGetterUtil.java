package util;

import java.util.List;
import java.util.stream.Collectors;
import service.DataReader;
import service.impl.FileReader;

public class DataSourceGetterUtil {
    private static final DataReader fileReader = new FileReader();
    private static final String INPUT_FILEPATH
            = "src/main/resources/dataSources.csv";
    private static final int FILE_START_POSITION = 10;
    private static final int URL_START_POSITION = 9;

    public static List<String> getFilePaths() {
        return fileReader
                .readFromDataSource(INPUT_FILEPATH)
                .stream()
                .filter(s -> s.contains("fileName"))
                .map(string -> string.substring(FILE_START_POSITION, string.lastIndexOf("\"")))
                .map(string -> "./src/main/resources/" + string)
                .collect(Collectors.toList());
    }

    public static List<String> getUrls() {
        return fileReader.readFromDataSource(INPUT_FILEPATH)
                .stream()
                .filter(s -> s.contains("urlName"))
                .map(string -> string.substring(URL_START_POSITION, string.lastIndexOf("\"")))
                .collect(Collectors.toList());
    }
}
