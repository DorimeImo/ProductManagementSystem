package controllersLayerTest;

import com.fasterxml.jackson.databind.ObjectMapper;
import config.SecurityConfig;
import config.WebConfig;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.json.JsonParser;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import product.Product;
import product.details.ProductType;
import product.details.Status;
import service.ProductService;


import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(classes = {SecurityConfig.class, WebConfig.class} )
@WithMockUser("username")
class SecuredProductControllerTest {

    @MockBean
    private ProductService productService;

    @Autowired
    private WebApplicationContext context;

    private MockMvc mockMvc;

    private Product product;

    private String productAsJson;

    @BeforeEach
    public void setup() {
        mockMvc = MockMvcBuilders
                .webAppContextSetup(context)
                .apply(springSecurity())
                .build();

        product = new Product();
        product.setDbId(1L);
        product.setProductId("PRD");
        product.setProductType(ProductType.PACKAGE);
        product.setStatus(Status.RETURNED);

        try{
            productAsJson = new ObjectMapper().writeValueAsString(product);
        }
        catch (Exception e){
            System.out.println(e.getMessage());
            productAsJson = null;
        }

    }

    @Test
    void whenSaveProduct_thenReturnDbId() throws Exception{
        BDDMockito.when(productService.save(product)).thenReturn(product.getDbId());


        mockMvc.perform(post("/saveProduct")
                        .characterEncoding("utf-8")
                        .content(productAsJson)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$").value(product.getDbId()));
    }

    @Test
    void whenGetById_thenReturnProduct() throws Exception{
        BDDMockito.when(productService.getById(product.getDbId())).thenReturn(product);

        mockMvc.perform(get("/getProductById/1")
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.dbId").value(product.getDbId()));
    }

    @Test
    void whenUpdateProduct_thenReturnProduct() throws Exception{
        BDDMockito.when(productService.update(product)).thenReturn(product);

        mockMvc.perform(put("/updateProduct")
                        .characterEncoding("utf-8")
                        .content(productAsJson)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.dbId").value(product.getDbId()));
    }

    @Test
    void whenGetAllProducts_thenReturnProductList() throws Exception{
        List<Product> productList = new ArrayList<>();
        productList.add(product);

        BDDMockito.when(productService.getProductList()).thenReturn(productList);

        mockMvc.perform(get("/getAllProducts"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[*].dbId", containsInAnyOrder(1)))
                .andExpect(jsonPath("$[*].productId", containsInAnyOrder("PRD")));
    }

    @Test
    void whenDeleteProductById_thenReturnNoContent() throws Exception{

        BDDMockito.doNothing().when(productService).delete(product.getDbId());

        mockMvc.perform(delete("/deleteProduct/1"))
                .andExpect(status().isNoContent());
    }

}