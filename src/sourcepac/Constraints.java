package sourcepac;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import users.Advisor;
import users.Student;
import users.Teacher;

/**
 * 
 * @author Steven Cozart
 * 
 * @version 5/31/2011 Added checkStudentCourse and Time Preference
 * @version 5/28/2011 Adjusted checkCreditLoad to use Teacher's methods -PB
 * @version Last update May 25 2011
 */
public class Constraints {

  /**
   * This constant hold the percentage of votes from the entire student
   * body that is needed for a course to be considered a preference. 
   */
  public static final double COURSE_PREFERENCE_THRESHOLD = .1;
    
  private List<Student> my_students;

  private List<CourseCopy> my_courses;

  private List<Teacher> my_teachers;

  private List<Advisor> my_advisors;

  /**
   * 
   * @param the_list
   * @param the_students
   * @param the_advisors
   * @param the_teachers
   */
  public Constraints(List<CourseCopy> the_list, List<Student> the_students,
                     List<Advisor> the_advisors, List<Teacher> the_teachers) {
    my_courses = the_list;
    my_students = the_students;
    my_teachers = the_teachers;
    my_advisors = the_advisors;

  }


  /**
   * Tests if a teacher is teaching two classes at the same time. Error message
   * format (Teacher) is teaching (class1) and (class2) at the same time (start
   * time)".
   * 
   * @pre Requires course list is a sorted list.
   * @return Error message for each violation in which a teacher is teaching two
   *         classes at the same time.
   */
  public List<CourseCopy> teacherSameTimes() {
    List<CourseCopy> dissLikedCourses = new ArrayList<CourseCopy>();
    
    for (Teacher teacher : my_teachers) {
      if (!teacher.getCourses().isEmpty()) { // if teacher has courses
        Set<CourseCopy> allSundayCourses = new HashSet<CourseCopy>();
        Set<CourseCopy> allMondayCourses = new HashSet<CourseCopy>();
        Set<CourseCopy> allTuesdayCourses = new HashSet<CourseCopy>();
        Set<CourseCopy> allWedCourses = new HashSet<CourseCopy>();
        Set<CourseCopy> allThursCourses = new HashSet<CourseCopy>();
        Set<CourseCopy> allFridayCourses = new HashSet<CourseCopy>();
        Set<CourseCopy> allSatCourses = new HashSet<CourseCopy>();

        // go through all courses and sort them by time, (radix type sort)
        for (CourseCopy course : my_courses) {
          boolean[] days = course.getDays();
          if (days[0]) {
            allSundayCourses.add(course);
          } else if (days[1]) {
            allMondayCourses.add(course);
          } else if (days[2]) {
            allTuesdayCourses.add(course);
          } else if (days[3]) {
            allWedCourses.add(course);
          } else if (days[4]) {
            allThursCourses.add(course);
          } else if (days[5]) {
            allFridayCourses.add(course);
          } else {
            allSatCourses.add(course);
          }
        }
        
        //sorts all days by times
        List<Set<CourseCopy>> allDays = new ArrayList<Set<CourseCopy>>();
        allDays.add(allSundayCourses);
        allDays.add(allMondayCourses);
        allDays.add(allTuesdayCourses);
        allDays.add(allWedCourses);
        allDays.add(allThursCourses);
        allDays.add(allFridayCourses);
        allDays.add(allSatCourses);
        //goes through each day checking times to ensure there is no overlap
        for (Set<CourseCopy> day : allDays) {
          if (!day.isEmpty()) {
            int i = 1;
            CourseCopy[] coursesToCompare = (CourseCopy[]) day.toArray();
            for (CourseCopy course : day) {              
              for (int j = i ; j < coursesToCompare.length; j++) {
                if (course.getTime().getEndTime() > coursesToCompare[j].getTime().getStartTime()) {
                  dissLikedCourses.add(course);
                }
              }
              i++;//push start to ensure i don't double check elements
            }
            
          }
        }

      }
    }
    return dissLikedCourses;

  }
   

