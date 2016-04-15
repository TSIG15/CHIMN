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
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNameformat() {
		return nameformat;
	}
	public void setNameformat(String nameformat) {
		this.nameformat = nameformat;
	}
	public boolean isActivateformat() {
		return activateformat;
	}
	public void setActivateformat(boolean activateformat) {
		this.activateformat = activateformat;
	}

}
