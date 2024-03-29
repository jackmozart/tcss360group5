package tests;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import sourcepac.Constraints;
import sourcepac.Course;
import sourcepac.CourseCopy;
import users.Advisor;
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
                                      1020, 1820, "Josh Tenenberg",
                                      days);
    CourseCopy second = new CourseCopy("TCSS360", "A","Fun Class", 5,
                                      1020, 1820, "Josh Tenenberg",
                                      days);
    courses.add(first);
    courses.add(second);
    testTeacher.addCourse(first);
    teachers.add(testTeacher);
    Constraints testing = new Constraints(courses, null, null, teachers);
    courses = testing.teacherSameTimes();
    assertEquals(courses.get(0), first);
  }
  
  /**
   * Tests checkCreditLoad, should return an empty list because
   * no credit loads were violated.
   * @author Chris Davidson 
   * @author Steven Cozart Modified for version 2 
   */
  @Test
  public void checkCreditLoadTest() {
    boolean[] days = new boolean[7];
    List<CourseCopy> courses = new ArrayList<CourseCopy>();
    List<Teacher> teachers = new ArrayList<Teacher>();
    Teacher testTeacher = new Teacher("jten", "345678912", 
                                      "Josh Tenenberg", null, 25, 0, null,
                                      null);
    days[1] = true;
    CourseCopy first = new CourseCopy("TCSS360", "A","Fun Class", 5,
                                      1020, 1820, "Josh Tenenberg",
                                      days);
    CourseCopy second = new CourseCopy("TCSS360", "A","Fun Class", 5,
                                      1020, 1820, "Josh Tenenberg",
                                      days);
    courses.add(first);
    courses.add(second);
    testTeacher.addCourse(first);
    testTeacher.addCourse(second);
    teachers.add(testTeacher);
    Constraints testing = new Constraints(null, null, null, teachers);
    assertTrue("The list of teachers should have been empty becauce no violations were made",testing.checkCreditLoad().isEmpty() );
  }
  
  /**
   * Tests checkCreditLoad, should return an teacher because
   * credit loads were violated.
   * @author Steven Cozart code stolen from Chris
   */
  @Test
  public void checkCreditLoadFailTest() {
    boolean[] days = new boolean[7];
    List<CourseCopy> courses = new ArrayList<CourseCopy>();
    List<Teacher> teachers = new ArrayList<Teacher>();
    Teacher testTeacher = new Teacher("jten", "345678912", 
                                      "Josh Tenenberg", null, 5, 0, null,
                                      null);
    days[1] = true;
    CourseCopy first = new CourseCopy("TCSS360", "A","Fun Class", 5,
                                      1020, 1820, "Josh Tenenberg",
                                      days);
    CourseCopy second = new CourseCopy("TCSS360", "A","Fun Class", 5,
                                      1020, 1820, "Josh Tenenberg",
                                      days);
    courses.add(first);
    courses.add(second);
    testTeacher.addCourse(first);
    testTeacher.addCourse(second);
    teachers.add(testTeacher);
    Constraints testing = new Constraints(null, null, null, teachers);
    List<Teacher> the_teacher = testing.checkCreditLoad();
    assertEquals("The list of teachers should have a single elment.", testTeacher.getName() ,the_teacher.get(0).getName() );
  }


  /**
   * Ensures that checkAdvisorPreferences returns a course that an advisor does
   * not prefer.
   * @author Chris Davidson 
   */
  @Test
  public void checkAdvisorPreferencesTest() {
    List<Course> advisorPrefs = new ArrayList<Course>();
    Course pref = new Course("TCSS360", "Awesome Class", 5);
    advisorPrefs.add(pref);
    Advisor testAdvisor = new Advisor("I Give Advice", "1234", "Paris Hilton",
                                      advisorPrefs);
    List<Advisor> advisors = new ArrayList<Advisor>();
    advisors.add(testAdvisor);
    List<CourseCopy> schedule = new ArrayList<CourseCopy>();
    CourseCopy course = new CourseCopy("TCSS365", "A", "Wrong Class", 5,
                                      1615, 1820, "Josh Tenenberg",
                                      new boolean[7]);
    schedule.add(course);
    Constraints testing = new Constraints(schedule, null, advisors, null);
    advisorPrefs = testing.checkAdvisorPreferences();
    assertEquals(pref, advisorPrefs.get(0));
  }
}
