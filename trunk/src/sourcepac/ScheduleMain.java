
package sourcepac;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeMap;

import users.Advisor;
import users.Student;

/**
 * This class is the holds the data types needed for running the Scheduling
 * program.
 * 
 * @author Phillip Bernard comments by Steven Cozart
 * @version 3.2 5/21/2011 worked on loadUsers, almost done need to finish readAvailability
 * @version 2.1 5/16/2011 fixed loadCourses method.
 * @version 1.0 5/13/2011 initial
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
  
  public static final int NUMBER_OF_TIME_BLOCKS = 5;
  
  public static final int NUMBER_OF_SCHOOL_DAYS = 4;
  /**
   * All possible courses to be offered.  
   */
  private Map<String, Course> my_course_catalog;
  /**
   * All possible time blocks courses can be offered.
   */
  private Map<Integer, Time> my_course_times;
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
    my_course_times = loadCourseTimes("CourseTimes.txt");
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
   * This methods returns a copy of the current course catalog.
   * 
   * @return A copy of the course list.
   */
  public Map<String, Course> getCourseCatalog() {
    return Collections.unmodifiableMap(my_course_catalog);
  }

  /**
   * This method loads courses from the given data file name. 
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
      
      next_line = scanner.nextLine();//read in the next line
      
      final Scanner line_scanner = new Scanner(next_line); 
      line_scanner.useDelimiter(",");
      course_title = line_scanner.next();
      course_description = line_scanner.next();
      credit = line_scanner.nextInt();
      
      courses.put(course_title, new Course(course_title, //Add the course
                                           course_description,
                                                      credit));
    }
    
    return courses;

  }

  /**
   * This method loads the course times from the given file name.
   * 
   * @param the_file_name
   * @return
   */
  public Map<Integer, Time> loadCourseTimes(String the_file_name) {
    final File time_file = new File(the_file_name);
    final  Map<Integer, Time> times = new TreeMap<Integer, Time>();
    Scanner scanner = null;
    int start_time;
    int end_time;
    try {
      scanner = new Scanner(time_file);
    } catch (FileNotFoundException e) {
      System.err.println("FileNotFound");
    }
      while (scanner.hasNextLine()) {
        Scanner line_scanner = new Scanner(scanner.nextLine());
      
        start_time = line_scanner.nextInt();
        end_time = line_scanner.nextInt();
        my_course_times.put(new Integer(start_time),
                            new Time(start_time, end_time));
      }
    return times;
  }

  /**
   * 
   * @param the_file_name
   * @return
   */
  public Map<String, UserRoleList> loadUsers(String the_file_name) {
    final  Map<String, UserRoleList> users = new HashMap<String, UserRoleList>();
    final File user_file = new File(the_file_name);
    Scanner scanner = null;
    String next_line = "";
    try {
      scanner = new Scanner(user_file);
    } catch (FileNotFoundException e) {
      System.err.println("FileNotFound");
    }
   
    while (scanner.hasNext()) {
      String user_type;
      String user_name;
      String user_pass;
      String first_name;
      String last_name;
      Set<Course> preferred_courses = new HashSet<Course>();
      Set<Course> unprefered_courses;
      List<Time> times;
      int[][] availability;
      
      next_line = scanner.nextLine();//read in the next line
      
      final Scanner line_scanner = new Scanner(next_line); 
      user_type = line_scanner.next();
      
      user_name = line_scanner.next();
      user_pass = line_scanner.next();
      first_name = line_scanner.next();
      last_name = line_scanner.next();
      preferred_courses = readCourses(line_scanner);
      while (scanner.hasNext()) {
        switch (user_type.charAt(0)) {
          case 'A':
            Advisor a = new Advisor(user_name, user_pass, 
                                    first_name + " " + last_name,
                                    preferred_courses);
            break;
          case 'S':
            times = readTimes(scanner);
            Student s = new Student(user_name, user_pass, 
                                    first_name + " " + last_name,
                                    preferred_courses, times);
            
            break;
          case 'T':
            readTimes(scanner);
            unprefered_courses = readCourses(scanner);
            availability = readAvailability(scanner);
            
            break;
          default:
            
        }
        
      }
    }
    return users;
  }

  public List<Time> readTimes(Scanner scanner) {
    final List<Time> times = new LinkedList<Time>();
    for (int i = scanner.nextInt(); i > 0; i--) {
      times.add(my_course_times.get(scanner.nextInt()));
    }
    return times;
  }

  public Set<Course> readCourses(Scanner scanner) {
    Set<Course> courses = new HashSet<Course>();
    String course_name = scanner.next();
    
    while (!course_name.equals("/")) {
      courses.add(my_course_catalog.get(course_name));
      course_name = scanner.next();
    }
    
    return courses;    
  }
  
  public int[][] readAvailability(Scanner scanner) {
    int[][] availability = new int[NUMBER_OF_SCHOOL_DAYS][NUMBER_OF_TIME_BLOCKS];
    
    return availability;
  }
  
  public UserRoleList getUser(String the_username) {
    return my_users.get(the_username); 
  }
}
