package Repository;

import Model.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.time.LocalDate;
import java.time.LocalTime;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Integer> {

    @Query(value = "SELECT r.hall_id FROM reservations r " +
            "WHERE r.event_date = :eventDate " +
            "AND r.status IN ('Confirmed', 'Pending') " +
            "AND ( " +
            "(:startTime < r.end_time AND :endTime > r.start_time) " +
            ")",
            nativeQuery = true)
    List<Integer> findConflictingHallIds(
            @Param("eventDate") LocalDate eventDate,
            @Param("startTime") LocalTime startTime,
            @Param("endTime") LocalTime endTime
    );
}
