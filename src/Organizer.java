package schedule;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class Organizer {

    private int num_periods;
    private int num_days;
    private ArrayList<Course> courses;
    private Student[] students;
    private HashMap<Course, Integer> courses_map;

    public Organizer(int numPeriods, int numDays, ArrayList<Course> crses, Student[] stdnts) {

        num_periods= numPeriods;
        num_days= numDays;
        courses= crses;
        students= stdnts;

        courses_map= new HashMap<>();

        for (int i= 0; i < courses.size(); i++ ) {
            courses_map.put(courses.get(i), i);
        }
    }

    public boolean valid_courses() {

        for (Student student : students) {
            for (Course course : student.getCourseList()) {
                if (!courses_map.containsKey(course)) { return false; }
            }
        }
        return true;
    }

    public ArrayList<ArrayList<ArrayList<Course>>> create_3d() {

        ArrayList<ArrayList<ArrayList<Course>>> matrix= new ArrayList<>();
        for (int i= 0; i < num_periods; i++ ) {
            ArrayList<ArrayList<Course>> period= new ArrayList<>();
            matrix.add(period);

            for (int j= 0; j < num_days; j++ ) {
                ArrayList<Course> day= new ArrayList<>();
                period.add(day);
            }
        }
        return matrix;
    }

    public boolean isValid(ArrayList<ArrayList<ArrayList<Course>>> matrix, int period, int day,
        Course course) {

        int count= 0;
        for (ArrayList<ArrayList<Course>> time : matrix) {
            for (Course cls : time.get(day)) {
                if (cls.equals(course)) { count++ ; }
            }
        }
        if (count >= 2) { return false; }

        ArrayList<ArrayList<Course>> time= matrix.get(period);
        ArrayList<Course> date= time.get(day);
        if (date.size() > 0) {
            for (Course cls : date) {
                for (Student student : students) {
                    HashMap<Course, Integer> stcourses= student.getCourses();
                    if (stcourses.containsKey(cls) && stcourses.containsKey(course)) {
                        return false;
                    }
                }
            }
        }

        return true;
    }

    public HashMap<Course, List<Integer>> createChecked() {

        HashMap<Course, List<Integer>> checked= new HashMap<>();
        List<Integer> start= Arrays.asList(0, 0);
        for (Course course : courses) {
            checked.put(course, start);
        }
        return checked;
    }

    public void updateChecked(HashMap<Course, List<Integer>> checked, int period, int day,
        Course course) {

        if (period == num_periods - 1 && day == num_days - 1) {

            List<Integer> pos= Arrays.asList(-1, -1);
            checked.put(course, pos);

        } else if (day == num_days - 1) {
            List<Integer> pos= Arrays.asList(period + 1, 0);
            checked.put(course, pos);

        } else {
            List<Integer> pos= Arrays.asList(period, day + 1);
            checked.put(course, pos);

        }
    }

    public boolean solve(ArrayList<ArrayList<ArrayList<Course>>> matrix,
        Course course, HashMap<Course, List<Integer>> checked) {

        // Get furthest unchecked position in 3d matrix for this course
        List<Integer> pos= checked.get(course);
        int periodpos= pos.get(0);
        int daypos= pos.get(1);

        // Check if this is a valid position for this course
        if (isValid(matrix, periodpos, daypos, course)) {
            matrix.get(periodpos).get(daypos).add(course);
            course.reduceCreditsLeft();
            // If creditsLeft is 0, then we can move onto next course
            if (course.creditsLeft() == 0) {
                // If that was the last course, then end the recursion
                if (courses.get(courses.size() - 1).equals(course)) { return true; }

                updateChecked(checked, periodpos, daypos, course);
                Course nextCourse= courses.get(courses.indexOf(course) + 1);
                // If next course returns true, end the recursion
                if (solve(matrix, nextCourse, checked)) { return true; }

                // If credits left isn't 0, then keep solving for same course until credits left is
                // 0
            } else if (solve(matrix, course, checked)) {
                return true;
            } else {
                // If child course is not solvable then reset everything except for "checked"
                // hashmap.
                ArrayList<Course> day= matrix.get(periodpos).get(daypos);
                day.remove(day.size() - 1);
                course.increaseCreditsLeft();
                // If there are more periods to check, then keep checking.
                if (checked.get(course).get(0) != -1) {
                    return solve(matrix, course, checked);
                } else {
                    // If there are no more to check, then go back to parent course.
                    return false;
                }
            }

        }
        // If this isn't a valid position, then move onto the next, unless that was the last period
        // possible.
        updateChecked(checked, periodpos, daypos, course);
        if (checked.get(course).get(0) != -1) {
            return solve(matrix, course, checked);
        } else {
            return false;
        }

    }

}