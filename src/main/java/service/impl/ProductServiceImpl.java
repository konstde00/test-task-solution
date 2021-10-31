package service.impl;

import dao.ProductDao;
import java.util.List;
import model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import service.ProductService;

@Service
public class ProductServiceImpl implements ProductService {
    private final ProductDao productDao;

    @Autowired
    public ProductServiceImpl(ProductDao productDao) {
        this.productDao = productDao;
    }

    @Override
    public Product save(Product product) {
        return productDao.save(product);
    }

    @Override
    public List<Product> getAll() {
        return productDao.getAll();
    }

    @Override
    public Product get(String uuid) {
        return productDao.get(uuid).get();
    }

    @Override
    public boolean exists(Product product) {
        return productDao.exists(product);
    }

    @Override
    public Product update(Product product) {
        return productDao.update(product);
    }
}
