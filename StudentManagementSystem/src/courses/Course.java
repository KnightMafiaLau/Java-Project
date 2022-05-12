package courses;

import java.util.ArrayList;

import roles.User;

/**
 *  a course in the management system
 * @author Zhiquan Liu
 *
 */
public class Course {
	
	//instant variables
	
	/**
	 * ID of the course
	 */
	private String courseID;
	
	/**
	 * Name of the course
	 */
	private String courseName;
	
	/**
	 * Lecturer of the course
	 */
	private String lecturer;
	
	/**
	 * date of the course
	 */
	private String date;
	
	/**
	 * Start time of the course
	 */
	private String startTime;
	
	/**
	 * End time of the course
	 */
	private String endTime;
	
	/**
	 * Class capacity of the course
	 */
	private String capacity;
	

	
	/**
	 * Enrollment of the course
	 */
	private ArrayList<User> enrollment = new ArrayList<User>();
	
	//constructor
	public Course(String courseID, String courseName, String lecturer, 
			String date, String startTime, String endTime, String capacity) {
		//assign the vars
		this.courseID = courseID;
		this.courseName = courseName;
		this.lecturer = lecturer;
		this.date = date;
		this.startTime = startTime;
		this.endTime = endTime;
		this.capacity = capacity;
	}
	
	/**
	 * Override the toString() method
	 */
	@Override
	public String toString() {
		return this.courseID + "|" + this.courseName + ", " + this.startTime 
				+ "-" + this.endTime + " on " + this.date + ", with course capacity: " 
				+ this.capacity + ", students: " + this.enrollment.size() + ", lecturer: " + this.lecturer;
	}
	
	
	
	//getters and setters

	/**
	 * @return the courseID
	 */
	public String getCourseID() {
		return courseID;
	}

	/**
	 * @return the courseName
	 */
	public String getCourseName() {
		return courseName;
	}

	/**
	 * @return the lecturer
	 */
	public String getLecturer() {
		return lecturer;
	}

	/**
	 * @return the date
	 */
	public String getDate() {
		return date;
	}

	/**
	 * @return the startTime
	 */
	public String getStartTime() {
		return startTime;
	}

	/**
	 * @return the endTime
	 */
	public String getEndTime() {
		return endTime;
	}

	/**
	 * @return the capacity
	 */
	public String getCapacity() {
		return capacity;
	}

	/**
	 * @return the enrollment
	 */
	public ArrayList<User> getEnrollment() {
		return enrollment;
	}
	
	

}
