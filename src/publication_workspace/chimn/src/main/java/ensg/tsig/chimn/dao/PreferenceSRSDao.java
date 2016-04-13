package ensg.tsig.chimn.dao;

import java.util.List;

import ensg.tsig.chimn.entities.PreferenceSRS;
import org.springframework.data.repository.CrudRepository;


public interface PreferenceSRSDao extends CrudRepository<PreferenceSRS, Long> {
	
	public List<PreferenceSRS> findByNom(int nom);
	 public List<PreferenceSRS> findByCode(String codeEpsg);
	 public List<PreferenceSRS> findByActivated(boolean activated);

}
