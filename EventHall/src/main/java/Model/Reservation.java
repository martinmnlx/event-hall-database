package Model;

import jakarta.persistence.*;
import lombok.Data;
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
    private Integer staffId; // foreign Key to Staff table
    private LocalDateTime startsOn;
    private LocalDateTime endsOn;
    private String eventType;
    private Integer guessCount;

    // to ensure numerical values will save as string such as Pending, Confirmed, Canceled, Completed
    @Enumerated(EnumType.STRING)
    private ReservationStatus status = ReservationStatus.Pending;

    private LocalDateTime createdOn = LocalDateTime.now();

    public enum ReservationStatus {
        Pending, Confirmed, Canceled, Completed
    }
}
