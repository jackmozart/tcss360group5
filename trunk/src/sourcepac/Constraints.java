package sourcepac;

import java.util.List;

/**
 * 
 * @author Steven Cozart
 *
 */
public class Constraints {

  private List<StudentPrefrence> my_students;
  
  private List<CourseCopy> my_course;
  
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
    my_course = the_list;
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

  private String teacherSameTimes() {
    String error_message = "Teacher " + "" + "is teaching two classes at the same time";
    return error_message;
  }
  
  

}
