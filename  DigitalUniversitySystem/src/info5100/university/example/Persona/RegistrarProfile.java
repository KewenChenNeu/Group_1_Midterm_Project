/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package info5100.university.example.Persona;

/**
 *
 * @author chris
 */
public class RegistrarProfile {
    private Person person;
    private String officeLocation;
    private String officeHours;
    private String department;
    
    public RegistrarProfile(Person p) {
        this.person = p;
    }
    
    public boolean isMatch(String id) {
        return person.getPersonId().equals(id);
    }
    
    public Person getPerson() {
        return person;
    }

    public String getOfficeLocation() {
        return officeLocation;
    }

    public void setOfficeLocation(String officeLocation) {
        this.officeLocation = officeLocation;
    }

    public String getOfficeHours() {
        return officeHours;
    }

    public void setOfficeHours(String officeHours) {
        this.officeHours = officeHours;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }
    
    
}
