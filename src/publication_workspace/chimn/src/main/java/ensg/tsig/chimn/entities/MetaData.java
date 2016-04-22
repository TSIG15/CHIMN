package ensg.tsig.chimn.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.springframework.transaction.annotation.Transactional;
/**
 * 
 *
 */
@Entity
public class MetaData {

	    @Id
	    @GeneratedValue
	    private Long id;
	    private String title;
	    private String license;
	    private String created;
	    private String modified;
	    private boolean changed;
	    private boolean deleted;
	    private boolean asked;
	    private boolean activated;
	    private String idisogeo;
	    private String geometrytype;
	    private String srs;
	    
	    /**
	     * 
	     * @param title
	     * @param license
	     * @param _created
	     * @param _modified
	     * @param _deleted
	     * @param idisogeo
	     * @param srs
	     * @param geom
	     */
	    public MetaData(String title, String license, String _created, String _modified, 
				boolean _deleted, String idisogeo,String srs, String geom) {
			super();
			this.title = title;
			this.license = license;
			this.created = _created;
			this.modified = _modified;
			this.changed = true;
			this.asked= true;
			this.deleted = _deleted;
			this.idisogeo = idisogeo;
			this.srs=srs;
			this.geometrytype=geom;
			this.activated=true;
		}
	    
	    /**
	     * 
	     */
		public MetaData() {
		}
	    
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
	     * @return title
	     */
	    public String getName() {
	        return title;
	    }
	    
	    /**
	     * 
	     * @param name
	     */
	    public void setName(String name) {
	        this.title = name;
	    }
	    
	    /**
	     * 
	     * @return license
	     */
	    public String getlicense() {
	        return license;
	    }
	    
	    /**
	     * 
	     * @param surname
	     */
	    public void setlicense(String surname) {
	        this.license = surname;
	    }
	    
	    /**
	     * 
	     */
	    @Override
	    public String toString() {
	        return "metadata [id=" + id + ", name=" + title + ", license=" + license + "]";
	    }
	    
	    /**
	     * 
	     * @return idisogeo
	     */
		public String getIdisogeo() {
			return idisogeo;
		}
		
		/**
		 * 
		 * @param id_isogeo
		 */
		public void setIdisogeo(String id_isogeo) {
			this.idisogeo = id_isogeo;
		}
		
		/**
		 * 
		 * @return changed
		 */
		public boolean isChanged() {
			return changed;
		}
		
		/**
		 * 
		 * @param changed
		 */
		public void setChanged(boolean changed) {
			this.changed = changed;
		}
		
		/**
		 * 
		 * @return modified
		 */
		public String get_modified() {
			return modified;
		}
		
		/**
		 * 
		 * @param _modified
		 */
		public void set_modified(String _modified) {
			this.modified = _modified;
		}
		
		/**
		 * 
		 * @return created
		 */
		public String get_created() {
			return created;
		}
		
		/**
		 * 
		 * @param _created
		 */
		public void set_created(String _created) {
			this.created = _created;
		}

		/**
		 * 
		 * @return deleted
		 */
		public boolean is_deleted() {
			return deleted;
		}
		
		/**
		 * 
		 * @param _deleted
		 */
		public void set_deleted(boolean _deleted) {
			this.deleted = _deleted;
		}
		
		/**
		 * 
		 * @return asked
		 */
		public boolean isAsked() {
			return asked;
		}
		
		/**
		 * 
		 * @param asked
		 */
		public void setAsked(boolean asked) {
			this.asked = asked;
		}
		
		/**
		 * 
		 * @return geometrytype
		 */
		public String getGeometrytype() {
			return geometrytype;
		}
		
		/**
		 * 
		 * @param geometrytype
		 */
		public void setGeometrytype(String geometrytype) {
			this.geometrytype = geometrytype;
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
	}



