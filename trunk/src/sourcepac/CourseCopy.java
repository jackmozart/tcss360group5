
package sourcepac;

public class CourseCopy extends Course{

  private static final String NO_TEACHER_ASSIGNED_MESSAGE = "No Teacher assigned";
  
  /**
   * Teacher of course.
   */
  private String my_teacher;
  
  /**
   * Time of the course.
   */
  private int my_time;
  
  /**
   * This constructor creates a course object from the given parameters.
   * 
   * @param the_course_title The Department and Code for the course
   * @param the_course_description The full name of the course
   * @param the_credit The credit worth of the course
   */
  public CourseCopy(String the_course_title, String the_course_description, int the_credit) {
    super(the_course_title, the_course_description, the_credit);
    my_teacher = NO_TEACHER_ASSIGNED_MESSAGE;
    my_time = 0;
  }

  /**
   * @return The course time.
   */
  public int getTime() {
    return my_time;
  }


}
