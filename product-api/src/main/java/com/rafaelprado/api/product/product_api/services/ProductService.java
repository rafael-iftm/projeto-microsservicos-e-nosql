package com.rafaelprado.api.product.product_api.services;

import com.rafaelprado.api.product.product_api.model.Product;
import com.rafaelprado.api.product.product_api.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Page<Product> getAllProductsPageable(int page, int size) {
        return productRepository.findAll(PageRequest.of(page, size));
    }

    public Product saveProduct(Product product) {
        return productRepository.save(product);
    }

    public Product updateProduct(String id, Product updatedProduct) {
        Optional<Product> existingProduct = productRepository.findById(id);
        if (existingProduct.isPresent()) {
            updatedProduct.setId(id); // Garante que o ID seja mantido
            return productRepository.save(updatedProduct);
        }
        throw new RuntimeException("Product not found with id: " + id);
    }

    public void deleteProduct(String id) {
        productRepository.deleteById(id);
    }

    public List<Product> getProductsByCategoryId(String categoryId) {
        return productRepository.findByCategoryId(categoryId);
    }

    public Product findByProductIdentifier(String productIdentifier) {
        return productRepository.findByProductIdentifier(productIdentifier)
                .orElseThrow(() -> new RuntimeException("Product not found with identifier: " + productIdentifier));
    }

    public Optional<Product> findById(String id) {
        return productRepository.findById(id);
    }
}
