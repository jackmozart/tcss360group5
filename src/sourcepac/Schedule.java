
package sourcepac;

import java.util.ArrayList;
import java.util.List;


/**
 * Stores courses together in a schedule for easier constraint
 * checking and output
 * @author Chris Davidson
 * @author Christian Tomyn
 * @version 5/22/2011
 *
 */
public class Schedule {

  private List<CourseCopy> my_courses;
  
  /** The Schedule's title, i.e.: "Autumn 2010" */
  private String my_title;
  
  /**
   * Creates an empty Schedule with no initial title.
   */
  public Schedule() {
    this(null);
  }
  
  /**
   * Creates an empty Schedule with a title.  Used to aid testing.
   * @param quarter The title for the Schedule.
   */
  public Schedule(String quarter) {
    my_courses = new ArrayList<CourseCopy>();
    my_title = quarter;
  }
  
  public List<CourseCopy> generateSchedule(final List<CourseCopy> a_course_copy)  {
    
    return getCourses();
  }
  
  /**
   * Sets the Schedule's title.  Replaces any previous title.
   * @param quarter The new title.
   */
  public void setTitle(String quarter) {
    my_title = quarter;
  }
  /**
   * Retrieves the Schedule's title.  Returns null if none exists.
   * @return A String representing the title.
   */
  public String getTitle() {
    return my_title;
  }
  
  /**
   * Adds a CourseCopy to the Schedule, maintaining the order entered. 
   * @param course The CourseCopy to be added.
   */
  public void addCourse(CourseCopy course) {
    my_courses.add(course);
  }

  /**
   * Retrieves the current CourseCopies in the Schedule, entry order
   * maintained.
   * @return A List of CourseCopies
   */
  public List<CourseCopy> getCourses() {
    return my_courses;
  }

}
