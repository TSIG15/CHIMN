package ensg.tsig.chimn.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
 
@Entity
public class Criteres {
	
	@Id
    @GeneratedValue
    private Long id;
	private String motcle;
	private String license;
	private String proprietaire;
	private int periodicite;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getMotcle() {
		return motcle;
	}
	public void setMotcle(String motcle) {
		this.motcle = motcle;
	}
	
	public String getLicense() {
		return license;
	}
	public void setLicense(String license) {
		this.license = license;
	}
	
	public String getProprietaire() {
		return proprietaire;
	}
	public void setProprietaire(String proprietaire) {
		this.proprietaire = proprietaire;
	}
	public int getPeriodicite() {
		return periodicite;
	}
	public void setPeriodicite(int periodicite) {
		this.periodicite = periodicite;
	}
	
	

}
