/**
 * 
 */
package directoryControls;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.*;

import com.google.appengine.api.datastore.Text;
import com.google.appengine.api.search.Document;

import directoryModel.*;

public class SubmitCompanyServlet extends HttpServlet{
	
	final String ALL_COMPANIES_INDEX = "AllCompanies";
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException{
		String firstName = request.getParameter("fname").trim();
		String lastName = request.getParameter("lname").trim();
		String email = request.getParameter("email").trim();
		
		//Extract Company information
		String companyName = request.getParameter("companyName").trim();
		String website = request.getParameter("website").trim();
		String telephone = request.getParameter("companyPhone");
		Text description = new Text(request.getParameter("companyDescription"));
		String address1 = request.getParameter("companyAddress1").trim();
		String address2 = request.getParameter("companyAddress2");
		String city = request.getParameter("companyCity").trim();
		String state = request.getParameter("companyState");
		String zipcode = "" + request.getParameter("companyZip");//convert zipcode to string
		
		
		//extract primary categories
		String primaryCategoryDropdown = request.getParameter("category_dropdown");//first level of primary category
		String primarySecondLevel = request.getParameter("subcategory");//get the input from the second level of primary category
		String primaryThirdLevel = request.getParameter("primary3rdLevel");//get the input from the third level other
		String primaryFourthLevel = request.getParameter("primary4thLevel");
		
		//extract secondary categories
		String secondaryCategoryDropdown = request.getParameter("category_2nd");//gets first parameter of first level from secondary categories
		String secondaryCatSecondLevel = request.getParameter("secondaryCatSecondLevel");
		String secondaryCatThirdLevel = request.getParameter("secondaryCat3rdLevel");
		String secondaryCatFourthLevel = request.getParameter("secondaryCat4thLevel");
		//extract tertiary categories
		String tertiaryCategoryDropdown = request.getParameter("category_3rd");//gets first parameter of first level from secondary categories
		String tertiaryCatSecondLevel = request.getParameter("tertiaryCatSecondLevel");
		String tertiaryCatThirdLevel = request.getParameter("tertiaryCat3rdLevel");
		String tertiaryCatFourthLevel = request.getParameter("tertiaryCat4thLevel");		
		
		//extract company specialties
		String specialty1 = request.getParameter("specialty1").trim();
		String specialty2 = request.getParameter("specialty2").trim();
		String specialty3 = request.getParameter("specialty3").trim();
		
		String [] primCats = {primaryCategoryDropdown,primarySecondLevel,primaryThirdLevel,primaryFourthLevel};
		String [] secCats = {secondaryCategoryDropdown,secondaryCatSecondLevel,secondaryCatThirdLevel,secondaryCatFourthLevel};
		String [] terCats = {tertiaryCategoryDropdown,tertiaryCatSecondLevel,tertiaryCatThirdLevel,tertiaryCatFourthLevel};
		//append http:// to website
		website = "http://" + website;
		//create company object
		Company comp = EntityCreator.createCompany(companyName,website,description);
		//populate aCompany object with data from form
		PointOfContact poc = new PointOfContact();
		poc.setFirstName(firstName);
		poc.setLastName(lastName);
		poc.setEmail(email);
		comp.setPointOfContact(poc);
		comp.setTelephone(telephone);
		Address address = EntityCreator.createAddress(address1,address2, city,state,zipcode);
		comp.setAddress(address);
		//build category object with 3 params, type, category name, hierarchy
		Category primaryCategory = Company.createCategory("Primary",Utility.getCategoryName(primCats),Utility.buildCategoryHierarchy(primCats));
		Category secondaryCategory = Company.createCategory("Secondary",Utility.getCategoryName(secCats),Utility.buildCategoryHierarchy(secCats));
		Category tertiaryCategory = Company.createCategory("Tertiary",Utility.getCategoryName(terCats),Utility.buildCategoryHierarchy(terCats));
		//store categories to company object (comp)
		comp.setPrimaryCategory(primaryCategory);
		comp.setSecondaryCategory(secondaryCategory);
		comp.setTertiaryCategories(tertiaryCategory);
		
		comp.setSpecialty1(specialty1);
		comp.setSpecialty2(specialty2);
		comp.setSpecialty3(specialty3);
		//store company object into the datastore and redirect to Successful Submission Screen
		
		//save created company to the datastore
		Utility.saveCompanyToDatastore(comp);
		
		//create document for search index
		Document doc = SearchUtility.createCompanyDocument(comp);
		//add doc to AllCompanies Index
		SearchUtility.IndexADocument(ALL_COMPANIES_INDEX, doc);
		 request.setAttribute("submittedCompanyName", comp.getName());//
		 request.setAttribute("companyId", comp.getKey().getId());//send the id also to form a link
		 try {
			getServletContext().getRequestDispatcher("/success.jsp").forward(request, response);
		} catch (ServletException e) {			
			e.printStackTrace();
		} catch (IOException e) {
			response.getWriter().write("Oops Your request could not be complete.");;
		}		
	}
}