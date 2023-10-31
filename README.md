# Course Scheduler
A general schedule creator for a high school or a school with a similar structure that creates a schedule based on available courses as well as student course rankings. Created using Java. Schedule creation is done with a backtracking algorithm.

## Running the Program
In order to create a schedule with sample inputs, simply execute Main.java. In order to create a schedule with your own inputs, edit the inputs in Main.java to create your custom schedule. The parameters are set by instantiating an Organizer object, and the schedule is created by calling the solve method. 

# Program Information
**Purpose:**
My high school had a two week period of no classes during which our physics teacher would spend all of his time creating a schedule to fit everyone's needs. While I will not send him this repository as I believe he enjoys creating the schedules, this program automates the process of organizing courses into time slots based on parameters such as student course enrollments and course credit amounts, where credit amounts represent the number of times the course appears per cycle.

**Schedule Layout:**
While my high school worked on a 6 period per day, 5 day per cycle layout, this program can organize a schedule with any combination of periods and days. The schedule is presented as a 3d nested Java ArrayList. The innermost nest is an ArrayList representing a block of time containing associated courses and their credit amounts. the next nest is a specific period of the day containing blocks of time for each day of the cycle. The outermost nest contains every period of the day. 

**Schedule Rules:**
First, a course cannot occur more than two times in a day. Second, a course can occupy the same timeslot as another, but only if the two courses do not have any overlapping students.

**Program Notes:**
I think that the most interesting part of this project was implementing the boolean solve method in class Organizer. This method returns true if a schedule was successfully created and false if it is not possible to create a schedule with the given restraints. The method implements a recursive backtracking algorithm that is somewhat complex as there are many factors to consider as there are many different possible paths that a specific course can take. I was also able to do some work with Java classes, and overriding methods including toString, equals, and hashcode. The hashcode method needed to be updated in order to use the HashMap method containsKey, the equals method needed to be updated to compare two courses in a couple of different locations, and the toString method needed to be updated in order to neatly present the courses within the schedule. The most difficult part of this project was testing the solve method as it is fairly long and can go in a lot of different directions. The most effective way that I found to test this method other than my JUnit 5 Test Cases were print statements at crucial lines of the method.

**Roadmap:**
While the program works and has been thoroughly tested, it is fairly difficult to use. While I primarly created this program for the functionality and to write interesting code, it would be beneficial to implement a GUI. After implementing the GUI, I could also potentially write a method to format the ArrayList into a more schedule-like format into the console, or even copy the information onto another file that can be printed for people to use. 
