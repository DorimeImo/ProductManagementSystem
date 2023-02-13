package controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import product.Product;
import service.ProductService;

import java.util.List;

@RestController
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping (path = "/saveProduct", consumes = {"application/json"}, produces = {"application/json"})
    @ResponseStatus(HttpStatus.CREATED)
    public Long saveProduct(@RequestBody Product product){
        return productService.save(product);
    }

    @GetMapping(path="/getProductById/{id}", produces = {"application/json"})
    @ResponseStatus(HttpStatus.OK)
    public Product getById(@PathVariable Long id){
        return productService.getById(id);
    }

    @PutMapping(path = "/updateProduct", consumes = {"application/json"}, produces = {"application/json"})
    @ResponseStatus(HttpStatus.CREATED)
    public Product updateProduct(@RequestBody Product product){
        return productService.update(product);
    }

    @GetMapping (path ="/getAllProducts")
    @ResponseStatus(HttpStatus.OK)
    public List<Product> getAllProducts(){
        return productService.getProductList();
    }

    @DeleteMapping(path="/deleteProduct/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteProductById(@PathVariable Long id){
        productService.delete(id);
    }

}
