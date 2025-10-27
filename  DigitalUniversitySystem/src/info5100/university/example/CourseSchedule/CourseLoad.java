/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package info5100.university.example.CourseSchedule;

import java.util.ArrayList;

/**
 *
 * @author kal bugrara
 */
public class CourseLoad {
    String semester;
    ArrayList<SeatAssignment> seatassignments;
    
    public CourseLoad(String s){
        seatassignments = new ArrayList();
        semester = s;
    }
    
    // Check the student could add the course or not
    public boolean canRegister(int newCredits) {
        int current = 0;
        for(SeatAssignment sa : seatassignments) {
            current += sa.getCreditHours();
        }
        return (current + newCredits) <= 8;
    }
    
    public SeatAssignment newSeatAssignment(CourseOffer co){
        
        Seat seat = co.getEmptySeat(); // seat linked to courseoffer
        if (seat==null) return null;
        SeatAssignment sa = seat.newSeatAssignment(this);
        seatassignments.add(sa);  //add to students course 
        return sa;
    }
    
    public void registerStudent(SeatAssignment sa){
        
        
        sa.assignSeatToStudent(this);
        seatassignments.add(sa);
    }
    
    public float getSemesterScore(){ //total score for a full semeter
        float sum = 0;
        for (SeatAssignment sa: seatassignments){
            sum = sum + sa.GetCourseStudentScore();
        }
        return sum;
    }
        public ArrayList<SeatAssignment> getSeatAssignments(){
            return seatassignments;
        }

    public String getSemester() {
        return semester;
    }
    
    public ArrayList<SeatAssignment> getSeatassignments() {
        return seatassignments;
    }

}
