package roles;

import java.util.ArrayList;
import java.util.HashMap;

import courses.Course;


/**
 * Subclass of user. Represents a professor in the management system
 * @author Zhiquan Liu
 *
 */
public class Professor extends User {

	//constructor
	public Professor(String ID, String name, String userName, String password) {
		super(ID, name, userName, password);
	}


	/**
	 * Print the enroll
	 */
	@Override
	public void printEroll() {
		System.out.println("------------The course list------------");
		for (Course c : this.getCourseEnroll()) System.out.println(c);
		System.out.println();
	}

	
	@Override
	public HashMap<String, String> getCourseGrade() {
		return null;
	}

	/**
	 * Prints the professor's menu
	 */
	@Override
	public void printMenu() {

		System.out.println();
		System.out.println("----------------------------------------");
		System.out.println("      Welcome, " + this.getName());
		System.out.println("----------------------------------------");
		System.out.println();
		System.out.println(" 1 -- View given courses");
		System.out.println(" 2 -- View student list of the given course");
		System.out.println(" 3 -- Return to the previous menu");
		System.out.println();
		System.out.println("Please enter your option, eg. '1'.");
	}
	
	/**
	 * For professors, unlike the students, professor's course list is within the system
	 * This method will update the professor's course enrollment list
	 */
	@Override
	public void getCourseEnroll(ArrayList<Course> course) {
		//iterate over the course list
		for (Course c : course) {
			//find if the lecturer's name is the same as the professor's name, add to ArrayList if match
			if ((c.getLecturer().equals(this.getName())) && !this.getCourseEnroll().contains(c)) this.getCourseEnroll().add(c);
		}
	}

	

}
