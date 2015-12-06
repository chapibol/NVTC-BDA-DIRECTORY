package directoryModel;


import java.util.ArrayList;
import java.util.List;

import javax.jdo.JDOUserException;
import javax.jdo.PersistenceManager;
import javax.jdo.Query;

/**
 * 
 * @author Luis
 *The purpose of this class is to provide a toolbox of methods to be uesd by servlets and Data definition classes
 */
public final class Utility {	
	
	/**
	 * Method to validate a string. checks that the string is not null
	 * nor it contains empty spaces. 
	 * @param strValue
	 * @return true if strValue is valid
	 */
	public static boolean isStringDataValid(String strValue){
		boolean isValid = true;
		boolean isStrEmpty = true;
		//check for an null variable being passed in
		if(strValue == null){
			return false;
		}
		//check to see strValue is not an empty string
		if(strValue.length() > 0 ){
			//check to make sure strValue is not full of empty spaces.
			for(int i = 0; i < strValue.length(); i++){
				if(!(strValue.charAt(i) == ' ')){
					isStrEmpty = false;
					break;
				}
			}
			if(isStrEmpty){
				isValid = false;
			}
		}else{
			isValid = false;
		}
		return isValid;
	}
	
	/**
	 * Method to extract category's path
	 * @param cats
	 * @return
	 */
	public static String buildCategoryHierarchy(String [] cats){
		String output = "";
		for(int i = 0; i < cats.length; i++){
			if(cats[i] != null){
				output += cats[i] + "-";
			}
		}
		//delete the last hyphen in the string before returning
		output = output.substring(0,output.length() -1);
	 return output;
	}
	/**
	 * Gets the category name from an array of categories. The last non null element in the array is the category name
	 * @param cats array of categories
	 * @return the category or an empty space ""
	 */
	public static String getCategoryName(String [] cats){
		for(int i = cats.length-1; i >= 0; i--){
			if(cats[i] != null){
				return cats[i];
			}
		}
		return "";		
	}
	
	/**
	 * Method to save a company object to the datastore.
	 * @param c
	 */
	public static void saveCompanyToDatastore(Company c){
		PersistenceManager pm = PMF.get().getPersistenceManager();
		try {
			//use a transaction to ensure that the question is saved to the datastore
			pm.currentTransaction().begin();
			pm.makePersistent(c);
		}
		finally {
			pm.currentTransaction().commit();
			pm.close();
		}
	}
	
	
	
	/**
	 * Method to retrieve a single company from the data store.
	 * @param companyId
	 * @return
	 */
	public static Company getCompanyById(long companyId){
		//will have to change this to just search datastore for single question not all.
		PersistenceManager pm = PMF.get().getPersistenceManager();
		Company c = pm.getObjectById(Company.class, companyId);
		return c;
	}
	/**
	 * Gets all of the company entities stored in the datastore
	 * @return
	 */
	public static List<Company> getAllCompanies() {
		PersistenceManager pm = PMF.get().getPersistenceManager();
		List<Company> results = null;
		try {			
			Query q = pm.newQuery(Company.class);
			q.setOrdering("name asc");//set ordering
			q.setDatastoreReadTimeoutMillis(4000);
			results = (List<Company>)q.execute();
		} catch (Exception e) {
			//nothing
		}
		finally{
			pm.close();
		}
		return results;
	}
	
	/**
	 * Method to create a snippet from a parameter full Text
	 * @param fullText
	 * @return
	 */
	public static String toSnippet(String fullText){
		String snippet = fullText;
		if(fullText.length() > 161){
			snippet = fullText.substring(0,160);
		}
		
		return snippet + "...";
	}	
}