package directoryModel;

public class ClassTester {

	public static void main(String[] args) {
		
		Company a = new Company("George Mason University", "www.gmu.edu", "One of the largest Universities in the state of Virginia");
		
		System.out.println(a.getDescription());
		
		System.out.println(a.getWebsite());
		System.out.println(a.getName());

	}

}