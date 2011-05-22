
package sourcepac;
/**
 * Will add comments later need sleep
 * @author Phillip
 * @version 1.0 5/21/2011
 */
public class Time {
  private final int my_start_time;
  private final int my_end_time;
  
  public Time(int the_start_time, int the_end_time) {
    my_start_time = the_start_time;
    my_end_time = the_end_time;
  }
  
  public int getStartTime() {
    return my_start_time;
  }
  
  public int getEndTime() {
    return my_end_time;
  }
}
