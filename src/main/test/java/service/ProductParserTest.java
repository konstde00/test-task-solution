package service;

import dto.response.ProductResponseDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import service.impl.ProductParserImpl;

public class ProductParserTest {
    private static ProductParser parser;

    @BeforeAll
    public static void beforeClass() throws Exception {
        parser = new ProductParserImpl();
    }

    @Test
    public void productParser_correctData_OK() {
        String data = "[{\"productUuid\":\"9b3e9312-b5f8-4d15-a291-4db1803cddac\""
                + ",\"productName\":\"Box\",\"amount\":100}]";
        ProductResponseDto expected = new ProductResponseDto();
        expected.setProductUuid("9b3e9312-b5f8-4d15-a291-4db1803cddac");
        expected.setProductName("Box");
        expected.setAmount(100);

        ProductResponseDto actual = parser.parseData(data);
        Assertions.assertEquals(expected, actual);
    }
}
