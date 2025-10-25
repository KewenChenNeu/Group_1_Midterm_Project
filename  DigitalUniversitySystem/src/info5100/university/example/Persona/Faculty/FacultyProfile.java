/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package info5100.university.example.Persona.Faculty;

import info5100.university.example.Persona.*;
import info5100.university.example.CourseSchedule.CourseOffer;
import java.util.ArrayList;

/**
 *
 * @author kal bugrara
 */
public class FacultyProfile {

    Person person;
    ArrayList <FacultyAssignment> facultyassignments; 
    
    public FacultyProfile(Person p) {

        person = p;
        facultyassignments = new ArrayList();
    }
    public  double getProfAverageOverallRating(){
        
        double sum = 0.0;
        //for each facultyassignment extract class rating
        //add them up and divide by the number of teaching assignmnet;
        for(FacultyAssignment fa: facultyassignments){
            
            sum = sum + fa.getRating();
            
        }
        //divide by the total number of faculty assignments
        
        return sum/(facultyassignments.size()*1.0); //this ensure we have double/double
        
    }

    public FacultyAssignment AssignAsTeacher(CourseOffer co){
        
        FacultyAssignment fa = new FacultyAssignment(this, co);
        facultyassignments.add(fa);
        
        return fa;
    }
    
    public FacultyProfile getCourseOffer(String courseid){
        return null; //complete it later
    }

    public boolean isMatch(String id) {
        if (person.getPersonId().equals(id)) {
            return true;
        }
        return false;
    }

    // New methods for Faculty Use Case
    public ArrayList<FacultyAssignment> getFacultyAssignments() {
        return facultyassignments;
    }

    public Person getPerson() {
        return person;
    }

    public ArrayList<CourseOffer> getAssignedCourses() {
        ArrayList<CourseOffer> courses = new ArrayList<>();
        for (FacultyAssignment fa : facultyassignments) {
            if (fa.getCourseOffer() != null) {
                courses.add(fa.getCourseOffer());
            }
        }
        return courses;
    }

}
