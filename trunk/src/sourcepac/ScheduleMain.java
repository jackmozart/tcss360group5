
package sourcepac;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeMap;

import users.Advisor;
import users.Student;
import users.Teacher;
import users.Voter;

/**
 * This class is the holds the data types needed for running the Scheduling
 * program.
 * 
 * @author Phillip Bernard comments by Steven Cozart
 * @version 5.2 5/28/2011 fixed a mysterious bug in loadCourseTimes()
 * @version 4.2 5/23/2011 finished loadUsers()
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
  
  public static final String DEFAULT_USER_LIST = "UserList.txt";
  
  private int my_number_of_time_blocks;
  
  private int my_number_of_days;
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
  private List<StudentPreference> my_student_preferences; 
  private List<AdvisorPreference> my_advisor_preferences;
  private List<TeacherPreference> my_teacher_preferences;
  private List<Teacher> my_teachers; //is this what you want Steven?
  /**
   * Division between day and evening classes.
   */
  private Time my_day_evening_time_division;

  /**
   * Reads in user created files of course times and course list.  
   */
  public ScheduleMain() {
    this("Userlist.txt");
  }
  
  public ScheduleMain(String the_user_list) {
    my_course_catalog = loadCourseData("CourseList.txt");
    my_course_times = loadCourseTimes("CourseTimes.txt");
    my_teachers = new ArrayList<Teacher>();
    my_users = loadUsers(the_user_list);
  }

  /**
   * 
   * @param the_args Unused array of strings.
   */
  public static void main(String[] the_args) {
    ScheduleMain sm = new ScheduleMain();
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
   * @param my_number_of_days 
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
    my_number_of_days = scanner.nextInt();
    //scanner.next(); // clear to the next line
    while (scanner.hasNextLine()) {
      String line = scanner.nextLine();

      final Scanner line_scanner = new Scanner(line);
    
      start_time = line_scanner.nextInt();
      end_time = line_scanner.nextInt();
      Time time = new Time(start_time, end_time);
      times.put(start_time , time);
      my_number_of_time_blocks++;
    }
    return times;
  }

  /**
   * This method loads users and their preferences from a file.
   * 
   * @param the_file_name to load the users from
   * @return a Map of users
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
   
    while (scanner.hasNextLine()) {
      String user_type;
      String user_name = "";
      String user_pass;
      String first_name;
      String last_name;
      int max_credit_load;
      int current_credit_load;
      Set<Course> preferred_courses = new HashSet<Course>();
      Set<Course> unpreferred_courses;
      List<Time> times;
      int[][] availability;
      
      next_line = scanner.nextLine(); //read in the next line
     
      final Scanner line_scanner = new Scanner(next_line); 
      final UserRoleList roles = new UserRoleList(); 
      while (line_scanner.hasNext()) {
        user_type = line_scanner.next();
        
        user_name = line_scanner.next();
        user_pass = line_scanner.next();
        first_name = line_scanner.next();
        last_name = line_scanner.next();
        preferred_courses = readCourses(line_scanner);
      
        switch (user_type.charAt(0)) {
          case 'A':
            roles.setAdvisor(new Advisor(user_name, user_pass, 
                                    first_name + " " + last_name,
                                    preferred_courses));
            
            break;
          case 'S':
            times = readTimes(line_scanner);
            roles.setStudent(new Student(user_name, user_pass, 
                                    first_name + " " + last_name,
                                    preferred_courses, times));
            
            break;
          case 'T':
            max_credit_load = line_scanner.nextInt();
            current_credit_load = line_scanner.nextInt();
            unpreferred_courses = readCourses(line_scanner);
            availability = readAvailability(line_scanner);
            Teacher t = new Teacher(user_name, user_pass, 
                                    first_name + " " + last_name,
                                    preferred_courses, max_credit_load,
                                    current_credit_load,unpreferred_courses, 
                                    availability);
            roles.setTeacher(t);
            my_teachers.add(t);
            break;
          default:
            throw new IllegalArgumentException("Invalid Character");
        } 
      }
      users.put(user_name, roles);
    }
    return users;
  }
  
  public UserRoleList getUser(String the_username) {
    return my_users.get(the_username); 
  }
  
  public void getPreferences() {
    Set<UserRoleList> users = (HashSet<UserRoleList>) my_users.values();
    Iterator itr = users.iterator();
    
    while(itr.hasNext()) {
      UserRoleList roles = (UserRoleList) itr.next();
      if(roles.getAdvisor() != null) {
        
      }
    }
  }
  
  public List<Teacher> getTeachers() {
    List teachers = new ArrayList<Teacher>();
    
    
    return teachers;
  }
  
  private List<Time> readTimes(Scanner the_scanner) {
    final List<Time> times = new LinkedList<Time>();
   
    for (int i = the_scanner.nextInt(); i > 0; i--) {
      times.add(my_course_times.get(the_scanner.nextInt()));
    }
    return times;
  }

  private Set<Course> readCourses(Scanner the_scanner) {
    Set<Course> courses = new HashSet<Course>();
    String course_name = the_scanner.next();
    
    while (!course_name.equals("/")) {
      courses.add(my_course_catalog.get(course_name));
      course_name = the_scanner.next();
    }
    
    return courses;    
  }
  
  /**
   * This method scans a line for the given availability for a teacher
   * it converts the line into a matrix with -1 representing not available
   * 0 as available and 1 as preferred.
   * 
   * @param scanner A scanner that is already initialized with the 
   *  line to scan
   * @return a 2d array of availability preferences
   */
  private int[][] readAvailability(Scanner the_scanner) {
    int[][] availability = new int[my_number_of_days][my_number_of_time_blocks];
    
    final int days = the_scanner.nextInt();
    
    
    for (int i = 0; i < days; i++) {
      final int time_blocks = the_scanner.nextInt();
      for (int j = 0; j < time_blocks; j++) {
        availability[i][j] = the_scanner.nextInt();
      }
     the_scanner.next();
    }
    return availability;
  }

}
