package ensg.tsig.chimn.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
 
@Entity

public class PreferenceService {
	
	@Id
    @GeneratedValue
    private Long id;
	private String nameSv;
	private boolean activated;
	private String style;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getNameSv() {
		return nameSv;
	}
	public void setNameSv(String nameSv) {
		this.nameSv = nameSv;
	}
	
	public boolean isActivated() {
		return activated;
	}
	public void setActivated(boolean activated) {
		this.activated = activated;
	}
	
	public String getStyle() {
		return style;
	}
	public void setStyle(String style) {
		this.style = style;
	}
	
}
