package ensg.tsig.chimn.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
 
@Entity
public class MetaData {

	    @Id
	    @GeneratedValue
	    private Long id;
	    private String title;
	    private String license;
	    private String _created;
	    private String _modified;
	    private boolean changed;
	    private boolean _deleted;
	    private boolean asked;
	    private String idisogeo;
	    
	    public MetaData(String title, String license, String _created, String _modified, 
				boolean _deleted, String idisogeo) {
			super();
			this.title = title;
			this.license = license;
			this._created = _created;
			this._modified = _modified;
			this.changed = true;
			this.asked=true;
			this._deleted = _deleted;
			this.idisogeo = idisogeo;
		}

		public MetaData() {
			
		}
	 
	  
	     
	    public Long getId() {
	        return id;
	    }
	    public void setId(Long id) {
	        this.id = id;
	    }
	    public String getName() {
	        return title;
	    }
	    public void setName(String name) {
	        this.title = name;
	    }
	    public String getlicense() {
	        return license;
	    }
	    public void setlicense(String surname) {
	        this.license = surname;
	    }
	    @Override
	    public String toString() {
	        return "metadata [id=" + id + ", name=" + title + ", license=" + license + "]";
	    }

		public String getIdisogeo() {
			return idisogeo;
		}

		public void setIdisogeo(String id_isogeo) {
			this.idisogeo = id_isogeo;
		}

		public boolean isChanged() {
			return changed;
		}

		public void setChanged(boolean changed) {
			this.changed = changed;
		}

		public String get_modified() {
			return _modified;
		}

		public void set_modified(String _modified) {
			this._modified = _modified;
		}

		public String get_created() {
			return _created;
		}

		public void set_created(String _created) {
			this._created = _created;
		}

		public boolean is_deleted() {
			return _deleted;
		}

		public void set_deleted(boolean _deleted) {
			this._deleted = _deleted;
		}

		public boolean isAsked() {
			return asked;
		}

		public void setAsked(boolean asked) {
			this.asked = asked;
		}
	}



