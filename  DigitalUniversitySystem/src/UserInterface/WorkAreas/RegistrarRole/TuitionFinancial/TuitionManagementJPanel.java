/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package UserInterface.WorkAreas.RegistrarRole.TuitionFinancial;

import info5100.university.example.Department.Department;
import info5100.university.example.CourseSchedule.CourseLoad;
import info5100.university.example.CourseSchedule.SeatAssignment;
import info5100.university.example.Persona.StudentProfile;
import info5100.university.example.Persona.StudentDirectory;
import info5100.university.example.Persona.Person;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.text.NumberFormat;
import java.util.*;

/**
 *
 * @author chris
 */
public class TuitionManagementJPanel extends javax.swing.JPanel {

    JPanel CardSequencePanel;
    Department department;
    DefaultTableModel tableModel;
    
    // Constants
    private static final double PRICE_PER_CREDIT = 1500.0;
    
    /**
     * Creates new form TuitionManagementJPanel
     */
    public TuitionManagementJPanel(Department department, JPanel jpanel) {
        this.CardSequencePanel = jpanel;
        this.department = department;
        initComponents();
        customInit();
    }
    
    /**
     * Custom initialization
     */
    private void customInit() {
        // Set up table model
        tableModel = (DefaultTableModel) tblTuition.getModel();
        
        // Make summary fields non-editable
        fieldTuitionExpected.setEditable(false);
        fieldTotalCollected.setEditable(false);
        fieldOutstanding.setEditable(false);
        
        // Load data
        loadPaymentStatusOptions();
        loadTuitionData();
        
        // Don't add automatic listeners for combo boxes anymore
        // User will click Filter button to apply filters
    }
    
    
    /**
     * Load payment status options
     */
    private void loadPaymentStatusOptions() {
        cmbPaymentStatus.removeAllItems();
        cmbPaymentStatus.addItem("All");
        cmbPaymentStatus.addItem("Paid");
        cmbPaymentStatus.addItem("Unpaid");
        cmbPaymentStatus.addItem("Partial");
    }
    
    /**
     * Load and display tuition data
     */
    private void loadTuitionData() {
        // Clear table
        tableModel.setRowCount(0);
        
        String selectedStatus = (String) cmbPaymentStatus.getSelectedItem();
        
        // Summary variables
        double totalExpected = 0.0;
        double totalCollected = 0.0;
        double totalOutstanding = 0.0;
        
        // Get student directory
        StudentDirectory studentDirectory = department.getStudentDirectory();
        if (studentDirectory != null && studentDirectory.getStudentList() != null) {
            
            for (StudentProfile student : studentDirectory.getStudentList()) {
                Person person = student.getPerson();
                String studentId = person.getPersonId();
                String studentName = person.getName();
                
                // Calculate tuition for this student (all semesters)
                double tuition = calculateStudentTuition(student);
                
                // For demo purposes, simulate some payments
                double paidAmount = simulatePaidAmount(tuition);
                double outstanding = tuition - paidAmount;
                String status = determineStatus(tuition, paidAmount);
                
                // Filter by status if needed
                if (!"All".equals(selectedStatus) && !status.equals(selectedStatus)) {
                    continue;
                }
                
                // Add row to table
                Object[] row = {
                    studentId,
                    studentName,
                    String.format("$%.2f", tuition),
                    String.format("$%.2f", paidAmount),
                    String.format("$%.2f", outstanding),
                    status
                };
                tableModel.addRow(row);
                
                // Update totals
                totalExpected += tuition;
                totalCollected += paidAmount;
                totalOutstanding += outstanding;
            }
        }
        
        // Update summary fields
        fieldTuitionExpected.setText(String.format("$%.2f", totalExpected));
        fieldTotalCollected.setText(String.format("$%.2f", totalCollected));
        fieldOutstanding.setText(String.format("$%.2f", totalOutstanding));
    }
    
    /**
     * Calculate tuition for a student (all semesters)
     */
    private double calculateStudentTuition(StudentProfile student) {
        double tuition = 0.0;
        
        // Calculate for all semesters
        ArrayList<SeatAssignment> allCourses = student.getCourseList();
        if (allCourses != null) {
            for (SeatAssignment sa : allCourses) {
                tuition += sa.getCreditHours() * PRICE_PER_CREDIT;
            }
        }
        
        return tuition;
    }
    
    /**
     * Simulate paid amount (for demo purposes)
     */
    private double simulatePaidAmount(double tuition) {
        if (tuition == 0) return 0;
        
        // Randomly simulate different payment scenarios
        Random rand = new Random();
        int scenario = rand.nextInt(3);
        
        switch (scenario) {
            case 0: // Fully paid
                return tuition;
            case 1: // Partially paid
                return tuition * 0.5;
            case 2: // Unpaid
                return 0;
            default:
                return tuition;
        }
    }
    
    /**
     * Determine payment status
     */
    private String determineStatus(double tuition, double paid) {
        if (tuition == 0) return "N/A";
        if (paid >= tuition) return "Paid";
        if (paid > 0) return "Partial";
        return "Unpaid";
    }


    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblTuition = new javax.swing.JTable();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        cmbPaymentStatus = new javax.swing.JComboBox<>();
        fieldTuitionExpected = new javax.swing.JTextField();
        fieldTotalCollected = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        fieldOutstanding = new javax.swing.JTextField();
        Back3 = new javax.swing.JButton();
        btnFilter = new javax.swing.JButton();

        jLabel1.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        jLabel1.setText("Tuition & Financial Management");

        jLabel3.setText("Payment Status");

        tblTuition.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Student ID", "Student Name", "Total Tuition", "Paid Amount", "Outstanding Balance", "Payment Status"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tblTuition);

        jLabel4.setText("Total Tuition Expected");

        jLabel5.setText("Total Collected");

        cmbPaymentStatus.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel6.setText("Total Outstanding Balance");

        Back3.setText("<< Back");
        Back3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Back3ActionPerformed(evt);
            }
        });

        btnFilter.setText("Filter");
        btnFilter.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFilterActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(54, 54, 54)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(Back3, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(133, 133, 133)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addComponent(jLabel5)
                            .addComponent(jLabel6))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(fieldTotalCollected)
                            .addComponent(fieldTuitionExpected)
                            .addComponent(fieldOutstanding, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 800, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(jLabel3)
                            .addGap(18, 18, 18)
                            .addComponent(cmbPaymentStatus, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addComponent(btnFilter, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 432, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(282, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(cmbPaymentStatus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnFilter))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 294, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(fieldTuitionExpected, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(fieldTotalCollected, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(fieldOutstanding, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(Back3)
                .addContainerGap(102, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void Back3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Back3ActionPerformed
        // Go back to previous screen
        CardSequencePanel.remove(this);
        ((java.awt.CardLayout) CardSequencePanel.getLayout()).previous(CardSequencePanel);
    }//GEN-LAST:event_Back3ActionPerformed

    private void btnFilterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFilterActionPerformed
        // TODO add your handling code here:
        // Apply filters when Filter button is clicked
        loadTuitionData();
    }//GEN-LAST:event_btnFilterActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Back3;
    private javax.swing.JButton btnFilter;
    private javax.swing.JComboBox<String> cmbPaymentStatus;
    private javax.swing.JTextField fieldOutstanding;
    private javax.swing.JTextField fieldTotalCollected;
    private javax.swing.JTextField fieldTuitionExpected;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblTuition;
    // End of variables declaration//GEN-END:variables
}
