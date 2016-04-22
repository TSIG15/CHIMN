package ensg.tsig.chimn.dao;

import java.util.List;

import ensg.tsig.chimn.entities.Criteria;

import org.springframework.data.repository.CrudRepository;
/**
 * 
 *
 */
public interface CriteriaDao extends CrudRepository<Criteria, Long>{
	
	public List<Criteria> findById(Long id);
	public List<Criteria> findAll();
	

}
