package awake;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface PaymentRepository extends CrudRepository<Payment, Integer> {
	@Query(value = "SELECT * FROM payment,employee WHERE payment.id_emp = employee.id_emp", nativeQuery = true)
	List<Payment> findPaymentByEmployee();

}
