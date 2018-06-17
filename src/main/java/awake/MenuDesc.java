package awake;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class MenuDesc {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer MId;
	private String MName;
	private Integer discount;
	public Integer getMId() {
		return MId;
	}
	public void setMId(Integer mId) {
		MId = mId;
	}
	public String getMName() {
		return MName;
	}
	public void setMName(String mName) {
		MName = mName;
	}
	public Integer getDiscount() {
		return discount;
	}
	public void setDiscount(Integer discount) {
		this.discount = discount;
	}
	
	
	
}
