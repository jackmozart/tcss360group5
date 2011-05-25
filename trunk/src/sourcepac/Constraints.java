package sourcepac;

import java.util.ArrayList;
import java.util.List;

import users.Teacher;

/**
 * 
 * @author Steven Cozart
 *
 */
public class Constraints {

  private List<StudentPrefrence> my_students;
  
  private List<CourseCopy> my_courses;
  
  private List<Teacher> my_teachers;
  
  private List<AdvisorPrefrence> my_advisors;

  /**
   * 
   * @param the_list
   * @param the_students
   * @param the_advisors
   * @param the_teachers
   */
  public Constraints(List<CourseCopy> the_list, List<StudentPrefrence> the_students,
                     List<AdvisorPrefrence> the_advisors, List<Teacher> the_teachers) {
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
    //reads through each course collection posible conflicts
    for (CourseCopy course : my_courses) {
      index = 1 + my_courses.indexOf(course);
      curr_course = my_courses.get(index);
      while (course.getStartTime() > curr_course.getEndTime()) {
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
          error_message.append(course.getStartTime() + ")\n");
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
    Teacher teacher;
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
        if(i > numTeachers){
          Teacher missingTeacher = new Teacher(teacherName, teacherName, teacherName, null, numTeachers, null, null);
          my_teachers.add(missingTeacher);
          teacher = missingTeacher;
          matchFound = false;
        }
      }
      while (matchFound);
      
      teacher.setCredits(  course.getCredit() + teacher.getCurrCredits());
      
    }
    
    for(Teacher teacher_pref : my_teachers){
      if(teacher_pref.getCreditLoad()< teacher.getCurrCredits()){
        //appends error message (teacher) exceeds credit load by (amount) credits.
        error_message.append(teacher_pref.getName());
        error_message.append(" exceeds credit load by ");
        error_message.append(teacher.getCredits()- teacher_pref.getCreditLoad());
        error_message.append(" credits.\n ");
    
     }
     }
     return error_message.toString();
  }
  
  

}
