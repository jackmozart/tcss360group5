package users;

import java.util.List;
import java.util.Set;

import sourcepac.Course;
import sourcepac.Time;
/**
 * This class holds the preference data for a Student.
 * 
 * @author Phillip Bernard
 * @version 5/10/2011
 */
public class Student extends Voter {
	/**
	 * This field holds the times the student prefers to take courses.
	 */
	private List<Time> my_times;
	
	/**
	 * This constructor creates a Student object with the given parameters
	 * 
	 * @param the_username is the users login ID
	 * @param the_password is the phrase that authenticates the user
	 * @param the_name is the user's real name
	 * @param the_courses the courses the student prefers
	 */
	public Student(String the_username, String the_password, String the_name,
			Set<Course> the_courses, List<Time> the_times) {
		super(the_username, the_password, the_name, the_courses);
		
		my_times = the_times;
		
	}

}
