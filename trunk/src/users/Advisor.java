package users;

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
	
	/**
	 * This constructor creates an Advisor object with the given parameters
	 * 
	 * @param the_username is the users login ID
	 * @param the_password is the phrase that authenticates the user
	 * @param the_name is the user's real name
	 * @param the_courses the courses the teacher prefers
	 */
	public Advisor(String the_username, String the_password, String the_name,
			Set<Course> the_courses) {
		super(the_username, the_password, the_name, the_courses);
		
	}

}
