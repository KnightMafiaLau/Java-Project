package control;
import files.FileInfoReader;
/**
 * 
 * @author Zhiquan Liu 87454441
 *
 */
import roles.Professor;
import roles.Student;
import roles.User;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

import courses.Course;

public class Controller {
	
	
	/**
	 * Prints the main menu
	 */
	public static void mainMenu() {
		System.out.println("--------------------------");
		System.out.println("Students Management System");
		System.out.println("--------------------------");
		System.out.println(" 1 -- Login as a student");
		System.out.println(" 2 -- Login as a professor");
		System.out.println(" 3 -- Login as an admin");
		System.out.println(" 4 -- Quit the System");
		System.out.println("");
		System.out.println("Please enter your option, eg. '1'");
	}
	
	/**
	 * Used for validation of user's input when directing within the menus
	 * This method will keep asking user to give a input until the given input is valid.
	 * @param sc Scanner for input
	 * @param upperBound of the valid input, this is the bound given in certain stage of the program to determine the next step
	 * @param lowerBound of the valid input, this is the bound given in certain stage of the program to determine the next step
	 * @return the validated integer input
	 */
	public static int inputValidation(Scanner sc, int lowerBound, int upperBound) {
		//initialize the variables
		String input = "";
		//this variable is for storing the input as integer
		int userInInt = 0;
		//flag variable that determine whether the variable is valid
		input = sc.next();
		boolean checkQuit = false;//if the user want to quit at the main step, then we should exit the whole program
		while (true) {//using the while loop to control the whole projext running
			boolean checkInteger = false; 

			try
			{
				Integer.parseInt(input);
				userInInt = Integer.parseInt(input);
				if (userInInt >= lowerBound && userInInt <= upperBound) {
					checkInteger = true;
					break;
				}else {
					System.out.println ("Enter a valid integer");
					input = sc.next();
				}
			}catch (NumberFormatException e){
				System.out.println ("Enter a valid integer");
				input = sc.next();
			}
				
				
				
		}
		
		if (checkQuit) {
			System.exit(0);
		
		}
		return userInInt;
		
	}
		
	/**
	 * This methods check the user's input of user name and password and find the 
	 * matching user in the system 
	 * The input ArrayList users can be Admins, Professors, or Students.
	 * The method will keep asking user to give a correct user name and password combination
	 * @param users to check over
	 * @param sc scanner
	 * @return the user file corresponding to the user's login info.
	 */
	public static User userLogin(ArrayList<User> users, Scanner sc) {
	
		//initialize the indicators, this use to store the user's index in the array list
		int index = -1;
		boolean check_login_info = false;
		
		while (true) {//using the while loop to keep the program running until quit
			System.out.println("Please enter your username, or type 'q' to quit.");
			String user_name = sc.next();
			if (user_name.equals("q")) {
				break;//exit the while loop when user enter q
			}else{
				boolean match_user_name = false;//flag variable used to identify whether there is a user name exist in the database
				while (!match_user_name) {
					if (user_name.equals("q")) {
						break;//exit the while loop when user enter q
				}
					for (User user:users) {//check if the input username exists in the database
						if (user.getUserName().equals(user_name)) {
							match_user_name = true;
						}
					}if (!match_user_name) {
						System.out.println("Please enter a valid username, or type 'q' to quit.");
						user_name = sc.next();
					}
			}
		}
			System.out.println("Please enter your password, or type 'q' to quit.");
			String password = sc.next();
			if (password.equals("q")) {
				break;
			}else {
				
				for (User user : users) {
					//check both the user name and password
					if (user.getUserName().equals(user_name)) {
						if (user.getPassword().equals(password)) {
							//if both are correct, record the index
							index = users.indexOf(user);
							check_login_info = true;
							break;
						}
					}
				}
			}

			//the index == -1 indicates no user is found based on the given login info
			if (index == -1) {
				System.out.println("Password is not right. Please try again.");
				continue;
			}
			if (check_login_info) {
				//if the username and password match, break the loop
				break;
			}
		}
		//return the user profile found
		return users.get(index);
	}

