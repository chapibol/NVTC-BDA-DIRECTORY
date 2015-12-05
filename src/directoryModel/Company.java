package directoryModel;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.Text;

import java.util.ArrayList;
import java.util.List;
import javax.jdo.annotations.*;
/**
 * 
 * @author Luis
 *
 *The purpose of this class is to model a big data entity (company) for storage in the directory
 */
@PersistenceCapable
@Inheritance(customStrategy = "complete-table")
public class Company {
	@PrimaryKey
    @Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
	private Key key;//note the variable name has key all lower caps. while the type is Key
	
	@Persistent private String name;	
	@Persistent private String website;
	@Persistent private String telephone;
	@Persistent private Text description;	
	@Persistent	private Address companyAddress;	
	@Persistent private Category primaryCategory;	
	@Persistent private Category secondaryCategory;	
	@Persistent private Category tertiaryCategory;	
	@Persistent private PointOfContact poc;	
	@Persistent private String specialty1;
	@Persistent private String specialty2;
	@Persistent private String specialty3;
		
	
	public Company(){
		this("company name", "www.example.com", new Text("Sample Text"));
	}
	
	public Company(String aName, String website, Text description){
		this.name = aName;
		this.website = website;
		this.telephone = "555-555-5555";
		this.description = description;
		
	}

	/**
	 * 
	 * @return name of the company
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name)throws IllegalArgumentException { 
		if(Utility.isStringDataValid(name)){
			this.name = name;			
		}else{
			throw new IllegalArgumentException("Invalid string input");
		}
		
	}

	/**
	 * @return the website
	 */
	public String getWebsite() {
		return this.website;
	}

	/**
	 * @param website the website to set
	 */
	public void setWebsite(String website) {
		this.website = website;
	}
	
	public String getTelephone(){
		return this.telephone;
	}
	
	public void setTelephone(String aPhone){
		this.telephone = aPhone;
	}

	/**
	 * @return the description
	 */
	public Text getDescription() {
		return this.description;
	}

	/**
	 * @param description the description to set
	 */
	public void setDescription(Text description){
		this.description = description;
	}

	/**
	 * @return the addressList
	 */
	public Address getAddress() {
		return this.companyAddress;
	}

	/**
	 * 
	 * Method to set an entire list of addresses
	 * @param addressList the addressList to set
	 */
	public void setAddress(Address address) {
		this.companyAddress = address;
	}

	/**
	 * Method to get the list of primary categories.
	 * @return the primaryCategories
	 */
	public Category getPrimaryCategory() {
		return this.primaryCategory;
	}

	/**
	 * @param primaryCategories the primaryCategories to set
	 */
	public void setPrimaryCategory(Category primaryCategory) {
		this.primaryCategory = primaryCategory;
	}
	
	public static Category createCategory(String type, String catName, String hierarchy){
		return new Category(type, catName, hierarchy);
	}

	/**
	 * 
	 * @return
	 */
	public Category getSecondaryCategory() {
		return this.secondaryCategory;
	}

	/**
	 * @param secondaryCategories the secondaryCategories to set
	 */
	public void setSecondaryCategory(Category secondaryCategory) {
		this.secondaryCategory = secondaryCategory;
	}
	
	

	/**
	 * @return the tertiaryCategories
	 */
	public Category getTertiaryCategory() {
		return this.tertiaryCategory;
	}

	/**
	 * @param tertiaryCategories the tertiaryCategories to set
	 */
	public void setTertiaryCategories(Category tertiaryCategory) {
		this.tertiaryCategory = tertiaryCategory;
	}

	/**
	 * @return the pointOfContactList
	 */
	public PointOfContact getPointOfContact() {
		return this.poc;
	}

	/**
	 * @param pointOfContactList the pointOfContactList to set
	 */
	public void setPointOfContact(PointOfContact poc) {
		this.poc = poc;
	}
	

	/**
	 * @return the specialty1
	 */
	public String getSpecialty1() {
		return specialty1;
	}

	/**
	 * @param specialty1 the specialty1 to set
	 */
	public void setSpecialty1(String specialty1) {
		this.specialty1 = specialty1;
	}

	/**
	 * @return the specialty2
	 */
	public String getSpecialty2() {
		return specialty2;
	}

	/**
	 * @param specialty2 the specialty2 to set
	 */
	public void setSpecialty2(String specialty2) {
		this.specialty2 = specialty2;
	}

	/**
	 * @return the specialty3
	 */
	public String getSpecialty3() {
		return specialty3;
	}

	/**
	 * @param specialty3 the specialty3 to set
	 */
	public void setSpecialty3(String specialty3) {
		this.specialty3 = specialty3;
	}

	/**
	 * @return the key
	 */
	public Key getKey() {
		return this.key;
	}

	/**
	 * Returns a string representation of company.
	 */
	public String toString(){
		String output = "Point of Contact Information\n<br/>"
				+ "First Name: " + poc.getFirstName() + " - Last Name: " + poc.getLastName() + "\n<br/>"
				+ "Email: " + poc.getEmail() + "\n<br/>"
				+ "Company Information\n<br/>"
				+ "Company Name: " + this.getName() + " - Company Website: " + this.getWebsite() + "\n<br/>"
				+ "Company Telephone: " + this.getTelephone() + "\n<br/>"
				+ "Company Description: " + this.getDescription() + "\n<br/>"
				+ "Address 1: \n<br/>"
				+ this.getAddress().getStreetName() + "\n<br/>"
				+ this.getAddress().getCity() + ", " + this.getAddress().getState() + " " + this.getAddress().getZipcode() + "\n<br/>"
			    + "Category Info: \n<br/>"
			    + "Primary Category: " + this.getPrimaryCategory().getCategoryName() + "\n<br/>"
			    + "Primary Category Hierarchy: " + this.getPrimaryCategory().getCategoryHierarchy() + "\n<br/>"
			    + "Secondary Category: " + this.getSecondaryCategory().getCategoryName() + "\n<br/>"
			    + "Secondary Category Hierarchy: " + this.getSecondaryCategory().getCategoryHierarchy() + "\n<br/>"
			    + "Tertiary Category: " + this.getTertiaryCategory().getCategoryName() + "\n<br/>"
			    + "Tertiary Category Hierarchy: " + this.getTertiaryCategory().getCategoryHierarchy() + "\n<br/>"
			    + "Specialty Info:\n<br/>"
			    + "Specialty 1: " + this.getSpecialty1() + "\n<br/>"
			    + "Specialty 2: " + this.getSpecialty2() + "\n<br/>"
			    + "Specialty 3: " + this.getSpecialty3() + "\n<br/>";		;
		
		return output;
	}
	
}