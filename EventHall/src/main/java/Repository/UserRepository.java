package Repository;

// Imports the Entity for User
import Model.User;
// Imports the tool needed to access the database
import org.springframework.data.jpa.repository.JpaRepository;

// interface for database operations like FindBy, Save, or Delete
// This manages the user model our integer as the primary key
public interface UserRepository extends JpaRepository<User, Integer> {

    // Similar translation to doing SELECT * FROM users WHERE email = (example)
    User findByEmail(String email);
}



