
package tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;

import org.junit.Before;
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

  /**
   * This method sets us the field for testing.
   */
  public final void startUp(String test_file) {
    my_scheduler = new ScheduleMain(test_file);
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
    final Advisor test_advisor = new Advisor("jsnuffy", 
                                             "123456789", 
                                             "Joe Snuffy", 
                                             new HashSet<Course>());

    assertEquals("Users should be the same", test_advisor, 
                                my_scheduler.getUser("jsnuffy").getAdvisor());
  }

  /**
   * This methods test the loadUsers method for loading an Student object
   * from a file.
   */
  @Test
  public void loadUserFileStudentTest() {
    startUp("singleStudentTestFile.txt");
    final Student test_student = new Student("obrother", 
                                             "234567891", 
                                             "Ou Brother", 
                                             new HashSet<Course>(), 
                                             new ArrayList<Time>());

    assertEquals("Users should be the same", test_student,
                         my_scheduler.getUser("obrother").getStudent());
  }

  /**
   * This methods test the loadUsers method for loading an Teacher object
   * from a file.
   */
  @Test
  public void loadUserFileTeacherTest() {
    startUp("singleTeacherTestFile.txt");
    final Teacher test_teacher = new Teacher("hseaward", 
                                             "345678912", 
                                             "Harold Seaward", 
                                             new HashSet<Course>(), 25,
                                             new HashSet<Course>(),
                                             new int[][]{});

    assertEquals("Users should be the same", test_teacher, 
                         my_scheduler.getUser("hseaward"));
  }
  
  /**
   * This methods test the loadUser method's ability to handle a multi-roled 
   * user from a file.
   */
  @Test
  public void loadSingleMultiRoleUserTest() {
    startUp("singleMultiRoledUserTestFile.txt");
    final Student multi_role_test_student = new Student("darmstrong",
                                              "456789123",
                                              "Richard Armstrong",
                                              new HashSet<Course>(),
                                              new LinkedList<Time>());
    final Advisor multi_role_test_advisor = new Advisor("darmstrong",
                                             "456789123",
                                             "Richard Armstrong",
                                             new HashSet<Course>());
    
    final UserRoleList test_user_list = my_scheduler.getUser("darmstrong");

    assertTrue(test_user_list.getStudent().equals(multi_role_test_student) &&
                  test_user_list.getAdvisor().equals(multi_role_test_advisor));
    
  }
  
  /**
   * This method test the loadUsers method to handle a loading multiple users
   * with different amounts of roles from a file.
   */
  @Test
  public void loadMultipleUsersTest() {
    startUp("multipleUsersTestFile.txt");
    final Student test_student = new Student("obrother", 
                                             "234567891", 
                                             "Ou Brother", 
                                             new HashSet<Course>(),
                                             new ArrayList<Time>());
    final Teacher test_teacher = new Teacher("hseaward", 
                                             "345678912", 
                                             "Harold Seaward", 
                                             new HashSet<Course>(), 25,
                                             new HashSet<Course>(), 
                                             new int[][]{});
    final Advisor test_advisor = new Advisor("jsnuffy", 
                                             "123456789", 
                                             "Joe Snuffy", 
                                             new HashSet<Course>());
    final Student multi_role_test_student = new Student("darmstrong",
                                                        "456789123",
                                                        "Richard Armstrong",
                                                        new HashSet<Course>(),
                                                        null);
    final Advisor multi_role_test_advisor = new Advisor("darmstrong",
                                                       "456789123",
                                                       "Richard Armstrong",
                                                       new HashSet<Course>());

    final UserRoleList test_user_list = my_scheduler.getUser("darmstrong");
    
    assertTrue(test_user_list.contains(multi_role_test_student) &&
               test_user_list.contains(multi_role_test_advisor) && 
               test_student.equals(my_scheduler.getUser("obrother")) &&
               test_teacher.equals(my_scheduler.getUser("hseaward")) &&
               test_advisor.equals(my_scheduler.getUser("jsnuffy")));
  }
  
  @Test
  public void loadCourseCatalogTest() {
    startUp("singleAdvisorTestFile.txt");
    my_scheduler.loadCourseData("CourseList.txt");
  }
}
