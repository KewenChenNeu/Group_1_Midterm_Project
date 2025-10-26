/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package info5100.university.example.Reports;

import java.util.HashMap;

/**
 *
 * @author chris
 */
public class GPADistributionReport {
    private String program;
    private HashMap<String, Integer> gpaRanges; // e.g., "3.5-4.0", "3.0-3.5"
    private double averageGPA;
    private int totalStudents;
    
    public GPADistributionReport(String program) {
        this.program = program;
        this.gpaRanges = new HashMap<>();
        initializeGPARanges();
    }
    
    private void initializeGPARanges() {
        gpaRanges.put("3.5-4.0", 0);
        gpaRanges.put("3.0-3.5", 0);
        gpaRanges.put("2.5-3.0", 0);
        gpaRanges.put("2.0-2.5", 0);
        gpaRanges.put("Below 2.0", 0);
    }
    
    public void addStudent(double gpa) {
        String range = getGPARange(gpa);
        gpaRanges.put(range, gpaRanges.get(range) + 1);
        totalStudents++;
    }
    
    private String getGPARange(double gpa) {
        if (gpa >= 3.5) return "3.5-4.0";
        else if (gpa >= 3.0) return "3.0-3.5";
        else if (gpa >= 2.5) return "2.5-3.0";
        else if (gpa >= 2.0) return "2.0-2.5";
        else return "Below 2.0";
    }
    
    // Getters
    public HashMap<String, Integer> getGPADistribution() {
        return gpaRanges;
    }
    
    public double getAverageGPA() {
        return averageGPA;
    }
    
    public void setAverageGPA(double avg) {
        this.averageGPA = avg;
    }
}
