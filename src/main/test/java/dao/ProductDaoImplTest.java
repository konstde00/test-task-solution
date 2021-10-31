package dao;

import dao.impl.ProductDaoImpl;
import exception.DataProcessingException;
import exception.UniqueFieldDuplicationException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import model.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

public class ProductDaoImplTest extends AbstractTest {
    private ProductDao productDao;
    Product cup;
    Product plate;
    Product spoon;
    List<Product> products;

    @Override
    protected Class<?>[] entities() {
        return new Class[] {Product.class};
    }

    @BeforeEach
    void setUp() {
        productDao = new ProductDaoImpl(getSessionFactory());

        cup = new Product();
        cup.setProductUuid("10111213-b5f8-4d15-a291-4db1803cddac");
        cup.setProductName("Cup");
        cup.setAmount(10);

        plate = new Product();
        plate.setProductUuid("14151617-b5f8-4d15-a291-4db1803cddac");
        plate.setProductName("Plate");
        plate.setAmount(10);

        spoon = new Product();
        spoon.setProductUuid("18192021-b5f8-4d15-a291-4db1803cddac");
        spoon.setProductName("Spoon");
        spoon.setAmount(10);

        products = List.of(cup, plate, spoon);
    }

    @Test
    public void productDao_save_Ok() {
        Product actual = productDao.save(cup);
        Assertions.assertNotNull(actual);

        String expectedId = "10111213-b5f8-4d15-a291-4db1803cddac";
        String actualId = actual.getProductUuid();
        Assertions.assertEquals(expectedId, actualId);
    }

    @Test
    void productDao_findAll_Ok() {
        productDao.save(cup);
        productDao.save(plate);
        productDao.save(spoon);

        List<Product> expected = products;
        List<Product> actual = productDao.getAll();

        Assertions.assertEquals(expected, actual);
    }

    @Test
    void productDao_save_productWithDuplictedId_fieldDuplication() {
        productDao.save(spoon);
        Product fork = spoon;
        fork.setProductName("Fork");

        Assertions.assertThrows(UniqueFieldDuplicationException.class, () -> {
            productDao.save(fork);
        });
    }

    @Test
    void productDao_save_NullObject_DataProcessing() {
        Assertions.assertThrows(DataProcessingException.class, () -> {
            productDao.save(null);
        });
    }

    @Test
    void productDao_findAll_EmptyDB_Ok() {
        List<Product> expected = new ArrayList<>();
        List<Product> actual = productDao.getAll();

        Assertions.assertEquals(expected, actual);
    }

    @Test
    void productDao_get_Ok() {
        productDao.save(cup);
        String cupId = "10111213-b5f8-4d15-a291-4db1803cddac";

        Optional<Product> expected = Optional.of(cup);
        Optional<Product> actual = productDao.get(cupId);

        Assertions.assertEquals(expected, actual);
    }

    @Test
    void productDao_get_InvalidId_DataProcessing() {
        String invalidId = "10111213-b5f8-4d15-a291-4db1803cddac";

        Assertions.assertThrows(DataProcessingException.class, () -> {
            productDao.get(invalidId);
        });
    }

    @Test
    public void productDao_update_Ok() {
        productDao.save(cup);
        int startAmount = cup.getAmount();
        int additionalAmount = 20;

        Product newCup = cup;
        newCup.setAmount(additionalAmount);

        Product updatedCup = productDao.update(newCup);
        int expectedUpdatedAmount = startAmount + additionalAmount;
        int actualUpdatedAmount = updatedCup.getAmount();

        Assertions.assertNotNull(updatedCup);
        Assertions.assertEquals(expectedUpdatedAmount, actualUpdatedAmount);
    }

    @Test
    void productDao_exists_Ok() {
        productDao.save(cup);

        Boolean actual = productDao.exists(cup);
        Assertions.assertTrue(actual);
    }

    @Test
    void productDao_exists_noSuchElementInDB_not_Ok() {
        Boolean actual = productDao.exists(cup);
        Assertions.assertFalse(actual);
    }

    @Test
    void productDao_exists_NullObject_not_Ok() {
        Boolean actual = productDao.exists(null);
        Assertions.assertFalse(actual);
    }
}
