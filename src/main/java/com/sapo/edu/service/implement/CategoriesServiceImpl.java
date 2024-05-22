package com.sapo.edu.service.implement;

import com.sapo.edu.exception.ResourceNotFoundException;
import com.sapo.edu.model.Categories;
import com.sapo.edu.model.Warehouses;
import com.sapo.edu.repository.CategoriesRepository;
import com.sapo.edu.repository.ProductsRepository;
import com.sapo.edu.service.CategoriesService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

// Implement các phương thức từ CategoriesService
@Service
public class CategoriesServiceImpl implements CategoriesService {


@Autowired
private CategoriesRepository categoriesRepository;

@Autowired
private ProductsRepository productsRepository;

    @Override
    public List<Categories> getAllCategories() {
        return categoriesRepository.findAll();
    }

    @Override
    public Categories getCategoryById(int id) {

        Optional<Categories> categoryData = categoriesRepository.findById(id);
        if(categoryData.isPresent()){
            return categoryData.get();
        }
        else{
            throw new ResourceNotFoundException("Category","categoryId",id);
        }


    }
    @Override
    public Categories createCategory(Categories categories){
        return categoriesRepository.save(categories);
    }

    @Override
    public  Categories updateCategory(Categories categories, int id){
        Optional<Categories> categoryData = categoriesRepository.findById(id);
        if(categoryData.isPresent()){
            Categories categoryUpdate = categoryData.get();
            categoryUpdate.setCategoryCode(categories.getCategoryCode());
            categoryUpdate.setName(categories.getName());
            categoryUpdate.setDescription(categories.getDescription());
            return categoriesRepository.save(categoryUpdate);
        }
        else{
            throw new ResourceNotFoundException("Category","categoryId",id);
        }
    }


    @Override
    @Transactional
    public void deleteCategory(int id){

        Optional<Categories> categoryData = categoriesRepository.findById(id);
        if(categoryData.isPresent()){
            productsRepository.deleteByCategory_CategoryId(id);
           categoriesRepository.deleteById(id);

        }
        else{
            throw new ResourceNotFoundException("Category","categoryId",id);
        }
    }

}
