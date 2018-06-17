package awake;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Payment {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer idPay;
	private Integer totalWage;
	private Date dateBill;
	
	@ManyToOne
	@JoinColumn(name="IdEmp")
	private Employee employee;
	
	@ManyToOne
	@JoinColumn(name="checkNo")
	private WorkTime workTime;
	
	public Payment(Integer totalWage, Date dateBill, Employee employee, WorkTime workTime){
		this.totalWage = totalWage;
		this.dateBill = dateBill;
		this.employee = employee;
		this.workTime = workTime;
	}
	public Payment(){}
	public Integer getIdPay() {
		return idPay;
	}
	public void setIdPay(Integer idPay) {
		this.idPay = idPay;
	}
	public Integer getTotalWage() {
		return totalWage;
	}
	public void setTotalWage(Integer totalWage) {
		this.totalWage = totalWage;
	}
	public Date getDateBill() {
		return dateBill;
	}
	public void setDateBill(Date dateBill) {
		this.dateBill = dateBill;
	}
	public Employee getEmployee() {
		return employee;
	}
	public void setEmployee(Employee employee) {
		this.employee = employee;
	}
	public WorkTime getWorkTime() {
		return workTime;
	}
	public void setWorkTime(WorkTime workTime) {
		this.workTime = workTime;
	}
	
	
}
