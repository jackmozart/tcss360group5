
package tests;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import sourcepac.Constraints;
import sourcepac.CourseCopy;

/**
 * Tests the constraints to validate whether the teacher is teaching at the same
 * time on the same day.
 * 
 * @author Christian, Chris (minor edits to setUp)
 * @version 1.0
 */

public class NumberOneConstraintTest {
 /**
  * Start time at 8.
  */
  public static final int START_TIME_ONE = 800;

 /**
  * First teacher name.
  */
  public static final String TEACHER_NAME_ONE = "Tenenberg";

 /**
  * End time at 10.
  */
  public static final int END_TIME_ONE = 1000;

  /**
   * A list of CourseCopy.
   */
  private List<CourseCopy> my_schedule_list_one;

  /**
   * A list of CourseCopy.
   */
  private List<CourseCopy> my_schedule_list_two;


  /**
   * Creates many CourseCopy to prepare for the testing.
   */
  @Before
  public void setUp() {
    my_schedule_list_one = new ArrayList<CourseCopy>();
    my_schedule_list_one.add(new CourseCopy("TCSS 343", "A", "A TCSS class", 5,
                         START_TIME_ONE, END_TIME_ONE, TEACHER_NAME_ONE, new boolean[7]));
    my_schedule_list_one.add(new CourseCopy("TCSS 360", "B", "A TCSS class", 5,
                         START_TIME_ONE, END_TIME_ONE, TEACHER_NAME_ONE, new boolean[7]));
    my_schedule_list_two = new ArrayList<CourseCopy>();
    my_schedule_list_two.add(new CourseCopy("TCSS 343", "A", "A TCSS class", 5,
                         START_TIME_ONE, END_TIME_ONE, TEACHER_NAME_ONE, new boolean[7]));
    my_schedule_list_two.add(new CourseCopy("TCSS 360", "B",  "A TCSS class", 5,
                         10, 12, TEACHER_NAME_ONE, new boolean[7]));
  }

  /**
   * Checks to see if any teachers are teaching at the same time on the same
   * day when the times are same.
   */
  @Test
  public void teacherSameTimeTestShouldFail() {
    for (int i = 0; i < my_schedule_list_one.size(); i++) {
      for (int j = i + 1; j < my_schedule_list_one.size(); j++) {
        if (my_schedule_list_one.get(i).getTeacher().equals
           (my_schedule_list_one.get(j).getTeacher())) {
          if (my_schedule_list_one.get(i).getTime().getStartTime() ==
              my_schedule_list_one.get(j).getTime().getStartTime() &&
              my_schedule_list_two.get(i).getDays() ==
                    my_schedule_list_two.get(j).getDays()) {
            fail("The teacher (teacher name here) is teaching" +
                " two classes at the same time on the same day.");
          }
        }
      }
    }
  }
  
  /**
   * Checks to see if any teachers are teaching at the same time on the same
   * day when the times are different.
   */
  @Test
  public void teacherSameTimeTestShouldSuccess() {
    for (int i = 0; i < my_schedule_list_two.size(); i++) {
      for (int j = i + 1; j < my_schedule_list_two.size(); j++) {
        if (my_schedule_list_two.get(i).getTeacher()
        	.equals(my_schedule_list_two.get(j).getTeacher())) {
          if (my_schedule_list_two.get(i).getTime().getStartTime() ==
        	  my_schedule_list_two.get(j).getTime().getStartTime() &&
        	  my_schedule_list_two.get(i).getDays() ==
              my_schedule_list_two.get(j).getDays()) {
            fail("The teacher " + my_schedule_list_two.get(i).getTeacher() +
            		" is teaching two classes at the same time on the same day.");
          }
        }
      }
    }
  }
  /**
   * Checks the constraints class to see if it actually checks to see if the
   * teacher doesn't teach at the same time on the same day.
   */
  @Test
  public void teacherSameTimeConstraintTest() {
    final Constraints constraint = new Constraints(my_schedule_list_one, null,
			  null, null);
    assertFalse(("Teacher " + "" + "is teaching two classes at the same time").equals
      (constraint.teacherSameTimes()));
  }
}