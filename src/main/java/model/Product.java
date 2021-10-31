package model;

import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Entity
@Table(name = "products")
public class
        Product {
    @Id
    @NotNull
    private String productUuid;
    @NotNull
    private String productName;
    @Positive
    private int amount;

    public Product() {
    }

    public String getProductUuid() {
        return productUuid;
    }

    public void setProductUuid(String productUuid) {
        this.productUuid = productUuid;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Product product = (Product) o;
        return amount == product.amount
                && productUuid.equals(product.productUuid)
                && productName.equals(product.productName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(productUuid, productName, amount);
    }

    @Override
    public String toString() {
        return "Product{"
                + "productUuid='" + productUuid + '\''
                + ", productName='" + productName + '\''
                + ", amount=" + amount
                + '}';
    }
}
