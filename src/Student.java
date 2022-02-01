package schedule;

import java.util.HashMap;
import java.util.ArrayList;

public class Student {
    
    private String name;
    private ArrayList<Course> course_list;
    private HashMap<Course, Integer> courses;

    public Student(String studentName, ArrayList<Course> crses)  {
        this.name= studentName;
        this.course_list= crses;
        this.courses= new HashMap<>();

        for (int i= 0; i < course_list.size(); i++) {
            this.courses.put(course_list.get(i), i);
        }
    }

    public String getName() {
        return this.name;
    }

    public HashMap<Course, Integer> getCourses() {
        return this.courses;
    }

    public ArrayList<Course> getCourseList() {
        return this.course_list;
    }

}




