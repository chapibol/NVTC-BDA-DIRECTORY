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
public class Category {
	@PrimaryKey
    @Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
	private Key key;
	
	@Persistent 
	private String type;
	
	@Persistent 
	private String categoryName;
	
	@Persistent
	private String categoryHierarchy;
		
	public Category(){
		this("type", "categoryName","level1-level2-level3-level4");
	}
	public Category(String type, String catName, String hierarchy) {
		this.type = type;
		this.categoryName = catName;
		this.categoryHierarchy = hierarchy;
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
	public String getCategoryName() {
		return categoryName;
	}
	/**
	 * @param description the description to set
	 */
	public void setCategoryName(String catName) {
		this.categoryName = catName;
	}
	/**
	 * Method that returns the category's hierarchy separated by a '-' for exmample hardware-compute
	 * @return the categoryHierarchy
	 */
	public String getCategoryHierarchy() {
		return categoryHierarchy;
	}
	/**
	 * @param categoryHierarchy the categoryHierarchy to set
	 */
	public void setCategoryHierarchy(String categoryHierarchy) {
		this.categoryHierarchy = categoryHierarchy;
	}
	/**
	 * @return the key
	 */
	public Key getKey() {
		return key;
	}
	
	public String toString(){
		return "Category Default String";
	}

}