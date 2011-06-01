
package sourcepac;


/**
 * This class holds the basic information about a course including the Course
 * title course description, and the course's credit worth.
 * 
 * @author Phillip Bernard
 * @version 1.1 5/25/2011 Added compareTo
 * @version 1.0 5/12/2011
 */
public class Course implements Comparable{
  /**
   * This field holds the the course title which consists of the Department and
   * level number and should be formatted like this: TCSS 360.
   */
  private String my_course_title;
  /**
   * This field holds the course description; the full name of the course, such
   * as: Software Development and Quality Assurance Techniques.
   */
  private String my_course_description;
  /**
   * This field stores the amount of credit the course is worth.
   */
  private int my_credit;

  /**
   * This constructor creates a course object from the given parameters.
   * @author Steven Cozart 
   * @param the_course_title The Department and Code for the course
   * @param the_course_description The full name of the course
   * @param the_credit The credit worth of the course
   */
  public Course(String the_course_title, String the_course_description, int the_credit) {
    my_course_title = the_course_title;
    my_course_description = the_course_description;
    if (the_credit < 0) {
      throw new IllegalArgumentException();
    } else {
      my_credit = the_credit;
    }
  }

  /**
   * This method returns the course title.
   * 
   * @return The course title
   */
  public String getCourseTitle() {
    return my_course_title;
  }

  /**
   * This method returns the course description.
   * 
   * @return The course description
   */
  public String getCourseDescription() {
    return my_course_description;
  }

  /**
   * This method returns the amount of credit the course is worth.
   * 
   * @return The course credit worth
   */
  public int getCredit() {
    return my_credit;
  }

  /**
   * This method returns a string representation of the course including the
   * course title course description and course credit.
   * 
   * @return A String representation of the course
   */
  public String toString() {
    return my_course_title + " " + my_course_description + " " + my_credit;
  }

  /**
   * 
   */
  public int compareTo(Object other_object) {
    int result = 1;
    if(this == other_object){
      result = 0;
    }else if (other_object != null && other_object.getClass() == getClass()){
    Course other_course = (Course) other_object;
      if(my_course_title.equals(other_course.my_course_title) &&
         my_course_description.equals(other_course.my_course_description) &&
         my_credit == other_course.my_credit) {
        result = 0;
      } else {
        result = my_course_title.compareTo(other_course.my_course_title) +
          my_course_description.compareTo(other_course.my_course_description) +
          my_credit - other_course.my_credit;
      }
    }
    return result;
  }
  
  /**
   * {@inheritDoc}
   */
  public boolean equals(Object other) {
    boolean result = false;
    if (compareTo(other) == 0) {
      result = true;
    }
    return result;
  }
}
