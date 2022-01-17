import java.util.ArrayList;
import java.util.Set;
import java.util.TreeMap;
import java.io.IOException;

public class Scheduler {
	// In main() after you create a Registry object, create a Scheduler object and
	// pass in the students ArrayList/TreeMap
	// If you do not want to try using a Map then uncomment
	// the line below and comment out the TreeMap line

	// ArrayList<Student> students;

	TreeMap<String, ActiveCourse> schedule;

	public Scheduler(TreeMap<String, ActiveCourse> courses) {
		schedule = courses;
	}

	public void setDayAndTime(String courseCode, String day, int startTime, int duration) {
		// see assignment doc
		boolean courseFound = false; // variable to see if course is found
		boolean collision = false; // variable to see if collision occurs

		// checks to see if the course exists
		for (String code : schedule.keySet()) {
			if (code.equalsIgnoreCase(courseCode)) {
				courseFound = true;
			}
		}

		// throws exception if course is not found
		if (courseFound == false) {
			throw new UnknownCourseException();
		}

		// throws exception if day is invalid input
		if (!(day.equalsIgnoreCase("Mon") || day.equalsIgnoreCase("Tue") || day.equalsIgnoreCase("Wed")
				|| day.equalsIgnoreCase("Wed") || day.equalsIgnoreCase("Thur") || day.equalsIgnoreCase("Fri"))) {
			throw new InvalidDayException();
		}

		// throws exception if time is invalid input
		if (startTime < 800 || startTime > 1700) {
			throw new InvalidTimeException();
		}

		// throws exception if duration is less than 1 or more than 3
		if (duration < 1 || duration > 3) {
			throw new InvalidDurationException();
		}

		// goes through all courses in schedule
		for (String id : schedule.keySet()) {
			// checks to see if course is already added to schedule
			if (schedule.get(id).scheduleAdded == true) {
				// checks to see if it is the same day
				if (schedule.get(id).lectureDay.equalsIgnoreCase(day)) {
					int thisEndTime = startTime + (duration * 100); // end time of course wanting to be scheduled
					int otherEndTime = schedule.get(id).lectureStart + (schedule.get(id).lectureDuration * 100); // end
																													// time
																													// of
																													// other
																													// courses
																													// already
																													// scheduled

					if (schedule.get(id).lectureStart <= startTime && startTime <= otherEndTime) // if the start time is
																									// in between the
																									// start and end
																									// time of a
																									// scheduled course
					{
						// checks to see if the overlapping course is not the same course
						if (!(id.equalsIgnoreCase(courseCode))) {
							collision = true;
						}
					} else if (schedule.get(id).lectureStart <= thisEndTime && thisEndTime <= otherEndTime) // if the
																											// end time
																											// is in
																											// between
																											// the start
																											// and end
																											// time of a
																											// scheduled
																											// course
					{
						if (!(id.equalsIgnoreCase(courseCode))) {
							collision = true;
						}
					} else {
						collision = false;
					}
				}
			}

		}

		// throws exception if there is a collision
		if (collision) {
			throw new LectureTimeCollisionException();
		}

		// overwrite the course that might be already scheduled
		clearSchedule(courseCode);

		// set parameters in ActiveCourse class
		for (String key : schedule.keySet()) {
			if (key.equalsIgnoreCase(courseCode)) {
				schedule.get(key).scheduleAdded = true;
				schedule.get(key).lectureStart = startTime;
				schedule.get(key).lectureDuration = duration;
				schedule.get(key).lectureDay = day;
			}
		}
	}

	// clears schedule by setting everything to blank or 0
	public void clearSchedule(String courseCode) {
		ActiveCourse scheduleCourse = schedule.get(courseCode);
		if (scheduleCourse != null) {
			scheduleCourse.lectureDay = "";
			scheduleCourse.lectureStart = 0;
			scheduleCourse.lectureDuration = 0;
		}

	}

	// displays schedule like a calendar
	public void printSchedule() {
		// see assignment doc
		final int ROW = 10;
		final int COL = 6;
		int scheduleDay = 1;
		int scheduleHour = 1;
		int startTime = 0;
		int endTime = 0;
		String[][] schedulePrint = new String[ROW][COL];
		String[] days = { "Mon", "Tue", "Wed", "Thur", "Fri" };
		// sets the empty schedule
		schedulePrint[0][0] = "";
		schedulePrint[0][1] = "Mon";
		schedulePrint[0][2] = "Tue";
		schedulePrint[0][3] = "Wed";
		schedulePrint[0][4] = "Thur";
		schedulePrint[0][5] = "Fri";
		schedulePrint[1][0] = "0800";
		schedulePrint[2][0] = "0900";
		schedulePrint[3][0] = "1000";
		schedulePrint[4][0] = "1100";
		schedulePrint[5][0] = "1200";
		schedulePrint[6][0] = "1300";
		schedulePrint[7][0] = "1400";
		schedulePrint[8][0] = "1500";
		schedulePrint[9][0] = "1600";

		// sets the rest of the empty schedule to blank
		for (int i = 1; i < ROW; i++) {
			for (int j = 1; j < COL; j++) {
				schedulePrint[i][j] = "";
			}
		}

		// runs through each day
		for (String day : days) {
			// runs through each course in schedule
			for (String id : schedule.keySet()) {
				// if the course was added to schedule and the day of the courses equals the day
				// that is being checked
				// then the schedulePrint 2d array is updated to hold the course code for the
				// specific day and time
				if (schedule.get(id).scheduleAdded == true && schedule.get(id).lectureDay.equalsIgnoreCase(day)) {
					startTime = schedule.get(id).lectureStart;
					endTime = schedule.get(id).lectureStart + (schedule.get(id).lectureDuration * 100);

					for (int i = 800; i < 1700; i += 100) {
						if (i >= startTime && i < endTime) {
							schedulePrint[scheduleHour][scheduleDay] = id;
						}
						scheduleHour++; // increments the hour

					}
					scheduleHour = 1; // resets the hour for the next day
				}
			}
			scheduleDay++; // increments the day
		}

		// runs through the whole schedulePrint 2d array and prints out each block
		for (int i = 0; i < ROW; i++) {
			for (int j = 0; j < COL; j++) {
				System.out.print(schedulePrint[i][j] + "\t");
			}
			System.out.println("\n");
		}
	}

}
