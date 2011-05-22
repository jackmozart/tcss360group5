
package sourcepac;

import java.util.ArrayList;
import java.util.List;

import users.Advisor;
import users.Student;
import users.Teacher;
import users.Voter;

/**
 * This Class manages user roles for a specific user.
 * 
 * @author
 * @version 5/13/2011
 */
public class UserRoleList {
  private Advisor my_advisor;
  private Student my_student;
  private Teacher my_teacher;
  
  public UserRoleList() {
    my_advisor = null;
    my_student = null;
    my_teacher = null;
  }
  
  public void setAdvisor(Advisor the_role) {
    my_advisor = the_role;
  }
  
  public Advisor getAdvisor() {
    return my_advisor;
  }
  
  public void setTeacher(Teacher the_role) {
    my_teacher = the_role;
  }
  
  public Teacher getTeacher() {
    return my_teacher;
  }

  public void setStudent(Student the_role) {
    my_student = the_role;
  }
  
  public Student getStudent() {
    return my_student;
  }
  
  public boolean contains(Voter the_role) {
    final char role = the_role.getClass().getSimpleName().charAt(0);
    boolean result = false;
    switch(role) {
      case 'A' :
        if (my_advisor != null) {
          result = true;
        }
        break;
      case 'S' :
        if (my_student != null) {
          result = true;
        }
        break;
      case 'T' :
        if (my_teacher != null) {
          result = true;
        }
        break;
      default  :
       //do nothing
        break;
    }
    
    return result;
  }

}
