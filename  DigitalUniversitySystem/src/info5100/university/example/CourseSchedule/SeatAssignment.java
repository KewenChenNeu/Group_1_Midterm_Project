/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package info5100.university.example.CourseSchedule;

import info5100.university.example.CourseCatalog.Course;


/**
 *
 * @author kal bugrara
 */
public class SeatAssignment {
    float grade; //(Letter grade mappings: A=4.0, A-=3.7, B+=3.3, B=3.0, )
    Seat seat;
    boolean like; //true means like and false means not like
    CourseLoad courseload;
    String letterGrade = "F";
    String assignmentStatus = "Pending";
    
    public SeatAssignment(CourseLoad cl, Seat s) {
        this.courseload = cl;
        this.seat = s;
    }

    public void setGrade(String letter) {
        this.letterGrade = letter;
        switch (letter) {
            case "A": this.gradePoint = 4.0f; break;
            case "A-": this.gradePoint = 3.7f; break;
            case "B+": this.gradePoint = 3.3f; break;
            case "B": this.gradePoint = 3.0f; break;
            case "B-": this.gradePoint = 2.7f; break;
            case "C+": this.gradePoint = 2.3f; break;
            case "C": this.gradePoint = 2.0f; break;
            case "C-": this.gradePoint = 1.7f; break;
            default: this.gradePoint = 0.0f; break;
        }
    }

    public String getGrade() {
        return letterGrade;
    }

    public float getGradePoint() {
        return gradePoint;
    }

    public Seat getSeat() {
        return seat;
    }

    public CourseOffer getCourseOffer() {
        return seat.getCourseOffer();
    }

    public Course getAssociatedCourse() {
        return getCourseOffer().getSubjectCourse();
    }

    public boolean getLike() {
        return like;
    }

    public void assignSeatToStudent(CourseLoad cl) {
        this.courseload = cl;
    }

    public int getCreditHours() {
        return seat.getCourseCredits();
    }

    public float GetCourseStudentScore() {
        return getCreditHours() * gradePoint;
    }

    public Object getCourseoffer() {
        return seat.getCourseOffer();
    }
    
    public String getAssignmentStatus() {
        return assignmentStatus;
    }

    public void setAssignmentSubmit() {
        this.assignmentStatus = "Submitted";
    public CourseLoad getCourseLoad() {
        return courseload;
    }
    
}


