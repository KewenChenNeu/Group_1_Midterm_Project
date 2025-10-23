/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package info5100.university.example.Persona;

import java.util.ArrayList;

/**
 *
 * @author chris
 */
public class UserAccountDirectory {
    
    private ArrayList<UserAccount> useraccountlist;
    
    public UserAccountDirectory() {
        useraccountlist = new ArrayList<>();
    }
    
    // 添加这个方法 - 创建新的用户账户
    public UserAccount newUserAccount(Person p, String username, String password, String role) {
        UserAccount ua = new UserAccount(p, username, password, role);
        useraccountlist.add(ua);
        return ua;
    }
    
    // 认证方法
    public UserAccount authenticate(String username, String password) {
        for (UserAccount ua : useraccountlist) {
            if (ua.authenticate(username, password)) {
                return ua;
            }
        }
        return null;
    }
    
    // 根据ID查找用户
    public UserAccount findUserAccount(String id) {
        for (UserAccount ua : useraccountlist) {
            if (ua.getPerson().getPersonId().equals(id)) {
                return ua;
            }
        }
        return null;
    }
    
    // 获取所有账户
    public ArrayList<UserAccount> getUserAccountList() {
        return useraccountlist;
    }
    
    // 根据角色获取用户列表
    public ArrayList<UserAccount> getUserAccountsByRole(String role) {
        ArrayList<UserAccount> result = new ArrayList<>();
        for (UserAccount ua : useraccountlist) {
            if (ua.getRole().equals(role)) {
                result.add(ua);
            }
        }
        return result;
    }
    
    // 删除用户账户
    public boolean deleteUserAccount(String username) {
        UserAccount toDelete = null;
        for (UserAccount ua : useraccountlist) {
            if (ua.getUsername().equals(username)) {
                toDelete = ua;
                break;
            }
        }
        if (toDelete != null) {
            useraccountlist.remove(toDelete);
            return true;
        }
        return false;
    }
    
    // 检查用户名是否已存在
    public boolean isUsernameAvailable(String username) {
        for (UserAccount ua : useraccountlist) {
            if (ua.getUsername().equals(username)) {
                return false;
            }
        }
        return true;
    }
    
    // 获取用户总数
    public int getTotalUsers() {
        return useraccountlist.size();
    }
    
    // 根据角色统计用户数
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