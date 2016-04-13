package ensg.tsig.chimn.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
 
@Entity

public class PreferenceSRS {
	
	@Id
    @GeneratedValue
    
	private String nom;
	private String codeEpsg;
	private boolean activated;
	
	
	
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	
	
	public String getcodeEpsg() {
		return codeEpsg;
	}
	public void setcodeEpsg(String codeEpsg) {
		this.codeEpsg = codeEpsg;
	}
	
	
	public boolean isActivated() {
		return activated;
	}
	public void setActivated(boolean activated) {
		this.activated = activated;
	}
	
	
	

}
