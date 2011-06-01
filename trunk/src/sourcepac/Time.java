
package sourcepac;
/**
 * Will add comments later need sleep
 * @author Phillip Bernard;
 * @version 1.0 5/21/2011
 */
public class Time implements Comparable{
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
