/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package info5100.university.example.CourseSchedule;

import info5100.university.example.CourseCatalog.Course;
import info5100.university.example.Persona.Faculty.FacultyAssignment;
import info5100.university.example.Persona.Faculty.FacultyProfile;
import info5100.university.example.Persona.StudentProfile;
import java.util.ArrayList;

/**
 *
 * @author kal bugrara
 */
public class CourseOffer {

    Course course;
    ArrayList<Seat> seatlist;
    FacultyAssignment facultyassignment;
    String room;
    String courseScheduleTime;
    

    public CourseOffer(Course c) {
        course = c;
        seatlist = new ArrayList();
    }
     
    public void AssignAsTeacher(FacultyProfile fp) {

        facultyassignment = new FacultyAssignment(fp, this);
    }

    public FacultyProfile getFacultyProfile() {
        if (facultyassignment == null) {
            return null;
        }
        return facultyassignment.getFacultyProfile();
    }

    public String getCourseNumber() {
        return course.getCOurseNumber();
    }
    
    
    public String getTeacherName() {
        return course.getTeacherName();
    }


    public void generatSeats(int n) {

        for (int i = 0; i < n; i++) {

            seatlist.add(new Seat(this, i));

        }

    }

    public Seat getEmptySeat() {

        for (Seat s : seatlist) {

            if (!s.isOccupied()) {
                return s;
            }
        }
        return null;
    }


    public SeatAssignment assignEmptySeat(CourseLoad cl) {

        Seat seat = getEmptySeat();
        if (seat == null) {
            return null;
        }
        SeatAssignment sa = seat.newSeatAssignment(cl); //seat is already linked to course offer
        cl.registerStudent(sa); //coures offer seat is now linked to student
        return sa;
    }

    public int getTotalCourseRevenues() {

        int sum = 0;

        for (Seat s : seatlist) {
            if (s.isOccupied() == true) {
                sum = sum + course.getCoursePrice();
            }

        }
        return sum;
    }

    public Course getCourse() {
        return course;
    }
    
    public String getCourseName() {
        return course.getName();
    } 
    
    public Course getSubjectCourse(){
        return course;
    }
    public int getCreditHours(){
        return course.getCredits();
    }

    // New methods for Faculty UI
    public ArrayList<Seat> getSeatList() {
        return seatlist;
    }

    public int getEnrolledCount() {
        int count = 0;
        for (Seat s : seatlist) {
            if (s.isOccupied()) {
                count++;
            }
        }
        return count;
    }

    private boolean enrollmentOpen = true;

    public boolean isEnrollmentOpen() {
        return enrollmentOpen;
    }

    public void setEnrollmentOpen(boolean open) {
        this.enrollmentOpen = open;
    }

    
    
    // New##################################################################

    // Fixed enrollStudent method
    public boolean enrollStudent(StudentProfile student) {
        // Check if there's capacity
        if (getAvailableSeats() <= 0) {
            return false;
        }

        // Get or create current course load for the student
        CourseLoad courseLoad = student.getCurrentCourseLoad();
        if (courseLoad == null) {
            // You might need to create a new course load here
            // depending on your semester handling
            return false;
        }

        SeatAssignment sa = assignEmptySeat(courseLoad);
        return sa != null;
    }

    // Fixed dropStudent method
    public boolean dropStudent(StudentProfile student) {
        // Find and remove the student's seat assignment
        for (Seat seat : seatlist) {
            if (seat.isOccupied()) {
                // Check if this seat belongs to the student
                if (seat.seatassignment != null && 
                    seat.seatassignment.getCourseLoad() != null) {
                    
                    // Need to check if this courseload belongs to the student
                    // This requires going through the student's course loads
                    ArrayList<SeatAssignment> studentAssignments = student.getCourseList();
                    for (SeatAssignment sa : studentAssignments) {
                        if (sa.getSeat() == seat) {
                            // Found the student's seat, now release it
                            seat.occupied = false;
                            seat.seatassignment = null;
                            
                            // Also remove from student's course load
                            sa.getCourseLoad().getSeatAssignments().remove(sa);
                            
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

    public int getAvailableSeats() {
        int available = 0;
        for (Seat seat : seatlist) {
            if (!seat.isOccupied()) {
                available++;
            }
        }
        return available;
    }

    public int getCurrentEnrollment() {
        int enrolled = 0;
        for (Seat seat : seatlist) {
            if (seat.isOccupied()) {
                enrolled++;
            }
        }
        return enrolled;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    public String getRoom() {
        return room;
    }

    // Fixed schedule methods
    public void setSchedule(String schedule) {
        this.courseScheduleTime = schedule;  // Use the actual variable name
    }

    public String getSchedule() {
        return courseScheduleTime;  // Use the actual variable name
    }

    public void setEnrollmentCapacity(int capacity) {
        // Adjust seat list size if needed
        if (capacity > seatlist.size()) {
            generatSeats(capacity - seatlist.size());
        }
    }
}
