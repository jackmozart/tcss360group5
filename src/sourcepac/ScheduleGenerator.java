package sourcepac;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.Scanner;

/**
 * The ScheduleGenerator class
 * @author Chris Davidson
 * @version 5/16/2011
 *
 */
public class ScheduleGenerator {
  
  /**
   * The Schedule being generated by this class.  Contains a title
   * and courses read in from a .csv file.
   */
  private Schedule my_schedule;

  public ScheduleGenerator() {
    my_schedule = new Schedule();
  }
  
  /**
   * Imports a schedule from a .csv file in the specified
   * format for constraints checking.
   * @pre fileName represents a valid, existing file containing 
   *      a correctly formatted schedule.
   * @post A Schedule in the format readable by the program. 
   * @param the_file_name The name of the file for importing.
   */
  public void importSchedule(String the_file_name) {
    final File inputSchedule = new File(the_file_name);
    Scanner in = null;
    String current = "";
    String[] line = new String[8];
    CourseCopy course;
    try {
      in = new Scanner(inputSchedule);
    } catch (FileNotFoundException exception) {
      System.err.println("File not found");
    }
    while(in.hasNext()) {
      current = in.nextLine();
      if(!current.startsWith("%")) {
        break; // Skip all of the comments
      }
    }
    my_schedule.setTitle(current.substring(0, current.indexOf(',')));
    if(in.hasNext()) { // Skip the column headers
      current = in.nextLine();
    }
    while (in.hasNext()) {
      current = in.nextLine();
      line = current.split(",");
      course = new CourseCopy(line[0], line[1], line[2], 
                              Integer.parseInt(line[7]),
                              Integer.parseInt(line[5]),
                              Integer.parseInt(line[6]), line[3],
                              parseDays(line[4]));
      my_schedule.addCourse(course);
    }
  }

  /**
   * This method takes weekdays in the given format and
   * stores them in a format useful to constraints checking.
   * @pre days represents days of the week in the format specified
   *      and contains "TBA" if no days are presently scheduled.
   * @post A representation of the days the given course is taught.
   * @param the_days The String representing the days taught.
   * @return A boolean[] with appropriate values for the
   *         days taught.
   */
  private boolean[] parseDays(String the_days) {
    boolean[] week = new boolean[7];
    if(the_days.contains("TBA")) {
      return week;
    } else {
      for(int i = 0; i < the_days.length(); i++) {
        switch(the_days.charAt(i)) {
          case 'N':
            week[0] = true;
            break;
          case 'M':
            week[1] = true;
            break;
          case 'T':
            week[2] = true;
            break;
          case 'W':
            week[3] = true;
            break;
          case 'R':
            week[4] = true;
            break;
          case 'F':
            week[5] = true;
            break;
          case 'S':
            week[6] = true;
        }
      }
      return week;
    }
  }
  
  /**
   * This prints the calculated constraint violations to a text
   * file.
   * @pre the_file_name is a valid file name and the_constraints have
   * been computed.
   * @post A file containing the violated constraints.
   * @param the_file_name The name of the output file
   * @param the_constraints The representation of violated constraints
   */
  public void outputConstraintViolations(String the_file_name,
                                String the_constraints) {
    final File output = new File(the_file_name);
    PrintStream out = null;
    try {
      out = new PrintStream(output);
    } catch (FileNotFoundException e) {
      System.err.println("Bad File name");
    }
    
    out.print(the_constraints);
    out.close();
  }

  private static void output(String out) {
    FileOutputStream aFileOutStream;
    PrintStream aPrintStream;
    try
    {
    aFileOutStream = new FileOutputStream("constraints.txt");
    aPrintStream = new PrintStream( aFileOutStream );
    aPrintStream.format(out);
    aPrintStream.close();
    }
    catch (Exception e)
    {
    System.err.println ("Error writing to file");
    }

    }

  /**
   * Getter method for the Schedule being generated.  Contains no
   * relevant information if no Schedule has been imported.
   * @return The Schedule, generated or not.
   */
  public Schedule getSchedule() {
    return my_schedule;
  }

}
