/**
 * 
 */
package sourcepac;

/**
 * @author Steven Cozart
 * @author Christian Tomyn
 *
 */
public class StudentPrefrence {
  
  private String my_prefrence;
  
  private String my_student_name;
  
  public StudentPrefrence(String a_name, String a_prefrence)  {
    my_student_name = a_name;
    my_prefrence = a_prefrence;
  }
  
  public String getPrefrences() {
    return my_prefrence;  
  }
}
