package tests;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

import org.junit.Before;

import sourcepac.Course;
import users.Teacher;

/**
 * 
 * @author Steven Cozart
 * @version 5/31/2011
 */
public class TeacherTest {
  
  public static final int MAX_CREDIT = 25;
  
  public static final int CUR_CREDIT = 0;
  
  private Teacher my_teacher;
  

  
  /**
   * Sets up default teacher object.
   * @author Steven Cozart 
   *
   */
  @Before
  public final void startUp() {
    Set<Course> desiredCourses = new HashSet<Course>();
    desiredCourses.add(new Course(CourseTest.COURSE_NAME, CourseTest.COURSE_DESCRIPTION, CourseTest.COURSE_CREDIT));
    desiredCourses.add(new Course("305", CourseTest.COURSE_DESCRIPTION, 2));
    desiredCourses.add(new Course("306", CourseTest.COURSE_DESCRIPTION, CourseTest.COURSE_CREDIT));
    
    Set<Course> unDesiredCourses = new HashSet<Course>();
    unDesiredCourses.add(new Course("360",  CourseTest.COURSE_DESCRIPTION, 5));
    unDesiredCourses.add(new Course("590",  CourseTest.COURSE_DESCRIPTION, 3));
    unDesiredCourses.add(new Course("372",  CourseTest.COURSE_DESCRIPTION, 5));
    
    int [][] dayBlock = new int [7][7];
    for(int i = 0; i < dayBlock.length; i++){
      for(int j = 0; j < dayBlock.length; j++){
        Random r = new Random();
        switch(r.nextInt(3)){
          case 0:dayBlock[i][j] = 0;
          
          case 1:dayBlock[i][j] = 1;
          
          case 2:dayBlock[i][j] = -1;
        }
        
      }
    }
    
    
    my_teacher =
        new Teacher("jten", "345678912", "Josh Ten", desiredCourses, 25, 0, unDesiredCourses,
                    dayBlock);
  }
}
