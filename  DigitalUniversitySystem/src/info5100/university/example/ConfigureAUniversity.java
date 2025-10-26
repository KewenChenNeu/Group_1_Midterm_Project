/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package info5100.university.example;

import info5100.university.example.Department.Department;
import info5100.university.example.Persona.Person;
import info5100.university.example.Persona.StudentProfile;
import info5100.university.example.Persona.UserAccountDirectory;

/**
 *
 * @author karaboss
 */
public class ConfigureAUniversity {
    

/**
 * Initializes sample data for ProfileWorkAreaMainFrame.
 * Keeps compatibility with Department-based projects.
 */

    public static Department setupTestData() {
        Department department = new Department("Information Systems");

        // Create sample people
        Person admin = new Person("001");
        admin.setName("Admin User");

        Person student = new Person("002");
        student.setName("Student User");

        Person faculty = new Person("003");
        faculty.setName("Faculty User");

        Person registrar = new Person("004");
        registrar.setName("Registrar User");

        // Add student profile
        StudentProfile sp = department.getStudentDirectory().newStudentProfile(student);

        // Create user accounts
        UserAccountDirectory uad = department.getUserAccountDirectory();
        uad.newUserAccount(admin, "admin", "admin", "Admin");
        uad.newUserAccount(student, "student", "student", "Student");
        uad.newUserAccount(faculty, "faculty", "faculty", "Faculty");
        uad.newUserAccount(registrar, "registrar", "registrar", "Registrar");

        return department;
    }
}
