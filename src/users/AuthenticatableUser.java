package users;
/**
 * This class is the Super class for all other users. It contains user login
 * information and the name of the specific user.
 * 
 * @author Phillip Bernard
 * @version 5/10/2011
 */
public class AuthenticatableUser {
	/**
	 * This field stores the username so the user can be uniquely identified.
	 */
	private String my_username;
	/**
	 * This field stores the phrase for authenticating the user.
	 */
	private String my_password;
	/**
	 * This field is stores the users real name.
	 */
	private String my_name;
	
	/**
	 * Constructor that loads the username, password and real name of the user.
	 * 
	 * @param the_username is the users login ID
	 * @param the_password is the phrase that authenticates the user
	 * @param the_name is the user's real name
	 */
	public AuthenticatableUser (String the_username, String the_password, 
													     String the_name) {
		my_username = the_username;
		my_password = the_password;
		my_name = the_name;
	}
	
	public String getUsername() {
		return my_username;
	}
	
	public String getPassword() {
		return my_password;
	}
	
	public String getName() {
		return my_name;
	}
}
