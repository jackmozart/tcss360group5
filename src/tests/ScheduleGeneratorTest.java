package tests;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import sourcepac.CourseCopy;
import sourcepac.Schedule;
import sourcepac.ScheduleGenerator;

/**
 * Test class for ScheduleGenerator.
 * @author Chris Davidson
 * @version 5/16/2011
 */
public class ScheduleGeneratorTest {
  
  /**
   * A schedule generator used in testing.
   */
  private ScheduleGenerator my_generator;
  
  /**
   * This creates a ScheduleGenerator for use in testing.
   * @author Chris Davidson
   */
  @Before
  public void beginTests() {
    my_generator = new ScheduleGenerator();
  }
  
  /**
   * Tests proper exception handling on schedule I/O.
   * Based on test code from
   * http://radio.javaranch.com/lasse/2007/05/17/1179405760728.html
   * with relevant changes.
   * @throws Exception IOException
   * @author Lasse Koskela with changes by Chris Davidson
   */
  @Test
  public void loadImproperScheduleTest() throws Exception {
    try {
      my_generator.importSchedule("IDontExist.csv");
      fail("IO should have messed up. Find out why it's working.");
    } catch (Exception expected) {
      // Test passes
    }
  }
  
  /**
   * Tests whether importSchedule correctly interprets a course.
   * @author Chris Davidson
   */
  @Test
  public void loadsCorrectScheduleTest() {
    my_generator.importSchedule("OneCourse.csv");
    Schedule compare = new Schedule("Test Schedule");
    compare.addCourse(new CourseCopy("TCSS360", "jtenenbg",5,
                                     1615, 1820));
    assertEquals((List<CourseCopy>) my_generator.
                 getSchedule().getCourses(), 
                 (List<CourseCopy>) compare.getCourses());
  }
}
