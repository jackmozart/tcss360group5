
package users;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import sourcepac.Course;
import sourcepac.CourseCopy;

/**
 * This class represent the data for a teacher's preferences and profile It
 * holds the teachers time's they are available to teach the maximum credit load
 * they are willing to take on, the classes they are willing and unwilling to
 * teach.
 * 
 * @author Phillip Bernard  
 * @author Steven Cozart Many modifications and inclusion of several new methods
 * @version 5/31/2011
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
   * Stores all courses taught by a teacher sorted by time.
   */
  private Set<CourseCopy> my_courses;
  
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
    my_courses = new HashSet<CourseCopy>();
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
   * @author Steven Cozart
   * @return
   */
  public int getCurrCredits(){
    return my_current_credit_load;
  }
  
  /**
   * @author Steven Cozart
   * @param the_credits
   */
  public void setCurrCredits(int the_credits){
    my_current_credit_load = the_credits;
  }
  
  /**
   * @author Steven Cozart
   * @return
   */
  public int getMaxCredits(){
    return my_max_credit_load;
  }
  
  /**
   * @author Steven Cozart
   * @param the_credits
   */
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
           this.getPreferredCourses().size() == other_teach.getPreferredCourses().size()) {
         result = 0;  
         Iterator<Course> pref_itr = other_teach.getPreferredCourses().iterator();
         while(pref_itr.hasNext()) {
           if(!other_teach.getPreferredCourses().contains(pref_itr.next())) {
             result = 1;
           }
         }
         Iterator<Course> unpref_itr = other_teach.getPreferredCourses().iterator();
         while(unpref_itr.hasNext()) {
           if(!other_teach.getPreferredCourses().contains(unpref_itr.next())) {
             result = 1;
           }
         }
         
         
       }else {
         result = 1;
       }
     }
    return result;
  }
  
  /**
   * {@inheritDoc}
   */
  public boolean equals(Object other_object){
    boolean result = false;
    
    if (compareTo(other_object) == 0) {
      result = true;
    }
    return result;
    
  }

  /**
   * @author Steven Cozart
   * @param the_course The course to be assigned to the teacher.
   */
  public void addCourse(CourseCopy the_course){
    my_courses.add(the_course);
    sortCourses();
  }
  
  /**
   * @author Steven Cozart
   * Sorts all courses according to day and time.
   */
  private void sortCourses() {
    if(my_courses.isEmpty()){
      return;
    }
    Set<CourseCopy> allSundayCourses = new HashSet<CourseCopy>();
    Set<CourseCopy> allMondayCourses = new HashSet<CourseCopy>();
    Set<CourseCopy> allTuesdayCourses = new HashSet<CourseCopy>();
    Set<CourseCopy> allWedCourses = new HashSet<CourseCopy>();
    Set<CourseCopy> allThursCourses = new HashSet<CourseCopy>();
    Set<CourseCopy> allFridayCourses = new HashSet<CourseCopy>();
    Set<CourseCopy> allSatCourses = new HashSet<CourseCopy>();

    // go through all courses and sort them by time, (radix type sort)
    for (CourseCopy course : my_courses) {
      boolean[] days = course.getDays();
      if (days[0]) {
        allSundayCourses.add(course);
      } else if (days[1]) {
        allMondayCourses.add(course);
      } else if (days[2]) {
        allTuesdayCourses.add(course);
      } else if (days[3]) {
        allWedCourses.add(course);
      } else if (days[4]) {
        allThursCourses.add(course);
      } else if (days[5]) {
        allFridayCourses.add(course);
      } else {
        allSatCourses.add(course);
      }
    }
    
    //sorts all days by times
    allSundayCourses =  sortTimes(allSundayCourses);
    allMondayCourses = sortTimes(allMondayCourses);
    allTuesdayCourses = sortTimes(allTuesdayCourses);
    allWedCourses = sortTimes(allWedCourses);
    allThursCourses = sortTimes(allThursCourses);
    allFridayCourses = sortTimes(allFridayCourses);
    allSatCourses = sortTimes(allSatCourses);
    
    //clears field and places sorted elements in.
    my_courses.clear();
    my_courses.addAll(allSundayCourses);
    my_courses.addAll(allMondayCourses);
    my_courses.addAll(allTuesdayCourses);
    my_courses.addAll(allWedCourses);
    my_courses.addAll(allThursCourses);
    my_courses.addAll(allFridayCourses);
    my_courses.addAll(allSatCourses);

  }
  
 
  /**
   * Sorts courses according to time.(lowest listed first)
   * @author Steven Cozart
   * @param the_Courses The courses to be sorted.
   */
  private Set<CourseCopy> sortTimes(Set<CourseCopy> the_Courses) {
    if (the_Courses.isEmpty()) {
      return the_Courses;
    }

    int size = the_Courses.size();
    Object[] sortedCourses =  the_Courses.toArray();
    // yay! bubble sort is most efficient.
    for (int i = 0; i < size; i++) {
      for (int j = size - 1; j > i; j--) {
        // gets neighboring objects and compares start time
        if (((CourseCopy) sortedCourses[j - 1]).getTime().getStartTime() < ((CourseCopy) sortedCourses[j]).getTime()
            .getStartTime()) {
          CourseCopy temp = (CourseCopy) sortedCourses[j - 1];
          sortedCourses[j - 1] = sortedCourses[j];
          sortedCourses[j] = temp;
        }
      }
    }
    Set<CourseCopy> sortedSet = new HashSet<CourseCopy>();
    for(Object course: sortedCourses){
      sortedSet.add((CourseCopy) course);
    }
    return sortedSet;
  }
  
  
  
  /**
   * Clears all courses from the course list.
   */
  public void clearCourses(){
    my_courses.clear();
  }
  
  /**
   * @author Steven Cozart
   * @return All courses taught by the teacher 
   */
  public Set<CourseCopy> getCourses (){
    return my_courses;
  }

  /**
   * 
   * @return The teacher availability (negative = dislike, 0 = no preference,
   *         positive = would like)
   */
  public int[][] getTimePreferences() {
    return my_availability;
  }

}
