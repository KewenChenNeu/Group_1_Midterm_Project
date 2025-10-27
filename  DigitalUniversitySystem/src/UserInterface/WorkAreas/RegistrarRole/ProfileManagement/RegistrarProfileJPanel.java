/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package UserInterface.WorkAreas.RegistrarRole.ProfileManagement;

import info5100.university.example.Department.Department;
import info5100.university.example.Persona.Person;
import info5100.university.example.Persona.UserAccount;
import info5100.university.example.Persona.RegistrarProfile;
import info5100.university.example.Persona.RegistrarDirectory;
import javax.swing.*;
import java.awt.*;

/**
 *
 * @author chris
 */
public class RegistrarProfileJPanel extends javax.swing.JPanel {

    private JPanel CardSequencePanel;
    private Department department;
    private UserAccount useraccount;
    private RegistrarProfile registrarProfile;
    private RegistrarDirectory registrarDirectory;
    
    /**
     * Creates new form RegistrarProfileJPanel
     */
    public RegistrarProfileJPanel(Department department, UserAccount useraccount, JPanel jpanel) {
        this.CardSequencePanel = jpanel;
        this.department = department;
        this.useraccount = useraccount;
        
        // Initialize registrar directory and get current registrar profile
        this.registrarDirectory = department.getRegistrarDirectory();
        
        // Find the registrar profile associated with this user account
        Person person = useraccount.getPerson();
        this.registrarProfile = registrarDirectory.findRegistrar(person.getPersonId());
        
        initComponents();
        loadProfileData();
    }
    
    /**
     * Load existing profile data into the form fields
     */
    private void loadProfileData() {
        if (registrarProfile != null) {
            Person person = registrarProfile.getPerson();
            
            // Load person information
            fieldName.setText(person.getName());
            fieldEmployeeID.setText(person.getPersonId());
            fieldEmail.setText(person.getEmail());
            fieldPhone.setText(person.getPhone());
            
            // Load registrar-specific information
            fieldOfficeLocation.setText(registrarProfile.getOfficeLocation());
            fieldOfficeHours.setText(registrarProfile.getOfficeHours());
            fieldDepartment.setText(department.getName());
            
            // Make employee ID non-editable (should not be changed)
            fieldEmployeeID.setEditable(false);
            
            // Department field should also be non-editable
            fieldDepartment.setEditable(false);
        }
    }
    
