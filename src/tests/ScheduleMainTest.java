
package tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.HashSet;

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
  @Before
  public final void startUp() {
    my_scheduler = new ScheduleMain();
  }

  /**
   * This methods test the loadUsers method for loading an Advisor object
   * from a file.
   */
  @Test
  public void loadUserFileAdvisorTest() {
    final Advisor test_advisor = new Advisor("jsnuffy", 
                                             "123456789", 
                                             "Joe Snuffy", 
                                             new HashSet<Course>());
    my_scheduler.loadUsers("SingleAdvisorTest.txt");
    assertEquals("Users should be the same", test_advisor, 
                                my_scheduler.getUser("jsnuffy"));
  }

  /**
   * This methods test the loadUsers method for loading an Student object
   * from a file.
   */
  @Test
  public void loadUserFileStudentTest() {
    final Student test_student = new Student("obrother", 
                                             "234567891", 
                                             "Ou Brother", 
                                             new HashSet<Course>(), 
                                             new ArrayList<Time>());
    my_scheduler.loadUsers("SingleStudentTest.txt");
    assertEquals("Users should be the same", test_student,
                         my_scheduler.getUser("obrother"));
  }

  /**
   * This methods test the loadUsers method for loading an Teacher object
   * from a file.
   */
  @Test
  public void loadUserFileTeacherTest() {
    final Teacher test_teacher = new Teacher("hseaward", 
                                             "345678912", 
                                             "Harold Seaward", 
                                             new HashSet<Course>(),
                                             new HashSet<Course>(),
                                             new int[][]{});
    my_scheduler.loadUsers("SingleTeacherTest.txt");
    assertEquals("Users should be the same", test_teacher, 
                         my_scheduler.getUser("hseaward"));
  }
  
  /**
   * This methods test the loadUser method's ability to handle a multi-roled 
   * user from a file.
   */
  @Test
  public void loadSingleMultiRoleUserTest() {
    final Student multi_role_test_student = new Student("darmstrong",
                                              "456789123",
                                              "Richard Armstrong",
                                              new HashSet<Course>(),
                                              new ArrayList<Time>());
    final Advisor multi_role_test_advisor = new Advisor("darmstrong",
                                             "456789123",
                                             "Richard Armstrong",
                                             new HashSet<Course>());
    my_scheduler.loadUsers("SingleMultiUserTest.txt");
    final UserRoleList test_user_list = my_scheduler.getUser("darmstrong");

    assertTrue(test_user_list.contains(multi_role_test_student) &&
                      test_user_list.contains(multi_role_test_advisor));
    
  }
  
  /**
   * This method test the loadUsers method to handle a loading multiple users
   * with different amounts of roles from a file.
   */
  @Test
  public void loadMultipleUsersTest() {
    final Student test_student = new Student("obrother", 
                                             "234567891", 
                                             "Ou Brother", 
                                             new HashSet<Course>(),
                                             new ArrayList<Time>());
    final Teacher test_teacher = new Teacher("hseaward", 
                                             "345678912", 
                                             "Harold Seaward", 
                                             new HashSet<Course>(),
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
    my_scheduler.loadUsers("MultipleUserTest.txt");
    final UserRoleList test_user_list = my_scheduler.getUser("darmstrong");
    
    assertTrue(test_user_list.contains(multi_role_test_student) &&
               test_user_list.contains(multi_role_test_advisor) && 
               test_student.equals(my_scheduler.getUser("obrother")) &&
               test_teacher.equals(my_scheduler.getUser("hseaward")) &&
               test_advisor.equals(my_scheduler.getUser("jsnuffy")));
  }
  
  @Test
  public void loadCourseCatalogTest() {
    my_scheduler.loadCourseData("CourseList.txt");
  }
}
