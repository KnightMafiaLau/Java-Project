package roles;

import java.util.ArrayList;
import java.util.HashMap;

import courses.Course;

/**
 * Subclass of User. Represents an admin in the management system
 * @author Zhiquan Liu
 *
 */
public class Admin extends User {

	//constructor
	
	/**
	 * Construct an instance of admin
	 * @param ID of the admin
	 * @param name of the admin
	 * @param userName of the admin
	 * @param password of the admin
	 */
	public Admin(String ID, String name, String userName, String password) {
		super(ID, name, userName, password);
	}

	@Override
	public ArrayList<Course> getCourseEnroll() {
		
		return null;
	}

	@Override
	public void printEroll() {
		
		
	}

	@Override
	public HashMap<String, String> getCourseGrade() {
		
		return null;
	}

	@Override
	public void printMenu() {
		// TODO Auto-generated method stub
		System.out.println();
		System.out.println("----------------------------------------");
		System.out.println("      Welcome, " + this.getName());
		System.out.println("----------------------------------------");
		System.out.println();
		System.out.println(" 1 -- View all courses");
		System.out.println(" 2 -- Add new courses");
		System.out.println(" 3 -- Delete courses");
		System.out.println(" 4 -- Add new professor");
		System.out.println(" 5 -- Delete Professor");
		System.out.println(" 6 -- Add new student");
		System.out.println(" 7 -- Delete student");
		System.out.println(" 8 -- Return to previous menu");
		System.out.println();
		System.out.println("Please enter your option, eg. '1'.");
		
	}


}
