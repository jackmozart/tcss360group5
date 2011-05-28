
package users;

import java.util.Iterator;
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
public class Teacher extends Voter implements Comparable{
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

  /**
   * 
   */
  public int compareTo(Object other_object) {
    int result = 1;
    if(this == other_object){
       result = 0;
     }else if (other_object != null && other_object.getClass() == getClass()){
       Teacher other_teach = (Teacher) other_object;
       if (this.getName().equals(other_teach.getName()) &&
           this.getUsername().equals(other_teach.getUsername()) &&
           this.getPassword().equals(other_teach.getPassword()) &&
           this.my_max_credit_load == other_teach.my_max_credit_load &&
           this.my_current_credit_load == other_teach.my_current_credit_load &&
           this.my_unpreferred_courses.size() == other_teach.my_unpreferred_courses.size() &&
           this.getPreferedCourses().size() == other_teach.getPreferedCourses().size()) {
         result = 0;  
         Iterator<Course> pref_itr = other_teach.getPreferedCourses().iterator();
         while(pref_itr.hasNext()) {
           if(!other_teach.getPreferedCourses().contains(pref_itr.next())) {
             result = 1;
           }
         }
         Iterator<Course> unpref_itr = other_teach.getPreferedCourses().iterator();
         while(unpref_itr.hasNext()) {
           if(!other_teach.getPreferedCourses().contains(unpref_itr.next())) {
             result = 1;
           }
         }
         
         
       }else {
         result = 1;
       }
     }
    return result;
  }
  
  public boolean equals(Object other_object){
    boolean result = false;
    
    if (compareTo(other_object) == 0) {
      result = true;
    }
    return result;
    
  }

  public void setCredits(int i) {
    // TODO Auto-generated method stub
    
  }


}
