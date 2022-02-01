package schedule;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import org.junit.jupiter.api.Test;

class SchedulerTest {

    @Test
    void testValidCourses() {

        Course c1= new Course("Physics", 2);
        Course c2= new Course("AmStud", 4);
        Course c3= new Course("Spanish 3", 2);
        ArrayList<Course> courses= new ArrayList<>(
            Arrays.asList(c1, c2, c3));
        ArrayList<Course> cl1= new ArrayList<>(Arrays.asList(c1, c2));
        Student p1= new Student("Sean", cl1);
        ArrayList<Course> cl2= new ArrayList<>(Arrays.asList(c2, c3));
        Student p2= new Student("Julian", cl2);
        ArrayList<Course> cl3= new ArrayList<>(Arrays.asList(c1, c3));
        Student p3= new Student("Luca", cl3);
        Student[] students= { p1, p2, p3 };
        Organizer org1= new Organizer(5, 5, courses, students);
        boolean isvalid= org1.valid_courses();
        assertEquals(true, isvalid);

        Course c4= new Course("DNE", 1);
        ArrayList<Course> cl4= new ArrayList<>(Arrays.asList(c2, c4));
        Student p4= new Student("Zach", cl4);
        Student[] students2= { p1, p2, p3, p4 };
        Organizer org2= new Organizer(5, 5, courses, students2);
        boolean isvalid2= org2.valid_courses();
        assertEquals(false, isvalid2);
    }

    @Test
    void testCreate3d() {

        ArrayList<Course> courses= new ArrayList<>();
        Student[] students= new Student[2];
        Organizer org3= new Organizer(2, 2, courses, students);
        ArrayList<ArrayList<ArrayList<Course>>> expected= new ArrayList<>();
        ArrayList<ArrayList<Course>> nest1expected= new ArrayList<>();
        expected.add(nest1expected);
        ArrayList<ArrayList<Course>> nest2expected= new ArrayList<>();
        expected.add(nest2expected);
        ArrayList<Course> nest3expected= new ArrayList<>();
        nest1expected.add(nest3expected);
        ArrayList<Course> nest4expected= new ArrayList<>();
        nest2expected.add(nest4expected);
        ArrayList<Course> nest5expected= new ArrayList<>();
        nest1expected.add(nest5expected);
        ArrayList<Course> nest6expected= new ArrayList<>();
        nest2expected.add(nest6expected);
        assertEquals(expected, org3.create_3d());
    }

    @Test
    void testIsValid() {

        Course c1= new Course("Physics", 2);
        Course c2= new Course("AmStud", 4);
        Course c3= new Course("Spanish 3", 2);
        ArrayList<Course> courses= new ArrayList<>(
            Arrays.asList(c1, c2, c3));
        ArrayList<Course> cl1= new ArrayList<>(Arrays.asList(c1, c2));
        Student p1= new Student("Sean", cl1);
        ArrayList<Course> cl2= new ArrayList<>(Arrays.asList(c2, c3));
        Student p2= new Student("Julian", cl2);
        ArrayList<Course> cl3= new ArrayList<>(Arrays.asList(c2, c3));
        Student p3= new Student("Luca", cl3);
        Student[] students= { p1, p2, p3 };
        Organizer org3= new Organizer(3, 3, courses, students);

        ArrayList<ArrayList<ArrayList<Course>>> matrix= org3.create_3d();
        boolean valid= org3.isValid(matrix, 0, 0, c1);
        assertEquals(true, valid);

        matrix.get(0).get(0).add(c1);
        boolean valid2= org3.isValid(matrix, 0, 0, c2);
        assertEquals(false, valid2);

        matrix.get(1).get(0).add(c1);
        boolean valid3= org3.isValid(matrix, 2, 0, c1);
        assertEquals(false, valid3);

        boolean valid4= org3.isValid(matrix, 0, 0, c3);
        assertEquals(true, valid4);

    }

