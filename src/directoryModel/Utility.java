package directoryModel;


import java.util.ArrayList;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import com.google.appengine.api.search.Document;
import com.google.appengine.api.search.Field;
import com.google.appengine.api.search.GetRequest;
import com.google.appengine.api.search.GetResponse;
import com.google.appengine.api.search.Index;
import com.google.appengine.api.search.IndexSpec;
import com.google.appengine.api.search.SearchServiceFactory;
import com.google.appengine.api.search.PutException;
import com.google.appengine.api.search.StatusCode;

/**
 * 
 * @author Luis
 *The purpose of this class is to provide a toolbox of methods to be uesd by servlets and Data definition classes alike
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
			pm.currentTransaction().commit();
		}
		finally {			
			pm.close();
		}
	}
	
	/**
	 * Method to create a search API document for an single company
	 * @param c
	 * @return Document
	 */
	public static Document createCompanyDocument(Company c){
		long myDocId = c.getKey().getId();
		Document doc = Document.newBuilder().setId(""+myDocId)
			.addField(Field.newBuilder().setName("companyName")
					.setAtom(c.getName()))
			.addField(Field.newBuilder().setName("telephone")
					.setAtom(c.getTelephone()))
			.addField(Field.newBuilder().setName("companyDescription")
					.setText(c.getDescription()))
			.addField(Field.newBuilder().setName("primaryCategory")
					.setAtom(c.getPrimaryCategory().getCategoryName()))
			.addField(Field.newBuilder().setName("secondaryCategory")
					.setAtom(c.getSecondaryCategory().getCategoryName()))
			.addField(Field.newBuilder().setName("tertiaryCategory")
					.setAtom(c.getTertiaryCategory().getCategoryName()))
			.addField(Field.newBuilder().setName("primaryCategoryHierarchy")
					.setText(c.getPrimaryCategory().getCategoryHierarchy()))
			.addField(Field.newBuilder().setName("secondaryCategoryHierarchy")
					.setText(c.getSecondaryCategory().getCategoryHierarchy()))
			.addField(Field.newBuilder().setName("tertiaryCategoryHierarchy")
					.setText(c.getTertiaryCategory().getCategoryHierarchy()))
			.addField(Field.newBuilder().setName("specialty1")
					.setText(c.getSpecialty1()))
			.addField(Field.newBuilder().setName("specialty2")
					.setText(c.getSpecialty2()))
			.addField(Field.newBuilder().setName("specialty3")
					.setText(c.getSpecialty3()))
			.addField(Field.newBuilder().setName("pocName")
					.setText(c.getPointOfContact().getFullName())).build();
		
		return doc;
	}
	
	/**
	 * Method to add a document to the specified Index. if the Index already exists then no new index will be created.
	 * 
	 * @param indexName
	 * @param document
	 */
	public static void IndexADocument(String indexName, Document document) {
	    IndexSpec indexSpec = IndexSpec.newBuilder().setName(indexName).build(); 
	    Index index = SearchServiceFactory.getSearchService().getIndex(indexSpec);
	    
	    try {
	        index.put(document);
	    } catch (PutException e) {
	        if (StatusCode.TRANSIENT_ERROR.equals(e.getOperationResult().getCode())) {
	            // retry putting the document
	        	index.put(document);
	        }
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
			results = (List<Company>)q.execute();
		} catch (Exception e) {
			//nothing
		}
		finally{
			pm.close();
		}
		return results;
	}
	
	public static void deleteAllDocsInIndex(String indexName){
		IndexSpec indexSpec = IndexSpec.newBuilder().setName(indexName).build(); 
	    Index index = SearchServiceFactory.getSearchService().getIndex(indexSpec);
		try {
		    // looping because getRange by default returns up to 100 documents at a time
		    while (true) {
		        List<String> docIds = new ArrayList<String>();
		        // Return a set of doc_ids.
		        GetRequest request = GetRequest.newBuilder().setReturningIdsOnly(true).build();
		        GetResponse<Document> response = index.getRange(request);
		        if (response.getResults().isEmpty()) {
		            break;
		        }
		        for (Document doc : response) {
		            docIds.add(doc.getId());
		        }
		        index.delete(docIds);
		    }
		} catch (RuntimeException e) {
		    System.out.print("Exception Has Occurred");
		}
	}
}