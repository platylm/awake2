package awake;

import java.sql.Date;
import java.sql.Time;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class WorkTime {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer checkNo;
	private Time checkIn;
	private Time checkOut;
	private Date dateWork;
	private Integer status;
	private Integer Wage;

	@ManyToOne
	@JoinColumn(name = "IdEmp")
	private Employee employee;

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "workTime")
	private List<Payment> payment;

	public WorkTime(Time checkIn, Time checkOut, Date dateWork, Integer status, Employee employee) {
		this.checkIn = checkIn;
		this.checkOut = checkOut;
		this.dateWork = dateWork;
		this.status = status;
		this.employee = employee;
	}

	public WorkTime() {
	}

	public Integer getCheckNo() {
		return checkNo;
	}

	public void setCheckNo(Integer checkNo) {
		this.checkNo = checkNo;
	}

	public Date getDateWork() {
		return dateWork;
	}

	public void setDateWork(Date dateWork) {
		this.dateWork = dateWork;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public Time getCheckIn() {
		return checkIn;
	}

	public void setCheckIn(Time checkIn) {
		this.checkIn = checkIn;
	}

	public Time getCheckOut() {
		return checkOut;
	}

	public void setCheckOut(Time checkOut) {
		this.checkOut = checkOut;
	}

	public List<Payment> getPayment() {
		return payment;
	}

	public void setPayment(List<Payment> payment) {
		this.payment = payment;
	}

	public Integer getWage() {
		return Wage;
	}

	public void setWage(Integer wage) {
		Wage = wage;
	}

}
