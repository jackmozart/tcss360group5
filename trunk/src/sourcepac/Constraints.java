package sourcepac;

import java.util.ArrayList;
import java.util.HashSet;
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
 * @version 5/31/2011 Added checkStudentCourse and Time Preference -PB
 * @version 5/28/2011 Adjusted checkCreditLoad to use Teacher's methods -PB
 * @version Last update May 25 2011
 */
public class Constraints {

  /**
   * This constant hold the percentage of votes from the entire student
   * body that is needed for a course to be considered a preference. 
   */
  public static final double COURSE_PREFERENCE_THRESHOLD = .1;
  
  /**
   * This constant hold the percentage of votes from the entire student
   * body that is needed for a time to be considered a preference. 
   */
  public static final double TIME_PREFERENCE_THRESHOLD = .1;
    
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
    //List<CourseCopy> dissLikedCourses = new ArrayList<CourseCopy>(); 
    Set<CourseCopy> dissLikedCourses = new HashSet<CourseCopy>(); //to eliminate duplicates
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
        for (CourseCopy course : teacher.getCourses()) {
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
            int i = 0;
            Object[] coursesToCompare =  day.toArray();
            for (CourseCopy course : day) {              
              for (int j = i + 1 ; j < coursesToCompare.length; j++) {
                if (course.getTime().getEndTime() > ((CourseCopy) coursesToCompare[j]).getTime().getStartTime() && !course.equals(coursesToCompare[j])) {
                  dissLikedCourses.add(course);
                  dissLikedCourses.add((CourseCopy) coursesToCompare[j]);
                }
              }
              i++;//push start to ensure i don't double check elements
            }
            
          }
        }

      }
    }
    return new ArrayList<CourseCopy>(dissLikedCourses);

  }
   

  /**
   * Checks teacher credit load.
   * 
   * @return Error message in form (teacher) exceeds credit load by (amount) credits.
   */
  public List<Teacher> checkCreditLoad() {
    final List<Teacher> error_message = new ArrayList<Teacher>();
    
    for (Teacher aTeacher : my_teachers) {
      aTeacher.setCurrCredits(0);
      for(CourseCopy course: aTeacher.getCourses()){
       aTeacher.setCurrCredits(aTeacher.getCurrCredits() + course.getCredit());
      }
    }

    for (Teacher teacher_pref : my_teachers) {
      if (teacher_pref.getMaxCredits() < teacher_pref.getCurrCredits()) {
        error_message.add(teacher_pref);
       }
     }
     return error_message;
  }
  
  /**
   * 
   * @return List of all courses suggested by advisor but not offered.
   */
  public List<Course> checkAdvisorPreferences(){  
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
  public List<CourseCopy> checkTeacherPreference() {
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
        if(course.getBlockNum() == -1){
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

    }
    return disslikeCoursesTimes;
  }

  /**
   * 
   * @return List of courses not being offered and have been requested by a
   *         number of students greater then the preference threshold.
   * @author Phillip Bernard
   */
  public List<Course> checkStudentCoursePreferences() {
    Set<Course> missed_courses = new HashSet<Course>();
    Map<Course, Integer> tally = new TreeMap<Course, Integer>();
    
    int min_needed_votes = (int) (my_students.size() * COURSE_PREFERENCE_THRESHOLD);
    /*
     * This loop tallies the votes for courses from student preferences.
     */
    for(Student s: my_students) {
      Set<Course> courses = s.getCourses();
      
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
      boolean result = true;
      if (tally.get(course) >= min_needed_votes) {
        result = false;
        for(Course coursecopy : my_courses) {
          if(course.getCourseTitle().equals(coursecopy.getCourseTitle())){
            result = true;
            break;
          }
        }
      }
      
      if (!result) {
        missed_courses.add(course);
      }
    }
    
    return new ArrayList<Course>(missed_courses);
  }
  
  /**
   * 
   * @return The list of all courses not courses that are not offered at the
   *         desired time but are offered elsewhere.
   * @author Phillip Bernard
   * @author Steven Cozart (comments only)
   */
  public List<Course> checkStudentTimePreferences() {
    Set<CourseCopy> missed_courses = new HashSet<CourseCopy>();
    
    Map<CourseCopy, Integer> tally = new TreeMap<CourseCopy, Integer>();
    
    int min_needed_votes = (int) (my_students.size() * TIME_PREFERENCE_THRESHOLD);
    /*
     * This loop tallies votes for each class and time from student preferences.
     */
    for(Student s: my_students) {
      List<Time> times = s.getTimes();
      Set<Course> courses = s.getCourses();
      
      /*
       * If the course is not scheduled for a time requested the course is recorded
       */
      for(CourseCopy coursecopy : my_courses) { //courses in schedule
        for(Course course : courses) {          //courses in student preferences
          if(course.equals(coursecopy) && !times.contains(coursecopy.getTime())){
            int num = tally.get(coursecopy);
            if(num > 0) {
              tally.put(coursecopy, num + 1); //puts the course back with an extra vote added
            } else {
              tally.put(coursecopy, 1);
            }
          }
        }
      }
      
      /*
       * This portion iterates though the tallied votes and checks if those courses
       * are in the schedule.
       */
      for (CourseCopy coursecopy : tally.keySet()) {
        
        if (tally.get(coursecopy) >= min_needed_votes) {
          missed_courses.add(coursecopy);
        }
      }

   
      
    }
    return new ArrayList<Course>(missed_courses);
  }


  /**
   * 
   * @return All teachers who have been assigned no credits
   */
  public List<Teacher> checkNoCredits() {
    final List<Teacher> error_message = new ArrayList<Teacher>();

    for (Teacher aTeacher : my_teachers) {
      aTeacher.setCurrCredits(0);
      for (CourseCopy course : aTeacher.getCourses()) {
        aTeacher.setCurrCredits(aTeacher.getCurrCredits() + course.getCredit());
      }
    }

    for (Teacher teacher_pref : my_teachers) {
      if (0 ==  teacher_pref.getCurrCredits()) {
        error_message.add(teacher_pref);
      }
    }
    return error_message;

  }
}
