/**
 * 
 */
package ensg.tsig.chimn.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import ensg.tsig.chimn.entities.Commande;

/**
 * 
 * 
 *
 */
public interface CommandeDao extends CrudRepository<Commande, Long> {
	public List<Commande> findById(Long id);
	public List<Commande> findAll();
	public List<Commande> findByProcessed(boolean value);
}
