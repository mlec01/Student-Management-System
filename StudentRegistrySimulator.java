import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.io.IOException;

public class StudentRegistrySimulator {

	public static void main(String[] args) {
		try {
			Registry registry = new Registry();
			Scheduler schedule = new Scheduler(registry.courses);

			Scanner scanner = new Scanner(System.in);
			System.out.print(">");

			while (scanner.hasNextLine()) {
				String inputLine = scanner.nextLine();
				if (inputLine == null || inputLine.equals(""))
					continue;

				Scanner commandLine = new Scanner(inputLine);
				String command = commandLine.next();

				if (command == null || command.equals(""))
					continue;

				else if (command.equalsIgnoreCase("L") || command.equalsIgnoreCase("LIST")) {
					registry.printAllStudents();
				} else if (command.equalsIgnoreCase("Q") || command.equalsIgnoreCase("QUIT"))
					return;

				else if (command.equalsIgnoreCase("REG")) {
					// register a new student in registry
					// get name and student id string
					// e.g. reg JohnBoy 74345
					// Checks:
					// ensure name is all alphabetic characters
					// ensure id string is all numeric characters
					if (commandLine.hasNext()) // checks if there is something after the command
					{
						String name = commandLine.next();
						if (commandLine.hasNext()) // checks if there is something after the name
						{
							String id = commandLine.next();
							if (isStringOnlyAlphabet(name)) // checks if the name is all letters
							{
								if (isNumeric(id)) // checks if the id is all numbers
								{
									registry.addNewStudent(name, id);
								} else // error message if the id isn't all numbers
								{
									System.out.println("Invalid characters in ID " + id);
								}
							} else // error message if the name isn't all letters
							{
								System.out.println("Invalid characters in name " + name);
							}
						} else // error message if the user didn't enter the student's id
						{
							System.out.println("Please enter student's ID");
						}
					} else // error message if the user didn't enter the student's name
					{
						System.out.println("Please enter student's name");
					}
				} else if (command.equalsIgnoreCase("DEL")) {
					// delete a student from registry
					// get student id
					// ensure numeric
					// remove student from registry
					if (commandLine.hasNext()) // checks if there is something after the command
					{
						String id = commandLine.next();
						if (isNumeric(id)) // checks to see if the id is all numbers
						{
							registry.removeStudent(id);
						} else // error message if the id isn't all numbers
						{
							System.out.println("Invalid characters in ID " + id);
						}
					} else // error message if the user didn't enter student's id
					{
						System.out.println("Please enter student's ID");
					}
				}

				else if (command.equalsIgnoreCase("ADDC")) {
					// add a student to an active course
					// get student id and course code strings
					// add student to course (see class Registry)
					if (commandLine.hasNext()) // checks to see if there is something after the command
					{
						String id = commandLine.next();
						if (commandLine.hasNext()) // checks to see if there is something after the id
						{
							String code = commandLine.next();
							if (isNumeric(id)) // checks to see the id is all numbers
							{
								registry.addCourse(id, code);
							} else // error message if the id is not all numbers
							{
								System.out.println("Invalid characters in ID" + id);
							}
						} else // error message if the class isn't all letters
						{
							System.out.println("Please enter class course");
						}
					} else // error message if user didn't enter the student's id
					{
						System.out.println("Please enter student's ID");
					}

				} else if (command.equalsIgnoreCase("DROPC")) {
					// get student id and course code strings
					// drop student from course (see class Registry)
					if (commandLine.hasNext()) // checks to see if there is something after the command
					{
						String id = commandLine.next();
						if (commandLine.hasNext()) // checks to see if there is something after the id
						{
							String code = commandLine.next();
							if (isNumeric(id)) // checks to see if the id is all numbers
							{
								registry.dropCourse(id, code);
							} else // error message if the id isn't all numbers
							{
								System.out.println("Invalid characters in ID " + id);
							}
						} else // error message if user didn't enter course code
						{
							System.out.println("Please enter course code");

						}
					} else // error message if user didn't enter student's id
					{
						System.out.println("Please enter student's ID");
					}
				} else if (command.equalsIgnoreCase("PAC")) {
					// print all active courses
					registry.printActiveCourses();
				} else if (command.equalsIgnoreCase("PCL")) {
					// get course code string
					// print class list (i.e. students) for this course
					if (commandLine.hasNext()) // checks to see if there is something after the command
					{
						String code = commandLine.next();
						registry.printClassList(code);
					} else // error message if user didn't enter course code
					{
						System.out.println("Please enter course code");
					}
				} else if (command.equalsIgnoreCase("PGR")) {
					// get course code string
					// print name, id and grade of all students in active course
					if (commandLine.hasNext()) // checks to see if there is something after the command
					{
						String code = commandLine.next();
						registry.printGrades(code);
					} else // error message if user didn't enter course code
					{
						System.out.println("Please enter course code");
					}
				} else if (command.equalsIgnoreCase("PSC")) {
					// get student id string
					// print all credit courses of student
					if (commandLine.hasNext()) // checks to see if there is somethig after the command
					{
						String id = commandLine.next();
						if (isNumeric(id)) // checks to see if the id is all numbers
						{
							registry.printStudentCourses(id);
						} else // error message if the id isn't all numbers
						{
							System.out.println("Invalid characters in ID " + id);
						}
					} else // error message if user didn't enter student's id
					{
						System.out.println("Please enter student's ID");
					}
				} else if (command.equalsIgnoreCase("PST")) {
					// get student id string
					// print student transcript
					if (commandLine.hasNext()) // checks if there is something after the command
					{
						String id = commandLine.next();
						if (isNumeric(id)) // checks if the id is all numbers
						{
							registry.printStudentTranscript(id);
						} else // error message if id isn't all numbers
						{
							System.out.println("Invalid characters in ID " + id);
						}
					} else // error message if user didn't enter student's id
					{
						System.out.println("Please enter student's ID");
					}
				} else if (command.equalsIgnoreCase("SFG")) {
					// set final grade of student
					// get course code, student id, numeric grade
					// use registry to set final grade of this student (see class Registry)
					if (commandLine.hasNext()) // checks to see if there is something after the command
					{
						String code = commandLine.next();
						if (commandLine.hasNext()) // checks to see if there is something after the course code
						{
							String id = commandLine.next();
							if (commandLine.hasNext()) // checks to see if there is something after the student id
							{
								String grade = commandLine.next();
								double gradeDouble;
								if (isNumeric(id)) // checks if the id is all numbers
								{
									if (isNumeric(grade)) // checks if the grade is all numbers
									{
										gradeDouble = Double.parseDouble(grade);
										registry.setFinalGrade(code, id, gradeDouble);
									} else // error message if grade isn't all numbers
									{
										System.out.println("Invalid characters in grade " + grade);
									}
								} else // error message if id isn't all numbers
								{
									System.out.println("Invalid characters in ID " + id);
								}
							} else // error message if user didn't enter student's grade
							{
								System.out.println("Please enter student's grade");
							}
						} else // error message if user didn't enter student's id
						{
							System.out.println("Please enter student's ID");
						}
					} else // error message if user didn't enter course code
					{
						System.out.println("Please enter course code");
					}
				} else if (command.equalsIgnoreCase("SCN")) {
					// get course code
					// sort list of students in course by name (i.e. alphabetically)
					// see class Registry
					if (commandLine.hasNext()) // checks if there is something after the command
					{
						String code = commandLine.next();
						registry.sortCourseByName(code);

					} else // error message if user didn't enter the course code
					{
						System.out.println("Please enter course code");
					}
				} else if (command.equalsIgnoreCase("SCI")) {
					// get course code
					// sort list of students in course by student id
					// see class Registry
					if (commandLine.hasNext()) // checks if there is something after the command
					{
						String code = commandLine.next();
						registry.sortCourseById(code);
					} else // error message if user didn't enter the course code
					{
						System.out.println("Please enter course code");
					}
				} else if (command.equalsIgnoreCase("SCH")) {
					// schedules a course for a certain day, start time and duration
					if (commandLine.hasNext()) {
						// trys to set the day and time
						try {
							String code = commandLine.next();
							String day = commandLine.next();
							int start = Integer.parseInt(commandLine.next());
							int duration = Integer.parseInt(commandLine.next());
							schedule.setDayAndTime(code, day, start, duration);
						}
						// if there is any invalid input, exception is caught and error message is
						// printed
						catch (UnknownCourseException unknownCourse) {
							System.out.println("Unknown course");
						} catch (InvalidDayException invalidDay) {
							System.out.println("Invalid Lecture Day");
						} catch (InvalidTimeException invalidTime) {
							System.out.println("Invalid Lecture Start Time");
						} catch (InvalidDurationException invalidDuration) {
							System.out.println("Invalid Lecture Duration");
						} catch (LectureTimeCollisionException lectureCollision) {
							System.out.println("Lecture Time Collision");
						} catch (RuntimeException runTime) {
							System.out.println(
									"Please enter all necessary information: course code, day, time, duration");
						}

					}
				} else if (command.equalsIgnoreCase("CSCH")) {
					// clears the schedule for given courses
					try {
						String code = commandLine.next();
						schedule.clearSchedule(code);
					}
					// if courses was not entered, error message printed
					catch (RuntimeException runTime) {
						System.out.println("Please enter course");
					}
				} else if (command.equalsIgnoreCase("PSCH")) {
					// prints the entire schedule
					schedule.printSchedule();
				} else // error message if user entered an invalid command
				{
					System.out.println("Invalid command");

				}
				System.out.print("\n>");
			}
		}
		// catches any exceptions when trying to read the students.txt file
		catch (FileNotFoundException fileNotFound) {
			System.out.println("students.txt File Not Found");
		} catch (NoSuchElementException badFileFormat) {
			System.out.println("Bad File Format students.txt");
		} catch (IOException io) {
			System.out.println("IOException caught");
		}
	}

	private static boolean isStringOnlyAlphabet(String str) {
		// checks if string str contains only alphabetic characters
		str = str.toLowerCase();
		char[] charArray = str.toCharArray();
		for (int i = 0; i < charArray.length; i++) {
			char chCheck = charArray[i];
			if (!(chCheck >= 'a' && chCheck <= 'z')) {
				return false;
			}
		}
		return true;
	}

	public static boolean isNumeric(String str) {
		// checks if string str contains only numeric characters
		char[] charArray = str.toCharArray();
		for (int i = 0; i < charArray.length; i++) {
			char chCheck = charArray[i];
			if (!(chCheck >= '0' && chCheck <= '9')) {
				return false;
			}
		}
		return true;
	}

}