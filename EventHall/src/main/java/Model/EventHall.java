package Model;

// used to help us map this class with the database table
import jakarta.persistence.*;
// used for precision in numbers
import java.math.BigDecimal;
// Used for automatic getters and setters
import lombok.Data;

// represents a table in a relational database schema
@Entity
// goes and confirm the name of the table in PostgreSQL
@Table(name = "Event_Halls")
// used to make all getters, setters and constructors
@Data

public class EventHall {

    // will mark hallId as the primary Key
    @Id
    // counter when a new hall is added
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer hallId;


    private String hallName;
    private Integer capacity;
    private String location;

    // to ensure numerical values will save as string such as Available, Booked, Under_Maintenance
    @Enumerated(EnumType.STRING)
    private HallStatus status;

    public enum HallStatus {
        Available, Booked, Under_Maintenance
    }
}
