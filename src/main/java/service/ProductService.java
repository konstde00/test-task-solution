package service;

import java.util.List;
import model.Product;

public interface ProductService {
    Product save(Product product);

    List<Product> getAll();

    Product get(String uuid);

    boolean exists(Product product);

    Product update(Product product);
}
