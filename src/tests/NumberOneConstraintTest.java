
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
	 * A list of CourseCopy
	 */
	List<CourseCopy> scheduleList;
  /**
   * Creates many CourseCopy to prepare for the testing.
   */
  @Before
  public void setUp() {
	  scheduleList = new ArrayList<CourseCopy>();
  }

  /**
   * Checks to see if any teachers are teaching at the same time on the same
   * day.
   */
  @Test
  public void teacherSameTimeTest() {
    
    for (int i = 0; i < scheduleList.size(); i++) {
      for (int j = i + 1; j < scheduleList.size(); j++) {
        if (scheduleList.get(i) == scheduleList.get(j)) {
          fail("The teacher (teacher name here) is teaching two classes at the same time on the same day.");
        }
      }
    }
  }
}
