package directoryControls;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.api.search.Field;
import com.google.appengine.api.search.Results;
import com.google.appengine.api.search.ScoredDocument;

import directoryModel.SearchUtility;
import directoryModel.Utility;

public class SearchServlet extends HttpServlet {
	final String ALL_COMPANIES_INDEX = "AllCompanies";
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException{
		String queryString = request.getParameter("searchQuery");
		Results<ScoredDocument> results = SearchUtility.searchFor(queryString, ALL_COMPANIES_INDEX);	
		//testing code.
//		for(ScoredDocument d: results){		
//			
//			System.out.println("Doc Id: " + d.getId() + "\n"
//					+ "Company Name: " + d.getOnlyField("companyName").getAtom() + "\n"
//							+ "Company Description: " + Utility.toSnippet(d.getOnlyField("companyDescription").getText()));
//			System.out.println();
//		}
		//send results to broseCompanies.jsp in order to be displayed
		request.setAttribute("searchResults", results);//send the id also to form a link
		 try {
			getServletContext().getRequestDispatcher("/searchResults.jsp").forward(request, response);
		} catch (ServletException e) {			
			e.printStackTrace();
		}		
				
	}
}
