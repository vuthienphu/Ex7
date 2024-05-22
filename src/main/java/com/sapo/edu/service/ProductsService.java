package com.sapo.edu.service;

import com.sapo.edu.model.Products;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ProductsService {

   List<Products> getAllProducts();

   Products getProductById(int id);

   Products createProduct(Products products);

  Products updateProduct(int id, Products products);

  void deleteProduct(int id);

   List<Products> filterProducts(String keyword, String categoryCode);

    List<Products> getTop10BestSellingProducts();

    List<Object[]> getProductCountPerCategory();

    List<Products> getProductListPagination(String name, int currentPage);
}
