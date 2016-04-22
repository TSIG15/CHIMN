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
	
	/**
	 * 
	 * @return
	 */
	public String getNameSRS() {
		return namesrs;
	}
	
	/**
	 * 
	 * @param namesrs
	 */
	public void setNameSRS(String namesrs) {
		this.namesrs = namesrs;
	}
	
	/**
	 * 
	 * @return epsg
	 */
	public String getEpsg() {
		return epsg;
	}
	
	/**
	 * 
	 * @param epsg
	 */
	public void setEpsg(String epsg) {
		this.epsg = epsg;
	}
	
	/**
	 * 
	 * @return activatesrs
	 */
	public boolean isActivatesrs() {
		return activatesrs;
	}
	
	/**
	 * 
	 * @param activatesrs
	 */
	public void setActivatesrs(boolean activatesrs) {
		this.activatesrs = activatesrs;
	}

	
}
