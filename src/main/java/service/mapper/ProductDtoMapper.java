package service.mapper;

import dto.response.ProductResponseDto;
import model.Product;
import org.springframework.stereotype.Component;

@Component
public class ProductDtoMapper {
    public Product mapToModel(ProductResponseDto dto) {
        Product product = new Product();
        product.setProductUuid(dto.getProductUuid());
        product.setProductName(dto.getProductName());
        product.setAmount(dto.getAmount());
        return product;
    }

    public ProductResponseDto parse(Product product) {
        ProductResponseDto responseDto = new ProductResponseDto();
        responseDto.setProductUuid(product.getProductUuid());
        responseDto.setProductName(product.getProductName());
        responseDto.setAmount(product.getAmount());
        return responseDto;
    }
}
