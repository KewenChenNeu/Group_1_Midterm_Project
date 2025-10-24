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
    ArrayList<FacultyAssignment> facultyassignments;

    String department;
    String title;
    String facultyName;

    public FacultyProfile(Person p) {

        this.person = p;
        this.facultyassignments = new ArrayList<>();
    }

    public FacultyAssignment AssignAsTeacher(CourseOffer co) {
        FacultyAssignment fa = new FacultyAssignment(this, co);
        facultyassignments.add(fa);
        return fa;
    }

    public CourseOffer findAssignedCourseOffer(String courseNumber) {
        if (courseNumber == null) {
            return null;
        }
        for (FacultyAssignment fa : facultyassignments) {
            CourseOffer co = fa.getCourseOffer();
            if (co != null && courseNumber.equals(co.getCourseNumber())) {
                return co;
            }
        }
        return null;
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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getFacultyName() {
        return facultyName;
    }

    public void setFacultyName(String facultyName) {
        this.facultyName = facultyName;
    }

    public double getProfAverageOverallRating() {
        if (facultyassignments.isEmpty()) {
            return 0.0;
        }
        double sum = 0.0;
        for (FacultyAssignment fa : facultyassignments) {
            sum += fa.getRating();
        }
        return sum / (facultyassignments.size() * 1.0);
    }

    public ArrayList<CourseOffer> getAssignedCourseOffers() {
        ArrayList<CourseOffer> out = new ArrayList<>();
        for (FacultyAssignment fa : facultyassignments) {
            out.add(fa.getCourseOffer());
        }
        return out;
    }

    public boolean unassignFromCourse(CourseOffer co) {
        if (co == null) {
            return false;
        }
        FacultyAssignment target = null;
        for (FacultyAssignment fa : facultyassignments) {
            if (fa.getCourseOffer() == co) {
                target = fa;
                break;
            }
        }
        return target != null && facultyassignments.remove(target);
    }

//    @Override
//    public String toString() {
//        String id = getPersonId();
//        String name = (facultyName != null ? facultyName : "(no name)");
//        return name + " [" + (id != null ? id : "?") + "]";
//    }
    
}
