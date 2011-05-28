package sourcepac;

import users.Teacher;

/**
 * This Class holds the preferences or a specific teacher as well a reference
 * to update any changes that need to be applied to it
 * 
 * @author Phillip Bernard
 * @version 1.0 5/28/2011
 */
public class TeacherPreference {
  Teacher my_teacher;
  
  public TeacherPreference(Teacher the_teacher) {
    my_teacher = the_teacher;
  }
  
  /**
   * This method returns a modifiable teacher that is associated to this 
   * preference object.
   * 
   * @return Teacher
   */
  public Teacher getTeacher() {
    return my_teacher;
  }
  
  
  public int getCreditLoad() {
    
    return my_teacher.getCurrCredits();
  }

  public Object getName() {
    // TODO Auto-generated method stub
    return null;
  }

}
