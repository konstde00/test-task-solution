package service.impl;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import service.DataReader;

import java.util.List;

class WebSourceReaderTest {
    private static DataReader webSourceReader;
    private final String CORRECT_LINK
            = "https://test-api-heroku.herokuapp.com/correctLink";

    @BeforeAll
    public static void beforeClass() {
        webSourceReader = new WebSourceReader();
    }

    @Test
    public void webSourceReader_correctUrl_Ok() {
        List<String> expected = List.of("{\"productUuid\":\"123e4567-e89b-12d3-a456-426655440000\","
                + "\"productName\":\"Kettle\",\"amount\":3}");
        List<String> actual = webSourceReader.readFromDataSource(CORRECT_LINK);

        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void webSourceReader_incorrectFilePath_RuntimeException() {
        Assertions.assertThrows(RuntimeException.class, () -> {
            webSourceReader.readFromDataSource("");
        });
    }
}