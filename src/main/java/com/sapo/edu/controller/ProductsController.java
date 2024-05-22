package com.sapo.edu.controller;

import com.sapo.edu.model.Products;
import com.sapo.edu.model.Warehouses;
import com.sapo.edu.service.CategoriesService;
import com.sapo.edu.service.ProductsService;
import com.sapo.edu.service.implement.ProductsServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class ProductsController {

    @Autowired
    private ProductsService productsService;

    @Autowired
    private CategoriesService categoriesService;

    @GetMapping("/products/all")
    public List<Products> getAllProducts() {
        return productsService.getAllProducts();
    }

    @GetMapping("/products/{id}")
    public ResponseEntity<Products> getProductById(@PathVariable("id") int id) {
        return new ResponseEntity<Products>(productsService.getProductById(id),HttpStatus.OK);
    }

    @PostMapping("/products")
    public  ResponseEntity<Products> createProduct(@RequestBody Products products){
        return new ResponseEntity<Products>(productsService.createProduct(products),HttpStatus.CREATED);
    }

    @PutMapping("/products/{id}")
    public ResponseEntity<Products> updateProduct(@PathVariable("id") int id, @RequestBody Products products) {
        return new ResponseEntity<Products>(productsService.updateProduct(id, products), HttpStatus.OK);
    }

    @DeleteMapping("/products/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable("id") int id) {
        productsService.deleteProduct(id);
        return new ResponseEntity<String>("Product deleted successfully", HttpStatus.OK);
    }

    @GetMapping("/products/filter")
    public List<Products> filterProducts(@RequestParam String keyword, @RequestParam String categoryCode) {
        return productsService.filterProducts(keyword, categoryCode);
    }

    @GetMapping("/products/top-best-selling")
    public List<Products> getTop10BestSellingProducts() {
        return productsService.getTop10BestSellingProducts();
    }

    @GetMapping("/products/product-count")
    public List<Object[]> getProductCountPerCategory() {
        return productsService.getProductCountPerCategory();
    }

    @GetMapping("/products")
    public List<Products> getProductListByName(@RequestParam String name, @RequestParam int currentPage) {
        return productsService.getProductListPagination(name, currentPage);
    }
}
