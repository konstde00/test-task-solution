package service.impl;

import org.springframework.stereotype.Service;
import service.Validator;

@Service
public class ValidatorImpl implements Validator {
    private static final String COMMA = ",";
    private static final int ID_INDEX = 0;
    private static final String CORRECT_FORMAT = "^[a-zA-Z0-9]+$";
    private static final int NAME_INDEX = 1;
    private static final int AMOUNT_INDEX = 2;
    private static final int AMOUNT_BEGIN_INDEX = 6;

    @Override
    public boolean checkInputData(String data) {
        data = data.replace("[", "");
        data = data.replace("]", "");

        return isCorrectProductUuId(data)
                && isCorrectProductName(data)
                && isCorrectProductAmount(data);
    }

    private boolean isCorrectProductUuId(String data) {
        String productId = data.split(COMMA)[ID_INDEX]
                .replaceAll("productUuid", "")
                .replaceAll(":", "")
                .replaceAll("\\{", "")
                .replaceAll("\"", "");
        String[] idParts = productId.split("-");

        return productId.length() == 36
                && idParts.length == 5
                && idParts[0].length() == 8 && idParts[0].matches(CORRECT_FORMAT)
                && idParts[1].length() == 4 && idParts[1].matches(CORRECT_FORMAT)
                && idParts[2].length() == 4 && idParts[2].matches(CORRECT_FORMAT)
                && idParts[3].length() == 4 && idParts[3].matches(CORRECT_FORMAT)
                && idParts[4].length() == 12 && idParts[4].matches(CORRECT_FORMAT);
    }

    private boolean isCorrectProductName(String data) {
        return data.split(COMMA)[NAME_INDEX]
                .replaceAll("productName", "")
                .replaceAll(":", "")
                .replaceAll("\"", "")
                .matches(CORRECT_FORMAT);
    }

    private boolean isCorrectProductAmount(String data) {
        return Integer.parseInt(data.split(COMMA)[AMOUNT_INDEX]
                .replaceAll("}", "")
                .replaceAll("\"", "")
                .replaceAll(":", "")
                .substring(AMOUNT_BEGIN_INDEX)) >= 0;
    }
}
