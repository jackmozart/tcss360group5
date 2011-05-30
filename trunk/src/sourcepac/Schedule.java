
package sourcepac;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import users.Advisor;
import users.Student;
import users.Teacher;



/**
 * Stores courses together in a schedule for easier constraint
 * checking and output and creates schedule.
 * 
 * @author Chris Davidson
 * @author Christian Tomyn
 * @version 5/22/2011
 *
 */
public class Schedule {

  private List<CourseCopy> my_courses;
  
  /**
   * Adds the course to the list of possible courses if the students preferred it
   * more than this many amounts of time.
   */
  public static final int ADD_COURSE = 10;
  
  /**
   * Determines whether there be two of the same courses added to the list
   */
  public static final int ADD_SECOND_COURSE = 20;
  
  /** The Schedule's title, i.e.: "Autumn 2010" */
  private String my_title;
  
  /**
   * Creates an empty Schedule with no initial title.
   */
  public Schedule() {
    this(null);
  }
  
  /**
   * Creates an empty Schedule with a title.  Used to aid testing.
   * @param quarter The title for the Schedule.
   */
  public Schedule(String quarter) {
    my_courses = new ArrayList<CourseCopy>();
    my_title = quarter;
  }
  
  /**
   //Check random and array initialization to determine if
   //array out of bounds error will occur.
   * 
   * Generates a schedule based on the input param and outputs the schedule.
   * 
   * @author Christian Tomyn
   * @param a_course_list
   * @param a_student_list
   * @param a_teacher_list
   * @param an_advisor_list
   * @return The generated schedule
   */
  public List<CourseCopy> generateSchedule(List<Course> a_course_list,
                                           List<Student> a_student_list,
                                           List<Teacher> a_teacher_list,
                                           List<Advisor> an_advisor_list)  {
    List<Course> finished_course_list = new ArrayList<Course>();
    int[] student_preference_adder = new int[a_course_list.size()];
    int[] number_of_teacher_courses;
    /*
     * Counts the number of votes per class offered for students
     */
    for(int i = 0; i < a_student_list.size(); i++)  {
      for(int j = 0; j < a_course_list.size(); j++)  {
//        for(int k = 0; k < a_student_list.get(i).getPreferedCourses().size(); k++)  {
          if (a_student_list.get(i).getPreferredCourses().contains(a_course_list.get(j)))  {
            student_preference_adder[j]++;
//          }
        }  
      }
    }
    
    /*
     * Add course to finished course list if the students voted for the class more
     * than the specified amount to add a course.
     */
    for(int i = 0; i < a_course_list.size(); i++)  {
      if(student_preference_adder[i] >= ADD_COURSE)  {
        finished_course_list.add(a_course_list.get(i));
        if(student_preference_adder[i] >= ADD_SECOND_COURSE)  {
          finished_course_list.add(a_course_list.get(i));
        }
      }
    }
    
    /*
     * Adds an advisor preference to the list of finished_course_list
     * if it is not already there.
     */
    for(int i = 0; i < finished_course_list.size(); i++) {
      for(int j = 0; j < an_advisor_list.size(); j++)  {
        Iterator<Course> advisor_iter = an_advisor_list.get(j).getPreferredCourses().iterator();
        while (advisor_iter.hasNext())  {
          Course current = advisor_iter.next();
          if(current != finished_course_list.get(i))  {
            finished_course_list.add(current);
          }
        }
      }
    }
    /*
     * Assigns the teachers at random to a course and time to teach the course unless
     * the course is unpreferred or until the teacher has been assigned to three classes.
     */
    number_of_teacher_courses = new int[a_teacher_list.size()];
    for(int i = 0; i < finished_course_list.size(); i++)  {
      Random rand = new Random(a_teacher_list.size());
      int rand_int = rand.nextInt();
      
      /*
       * Checks if a teacher has been assigned to teach three courses already.
       */
      for(int j = 0; j < number_of_teacher_courses.length; j++)  {
        if(number_of_teacher_courses[j] > 3 && a_teacher_list.get(j) != null)  {
          a_teacher_list.set(j, null);
        }
      }
      
      /*
       * Adds the course to my_courses. If it's not prefered by any
       * teacher a random teacher will be chosen. If the number of classes
       * outweighs the teachers then nothing will be added.
       */
      //Need to finish time assignment for this course copy adding.
      //not sure how we will represent the time slots or days.
      if(a_teacher_list.size() > finished_course_list.size()/3)  {
        System.out.print("Did not create schedule. Not enough teachers.\n" +
        		"Must have at least one-third the amount of teachers than classes");
      } else if(a_teacher_list.get(rand_int) == null)  {
        i--;
      } else if(a_teacher_list.get(rand_int).getUnpreferedCourses()
          .contains(finished_course_list.get(i))) {
        int k = 0;
        boolean has_added = false;
        while(k <= a_teacher_list.size() && !has_added)  {
          if(!a_teacher_list.get(k).getUnpreferedCourses()
          .contains(finished_course_list.get(i)) &&
            a_teacher_list.get(rand_int) != null)  {
            addCourse(new CourseCopy(finished_course_list.get(i).getCourseTitle(),
                                     "course section",
                                     finished_course_list.get(i).getCourseDescription(),
                                     finished_course_list.get(i).getCredit(),
                                     8, 10, a_teacher_list.get(k).getName(), new boolean[7]));
            number_of_teacher_courses[k]++;
            has_added = true;
          } else if(k == a_teacher_list.size()) {
            addCourse(new CourseCopy(finished_course_list.get(i).getCourseTitle(),
                                     "course section",
                                     finished_course_list.get(i).getCourseDescription(),
                                     finished_course_list.get(i).getCredit(),
                                     8, 10, a_teacher_list.get(rand_int).getName(), new boolean[7]));
            has_added = true;
          }
          k++;
        }
      } else {
        addCourse(new CourseCopy(finished_course_list.get(i).getCourseTitle(),
                                 "course section",
                                 finished_course_list.get(i).getCourseDescription(),
                                 finished_course_list.get(i).getCredit(),
                                 8, 10, a_teacher_list.get(rand_int).getName(), new boolean[7]));
        number_of_teacher_courses[rand_int]++;
      }
    }
    return getCourses();
  }
  
  /**
   * Sets the Schedule's title.  Replaces any previous title.
   * @param quarter The new title.
   */
  public void setTitle(String quarter) {
    my_title = quarter;
  }
  /**
   * Retrieves the Schedule's title.  Returns null if none exists.
   * @return A String representing the title.
   */
  public String getTitle() {
    return my_title;
  }
  
  /**
   * Adds a CourseCopy to the Schedule, maintaining the order entered. 
   * @param course The CourseCopy to be added.
   */
  public void addCourse(CourseCopy course) {
    my_courses.add(course);
  }

  /**
   * Retrieves the current CourseCopies in the Schedule, entry order
   * maintained.
   * @return A List of CourseCopies
   */
  public List<CourseCopy> getCourses() {
    return my_courses;
  }

}
