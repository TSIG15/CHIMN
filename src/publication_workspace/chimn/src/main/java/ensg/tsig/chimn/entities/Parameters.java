/**
 * 
 */
package ensg.tsig.chimn.entities;

/**
 *
 */

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
 
@Entity
public class Parameters {

	@Id
    @GeneratedValue
    private Long id;
	private String dbhote;
	private String dbport;
	private String dbuser;
	private String dbpsw;
	private String dbname;
	private String gshote;
	private String gsport;
	private String gsuser;
	private String gspsw;
	private String isid;
	private String issecret;
	private String tlurl;
	
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
	 * @return dbhote
	 */
	public String getDbhote() {
		return dbhote;
	}
	
	/**
	 * 
	 * @param dbhote
	 */
	public void setDbhote(String dbhote) {
		this.dbhote = dbhote;
	}
	
	/**
	 * 
	 * @return dbport
	 */
	public String getDbport() {
		return dbport;
	}
	
	/**
	 * 
	 * @param dbport
	 */
	public void setDbport(String dbport) {
		this.dbport = dbport;
	}
	
	/**
	 * 
	 * @return dbuser
	 */
	public String getDbuser() {
		return dbuser;
	}
	
	/**
	 * 
	 * @param dbuser
	 */
	public void setDbuser(String dbuser) {
		this.dbuser = dbuser;
	}
	
	/**
	 * 
	 * @return dbpsw
	 */
	public String getDbpsw() {
		return dbpsw;
	}
	
	/**
	 * 
	 * @param dbpsw
	 */
	public void setDbpsw(String dbpsw) {
		this.dbpsw = dbpsw;
	}
	
	/**
	 * 
	 * @return dbname
	 */
	public String getDbname() {
		return dbname;
	}
	
	/**
	 * 
	 * @param dbname
	 */
	public void setDbname(String dbname) {
		this.dbname = dbname;
	}
	
	/**
	 * 
	 * @return gshote
	 */
	public String getGshote() {
		return gshote;
	}
	
	/**
	 * 
	 * @param gshote
	 */
	public void setGshote(String gshote) {
		this.gshote = gshote;
	}
	
	/**
	 * 
	 * @return gsport
	 */
	public String getGsport() {
		return gsport;
	}
	
	/**
	 * 
	 * @param gsport
	 */
	public void setGsport(String gsport) {
		this.gsport = gsport;
	}
	
	/**
	 * 
	 * @return gsuser
	 */
	public String getGsuser() {
		return gsuser;
	}
	
	/**
	 * 
	 * @param gsuser
	 */
	public void setGsuser(String gsuser) {
		this.gsuser = gsuser;
	}
	
	/**
	 * 
	 * @return gspsw
	 */
	public String getGspsw() {
		return gspsw;
	}
	
	/**
	 * 
	 * @param gspsw
	 */
	public void setGspsw(String gspsw) {
		this.gspsw = gspsw;
	}
	
	/**
	 * 
	 * @return isid
	 */
	public String getIsid() {
		return isid;
	}
	
	/**
	 * 
	 * @param isid
	 */
	public void setIsid(String isid) {
		this.isid = isid;
	}
	
	/**
	 * 
	 * @return issecret
	 */
	public String getIssecret() {
		return issecret;
	}
	
	/**
	 * 
	 * @param issecret
	 */
	public void setIssecret(String issecret) {
		this.issecret = issecret;
	}
	
	/**
	 * 
	 * @return tlurl
	 */
	public String getTlurl() {
		return tlurl;
	}
	
	/**
	 * 
	 * @param tlurl
	 */
	public void setTlurl(String tlurl) {
		this.tlurl = tlurl;
	}

	/**
	 * 
	 */
	@Override
	public String toString() {
		return "Parameters [id=" + id + ", dbhote=" + dbhote + ", dbport=" + dbport + ", dbuser=" + dbuser + ", dbpsw="
				+ dbpsw + ", dbname=" + dbname + ", gshote=" + gshote + ", gsport=" + gsport + ", gsuser=" + gsuser
				+ ", gspsw=" + gspsw + ", isid=" + isid + ", issecret=" + issecret + ",tlurl=" + tlurl +"]";
	}
	
}