	/**
	 * This methods takes to courses in and check if there is a time conflict
	 * @param course1 to check
	 * @param course2 to check
	 * @return true if conflict
	 */
	public static boolean checkTime(Course course1, Course course2) {
		//initialize the flag
		boolean checkConflict = false;
		// get the first start time and the end time of the selected course
		String startTimeA = course1.getStartTime();
		String endTimeA = course1.getEndTime();
		//get the date of selected course
		String date1 = course1.getDate();
		ArrayList<Character> date1Char = new ArrayList<Character>();
		for (int i = 0; i < date1.length(); i++) date1Char.add(date1.charAt(i));
		// get the another start time and the end time of the selected course as the first one
		String startTimeB = course2.getStartTime();
		String endTimeB = course2.getEndTime();
		String date2 = course2.getDate();
		ArrayList<Character> date2Char = new ArrayList<Character>();
		for (int i = 0; i < date2.length(); i++) date2Char.add(date2.charAt(i));
		
		//hour * 60 + minutes to get the total minutes from midnight
		int startMinA = Integer.parseInt(startTimeA.split(":")[0]) * 60 
				+ Integer.parseInt(startTimeA.split(":")[1]);	
		
		int endMinA = Integer.parseInt(endTimeA.split(":")[0]) * 60 
				+ Integer.parseInt(endTimeA.split(":")[1]);
		
		int startMinB = Integer.parseInt(startTimeB.split(":")[0]) * 60 
				+ Integer.parseInt(startTimeB.split(":")[1]);
		
		int endMinB = Integer.parseInt(endTimeB.split(":")[0]) * 60 
				+ Integer.parseInt(endTimeB.split(":")[1]);
		
		//check whether two classes are on the same day
		//iterate over the less meeting times class
		if (date1Char.size() >= date2Char.size()) {
			for (char c : date2Char) {
				//if the two class happens on the same day, check if the time is conflict
				if (date1Char.contains(c)) {
					//if the later class starts during the earlier class, then conflict
					if ((startMinB <= startMinA) && (startMinA < endMinB)) {
						checkConflict = true;
					}
					else if ((startMinA <= startMinB) && (startMinB < endMinA)) {
						checkConflict = true;
					}
				}
			}
		}
		return checkConflict;
	}
	

	public static boolean isInList(String toCheck, ArrayList<User> user, String check) {
		//this is used for checking whether the given user is in the given list, like the "is in" in python
		for (User u : user) {
			//checking id if the input match ID option
			if (check.equals("username")) {
				if (u.getUserName().equals(toCheck)) return true; //return true if find the same user name
			} else if(check.equals("ID")) {
				if (u.getID().equals(toCheck)) return true;	//return true if find the same id
			//check user name if the input match user name option
			}
			
		}
		return false; //return false if no match option is found
	}
	
