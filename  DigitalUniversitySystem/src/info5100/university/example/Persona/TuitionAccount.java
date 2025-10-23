/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package info5100.university.example.Persona;

import java.util.ArrayList;
import java.util.Date;
/**
 *
 * @author chris
 */
public class TuitionAccount {
    private double balance;
    private ArrayList<Payment> paymentHistory;
    private static final double PRICE_PER_CREDIT = 1500.0;
    
    public TuitionAccount() {
        balance = 0;
        paymentHistory = new ArrayList<>();
    }
    
    // 收费（按学分）
    public void charge(int credits) {
        double amount = credits * PRICE_PER_CREDIT;
        balance += amount;
    }
    
    // 支付学费
    public boolean pay(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            Payment payment = new Payment(amount, new Date());
            paymentHistory.add(payment);
            return true;
        }
        return false;
    }
    
    // 退款（比如学生退课）
    public void refund(double amount) {
        balance -= amount; // 负数表示学校欠学生钱
    }
    
    // 获取余额
    public double getBalance() {
        return balance;
    }
    
    // 检查是否已付清
    public boolean isPaid() {
        return balance <= 0;
    }
    
    // 获取支付历史
    public ArrayList<Payment> getPaymentHistory() {
        return paymentHistory;
    }
    
    // 内部类 - 支付记录
    public static class Payment {
        private double amount;
        private Date paymentDate;
        private String paymentId;
        
        public Payment(double amount, Date date) {
            this.amount = amount;
            this.paymentDate = date;
            this.paymentId = "PAY" + System.currentTimeMillis();
        }
        
        public double getAmount() { return amount; }
        public Date getPaymentDate() { return paymentDate; }
        public String getPaymentId() { return paymentId; }
    }
}