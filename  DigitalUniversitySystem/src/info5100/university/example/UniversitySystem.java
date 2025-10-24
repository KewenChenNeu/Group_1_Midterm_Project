package info5100.university.example;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */


import info5100.university.example.Persona.PersonDirectory;
import info5100.university.example.Employer.EmployerDirectory;
import info5100.university.example.Persona.StudentDirectory;

import info5100.university.example.Persona.UserAccountDirectory;

/**
 *
 * @author zhaojinkun
 */
public class UniversitySystem {
    String name;
    PersonDirectory persondirectory; //all people profiles regardless of the role

    EmployerDirectory employeedirectory;
    UserAccountDirectory useraccountdirectory;
    StudentDirectory studentdirectory;
    


    public UniversitySystem(String n) {
        name = n;

        persondirectory = new PersonDirectory();
        useraccountdirectory = new UserAccountDirectory();
        studentdirectory = new StudentDirectory();


    }

    public PersonDirectory getPersonDirectory() {
        return persondirectory;
    }

    public UserAccountDirectory getUserAccountDirectory() {
        return useraccountdirectory;
    }



    public StudentDirectory getStudentDirectory(){
        return studentdirectory;
    }
}
