package com.example.Web_Koleso.repositories;

import com.example.Web_Koleso.models.Warehouse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface WarehouseRepository extends JpaRepository<Warehouse, Long> {
}
