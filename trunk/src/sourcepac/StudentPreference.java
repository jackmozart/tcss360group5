/**
 * 
 */
package sourcepac;

/**
 * @author Steven Cozart
 * @author Christian Tomyn
 *
 */
public class StudentPreference {
  
  private String my_preference;
  
  private String my_student_name;
  
  public StudentPreference(String a_name, String a_preference)  {
    my_student_name = a_name;
    my_preference = a_preference;
  }
  
  public String getPrefrences() {
    return my_preference;  
  }
}
