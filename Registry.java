import java.util.ArrayList;
import java.util.Collections;
import java.util.Map;
import java.util.*;
import java.io.*;

public class Registry {
	private TreeMap<String, Student> students = new TreeMap<String, Student>();
	public TreeMap<String, ActiveCourse> courses = new TreeMap<String, ActiveCourse>(String.CASE_INSENSITIVE_ORDER);

	public Registry() throws IOException {
		Scanner input = new Scanner(new File("students.txt"));
		String name = "";
		String id = "";
		// scan the file for input
		while (input.hasNext()) {
			name = input.next();
			id = input.next();
			Student student = new Student(name, id);
			students.put(id, student);
		}

		ArrayList<Student> list = new ArrayList<Student>();
		int count = 1;

		// CPS209
		String courseName = "Computer Science II";
		String courseCode = "CPS209";
		String descr = "Learn how to write complex programs!";
		String format = "3Lec 2Lab";

		// add students to course
		for (String key : students.keySet()) {
			if (count >= 2 && count <= 4) {
				list.add(students.get(key));
				// Add course to student list of courses
				students.get(key).addCourse(courseName, courseCode, descr, format, "W2020", 0);
			}
			count++;
		}

		courses.put(courseCode, new ActiveCourse(courseName, courseCode, descr, format, "W2020", list));

		// CPS511
		list.clear();
		count = 1;
		courseName = "Computer Graphics";
		courseCode = "CPS511";
		descr = "Learn how to write cool graphics programs";
		format = "3Lec";

		// add students to course
		for (String key : students.keySet()) {
			if (count == 1 || count == 5 || count == 6) {
				list.add(students.get(key));
				// Add course to student list of courses
				students.get(key).addCourse(courseName, courseCode, descr, format, "F2020", 0);
			}
			count++;
		}

		courses.put(courseCode, new ActiveCourse(courseName, courseCode, descr, format, "F2020", list));

		// CPS643
		list.clear();
		count = 1;
		courseName = "Virtual Reality";
		courseCode = "CPS643";
		descr = "Learn how to write extremely cool virtual reality programs";
		format = "3Lec 2Lab";

		// add students to course
		for (String key : students.keySet()) {
			if (count == 1 || count == 2 || count == 4 || count == 6) {
				list.add(students.get(key));
				// Add course to student list of courses
				students.get(key).addCourse(courseName, courseCode, descr, format, "W2020", 0);
			}
			count++;
		}

		courses.put(courseCode, new ActiveCourse(courseName, courseCode, descr, format, "W2020", list));

		// CPS706
		list.clear();
		courseName = "Computer Networks";
		courseCode = "CPS706";
		descr = "Learn about Computer Networking";
		format = "3Lec 1Lab";

		courses.put(courseCode, new ActiveCourse(courseName, courseCode, descr, format, "W2020", list));

		// CPS616
		list.clear();
		courseName = "Algorithms";
		courseCode = "CPS616";
		descr = "Learn about Algorithms";
		format = "3Lec 1Lab";

		courses.put(courseCode, new ActiveCourse(courseName, courseCode, descr, format, "W2020", list));
	}

	// Add new student to the registry (students arraylist above)
	public boolean addNewStudent(String name, String id) {
		// Create a new student object
		// check to ensure student is not already in registry
		// if not, add them and return true, otherwise return false
		// make use of equals method in class Student
		if (students.containsKey(id)) {
			System.out.println("Student " + name + " has already registered");
			return false;
		}
		Student newStudent = new Student(name, id);
		students.put(id, newStudent);
		return true;

	}

	// Remove student from registry
	public boolean removeStudent(String studentId) {
		if (students.containsKey(studentId)) {
			students.remove(studentId);
			return true;
		}
		// Find student in students TreeMap
		// If found, remove this student and return true
		return false;
	}

	// Print all registered students
	public void printAllStudents() {
		for (String id : students.keySet()) {
			System.out.println("ID: " + id + " Name: " + students.get(id).getName());
		}

	}

	// Given a studentId and a course code, add student to the active course
	public void addCourse(String studentId, String courseCode) {
		// Find student object in registry (i.e. students arraylist)
		// Check if student has already taken this course in the past Hint: look at
		// their credit course list
		// If not, then find the active course in courses array list using course code
		// If active course found then check to see if student already enrolled in this
		// course
		// If not already enrolled
		// add student to the active course
		// add course to student list of credit courses with initial grade of 0
		Integer creditCourseIndex = null; // holds the index of the creditCourse being searched
		// checks to see if the student exists
		if (students.containsKey(studentId)) {
			Student studentObject = students.get(studentId);
			// runs through the student's courses
			for (int i = 0; i < studentObject.courses.size(); i++) {
				if (studentObject.courses.get(i).getCode().equalsIgnoreCase(courseCode)) {
					creditCourseIndex = i;
				}
			}
			// if the courses is not in the student's list, add the course
			if (creditCourseIndex == null) {
				if (courses.containsKey(courseCode)) {
					ActiveCourse activeCourseObject = courses.get(courseCode);
					activeCourseObject.students.add(studentObject);
					String name = activeCourseObject.getName();
					String code = activeCourseObject.getCode();
					String description = activeCourseObject.getDescription();
					String format = activeCourseObject.getFormat();
					String sem = activeCourseObject.getSemester();
					studentObject.addCourse(name, code, description, format, sem, 0.0);

				}
			} else {
				System.out.println("Student " + studentId + " is already enrolled in this class");
			}
		} else {
			System.out.println("Please register student first");
		}
	}

