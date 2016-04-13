/**
 * 
 */
package ensg.tsig.chimn.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import ensg.tsig.chimn.entities.Preferenceformat;


public interface PreferenceformatDao extends CrudRepository<Preferenceformat, Long>{
	public List<Preferenceformat> findById(Long id);
	public List<Preferenceformat> findByNom(String nom);
	public List<Preferenceformat> findByActivated(boolean activated);
}
