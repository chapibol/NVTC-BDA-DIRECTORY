package directoryControls;

import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SearchServlet extends HttpServlet {
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException{
		String queryString = request.getParameter("searchQuery");
		System.out.println(queryString);
		//search index CompanyIndex
		//retrieve results
		//convert to JSON
		//send to front end
		//broseCompanies.html
		
		
	}
}
