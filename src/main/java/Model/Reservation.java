package Model;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.LocalDateTime;

@Entity
@Table(name = "Reservations")
@Data

public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer reservationId;

    private Integer userId; // foreign Key to Users table
    private Integer hallId; // foreign Key to EventHall table
    private LocalDate eventDate;
    private LocalTime startTime;
    private LocalTime endTime;
    private String eventType;
    private Integer guessCount;

    // to ensure numerical values will save as string such as Pending, Confirmed, Canceled, Completed
    @Enumerated(EnumType.STRING)
    private ReservationStatus status = ReservationStatus.Pending;

    private LocalDateTime createdAt = LocalDateTime.now();

    public enum ReservationStatus {
        Pending, Confirmed, Canceled, Completed
    }
}
