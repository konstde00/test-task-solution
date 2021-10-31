package service.impl;

import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import service.DataReader;

class FileReaderTest {
    private static DataReader fileReader;

    @BeforeAll
    public static void beforeClass() {
        fileReader = new FileReader();
    }

    @Test
    public void fileReader_csvFile_ok() {
        List<String> expected = List.of("Data from .csv file");
        List<String> actual = fileReader
                .readFromDataSource("./src/main/test/resources/readerTest.csv");

        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void fileReader_jsonFile_ok() {
        List<String> expected = List.of("Data from .json file");
        List<String> actual = fileReader
                .readFromDataSource("./src/main/test/resources/readerTest.json");

        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void fileReader_incorrectFilePath_RuntimeException() {
        Assertions.assertThrows(RuntimeException.class, () -> {
            fileReader.readFromDataSource("");
        });
    }
}
