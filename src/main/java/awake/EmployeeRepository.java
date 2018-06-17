package awake;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface EmployeeRepository extends CrudRepository<Employee, Integer> {
	
	@Query(value = "SELECT * FROM employee WHERE  id_emp = ?1 AND password = ?2", nativeQuery = true)
	List<Employee>  getEmployeeByID(String id_emp,String password);
	
	@Query(value = "SELECT * FROM employee WHERE type = 'Pre-Employee'", nativeQuery = true)
	List<Employee>  findEmployeeByTypeTestTime();
	
	@Query(value = "SELECT * FROM employee WHERE type = 'Full-Employee'", nativeQuery = true)
	List<Employee>  findEmployeeByTypePartTime();

	@Query(value = "SELECT * FROM employee,work_time WHERE employee.id_emp = work_time.id_emp", nativeQuery = true)
	List<Employee> findEmployeeByWorkTime();
	
	@Query(value = "SELECT * FROM `employee` WHERE username = ?1", nativeQuery = true)
	List<Employee>  findByusername(String id_emp);
	
	@Query(value = "SELECT * FROM `employee` WHERE id_emp = ?1", nativeQuery = true)
	List<Employee>  findById(Integer id);
	
	@Query(value = "SELECT * FROM `employee` WHERE id_emp = ?1", nativeQuery = true)
	Employee  findId(Integer id);
	
	@Query(value = "SELECT * FROM `employee` WHERE username = ?1", nativeQuery = true)
	Employee  findByUsername(String user);
	
	@Query(value = "Select rate FROM employee WHERE employee.username = ?1", nativeQuery = true)
	Integer findRate(String id);
	
}
