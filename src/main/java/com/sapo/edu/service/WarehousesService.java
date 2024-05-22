package com.sapo.edu.service;

import com.sapo.edu.model.Warehouses;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

// Giao diện dịch vụ để quản lý các kho hàng
@Service
public interface WarehousesService {

  List<Warehouses> getAllWarehouses();

Warehouses getWareHouseById(int id);

  Warehouses createWarehouse(Warehouses warehouses);

  Warehouses updateWarehouse(Warehouses warehouses, int id);

void deleteWarehouse(int id);
}
