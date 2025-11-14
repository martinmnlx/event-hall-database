package Repository;

// Imports the Entity for Payment
import Model.Payment;
// Imports the tool needed to access the database
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Integer> {
}
