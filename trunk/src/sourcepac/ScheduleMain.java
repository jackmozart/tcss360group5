
package sourcepac;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

/**
 * This class is the holds the data types needed for running the Scheduling
 * program.
 * 
 * @author Phillip Bernard comments by Steven Cozart
 * @version 5/16/2011 fixed loadCourses method.
 * @version 5/13/2011
 */
public class ScheduleMain {
  /**
   * Weight applied to course preferences of student user type.  
   */
  public static final int STUDENT_WEIGHT = 1;
  /**
   * Weight applied to course preferences of teacher user type.  
   */
  public static final int TEACHER_WEIGHT = 2;
  /**
   * Weight applied to course preferences of advisor user type.  
   */
  public static final int ADVISOR_WEIGHT = 3;
  /**
   * All possible courses to be offered.  
   */
  private Map<String, Course> my_course_catalog;
  /**
   * All possible time blocks courses can be offered.
   */
  private Set<Time> my_course_times;
  /**
   * List of all users.  
   */
  private Map<String, UserRoleList> my_users;  
  /**
   * Division between day and evening classes.
   */
  private Time my_day_evening_time_division;

  /**
   * Reads in user created files of course times and course list.  
   */
  public ScheduleMain() {
    my_course_catalog = loadCourseData("CourseList.txt");
    //my_course_times = loadCourseTimes("CourseTimes.txt");
    my_users = new HashMap<String, UserRoleList>();
  }

  /**
   * 
   * @param the_args Unused array of strings.
   */
  public static void main(String[] the_args) {
    //do something here
  }

  /**
   * 
   * @return A copy of the course list.
   */
  public Map<String, Course> getCourseCatalog() {
    return my_course_catalog;
  }

  /**
   * 
   * @param the_file_name the file name to load courses from
   * @return A Map of courses in the file
   */
  public Map<String, Course> loadCourseData(String the_file_name) {
    final Map<String, Course> courses = new HashMap<String, Course>();
    final File course_file = new File(the_file_name);
    Scanner scanner = null;
    String next_line = "";
    try {
      scanner = new Scanner(course_file);
    } catch (FileNotFoundException e) {
      System.err.println("FileNotFound");
    }
   
    while (scanner.hasNext()) {
      String course_title;
      String course_description;
      int credit;
      
      next_line = scanner.nextLine();
      
      final Scanner line_scanner = new Scanner(next_line);
      line_scanner.useDelimiter(",");
      course_title = line_scanner.next();
      course_description = line_scanner.next();
      credit = line_scanner.nextInt();
      
      courses.put(course_title, new Course(course_title,
                                                     course_description,
                                                     credit));
    }
    
    return courses;

  }

  public Set<Time> loadCourseTimes(String the_file_name) {
    final  Set<Time> times = new TreeSet<Time>();

    return times;
  }

  public Map<String, UserRoleList> loadUsers(String the_file_name) {
    final  Map<String, UserRoleList> users = new HashMap<String, UserRoleList>();

    return users;
  }

  public UserRoleList getUser(String the_username) {
    return my_users.get(the_username);
  }
}
