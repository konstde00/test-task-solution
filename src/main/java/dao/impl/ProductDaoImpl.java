package dao.impl;

import dao.ProductDao;
import exception.DataProcessingException;
import java.util.Optional;
import model.Product;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ProductDaoImpl extends AbstractDao<Product, String> implements ProductDao {
    @Autowired
    public ProductDaoImpl(SessionFactory sessionFactory) {
        super(sessionFactory, Product.class);
    }

    @Override
    public Optional<Product> get(String uuid) {
        try (Session session = sessionFactory.openSession()) {
            Query<Product> getProductsQuery = session
                    .createQuery("FROM Product p WHERE p.productUuid = :uuid ",
                            Product.class);
            getProductsQuery.setParameter("uuid", uuid);
            return Optional.ofNullable(getProductsQuery.getResultList().get(0));
        } catch (Exception e) {
            throw new DataProcessingException("Can't get user by id: " + uuid, e);
        }
    }

    @Override
    public boolean exists(Product product) {
        if (product == null) {
            return false;
        }
        try (Session session = sessionFactory.openSession()) {
            Query<Product> getProductsQuery = session
                    .createQuery("FROM Product p WHERE p.productUuid = :uuid "
                            + "AND p.productName = :productName ", Product.class);
            getProductsQuery.setParameter("uuid", product.getProductUuid());
            getProductsQuery.setParameter("productName", product.getProductName());
            return !getProductsQuery.getResultList().isEmpty();
        } catch (Exception e) {
            throw new DataProcessingException("Exception during executing operation with DB", e);
        }
    }

    @Override
    public Product update(Product product) {
        Session session = null;
        Transaction transaction = null;
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            int newAmount = product.getAmount()
                    + get(product.getProductUuid()).get().getAmount();
            product.setAmount(newAmount);
            session.update(product);
            transaction.commit();
            return product;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new DataProcessingException("Can't update product " + product + " in DB", e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }
}
