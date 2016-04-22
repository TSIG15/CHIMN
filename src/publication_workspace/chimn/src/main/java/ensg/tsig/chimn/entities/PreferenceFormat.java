/**
 * 
 */
package ensg.tsig.chimn.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * @author hanane
 *
 */
@Entity
public class PreferenceFormat {
	
	@Id
    @GeneratedValue
    private Long id;
	private String nameformat;
	private boolean activateformat;
	
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
	 * @return nameformat
	 */
	public String getNameformat() {
		return nameformat;
	}
	
	/**
	 * 
	 * @param nameformat
	 */
	public void setNameformat(String nameformat) {
		this.nameformat = nameformat;
	}
	
	/**
	 * 
	 * @return activateformat
	 */
	public boolean isActivateformat() {
		return activateformat;
	}
	
	/**
	 * 
	 * @param activateformat
	 */
	public void setActivateformat(boolean activateformat) {
		this.activateformat = activateformat;
	}

}
