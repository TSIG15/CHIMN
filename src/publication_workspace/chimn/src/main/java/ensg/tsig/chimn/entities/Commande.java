/**
 * 
 */
package ensg.tsig.chimn.entities;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Entity;
/**
 * 
 * 
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
	private String lien;
	private boolean processed;
	
	/**
	 * 
	 * @return id
	 */
	public Long getId() {
		return id;
	}
	
	/**
	 * 
	 */
	public Commande()
	{
		
	}
	
	/**
	 * 
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
	
	/**
	 * 
	 * @param id
	 */
	public void setId(Long id) {
		this.id = id;
	}
	/**
	 * 
	 * @return nameuser
	 */
	public String getNameuser() {
		return nameuser;
	}
	/**
	 * 
	 * @param nameuser
	 */
	public void setNameuser(String nameuser) {
		this.nameuser = nameuser;
	}
	
	/**
	 * 
	 * @return email
	 */
	public String getEmail() {
		return email;
	}
	
	/**
	 * 
	 * @param email
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	
	/**
	 * 
	 * @return srs
	 */
	public String getSrs() {
		return srs;
	}
	
	/**
	 * 
	 * @param srs
	 */
	public void setSrs(String srs) {
		this.srs = srs;
	}
	
	/**
	 * 
	 * @return format
	 */
	public String getFormat() {
		return format;
	}
	
	/**
	 * 
	 * @param format
	 */
	public void setFormat(String format) {
		this.format = format;
	}
	
	/**
	 * 
	 * @return point1lat
	 */
	public String getPoint1lat() {
		return point1lat;
	}
	
	/**
	 * 
	 * @param point1lat
	 */
	public void setPoint1lat(String point1lat) {
		this.point1lat = point1lat;
	}
	
	/**
	 * 
	 * @return point1lng
	 */
	public String getPoint1lng() {
		return point1lng;
	}
	
	/**
	 * 
	 * @param point1lng
	 */
	public void setPoint1lng(String point1lng) {
		this.point1lng = point1lng;
	}
	
	/**
	 * 
	 * @return point2lat
	 */
	public String getPoint2lat() {
		return point2lat;
	}
	
	/**
	 * 
	 * @param point2lat
	 */
	public void setPoint2lat(String point2lat) {
		this.point2lat = point2lat;
	}
	
	/**
	 * 
	 * @return point2lng
	 */
	public String getPoint2lng() {
		return point2lng;
	}
	
	/**
	 * 
	 * @param point2lng
	 */
	public void setPoint2lng(String point2lng) {
		this.point2lng = point2lng;
	}
	
	/**
	 * 
	 * @return datecmd
	 */
	public String getDatecmd() {
		return datecmd;
	}
	
	/**
	 * 
	 * @param datecmd
	 */
	public void setDatecmd(String datecmd) {
		this.datecmd = datecmd;
	}
	
	/**
	 * 
	 * @return heurecmd
	 */
	public String getHeurecmd() {
		return heurecmd;
	}
	
	/**
	 * 
	 * @param heurecmd
	 */
	public void setHeurecmd(String heurecmd) {
		this.heurecmd = heurecmd;
	}
	
	/**
	 * 
	 * @return titledata
	 */
	public String getTitledata() {
		return titledata;
	}
	
	/**
	 * 
	 * @param titledata
	 */
	public void setTitledata(String titledata) {
		this.titledata = titledata;
	}
	
	/**
	 * 
	 * @return lien
	 */
	public String getLien() {
		return lien;
	}
	
	/**
	 * 
	 * @param lien
	 */
	public void setLien(String lien) {
		this.lien = lien;
	}
	
	/**
	 * 
	 * @return processed
	 */
	public boolean isProcessed() {
		return processed;
	}
	
	/**
	 * 
	 * @param processed
	 */
	public void setProcessed(boolean processed) {
		this.processed = processed;
	}
}
