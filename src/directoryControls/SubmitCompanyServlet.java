/**
 * 
 */
package directoryControls;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.*;
import directoryModel.*;

public class SubmitCompanyServlet extends HttpServlet{

	public void doPost(HttpServletRequest request, HttpServletResponse response)throws IOException{
		String firstName = request.getParameter("fname");
		String lastName = request.getParameter("lname");
		String email = request.getParameter("email");
		
		//Extract Company info
		String companyName = request.getParameter("companyName");
		String website = request.getParameter("website");
		String telephone = request.getParameter("companyPhone");
		String description = request.getParameter("companyDescription");
		String address1 = request.getParameter("companyAddress1");
		String address2 = request.getParameter("companyAddress2");
		String city = request.getParameter("companyCity");
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
		String specialty1 = request.getParameter("specialty1");
		String specialty2 = request.getParameter("specialty2");
		String specialty3 = request.getParameter("specialty3");
		
		String [] primCats = {primaryCategoryDropdown,primarySecondLevel,primaryThirdLevel,primaryFourthLevel};
		String [] secCats = {secondaryCategoryDropdown,secondaryCatSecondLevel,secondaryCatThirdLevel,secondaryCatFourthLevel};
		String [] terCats = {tertiaryCategoryDropdown,tertiaryCatSecondLevel,tertiaryCatThirdLevel,tertiaryCatFourthLevel};
		
		//create company object
		Company comp = EntityCreator.createCompany(companyName,website,description);
		//populate aCompany object with data from form
		PointOfContact poc = new PointOfContact();
		poc.setFirstName(firstName);
		poc.setLastName(lastName);
		poc.setEmail(email);
		comp.setPointOfContact(poc);
		comp.setTelephone(telephone);
		Address address = new Address(address1,city,state,zipcode);
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
		
		response.setContentType("text/html");
		response.getWriter().println(comp.toString());
		
		
	
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		

		
		
		
		
		
		
//    	PointOfContact poc = EntityCreator.createPointOfContact(firstName, lastName, email);
//		Company aCompany = EntityCreator.createCompany(companyName, website, description);
//		aCompany.setTelephone(telephone);
		
		
		
		
		
		
		
		
		
		
		//while(!data.isEmpty()){
			//System.out.println(data.get("companyName"));
		//}
		
		
//		+ "\nPrimary Cat: " +primaryCategory+
//				"\nPCat Second Level:" + primaryCategorySecond + "\nPCat Third: " + primaryCategoryThird);
	
		/*String username = request.getParameter("username");
		String answerText = request.getParameter("answer-text");
		String answerCode = request.getParameter("answer-code");
		//Luis, changed questionId to long because that is the datatype the datastore uses.
		long questionId = Long.parseLong(request.getParameter("qid"));
		//create the answer
		Answer a = Answer.createAnswer(username, answerText, answerCode);
		//@Luis: update the question with the answer specified, this was done as a single static method so that only one instance of the persistence manager is used.
		Question.updateQuestionWithAnswer(questionId, a);
		//after saving the answer to the question, go back to the view .jsp
		response.sendRedirect("/view.jsp?qid=" + questionId);*/		
	}

}