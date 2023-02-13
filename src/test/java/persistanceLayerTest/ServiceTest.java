package persistanceLayerTest;

import dao.ProductDao;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import product.Product;
import product.details.ProductType;
import product.details.Status;
import service.ProductServiceImpl;

import java.util.ArrayList;
import java.util.List;

@ExtendWith(MockitoExtension.class)
public class ServiceTest {

    @InjectMocks
    ProductServiceImpl productService;

    @Mock
    ProductDao productDao;

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
    public void whenSaveProduct_thenReturnProductDbId(){
        BDDMockito.given(productDao.save(product)).willReturn(product.getDbId());

        Assertions.assertThat(productService.save(product)).isEqualTo(product.getDbId());
    }

    @Test
    public void whenGetById_thenReturnProduct(){
        BDDMockito.given(productDao.getProductById(product.getDbId())).willReturn(product);

        Assertions.assertThat(productService.getById(product.getDbId())).isEqualTo(product);
    }

    @Test
    public void whenGetProductList_thenReturnProductList(){
        List<Product> productList = new ArrayList<>();
        productList.add(product);

        BDDMockito.given(productDao.getProductList()).willReturn(productList);

        Assertions.assertThat(productService.getProductList()).isEqualTo(productList);
    }

    @Test
    public void whenUpdate_thenReturnProduct(){
        BDDMockito.given(productDao.update(product)).willReturn(product);

        Assertions.assertThat(productService.update(product)).isEqualTo(product);
    }

    @Test
    public void whenDelete_thenDeleteMethodIsCalled(){
        BDDMockito.doNothing().when(productDao).delete(product.getDbId());

        productService.delete(product.getDbId());

        BDDMockito.verify(productDao, Mockito.times(1)).delete(product.getDbId());
    }
}
