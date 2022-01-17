import java.util.ArrayList;

// Make class Student implement the Comparable interface
// Two student objects should be compared by their name
public class Student implements Comparable<Student> {
  private String name;
  private String id;
  public ArrayList<CreditCourse> courses;

  public Student(String name, String id) {
    this.name = name;
    this.id = id;
    courses = new ArrayList<CreditCourse>();
  }

  public String getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  // add a credit course to list of courses for this student
  public void addCourse(String courseName, String courseCode, String descr, String format, String sem, double grade) {
    // create a CreditCourse object
    // set course active
    // add to courses array list
    CreditCourse newCourse = new CreditCourse(courseName, courseCode, descr, format, sem, grade);
    newCourse.setActive();
    courses.add(newCourse);

  }

  // Prints a student transcript
  // Prints all completed (i.e. non active) courses for this student (course code,
  // course name,
  // semester, letter grade
  // see class CreditCourse for useful methods
  public void printTranscript() {
    for (int i = 0; i < courses.size(); i++) {
      System.out.println(courses.get(i).displayGrade());

    }
  }

  // Prints all active courses this student is enrolled in
  // see variable active in class CreditCourse
  public void printActiveCourses() {
    for (int i = 0; i < courses.size(); i++) {
      if (courses.get(i).active) {
        System.out.println(courses.get(i).getDescription());
      }
    }
  }

  // Drop a course (given by courseCode)
  // Find the credit course in courses arraylist above and remove it
  // only remove it if it is an active course
  public void removeActiveCourse(String courseCode) {
    for (int i = 0; i < courses.size(); i++) {
      if (courses.get(i).getCode().equalsIgnoreCase(courseCode)) {
        if (courses.get(i).active) {
          courses.remove(i);
        }
      }
    }
  }

  public String toString() {
    return "Student ID: " + id + " Name: " + name;
  }

  // override equals method inherited from superclass Object
  // if student names are equal *and* student ids are equal (of "this" student
  // and "other" student) then return true
  // otherwise return false
  // Hint: you will need to cast other parameter to a local Student reference
  // variable
  public boolean equals(Object other) {
    if (this.name.equals(((Student) other).name)) {
      if (this.id.equals(((Student) other).id)) {
        return true;
      } else {
        return false;
      }
    }
    return false;
  }

  // override compareTo method inherited from Comparable interface
  // compares the names of two students
  // if the name of this student comes before otherStudent then returns 1
  // if the name of this student comes after otherStudent then returns -1
  // if the names are the same returns 0
  public int compareTo(Student otherStudent) {
    if (name.compareTo(otherStudent.name) > 0)
      return 1;
    else if (name.compareTo(otherStudent.name) < 0)
      return -1;
    else
      return 0;
  }

}
