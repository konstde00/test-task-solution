package service;

import dto.response.ProductResponseDto;

public interface ProductParser {
    public ProductResponseDto parseData(String data);
}
