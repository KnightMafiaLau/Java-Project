package roles;

import java.util.ArrayList;
import java.util.HashMap;

import courses.Course;

/**
 * Represents all the users in the system, including Professors, students and admins
 * @author Zhiquan Liu
 *
 */
public abstract class User {
	
	//instant vars
	
	/**
	 * Name of the user
	 */
	private String name;
	
	/**
	 * user name of the user
	 */
	private String userName;
	
	/**
	 * password of the user
	 */
	private String password;
	
	/**
	 * Id of the user
	 */
	private String ID;
	
	/**
	 * 
	 */
	private ArrayList<Course> courseEnroll = new ArrayList<Course>();
	
	//getters and setters

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}


	/**
	 * @return the userName
	 */
	public String getUserName() {
		return userName;
	}


	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}


	/**
	 * @return the iD
	 */
	public String getID() {
		return ID;
	}

	
	//constructor
	
	/**
	 * Construct a User with the following user information
	 * @param ID of the user
	 * @param name of the user
	 * @param userName of the user
	 * @param password of the user
	 */
	public User(String ID, String name, String userName, String password) {
		this.ID = ID;
		this.name = name;
		this.userName = userName;
		this.password = password;
	}
	

	/**
	 * Gets the course user enrolls in
	 * @return an ArrayList of course for enrollment
	 */
	public ArrayList<Course> getCourseEnroll(){
		return this.courseEnroll;
	};
	
	/**
	 * Prints the enrollment of course
	 */
	public abstract void printEroll();
	
	/**
	 * Prints the menu corresponding to the user
	 */
	public abstract void printMenu();
	
	/**
	 * Gets the course enrolled in the past and the grade
	 * @return a HashMap containing the course ID (key) and grade (val) pair
	 */
	public abstract HashMap<String, String> getCourseGrade();
	
	/**
	 * Get course enrolled from a list of course
	 * @param course to check from
	 */
	public void getCourseEnroll(ArrayList<Course> course) {
		
	}
	
	/**
	 * Overrides toString function to printout user
	 */
	@Override
	public String toString() {
		return this.getID() + " " + this.getName();
	}
	


}