package awake;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Employee {
	 @Id
	 @GeneratedValue(strategy=GenerationType.AUTO)
	 private Integer IdEmp;
	 private String username;
	 private String ssn;
	 private String fname;
	 private String lname;
	 private String address;
	 private String phone;
	 private Date startDate;
	 private String type;
	 private Integer rate;
	 private String password;
	 @OneToMany(fetch = FetchType.EAGER, mappedBy = "employee")
	 private List<WorkTime> workTime;

	public String getSsn() {
		return ssn;
	}

	public Integer getIdEmp() {
		return IdEmp;
	}

	public void setIdEmp(Integer idEmp) {
		IdEmp = idEmp;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setSsn(String ssn) {
		this.ssn = ssn;
	}

	public String getFname() {
		return fname;
	}

	public void setFname(String fname) {
		this.fname = fname;
	}

	public String getLname() {
		return lname;
	}

	public void setLname(String lname) {
		this.lname = lname;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Integer getRate() {
		return rate;
	}

	public void setRate(Integer rate) {
		this.rate = rate;
	}

	public List<WorkTime> getWorkTime() {
		return workTime;
	}

	public void setWorkTime(List<WorkTime> workTime) {
		this.workTime = workTime;
	}	 
}
