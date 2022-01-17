public class Course {
	private String code;
	private String name;
	private String description;
	private String format;

	public Course() {
		this.code = "";
		this.name = "";
		this.description = "";
		this.format = "";
	}

	public Course(String name, String code, String descr, String fmt) {
		this.code = code;
		this.name = name;
		this.description = descr;
		this.format = fmt;
	}

	public String getCode() {
		return code;
	}

	public String getName() {
		return name;
	}

	public String getFormat() {
		return format;
	}

	public String getDescription() {
		return code + " - " + name + "\n" + description + "\n" + format;
	}

	public String getInfo() {
		return code + " - " + name;
	}

	// static method to convert numeric score to letter grade string
	// e.g. 91 --> "A+"
	public static String convertNumericGrade(double score) {
		// based off Ryerson Grade scale
		if (score >= 90.00 && score <= 100.00) {
			return "A+";
		} else if (score >= 85.00 && score <= 89.99) {
			return "A";
		} else if (score >= 80.00 && score <= 84.99) {
			return "A-";
		} else if (score >= 77.00 && score <= 79.99) {
			return "B+";
		} else if (score >= 73.00 && score <= 76.99) {
			return "B";
		} else if (score >= 70.00 && score <= 72.99) {
			return "B-";
		} else if (score >= 67.00 && score <= 69.99) {
			return "C+";
		} else if (score >= 63.00 && score <= 66.99) {
			return "C";
		} else if (score >= 60.00 && score <= 62.99) {
			return "C-";
		} else if (score >= 57.00 && score <= 59.99) {
			return "D+";
		} else if (score >= 53.00 && score <= 56.99) {
			return "D";
		} else if (score >= 50.00 && score <= 52.99) {
			return "D-";
		} else {
			return "F";
		}
	}

}
