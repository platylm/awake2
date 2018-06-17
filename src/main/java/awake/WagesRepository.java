package awake;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface WagesRepository extends CrudRepository<Wages, Integer> {
	@Query(value = "SELECT wage_no,total_wage,wdate,wages.id_emp,fname,lname,wtime FROM wages,employee WHERE wages.id_emp = employee.id_emp  AND MONTH(wdate) = ?1 AND wage_no >0 GROUP BY wage_no,total_wage,wdate,wages.id_emp"
			, nativeQuery = true)
	List<Wages> findWages(String month);

}
