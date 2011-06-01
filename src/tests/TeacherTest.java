package tests;

import static org.junit.Assert.*;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;

import sourcepac.Course;
import sourcepac.CourseCopy;
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
  
  /**
   * Tests a unsorted assignment of classes and sees if they are sorted.
   */
  @Test
  public void courseSortTest(){
    boolean [] daysTaught = new boolean [7];
    daysTaught[1] = true;
    daysTaught[3] = true;

    my_teacher.addCourse(new CourseCopy("TCSS 343", "A", "A TCSS class", 5, 800, 1000,
                                        NumberOneConstraintTest.TEACHER_NAME_ONE, daysTaught));
    my_teacher.addCourse(new CourseCopy("TCSS 360", "A", "A TCSS class", 5, 1230, 12400,
                                        NumberOneConstraintTest.TEACHER_NAME_ONE, daysTaught));
    my_teacher.addCourse(new CourseCopy("TCSS 342", "A", "A TCSS class", 5, 1000, 1200,
                                        NumberOneConstraintTest.TEACHER_NAME_ONE, daysTaught));
    my_teacher.addCourse(new CourseCopy("TCSS 332", "A", "A TCSS class", 5, 1300, 1500,
                                        NumberOneConstraintTest.TEACHER_NAME_ONE, daysTaught));

    Set<CourseCopy> expected = new HashSet<CourseCopy>();
    expected.add(new CourseCopy("TCSS 343", "A", "A TCSS class", 5, 800, 1000,
                                NumberOneConstraintTest.TEACHER_NAME_ONE, daysTaught));
    expected.add(new CourseCopy("TCSS 342", "A", "A TCSS class", 5, 1000, 1200,
                                NumberOneConstraintTest.TEACHER_NAME_ONE, daysTaught));
    expected.add(new CourseCopy("TCSS 360", "A", "A TCSS class", 5, 1230, 1240,
                                NumberOneConstraintTest.TEACHER_NAME_ONE, daysTaught));
    expected.add(new CourseCopy("TCSS 332", "A", "A TCSS class", 5, 1300, 1500,
                                NumberOneConstraintTest.TEACHER_NAME_ONE, daysTaught));

    assertEquals("The courses are out of order.", expected, my_teacher.getCourses());

  }
  
  /**
   * Tests a unsorted assignment of classes and sees if they are sorted.
   */
  @Test
  public void clearCoursesTest(){
    boolean [] daysTaught = new boolean [7];
    daysTaught[1] = true;
    daysTaught[3] = true;

    my_teacher.addCourse(new CourseCopy("TCSS 343", "A", "A TCSS class", 5, 800, 1000,
                                        NumberOneConstraintTest.TEACHER_NAME_ONE, daysTaught));
    my_teacher.addCourse(new CourseCopy("TCSS 360", "A", "A TCSS class", 5, 1230, 12400,
                                        NumberOneConstraintTest.TEACHER_NAME_ONE, daysTaught));
    my_teacher.addCourse(new CourseCopy("TCSS 342", "A", "A TCSS class", 5, 1000, 1200,
                                        NumberOneConstraintTest.TEACHER_NAME_ONE, daysTaught));
    my_teacher.addCourse(new CourseCopy("TCSS 332", "A", "A TCSS class", 5, 1300, 1500,
                                        NumberOneConstraintTest.TEACHER_NAME_ONE, daysTaught));
    my_teacher.clearCourses();
    assertTrue("The courses were not cleared.", my_teacher.getCourses().isEmpty());

  }
}
