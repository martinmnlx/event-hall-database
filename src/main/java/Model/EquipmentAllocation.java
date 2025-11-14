package Model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "Equipment_Allocations")
@Data

public class EquipmentAllocation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer allocId;

    private Integer reservationId; // Foreign Key
    private Integer equipmentId; // Foreign Key
    private Integer quantity;
}
