package ensg.tsig.chimn.dao;

import java.util.List;

import ensg.tsig.chimn.entities.Criteres;

import org.springframework.data.repository.CrudRepository;

public interface CriteresDao extends CrudRepository<Criteres, Long>{
	
	public List<Criteres> findById(Long id);
	 public List<Criteres> findByMotcle(String motcle);
	 public List<Criteres> findByLicense(String license);
	 public List<Criteres> findByProprietaire(String proprietaire);
	 public List<Criteres> findByPeriodicite(int periodicite);
	

}
