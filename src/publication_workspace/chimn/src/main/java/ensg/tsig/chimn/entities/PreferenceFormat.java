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
	private String nameFm;
	private boolean activated;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNameFm() {
		return nameFm;
	}
	public void setNameFm(String nameFm) {
		this.nameFm = nameFm;
	}
	public boolean isActivated() {
		return activated;
	}
	public void setActivated(boolean activated) {
		this.activated = activated;
	}

}