	/**
	 * Method overloading for Course class. We only care ID since it is the identifier for courses
	 * @param toCheck ID input
	 * @param course an ArrayList containing all the course information
	 * @return true if the ID is already in the system
	 */
	public static boolean isInList(String toCheck, ArrayList<Course> course) {
		//this is used for checking whether the given course is in the given list
		for (Course c : course) {
			//checking id if the input match ID option
			if (c.getCourseID().equals(toCheck)) return true;	//return true if find the same id
		}
		return false; //return false if no match option is find
	}
	
	
	/**
	 * This method add a new student into the database based on the input
	 * @param sc Scanner to get input
	 * @param stud an ArrayList containing the student's information
	 * @param course an Arraylist containing the course info.
	 * @return true if the new student is successfully added to the system.
	 */
	public static boolean addStudent(Scanner sc, ArrayList<User> stud, ArrayList<Course> course) {
		//initialize the variables
		String ID = null, name, stuName = null, password, courseTaken, grade;
		HashMap<String, String> courseGrade = new HashMap<String, String>();
		boolean check = false;
		//since id should be numeric, keep asking if the id input is not a number
		         

		while (!check) {
			System.out.println("Please enter the student's ID, or type 'q' to quit");
			ID = sc.next();
			if (!ID.equals("q")) {
				try
				{
					Integer.parseInt(ID);
					if (!isInList(ID, stud, "ID")) {
						check = true;
					}
					else{
						System.out.println("the ID is already occupied, please enter another one");
					}
				}catch (NumberFormatException e) {
					System.out.println("please enter a valid ID");
				}
			}else {
				return false;
			}
		}
			

		//ask for student's name
		System.out.println("Please enter the student's name, or type 'q' to quit");
		name = sc.next();
		if (name.equals("q")) return false;	//quit if q
		
		//keep asking student's userName, the userName should be unique
		check = false;
		System.out.println("Please enter the student's username, or type 'q' to quit");
		stuName = sc.next();//get the student name from user
		while(!check) {
			if (stuName.equals("q")) return false;	//quit if q
			else if (!isInList(stuName, stud, "username")) { 
				check = true;
			}else {
				System.out.println("Please enter a new student's username, or type 'q' to quit");
				stuName = sc.next();//keep asking until the name is valid
			}
		}
		//get student's password
		System.out.println("Please enter the student's password, or type 'q' to quit");
		password = sc.next();
		if (password.equals("q")) return false;
		
		//get the course student has already taken and it's grade. This will keep asking until user decide to stop
		while (true) {
			System.out.println("Please enter ID of a course which this student already took, one in a time");
			System.out.println("Type 'q' to quit, type 'n' to stop adding");
			courseTaken = sc.next();
			if (courseTaken.equals("n")) {
				break;
			}else if (courseTaken.equals("q")) {
				return false;
			}else if (courseGrade.containsKey(courseTaken)) {	 
				System.out.println("The course is already in the system. Please enter another one");
				continue;
			}else if (!isInList(courseTaken, course)) {	//if the course is not in the database, print out a message
				System.out.println("The course entered is not found. Please enter a valid course ID");
				continue;
			}
			
			//take the grade of the corresponding course
			System.out.println("Please enter the grade, eg, 'A'");
			grade = sc.next();
			//add to the HashMap containing the course ID and grade
			courseGrade.put(courseTaken, grade);
		}
		//create a new instance of student and add it to the ArrayList containing all the student's information
		User newStud = new Student(ID, name, stuName, password, courseGrade);
		stud.add(newStud);
		System.out.println("Successfully added the new studnet: " + newStud.getID() + " " + newStud.getName());
		return true;
	}
	
	
	
	/**
	 * This method adds a new professor to the management system. It will ask for informations about the new professor.
	 * This method will return true if the new professor is successfully added, and false if decided to return to the
	 * previous menu
	 * @param sc Scanner to take input
	 * @param prof ArrayList containing the information of all professors
	 * @return true if the new professor is successfully added to the system
	 */
	public static boolean addNewProf(Scanner sc, ArrayList<User> prof) {
		//initialize variables to store the information of the new professor
		String iD = "";
		String name = "";
		String newUserName = "";
		String password = "";
		boolean check = false;
		while (!check) {
			System.out.println("Please enter the professor's ID, or type 'q' to quit");
			iD = sc.next();
			if (!iD.equals("q")) {
				try
				{
					Integer.parseInt(iD);
					if (!isInList(iD, prof, "ID")) {
						check = true;
					}
					else{
						System.out.println("the ID is already occupied, please enter another one");
					}
				}catch (NumberFormatException e) {
					System.out.println("please enter a valid ID");
				}
			}else {
				return false;
			}
		}

		//ask for the name of the new professor and check if the admin want to quit
		System.out.println("Please enter professor's name, or type 'q' to end");
		name = sc.next();
		if (name.equals("q")) return false;
		
		//ask for new prof's username. The user name should be unique so keep asking for a 
		//valid user name if the input is invalid
		boolean flag = false;
		while (!flag) {
			System.out.println("Please enter the professor's user name, or type 'q' to quit");
			newUserName = sc.next();
			if(!isInList(newUserName, prof, "userName")){
				flag = true;
			}else System.out.println("The userName already exists");
		}
		
		
		//ask for the password
		System.out.println("Please enter a password");
		password = sc.next();
		//create a new instance of professor with the information gathered above, and add the 
		//new professor to the existing professor's list
		User newProf = new Professor(iD, name, newUserName, password);
		prof.add(newProf);
		System.out.println("succesffuly added the new professor "  + ": " 
				+ prof.get(prof.size() - 1).getID() + " " + prof.get(prof.size() - 1).getName());
		return true;
	}
	
