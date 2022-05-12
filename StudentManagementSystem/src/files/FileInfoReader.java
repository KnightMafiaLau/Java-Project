package files;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

import courses.Course;
import roles.Admin;
import roles.Professor;
import roles.Student;
import roles.User;

/**
 * Read the files and parse the files into ArrayList for later user
 * @author Zhiquan Liu
 *
 */
public class FileInfoReader {
	
	//instance var
	static final String COURSE = "courseInfo.txt";
	static final String PROF = "profInfo.txt";
	static final String ADMIN = "adminInfo.txt";
	static final String STUD = "studentInfo.txt";
	
	
	//constructor
	public FileInfoReader() {
		
	}
	

	
	/**
	 * Read the course file and export an ArrayList containing all the course information
	 * @return an ArrayList containing all the course information
	 * @throws FileNotFoundException
	 */
	public ArrayList<Course> readCourse() throws FileNotFoundException {
		File courseInfoFile = new File(COURSE);
		ArrayList<Course> courseInfo = new ArrayList<Course>();
		
		//read the course file
		Scanner sc = new Scanner(courseInfoFile);
		
		//while scanner has another token (value)
		while (sc.hasNextLine()) {
			//get the next line of string, split by the regex ";"
			String[] courseInfoDiv = sc.nextLine().split(";");
			
			//assign the divided information and create a new instance of course
			Course courseInfoItem = new Course(courseInfoDiv[0].trim(),
					courseInfoDiv[1].trim(), courseInfoDiv[2].trim(), 
					courseInfoDiv[3].trim(), courseInfoDiv[4].trim(), 
					courseInfoDiv[5].trim(), courseInfoDiv[6].trim());
			//add the new instance of course to the ArrayList
			courseInfo.add(courseInfoItem);
		}
		sc.close();
		return courseInfo;
	}
	
	/**
	 * Read the professor file and export an ArrayList containing all the professor information
	 * @return and ArrayList containing all the professor information
	 * @throws FileNotFoundException
	 */
	public ArrayList<User> readProfessor(ArrayList<Course> course) throws FileNotFoundException {
		File profInfoFile = new File(PROF);
		ArrayList<User> profInfo = new ArrayList<User>();
		
		//read the course file
		Scanner sc = new Scanner(profInfoFile);
		//while have next line to read in the file
		while (sc.hasNextLine()) {
			//read the next line and divide by the regex ";" to get the info
			String[] professorInfoDiv = sc.nextLine().split(";");
			//create a new instance of professor with the info
			User professorInfoItem = new Professor(professorInfoDiv[1].trim(), professorInfoDiv[0].trim(), 
					professorInfoDiv[2].trim(), professorInfoDiv[3].trim());
			professorInfoItem.getCourseEnroll(course);
			//add that instance of professor to the ArrayList
			profInfo.add(professorInfoItem);
		}
		//close the scanner
		sc.close();
		return profInfo;
	}
	
	/**
	 * Read the admin file and export an ArrayList containing all the admin information
	 * @return an ArrayList containing all the admin information
	 * @throws FileNotFoundException
	 */
	public ArrayList<User> readAdmin() throws FileNotFoundException {
		File adminInfoFile = new File(ADMIN);
		ArrayList<User> adminInfo = new ArrayList<User>();
		
		//read the course file
		Scanner sc = new Scanner(adminInfoFile);
		//while have next line to read in the file
		while (sc.hasNextLine()) {
			//read the next line and divide by the regex ";" to get the info
			String[] adminInfoDiv = sc.nextLine().split(";");
			//create a new instance of professor with the info
			User adminInfoItem = new Admin(adminInfoDiv[0].trim(), adminInfoDiv[1].trim(), 
					adminInfoDiv[2].trim(), adminInfoDiv[3].trim());
			//add that instance of professor to the ArrayList
			adminInfo.add(adminInfoItem);
		}
		//close the scanner
		sc.close();
		return adminInfo;
	}
	
	/**
	 * Read the student file and export an ArrayList containing all the student information
	 * @return an ArrayList containing all the student information
	 * @throws FileNotFoundException
	 */
	public ArrayList<User> readStudent() throws FileNotFoundException{
		File studentInfoFile = new File(STUD);
		ArrayList<User> studentInfo = new ArrayList<User>();
		
		//read the course file
		Scanner sc = new Scanner(studentInfoFile);
		
		//while have next line to read in the file
		while (sc.hasNextLine()) {
			//read the next line of file and split them with regex ";"
			String[] studentInfoDiv = sc.nextLine().split(";");
			//initiate a HashMap to store the course and grade
			HashMap<String, String> courseNGrade = new HashMap<String, String>();
			//split different course by regex ","
			String[] courseTaken = studentInfoDiv[4].split(",");
			//iterate over the total number of courses
			for (int i = 0; i < courseTaken.length; i++) {
				//assign the course ID and grade into the HashMap
				String courseName = courseTaken[i].split(":")[0].trim();
				String grade = courseTaken[i].split(":")[1].trim();
				courseNGrade.put(courseName, grade);
			}
			//create a new instance of student
			User student = new Student(studentInfoDiv[0].trim(), 
					studentInfoDiv[1].trim(), studentInfoDiv[2].trim(), studentInfoDiv[3].trim(), courseNGrade);
			//add the instance of student into the ArrayList
			studentInfo.add(student);
		}
		sc.close();
		return studentInfo;
	}
	
	

	
}
