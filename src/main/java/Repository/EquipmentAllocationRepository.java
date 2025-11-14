package Repository;

// Imports the Entity for EquipmentAllocation
import Model.EquipmentAllocation;
// Imports the tool needed to access the database
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EquipmentAllocationRepository extends JpaRepository<EquipmentAllocation, Integer>{
}
