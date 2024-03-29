
package sourcepac;

import java.util.List;

import users.Teacher;

/**
 * Generates all strings for constraint class.  
 * @author Steven Cozart
 * @version 5/31/2011
 * 
 */
public class Display {

  private Constraints my_Constraints;

  /**
   * Formats all lists of violations into a printable string.   
   * @param the_constraint The constraint object to run tests on.
   */
  public Display(Constraints the_constraint) {
    my_Constraints = the_constraint;
  }

  /**
   * Displays all constraints.
   */
  public String toString() {
    final StringBuilder errorMessage = new StringBuilder();
    errorMessage.append(getAdvisorPreferences());
    errorMessage.append(getTeacherSameTimeConflicts());
    errorMessage.append(getTeacherPreferences());
    errorMessage.append(getTeacherTimePreference());
    errorMessage.append(getTeacherCreditload());
    errorMessage.append(getNoTeacherCredit());
    errorMessage.append(getStudentCourses());
    errorMessage.append(getStudentTime());
    return errorMessage.toString();
  }
  
  /**
   * Displays all constraints.
   */
  public String displayConstraints() {
    final StringBuilder errorMessage = new StringBuilder();
    errorMessage.append(getAdvisorPreferences());
    errorMessage.append(getTeacherSameTimeConflicts());
    errorMessage.append(getTeacherPreferences());
    errorMessage.append(getTeacherTimePreference());
    errorMessage.append(getStudentCourses());
    errorMessage.append(getStudentTime());
    errorMessage.append(getTeacherCreditload());
    errorMessage.append(getNoTeacherCredit());
    return errorMessage.toString();
  }
  
  
  /**
   * 
   * @return The list of all teachers who exceed their credit set load.  
   */
  private String getTeacherCreditload() {
    final StringBuilder errorMessage = new StringBuilder();
    errorMessage
        .append("The following teachers have more credits assigned then their preset limit: \n");
    List<Teacher> missingCourses = my_Constraints.checkCreditLoad();
    if(missingCourses.isEmpty()){
      errorMessage.append("\nNone\n");
    } else {
      for (Teacher aTeacher : missingCourses) {
        errorMessage.append(aTeacher.getName() + "\n");
      }
    }
    return errorMessage.toString();
  }
  
  /**
   * 
   * @return The list of all teachers who have no classes.  
   */
  private String getNoTeacherCredit() {
    final StringBuilder errorMessage = new StringBuilder();
    errorMessage
        .append("The following teachers have no courses: \n");
    List<Teacher> missingCourses = my_Constraints.checkNoCredits();
    if(missingCourses.isEmpty()){
      errorMessage.append("\nNone");
    } else {
      for (Teacher aTeacher : missingCourses) {
        errorMessage.append(aTeacher.getName() + "\n");
      }
    }
    return errorMessage.toString();
  }

  /**
   * 
   * @return Al courses being suggested by an advisor but not offered.  
   */
  private String getAdvisorPreferences() {
    final StringBuilder errorMessage = new StringBuilder();
    errorMessage
        .append("The following courses are not included in the schedule and have been requested by an advisor: \n");
    List<Course> missingCourses = my_Constraints.checkAdvisorPreferences();
    if(missingCourses.isEmpty()){
      errorMessage.append("None\n");
    } else {
      for (Course aCourse : missingCourses) {
        errorMessage.append(aCourse.getCourseTitle() + "\n");
      }
    }
    return errorMessage.toString();
  }

  /**
   * 
   * @return The List of all courses that are being taught by the same teacher at the same time.
   */
  private String getTeacherSameTimeConflicts() {
    final StringBuilder errorMessage = new StringBuilder();
    errorMessage.append("The following courses are taught by the same teacher at the same time:\n");
    List<CourseCopy> dissLikedCourses = my_Constraints.teacherSameTimes();
    if(dissLikedCourses.isEmpty()){
      errorMessage.append("None\n");
    } else {
      for (CourseCopy course : dissLikedCourses) {
        errorMessage.append(course.getCourseTitle() + "\n");
      }
    }
    return errorMessage.toString();
  }

  /**
   * 
   * @return List of all courses that are being taught by teachers who have
   *         specified the course time is outside of the ability.
   */
  private String getTeacherTimePreference() {
    final StringBuilder errorMessage = new StringBuilder();
    errorMessage.append("The following courses are taught by a teacher outside of the teachers avaliablity:\n");
    List<CourseCopy> dissLikedCourses = my_Constraints.checkTeacherPreference();
    if(dissLikedCourses.isEmpty()){
      errorMessage.append("None\n");
    } else {
      for (CourseCopy course : dissLikedCourses) {
        errorMessage.append(course.getCourseTitle() + "\n");
      }
    }
    return errorMessage.toString();
  }

  /**
   * 
   * @return List of all courses that are being taught by teachers who had the
   *         courses on their dislike list.
   */
  private String getTeacherPreferences(){
    final StringBuilder errorMessage = new StringBuilder();
    errorMessage
        .append("The following courses are taught by teachers who stated they did not wish to teach the course:\n");
    List<CourseCopy> dissLikedCourses = my_Constraints.checkTeacherPreference();
    if(dissLikedCourses.isEmpty()){
      errorMessage.append("None\n");
    } else {
      for (CourseCopy course : dissLikedCourses) {
        errorMessage.append(course.getTeacher()+" "+course.getCourseTitle() + "\n");
      }
    }
    return errorMessage.toString();
  }
  
  /**
   * 
   * @return List courses requested by students but not offered.
   */
  private String getStudentCourses(){
    final StringBuilder errorMessage = new StringBuilder();
    errorMessage
        .append("The following courses have been requested by "+ Constraints.COURSE_PREFERENCE_THRESHOLD * 100 +"% of all students but are not being offered:\n");
    List<Course> dissLikedCourses = my_Constraints.checkStudentCoursePreferences();
    if(dissLikedCourses.isEmpty()){
      errorMessage.append("None\n");
    } else {
      for (Course course : dissLikedCourses) {
        errorMessage.append(course.getCourseTitle() + "\n");
      }
    }
    return errorMessage.toString();
  }
  
  /**
   * 
   * @return List of all courses offered but not at the student requested times. 
   */
  private String getStudentTime(){
    final StringBuilder errorMessage = new StringBuilder();
    errorMessage
        .append("The following courses have been requested by "+ Constraints.TIME_PREFERENCE_THRESHOLD * 100 +"% of all students but are not being offered at the desired times:\n");
    List<Course> dissLikedCourses = my_Constraints.checkStudentTimePreferences();
    if(dissLikedCourses.isEmpty()){
      errorMessage.append("None\n");
    } else {
      for (Course course : dissLikedCourses) {
        errorMessage.append(course.getCourseTitle() + "\n");
      }
    }
    return errorMessage.toString();
  }
  
  
}
