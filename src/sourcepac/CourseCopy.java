
package sourcepac;

/**
 * Course copy stores a inputed course assigned to a teacher and time.  
 * @author Steven Cozart, Chris Davidson (constructor changes)
 * @version 1.0 5/16/11
 */
public class CourseCopy extends Course {

  /**
   * Default message if no teacher is assigned. 
   */
  private static final String NO_TEACHER_ASSIGNED_MESSAGE = "No Teacher assigned";
  
  /**
   * Teacher of course.
   */
  private String my_teacher;
  
  /**
   * The section letter
   */
  private String my_section;
  
  /**
   * Time of the start course.
   */
  private int my_start_time;
  
  /**
   * Time of the course.
   */
  private int my_end_time;
  
  /**
   * Day of the course.
   */
  private int my_day;
  
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
    my_section = "A";
    my_start_time = 0;
    my_end_time = 0;
    my_day = 0;
  }

  /**
   * This constructor creates a course object from the given parameters.
   * 
   * @param the_course_title The Department and Code for the course
   * @param the_course_description The full name of the course
   * @param the_credit The credit worth of the course
   * @param the_start The start time of the course
   * @param the_end The end time of the course.
   */
  public CourseCopy(String the_course_title, String the_course_description, int the_credit,
                    int the_start, int the_end) {
    super(the_course_title, the_course_description, the_credit);
    my_teacher = NO_TEACHER_ASSIGNED_MESSAGE;
    my_section = "A";
    my_start_time = the_start;
    my_end_time = the_end;
    my_day = 0;
  }

  /**
   * This constructor creates a course object from the given parameters.
   * 
   * @param the_course_title The Department and Code for the course
   * @param the_course_section The section for this course copy
   * @param the_course_description The full name of the course
   * @param the_credit The credit worth of the course
   * @param the_start The start time of the course
   * @param the_end The end time of the course.
   * @param the_teacher The name of the teacher. 
   * @param the_day The day of the class. 
   */
  public CourseCopy(String the_course_title, String the_course_section, 
                    String the_course_description, int the_credit,
                    int the_start, int the_end, String the_teacher, int the_day) {
    super(the_course_title, the_course_description, the_credit);
    my_section = the_course_section;
    my_teacher = the_teacher;
    my_start_time = the_start;
    my_end_time = the_end;
    my_day = the_day;
  }


  /**
   * @return The course start time.
   */
  public int getStartTime() {
    return my_start_time;
  }
  
  /**
   * @return The course end time.
   */
  public int getEndTime() {
    return my_end_time;
  }
  
  /**
   * @return The teacher of the course.
   */
  public String getTeacher() {
    return my_teacher;
  }
  
  /**
   * @return The day course will be taught.
   */
  public int getDay() {
    return my_day;
  }


}
