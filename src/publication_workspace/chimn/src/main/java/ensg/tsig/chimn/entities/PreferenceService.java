package ensg.tsig.chimn.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
 
@Entity

public class PreferenceService {
	
	@Id
    @GeneratedValue
    private Long id;
	private String namesv;
	private boolean activated;
	private String style;
	
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
	 * @return namesv
	 */
	public String getNamesv() {
		return namesv;
	}
	
	/**
	 * 
	 * @param namesv
	 */
	public void setNamesv(String namesv) {
		this.namesv = namesv;
	}
	
	/**
	 * 
	 * @return activated
	 */
	public boolean isActivated() {
		return activated;
	}
	
	/**
	 * 
	 * @param activated
	 */
	public void setActivated(boolean activated) {
		this.activated = activated;
	}
	
	/**
	 * 
	 * @return style
	 */
	public String getStyle() {
		return style;
	}
	
	/**
	 * 
	 * @param style
	 */
	public void setStyle(String style) {
		this.style = style;
	}
	
}
