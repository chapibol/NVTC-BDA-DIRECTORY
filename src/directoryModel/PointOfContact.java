/**
 * 
 */
package directoryModel;

import javax.jdo.annotations.*;
import com.google.appengine.api.datastore.Key;

/**
 * @author Luis
 *
 */

@PersistenceCapable
@Inheritance(customStrategy = "complete-table")
public class PointOfContact {

	@PrimaryKey
    @Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
	private Key key;
	
	@Persistent private String email;
	
	@Persistent private String firstName;
	
	@Persistent private String lastName;
	
	public PointOfContact(){
		this("aFirstName", "aLastName", "email@example.com");
	}
	
	public PointOfContact(String firstName, String lastName, String email){
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return this.email;
	}

	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return the firstName
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * @param firstName the firstName to set
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * @return the lastName
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * @param lastName the lastName to set
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 * @return the key
	 */
	public Key getKey() {
		return key;
	}
	
	public String toString(){
		return "POC toString default";
	}
	
	public String getFullName(){
		return this.getFirstName() + " " + this.getLastName();
	}
	
	
	
}