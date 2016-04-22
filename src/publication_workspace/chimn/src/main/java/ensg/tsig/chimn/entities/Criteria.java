package ensg.tsig.chimn.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
/**
 * 
 *
 */
@Entity
public class Criteria {
	
	@Id
    @GeneratedValue
    private Long id;
	private String keyword;
	private boolean license;
	private String owner;
	private int periodicity=0;
	 
	/**
	  * 
	  * @return id
	  */
	public Long getId() {
		return id;
	}
	
	/**
	 * 
	 * @param id
	 */
	public void setId(Long id) {
		this.id = id;
	}
	
	/**
	 * 
	 * @return keyword
	 */
	public String getKeyword() {
		return keyword;
	}
	
	/**
	 * 
	 * @param keyword
	 */
	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
	
	/**
	 * 
	 * @return license
	 */
	public boolean isLicense()
	{
		return this.license;
	}
	
	/**
	 * 
	 * @param license
	 */
	public void setLicense(boolean license) {
		this.license = license;
	}
	
	/**
	 * 
	 * @return owner
	 */
	public String getOwner() {
		return owner;
	}
	
	/**
	 * 
	 * @param owner
	 */
	public void setOwner(String owner) {
		this.owner = owner;
	}
	
	/**
	 * 
	 * @return periodicity
	 */
	public int getPeriodicity() {
		return periodicity;
	}
	
	/**
	 * 
	 * @param periodicity
	 */
	public void setPeriodicity(int periodicity) {
		this.periodicity = periodicity;
	}
	
}
