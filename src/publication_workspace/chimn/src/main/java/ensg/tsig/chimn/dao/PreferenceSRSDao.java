package ensg.tsig.chimn.dao;

import java.util.List;

import ensg.tsig.chimn.entities.PreferenceSRS;
import org.springframework.data.repository.CrudRepository;


public interface PreferenceSRSDao extends CrudRepository<PreferenceSRS, String> {
	

	public List<PreferenceSRS> findByNamesrs(String namesrs);
	public List<PreferenceSRS> findByEpsg(String epsg);
	public List<PreferenceSRS> findByActivatesrs(boolean activatesrs);

}
