/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package UserInterface.WorkAreas.RegistrarRole.CourseOfferingManagement;

import info5100.university.example.CourseCatalog.Course;
import info5100.university.example.CourseSchedule.CourseOffer;
import info5100.university.example.Department.Department;
import info5100.university.example.Persona.Faculty.FacultyDirectory;
import info5100.university.example.Persona.Faculty.FacultyProfile;
import javax.swing.*;
import java.awt.*;

/**
 *
 * @author chris
 */
public class EditCourseOfferingJPanel extends javax.swing.JPanel {
    
    private JPanel CardSequencePanel;
    private Department department;
    private CourseOffer courseOffer;
    private String semester;

    /**
     * Creates new form EditCourseOfferingJPanel
     */
    public EditCourseOfferingJPanel(Department dept, JPanel jpanel, CourseOffer co, String sem) {
        this.CardSequencePanel = jpanel;
        this.department = dept;
        this.courseOffer = co;
        this.semester = sem;
        initComponents();
        populateFields();
    }
    
    private void populateFields() {
        if (courseOffer != null) {
            // Populate Semester (read-only)
            fieldSemester.setText(semester);
            fieldSemester.setEditable(false);
            
            // Populate Course (read-only)
            fieldCourse.setText(courseOffer.getCourseNumber());
            fieldCourse.setEditable(false);
            
            // Get course details
            Course course = courseOffer.getSubjectCourse();
            if (course != null) {
                fieldCourse.setText(courseOffer.getCourseNumber() + " - " + course.getName());
            }
            
            // Populate Faculty
            FacultyProfile faculty = courseOffer.getFacultyProfile();
            if (faculty != null && faculty.getPerson() != null) {
                fieldFaculity.setText(faculty.getPerson().getPersonId());
            } else {
                fieldFaculity.setText("Not Assigned");
            }
            
            // Populate Enrollment Capacity
            int capacity = getSeatCapacity();
            fieldEnrollement.setText(String.valueOf(capacity));
            
        }
    }
    
    private int getSeatCapacity() {
        try {
            // Try different method names to get seat list
            java.util.ArrayList seats = null;
            try {
                java.lang.reflect.Method method = courseOffer.getClass().getMethod("getSeatList");
                seats = (java.util.ArrayList) method.invoke(courseOffer);
            } catch (NoSuchMethodException e) {
                try {
                    java.lang.reflect.Method method = courseOffer.getClass().getMethod("getSeatlist");
                    seats = (java.util.ArrayList) method.invoke(courseOffer);
                } catch (NoSuchMethodException e2) {
                    java.lang.reflect.Field field = courseOffer.getClass().getDeclaredField("seatlist");
                    field.setAccessible(true);
                    seats = (java.util.ArrayList) field.get(courseOffer);
                }
            }
            return seats != null ? seats.size() : 0;
        } catch (Exception e) {
            return 0;
        }
    }
    
