
package tests;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import sourcepac.CourseCopy;

/**
 * Tests the constraints to validate whether the teacher is teaching at the same
 * time on the same day.
 * 
 * @author Christian
 * @version 1.0
 */

public class NumberOneConstraintTest {
  /**
   * A list of CourseCopy.
   */
  private List<CourseCopy> my_schedule_list;
  
  /**
    * Start time at 8.
    */
  public static final int START_TIME_ONE = 8;
  
  /**
   * First teacher name.
   */
  public static final String TEACHER_NAME_ONE = "Tenenberg";
  
 /**
  * End time at 10.
  */
  public static final int END_TIME_ONE = 10;

  /**
   * Creates many CourseCopy to prepare for the testing.
   */
  @Before
  public void setUp() {
    my_schedule_list = new ArrayList<CourseCopy>();
    my_schedule_list.add(new CourseCopy("TCSS 343", TEACHER_NAME_ONE, START_TIME_ONE, 10));
    my_schedule_list.add(new CourseCopy("TCSS 360", TEACHER_NAME_ONE, START_TIME_ONE, 10));
  }

  /**
   * Checks to see if any teachers are teaching at the same time on the same
   * day.
   */
  @Test
  public void teacherSameTimeTest() {
    
    for (int i = 0; i < my_schedule_list.size(); i++) {
      for (int j = i + 1; j < my_schedule_list.size(); j++) {
        if (my_schedule_list.get(i) == my_schedule_list.get(j)) {
          fail("The teacher (teacher name here) is teaching" +
               " two classes at the same time on the same day.");
        }
      }
    }
  }
}
