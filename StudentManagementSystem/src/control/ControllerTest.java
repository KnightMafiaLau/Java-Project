package control;
/**
 * @author zhiquan Liu
 */
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import courses.Course;
import files.FileInfoReader;
import roles.Professor;
import roles.Student;
import roles.User;

class ControllerTest {
	ArrayList<Course> course;
	ArrayList<User> prof, student, admin;
	Scanner sc = new Scanner(System.in);
	FileInfoReader fileRead;
	
	
	@BeforeEach
	void setUp() throws Exception {
		fileRead = new FileInfoReader();
		course = fileRead.readCourse();
		prof = fileRead.readProfessor(course);
		student = fileRead.readStudent();
		admin = fileRead.readAdmin();
	}

	@Test
	void testAddNewCourse() {
		Boolean isAdd = Controller.addNewCourse(sc, course, prof);
		//ID of Prof Smith is 002
		Course newCourse = new Course("MEAM111", "F1", "Harry Smith", "MW", "10:00", "20:00", "1");		
		if (isAdd) {
			assertEquals(
					course.get(course.size() - 1).getCourseID(), newCourse.getCourseID());
			assertEquals(course.get(course.size() - 1).getCourseName(), newCourse.getCourseName());
			assertEquals(course.get(course.size() - 1).getCapacity(), newCourse.getCapacity());
			assertEquals(course.get(course.size() - 1).getStartTime(), newCourse.getStartTime());
			assertEquals(course.get(course.size() - 1).getEndTime(), newCourse.getEndTime());
			assertEquals(course.get(course.size() - 1).getLecturer(), newCourse.getLecturer());
			assertEquals(course.get(course.size() - 1).getDate(), newCourse.getDate());
		}
	}
	
	@Test
	void testAddNewProf() {
		Boolean isAdd = Controller.addNewProf(sc, prof);
		Professor newProf = new Professor("111", "Charles Leclerc", "prof01", "qwe123");
		if (isAdd) {
			assertEquals(prof.get(prof.size() - 1).getID(), newProf.getID());
			assertEquals(prof.get(prof.size() - 1).getName(), newProf.getName());
			assertEquals(prof.get(prof.size() - 1).getUserName(), newProf.getUserName());
			assertEquals(prof.get(prof.size() - 1).getPassword(), newProf.getPassword());
		}
	}
	
	@Test
	void testAddStudent() {
		Boolean isAdd = Controller.addStudent(sc, student, course);
		HashMap<String, String> courseGrade = new HashMap<String, String>();
		courseGrade.put("CIT590", "A");
		courseGrade.put("CIT591", "A");
		User student1 = new Student("005", "Carl", "carl", "qwe123", courseGrade);
		
		if(isAdd) {
			assertEquals(student.get(student.size() - 1).getID(), student1.getID());
			assertEquals(student.get(student.size() - 1).getName(), student1.getName());
			assertEquals(student.get(student.size() - 1).getUserName(), student1.getUserName());
			assertEquals(student.get(student.size() - 1).getPassword(), student1.getPassword());
			assertEquals(student.get(student.size() - 1).getCourseGrade(), student1.getCourseGrade());
		}
		
		
		}
	
	@Test
	void testCheckTime() {
		Course course1 = new Course("MEAM111", "F1", "Harry Smith", "MW", "10:00", "20:00", "1");	
		Course course2 = new Course("MEAM112", "F1", "Harry Smith", "MW", "10:00", "20:00", "1");	
		Course course3 = new Course("MEAM113", "F1", "Harry Smith", "TF", "10:00", "20:00", "1");	
		assertTrue(Controller.checkTime(course1, course2));
		assertFalse(Controller.checkTime(course1, course3));
		assertFalse(Controller.checkTime(course2, course3));
	}
	
	@Test
	void testDeleteCourse() {
		Course courseDelete = course.get(0);
		assertTrue(course.contains(courseDelete));
		
		//delete course with ID CIT590
		Controller.deleteCourse(sc, course);
		assertFalse(course.contains(courseDelete));
	}
	
	@Test
	void testDeleteUser() {
		User stud1 = student.get(0);
		assertTrue(student.contains(stud1));
		
		Controller.deleteUser(sc, student, "student");
		assertFalse(student.contains(stud1));
		
		User prof1 = prof.get(0);
		assertTrue(prof.contains(prof1));
		
		//Greenburg 001
		Controller.deleteUser(sc, prof, "professor");
		assertFalse(prof.contains(prof1));
	}
	
	@Test 
	void testInputValidation() {
		int userIn = 2;
		
		//input 2
		int validate = Controller.inputValidation(sc, 1, 6);
		assertEquals(userIn, validate);
	}
	
	@Test
	void testIsInList() {
		//Variation 1, check ID or userName for User class
		String ID1 = "001";
		String ID2 = "112";
		String userName1 = "Smith";
		String userName2 = "carl";
		
		assertTrue(Controller.isInList(ID1, prof, "ID"));
		assertFalse(Controller.isInList(ID2, prof, "ID"));
		assertTrue(Controller.isInList(userName1, prof, "username"));
		assertFalse(Controller.isInList(userName2, prof, "username"));
		
		//Variation 2, check course ID for Course class
		String courseID1 = "CIT590";
		String courseID2 = "MEAM111";
		
		assertTrue(Controller.isInList(courseID1, course));
		assertFalse(Controller.isInList(courseID2, course));
		
		
		
	}
	
	@Test
	void testUserLogin() {
		//login as student
		User student1 = student.get(0);
		//login using username "testStudent01" and password "password590"
		User student2 = Controller.userLogin(student, sc);
		assertEquals(student1, student2);
		
		//login as professor
		User prof1 = prof.get(0);
		User prof2 = Controller.userLogin(prof, sc);
		assertEquals(prof1, prof2);
		
		//login as admin
		User admin1 = admin.get(0);
		User admin2 = Controller.userLogin(admin, sc);
		assertEquals(admin1, admin2);
		
	}

}
