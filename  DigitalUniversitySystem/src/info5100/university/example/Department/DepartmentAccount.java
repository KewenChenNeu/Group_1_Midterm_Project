/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package info5100.university.example.Department;

import info5100.university.example.CourseSchedule.CourseSchedule;

/**
 *
 * @author kal bugrara
 */
public class DepartmentAccount {
  Department department;
    public DepartmentAccount(Department d){
        department = d;
    }
        
    public int calculateRevenuesBySemester(String semester) {

        CourseSchedule css = department.getCourseSchedule(semester);

        return css.calculateTotalRevenues();

    }
}
