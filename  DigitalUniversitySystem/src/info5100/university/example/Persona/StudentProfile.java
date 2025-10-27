/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package info5100.university.example.Persona;

import info5100.university.example.CourseSchedule.CourseLoad;
import info5100.university.example.CourseSchedule.SeatAssignment;
import info5100.university.example.Persona.EmploymentHistory.EmploymentHistroy;
import java.util.ArrayList;

/**
 *
 * @author kal bugrara
 */
public class StudentProfile {

    Person person;
    Transcript transcript;
    EmploymentHistroy employmenthistory;
    
    String department;
    String academicStatus;
    String studentName; 
    
    private TuitionAccount tuitionAccount; 
    

    public StudentProfile(Person p) {

        this.person = p;
        this.transcript = new Transcript(this);
        this.employmenthistory = new EmploymentHistroy();
        this.tuitionAccount = new TuitionAccount();
    }
    
    public boolean isMatch(String id) {
        return person != null && person.getPersonId().equals(id);
    }

    public Person getPerson() {
        return person;
    }

    public String getPersonId() {
        return person != null ? person.getPersonId() : null;
    }
    
    public String getDepartment() {
        return department;
    }
    public void setDepartment(String department) {
        this.department = department;
    }

    public String getAcademicStatus() {
        return academicStatus;
    }
    public void setAcademicStatus(String academicStatus) {
        this.academicStatus = academicStatus;
    }

    public String getStudentName() {
        return studentName;
    }
    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }
    
    public TuitionAccount getTuitionAccount() {
        return tuitionAccount;
    }
    
    public Transcript getTranscript() {
        return transcript;
    }

    

    public CourseLoad getCurrentCourseLoad() {
        return transcript.getCurrentCourseLoad();
    }

    public CourseLoad newCourseLoad(String semester) {
        return transcript.newCourseLoad(semester);
    }

    public ArrayList<SeatAssignment> getCourseList() {
        return transcript.getCourseList();
    }

    public EmploymentHistroy getEmploymenthistory() {
        return employmenthistory;
    }
    
    public CourseLoad getCourseLoadBySemester(String semester) {
    if (transcript == null) return null;

    for (CourseLoad cl : transcript.getCourseloadlist()) {
        if (cl.getSemester().equalsIgnoreCase(semester)) {
            return cl;
        }
    }
    return null;
}
    
}
