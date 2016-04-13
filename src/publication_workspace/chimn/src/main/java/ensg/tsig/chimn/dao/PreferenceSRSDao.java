package ensg.tsig.chimn.dao;

import java.util.List;

import ensg.tsig.chimn.entities.PreferenceSRS;
import org.springframework.data.repository.CrudRepository;


public interface PreferenceSRSDao extends CrudRepository<PreferenceSRS, Long> {
	
	public List<PreferenceSRS> findById(Long id);
	public List<PreferenceSRS> findByNameSRS(String nameSRS);
	public List<PreferenceSRS> findByEpsg(String epsg);
	public List<PreferenceSRS> findByActivated(boolean activated);

}
