package directoryControls;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.*;

import directoryModel.Company;
import directoryModel.Utility;

//the purpose of this servlet is to retrieve the 
public class RetrieveCompanyServlet extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException{
		long companyId = Long.parseLong(request.getParameter("cId"));
		System.out.println("This is the cId from search: " + companyId);
		Company c = Utility.getCompanyById(companyId);
		request.setAttribute("aCompany", c);
		 try {
			getServletContext().getRequestDispatcher("/viewCompany.jsp").forward(request, response);
		} catch (ServletException e) {			
			e.printStackTrace();
		}
	}
}