	/**
	 * This method adds a course based on admin's input information
	 * @param sc Scanner for admin input
	 * @param course an ArrayList containing all the information for course
	 * @param prof an ArrayList containing all the information for professors
	 * @return true if course is successfully added, false if admin want to quit
	 */
	public static boolean addNewCourse(Scanner sc, ArrayList<Course> course, ArrayList<User> prof) {
		//initialize the information of course for later use
		String courseID = "";
		String courseName, startTime, endTime, date, capacity, lecturer;
		//keep asking for a valid course ID. Each course should have an unique course ID
		boolean flag = false;
		System.out.println("Please enter the course ID, or type 'q' to end.");
		courseID = sc.next();
		while (!flag) {
			if (!courseID.equals("q")) {
				if (!isInList(courseID, prof, "ID")) {
						flag = true;
					}
					else{
						System.out.println("The course already exists.");
					}
				
			}else {
				return false;
			}
		}
	
		
		//get the name of the course
		System.out.println("Please enter the course name");
		courseName = sc.next();

		
		//get the starting time of the course
		System.out.println("Please enter the course start time eg. '19:00'");
		startTime = sc.next();

		
		//get the end time of the course
		System.out.println("Please enter the course end time eg. '20:00'");
		endTime = sc.next();

		//get the date of the course
		System.out.println("Please enter the course date eg. 'MW'");
		date = sc.next();

		
		//get the capacity of the course
		System.out.println("Please enter the course capacity. eg. '72'");
		capacity = sc.next();

		
		//get the lecturer info. Check if the lecturer exists, if not exist, add a new lecturer
		System.out.println("Please enter the course lecturer's id eg. '001'");
		lecturer = sc.next();
		if (!isInList(lecturer, prof, "ID")) {
			System.out.println("The professor isn't in the system, please add this professor first");
			if (!addNewProf(sc, prof)) return false;
		}
		//convert the professor's ID to professor's name
		User professor = null;
		for (User p : prof) {
			if (p.getID().equals(lecturer)) {
				professor = p;
			}
		}
		lecturer = professor.getName();
		//create a new instance of course use the given informations, add it to the course list
		
		Course newCourse = new Course(courseID, courseName, lecturer, date, startTime, endTime, capacity);
		
		for (Course c : professor.getCourseEnroll()) {
			if (checkTime(c, newCourse)) {
				System.out.println("There is a time conflict with course: " + c);
				System.out.println("Unable to add new course: " + newCourse);
				return false;
			}
		}
		course.add(newCourse);
		System.out.println("Successfully added the course: " + newCourse);
		
		return true;
	}
	
	/**
	 * This methods delete a course based on the user's input
	 * @param sc Scanner for user input
	 * @param course an ArrayList containing the information of courses
	 * @return true if the course is successfully deleted
	 */
	public static boolean deleteCourse(Scanner sc, ArrayList<Course> course) {
		//initialize a variables for storing the course ID
		String courseID;
		boolean Found = false;
		//keep asking for course ID if the course is not find in the system
		System.out.println("Please enter the id of the course you want to delete, or type 'q' to end. eg.'CIT590'");
		courseID = sc.next();

		if (courseID.equals("q")) return false;
		//iterate over the course list find the course that matches the course ID 
		for (Course c : course) {
			if (c.getCourseID().equals(courseID)) {
				//remove the course and print out the message if the course is find
				course.remove(c);
				System.out.println("Successfully deleted the course: " + c.getCourseID());
				//update the indicator and break the loop
				Found = true;
				break;
			}
		}
		
		if (!Found) {
			//if the course ID enter not exist, return to the last menu
			System.out.println("The course you enter: " + courseID + " is not found");
		}
		return true;
	}
	
