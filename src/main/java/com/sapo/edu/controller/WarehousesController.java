package com.sapo.edu.controller;

import com.sapo.edu.model.Warehouses;
import com.sapo.edu.repository.WarehousesRepository;
import com.sapo.edu.service.WarehousesService;
import com.sapo.edu.service.implement.WarehousesServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class WarehousesController {

@Autowired
    private WarehousesService warehousesService;

    @GetMapping("/warehouses")
    public List<Warehouses> getAllWarehouses() {
        return warehousesService.getAllWarehouses();
    }

    @GetMapping("/warehouses/{id}")
    public ResponseEntity<Warehouses> getProductById(@PathVariable("id") int id) {
return new ResponseEntity<Warehouses>(warehousesService.getWareHouseById(id),HttpStatus.OK);
    }

    @PostMapping("/warehouses")
    public  ResponseEntity<Warehouses> createWarehouse(@RequestBody Warehouses warehouses){
        return new ResponseEntity<Warehouses>(warehousesService.createWarehouse(warehouses),HttpStatus.CREATED);
    }


    @PutMapping("/warehouses/{id}")
    public ResponseEntity<Warehouses> updateWarehouse(@PathVariable("id") int id, @RequestBody Warehouses warehouses) {
        return new ResponseEntity<Warehouses>(warehousesService.updateWarehouse(warehouses,id), HttpStatus.OK);
    }

    @DeleteMapping("/warehouses/{id}")
    public ResponseEntity<String> deleteWarehouse(@PathVariable("id") int id) {
        warehousesService.deleteWarehouse(id);
        return new ResponseEntity<String>("Warehouse deleted successfully", HttpStatus.OK);
    }
}
