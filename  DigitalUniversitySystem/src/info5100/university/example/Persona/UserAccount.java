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
import java.time.LocalDateTime;

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
    private LocalDateTime lastLoginTime;
    private LocalDateTime lastUpdatedTime;
    
    public UserAccount(Person p, String un, String pw, String r) {
        this.person = p;
        this.username = un;
        this.password = pw;
        this.role = r;
        this.isActive = true;
        this.lastLoginTime = null;
        this.lastUpdatedTime = null;
        
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
    
   
    public void setPassword(String newPassword) {
        this.password = newPassword;
        touchUpdatedTime();
    }
    
    public void setRole(String role) {
        this.role = role;
        touchUpdatedTime();
    }
    
    public void setActive(boolean active) {
        this.isActive = active;
        touchUpdatedTime();
    }
    
    
    public boolean isMatch(String id) {
        return person.getPersonId().equals(id);
    }

    public LocalDateTime getLastLoginTime() {
        return lastLoginTime;
    }
    
    public void markLoginNow() {
        this.lastLoginTime = LocalDateTime.now();
    }
    
    public void touchUpdatedTime() {
        this.lastUpdatedTime = LocalDateTime.now();
    }
    
    public void setLastLoginTime(LocalDateTime lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }

    public LocalDateTime getLastUpdatedTime() {
        return lastUpdatedTime;
    }

    public void setLastUpdatedTime(LocalDateTime lastUpdatedTime) {
        this.lastUpdatedTime = lastUpdatedTime;
    }
    
    
    @Override
    public String toString() {
        return username + " (" + role + ")";
    }
}