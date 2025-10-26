/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package info5100.university.example.Persona;

import java.util.ArrayList;
import java.util.Collections;

/**
 *
 * @author chris
 */
public class UserAccountDirectory {

    private ArrayList<UserAccount> useraccountlist;

    public UserAccountDirectory() {
        useraccountlist = new ArrayList<>();
    }

    public UserAccount newUserAccount(Person p, String username, String password, String role) {
        if (!isUsernameAvailable(username)) {
            throw new IllegalArgumentException("Username already exists: " + username);
        }
        UserAccount ua = new UserAccount(p, username, password, role);
        useraccountlist.add(ua);
        return ua;
    }

    public UserAccount authenticate(String username, String password) {
        for (UserAccount ua : useraccountlist) {
            if (ua.authenticate(username, password)) {
                return ua;
            }
        }
        return null;
    }

  
    public UserAccount findUserAccount(String personId) {
        if (personId == null) {
            return null;
        }
        for (UserAccount ua : useraccountlist) {
            if (ua.getPerson().getPersonId().equals(personId)) {
                return ua;
            }
        }
        return null;
    }

    public UserAccount getByUsername(String username) {
        if (username == null) {
            return null;
        }
        for (UserAccount ua : useraccountlist) {
            if (ua.getUsername().equals(username)) {
                return ua;
            }
        }
        return null;
    }

   
    public java.util.List<UserAccount> getUserAccountList() {
        return Collections.unmodifiableList(useraccountlist);
    }

   
    public ArrayList<UserAccount> getUserAccountsByRole(String role) {
        ArrayList<UserAccount> result = new ArrayList<>();
        for (UserAccount ua : useraccountlist) {
            if (ua.getRole().equals(role)) {
                result.add(ua);
            }
        }
        return result;
    }


    public boolean updateUserAccount(String username, String newPassword, String newRole) {
        for (UserAccount ua : useraccountlist) {
            if (ua.getUsername().equals(username)) {
                if (newPassword != null && !newPassword.isEmpty()) {
                    ua.setPassword(newPassword);
                }
                if (newRole != null && !newRole.isEmpty()) {
                    ua.setRole(newRole);
                }
                return true;
            }
        }
        return false;
    }

    public boolean deleteUserAccount(String username) {
        UserAccount toDelete = null;
        for (UserAccount ua : useraccountlist) {
            if (ua.getUsername().equals(username)) {
                toDelete = ua;
                break;
            }
        }
        return toDelete != null && useraccountlist.remove(toDelete);
    }

    public boolean isUsernameAvailable(String username) {
        if (username == null) {
            return false;
        }
        for (UserAccount ua : useraccountlist) {
            if (ua.getUsername().equals(username)) {
                return false;
            }
        }
        return true;
    }

    public int getTotalUsers() {
        return useraccountlist.size();
    }

    
    public int getUserCountByRole(String role) {
        int count = 0;
        for (UserAccount ua : useraccountlist) {
            if (ua.getRole().equals(role)) {
                count++;
            }
        }
        return count;
    }
}
