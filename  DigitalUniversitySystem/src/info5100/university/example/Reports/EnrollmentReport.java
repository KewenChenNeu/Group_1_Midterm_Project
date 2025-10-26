/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package info5100.university.example.Reports;

import java.util.HashMap;

/**
 *
 * @author chris
 */
public class EnrollmentReport {
    private String semester;
    private HashMap<String, Integer> departmentEnrollment;
    private HashMap<String, Integer> courseEnrollment;
    private int totalEnrollment;
    
    public EnrollmentReport(String semester) {
        this.semester = semester;
        this.departmentEnrollment = new HashMap<>();
        this.courseEnrollment = new HashMap<>();
        this.totalEnrollment = 0;
    }
    
    public void addDepartmentEnrollment(String department, int count) {
        departmentEnrollment.put(department, count);
        totalEnrollment += count;
    }
    
    public void addCourseEnrollment(String courseId, int count) {
        courseEnrollment.put(courseId, count);
    }
    
    // Getters
    public HashMap<String, Integer> getDepartmentEnrollment() {
        return departmentEnrollment;
    }
    
    public HashMap<String, Integer> getCourseEnrollment() {
        return courseEnrollment;
    }
    
    public int getTotalEnrollment() {
        return totalEnrollment;
    }
}
