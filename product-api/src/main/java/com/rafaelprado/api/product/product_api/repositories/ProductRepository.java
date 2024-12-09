package com.rafaelprado.api.product.product_api.repositories;

import com.rafaelprado.api.product.product_api.model.Product;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends MongoRepository<Product, String> {
    List<Product> findByCategoryId(String categoryId);

    Optional<Product> findByProductIdentifier(String productIdentifier);
}
