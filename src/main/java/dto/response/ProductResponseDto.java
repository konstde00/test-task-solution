package dto.response;

import java.util.Objects;

public class ProductResponseDto {
    private String productUuid;
    private String productName;
    private int amount;

    public ProductResponseDto() {
    }

    public ProductResponseDto(String productUuid,
                              String productName,
                              int amount) {
        this.productUuid = productUuid;
        this.productName = productName;
        this.amount = amount;
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
        ProductResponseDto that = (ProductResponseDto) o;
        return amount == that.amount
                && productUuid.equals(that.productUuid)
                && productName.equals(that.productName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(productUuid, productName, amount);
    }

    @Override
    public String toString() {
        return "ProductResponseDto{"
                + "productUuid='" + productUuid + '\''
                + ", productName='" + productName + '\''
                + ", amount=" + amount
                + '}';
    }
}
