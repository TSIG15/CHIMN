package ensg.tsig.chimn.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import ensg.tsig.chimn.entities.PreferenceFormat;


public interface PreferenceFormatDao extends CrudRepository<PreferenceFormat, Long>{
	public List<PreferenceFormat> findById(Long id);
	public List<PreferenceFormat> findByNameFm(String nameFm);
	public List<PreferenceFormat> findByActivated(boolean activated);
}
