package com.sam.eventhall.Model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "staff_assignments")
// tells JPA to use the staffAssignmentId class to identify the primary key combo
@IdClass(StaffAssignmentId.class)
@Data

public class StaffAssignment {

    @Id
    private Integer staffId;

    @Id
    private Integer reservationId;

    private Integer hallId;
    private String roleDescription;
}
