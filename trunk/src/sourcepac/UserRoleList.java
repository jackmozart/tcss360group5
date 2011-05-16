
package sourcepac;

import java.util.ArrayList;
import java.util.List;

import users.Voter;

/**
 * This Class manages user roles for a specific user.
 * 
 * @author
 * @version 5/13/2011
 */
public class UserRoleList {
  List<Voter> my_roles;
  
  public UserRoleList() {
    my_roles = new ArrayList<Voter>();
  }
  public void add(Voter the_role) {
    
    
  }

  public boolean contains(Voter the_role) {
    
    return false;
  }

}
