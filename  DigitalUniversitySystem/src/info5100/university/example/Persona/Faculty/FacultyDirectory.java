/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package info5100.university.example.Persona.Faculty;

import info5100.university.example.Persona.*;
import info5100.university.example.Department.Department;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

/**
 *
 * @author kal bugrara
 */
public class FacultyDirectory {

    Department department;
    ArrayList<FacultyProfile> teacherlist;

    public FacultyDirectory(Department d) {

        this.department = d;
        this.teacherlist = new ArrayList();

    }

    public boolean hasFaculty(String personId) {
        return findTeachingFaculty(personId) != null;
    }

    public FacultyProfile newFacultyProfile(Person p) {
        if (p == null) {
            throw new IllegalArgumentException("Person cannot be null");
        }
        String pid = p.getPersonId();
        if (pid == null) {
            throw new IllegalArgumentException("Person ID cannot be null");
        }
        if (hasFaculty(pid)) {
            throw new IllegalArgumentException("Faculty profile already exists for id: " + pid);
        }
        FacultyProfile fp = new FacultyProfile(p);
        teacherlist.add(fp);
        return fp;
    }

    public FacultyProfile findTeachingFaculty(String id) {
        for (FacultyProfile fp : teacherlist) {
            if (fp.isMatch(id)) {
                return fp;
            }
        }
        return null; 
    }

    public ArrayList<FacultyProfile> getFacultylist() {
        return teacherlist;
    }

    public boolean removeFaculty(String personId) {
        FacultyProfile fp = findTeachingFaculty(personId);
        return fp != null && teacherlist.remove(fp);
    }

    public boolean updateFacultyContact(String personId, String newEmail, String newPhone) {
        FacultyProfile fp = findTeachingFaculty(personId);
        if (fp == null) {
            return false;
        }

        Person p = fp.getPerson();
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

    public boolean updateFacultyDepartment(String personId, String newDepartment) {
        FacultyProfile fp = findTeachingFaculty(personId);
        if (fp == null) {
            return false;
        }
        fp.setDepartment(newDepartment);
        return true;
    }

    public boolean updateFacultyTitle(String personId, String newTitle) {
        FacultyProfile fp = findTeachingFaculty(personId);
        if (fp == null) {
            return false;
        }
        fp.setTitle(newTitle);
        return true;
    }

    public boolean updateFacultyName(String personId, String newName) {
        FacultyProfile fp = findTeachingFaculty(personId);
        if (fp == null) {
            return false;
        }
        fp.setFacultyName(newName);
        return true;
    }

    public java.util.List<FacultyProfile> searchById(String id) {
        FacultyProfile fp = findTeachingFaculty(id);
        return (fp == null) ? Collections.<FacultyProfile>emptyList() : Arrays.asList(fp);
    }

    public java.util.List<FacultyProfile> searchByDepartment(String departmentQuery) {
        if (departmentQuery == null || departmentQuery.trim().isEmpty()) {
            return Collections.<FacultyProfile>emptyList();
        }
        String q = departmentQuery.toLowerCase();
        java.util.List<FacultyProfile> out = new java.util.ArrayList<>();
        for (FacultyProfile fp : teacherlist) {
            String dep = fp.getDepartment();
            if (dep != null && dep.toLowerCase().contains(q)) {
                out.add(fp);
            }
        }
        return out;
    }

    public java.util.List<FacultyProfile> searchByName(String nameQuery) {
        if (nameQuery == null || nameQuery.trim().isEmpty()) {
            return Collections.<FacultyProfile>emptyList();
        }
        String q = nameQuery.toLowerCase();
        java.util.List<FacultyProfile> out = new java.util.ArrayList<>();
        for (FacultyProfile fp : teacherlist) {
            String name = fp.getFacultyName(); 
            if (name != null && name.toLowerCase().contains(q)) {
                out.add(fp);
            }
        }
        return out;
    }

    public FacultyProfile getTopProfessor() {
        double best = 0.0;
        FacultyProfile bestProf = null;
        for (FacultyProfile fp : teacherlist) {
            double r = fp.getProfAverageOverallRating();
            if (r > best) {
                best = r;
                bestProf = fp;
            }
        }
        return bestProf;
    }

}
