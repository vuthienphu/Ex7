package com.sapo.edu.service;

import com.sapo.edu.model.Categories;
import com.sapo.edu.model.Warehouses;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

// Giao diện dịch vụ để xử lý các danh mục
@Service
public interface CategoriesService {

List<Categories> getAllCategories();

  Categories getCategoryById(int id);

    Categories createCategory(Categories categories);

    Categories updateCategory(Categories categories, int id);

    void deleteCategory(int id);

}
