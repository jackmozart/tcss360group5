
package tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Test;

import sourcepac.Course;

/**
 * This classes tests the Course class.
 * 
 * @author Steven Cozart
 * @version 1.0 5/15/2011
 */
public class CourseTest {

  /**
   * Describes a single course.
   */
  public static final String COURSE_DESCRIPTION = "This class is epic.";

  /**
   * Name of a single course.
   */
  public static final String COURSE_NAME = "TCSS 360";

  /**
   * Number of credits earned from course.
   */
  public static final int COURSE_CREDIT = 5;

  /**
   * The test object of a course.
   */
  private Course my_course;

  /**
   * Sets up default course object.
   */
  @Before
  public final void startUp() {
    my_course = new Course(COURSE_NAME, COURSE_DESCRIPTION, COURSE_CREDIT);
  }

  /**
   * Tests a success of normal toString call.
   */
  @Test
  public void toStringTest() {
    final String expected = COURSE_NAME + " " + COURSE_DESCRIPTION + " " + COURSE_CREDIT;
    assertEquals("Course to string should be the same", expected, my_course.toString());
  }

  /**
   * Tests for credit input error.
   */
  @Test
  public void creditInputTest() {
    try {
      my_course = new Course(COURSE_NAME, COURSE_DESCRIPTION, -1);
      fail("This method should should have thrown an illegal argument error.");
    } catch (final IllegalArgumentException e) {
      // test passed exception was thrown
    }
  }

  /**
   * Test for correct credit input.
   */
  @Test
  public void creditInputtest() {
    assertEquals("Course credit load should match", COURSE_CREDIT, my_course.getCredit());
  }

  /**
   * Test for correct course description input.
   */
  @Test
  public void descriptionInputtest() {
    assertEquals("Course description should match", COURSE_DESCRIPTION,
                 my_course.getCourseDescription());
  }

  /**
   * Test for correct title input.
   */
  @Test
  public void titleInputtest() {
    assertEquals("Course credit load should match", COURSE_NAME, my_course.getCourseTitle());
  }
  
}
