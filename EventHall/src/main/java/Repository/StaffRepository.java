package com.sam.eventhall.Repository;

// Imports the Entity for User
import com.sam.eventhall.Model.Staff;
// Imports the tool needed to access the database
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StaffRepository extends JpaRepository<Staff, Integer> {
}
