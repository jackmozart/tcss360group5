
package users;

import java.util.Set;

import sourcepac.Course;

/**
 * This Class is a super class to all classes that hold preferences, it holds
 * the courses that are preferred.
 * 
 * @author Phillip Bernard
 * @version 5/10/2011
 */
public class Voter extends AuthenticatableUser {
  /**
   * This field holds the classes that are preferred.
   */
  private Set<Course> my_preferred_courses;

  /**
   * This constructor creates a Voter object with the given parameters.
   * 
   * @param the_username is the users login ID
   * @param the_password is the phrase that authenticates the user
   * @param the_name is the user's real name
   * @param the_courses the courses that are preferred
   */
  public Voter(String the_username, String the_password, String the_name,
               Set<Course> the_courses) {

    super(the_username, the_password, the_name);
    my_preferred_courses = the_courses;
  }

  /**
   * This method gets the courses that are preferred for this individual user.
   * 
   * @return the preferred courses
   */
  public Set<Course> getPreferredCourses() {
    return my_preferred_courses;
  }

  /**
   * This method adds a course to the Set of preferred courses is it is not
   * already in the Set.
   * 
   * @param the_course The course to add to the Set
   */
  public void addPreferredCourse(Course the_course) {
    my_preferred_courses.add(the_course);
  }
}
