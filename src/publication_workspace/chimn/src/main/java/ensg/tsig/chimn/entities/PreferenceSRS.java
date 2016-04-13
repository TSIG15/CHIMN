package ensg.tsig.chimn.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
 
@Entity

public class PreferenceSRS {
	
	@Id
    @GeneratedValue
    private Long id;
	private String nameSRS;
	private String epsg;
	private boolean activated;
	
	
	public String getNameSRS() {
		return nameSRS;
	}
	public void setNameSRS(String nameSRS) {
		this.nameSRS = nameSRS;
	}
	public String getEpsg() {
		return epsg;
	}
	public void setEpsg(String epsg) {
		this.epsg = epsg;
	}
	public boolean isActivated() {
		return activated;
	}
	public void setActivated(boolean activated) {
		this.activated = activated;
	}

	
}
