import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

// Active University Course

public class ActiveCourse extends Course {
   public ArrayList<Student> students; // changed to private from public
   private String semester;
   public int lectureStart;
   public int lectureDuration;
   public String lectureDay;
   public boolean scheduleAdded = false; // added variable to check if course is added to schedule

   // Add a constructor method with appropriate parameters
   // should call super class constructor to initialize inherited variables
   // make sure to *copy* students array list being passed in into new arraylist of
   // students
   // see class Registry to see how an ActiveCourse object is created and used
   public ActiveCourse(String name, String code, String descr, String fmt, String semester,
         ArrayList<Student> students) {
      super(name, code, descr, fmt);
      this.semester = semester;
      this.students = new ArrayList<Student>(students);
   }

   public String getSemester() {
      // returns the semester of course
      return semester;
   }

   // Prints the students in this course (name and student id)
   public void printClassList() {
      for (int i = 0; i < students.size(); i++) {
         System.out.println("Student ID: " + students.get(i).getId() + " Name: " + students.get(i).getName());
      }
   }

   // Prints the grade of each student in this course (along with name and student
   // id)
   public void printGrades() {
      for (int i = 0; i < students.size(); i++) {
         System.out.println(
               students.get(i).getId() + " " + students.get(i).getName() + " " + getGrade(students.get(i).getId()));
      }
   }

   // Returns the numeric grade in this course for this student
   // If student not found in course, return 0
   public double getGrade(String studentId) {
      // Search the student's list of credit courses to find the course code that
      // matches this active course
      // see class Student method getGrade()
      // return the grade stored in the credit course object
      Student studentfind = null; // holds the student object that belongs to the studentId
      for (int i = 0; i < students.size(); i++) {
         if (students.get(i).getId().equals(studentId)) {
            studentfind = students.get(i);
         }
      }
      int size = studentfind.courses.size(); // size variable to hold the size of the courses arrayList
      for (int i = 0; i < size; i++) {
         if (studentfind.courses.get(i).getCode().equals(this.getCode())) {
            return studentfind.courses.get(i).grade;

         }
      }
      return 0;
   }

   // Returns a String containing the course information as well as the semester
   // and the number of students
   // enrolled in the course
   // must override method in the superclass Course and use super class method
   // getDescription()
   public String getDescription() {
      return super.getDescription() + " " + semester + " Enrolment: " + students.size();
   }

   // Sort the students in the course by name using the Collections.sort() method
   // with appropriate arguments
   // Make use of a private Comparator class below
   public void sortByName() {
      Collections.sort(students, new NameComparator());
   }

   // Fill in the class so that this class implement the Comparator interface
   // This class is used to compare two Student objects based on student name
   private class NameComparator implements Comparator<Student> {
      public int compare(Student student1, Student student2) {
         return student1.getName().compareTo(student2.getName());
      }
   }

   // Sort the students in the course by student id using the Collections.sort()
   // method with appropriate arguments
   // Make use of a private Comparator class below
   public void sortById() {
      Collections.sort(students, new IdComparator());
   }

   // Fill in the class so that this class implement the Comparator interface
   // This class is used to compare two Student objects based on student id
   private class IdComparator implements Comparator<Student> {
      public int compare(Student student1, Student student2) {
         return student1.getId().compareTo(student2.getId());
      }
   }
}
