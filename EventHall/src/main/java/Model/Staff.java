package Model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "Staff")
@Data

public class Staff {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer staffId;

    private String name;
    private String role;
    private String department;
    private String contactNumber;
    private String shiftSchedule;
}
