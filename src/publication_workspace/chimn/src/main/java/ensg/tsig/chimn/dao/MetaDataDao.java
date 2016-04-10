package ensg.tsig.chimn.dao;

import java.util.List;

import ensg.tsig.chimn.entities.MetaData;
import org.springframework.data.repository.CrudRepository;
 

public interface MetaDataDao extends CrudRepository<MetaData, Long>{
	 public List<MetaData> findByTitle(String surname);
	 public List<MetaData> findByIdisogeo(String id);
	 public List<MetaData> findByAsked(boolean asked);
	 public List<MetaData> findByAskedAndChangedAndLicense(boolean asked, boolean _changed, String license);
}
