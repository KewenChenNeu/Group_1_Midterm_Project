/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package info5100.university.example.Department;

import info5100.university.example.CourseCatalog.Course;
import info5100.university.example.CourseCatalog.CourseCatalog;
import info5100.university.example.CourseSchedule.CourseLoad;
import info5100.university.example.CourseSchedule.CourseOffer;
import info5100.university.example.CourseSchedule.CourseSchedule;
import info5100.university.example.Degree.Degree;
import info5100.university.example.Employer.EmployerDirectory;
import info5100.university.example.Persona.Faculty.FacultyDirectory;
import info5100.university.example.Persona.Faculty.FacultyProfile;
import info5100.university.example.Persona.PersonDirectory;
import info5100.university.example.Persona.StudentDirectory;
import info5100.university.example.Persona.StudentProfile;
import info5100.university.example.Persona.UserAccountDirectory;
import java.util.HashMap;

/**
 *
 * @author kal bugrara
 */
public class Department {

    String name;
    CourseCatalog coursecatalog;
    PersonDirectory persondirectory;
    StudentDirectory studentdirectory;
    FacultyDirectory facultydirectory;
    EmployerDirectory employerdirectory;
    UserAccountDirectory useraccountdirectory;
    Degree degree;
    HashMap<String, CourseSchedule> mastercoursecatalog;

    public Department(String n) {
        name = n;
        mastercoursecatalog = new HashMap<>();
        coursecatalog = new CourseCatalog(this);
        studentdirectory = new StudentDirectory(this);
        persondirectory = new PersonDirectory();
        facultydirectory = new FacultyDirectory(this);
        employerdirectory = new EmployerDirectory(this);
        useraccountdirectory = new UserAccountDirectory();
        degree = new Degree("MSIS");
    }

    public FacultyDirectory getFacultyDirectory() {
        return facultydirectory;
    }

    public String getName() {
        return name;
    }

    public UserAccountDirectory getUserAccountDirectory() {
        return useraccountdirectory;
    }

    public void addCoreCourse(Course c) {
        degree.addCoreCourse(c);

    }

    public void addElectiveCourse(Course c) {
        degree.addElectiveCourse(c);

    }

    public PersonDirectory getPersonDirectory() {

        return persondirectory;

    }

    public StudentDirectory getStudentDirectory() {
        return studentdirectory;
    }

    public CourseSchedule newCourseSchedule(String semester) {

        CourseSchedule cs = new CourseSchedule(semester, coursecatalog);
        mastercoursecatalog.put(semester, cs);
        return cs;
    }

    public CourseSchedule getCourseSchedule(String semester) {

        return mastercoursecatalog.get(semester);

    }

    public CourseCatalog getCourseCatalog() {

        return coursecatalog;

    }

    public Course newCourse(String n, String nm, int cr) {

        Course c = coursecatalog.newCourse(n, nm, cr);
        return c;
    }

    public int calculateRevenuesBySemester(String semester) {

        CourseSchedule css = mastercoursecatalog.get(semester);

        return css.calculateTotalRevenues();

    }

    public void RegisterForAClass(String studentid, String cn, String semester) {

        StudentProfile sp = studentdirectory.findStudent(studentid);

        CourseLoad cl = sp.getCurrentCourseLoad();

        CourseSchedule cs = mastercoursecatalog.get(semester);

        CourseOffer co = cs.getCourseOfferByNumber(cn);

        co.assignEmptySeat(cl);

    }
    
    public HashMap<String, CourseSchedule> getMastercoursecatalog() {
        return mastercoursecatalog;
    }

    public void setMastercoursecatalog(HashMap<String, CourseSchedule> mastercoursecatalog) {
        this.mastercoursecatalog = mastercoursecatalog;
    }
    public boolean assignFacultyToCourse(String facultyPersonId, String courseNumber, String semester) {
        try {
            FacultyDirectory fd = this.facultydirectory;
            if (fd == null) {
                return false;
            }

            FacultyProfile fp = fd.findTeachingFaculty(facultyPersonId);
            if (fp == null) {
                return false;
            }

            CourseSchedule cs = getCourseSchedule(semester);
            if (cs == null) {
                return false;
            }

            CourseOffer co = cs.getCourseOfferByNumber(courseNumber);
            if (co == null) {
                return false;
            }

            co.AssignAsTeacher(fp);
            return true;

        } catch (Exception e) {
            return false;
        }
    }
    
    public Degree getDegree() {
        return degree;
    }

    public void setDegree(Degree degree) {
        this.degree = degree;
    }
}
