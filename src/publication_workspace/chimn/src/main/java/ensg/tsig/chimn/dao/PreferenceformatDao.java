/**
 * 
 */
package ensg.tsig.chimn.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import ensg.tsig.chimn.entities.MetaData;
import ensg.tsig.chimn.entities.Preferenceformat;

/**
 * @author hanane
 *
 */
public interface PreferenceformatDao extends CrudRepository<Preferenceformat, Long>{
	//public List<Preferenceformat> findAll();

}
