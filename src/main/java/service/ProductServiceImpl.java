package service;

import dao.ProductDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import product.Product;

import java.util.List;


@Service
@Transactional
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductDao productDao;

    @Override
    public Long save(Product product) {
        productDao.save(product);
        return product.getDbId();
    }

    @Override
    public Product getById(Long id) {
        return productDao.getProductById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List getProductList() {
        return productDao.getProductList();
    }

    @Override
    public Product update(Product product) {
        return productDao.update(product);
    }

    @Override
    public void delete(Long id) {
        productDao.delete(id);
    }
}
