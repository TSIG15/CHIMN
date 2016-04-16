package ensg.tsig.chimn.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
 
@Entity

public class PreferenceSRS {
	
	@Id
    @GeneratedValue
	private String namesrs;
	private String epsg;
	private boolean activatesrs;
	
	
	public String getNameSRS() {
		return namesrs;
	}
	public void setNameSRS(String namesrs) {
		this.namesrs = namesrs;
	}
	public String getEpsg() {
		return epsg;
	}
	public void setEpsg(String epsg) {
		this.epsg = epsg;
	}
	public boolean isActivatesrs() {
		return activatesrs;
	}
	public void setActivatesrs(boolean activatesrs) {
		this.activatesrs = activatesrs;
	}

	
}
