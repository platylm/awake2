package awake;

import java.sql.Date;
import java.sql.Time;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Wages {
	@Id
	 @GeneratedValue(strategy=GenerationType.AUTO)
	private Integer WageNo;
	private Integer TotalWage;
	private Date WDate;
	private Time WTime;
	
	@ManyToOne
	@JoinColumn(name="IdEmp")
	private Employee employee;
	
	public Wages(Integer TotalWage,Date WDate, Time WTime,Employee employee){
		this.TotalWage = TotalWage;
		this.WDate = WDate;
		this.WTime = WTime;
		this.employee = employee;
	}
	public Wages(){}
	public Integer getWageNo() {
		return WageNo;
	}
	public void setWageNo(Integer wageNo) {
		WageNo = wageNo;
	}
	public Integer getTotalWage() {
		return TotalWage;
	}
	public void setTotalWage(Integer totalWage) {
		TotalWage = totalWage;
	}
	public Date getWDate() {
		return WDate;
	}
	public void setWDate(Date wDate) {
		WDate = wDate;
	}
	public Time getWTime() {
		return WTime;
	}
	public void setWTime(Time wTime) {
		WTime = wTime;
	}
	public Employee getEmployee() {
		return employee;
	}
	public void setEmployee(Employee employee) {
		this.employee = employee;
	}
	
}
