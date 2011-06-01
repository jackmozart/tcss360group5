
package tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Map;

import org.junit.Test;

import sourcepac.Course;
import sourcepac.ScheduleMain;
import sourcepac.Time;
import sourcepac.UserRoleList;
import users.Advisor;
import users.Student;
import users.Teacher;
/**
 * This class test the user reading ability of the ScheduleMain class.
 * 
 * @author Phillip Bernard
 * @version 5/15/2011
 */
public class ScheduleMainTest {
  /**
   * This field holds a copy of ScheduleMain for testing.
   */
  private ScheduleMain my_scheduler;
  private Student my_student;
  private Advisor my_advisor;
  private Teacher my_teacher;
  private Advisor my_multi_advisor;
  private Student my_multi_student;
  

  /**
   * This method sets us the field for testing.
   */
  public final void startUp(final String the_test_file) {
    my_scheduler = new ScheduleMain(the_test_file);
    my_student = new Student("obrother", 
                                             "234567891", 
                                             "Ou Brother", 
                                             new HashSet<Course>(),
                                             new ArrayList<Time>());
    my_teacher = new Teacher("hseaward", 
                                             "345678912", 
                                             "Harold Seaward", 
                                             new HashSet<Course>(), 25,
                                             0, new HashSet<Course>(), 
                                             new int[][]{});
    my_advisor = new Advisor("jsnuffy", 
                                             "123456789", 
                                             "Joe Snuffy", 
                                             new ArrayList<Course>());
    my_multi_student = new Student("darmstrong",
                                                        "456789123",
                                                        "Richard Armstrong",
                                                        new HashSet<Course>(),
                                                        new ArrayList<Time>());
    my_multi_advisor = new Advisor("darmstrong",
                                                       "456789123",
                                                       "Richard Armstrong",
                                                       new ArrayList<Course>());

  }
  
  /**
   * 
   */
  @Test
  public void loadCourseTimesTest() {
    startUp("singleAdvisorTestFile.txt");
    Map<Integer, Time> times =  
                my_scheduler.loadCourseTimes("CourseTimes.txt");
    
    Time time1 = times.get(800);
    
  }
  
  /**
   * This methods test the loadUsers method for loading an Advisor object
   * from a file.
   */
  @Test
  public void loadUserFileAdvisorTest() {
    startUp("singleAdvisorTestFile.txt");
    assertTrue("Users should be the same", 
          my_scheduler.getUser("jsnuffy").getAdvisor().equals(my_advisor));
  }

  /**
   * This methods test the loadUsers method for loading an Student object
   * from a file.
   */
  @Test
  public void loadUserFileStudentTest() {
    startUp("singleStudentTestFile.txt");

    assertEquals("Users should be the same", my_student,
                         my_scheduler.getUser("obrother").getStudent());
  }

  /**
   * This methods test the loadUsers method for loading an Teacher object
   * from a file.
   */
  @Test
  public void loadUserFileTeacherTest() {
    startUp("singleTeacherTestFile.txt");

    assertTrue("Users should be the same", 
                 my_teacher.equals(my_scheduler.getUser("hseaward").getTeacher()));
  }
  
  /**
   * This methods test the loadUser method's ability to handle a multi-roled 
   * user from a file.
   */
  @Test
  public void loadSingleMultiRoleUserTest() {
    startUp("singleMultiRoledUserTestFile.txt");

    final UserRoleList test_user_list = my_scheduler.getUser("darmstrong");
    assertTrue(test_user_list.getStudent().equals(my_multi_student) &&
                  test_user_list.getAdvisor().equals(my_multi_advisor));
  }
  
  /**
   * This method test the loadUsers method to handle a loading multiple users
   * with different amounts of roles from a file.
   */
  @Test
  public void loadMultipleUsersTest() {
    startUp("multipleUsersTestFile.txt");
    final UserRoleList test_user_list = my_scheduler.getUser("darmstrong");
    
    assertTrue(test_user_list.getStudent().equals(my_multi_student) &&
               test_user_list.getAdvisor().equals(my_multi_advisor) && 
               my_student.equals(my_scheduler.getUser("obrother").getStudent()) &&
               my_teacher.equals(my_scheduler.getUser("hseaward").getTeacher()) &&
               my_advisor.equals(my_scheduler.getUser("jsnuffy").getAdvisor()));
  }
}
