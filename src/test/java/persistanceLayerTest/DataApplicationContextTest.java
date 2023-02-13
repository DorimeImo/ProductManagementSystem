package persistanceLayerTest;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;


public class DataApplicationContextTest extends ApplicationContextForDataTest {

    @Autowired
    private ApplicationContext applicationContext;

    @Test
    public void dataSourceIsLoaded(){
        Assertions.assertThat(applicationContext.getBean("dataSource")).isNotNull();
    }

    @Test
    public void transactionalManagerIsLoaded(){
        Assertions.assertThat(applicationContext.getBean("transactionManager")).isNotNull();
    }

    @Test
    public void sessionFactoryIsLoaded(){
        Assertions.assertThat(applicationContext.getBean("sessionFactory")).isNotNull();
    }

    @Test
    public void productDaoIsLoaded(){
        Assertions.assertThat(applicationContext.getBean("productDaoImpl")).isNotNull();
    }

    @Test
    public void productServiceIsLoaded(){
        Assertions.assertThat(applicationContext.getBean("productServiceImpl")).isNotNull();
    }



}
