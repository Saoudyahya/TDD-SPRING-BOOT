package com.example.tdd_DDD_OUTH2_.Product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/products")
public class ProductController  {
      private ProductService productService;

        @GetMapping
        public List<Product> getAllProducts() {
            return productService.getAllProducts();
        }

        @GetMapping("/{id}")
        public ResponseEntity<Product> getProductById(@PathVariable Integer id) {
            Optional<Product> product = productService.getProductById(id);
            return product.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
        }

        @PostMapping
        public Product createProduct(@RequestBody Product product) {
            return productService.createProduct(product);
        }

        @PutMapping("/{id}")
        public ResponseEntity<Product> updateProduct(@PathVariable Integer id, @RequestBody Product productDetails) {
            try {
                Product updatedProduct = productService.updateProduct(id, productDetails);
                return ResponseEntity.ok(updatedProduct);
            } catch (RuntimeException e) {
                return ResponseEntity.notFound().build();
            }
        }

        @DeleteMapping("/{id}")
        public ResponseEntity<Void> deleteProduct(@PathVariable Integer id) {
            try {
                productService.deleteProduct(id);
                return ResponseEntity.noContent().build();
            } catch (RuntimeException e) {
                return ResponseEntity.notFound().build();
            }
        }

}
