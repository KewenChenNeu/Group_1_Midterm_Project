/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package info5100.university.example.Financial;

import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author chris
 */
public class FinancialAccount {
    private HashMap<String, ArrayList<TuitionRecord>> semesterRecords;
    
    public FinancialAccount() {
        semesterRecords = new HashMap<>();
    }
    
    public void addTuitionRecord(String semester, TuitionRecord record) {
        if (!semesterRecords.containsKey(semester)) {
            semesterRecords.put(semester, new ArrayList<>());
        }
        semesterRecords.get(semester).add(record);
    }
    
    public ArrayList<TuitionRecord> getRecordsBySemester(String semester) {
        return semesterRecords.get(semester);
    }
    
    public double getTotalCollectedBySemester(String semester) {
        double total = 0;
        ArrayList<TuitionRecord> records = semesterRecords.get(semester);
        if (records != null) {
            for (TuitionRecord record : records) {
                total += record.getPaidAmount();
            }
        }
        return total;
    }
    
    public double getTotalOutstandingBySemester(String semester) {
        double total = 0;
        ArrayList<TuitionRecord> records = semesterRecords.get(semester);
        if (records != null) {
            for (TuitionRecord record : records) {
                total += record.getOutstandingBalance();
            }
        }
        return total;
    }
    
    public HashMap<String, Double> getDepartmentRevenueBySemester(String semester) {
        HashMap<String, Double> departmentRevenue = new HashMap<>();
        // Implementation to calculate revenue by department
        return departmentRevenue;
    }
}
