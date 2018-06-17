package awake;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface WorkTimeRepository extends CrudRepository<WorkTime, Integer> {
	@Query(value = "SELECT * FROM work_time WHERE check_no = ?1", nativeQuery = true)
	List<WorkTime> findByCheckNo(Integer checkno);

	@Query(value = "SELECT * FROM work_time ,employee WHERE work_time.id_emp = employee.id_emp AND employee.id_emp = ?1",nativeQuery = true)
	List<WorkTime> findByworkTime(Integer id);
	
	@Query(value = "SELECT * FROM work_time WHERE id_emp = ?1  AND Status = ?2", nativeQuery = true)
	List<WorkTime> findByIdEmpAndStatus(Integer IdEmp, Integer status);

	@Query(value = "SELECT * FROM work_time ,employee WHERE work_time.id_emp = employee.id_emp AND work_time.date_work= CURDATE()", nativeQuery = true)
	List<WorkTime> findBydateWork();

	@Query(value = "SELECT * FROM work_time ,employee WHERE work_time.id_emp = employee.id_emp", nativeQuery = true)
	List<WorkTime> findAllworkTime();
	
	@Query(value = "SELECT check_no FROM work_time  WHERE id_emp = ?1 AND status = -1", nativeQuery = true)
	Integer getLastCheckIng(Integer id);
	
	@Query(value = "SELECT work_time.check_no,work_time.check_in,work_time.check_out,work_time.date_work,work_time.status,employee.id_emp,employee.fname,employee.lname,work_time.wage FROM work_time,employee WHERE work_time.id_emp = employee.id_emp", nativeQuery = true)
	List<WorkTime> findAllworkTimeAndEmployee();
	
	@Query(value = "SELECT work_time.check_no FROM work_time,employee WHERE employee.username = ?1 AND work_time.status = -1 AND employee.id_emp = work_time.id_emp ", nativeQuery = true)
	Integer getLastCheckIn(String s);
}
