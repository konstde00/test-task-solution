package service.impl;

import dto.response.ProductResponseDto;
import org.springframework.stereotype.Service;
import service.ProductParser;

@Service
public class ProductParserImpl implements ProductParser {
    private static final String COMMA = ",";
    private static final int ID_INDEX = 0;
    private static final int NAME_INDEX = 1;
    private static final int AMOUNT_INDEX = 2;
    private static final int AMOUNT_BEGIN_INDEX = 6;

    public ProductParserImpl() {
    }

    @Override
    public ProductResponseDto parseData(String data) {
        data = data.replace("[", "");
        data = data.replace("]", "");

        return new ProductResponseDto(getProductId(data),
                getProductName(data),
                getAmount(data));
    }

    private String getProductId(String data) {
        return data.split(COMMA)[ID_INDEX]
                .replaceAll("productUuid", "")
                .replaceAll(":", "")
                .replaceAll("\\{", "")
                .replaceAll("\"", "");
    }

    private String getProductName(String data) {
        return data.split(COMMA)[NAME_INDEX]
                .replaceAll("productName", "")
                .replaceAll(":", "")
                .replaceAll("\"", "");
    }

    private int getAmount(String data) {
        return Integer.parseInt(data.split(COMMA)[AMOUNT_INDEX]
                .replaceAll("}", "")
                .replaceAll("\"", "")
                .replaceAll(":", "")
                .substring(AMOUNT_BEGIN_INDEX));
    }
}
