package service;

import dao.AbstractTest;
import model.Product;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import service.impl.ReportProductImpl;

public class ReportProductTest extends AbstractTest {
    private static ReportProduct reportProduct;
    private static ProductService productService;

    @Override
    protected Class<?>[] entities() {
        return new Class[] {Product.class};
    }

    @BeforeAll
    public static void beforeClass() {
        reportProduct = new ReportProductImpl();
    }

    @Test
    public void productReport_getReport_OK() {

    }
}