    @Test
    void testCreateChecked() {

        Course c1= new Course("Physics", 2);
        Course c2= new Course("AmStud", 4);
        ArrayList<Course> courses= new ArrayList<>(
            Arrays.asList(c1, c2));
        Student[] students= new Student[2];
        Organizer org4= new Organizer(3, 3, courses, students);
        HashMap<Course, List<Integer>> checked= org4.createChecked();
        HashMap<Course, List<Integer>> expected= new HashMap<>();
        List<Integer> c1check= Arrays.asList(0, 0);
        List<Integer> c2check= Arrays.asList(0, 0);
        expected.put(c1, c1check);
        expected.put(c2, c2check);
        assertEquals(expected, checked);
    }

    @Test
    void testUpdateChecked() {

        Course c1= new Course("Physics", 2);
        Course c2= new Course("AmStud", 4);
        ArrayList<Course> courses= new ArrayList<>(
            Arrays.asList(c1, c2));
        Student[] students= new Student[2];
        Organizer org5= new Organizer(2, 2, courses, students);
        HashMap<Course, List<Integer>> checked= org5.createChecked();
        org5.updateChecked(checked, 0, 0, c1);
        HashMap<Course, List<Integer>> expected= new HashMap<>();
        List<Integer> c1check= Arrays.asList(0, 1);
        List<Integer> c2check= Arrays.asList(0, 0);
        expected.put(c1, c1check);
        expected.put(c2, c2check);
        assertEquals(expected, checked);

        org5.updateChecked(checked, 0, 1, c1);
        List<Integer> c1check2= Arrays.asList(1, 0);
        expected.put(c1, c1check2);
        assertEquals(expected, checked);

        org5.updateChecked(checked, 1, 1, c1);
        List<Integer> c1check3= Arrays.asList(-1, -1);
        expected.put(c1, c1check3);
        assertEquals(expected, checked);
    }

    @Test
    void testEquals() {
        Course c1= new Course("Math", 1);
        Course c2= new Course("Math", 1);
        assertEquals(true, c1.equals(c2));

        Course c3= new Course("English", 1);
        assertEquals(false, c1.equals(c3));
    }

    @Test
    void testCredits() {
        Course c1= new Course("Math", 1);
        assertEquals(1, c1.credits());
    }

    @Test
    void testCreditsLeft() {
        Course c1= new Course("Math", 1);
        assertEquals(1, c1.creditsLeft());

        c1.reduceCreditsLeft();
        assertEquals(0, c1.creditsLeft());
        assertEquals(1, c1.credits());
    }

    @Test
    void testSolve() {

        // Test for a 1 period schedule with valid input. Only deals with part of code.
        Course c1= new Course("Physics", 1);
        ArrayList<Course> courses= new ArrayList<>(
            Arrays.asList(c1));
        ArrayList<Course> cl1= new ArrayList<>(Arrays.asList(c1));
        Student p1= new Student("Sean", cl1);
        Student[] students= { p1 };
        Organizer org6= new Organizer(1, 1, courses, students);
        ArrayList<ArrayList<ArrayList<Course>>> matrix= org6.create_3d();
        HashMap<Course, List<Integer>> checked= org6.createChecked();
        boolean output= org6.solve(matrix, c1, checked);
        assertEquals(true, output);
        ArrayList<ArrayList<ArrayList<Course>>> expectedmatrix= org6.create_3d();
        expectedmatrix.get(0).get(0).add(c1);
        assertEquals(expectedmatrix, matrix);
    }

    @Test
    void testSolve2() {

        // Test for 1 period schedule with valid first input, but invalid second.
        Course c2= new Course("Calculus", 1);
        Course c1= new Course("Physics", 1);
        ArrayList<Course> courses2= new ArrayList<>(
            Arrays.asList(c1, c2));
        ArrayList<Course> cl2= new ArrayList<>(Arrays.asList(c1, c2));
        Student p2= new Student("Sean", cl2);
        Student[] students2= { p2 };
        Organizer org2= new Organizer(1, 1, courses2, students2);
        ArrayList<ArrayList<ArrayList<Course>>> matrix2= org2.create_3d();
        HashMap<Course, List<Integer>> checked2= org2.createChecked();
        boolean output2= org2.solve(matrix2, c1, checked2);
        assertEquals(false, output2);
    }

