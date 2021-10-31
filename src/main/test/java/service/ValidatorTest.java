package service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import service.impl.ValidatorImpl;

class ValidatorTest {
    private static Validator validator;

    @BeforeAll
    public static void beforeClass() {
        validator = new ValidatorImpl();
    }

    @Test
    void validator_correctData_Ok() {
        boolean actual = validator
                .checkInputData("{\"productUuid\":\"9b3e9312-b5f8-4d15-a291-4db1803cddac\"" +
                        ",\"productName\":\"Product347\",\"amount\":71}");
        Assertions.assertTrue(actual);
    }

    @Test
    void validator_incorrectIdLength_not_Ok() {
        boolean actual = validator
                .checkInputData("{\"productUuid\":\"9b3e9312-b5f8-4d15-a291-4db1803cddacc\"" +
                        ",\"productName\":\"Product347\",\"amount\":71}");
        Assertions.assertFalse(actual);
    }

    @Test
    void validator_incorrectNameFormat_not_Ok() {
        boolean actual = validator
                .checkInputData("{\"productUuid\":\"9b3e9312-b5f8-4d15-a291-4db1803cddac\"" +
                        ",\"productName\":\"!Product347\",\"amount\":71}");
        Assertions.assertFalse(actual);
    }

    @Test
    void validator_onlyLetters_not_Ok() {
        boolean actual = validator
                .checkInputData("{\"productUuid\":\"aaaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaaa\"" +
                        ",\"productName\":\"aaaaaaa\",\"amount\":71}");
        Assertions.assertTrue(actual);
    }

    @Test
    void validator_onlyDigits_not_Ok() {
        boolean actual = validator
                .checkInputData("{\"productUuid\":\"11111111-1111-1111-1111-111111111111\"" +
                        ",\"productName\":\"11111111\",\"amount\":71}");
        Assertions.assertTrue(actual);
    }

    @Test
    void validator_zeroAmount_not_Ok() {
        boolean actual = validator
                .checkInputData("{\"productUuid\":\"9b3e9312-b5f8-4d15-a291-4db1803cddac\"" +
                        ",\"productName\":\"!Product347\",\"amount\":0}");
        Assertions.assertFalse(actual);
    }


    @Test
    void validator_negativeAmount_not_Ok() {
        boolean actual = validator
                .checkInputData("{\"productUuid\":\"9b3e9312-b5f8-4d15-a291-4db1803cddac\"" +
                        ",\"productName\":\"!Product347\",\"amount\":-71}");
        Assertions.assertFalse(actual);
    }

    @Test
    void validator_twoHyphensTogether_not_Ok() {
        boolean actual = validator
                .checkInputData("{\"productUuid\":\"9b3e9312--b5f8-4d15-a291-4db1803cddacc\"" +
                        ",\"productName\":\"Product347\",\"amount\":71}");
        Assertions.assertFalse(actual);
    }
}