/**
 * 
 */
package ensg.tsig.chimn.entities;

/**
 * @author hanane
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
	
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getDbhote() {
		return dbhote;
	}
	public void setDbhote(String dbhote) {
		this.dbhote = dbhote;
	}
	public String getDbport() {
		return dbport;
	}
	public void setDbport(String dbport) {
		this.dbport = dbport;
	}
	public String getDbuser() {
		return dbuser;
	}
	public void setDbuser(String dbuser) {
		this.dbuser = dbuser;
	}public String getDbpsw() {
		return dbpsw;
	}
	public void setDbpsw(String dbpsw) {
		this.dbpsw = dbpsw;
	}
	public String getDbname() {
		return dbname;
	}
	public void setDbname(String dbname) {
		this.dbname = dbname;
	}
	public String getGshote() {
		return gshote;
	}
	public void setGshote(String gshote) {
		this.gshote = gshote;
	}
	public String getGsport() {
		return gsport;
	}
	public void setGsport(String gsport) {
		this.gsport = gsport;
	}
	public String getGsuser() {
		return gsuser;
	}
	public void setGsuser(String gsuser) {
		this.gsuser = gsuser;
	}
	public String getGspsw() {
		return gspsw;
	}
	public void setGspsw(String gspsw) {
		this.gspsw = gspsw;
	}
	

	@Override
	public String toString() {
		return "Parameters [id=" + id + ", dbhote=" + dbhote + ", dbport=" + dbport + ", dbuser=" + dbuser + ", dbpsw="
				+ dbpsw + ", dbname=" + dbname + ", gshote=" + gshote + ", gsport=" + gsport + ", gsuser=" + gsuser
				+ ", gspsw=" + gspsw + "]";
	}
	
}
