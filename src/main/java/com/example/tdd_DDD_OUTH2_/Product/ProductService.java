package com.example.tdd_DDD_OUTH2_.Product;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

public class ProductService {


    private Productrepository productRepository;

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Optional<Product> getProductById(Integer id) {
        return productRepository.findById(id);
    }

    public Product createProduct(Product product) {
        return productRepository.save(product);
    }

    public Product updateProduct(Integer id, Product productDetails) {
        Optional<Product> optionalProduct = productRepository.findById(id);
        if (optionalProduct.isPresent()) {
            Product existingProduct = optionalProduct.get();
            existingProduct.setRef(productDetails.getRef());
            existingProduct.setQuantity(productDetails.getQuantity());
            return productRepository.save(existingProduct);
        } else {
            throw new RuntimeException("Product not found with id " + id);
        }
    }

    public void deleteProduct(Integer id) {
        Optional<Product> optionalProduct = productRepository.findById(id);
        if (optionalProduct.isPresent()) {
            productRepository.delete(optionalProduct.get());
        } else {
            throw new RuntimeException("Product not found with id " + id);
        }
    }
}
