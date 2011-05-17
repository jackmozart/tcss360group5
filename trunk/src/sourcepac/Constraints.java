package sourcepac;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author Steven Cozart
 *
 */
public class Constraints {

  private List<StudentPrefrence> my_students;
  
  private List<CourseCopy> my_courses;
  
  private List<TeacherPrefrence> my_teacehrs;
  
  private List<AdvisorPrefrence> my_advisors;

  /**
   * 
   * @param the_list
   * @param the_students
   * @param the_advisors
   * @param the_teachers
   */
  public Constraints(List<CourseCopy> the_list, List<StudentPrefrence> the_students,
                     List<AdvisorPrefrence> the_advisors, List<TeacherPrefrence> the_teachers) {
    my_courses = the_list;
    my_students = the_students;
    my_teacehrs = the_teachers;
    my_advisors = the_advisors;

  }

  /**
   * @return A description of all failed tests.  
   */
  public String runTests() {
    StringBuilder test_results = new StringBuilder();
    test_results.append(teacherSameTimes());
    return test_results.toString();
  }

  /**
   * Tests if a teacher is teaching two classes at the same time.
   * Error message format (Teacher) is teaching two classes at the same time (start time)".
   * @pre Requires a sorted list.
   * @return Error message for each violation in which a teacher is teaching two
   *         classes at the same time.
   */
  public String teacherSameTimes() {
    StringBuilder error_message = new StringBuilder();
    CourseCopy curr_course = my_courses.get(0);
    ArrayList<CourseCopy> posible_time_conflicts = new ArrayList<CourseCopy>();
    
    for(CourseCopy course: my_courses){
      if(course.getStartTime()> curr_course.getEndTime()){
        
      }
    }
    
    for(CourseCopy course: my_courses){
      for(CourseCopy course_conflicts: posible_time_conflicts){
        
      }
      if(course.getTeacher().equals(course.getTeacher())){///borororookeekekeke
        error_message.append(course.getTeacher());
        error_message.append(" is teaching two classes at the same time (");
        error_message.append(course.getStartTime()+ ")\n");
       
      }
    }
    return error_message.toString();
  }
  
  

}
