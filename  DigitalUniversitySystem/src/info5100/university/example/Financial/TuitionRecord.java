/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package info5100.university.example.Financial;

import info5100.university.example.Persona.StudentProfile;
import java.util.Date;

/**
 *
 * @author chris
 */
public class TuitionRecord {
    private StudentProfile student;
    private String semester;
    private double totalAmount;
    private double paidAmount;
    private double outstandingBalance;
    private Date lastPaymentDate;
    private String paymentStatus; // "Paid", "Unpaid", "Partial"
    
    public TuitionRecord(StudentProfile student, String semester, double amount) {
        this.student = student;
        this.semester = semester;
        this.totalAmount = amount;
        this.paidAmount = 0;
        this.outstandingBalance = amount;
        this.paymentStatus = "Unpaid";
    }
    
    public void makePayment(double amount) {
        this.paidAmount += amount;
        this.outstandingBalance = totalAmount - paidAmount;
        this.lastPaymentDate = new Date();
        updatePaymentStatus();
    }
    
    private void updatePaymentStatus() {
        if (outstandingBalance <= 0) {
            paymentStatus = "Paid";
        } else if (paidAmount > 0) {
            paymentStatus = "Partial";
        } else {
            paymentStatus = "Unpaid";
        }
    }

    public StudentProfile getStudent() {
        return student;
    }

    public void setStudent(StudentProfile student) {
        this.student = student;
    }

    public String getSemester() {
        return semester;
    }

    public void setSemester(String semester) {
        this.semester = semester;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public double getPaidAmount() {
        return paidAmount;
    }

    public void setPaidAmount(double paidAmount) {
        this.paidAmount = paidAmount;
    }

    public double getOutstandingBalance() {
        return outstandingBalance;
    }

    public void setOutstandingBalance(double outstandingBalance) {
        this.outstandingBalance = outstandingBalance;
    }

    public Date getLastPaymentDate() {
        return lastPaymentDate;
    }

    public void setLastPaymentDate(Date lastPaymentDate) {
        this.lastPaymentDate = lastPaymentDate;
    }

    public String getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(String paymentStatus) {
        this.paymentStatus = paymentStatus;
    }
    
    
}
