package dao;

import product.Product;

import java.util.List;

public interface ProductDao {

    Long save(Product product);

    Product getProductById(Long id);
    List getProductList();
    Product update(Product product);
    void delete(Long id);
}
