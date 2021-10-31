package dao;

import java.util.List;
import java.util.Optional;
import model.Product;

public interface ProductDao {
    Product save(Product product);

    List<Product> getAll();

    Optional<Product> get(String uuid);

    boolean exists(Product product);

    Product update(Product product);
}
