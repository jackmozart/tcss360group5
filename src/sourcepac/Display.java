
package sourcepac;

import java.util.List;

/**
 * 
 * @author Steven Cozart
 * @version 5/30/2011
 * 
 */
public class Display {

  private Constraints my_Constraints;

  /**
   * 
   * @param the_constraint
   */
  public Display(Constraints the_constraint) {
    my_Constraints = the_constraint;
  }

  /**
   * Displays all constraints.
   */
  public String displayConstraints() {
    final StringBuilder errorMessage = new StringBuilder();
    errorMessage.append(getAdvisorPrefrences());
    errorMessage.append(getTeacherSameTimeConflicts());
    errorMessage.append(getTeacherPrefrences());
    System.out.print(errorMessage);
    return errorMessage.toString();
  }

  /**
   * 
   * @return
   */
  private String getAdvisorPrefrences() {
    final StringBuilder errorMessage = new StringBuilder();
    errorMessage
        .append("The following courses are not included in the schedual and have been requested by an advisor:");
    List<Course> missingCourses = my_Constraints.checkAdvisorPrefrences();
    for (Course aCourse : missingCourses){
      errorMessage.append(aCourse.getCourseTitle() + "\n");
    }
    return errorMessage.toString();
  }

  /**
   * 
   * @return
   */
  private String getTeacherSameTimeConflicts() {
    final StringBuilder errorMessage = new StringBuilder();
    errorMessage.append("The following courses are taught by the same teacher at the same time:\n");
    List<CourseCopy> dissLikedCourses = my_Constraints.teacherSameTimes();
    for (CourseCopy course : dissLikedCourses) {
      errorMessage.append(course.getCourseTitle() + "\n");
    }
    return errorMessage.toString();
  }

  /**
   * 
   * @return List of all courses that are being taught by teachers who have
   *         specified the course time is outside of the ability.
   */
  private String getTeacherTimePrefrence() {
    final StringBuilder errorMessage = new StringBuilder();
    errorMessage.append("The following courses are taught by a teacher outside of the teachers avaliablity:\n");
    List<CourseCopy> dissLikedCourses = my_Constraints.checkTeacherPrefrence();
    for (CourseCopy course : dissLikedCourses) {
      errorMessage.append(course.getCourseTitle() + "\n");
    }
    return errorMessage.toString();
  }

  /**
   * 
   * @return List of all courses that are being taught by teachers who had the
   *         courses on their dislike list.
   */
  private String getTeacherPrefrences(){
    final StringBuilder errorMessage = new StringBuilder();
    errorMessage
        .append("The following courses are taught by teachers who stated they did not wish to teach the course:\n");
    List<CourseCopy> dissLikedCourses = my_Constraints.checkTeacherPrefrence();
    for (CourseCopy course : dissLikedCourses) {
      errorMessage.append(course.getCourseTitle() + "\n");
    }
    return errorMessage.toString();
  }
}
