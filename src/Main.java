package schedule;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        Course c1= new Course("Calculus", 2);
        Course c2= new Course("AmStud", 4);
        Course c3= new Course("Spanish 3", 2);
        Course c4= new Course("Spanish 4", 2);
        Course c5= new Course("French 3", 2);
        Course c6= new Course("Latin 4", 2);
        Course c7= new Course("Visual Arts", 1);
        Course c8= new Course("Orchestra", 1);
        Course c9= new Course("Jazz", 1);
        Course c10= new Course("PE", 1);
        Course c11= new Course("Programming", 1);
        ArrayList<Course> courses= new ArrayList<>(
            Arrays.asList(c1, c2, c3, c4, c5, c6, c7, c8, c9, c10, c11));
        ArrayList<Course> cl1= new ArrayList<>(Arrays.asList(c1, c2, c4, c9));
        Student p1= new Student("Sean", cl1);
        ArrayList<Course> cl2= new ArrayList<>(Arrays.asList(c1, c2, c3, c10, c11));
        Student p2= new Student("Sean", cl2);
        ArrayList<Course> cl3= new ArrayList<>(Arrays.asList(c1, c2, c5, c11));
        Student p3= new Student("Sean", cl3);
        ArrayList<Course> cl4= new ArrayList<>(Arrays.asList(c1, c2, c6, c8));
        Student p4= new Student("Sean", cl4);
        ArrayList<Course> cl5= new ArrayList<>(Arrays.asList(c1, c2, c4, c8));
        Student p5= new Student("Sean", cl5);
        Student[] students= { p1, p2, p3, p4, p5 };
        Organizer org= new Organizer(4, 3, courses, students);
        ArrayList<ArrayList<ArrayList<Course>>> matrix= org.create_3d();
        HashMap<Course, List<Integer>> checked= org.createChecked();
        boolean output= org.solve(matrix, c1, checked);
        System.out.println(output);
        System.out.println(matrix);
    }

}