    /**
     * Save/Update profile information
     */
    private void saveProfileData() {
        try {
            // Input validation
            if (!validateInputs()) {
                return;
            }
            
            // Update person information
            Person person = registrarProfile.getPerson();
            person.setName(fieldName.getText().trim());
            person.setEmail(fieldEmail.getText().trim());
            person.setPhone(fieldPhone.getText().trim());
            
            // Update registrar-specific information
            registrarProfile.setOfficeLocation(fieldOfficeLocation.getText().trim());
            registrarProfile.setOfficeHours(fieldOfficeHours.getText().trim());
            
            // Show success message
            JOptionPane.showMessageDialog(this, 
                "Profile updated successfully!", 
                "Success", 
                JOptionPane.INFORMATION_MESSAGE);
                
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, 
                "Error updating profile: " + e.getMessage(), 
                "Error", 
                JOptionPane.ERROR_MESSAGE);
        }
    }
    
    /**
     * Validate input fields before saving
     */
    private boolean validateInputs() {
        // Check for empty required fields
        if (fieldName.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, 
                "Name is required!", 
                "Validation Error", 
                JOptionPane.WARNING_MESSAGE);
            fieldName.requestFocus();
            return false;
        }
        
        if (fieldEmail.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, 
                "Email is required!", 
                "Validation Error", 
                JOptionPane.WARNING_MESSAGE);
            fieldEmail.requestFocus();
            return false;
        }
        
        // Validate email format
        String emailPattern = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$";
        if (!fieldEmail.getText().trim().matches(emailPattern)) {
            JOptionPane.showMessageDialog(this, 
                "Please enter a valid email address!", 
                "Validation Error", 
                JOptionPane.WARNING_MESSAGE);
            fieldEmail.requestFocus();
            return false;
        }
        
        // Validate phone number (optional but if provided, should be valid)
        if (!fieldPhone.getText().trim().isEmpty()) {
            String phonePattern = "^[0-9-+() ]{10,}$";
            if (!fieldPhone.getText().trim().matches(phonePattern)) {
                JOptionPane.showMessageDialog(this, 
                    "Please enter a valid phone number!", 
                    "Validation Error", 
                    JOptionPane.WARNING_MESSAGE);
                fieldPhone.requestFocus();
                return false;
            }
        }
        
        if (fieldOfficeLocation.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, 
                "Office location is required!", 
                "Validation Error", 
                JOptionPane.WARNING_MESSAGE);
            fieldOfficeLocation.requestFocus();
            return false;
        }
        
        if (fieldOfficeHours.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, 
                "Office hours are required!", 
                "Validation Error", 
                JOptionPane.WARNING_MESSAGE);
            fieldOfficeHours.requestFocus();
            return false;
        }
        
        return true;
    }
    
    /**
     * Reset form to original values
     */
    private void resetForm() {
        loadProfileData();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel2 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        btnUpdate = new javax.swing.JButton();
        btnCancel = new javax.swing.JButton();
        fieldName = new javax.swing.JTextField();
        fieldEmployeeID = new javax.swing.JTextField();
        fieldEmail = new javax.swing.JTextField();
        fieldPhone = new javax.swing.JTextField();
        fieldOfficeLocation = new javax.swing.JTextField();
        fieldOfficeHours = new javax.swing.JTextField();
        fieldDepartment = new javax.swing.JTextField();
        Back3 = new javax.swing.JButton();

        jLabel2.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        jLabel2.setText("My Profile");

        jLabel1.setText("Name");

        jLabel3.setText("Employee ID");

        jLabel4.setText("Email");

        jLabel5.setText("Phone");

        jLabel6.setText("Office Location");

        jLabel7.setText("Office Hours");

        jLabel8.setText("Department");

        btnUpdate.setText("Update");
        btnUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateActionPerformed(evt);
            }
        });

        btnCancel.setText("Cancel");
        btnCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelActionPerformed(evt);
            }
        });

        Back3.setText("<< Back");
        Back3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Back3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(49, 49, 49)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel3)
                            .addComponent(jLabel4)
                            .addComponent(jLabel5)
                            .addComponent(jLabel6)
                            .addComponent(jLabel7)
                            .addComponent(jLabel8))
                        .addGap(59, 59, 59)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(fieldName, javax.swing.GroupLayout.DEFAULT_SIZE, 132, Short.MAX_VALUE)
                            .addComponent(fieldEmployeeID)
                            .addComponent(fieldEmail)
                            .addComponent(fieldPhone)
                            .addComponent(fieldOfficeLocation)
                            .addComponent(fieldOfficeHours)
                            .addComponent(fieldDepartment)))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(btnUpdate, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(Back3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addComponent(btnCancel, javax.swing.GroupLayout.DEFAULT_SIZE, 136, Short.MAX_VALUE)))
                .addContainerGap(808, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addComponent(jLabel2)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(fieldName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(fieldEmployeeID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(fieldEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(fieldPhone, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(fieldOfficeLocation, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(fieldOfficeHours, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(fieldDepartment, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(34, 34, 34)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnUpdate)
                    .addComponent(btnCancel))
                .addGap(18, 18, 18)
                .addComponent(Back3)
                .addContainerGap(226, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateActionPerformed
        // TODO add your handling code here:
        saveProfileData();
    }//GEN-LAST:event_btnUpdateActionPerformed

    private void btnCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelActionPerformed
        // TODO add your handling code here:
        int confirm = JOptionPane.showConfirmDialog(this,
            "Are you sure you want to reset all fields to original values?",
            "Confirm Reset",
            JOptionPane.YES_NO_OPTION,
            JOptionPane.QUESTION_MESSAGE);
            
        if (confirm == JOptionPane.YES_OPTION) {
            resetForm();
        }
    }//GEN-LAST:event_btnCancelActionPerformed

    private void Back3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Back3ActionPerformed
        // TODO add your handling code here:
        CardSequencePanel.remove(this);
        CardLayout layout = (CardLayout) CardSequencePanel.getLayout();
        layout.previous(CardSequencePanel);
    }//GEN-LAST:event_Back3ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Back3;
    private javax.swing.JButton btnCancel;
    private javax.swing.JButton btnUpdate;
    private javax.swing.JTextField fieldDepartment;
    private javax.swing.JTextField fieldEmail;
    private javax.swing.JTextField fieldEmployeeID;
    private javax.swing.JTextField fieldName;
    private javax.swing.JTextField fieldOfficeHours;
    private javax.swing.JTextField fieldOfficeLocation;
    private javax.swing.JTextField fieldPhone;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    // End of variables declaration//GEN-END:variables
}
