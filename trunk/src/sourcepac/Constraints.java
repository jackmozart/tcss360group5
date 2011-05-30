package sourcepac;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import users.Advisor;
import users.Student;
import users.Teacher;

/**
 * 
 * @author Steven Cozart 
 * 
 * @version 5/28/2011 Adjusted checkCreditLoad to use Teacher's methods -PB
 * @version Last update May 25 2011
 */
public class Constraints {

  private List<Student> my_students;
  
  private List<CourseCopy> my_courses;
  
  private List<Teacher> my_teachers;
  
  private List<Advisor> my_advisors;

  /**
   * 
   * @param the_list
   * @param the_students
   * @param the_advisors
   * @param the_teachers
   */
  public Constraints(List<CourseCopy> the_list, List<Student> the_students,
                     List<Advisor> the_advisors, List<Teacher> the_teachers) {
    my_courses = the_list;
    my_students = the_students;
    my_teachers = the_teachers;
    my_advisors = the_advisors;

  }

  /**
   * @return A description of all failed tests.  
   */
  public String runTests() {
    final StringBuilder test_results = new StringBuilder();
    test_results.append(teacherSameTimes());
    test_results.append(checkCreditLoad());
    return test_results.toString();
  }

  /**
   * Tests if a teacher is teaching two classes at the same time.
   * Error message format (Teacher) is teaching (class1) and (class2) at the same time (start time)".
   * @pre Requires a sorted list.
   * @return Error message for each violation in which a teacher is teaching two
   *         classes at the same time.
   */
  public String teacherSameTimes() {
    final StringBuilder error_message = new StringBuilder();
    final List<CourseCopy> posible_time_conflicts = new ArrayList<CourseCopy>();
    CourseCopy curr_course = my_courses.get(0);
    int index = 0;
    //reads through each course collection possible conflicts
    for (CourseCopy course : my_courses) {
      index = 1 + my_courses.indexOf(course);
      curr_course = my_courses.get(index);
      while (course.getTime().getStartTime() > curr_course.getTime().getEndTime()) {
        posible_time_conflicts.add(curr_course);
        curr_course = my_courses.get(index);
        index++;
      }
    }
    //reads through each course comparing it to its possible conflicts 
    for (CourseCopy course : my_courses) {
      for (CourseCopy course_conflicts : posible_time_conflicts) {
        if (course.getTeacher().equals(course_conflicts.getTeacher())) {
          error_message.append(course.getTeacher());
          error_message.append(" is teaching " + course.getCourseTitle() + " and " +
                               course_conflicts.getCourseTitle() + "  at the same time (");
          error_message.append(course.getTime().getStartTime() + ")\n");
        }
      }
    }
    return error_message.toString();
  }

  /**
   * Checks teacher credit load.
   * 
   * @return Error message in form (teacher) exceeds credit load by (amount) credits.
   */
  public String checkCreditLoad() {
    final StringBuilder error_message = new StringBuilder();
    Teacher teacher = null;
    
    for (CourseCopy course : my_courses) {
      String teacherName = course.getTeacher();
      int i = 0;
      boolean matchFound = false;
      int numTeachers = my_teachers.size();
      
      do{
        teacher = my_teachers.get(i);
        matchFound = !my_teachers.get(i).getName().equals(teacherName);
        i ++;
        //if used to ensure that the list does not go beyond size of list in case of a missing teacher.
        if(
            i > numTeachers){
          Teacher missingTeacher = new Teacher(teacherName, teacherName, teacherName, null, numTeachers, null, null);
          my_teachers.add(missingTeacher);
          teacher = missingTeacher;
          matchFound = false;
        }
      }
      while (matchFound);
      
      teacher.setCurrCredits(  course.getCredit() + teacher.getCurrCredits());
      
    }
    
    for(Teacher teacher_pref : my_teachers){
      if(teacher_pref.getMaxCredits() < teacher.getCurrCredits()){
        //appends error message (teacher) exceeds credit load by (amount) credits.
        error_message.append(teacher_pref.getName());
        error_message.append(" exceeds credit load by ");
        error_message.append(teacher.getCurrCredits()- //PB I adjust this code so that it compiles and  
                                           teacher_pref.getMaxCredits()); //uses the methods already in Teacher
        error_message.append(" credits.\n ");
     }
     }
     return error_message.toString();
  }
  
  /**
   * 
   * @return List of all courses suggested by advisor but not offered.
   */
  public List<Course> checkAdvisorPrefrences(){  
    List<Course> missingCourses = new ArrayList<Course>();
    for(Advisor advisor: my_advisors){
      for(Course preferredCourse:advisor.getPreferredCourseList()){
        String courseName = preferredCourse.getCourseTitle();
        boolean courseFound = false;
        for (CourseCopy courses: my_courses){
          if(courses.getCourseTitle().equals(courseName)){
            courseFound = true;
            break;
          }     
        }
        if(!courseFound){
          missingCourses.add(preferredCourse);
        }
      }
    }
    return missingCourses;
  }
  
  /**
   * Collects all courses assigned to a teacher that does not wish to teach it.   
   * @return All courses that a teacher was assigned and is on the teacher disliked list.
   */
  public List<CourseCopy> checkTeacherPrefrence() {
    List<CourseCopy> dissLikeCourses = new ArrayList<CourseCopy>();
    // goes through each teacher
    for(Teacher currTeacher: my_teachers) {
      Set<Course> unpreferedCourses = currTeacher.getUnpreferedCourses();
      //finds all courses taught and ensures they are not in the disliked list.  
      for(CourseCopy aCourse: currTeacher.getCourses()) {
        String courseTitle = aCourse.getCourseTitle();
        for(Course  dislikedCourse: unpreferedCourses ) {
          if(dislikedCourse.getCourseTitle().equals(courseTitle)){
            dissLikeCourses.add(aCourse);
          }
        }
      }
    }
    return dissLikeCourses;
  }
  
  /**
   * @pre getTimePrefrences() != null, getDayPrefrences() != null
   * @pre timeBlocks() !=null
   * @return The courses that violate the day or time constraints 
   */
  public List<CourseCopy> checkTeacherTimes(){
    
    // to do finish this once the teacher implementation is finalized (time is curently unfinished)
    List<CourseCopy> disslikeCoursesTimes = new ArrayList<CourseCopy>();
    // goes through each teacher
    for (Teacher currTeacher : my_teachers) {
      int[][] preferedTimes = currTeacher.getTimePreferences();
      Set<CourseCopy> coursesSet = currTeacher.getCourses();
      //goes through each course assigned to a teachers 
      for (CourseCopy course: coursesSet) {
         boolean [] days = course.getDays();
         
        //if days dont match
        //if time dont match
        
      }

    }
    return disslikeCoursesTimes;
  }

}
