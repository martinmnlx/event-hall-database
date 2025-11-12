package Model;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

// similar to the one used in EventHall.java
@Entity
@Table(name = "Users")
@Data

public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer userId;

    // to ensure numerical values will save as string such as Customer, Admin, Staff
    @Enumerated(EnumType.STRING)
    private UserType type;

    private String name;
    @Column(unique = true)
    private String email;

    private String phone;
    private String passwordHash;
    private LocalDateTime dateCreated = LocalDateTime.now();

    public enum UserType {
        Customer, Admin, Staff
    }
}

