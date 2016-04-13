/**
 * 
 */
package ensg.tsig.chimn.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import ensg.tsig.chimn.entities.Parameters;;

/**
 * @author hanane
 *
 */
public interface ParametersDao extends CrudRepository<Parameters, Long> {
	 public List<Parameters> findAll(); //should return just one instance of parameters !
}
