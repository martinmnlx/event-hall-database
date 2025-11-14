package Repository;

// Imports the Entity for Equipment
import Model.Equipment;
// Imports the tool needed to access the database
import org.springframework.data.jpa.repository. JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EquipmentRepository extends JpaRepository<Equipment, Integer> {

}
