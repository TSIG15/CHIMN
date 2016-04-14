
package ensg.tsig.chimn.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import ensg.tsig.chimn.entities.User;

/**
 *
 */
public interface UserDAO extends CrudRepository<User, Long> {
	 public List<User> findAll(); //should return just one instance of user !
}
