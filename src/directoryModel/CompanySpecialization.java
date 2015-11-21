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
public class CompanySpecialization {
	
	@PrimaryKey
    @Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
	private Key key;
	
	@Persistent 
	private String type;
	
	@Persistent 
	private String specializationName;
	
	public CompanySpecialization(){
		this("specialization type", "Specialization description");
	}
	public CompanySpecialization(String type, String specializationName) {
		this.type = type;
		this.specializationName = specializationName;
	}
	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}
	/**
	 * @param type the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}
	/**
	 * @return the description
	 */
	public String getSpecializationName() {
		return specializationName;
	}
	/**
	 * @param description the description to set
	 */
	public void setSpecializationName(String specializationName) {
		this.specializationName = specializationName;
	}
	/**
	 * @return the key
	 */
	public Key getKey() {
		return key;
	}
	
	public String toString(){
		return specializationName;
	}
	

}