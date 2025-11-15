package com.sam.eventhall.Repository;

// Imports the Entity for EquipmentAllocation
import com.sam.eventhall.Model.EquipmentAllocation;
// Imports the tool needed to access the database
import org.springframework.data.jpa.repository.JpaRepository;

public interface EquipmentAllocationRepository extends JpaRepository<EquipmentAllocation, Integer>{
}
