package persistanceLayerTest;

import dao.ProductDao;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import product.Product;
import product.details.ProductType;
import product.details.Status;


@Transactional
public class ProductDaoTest extends ApplicationContextForDataTest {
    @Autowired
    private ProductDao productDao;

    private Product product;

    @BeforeEach
    public void setUp(){
        product = new Product();
        product.setDbId(1L);
        product.setProductId("PRD");
        product.setProductType(ProductType.PACKAGE);
        product.setStatus(Status.RETURNED);
    }

    @Test
    public void whenGetById_thenReturnProduct(){
        productDao.save(product);

        Assertions.assertThat(productDao.getProductById(product.getDbId())).isEqualTo(product);
    }

    @Test
    public void whenGetList_thenReturnProductList(){
        productDao.save(product);

        Assertions.assertThat(productDao.getProductList().get(0)).isEqualTo(product);
    }

    @Test
    public void whenDeleteById_thenNoProductWithIdReturned(){
        productDao.save(product);
        productDao.delete(product.getDbId());
        Assertions.assertThat(productDao.getProductById(product.getDbId())).isNull();
    }

    @Test
    public void testSaveAndUpdate(){
        productDao.save(product);

        product.setProductId("TEST");

        productDao.update(product);

        Assertions.assertThat(productDao.getProductById(product.getDbId()).getProductId()).isEqualTo(product.getProductId());
    }
}
