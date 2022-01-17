public class CreditCourse extends Course {
	private String semester;
	public double grade;
	public boolean active;

	// add a constructor method with appropriate parameters
	// should call the super class constructor
	public CreditCourse(String name, String code, String descr, String fmt, String semester, double grade) {
		super(name, code, descr, fmt);
		this.semester = semester;
		this.grade = grade;
	}

	public boolean getActive() {
		// returns the active variable
		return active;
	}

	public void setActive() {
		// set active variable as true
		active = true;
	}

	public void setInactive() {
		// set active variable as false
		active = false;
	}

	public String displayGrade() {
		// prints out the course code, course name, semester and grade of student
		return getCode() + " " + getName() + " " + semester + " Grade " + convertNumericGrade(grade);
	}

}