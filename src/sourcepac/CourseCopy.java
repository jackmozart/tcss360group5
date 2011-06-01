
package sourcepac;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Course copy stores a inputed course assigned to a teacher and time.  
 * 
 * @author Steven Cozart, Chris Davidson (constructor changes)
 * @author Phillip Bernard setBlock()
 * 
 * @version 2.1 6/1/2011
 * @version 1.0 5/16/2011
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
  private Time my_time;
  
  /**
   * Day of the course.
   */
  private boolean[] my_days;

  /**
   * The time block assigned to this course.
   */
  private int my_block;
  
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
    my_time = new Time(0,0);
    my_days = new boolean[7];
    setBlock();
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
    my_time = new Time(the_start, the_end);
    my_days = new boolean[7];
    setBlock();
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
                    int the_start, int the_end, String the_teacher, boolean[] the_days) {
    super(the_course_title, the_course_description, the_credit);
    my_section = the_course_section;
    my_teacher = the_teacher;
    my_time = new Time(the_start, the_end);
    my_days = the_days;
    setBlock();
  }


  /**
   * @return The course start time.
   */
  public Time getTime() {
    return my_time;
  }
  
  /**
   * 
   * @return Time block assigned to this course.
   */
  public int getBlockNum(){
    return my_block;
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
  public boolean[] getDays() {
    return my_days;
  }

  public void setBlock() {
    int block = 0;
    switch(my_time.getStartTime()) {
      case 800:
        block = 0;
        break;
      case 1020:
        block = 1;
        break;
      case 1330:
        block = 2;
        break;
      case 1615:
        block = 3;
        break;
      case 1835:
        block = 4;
        break;
      default:
        block = -1;
    }
    my_block = block;
  }

}
