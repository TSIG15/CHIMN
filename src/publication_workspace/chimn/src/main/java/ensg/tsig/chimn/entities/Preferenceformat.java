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
public class Preferenceformat {
	@Id
    @GeneratedValue
    private Long id;
	private String nom;
	private boolean activated;
	
	
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public boolean isActivated() {
		return activated;
	}
	public void setActivated(boolean activated) {
		this.activated = activated;
	}
	

}
