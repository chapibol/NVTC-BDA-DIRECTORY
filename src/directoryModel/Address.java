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
public class Address {
	@PrimaryKey
    @Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
	private Key key;
	
	@Persistent private String streetName;
	
	@Persistent private String streetName2;

	@Persistent private String city;
	
	@Persistent private String state;
	
	@Persistent private String stateCode;
	
	@Persistent private String zipcode;
	
	public Address(){
		this("114 Street Name","Suite 20", "city", "state", "20164");
		this.stateCode = "VA";
	}
	
	public Address(String streetName,String streeName2, String city, String state, String zipcode){
		this.streetName = streetName;
		this.streetName2 = streeName2;
		this.city = city;
		this.state = state;
		this.stateCode = state;
		this.zipcode = zipcode;
	}

	/**
	 * @return the streetName
	 */
	public String getStreetName() {
		return this.streetName;
	}

	/**
	 * @param streetName the streetName to set
	 */
	public void setStreetName(String streetName) {
		this.streetName = streetName;
	}
	
	public String getStreetName2() {
		return streetName2;
	}

	public void setStreetName2(String streetName2) {
		this.streetName2 = streetName2;
	}

	/**
	 * @return the city
	 */
	public String getCity() {
		return city;
	}

	/**
	 * @param city the city to set
	 */
	public void setCity(String city) {
		this.city = city;
	}

	/**
	 * @return the state
	 */
	public String getState() {
		return state;
	}

	/**
	 * @param state the state to set
	 */
	public void setState(String state) {
		this.state = state;
	}

	/**
	 * @return the stateCode
	 */
	public String getStateCode() {
		return stateCode;
	}

	/**
	 * @param stateCode the stateCode to set
	 */
	public void setStateCode(String stateCode) {
		this.stateCode = stateCode;
	}

	/**
	 * @return the zipcode
	 */
	public String getZipcode() {
		return zipcode;
	}

	/**
	 * @param zipcode the zipcode to set
	 */
	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}

	/**
	 * @return the key
	 */
	public Key getKey() {
		return key;
	}

		
	public String toString(){
		return "Address to String";
	}
}