package directoryModel;
import java.util.ArrayList;
import java.util.List;

import com.google.appengine.api.search.Document;
import com.google.appengine.api.search.Field;
import com.google.appengine.api.search.GetRequest;
import com.google.appengine.api.search.GetResponse;
import com.google.appengine.api.search.Index;
import com.google.appengine.api.search.IndexSpec;
import com.google.appengine.api.search.SearchServiceFactory;
import com.google.appengine.api.search.PutException;
import com.google.appengine.api.search.QueryOptions;
import com.google.appengine.api.search.Results;
import com.google.appengine.api.search.ScoredDocument;
import com.google.appengine.api.search.StatusCode;
import com.google.appengine.api.search.Query;
public class SearchUtility {
	
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
			.addField(Field.newBuilder().setName("website")
					.setText(c.getWebsite()))
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
	 * Method that deletes all of the indexed documents for an Index name indexName
	 * @param indexName
	 */
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
	
	public static  Results<ScoredDocument> searchFor(String queryStr, String indexName){
		
		// Build the QueryOptions
	    QueryOptions options = QueryOptions.newBuilder()
	        .setLimit(40)
	        .setFieldsToReturn("companyName","website","companyDescription")
	        .setFieldsToSnippet("companyDescription")//appears to not be working at this point (12/3/2015)
	        .build();

	    // A query string
	    String queryString = queryStr;

	    //  Build the Query and run the search
	    Query query = Query.newBuilder().setOptions(options).build(queryString);
	    IndexSpec indexSpec = IndexSpec.newBuilder().setName(indexName).build();
	    Index index = SearchServiceFactory.getSearchService().getIndex(indexSpec);
	    Results<ScoredDocument> result =  index.search(query);	    
	    return result;
	}
}
