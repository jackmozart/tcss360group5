
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
  private List<CourseCopy> my_schedule_list_one;
  
  /**
   * A list of CourseCopy.
   */
  private List<CourseCopy> my_schedule_list_two;
  
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
    my_schedule_list_one = new ArrayList<CourseCopy>();
    my_schedule_list_one.add(new CourseCopy("TCSS 343", "A TCSS class", 5,
    		                                START_TIME_ONE, END_TIME_ONE, TEACHER_NAME_ONE, 1));
    my_schedule_list_one.add(new CourseCopy("TCSS 360", "A TCSS class", 5,
    		                                START_TIME_ONE, END_TIME_ONE, TEACHER_NAME_ONE, 1));
    my_schedule_list_two = new ArrayList<CourseCopy>();
    my_schedule_list_two.add(new CourseCopy("TCSS 343", "A TCSS class", 5,
    		                                START_TIME_ONE, END_TIME_ONE, TEACHER_NAME_ONE, 1));
    my_schedule_list_two.add(new CourseCopy("TCSS 360", "A TCSS class", 5,
    		                                10, 12, TEACHER_NAME_ONE, 1));
  }

  /**
   * Checks to see if any teachers are teaching at the same time on the same
   * day.
   */
  @Test
  public void teacherSameTimeTestShouldFail() {
    for (int i = 0; i < my_schedule_list_one.size(); i++) {
      for (int j = i + 1; j < my_schedule_list_one.size(); j++) {
        if (my_schedule_list_one.get(i).getTeacher().equals
          (my_schedule_list_one.get(j).getTeacher())) {
          if (my_schedule_list_one.get(i).getStartTime() ==
        		my_schedule_list_one.get(j).getStartTime() &&
                my_schedule_list_two.get(i).getDay() ==
                    my_schedule_list_two.get(j).getDay()) {
            fail("The teacher (teacher name here) is teaching" +
                " two classes at the same time on the same day.");
          }
        }
      }
    }
  }
  /**
   * Checks to see if any teachers are teaching at the same time on the same
   * day.
   */
  @Test
  public void teacherSameTimeTestShouldSuccess() {
    
    for (int i = 0; i < my_schedule_list_two.size(); i++) {
      for (int j = i + 1; j < my_schedule_list_two.size(); j++) {
        if (my_schedule_list_two.get(i).getTeacher()
        	.equals(my_schedule_list_two.get(j).getTeacher())) {
          if (my_schedule_list_two.get(i).getStartTime() ==
        	  my_schedule_list_two.get(j).getStartTime() &&
        	  my_schedule_list_two.get(i).getDay() ==
                  my_schedule_list_two.get(j).getDay()) {
            fail("The teacher " + my_schedule_list_two.get(i).getTeacher() +
            		" is teaching two classes at the same time on the same day.");
          }
        }
      }
    }
  }
}
