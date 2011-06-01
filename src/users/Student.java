
package users;

import java.util.Collections;
import java.util.List;
import java.util.Set;

import sourcepac.Course;
import sourcepac.Time;

/**
 * This class holds the preference data for a Student.
 * 
 * @author Phillip Bernard
 * @version 2.0 5/31/2011 Equals method and comments
 * @version 1.0 5/11/2011
 */
public class Student extends Voter {
  /**
   * This field holds the times the student prefers to take courses.
   */
  private List<Time> my_times;

  private Set<Course> my_course;
  /**
   * This constructor creates a Student object with the given parameters.
   * 
   * @param the_username is the users login ID
   * @param the_password is the phrase that authenticates the user
   * @param the_name is the user's real name
   * @param the_courses the courses the student prefers
   * @param the_times the times the student has selected for courses
   */
  public Student(String the_username, String the_password, String the_name,
                 Set<Course> the_courses, List<Time> the_times) {
    super(the_username, the_password, the_name, the_courses);
    my_course = the_courses;
    my_times = the_times;
  }
  /**
   * Gets a set of courses that the student wants to take.
   * 
   * @return a set of courses
   */
  public Set<Course> getCourses() {
    return Collections.unmodifiableSet(my_course);
  }
  
  /**
   * Gets a list of times that the student chose for classes.
   * 
   * @return a list of times
   */
  public List<Time> getTimes() {
    return Collections.unmodifiableList(my_times);
  }
  
  /**
   * {@inheritDoc}
   */
  public boolean equals(Object the_object) {
    boolean result = false;
    if(this == the_object){
      result = true;
    }else if (the_object != null && the_object.getClass() == getClass()){
      Student other_student = (Student) the_object;
      
      if(this.getName().equals(other_student.getName()) && 
          this.getUsername().equals(other_student.getUsername()) &&
          this.getPassword().equals(other_student.getPassword())) {
        
        Set<Course> other_courses = other_student.getPreferredCourses();
        if(other_courses.size() == getPreferredCourses().size() && 
                          other_courses.containsAll(getPreferredCourses())) {
          if(other_student.getTimes().size() == getPreferredCourses().size() && 
              other_student.getTimes().containsAll(getPreferredCourses())) {
          
            result = true;
          }
        }
      }
    }
    return result;
  }
}
