package com.sam.eventhall.Repository;

import com.sam.eventhall.Model.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;
import java.time.LocalDateTime;

public interface ReservationRepository extends JpaRepository<Reservation, Integer> {

    @Query(value = "SELECT r.hall_id FROM reservations r " +
            "WHERE r.status IN ('Confirmed', 'Pending') " +
            // This is the standard formula for checking if two time ranges overlap
            "AND (r.starts_on < :newEndsOn AND r.ends_on > :newStartsOn)",
            nativeQuery = true)
    List<Integer> findConflictingHallIds(
            @Param("newStartsOn") LocalDateTime startsOn,
            @Param("newEndsOn") LocalDateTime endsOn
    );
}