package com.sam.eventhall.Model;

import jakarta.persistence.*;
import lombok.Data;
import java.math.BigDecimal;

@Entity
@Table(name = "equipment")
@Data

public class Equipment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer equipmentId;

    private String equipmentName;
    private Integer totalQuantity;


    @Enumerated(EnumType.STRING)
    private EquipmentStatus status;

    // to ensure numerical values will save as string such as Available, In Use, Maintenance
    public enum EquipmentStatus {
        Available, In_Use, Maintenance
    }
}
