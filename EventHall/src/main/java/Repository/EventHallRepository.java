package Repository;

// Imports the Entity for EventHall
import Model.EventHall;
// Imports the tool needed to access the database
import org.springframework.data.jpa.repository.JpaRepository;
// Will be used for returning a list of EventHall objects
import java.util.List;

public interface EventHallRepository extends JpaRepository<EventHall, Integer>{

    // Similar translation to SELECT * FROM event_halls WHERE status = ?
    // Example if we find something by Status in our frontend
    List<EventHall> findByStatus(EventHall.HallStatus status);
}
