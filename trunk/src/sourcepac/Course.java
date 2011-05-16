
package sourcepac;

/**
 * This class holds the basic information about a course including the Course
 * title course description, and the course's credit worth.
 * 
 * @author Phillip Bernard
 * @version 5/12/2011
 */
public class Course {
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
   * 
   * @param the_course_title The Department and Code for the course
   * @param the_course_description The full name of the course
   * @param the_credit The credit worth of the course
   */
  public Course(String the_course_title, String the_course_description, int the_credit) {
    my_course_title = the_course_title;
    my_course_description = the_course_description;
    my_credit = the_credit;
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
}
