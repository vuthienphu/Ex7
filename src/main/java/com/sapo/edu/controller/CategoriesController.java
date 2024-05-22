package com.sapo.edu.controller;

import com.sapo.edu.model.Categories;
import com.sapo.edu.model.Warehouses;
import com.sapo.edu.service.CategoriesService;
import com.sapo.edu.service.ProductsService;
import com.sapo.edu.service.implement.CategoriesServiceImpl;
import com.sapo.edu.service.implement.ProductsServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class CategoriesController {

    @Autowired
    private CategoriesService categoriesService;

    @Autowired
    private ProductsService productsService;

    @GetMapping("/categories")
    public List<Categories> getAllCategories() {
        return categoriesService.getAllCategories();
    }

    @GetMapping("/categories/{id}")
    public ResponseEntity<Categories> getCategoryById(@PathVariable("id") int id) {
        return new ResponseEntity<Categories>(categoriesService.getCategoryById(id),HttpStatus.OK);
    }

    @PostMapping("/categories")
    public  ResponseEntity<Categories> createCategory(@RequestBody Categories categories){
        return new ResponseEntity<Categories>(categoriesService.createCategory(categories),HttpStatus.CREATED);
    }

    @PutMapping("/categories/{id}")
    public ResponseEntity<Categories> updateCategory(@PathVariable("id") int id, @RequestBody Categories categories) {
        return new ResponseEntity<Categories>(categoriesService.updateCategory(categories,id), HttpStatus.OK);
    }

    @DeleteMapping("/categories/{id}")
    public ResponseEntity<String> deleteCategory(@PathVariable("id") int id) {
        categoriesService.deleteCategory(id);
        return new ResponseEntity<String>("Category deleted successfully", HttpStatus.OK);
    }
}
