package ensg.tsig.chimn.dao;

import java.util.List;

import ensg.tsig.chimn.entities.Criteria;

import org.springframework.data.repository.CrudRepository;

public interface CriteriaDao extends CrudRepository<Criteria, Long>{
	
	public List<Criteria> findById(Long id);
	public List<Criteria> findByKeyword(String keyword);
	public List<Criteria> findByLicense(String license);
	public List<Criteria> findByOwner(String owner);
	public List<Criteria> findByPeriodicity(int periodicity);

}
