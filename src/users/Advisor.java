
package users;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import sourcepac.Course;

/**
 * This class holds the information about and advisor and the suggestions for
 * courses that the advisor has.
 * 
 * @author Phillip Bernard
 * @version 5/10/2011
 */
public class Advisor extends Voter {
  private List<Course> my_courses;
  /**
   * This constructor creates an Advisor object with the given parameters.
   * 
   * @param the_username is the users login ID
   * @param the_password is the phrase that authenticates the user
   * @param the_name is the user's real name
   * @param the_courses the courses the teacher prefers
   */
  public Advisor(String the_username, String the_password, String the_name,
                 List<Course> the_courses) {
    super(the_username, the_password, the_name, new HashSet<Course>());
    my_courses = the_courses;
  }
  
  public List<Course> getPreferredCourseList() {
    return my_courses;
  }
  
  public boolean equals(Object the_object) {
    boolean result = false;
    if(this == the_object){
      result = true;
    }else if (the_object != null && the_object.getClass() == getClass()){
      Advisor other_advisor = (Advisor) the_object;
      
      if(this.getName().equals(other_advisor.getName()) && 
          this.getUsername().equals(other_advisor.getUsername()) &&
          this.getPassword().equals(other_advisor.getPassword())) {
        
        List<Course> other_courses = other_advisor.getPreferredCourseList();
        if(other_courses.size() == my_courses.size() && 
                             other_courses.containsAll(my_courses)) {
          result = true;
        }
      }
    }
    return result;
  }

}
