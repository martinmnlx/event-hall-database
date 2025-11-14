package Model;

// used to automatically create getters and setters
import lombok.Data;
// this is used to create a constructor that takes no input
import lombok.NoArgsConstructor;
//this is used to create constructor that lets us input all fields
import lombok.AllArgsConstructor;
// basically a market that tells our system a data is safe, packed up, sent and reused later
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class StaffAssignmentId implements Serializable {
    private Integer staffId;
    private Integer reservationId;
}
