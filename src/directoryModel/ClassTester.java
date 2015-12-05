package directoryModel;

import com.google.appengine.api.datastore.Text;

public class ClassTester {

	public static void main(String[] args) {
		
		Company a = new Company("George Mason University", "www.gmu.edu", new Text("One of the largest Universities in the state of Virginia"));
		
		System.out.println(a.getDescription().getValue());
		
		System.out.println(a.getWebsite());
		System.out.println(a.getName());

	}

}