package tests;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.HashSet;

import org.junit.Test;

import sourcepac.Course;
import users.Advisor;

public class AdvisorTest {
  private Advisor my_advisor;
  public AdvisorTest() {
    my_advisor = new Advisor("blank", "blank", "blank", new ArrayList<Course>());
  }
  
  @Test
  public void equalsTest() {
    assertTrue(my_advisor.equals(new Advisor("blank", "blank", "blank", new ArrayList<Course>())));
  }
}
