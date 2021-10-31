package service.impl;

import model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import service.ProductService;
import service.ReportProduct;

@Service
public class ReportProductImpl implements ReportProduct {
    private ProductService productService;

    public ReportProductImpl() {
    }

    @Autowired
    public ReportProductImpl(ProductService productService) {
        this.productService = productService;
    }

    @Override
    public String getReport() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("[");
        for (Product product : productService.getAll()) {
            if (productService != null) {
                stringBuilder.append("{")
                        .append("\"productUuid\":")
                        .append(product.getProductUuid())
                        .append("\",")
                        .append("\"productName\":\"")
                        .append(product.getProductName())
                        .append("\",")
                        .append("\"amount\":")
                        .append(product.getAmount())
                        .append("}")
                        .append(",");
            }
        }
        stringBuilder.delete(stringBuilder.length() - 1, stringBuilder.length()).append("]");
        stringBuilder.append(System.lineSeparator());
        return stringBuilder.toString();
    }
}