	/**
	 * This method takes input and delete a user's profile from system based on the input
	 * @param sc Scanner for input
	 * @param prof an ArrayList containing all the information of the users
	 * @return true if the user is successfully deleted from the system
	 */
	public static boolean deleteUser(Scanner sc, ArrayList<User> userType, String user) {
		//initialize the indicator
		boolean Found = false;

		while (!Found){
			if (user.equals("student")) {	
				System.out.println("Please enter the id of the student you wnat to delete, or type 'q' to end. eg. '001'");				
				String ID = sc.next();
				if (!ID.equals("q")) {
					try
					{
						Integer.parseInt(ID);
						for (User u : userType) {
							//update the indicator, remove the object and printout the message if find the user
							if (u.getID().equals(ID)) {
								Found = true;
								userType.remove(u);
								System.out.println("Successfully deleted the student: " + u.getID() + " " + u.getName());
								return true;
							}
							
						}
						if (!Found) {
							System.out.println("Could not find matched ID record.");//if the id not exist, return to the last menu
							return false;
						}
						
					}catch (NumberFormatException e) {
						System.out.println("please enter a valid ID!");
					}
				}else {
					return false;
				}
			}else if (user.equals("professor"))
				System.out.println("Please enter the id of the professor you wnat to delete, or type 'q' to end. eg. '001'");			
				String ID = sc.next();
				if (!ID.equals("q")) {
					try
					{
						Integer.parseInt(ID);
						for (User u : userType) {
							//update the indicator, remove the object and printout the message if find the user
							if (u.getID().equals(ID)) {
								Found = true;
								userType.remove(u);
								System.out.println("Successfully deleted the professor: " + u.getID() + " " + u.getName());
								return true;
							}
							
						}
						if (!Found) {
							System.out.println("Could not find matched ID record.");//if the id not exist, return to the last menu
							return false;
						}
						
					}catch (NumberFormatException e) {
						System.out.println("please enter a valid ID!");
					}
				}else {
					return false;
				}
		}
		return true;
	}

	

