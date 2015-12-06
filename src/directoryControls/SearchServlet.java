package directoryControls;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.jdo.JDOObjectNotFoundException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.api.search.Field;
import com.google.appengine.api.search.Results;
import com.google.appengine.api.search.ScoredDocument;

import directoryModel.Company;
import directoryModel.SearchUtility;
import directoryModel.Utility;

public class SearchServlet extends HttpServlet {
	final String ALL_COMPANIES_INDEX = "AllCompanies";
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException{
		String queryString = request.getParameter("searchQuery");
		Results<ScoredDocument> results = SearchUtility.searchFor(queryString, ALL_COMPANIES_INDEX);	
		
		List<Company> companies = new ArrayList<Company>();
		//retrieve companies from data store
		for(ScoredDocument doc: results){
			//get company from datastore
			try{
				Company c = Utility.getCompanyById(Long.parseLong(doc.getId()));
				if(c != null){
					companies.add(c);
				}
			}catch(JDOObjectNotFoundException j){
				System.out.println("object not found move to next iteration");				
			}finally{
				continue;
			}
			
		}
		//send results to broseCompanies.jsp in order to be displayed
		request.setAttribute("searchResults", results);//send the id also to form a link
		request.setAttribute("companies", companies);
		 try {
			getServletContext().getRequestDispatcher("/searchResults.jsp").forward(request, response);
		} catch (ServletException e) {			
			e.printStackTrace();
		}						
	}
}