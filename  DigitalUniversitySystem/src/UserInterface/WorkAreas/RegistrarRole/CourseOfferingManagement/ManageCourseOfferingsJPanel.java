/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package UserInterface.WorkAreas.RegistrarRole.CourseOfferingManagement;

import info5100.university.example.CourseCatalog.Course;
import info5100.university.example.CourseSchedule.CourseOffer;
import info5100.university.example.CourseSchedule.CourseSchedule;
import info5100.university.example.Department.Department;
import info5100.university.example.Persona.Faculty.FacultyProfile;
import java.util.HashMap;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author chris
 */
public class ManageCourseOfferingsJPanel extends javax.swing.JPanel {
    
    JPanel CardSequencePanel;
    Department department;
    private DefaultTableModel tableModel;
    private String currentSemester = "Fall2025";

    public ManageCourseOfferingsJPanel(Department department, JPanel jpanel) {
        this.CardSequencePanel = jpanel;
        this.department = department;
        initComponents();
        populateCourseOfferingsTable();
    }
    
    private void populateCourseOfferingsTable() {
        tableModel = (DefaultTableModel) tblCourse.getModel();
        tableModel.setRowCount(0);
        
        CourseSchedule courseSchedule = department.getCourseSchedule(currentSemester);
        
        if (courseSchedule != null) {
            java.util.ArrayList<CourseOffer> courseOffers = getCourseOffers(courseSchedule);
            
            if (courseOffers != null) {
                for (CourseOffer courseOffer : courseOffers) {
                    if (courseOffer != null) {
                        Object[] rowData = new Object[6];
                        
                        rowData[0] = courseOffer.getCourseNumber();
                        
                        Course course = courseOffer.getSubjectCourse();
                        rowData[1] = course != null ? course.getName() : "N/A";
                        
                        FacultyProfile faculty = courseOffer.getFacultyProfile();
                        rowData[2] = faculty != null ? faculty.getPerson().getPersonId() : "Not Assigned";
                        
                        // Enrollment Capacity
                        java.util.ArrayList<info5100.university.example.CourseSchedule.Seat> seatList = getSeatList(courseOffer);
                        rowData[3] = seatList != null ? seatList.size() : 0;
                        
                        // Current Enrollment
                        int enrolled = 0;
                        if (seatList != null) {
                            for (info5100.university.example.CourseSchedule.Seat seat : seatList) {
                                if (seat.isOccupied()) {
                                    enrolled++;
                                }
                            }
                        }
                        rowData[4] = enrolled;
                        
                        if (enrolled == 0) {
                            rowData[5] = "Open";
                        } else if (enrolled >= (int)rowData[3]) {
                            rowData[5] = "Full";
                        } else {
                            rowData[5] = "Available";
                        }
                        
                        tableModel.addRow(rowData);
                    }
                }
            }
        }
    }
    
    private java.util.ArrayList<CourseOffer> getCourseOffers(CourseSchedule courseSchedule) {
        try {
            // Try to access the schedule field directly using reflection
            java.lang.reflect.Field scheduleField = courseSchedule.getClass().getDeclaredField("schedule");
            scheduleField.setAccessible(true);
            return (java.util.ArrayList<CourseOffer>) scheduleField.get(courseSchedule);
        } catch (Exception e) {
            // If reflection fails, return empty list
            System.err.println("Error accessing course offers: " + e.getMessage());
            return new java.util.ArrayList<>();
        }
    }
    
     private boolean validateSelection() {
        int selectedRow = tblCourse.getSelectedRow();
        if (selectedRow < 0) {
            JOptionPane.showMessageDialog(this, 
                "Please select a course offering from the table.", 
                "No Selection", 
                JOptionPane.WARNING_MESSAGE);
            return false;
        }
        return true;
    }
    
    
    /**
     * Get the selected course offer
     */
    private CourseOffer getSelectedCourseOffer() {
        int selectedRow = tblCourse.getSelectedRow();
        if (selectedRow >= 0) {
            String courseId = (String) tableModel.getValueAt(selectedRow, 0);
            CourseSchedule courseSchedule = department.getCourseSchedule(currentSemester);
            if (courseSchedule != null) {
                return courseSchedule.getCourseOfferByNumber(courseId);
            }
        }
        return null;
    }
    
    private java.util.ArrayList<info5100.university.example.CourseSchedule.Seat> getSeatList(CourseOffer courseOffer) {
        try {
            // Try getSeatList first
            java.lang.reflect.Method method = courseOffer.getClass().getMethod("getSeatList");
            return (java.util.ArrayList<info5100.university.example.CourseSchedule.Seat>) method.invoke(courseOffer);
        } catch (Exception e1) {
            try {
                // Try getSeatlist (lowercase l)
                java.lang.reflect.Method method = courseOffer.getClass().getMethod("getSeatlist");
                return (java.util.ArrayList<info5100.university.example.CourseSchedule.Seat>) method.invoke(courseOffer);
            } catch (Exception e2) {
                try {
                    // Try direct field access
                    java.lang.reflect.Field field = courseOffer.getClass().getDeclaredField("seatlist");
                    field.setAccessible(true);
                    return (java.util.ArrayList<info5100.university.example.CourseSchedule.Seat>) field.get(courseOffer);
                } catch (Exception e3) {
                    return new java.util.ArrayList<>();
                }
            }
        }
    }
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblTitle = new javax.swing.JLabel();
        btnCreateNewOffering = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblCourse = new javax.swing.JTable();
        btnEdit = new javax.swing.JButton();
        btnDelete = new javax.swing.JButton();
        Back3 = new javax.swing.JButton();