	public static void main(String[] args) throws FileNotFoundException {

		
		//initialize the scanner and printout the welcome message and main menu
		Scanner sc = new Scanner(System.in);
		boolean inSystem = true;

		
		//Read the files and store the info from file to ArrayLists
		FileInfoReader fileRead = new FileInfoReader();
		ArrayList<Course> course = fileRead.readCourse();
		ArrayList<User> student = fileRead.readStudent();
		ArrayList<User> prof = fileRead.readProfessor(course);
		ArrayList<User> admin = fileRead.readAdmin();
		
		
		while (inSystem) {
			mainMenu();
			
			int userRole = inputValidation(sc, 1, 4);
			
			//student
			while (userRole == 1) {
				boolean inStudent = true;
				// enter user name and password, validate those inputs and find the corresponding account
				//initialize the indicators
				User studentProfile = userLogin(student, sc);
				if (studentProfile == null) break;
				
				while (inStudent) {
					studentProfile.printMenu();
					int userIn = inputValidation(sc, 1, 6);
					
					
					if (userIn == 1) {	//View all courses
						for (Course eachCourse : course) System.out.println(eachCourse);
						
					} else if (userIn == 2) {	//add course
						
						while (true) {
							//get student's input for course ID to enroll
							int index = -1;
							System.out.println("Please select the course ID you want to add to your list, eg. 'CIT590'");
							System.out.println("Or enter 'q' to return to the previous menu.");
							String courseInput = sc.next();
							
							//check if the user want to back to the previous page
							if (courseInput.equals("q")) break;
							else {
								//if not, iterate over the course list to find the course based on the user input
								boolean isFind = false;
								for (Course cor : course) {
									//record the course if find
									if (cor.getCourseID().equals(courseInput)) {
											index = course.indexOf(cor);
											isFind = true;
											break;
									}
								} 
								//if didn't find, printout the message and ask the user to enter again
								if (!isFind) {
									System.out.println("Invalid course ID. Please check and enter again.");
									continue;
								}
								
								Course select = course.get(index);
								//if the course is already in the user's course list, printout message and ask the user to enter again
								if (studentProfile.getCourseEnroll().contains(select)) {
									System.out.println("The course you selected is already in your list.");
									continue;
								}
								
								//if not in user's list then check if there is a conflict
								boolean isConflict = false;
								for (Course cor : studentProfile.getCourseEnroll()) {
									if (checkTime(cor, select)) isConflict = true;
								}
								//if no time conflict, add the course to the enroll course list.
								if (!isConflict) {
									//add this course to student's course list
									studentProfile.getCourseEnroll().add(select);
									//add student to the course's enrollment at the same time
									select.getEnrollment().add(studentProfile);
									System.out.println("Course added successfully");
									continue;
								} else System.out.println("There is a time conflict with the course you selected");
							}
						}
					
					} else if (userIn == 3) {	//view selected course
						//iterate over the class student enrolled in, and print out the courses
						studentProfile.printEroll();
						
					} else if (userIn == 4) {	//drop courses
					
						while (true) {
							//get student's input for course ID to enroll
							studentProfile.printEroll();
							System.out.println("Please select the course ID you want to drop from your list, eg. 'CIT591'");
							System.out.println("Or enter 'q' to return to the previous menu.");
							String courseEnter = sc.next();
							
							//check if the user want to back to the previous page
							if (courseEnter.equals("q")) break;
							else {
								//if not, iterate over the course list to find the course based on the user input
								boolean isFind = false;
								for (Course cor : studentProfile.getCourseEnroll()) {
									//remove the course if find
									if (cor.getCourseID().equals(courseEnter)) {
										studentProfile.getCourseEnroll().remove(cor);
										cor.getEnrollment().remove(studentProfile);
										System.out.println("Course dropped successfully");
										System.out.println();
										isFind = true;
										break;
									}
								} 
								//if didn't find, printout the message and ask the user to enter again
								if (!isFind) {
									System.out.println("The course is not in your schedule.");
									continue;
								}
							}
						}
						
					} else if (userIn == 5) {	//view grade
						System.out.println("Here are the courses you already taken, with your grade in a letter format");
						//iterate over the key sets containing all the courses the student took before
						for (String key : studentProfile.getCourseGrade().keySet()) {
							//take the course student took before and find that course in the system
							for (Course c : course) {
								if (key.equals(c.getCourseID())) System.out.println("Grade of " + c.getCourseID() + " " 
										+ c.getCourseName() + ": " + studentProfile.getCourseGrade().get(key));
							}
						}
						
					} else if (userIn == 6) {	//return to previous menu
						userRole = 0;
						break;
					}
				}
			}
			
			//professor
			while (userRole == 2) {
				//let user enter user name and password to login the corresponding professor account
				boolean inProf = true;
				User profProfile = userLogin(prof, sc);
				if (profProfile == null) break;
				
				//enter the professor system
				while (inProf) {
					//print out the professor's menu
					profProfile.printMenu();
					//get user's input to navigate through the menu
					int userIn = inputValidation(sc, 1, 3);
					
					if (userIn == 1) {	//view courses
						//print the course list
						profProfile.printEroll();
						
					} else if (userIn == 2) {	//review student list
						//print the course list first
						profProfile.printEroll();
						System.out.println("Please enter the course ID, eg. 'CIS519'.");
						System.out.println("Or type 'q' to quit.");
						//keep getting prof's input for course ID until valid
						while (true) {
							boolean isFound = false;
							String profIn = sc.next();
							if (profIn.equals("q")) break;
							//iterate over the course enroll list to see if the input match the course
							for (Course c : profProfile.getCourseEnroll()) {
								if (profIn.equals(c.getCourseID())) {
									//if match, then print out the enrollment of the course and break the loop
									System.out.println("Students in your course " + c.getCourseID() + " " + c.getCourseName() + ": ");
									for (User u : c.getEnrollment()) System.out.println(u);
									isFound = true;
									break;
								}
							}
							if (isFound) break;
							//if not found, print the message and ask for input again
							else System.out.println("Course not found. Please check your input.");
						}
						
					} else if (userIn == 3) {	//return the the previous menu
						userRole = 0;
						break;
					}
				}
			}
			
			
			//admin
			while (userRole == 3) {
				//let user enter user name and password to login the corresponding admin account
				boolean inAdmin = true;
				User adminProfile = userLogin(admin, sc);
				if (adminProfile == null) break;
				//adminProfile.printMenu();
				
				while(inAdmin) {
					adminProfile.printMenu();
					int userIn = inputValidation(sc, 1, 8);
					
					if (userIn == 1) {	//view all courses
						//iterate over the course list and printout each course
						for (Course eachCourse : course) System.out.println(eachCourse);
						
					} else if (userIn == 2) { //add new course
						addNewCourse(sc, course, prof);
						
					} else if (userIn == 3) { //delete course
						deleteCourse(sc, course);
						
					} else if (userIn == 4) { //add new professor
						addNewProf(sc, prof);
						
					} else if (userIn == 5) { //delete professor
						deleteUser(sc, prof, "professor");
						
					} else if (userIn == 6) { //add new student
						addStudent(sc, student, course);
						
					} else if (userIn == 7) { //delete student
						deleteUser(sc, student, "student");
						
					} else if (userIn == 8) { //return to previous menu
						userRole = 0;
						break;
					}
				}
			}
			
			
			if (userRole == 4) {	//quit the system
				System.out.println("Exit");
				inSystem = false;
				break;
			}
			
		}
		//close the Scanner
		sc.close();

	}

}
