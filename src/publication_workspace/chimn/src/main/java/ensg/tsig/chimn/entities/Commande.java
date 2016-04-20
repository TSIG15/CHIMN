/**
 * 
 */
package ensg.tsig.chimn.entities;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Entity;

/**
 * @author hanane
 *
 */
@Entity
public class Commande {
	@Id
    @GeneratedValue
    private Long id;
	private String nameuser;
	private String email;
	private String srs;
	private String format;
	private String point1lat;
	private String point1lng;
	private String point2lat;
	private String point2lng;
	private String datecmd;
	private String heurecmd;
	private String titledata;

	
	public Long getId() {
		return id;
	}
	/**
	 * @param nameuser
	 * @param email
	 * @param srs
	 * @param format
	 * @param point1lat
	 * @param point1lng
	 * @param point2lat
	 * @param point2lng
	 * @param datecmd
	 * @param heurecmd
	 * @param titledata
	 */
	public Commande(String nameuser, String email, String srs, String format, String point1lat, String point1lng,
			String point2lat, String point2lng, String datecmd, String heurecmd, String titledata) {
		super();
		this.nameuser = nameuser;
		this.email = email;
		this.srs = srs;
		this.format = format;
		this.point1lat = point1lat;
		this.point1lng = point1lng;
		this.point2lat = point2lat;
		this.point2lng = point2lng;
		this.datecmd = datecmd;
		this.heurecmd = heurecmd;
		this.titledata = titledata;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNameuser() {
		return nameuser;
	}
	public void setNameuser(String nameuser) {
		this.nameuser = nameuser;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getSrs() {
		return srs;
	}
	public void setSrs(String srs) {
		this.srs = srs;
	}
	public String getFormat() {
		return format;
	}
	public void setFormat(String format) {
		this.format = format;
	}
	public String getPoint1lat() {
		return point1lat;
	}
	public void setPoint1lat(String point1lat) {
		this.point1lat = point1lat;
	}
	public String getPoint1lng() {
		return point1lng;
	}
	public void setPoint1lng(String point1lng) {
		this.point1lng = point1lng;
	}
	public String getPoint2lat() {
		return point2lat;
	}
	public void setPoint2lat(String point2lat) {
		this.point2lat = point2lat;
	}
	public String getPoint2lng() {
		return point2lng;
	}
	public void setPoint2lng(String point2lng) {
		this.point2lng = point2lng;
	}
	public String getDatecmd() {
		return datecmd;
	}
	public void setDatecmd(String datecmd) {
		this.datecmd = datecmd;
	}
	public String getHeurecmd() {
		return heurecmd;
	}
	public void setHeurecmd(String heurecmd) {
		this.heurecmd = heurecmd;
	}
	public String getTitledata() {
		return titledata;
	}
	public void setTitledata(String titledata) {
		this.titledata = titledata;
	}
	

}
