package ensg.tsig.chimn.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
 
@Entity
public class Criteria {
	
	@Id
    @GeneratedValue
    private Long id;
	private String keyword;
	private String license;
	private String owner;
	private int periodicity;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getKeyword() {
		return keyword;
	}
	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
	
	public String getLicense() {
		return license;
	}
	public void setLicense(String license) {
		this.license = license;
	}
	
	public String getOwner() {
		return owner;
	}
	public void setOwner(String owner) {
		this.owner = owner;
	}
	public int getPeriodicity() {
		return periodicity;
	}
	public void setPeriodicity(int periodicity) {
		this.periodicity = periodicity;
	}
	
}