  /**
   * Checks teacher credit load.
   * 
   * @return Error message in form (teacher) exceeds credit load by (amount) credits.
   */
  public String checkCreditLoad() {
    final StringBuilder error_message = new StringBuilder();
    Teacher teacher = null;
    
    for (CourseCopy course : my_courses) {
      String teacherName = course.getTeacher();
      int i = 0;
      boolean matchFound = false;
      int numTeachers = my_teachers.size();
      
      do{
        teacher = my_teachers.get(i);
        matchFound = !my_teachers.get(i).getName().equals(teacherName);
        i ++;
        //if used to ensure that the list does not go beyond size of list in case of a missing teacher.
        if(
            i > numTeachers){
          Teacher missingTeacher = new Teacher(teacherName, teacherName, teacherName, null, numTeachers, null, null);
          my_teachers.add(missingTeacher);
          teacher = missingTeacher;
          matchFound = false;
        }
      }
      while (matchFound);
      
      teacher.setCurrCredits(  course.getCredit() + teacher.getCurrCredits());
      
    }
    
    for(Teacher teacher_pref : my_teachers){
      if(teacher_pref.getMaxCredits() < teacher.getCurrCredits()){
        //appends error message (teacher) exceeds credit load by (amount) credits.
        error_message.append(teacher_pref.getName());
        error_message.append(" exceeds credit load by ");
        error_message.append(teacher.getCurrCredits()- //PB I adjust this code so that it compiles and  
                                           teacher_pref.getMaxCredits()); //uses the methods already in Teacher
        error_message.append(" credits.\n ");
     }
     }
     return error_message.toString();
  }
  
  /**
   * 
   * @return List of all courses suggested by advisor but not offered.
   */
  public List<Course> checkAdvisorPrefrences(){  
    List<Course> missingCourses = new ArrayList<Course>();
    for(Advisor advisor: my_advisors){
      for(Course preferredCourse:advisor.getPreferredCourseList()){
        String courseName = preferredCourse.getCourseTitle();
        boolean courseFound = false;
        for (CourseCopy courses: my_courses){
          if(courses.getCourseTitle().equals(courseName)){
            courseFound = true;
            break;
          }     
        }
        if(!courseFound){
          missingCourses.add(preferredCourse);
        }
      }
    }
    return missingCourses;
  }
  
  /**
   * Collects all courses assigned to a teacher that does not wish to teach it.   
   * @return All courses that a teacher was assigned and is on the teacher disliked list.
   */
  public List<CourseCopy> checkTeacherPrefrence() {
    List<CourseCopy> dissLikeCourses = new ArrayList<CourseCopy>();
    // goes through each teacher
    for(Teacher currTeacher: my_teachers) {
      Set<Course> unpreferedCourses = currTeacher.getUnpreferedCourses();
      //finds all courses taught and ensures they are not in the disliked list.  
      for(CourseCopy aCourse: currTeacher.getCourses()) {
        String courseTitle = aCourse.getCourseTitle();
        for(Course  dislikedCourse: unpreferedCourses ) {
          if(dislikedCourse.getCourseTitle().equals(courseTitle)){
            dissLikeCourses.add(aCourse);
          }
        }
      }
    }
    return dissLikeCourses;
  }
  
  /**
   * Checks teacher availability.
   * @pre getTimePrefrences() != null, getDayPrefrences() != null
   * @pre timeBlocks() !=null, teachers.getCourses is sorted
   * @return The courses that violate the day or time constraints 
   */
  public List<CourseCopy> checkTeacherTimes(){
    
    // to do finish this once the teacher implementation is finalized (time is curently unfinished)
    List<CourseCopy> disslikeCoursesTimes = new ArrayList<CourseCopy>();
    // goes through each teacher
    for (Teacher currTeacher : my_teachers) {
      int[][] preferedTimes = currTeacher.getTimePreferences();
      Set<CourseCopy> coursesSet = currTeacher.getCourses();
      //goes through each course assigned to a teachers 
      for (CourseCopy course: coursesSet) {
         boolean [] daysTaught = course.getDays();
         //checks each day of the week
         for(int i = 0; i < 7; i++){
           if(daysTaught[i]){
             if(preferedTimes[i][course.getBlockNum()] == -1){
               disslikeCoursesTimes.add(course);
             }
           }
         }
      }

    }
    return disslikeCoursesTimes;
  }
  
  /**
   * 
   * @param schedule
   * @return
   * @author Phillip Bernard
   */
  public List<Course> checkStudentCoursePreferences(List<Course> schedule) {
    Set<Course> missed_courses = new HashSet<Course>();
    Map<Course, Integer> tally = new TreeMap<Course, Integer>();
    
    int min_needed_votes = (int) (my_students.size() * COURSE_PREFERENCE_THRESHOLD);
    /*
     * This loop tallies the votes for courses from student preferences.
     */
    for(Student s: my_students) {
      HashSet<Course> courses = (HashSet<Course>) s.getCourses();
      
      for(Course course : courses) {
        if (tally.containsKey(course)) {
          int num = tally.get(course);
          tally.put(course, num + 1); //puts the course back with an extra vote added
        } else {
          tally.put(course, 1);
        }
      }
    }
    
    /*
     * This portion iterates though the tallied votes and checks if those courses
     * are in the schedule.
     */
    for (Course course : tally.keySet()) {
      
      if (tally.get(course) >= min_needed_votes) {
        if(!schedule.contains(course)) {
          missed_courses.add(course);
        }
      }
    }
    
    return new ArrayList<Course>(missed_courses);
  }
}
