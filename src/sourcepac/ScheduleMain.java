
package sourcepac;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import users.Voter;

/**
 * This class is the holds the data types needed for running the Scheduling
 * program.
 * 
 * @author Phillip Bernard
 * @version 5/13/2011
 */
public class ScheduleMain {
  public static final int STUDENT_WEIGHT = 1;
  public static final int TEACHER_WEIGHT = 2;
  public static final int ADVISOR_WEIGHT = 3;
  private Map<String, Course> my_course_catalog;
  private Set<Time> my_course_times;
  private Map<String, List<Voter>> my_users;
  private Time my_day_evening_time_division;

  public ScheduleMain() {
    my_course_catalog = loadCourseData("CourseList.txt");
    my_course_times = loadCourseTimes("CourseTimes.txt");
  }

  public static void main(String[] args) {

  }

  public Map<String, Course> getCourseCatalog() {
    return my_course_catalog;
  }

  public Map<String, Course> loadCourseData(String the_file_name) {
    Map<String, Course> courses = new HashMap<String, Course>();

    return courses;

  }

  public Set<Time> loadCourseTimes(String the_file_name) {
    Set<Time> times = new TreeSet<Time>();

    return times;
  }

  public Map<String, List<Voter>> loadUsers(String the_file_name) {
    Map<String, List<Voter>> users = new HashMap<String, List<Voter>>();

    return users;
  }

  public List<Voter> getUser(String the_username) {
    return my_users.get(the_username);
  }
}
