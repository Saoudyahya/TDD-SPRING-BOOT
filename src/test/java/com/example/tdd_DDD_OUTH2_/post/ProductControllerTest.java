package com.example.tdd_DDD_OUTH2_.post;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.example.tdd_DDD_OUTH2_.Product.Product;
import com.example.tdd_DDD_OUTH2_.Product.ProductController;
import com.example.tdd_DDD_OUTH2_.Product.ProductService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Arrays;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class ProductControllerTest {


    private MockMvc mockMvc;

    @Mock
    private ProductService productService;

    @InjectMocks
    private ProductController productController;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(productController).build();
    }

    @Test
    void testGetAllProducts() throws Exception {
        Product product1 = new Product(1, "Product1", 10.0);
        Product product2 = new Product(2, "Product2", 20.0);

        when(productService.getAllProducts()).thenReturn(Arrays.asList(product1, product2));

        mockMvc.perform(MockMvcRequestBuilders.get("/api/products"))
                .andExpect(status().isOk());

        verify(productService, times(1)).getAllProducts();
    }

    @Test
    void testGetProductById() throws Exception {
        Product product = new Product(1, "Product1", 10.0);

        when(productService.getProductById(1)).thenReturn(Optional.of(product));

        mockMvc.perform(MockMvcRequestBuilders.get("/api/products/1"))
                .andExpect(status().isOk());

        verify(productService, times(1)).getProductById(1);
    }

    @Test
    void testGetProductByIdNotFound() throws Exception {
        when(productService.getProductById(1)).thenReturn(Optional.empty());

        mockMvc.perform(MockMvcRequestBuilders.get("/api/products/1"))
                .andExpect(status().isNotFound());

        verify(productService, times(1)).getProductById(1);
    }

    @Test
    void testCreateProduct() throws Exception {
        Product product = new Product(1, "Product1", 10.0);

        when(productService.createProduct(any(Product.class))).thenReturn(product);

        mockMvc.perform(MockMvcRequestBuilders.post("/api/products")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(product)))
                .andExpect(status().isOk());

        verify(productService, times(1)).createProduct(any(Product.class));
    }

    @Test
    void testUpdateProduct() throws Exception {
        Product product = new Product(1, "Product1", 10.0);

        when(productService.updateProduct(eq(1), any(Product.class))).thenReturn(product);

        mockMvc.perform(MockMvcRequestBuilders.put("/api/products/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(product)))
                .andExpect(status().isOk());

        verify(productService, times(1)).updateProduct(eq(1), any(Product.class));
    }

    @Test
    void testUpdateProductNotFound() throws Exception {
        when(productService.updateProduct(eq(1), any(Product.class))).thenThrow(new RuntimeException());

        mockMvc.perform(MockMvcRequestBuilders.put("/api/products/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(new Product(2, "Product2", 20.0))))
                .andExpect(status().isNotFound());

        verify(productService, times(1)).updateProduct(eq(1), any(Product.class));
    }

    @Test
    void testDeleteProduct() throws Exception {
        doNothing().when(productService).deleteProduct(1);

        mockMvc.perform(MockMvcRequestBuilders.delete("/api/products/1"))
                .andExpect(status().isNoContent());

        verify(productService, times(1)).deleteProduct(1);
    }

    @Test
    void testDeleteProductNotFound() throws Exception {
        doThrow(new RuntimeException()).when(productService).deleteProduct(1);

        mockMvc.perform(MockMvcRequestBuilders.delete("/api/products/1"))
                .andExpect(status().isNotFound());

        verify(productService, times(1)).deleteProduct(1);
    }
}
