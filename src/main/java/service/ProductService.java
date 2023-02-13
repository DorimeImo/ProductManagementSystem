package service;

import product.Product;

import java.util.List;

public interface ProductService {

    Long save(Product product);
    List<Product> getProductList();

    Product getById(Long id);

    Product update(Product product);

    void delete(Long id);
}
