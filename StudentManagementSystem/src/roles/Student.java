package roles;

import java.util.HashMap;

import courses.Course;

public class Student extends User {
	

	//instance variable
	/**
	 * Contains the grade for the course the student has already taken
	 */
	private HashMap<String, String> courseGrade;
	
	//getters and setters
	
	/**
	 * @return the courseGrade
	 */
	@Override
	public HashMap<String, String> getCourseGrade() {
		return courseGrade;
	}

	/**
	 * @param courseGrade the courseGrade to set
	 */
	public void setCourseGrade(HashMap<String, String> courseGrade) {
		this.courseGrade = courseGrade;
	}
	
	/**
	 * Creates a new instance of student in the student management system
	 * @param ID of the student
	 * @param name of the student
	 * @param userName of the student
	 * @param password of the student
	 * @param courseGrade of the student for a specific course
	 */
	public Student(String ID, String name, String userName, String password, HashMap<String, String> courseGrade) {
		super(ID, name, userName, password);
		
		this.courseGrade = courseGrade;
	}

	/**
	 * prints the course enrollment of the student
	 */
	@Override
	public void printEroll() {
		System.out.println();
		System.out.println("The courses in your list: ");
		for (Course c : this.getCourseEnroll()) System.out.println(c);
		System.out.println();
		
	}

	/**
	 * Prints the student's menu
	 */
	@Override
	public void printMenu() {
		System.out.println("--------------------------------------");
		System.out.println("         Welcomem, " + this.getName());
		System.out.println("--------------------------------------");
		System.out.println(" 1 -- View all courses");
		System.out.println(" 2 -- Add courses to your list");
		System.out.println(" 3 -- View selected courses");
		System.out.println(" 4 -- Drop courses in your list");
		System.out.println(" 5 -- View grades");
		System.out.println(" 6 -- Return to previous menu");
		System.out.println("");
		System.out.println("Please enter your option, eg. '1'.");
		
	}
	
	
	

	

}
