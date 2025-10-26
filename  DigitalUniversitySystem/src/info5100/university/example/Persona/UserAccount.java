/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package info5100.university.example.Persona;

/**
 *
 * @author kal bugrara
 */

import info5100.university.example.workareas.Workarea;

/**
 *
 * @author kal bugrara
 */
public class UserAccount {
    private Person person;
    private String username;
    private String password;
    private String role; // "Admin", "Faculty", "Student", "Registrar"
    private Workarea landingworkarea;
    private boolean isActive;
    private String lastLoginTime;
    
    public UserAccount(Person p, String un, String pw, String r) {
        person = p;
        username = un;
        password = pw;
        role = r;
        isActive = true;
    }
    
    public boolean authenticate(String un, String pw) {
        return username.equals(un) && password.equals(pw);
    }
    
    public String getRole() { 
        return role; 
    }
    
    public Person getPerson() { 
        return person; 
    }
    
    public String getUsername() { 
        return username; 
    }
    
    public String getPassword() {
        return password; 
    }
    
    public boolean isActive() {
        return isActive;
    }
    
    public String getLastLoginTime() {
        return lastLoginTime;
    }
    
    // Setter
    public void setPassword(String newPassword) {
        this.password = newPassword;
    }
    
    public void setRole(String role) {
        this.role = role;
    }
    
    public void setActive(boolean active) {
        this.isActive = active;
    }
    
    public void setLastLoginTime(String time) {
        this.lastLoginTime = time;
    }
    
    public boolean isMatch(String id) {
        return person.getPersonId().equals(id);
    }
    
    @Override
    public String toString() {
        return username + " (" + role + ")";
    }
}