package com.sapo.edu.service.implement;

import com.sapo.edu.exception.ResourceNotFoundException;
import com.sapo.edu.model.Warehouses;
import com.sapo.edu.repository.WarehousesRepository;
import com.sapo.edu.service.WarehousesService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

// Implement các phương thức từ WarehousesService
@Service
public class WarehousesServiceImpl implements WarehousesService {

    @Autowired
    private WarehousesRepository warehousesRepository;

    @Override
    public List<Warehouses> getAllWarehouses() {
        return warehousesRepository.findAll();
    }
    @Override
    public Warehouses getWareHouseById(int id) {

        Optional<Warehouses> warehousesData = warehousesRepository.findById(id);
        if(warehousesData.isPresent()){
            return warehousesData.get();
        }
        else{
           throw new ResourceNotFoundException("WareHouses","warehouseId",id);
        }


    }
    @Override
    public Warehouses createWarehouse(Warehouses warehouses){
        return warehousesRepository.save(warehouses);
    }

    @Override
    public  Warehouses updateWarehouse(Warehouses warehouses, int id){
        Optional<Warehouses> warehouseData = warehousesRepository.findById(id);
        if(warehouseData.isPresent()){
            Warehouses warehousesUpdate = warehouseData.get();
            warehousesUpdate.setWarehouseCode(warehouses.getWarehouseCode());
            warehousesUpdate.setName(warehouses.getName());
            warehousesUpdate.setLocation(warehouses.getLocation());
            return warehousesRepository.save(warehousesUpdate);
        }
        else{
            throw new ResourceNotFoundException("WareHouses","warehouseId",id);
        }
    }


    @Override
    public void deleteWarehouse(int id){

        Optional<Warehouses> warehousesData = warehousesRepository.findById(id);
        if(warehousesData.isPresent()){
            warehousesRepository.deleteById(id);
        }
        else{
            throw new ResourceNotFoundException("WareHouses","warehouseId",id);
        }
    }
}