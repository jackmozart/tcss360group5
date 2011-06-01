
package sourcepac;
/**
 * Holds a single time block consisting of a start and end time. 
 * @author Steven Cozart comments 
 * @author Phillip Bernard;
 * @version 1.0 5/21/2011
 */
public class Time implements Comparable{
  /**
   * The start time of the block.
   */
  private final int my_start_time;
  
  /**
   * The end time of the block.
   */
  private final int my_end_time;
  
  /***
   * 
   * @param the_start_time The beginning time of the time block.
   * @param the_end_time The ending time of the time block.  
   */
  public Time(int the_start_time, int the_end_time) {
    my_start_time = the_start_time;
    my_end_time = the_end_time;
  }
  
  /**
   * 
   * @return The beginning time of the time block.
   */
  public int getStartTime() {
    return my_start_time;
  }
  
  /**
   * 
   * @return The ending time of the time block
   */
  public int getEndTime() {
    return my_end_time;
  }

  /**
   * @return -1 if time is before the given time, 0 if equal, and 1 if after.  
   */
  @Override
  public int compareTo(Object other_object) {
    int result = 1;
    if(this == other_object){
      result = 0;
    }else if (other_object != null && other_object.getClass() == getClass()){
      Time other_time = (Time) other_object;
      if (my_start_time == other_time.getStartTime() &&
                my_end_time == other_time.getEndTime()){
        result = 0;
      } else if (my_start_time < other_time.getStartTime()){
        result = -1;
      } else {
        result = 1;
      }
    }
    return result;
  }
  
  /**
   * {@inheritDoc}
   */
  public boolean equals(Object the_other) {
    boolean result = false;
    
    if (compareTo(the_other) == 0) {
      result = true;
    }
    
    return result;
  }
}
