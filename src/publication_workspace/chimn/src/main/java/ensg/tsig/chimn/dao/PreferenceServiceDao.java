package ensg.tsig.chimn.dao;

import java.util.List;

import ensg.tsig.chimn.entities.PreferenceService;

import org.springframework.data.repository.CrudRepository;

public interface PreferenceServiceDao extends CrudRepository<PreferenceService, Long> {
	
	public List<PreferenceService> findById(int id);
	public List<PreferenceService> findByNamesv(String namesv);
	public List<PreferenceService> findByActivated(boolean activated);
	public List<PreferenceService> findByStyle (String style);
}
