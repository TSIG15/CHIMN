package ensg.tsig.chimn.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * 
 */
@Entity

public class User {

	@Id
    @GeneratedValue
    private Long id;
	private String word;
	
	public String getWord() {
		return word;
	}
	public void setWord(String word) {
		this.word = word;
	}
   
}