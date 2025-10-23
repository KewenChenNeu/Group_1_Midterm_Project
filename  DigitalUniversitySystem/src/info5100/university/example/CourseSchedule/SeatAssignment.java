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
    
    // NEW - set letter grade and get the matched GPA
    public void setGrade(String letter) {
        this.letterGrade = letter;
        switch(letter) {
            case "A": this.grade = 4.0f; break;
            case "A-": this.grade = 3.7f; break;
            case "B+": this.grade = 3.3f; break;
            case "B": this.grade = 3.0f; break;
            case "B-": this.grade = 2.7f; break;
            case "C+": this.grade = 2.3f; break;
            case "C": this.grade = 2.0f; break;
            case "C-": this.grade = 1.7f; break;
            default: this.grade = 0.0f; break;
        }
    }
    
    public String getLetterGrade() { return letterGrade; }
    public float getGradePoint() { return grade; }
    
    public SeatAssignment(CourseLoad cl, Seat s){
        seat = s;
        courseload = cl;
    }
     
    public boolean getLike(){
        return like;
    }
    public void assignSeatToStudent(CourseLoad cl){
        courseload = cl;
    }
    
    public int getCreditHours(){
        return seat.getCourseCredits();
       
    }
    public Seat getSeat(){
        return seat;
    }
    public CourseOffer getCourseOffer(){
        
        return seat.getCourseOffer();
    }
    public Course getAssociatedCourse(){
        
        return getCourseOffer().getSubjectCourse();
    }
    public float GetCourseStudentScore(){
        return getCreditHours()*grade;
    }
    
    
    
}