    private int getCurrentEnrollment() {
        int enrolled = 0;
        try {
            java.util.ArrayList seats = null;
            try {
                java.lang.reflect.Method method = courseOffer.getClass().getMethod("getSeatList");
                seats = (java.util.ArrayList) method.invoke(courseOffer);
            } catch (NoSuchMethodException e) {
                try {
                    java.lang.reflect.Method method = courseOffer.getClass().getMethod("getSeatlist");
                    seats = (java.util.ArrayList) method.invoke(courseOffer);
                } catch (NoSuchMethodException e2) {
                    java.lang.reflect.Field field = courseOffer.getClass().getDeclaredField("seatlist");
                    field.setAccessible(true);
                    seats = (java.util.ArrayList) field.get(courseOffer);
                }
            }
            
            if (seats != null) {
                for (Object seatObj : seats) {
                    java.lang.reflect.Method isOccupiedMethod = seatObj.getClass().getMethod("isOccupied");
                    Boolean occupied = (Boolean) isOccupiedMethod.invoke(seatObj);
                    if (occupied) {
                        enrolled++;
                    }
                }
            }
        } catch (Exception e) {
            // Ignore
        }
        return enrolled;
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
        btnUpdate = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        btnCancel = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        fieldEnrollement = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        fieldFaculity = new javax.swing.JTextField();
        fieldCourse = new javax.swing.JTextField();
        fieldSemester = new javax.swing.JTextField();
        Back3 = new javax.swing.JButton();

        jLabel1.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        jLabel1.setText("Update Course Offering");

        btnUpdate.setText("Update");
        btnUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateActionPerformed(evt);
            }
        });

        jLabel2.setText("Semester");

        btnCancel.setText("Cancel");
        btnCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelActionPerformed(evt);
            }
        });

        jLabel3.setText("Faculty Assignment");

        jLabel4.setText("Course");

        jLabel5.setText("Enrollment Capacity");

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
                .addGap(53, 53, 53)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 328, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(755, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(84, 84, 84)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(Back3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel3)
                    .addComponent(jLabel2)
                    .addComponent(jLabel4)
                    .addComponent(jLabel5)
                    .addComponent(btnUpdate, javax.swing.GroupLayout.DEFAULT_SIZE, 126, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(btnCancel, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 136, Short.MAX_VALUE)
                    .addComponent(fieldEnrollement, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(fieldFaculity)
                    .addComponent(fieldCourse, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(fieldSemester, javax.swing.GroupLayout.Alignment.LEADING))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(fieldSemester, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(fieldCourse, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(15, 15, 15)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(fieldFaculity, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(fieldEnrollement, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(158, 158, 158)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnUpdate)
                    .addComponent(btnCancel))
                .addGap(18, 18, 18)
                .addComponent(Back3)
                .addContainerGap(119, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelActionPerformed
        // TODO add your handling code here:
        // Confirm cancellation
        int result = JOptionPane.showConfirmDialog(this,
            "Are you sure you want to cancel? Any unsaved changes will be lost.",
            "Confirm Cancel",
            JOptionPane.YES_NO_OPTION,
            JOptionPane.WARNING_MESSAGE);
        
        if (result == JOptionPane.YES_OPTION) {
            // Navigate back to ManageCourseOfferingsJPanel
            navigateBack();
        }
    }//GEN-LAST:event_btnCancelActionPerformed

    private void navigateBack() {
        CardSequencePanel.removeAll();
        ManageCourseOfferingsJPanel managePanel = new ManageCourseOfferingsJPanel(department, CardSequencePanel);
        CardSequencePanel.add("ManageCourseOfferings", managePanel);
        ((CardLayout) CardSequencePanel.getLayout()).next(CardSequencePanel);
    }
    
    private void btnUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateActionPerformed
        // TODO add your handling code here:
        // Validate inputs
        if (!validateInputs()) {
            return;
        }
        
        try {
            // Update capacity
            int newCapacity = Integer.parseInt(fieldEnrollement.getText().trim());
            int currentEnrollment = getCurrentEnrollment();
            
            if (newCapacity < currentEnrollment) {
                JOptionPane.showMessageDialog(this,
                    "Capacity cannot be less than current enrollment (" + currentEnrollment + ").",
                    "Invalid Capacity",
                    JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            // Update seats if capacity changed
            int currentCapacity = getSeatCapacity();
            if (newCapacity != currentCapacity) {
                if (newCapacity > currentCapacity) {
                    // Add more seats
                    courseOffer.generatSeats(newCapacity - currentCapacity);
                }
                // Note: Reducing seats would require more complex logic
            }
            
            // Update faculty assignment
            String facultyId = fieldFaculity.getText().trim();
            if (!facultyId.isEmpty() && !"Not Assigned".equalsIgnoreCase(facultyId)) {
                FacultyDirectory facultyDir = department.getFacultyDirectory();
                if (facultyDir != null) {
                    FacultyProfile faculty = facultyDir.findTeachingFaculty(facultyId);
                    if (faculty != null) {
                        courseOffer.AssignAsTeacher(faculty);
                    } else {
                        JOptionPane.showMessageDialog(this,
                            "Faculty ID not found: " + facultyId + "\nFaculty assignment not changed.",
                            "Warning",
                            JOptionPane.WARNING_MESSAGE);
                    }
                }
            }
            
            // Show success message
            JOptionPane.showMessageDialog(this,
                "Course offering updated successfully!\n" +
                "Course: " + courseOffer.getCourseNumber() + "\n" +
                "New Capacity: " + newCapacity,
                "Success",
                JOptionPane.INFORMATION_MESSAGE);
            
            // Navigate back
            navigateBack();
            
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this,
                "Please enter valid numeric values for capacity.",
                "Invalid Input",
                JOptionPane.ERROR_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this,
                "Error updating course offering: " + e.getMessage(),
                "Error",
                JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnUpdateActionPerformed

    private boolean validateInputs() {
        // Validate capacity
        String capacity = fieldEnrollement.getText().trim();
        if (capacity.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                "Please enter enrollment capacity.",
                "Missing Information",
                JOptionPane.WARNING_MESSAGE);
            return false;
        }
        
        try {
            int cap = Integer.parseInt(capacity);
            if (cap <= 0) {
                JOptionPane.showMessageDialog(this,
                    "Capacity must be greater than 0.",
                    "Invalid Capacity",
                    JOptionPane.WARNING_MESSAGE);
                return false;
            }
            if (cap > 500) {
                JOptionPane.showMessageDialog(this,
                    "Capacity cannot exceed 500.",
                    "Invalid Capacity",
                    JOptionPane.WARNING_MESSAGE);
                return false;
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this,
                "Please enter a valid number for capacity.",
                "Invalid Input",
                JOptionPane.WARNING_MESSAGE);
            return false;
        }
        
        return true;
    }
    
    private void Back3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Back3ActionPerformed
        // TODO add your handling code here:
        navigateBack();
    }//GEN-LAST:event_Back3ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Back3;
    private javax.swing.JButton btnCancel;
    private javax.swing.JButton btnUpdate;
    private javax.swing.JTextField fieldCourse;
    private javax.swing.JTextField fieldEnrollement;
    private javax.swing.JTextField fieldFaculity;
    private javax.swing.JTextField fieldSemester;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    // End of variables declaration//GEN-END:variables

    
}
