package com.rafaelprado.api.product.product_api.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import com.rafaelprado.api.product.product_api.model.Category;
import com.rafaelprado.api.product.product_api.model.Product;
import com.rafaelprado.api.product.product_api.services.CategoryService;
import com.rafaelprado.api.product.product_api.services.ProductService;

@RestController
@RequestMapping("/product")
public class ProductController {

    private final ProductService productService;
    private final CategoryService categoryService;

    @Autowired
    public ProductController(ProductService productService, CategoryService categoryService) {
        this.productService = productService;
        this.categoryService = categoryService;
    }

    // Endpoints para /product

    // GET /product - Retorna todos os produtos
    @GetMapping
    public List<Product> getAllProducts() {
        return productService.getAllProducts();
    }

    // GET /product/{id} - Retorna um produto pelo ID
    @GetMapping("/{id}")
    public Optional<Product> findProductById(@PathVariable String id) {
        return productService.findById(id);
    }

    // POST /product - Cria um novo produto
    @PostMapping
    public Product saveProduct(@RequestBody Product product) {
        return productService.saveProduct(product);
    }

    // PUT /product/{id} - Atualiza um produto pelo ID
    @PutMapping("/{id}")
    public Product updateProduct(@PathVariable String id, @RequestBody Product product) {
        return productService.updateProduct(id, product);
    }

    // DELETE /product/{id} - Deleta um produto pelo ID
    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable String id) {
        productService.deleteProduct(id);
    }

    // // GET /product/pageable - Retorna todos os produtos paginados
    // @GetMapping("/pageable")
    // public List<Product> getAllProductsPageable(int page, int size) {
    //     return productService.getAllProductsPageable(int page, int size);
    // }

    // GET /product/category/{categoryId} - Retorna produtos por categoria
    @GetMapping("/category/{categoryId}")
    public List<Product> getProductByCategoryId(@PathVariable String categoryId) {
        return productService.getProductsByCategoryId(categoryId);
    }

    // GET /product/{productIdentifier} - Retorna um produto pelo identificador único
    @GetMapping("/identifier/{productIdentifier}")
    public Product findByProductIdentifier(@PathVariable String productIdentifier) {
        return productService.findByProductIdentifier(productIdentifier);
    }

    // Endpoints para /category

    // GET /category - Retorna todas as categorias
    @GetMapping("/category")
    public List<Category> getAllCategories() {
        return categoryService.getAllCategories();
    }

    // POST /category - Cria uma nova categoria
    @PostMapping("/category")
    public Category saveCategory(@RequestBody Category category) {
        return categoryService.saveCategory(category);
    }

    // PUT /category/{id} - Atualiza uma categoria pelo ID
    @PutMapping("/category/{id}")
    public Category updateCategory(@PathVariable String id, @RequestBody Category category) {
        return categoryService.updateCategory(id, category);
    }

    // DELETE /category/{id} - Deleta uma categoria pelo ID
    @DeleteMapping("/category/{id}")
    public void deleteCategory(@PathVariable String id) {
        categoryService.deleteCategory(id);
    }

    // GET /category/pageable - Retorna todas as categorias paginadas
    @GetMapping("/category/pageable")
    public Page<Category> getAllCategoriesPageable(int page, int size) {
        return categoryService.getAllCategoriesPageable(page, size);
    }
}
