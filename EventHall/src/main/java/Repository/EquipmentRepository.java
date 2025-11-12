package Repository;

// Imports the Entity for Equipment
import Model.Equipment;
// Imports the tool needed to access the database
import org.springframework.data.jpa.repository. JpaRepository;

public interface EquipmentRepository extends JpaRepository<Equipment, Integer> {

}
