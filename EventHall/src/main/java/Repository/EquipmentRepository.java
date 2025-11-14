package Repository;

// imports the Entity for Equipment
import Model.Equipment;
// imports the tool needed to access the database
import org.springframework.data.jpa.repository. JpaRepository;
// to be able to write our custom SQL
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface EquipmentRepository extends JpaRepository<Equipment, Integer> {

    // need so we can calculate the total quantity needed to be reserved for equipment_allocation
    @Query(value = "SELECT SUM(ea.quantity) FROM equipment_allocations ea " +
            "JOIN reservations r ON ea.reservation_id = r.reservation_id " +
            "WHERE ea.equipment_id = :equipmentId " +
            "AND r.status IN ('Confirmed', 'Pending')",
            nativeQuery = true)
    Integer getTotalReservedQuantityForEquipmentId(@Param("equipmentId") Integer equipmentId);

}
