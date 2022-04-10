package com.dietaryrecapp.accessingdatamysql.products;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequiredArgsConstructor
@RequestMapping(path="/products")
public class ProductController {

    private final ProductService productService;

    @GetMapping(path="/all")
    public @ResponseBody Iterable<Product> getAllProducts() {
        return productService.getAllProducts();
    }

    @GetMapping(path="/id/{product_id}")
    public @ResponseBody
    Optional<Product> getProductById(@PathVariable Integer product_id) {
        return productService.getProductById(product_id);
    }

    @GetMapping(path="/name/{product_name}")
    public @ResponseBody
    Optional<Product[]> getProductByName(@PathVariable String product_name) {
        return productService.getProductByName(product_name);
    }

    @PostMapping(path="/add")
    public ResponseEntity<Object> addProduct (@RequestBody Product product){
        Product addedProduct = productService.addProduct(product);
        return new ResponseEntity<>(addedProduct, HttpStatus.OK);
    }

    @PutMapping(path="/update/{id}")
    public ResponseEntity<Object> editUser(@RequestBody Product product) {
        Product editedProd = productService.editProduct(product);
        return new ResponseEntity<>(editedProd, HttpStatus.OK);
    }

}