        lblTitle.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        lblTitle.setText("Manage Course Offerings");

        btnCreateNewOffering.setText("Create New Offering");
        btnCreateNewOffering.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCreateNewOfferingActionPerformed(evt);
            }
        });

        tblCourse.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Course ID", "Course Name", "Faculty Assigned", "Enrollment Capacity", "Currect Enrollment", "Status"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tblCourse);

        btnEdit.setText("Edit");
        btnEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditActionPerformed(evt);
            }
        });

        btnDelete.setText("Delete");
        btnDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteActionPerformed(evt);
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
                .addGap(57, 57, 57)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 998, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 328, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(Back3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnEdit, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 160, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addComponent(btnDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnCreateNewOffering, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(81, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addComponent(lblTitle)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnCreateNewOffering)
                    .addComponent(btnDelete)
                    .addComponent(btnEdit))
                .addGap(18, 18, 18)
                .addComponent(Back3)
                .addContainerGap(162, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void Back3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Back3ActionPerformed
        // TODO add your handling code here:
        CardSequencePanel.remove(this);
        ((java.awt.CardLayout) CardSequencePanel.getLayout()).next(CardSequencePanel);

    }//GEN-LAST:event_Back3ActionPerformed

    private void btnEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditActionPerformed
        // TODO add your handling code here:
        if (!validateSelection()) {
            return;
        }
        
        CourseOffer selectedCourseOffer = getSelectedCourseOffer();
        
        if (selectedCourseOffer != null) {
            // Navigate to EditCourseOfferingJPanel with correct parameters
            EditCourseOfferingJPanel editPanel = new EditCourseOfferingJPanel(
                department,
                CardSequencePanel, 
                selectedCourseOffer,
                currentSemester
            );
            
            CardSequencePanel.removeAll();
            CardSequencePanel.add("EditCourseOffering", editPanel);
            ((java.awt.CardLayout) CardSequencePanel.getLayout()).next(CardSequencePanel);
        } else {
            JOptionPane.showMessageDialog(this, 
                "Could not find the selected course offering.", 
                "Error", 
                JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnEditActionPerformed

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
        // TODO add your handling code here:
        if (!validateSelection()) {
            return;
        }
        
        int selectedRow = tblCourse.getSelectedRow();
        String courseId = (String) tableModel.getValueAt(selectedRow, 0);
        String courseName = (String) tableModel.getValueAt(selectedRow, 1);
        int enrolledCount = (int) tableModel.getValueAt(selectedRow, 4);
        
        int result = JOptionPane.showConfirmDialog(this,
            "Delete " + courseId + " - " + courseName + "?",
            "Confirm Deletion",
            JOptionPane.YES_NO_OPTION,
            JOptionPane.WARNING_MESSAGE);
        
        if (result == JOptionPane.YES_OPTION) {
            if (enrolledCount > 0) {
                JOptionPane.showMessageDialog(this,
                    "Cannot delete - students enrolled!",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            // Get course offer and remove it
            CourseSchedule courseSchedule = department.getCourseSchedule(currentSemester);
            if (courseSchedule != null) {
                CourseOffer toDelete = courseSchedule.getCourseOfferByNumber(courseId);
                if (toDelete != null) {
                    try {
                        java.util.ArrayList<CourseOffer> offers = getCourseOffers(courseSchedule);
                        offers.remove(toDelete);
                        tableModel.removeRow(selectedRow);
                        JOptionPane.showMessageDialog(this, "Deleted successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
                    } catch (Exception e) {
                        JOptionPane.showMessageDialog(this, "Delete failed: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        }
    }//GEN-LAST:event_btnDeleteActionPerformed

    private void btnCreateNewOfferingActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCreateNewOfferingActionPerformed
        // Navigate to CreateCourseOfferingJPanel
        CreateCourseOfferingJPanel createPanel = new CreateCourseOfferingJPanel(
            department, 
            CardSequencePanel,
            currentSemester
        );
        
        CardSequencePanel.removeAll();
        CardSequencePanel.add("CreateCourseOffering", createPanel);
        ((java.awt.CardLayout) CardSequencePanel.getLayout()).next(CardSequencePanel);
    }//GEN-LAST:event_btnCreateNewOfferingActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Back1;
    private javax.swing.JButton Back2;
    private javax.swing.JButton Back3;
    private javax.swing.JButton btnCreateNewOffering;
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnEdit;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblTitle;
    private javax.swing.JTable tblCourse;
    // End of variables declaration//GEN-END:variables

    
}
