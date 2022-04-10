package com.dietaryrecapp.accessingdatamysql.products;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    public Iterable<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Optional<Product> getProductById(Integer product_id) {
        return productRepository.findById(product_id);
    }

    public Optional<Product[]> getProductByName(String product_name) {
        return productRepository.findByName(product_name);
    }

    public Product addProduct(Product product) {
        return productRepository.save(product);
    }

    @Transactional
    public Product editProduct(Product product) {
        System.out.println(product.getProduct_id());
        return productRepository.save(product);
    }

}