	// Given a studentId and a course code, drop student from the active course
	public void dropCourse(String studentId, String courseCode) {
		// Find the active course
		// Find the student in the list of students for this course
		// If student found:
		// remove the student from the active course
		// remove the credit course from the student's list of credit courses
		Integer studentIndex = null; // holds the index of where the student is in students arrayList
		// checks to see if the course exists
		if (courses.containsKey(courseCode)) {
			ActiveCourse activeCourseObject = courses.get(courseCode);
			// runs through all the students enrolled in courses
			for (int i = 0; i < activeCourseObject.students.size(); i++) {
				if (activeCourseObject.students.get(i).getId().equals(studentId)) {
					studentIndex = i;
				}
			}
			// if the student is found, removes the courses
			if (studentIndex != null) {
				Student studentObject = activeCourseObject.students.get(studentIndex);
				studentObject.removeActiveCourse(courseCode);
				activeCourseObject.students.remove(studentObject);
			} else {
				System.out.println("Student is not enrolled in this course");
			}
		} else {
			System.out.println("Could not find the active course " + courseCode);
		}
	}

	// Print all active courses
	public void printActiveCourses() {
		for (String courseId : courses.keySet()) {
			ActiveCourse ac = courses.get(courseId);
			System.out.println(ac.getDescription() + "\n");
		}
	}

	// Print the list of students in an active course
	public void printClassList(String courseCode) {
		// if course is found, print class list
		if (courses.containsKey(courseCode)) {
			courses.get(courseCode).printClassList();
		}
		// if course is not found, print error message
		else {
			System.out.println("Course not found");
		}
	}

	// Given a course code, find course and sort class list by student name
	public void sortCourseByName(String courseCode) {
		// if course is found, sort by name
		if (courses.containsKey(courseCode)) {
			courses.get(courseCode).sortByName();
		}
		// if course is not found, print error message
		else {
			System.out.println("Course is not found");
		}
	}

	// Given a course code, find course and sort class list by student name
	public void sortCourseById(String courseCode) {
		// if course is found, sorts by ID
		if (courses.containsKey(courseCode)) {
			courses.get(courseCode).sortById();
		}
		// if course is not found, prints error message
		else {
			System.out.println("Course is not found");
		}
	}

	// Given a course code, find course and print student names and grades
	public void printGrades(String courseCode) {
		// if course is found, print student's grades
		if (courses.containsKey(courseCode)) {
			courses.get(courseCode).printGrades();
		}
		// if course is not found, print error message
		else {
			System.out.println("Course not found");
		}
	}

	// Given a studentId, print all active courses of student
	public void printStudentCourses(String studentId) {
		// if student exists, active courses is printed
		if (students.containsKey(studentId)) {
			students.get(studentId).printActiveCourses();
		} else {
			System.out.println("Student is not registered");
		}
	}

	// Given a studentId, print all completed courses and grades of student
	public void printStudentTranscript(String studentId) {
		// if the student exists, transcript is printed
		if (students.containsKey(studentId)) {
			students.get(studentId).printTranscript();
		}
		// if student is not found, error message printed
		else {
			System.out.println("Student is not registered");
		}
	}

	// Given a course code, student id and numeric grade
	// set the final grade of the student
	public void setFinalGrade(String courseCode, String studentId, double grade) {
		// find the active course
		// If found, find the student in class list
		// then search student credit course list in student object and find course
		// set the grade in credit course and set credit course inactive
		Integer courseIndex = null; // holds the index of the course being searched

		// checks the TreeMap to see if student is found
		if (students.containsKey(studentId)) {
			Student studentObject = students.get(studentId);
			// goes through all the courses of the student
			for (int i = 0; i < studentObject.courses.size(); i++) {
				if (studentObject.courses.get(i).getCode().equalsIgnoreCase(courseCode)) {
					courseIndex = i;
				}
			}
			// if the course is found, final grade is set
			if (courseIndex != null) {
				CreditCourse courseObject = studentObject.courses.get(courseIndex);
				courseObject.grade = grade;
				courseObject.setInactive();
			}
			// if the course is not found, error message printed
			else {
				System.out.println("Student is not enrolled in course " + courseCode);
			}
		}
		// if the student is not found, error message printed
		else {
			System.out.println("Student is not registered");
		}

	}

}
