/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package info5100.university.example.Persona;

import info5100.university.example.Department.Department;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

/**
 *
 * @author kal bugrara
 */
public class StudentDirectory {

    private Department department;
    private ArrayList<StudentProfile> studentlist;

    public StudentDirectory() {
        studentlist = new ArrayList<>();
    }

    public StudentDirectory(Department department) {
        this.department = department;
        this.studentlist = new ArrayList<>();
    }

    public StudentProfile newStudentProfile(Person p) {
        if (p == null) {
            throw new IllegalArgumentException("Person cannot be null");
        }
        String pid = p.getPersonId();
        if (pid == null) {
            throw new IllegalArgumentException("Person ID cannot be null");
        }
        if (hasStudent(pid)) {
            throw new IllegalArgumentException("Student profile already exists for id: " + pid);
        }
        StudentProfile sp = new StudentProfile(p);
        studentlist.add(sp);
        return sp;
    }

    public StudentProfile findStudent(String id) {
        for (StudentProfile sp : studentlist) {
            if (sp.isMatch(id)) {
                return sp;
            }
        }
        return null; 
    }

    public boolean hasStudent(String personId) {
        return findStudent(personId) != null;
    }

    public ArrayList<StudentProfile> getStudentList() {
        return studentlist;
    }

    public boolean removeStudent(String personId) {
        StudentProfile sp = findStudent(personId);
        return sp != null && studentlist.remove(sp);
    }

    public boolean updateStudentContact(String personId, String newEmail, String newPhone) {
        StudentProfile sp = findStudent(personId);
        if (sp == null) {
            return false;
        }

        Person p = sp.getPerson();
        if (p == null) {
            return false;
        }

        if (newEmail != null) {
            newEmail = newEmail.trim();
        }
        if (newPhone != null) {
            newPhone = newPhone.trim();
        }

        if (newEmail != null && !newEmail.equalsIgnoreCase(p.getEmail())) {
            if (department.getPersonDirectory().emailExists(newEmail)) {
                return false;
            }
            p.setEmail(newEmail);
        }
        if (newPhone != null) {
            p.setPhone(newPhone);
        }
        return true;
    }

    public boolean updateStudentDepartment(String personId, String newDepartment) {
        StudentProfile sp = findStudent(personId);
        if (sp == null) {
            return false;
        }
        sp.setDepartment(newDepartment);
        return true;
    }

    public boolean updateStudentAcademicStatus(String personId, String newStatus) {
        StudentProfile sp = findStudent(personId);
        if (sp == null) {
            return false;
        }
        sp.setAcademicStatus(newStatus);
        return true;
    }

    public java.util.List<StudentProfile> searchById(String id) {
        StudentProfile sp = findStudent(id);
        return (sp == null) ? Collections.<StudentProfile>emptyList() : Arrays.asList(sp);
    }

    public java.util.List<StudentProfile> searchByDepartment(String departmentQuery) {
        if (departmentQuery == null || departmentQuery.trim().isEmpty()) {
            return Collections.<StudentProfile>emptyList();
        }
        String q = departmentQuery.toLowerCase();
        java.util.List<StudentProfile> out = new java.util.ArrayList<>();
        for (StudentProfile sp : studentlist) {
            String dep = sp.getDepartment();
            if (dep != null && dep.toLowerCase().contains(q)) {
                out.add(sp);
            }
        }
        return out;
    }

    public java.util.List<StudentProfile> searchByName(String nameQuery) {
        if (nameQuery == null || nameQuery.trim().isEmpty()) {
            return Collections.<StudentProfile>emptyList();
        }
        String q = nameQuery.toLowerCase();
        java.util.List<StudentProfile> out = new java.util.ArrayList<>();
        for (StudentProfile sp : studentlist) {
            String name = sp.getStudentName();
            if (name != null && name.toLowerCase().contains(q)) {
                out.add(sp);
            }
        }
        return out;
    }

}
