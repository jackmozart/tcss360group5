
package tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import sourcepac.ScheduleMain;
import users.Advisor;
import users.Student;

public class ScheduleMainTest {
  private ScheduleMain my_scheduler;

  @Before
  public final void startUp() {
    my_scheduler = new ScheduleMain();
  }

  @Test
  public void loadUserFileAdvisorTest() {
    Advisor test_advisor = new Advisor("jsnuffy", "123456789", "Joe Snuffy", null);
    my_scheduler.loadUsers("SingleAdvisorTest.txt");
    assertEquals("Users should be the same", test_advisor, my_scheduler.getUser("jsnuffy"));

  }

  @Test
  public void loadUserFileStudentTest() {
    Student test_student = new Student("jsnuffy", "123456789", "Joe Snuffy", null, null);
    my_scheduler.loadUsers("SingleStudentTest.txt");
    assertEquals("Users should be the same", test_student, my_scheduler.getUser("jsnuffy"));
  }

  public void loadUserFileTeacherTest() {

  }
}
