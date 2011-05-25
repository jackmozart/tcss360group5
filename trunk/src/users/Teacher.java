
package users;

import java.util.Set;

import sourcepac.Course;

/**
 * This class represent the data for a teacher's preferences and profile It
 * holds the teachers time's they are available to teach the maximum credit load
 * they are willing to take on, the classes they are willing and unwilling to
 * teach.
 * 
 * @author Phillip Bernard
 * @version 5/15/2011
 */
public class Teacher extends Voter {
  /**
   * This is the constant for the default number of credits a teacher can teach.
   */
  public static final int DEFAULT_MAX_CREDIT_LOAD = 25;
  /**
   * This field holds the courses the teacher is unwilling to teach.
   */
  private Set<Course> my_unpreferred_courses;
  /**
   * This field holds an availability matrix of the times the teacher is willing
   * and unwilling to teach.
   */
  private int[][] my_availability;
  /**
   * This field holds the maximum credits the teacher is willing to take on.
   */
  private int my_max_credit_load;
  /**
   * This field holds the number of credits the teacher is currently assigned.
   */
  private int my_current_credit_load;
  
  /**
   * This constructor creates a Teacher object with the given parameters.
   * 
   * @param the_username is the users login ID
   * @param the_password is the phrase that authenticates the user
   * @param the_name is the user's real name
   * @param the_courses the courses the teacher prefers
   * @param the_unpreferred_courses the courses the teacher does not prefer
   * @param the_availability the times the teacher is available to teach
   */
  public Teacher(String the_username, String the_password, String the_name,
                         Set<Course> the_courses,                                               
                                      Set<Course> the_unpreferred_courses,
                                                int[][] the_availability) {
    this(the_username, the_password, the_name, the_courses, 
         DEFAULT_MAX_CREDIT_LOAD, 0, the_unpreferred_courses, 
                                            the_availability);

  }
  /**
   * This constructor creates a Teacher object with the given parameters.
   * 
   * @param the_username is the users login ID
   * @param the_password is the phrase that authenticates the user
   * @param the_name is the user's real name
   * @param the_courses the courses the teacher prefers
   * @param the_unpreferred_courses the courses the teacher does not prefer
   * @param the_availability the times the teacher is available to teach
   */
  public Teacher(String the_username, String the_password, String the_name,
                         Set<Course> the_courses, int the_current_credit,                                               
                                      Set<Course> the_unpreferred_courses,
                                                int[][] the_availability) {
    this(the_username, the_password, the_name, the_courses, 
               DEFAULT_MAX_CREDIT_LOAD, the_current_credit, 
                 the_unpreferred_courses, the_availability);

  }
  /**
   * This constructor creates a Teacher object with the given parameters.
   * 
   * @param the_username is the users login ID
   * @param the_password is the phrase that authenticates the user
   * @param the_name is the user's real name
   * @param the_courses the courses the teacher prefers
   * @param the_max_credit_load the max credit load of the teacher
   * @param the_curr_credit_load = the current credit load that the teacher has
   * @param the_unpreferred_courses the courses the teacher does not prefer
   * @param the_availability the times the teacher is available to teach
   */
  public Teacher(String the_username, String the_password, String the_name,
                         Set<Course> the_courses, int the_max_credit_load,
                                                 int the_curr_credit_load,
                                      Set<Course> the_unpreferred_courses,
                                                int[][] the_availability) {
    super(the_username, the_password, the_name, the_courses);
    my_max_credit_load = the_max_credit_load;
    my_current_credit_load = the_curr_credit_load;
    my_unpreferred_courses = the_unpreferred_courses;
    my_availability = the_availability;
    
    my_max_credit_load = DEFAULT_MAX_CREDIT_LOAD;

  }
  
  /**
   * This method gets the courses that are unpreferred for this teacher.
   * 
   * @author Christian Tomyn
   * @return the unpreferred courses
   */
  public Set<Course> getUnpreferedCourses() {
    return my_unpreferred_courses;
  }
  
  /**
   * 
   * @return
   */
  public int getCurrCredits(){
    return my_current_credit_load;
  }
  
  /**
   * 
   * @param the_credits
   */
  public void setCurrCredits(int the_credits){
    my_current_credit_load = the_credits;
  }
  
  /**
   * 
   * @return
   */
  public int getMaxCredits(){
    return my_max_credit_load;
  }
  
  public void setMaxCredits(int the_credits){
    my_max_credit_load = the_credits;
  }

  public int getCreditLoad() {
    // TODO Auto-generated method stub
    return 0;
  }


  public int getCredits() {
    // TODO Auto-generated method stub
    return 0;
  }
}
