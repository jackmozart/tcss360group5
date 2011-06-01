package tests;

import static org.junit.Assert.assertEquals;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import sourcepac.Constraints;
import sourcepac.CourseCopy;
import users.Teacher;

/**
 * Tests constraints primary activities, does not test individual methods.
 * @author 
 *
 */
public class MainConstraintTest {
  
  /**
   * This tests teacherSameTime, making sure it adds a conflicting
   * course if it is passed conflicting courses.
   * @author Chris Davidson
   */
  @Test
  public void teachesSameTimeTest() {
    boolean[] days = new boolean[7];
    List<CourseCopy> courses = new ArrayList<CourseCopy>();
    List<Teacher> teachers = new ArrayList<Teacher>();
    Teacher testTeacher = new Teacher("jten", "345678912", 
                                      "Josh Tenenberg", null, 25, 0, null,
                                      null);
    days[1] = true;
    CourseCopy first = new CourseCopy("TCSS360", "A","Fun Class", 5,
                   1615, 1820, "Josh Tenenberg",
                   days);
    CourseCopy second = new CourseCopy("TCSS360", "A","Fun Class", 5,
                                      1700, 1820, "Josh Tenenberg",
                                      days);
    courses.add(first);
    courses.add(second);
    testTeacher.addCourse(first);
    teachers.add(testTeacher);
    Constraints testing = new Constraints(courses, null, null, teachers);
    courses = testing.teacherSameTimes();
    assertEquals(courses.get(0), first);
  }

}
