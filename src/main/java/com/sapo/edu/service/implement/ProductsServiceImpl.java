package com.sapo.edu.service.implement;

import com.sapo.edu.exception.ResourceNotFoundException;
import com.sapo.edu.model.Products;
import com.sapo.edu.repository.CategoriesRepository;
import com.sapo.edu.repository.ProductsRepository;
import com.sapo.edu.service.ProductsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import java.util.List;
import java.util.Optional;

@Service
public class ProductsServiceImpl implements ProductsService {

    @Autowired
    private ProductsRepository productsRepository;

    @Autowired
    private CategoriesRepository categoriesRepository;

    @Override
    public List<Products> getAllProducts(){
        return productsRepository.findAll();
    }

    @Override
    public Products getProductById(int id){
        Optional<Products> productData = productsRepository.findById(id);
        if(productData.isPresent()){
            return productData.get();
        }
        else{
            throw new ResourceNotFoundException("Product","productId",id);
        }
    }

    @Override
    public Products createProduct(Products products){
        return productsRepository.save(products);
    }

    @Override
    public Products updateProduct(int id, Products products){
        Optional<Products> productData = productsRepository.findById(id);
        if(productData.isPresent()){
            Products productUpdate = productData.get();
            productUpdate.setCategory(products.getCategory());
            productUpdate.setWarehouse(products.getWarehouse());
            productUpdate.setPrice(products.getPrice());
            productUpdate.setName(products.getName());
            productUpdate.setProductDescription(products.getProductDescription());
            productUpdate.setImageUrl(products.getImageUrl());
            productUpdate.setQuantitySold(products.getQuantitySold());
            productUpdate.setQuantityInStock(products.getQuantityInStock());
            return productsRepository.save(productUpdate);
        }
        else{
            throw new ResourceNotFoundException("Product","productId",id);
        }
    }

    @Override
    public void deleteProduct(int id){
        Optional<Products> productData = productsRepository.findById(id);
        if(productData.isPresent()){
           productsRepository.deleteById(id);

        }
        else{
            throw new ResourceNotFoundException("Product","productId",id);
        }
    }

    @Override
    public List<Products> filterProducts(String keyword, String categoryCode) {

            List<Products> filteredProducts = productsRepository.findFilteredProducts(keyword, categoryCode);
           return filteredProducts;

    }

    @Override
    public List<Products> getTop10BestSellingProducts() {

            List<Products> topProducts = productsRepository.findTop10ByOrderByQuantitySoldDesc();
            return topProducts;

    }

    @Override
    public List<Object[]> getProductCountPerCategory() {

            List<Object[]> counts = productsRepository.getProductCountPerCategory();
            return counts;
    }

    @Override
    public List<Products> getProductListPagination(String name, int currentPage) {
            Pageable pageable = PageRequest.of(currentPage, 10, Sort.by("name").ascending());
            List<Products> productsList = productsRepository.findAllByName(name, pageable);
            return productsList;

    }
}