    @Test
    void testSolve3() {

        // Test for 1 period schedule with two valid inputs.
        Course c2= new Course("Calculus", 1);
        Course c1= new Course("Physics", 1);
        ArrayList<Course> courses2= new ArrayList<>(
            Arrays.asList(c1, c2));
        ArrayList<Course> cl3= new ArrayList<>(Arrays.asList(c1));
        ArrayList<Course> cl4= new ArrayList<>(Arrays.asList(c2));
        Student p3= new Student("Jack", cl3);
        Student p4= new Student("Julian", cl4);
        Student[] students3= { p3, p4 };
        Organizer org3= new Organizer(1, 1, courses2, students3);
        ArrayList<ArrayList<ArrayList<Course>>> matrix3= org3.create_3d();
        HashMap<Course, List<Integer>> checked3= org3.createChecked();
        boolean output3= org3.solve(matrix3, c1, checked3);
        assertEquals(true, output3);
    }

    @Test
    void testSolve4() {

        // Test for 2 period schedule with two valid inputs, but one has to go in second period.
        Course c2= new Course("Calculus", 1);
        Course c1= new Course("Physics", 1);
        ArrayList<Course> courses= new ArrayList<>(
            Arrays.asList(c1, c2));
        ArrayList<Course> cl1= new ArrayList<>(Arrays.asList(c1, c2));
        Student p1= new Student("Jack", cl1);
        Student p2= new Student("Julian", cl1);
        Student[] students= { p1, p2 };
        Organizer org= new Organizer(2, 1, courses, students);
        ArrayList<ArrayList<ArrayList<Course>>> matrix= org.create_3d();
        HashMap<Course, List<Integer>> checked= org.createChecked();
        boolean output= org.solve(matrix, c1, checked);
        assertEquals(true, output);
        ArrayList<ArrayList<ArrayList<Course>>> expectedmatrix= org.create_3d();
        expectedmatrix.get(0).get(0).add(c1);
        expectedmatrix.get(1).get(0).add(c2);
        assertEquals(expectedmatrix, matrix);
    }

    @Test
    void testSolve5() {

        // Test for two credit courses
        Course c1= new Course("Calculus", 2);
        Course c2= new Course("Physics", 1);
        ArrayList<Course> courses= new ArrayList<>(
            Arrays.asList(c1, c2));
        ArrayList<Course> cl1= new ArrayList<>(Arrays.asList(c2));
        ArrayList<Course> cl2= new ArrayList<>(Arrays.asList(c1));
        Student p1= new Student("Jack", cl2);
        Student p2= new Student("Julian", cl1);
        Student[] students= { p1, p2 };
        Organizer org= new Organizer(1, 2, courses, students);
        ArrayList<ArrayList<ArrayList<Course>>> matrix= org.create_3d();
        HashMap<Course, List<Integer>> checked= org.createChecked();
        boolean output= org.solve(matrix, c1, checked);
        assertEquals(true, output);

    }

    @Test
    void testSolve6() {

        // Test for three of the same course in the same day failure
        Course c1= new Course("Calculus", 3);
        ArrayList<Course> courses= new ArrayList<>(
            Arrays.asList(c1));
        ArrayList<Course> cl1= new ArrayList<>(Arrays.asList(c1));
        Student p1= new Student("Julian", cl1);
        Student[] students= { p1 };
        Organizer org= new Organizer(4, 1, courses, students);
        ArrayList<ArrayList<ArrayList<Course>>> matrix= org.create_3d();
        HashMap<Course, List<Integer>> checked= org.createChecked();
        boolean output= org.solve(matrix, c1, checked);
        assertEquals(false, output);

    }

}
