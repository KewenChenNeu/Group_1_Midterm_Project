package UserInterface.WorkAreas.FacultyRole;

import info5100.university.example.Department.Department;
import info5100.university.example.Persona.Faculty.FacultyProfile;
import info5100.university.example.Persona.Faculty.FacultyAssignment;
import info5100.university.example.CourseSchedule.CourseOffer;
import info5100.university.example.CourseCatalog.Course;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;

public class CourseManagementJPanel extends javax.swing.JPanel {

    private JPanel CardSequencePanel;
    private Department department;
    private FacultyProfile facultyProfile;
    private DefaultTableModel courseTableModel;

    public CourseManagementJPanel(Department d, FacultyProfile fp, JPanel clp) {
        department = d;
        facultyProfile = fp;
        CardSequencePanel = clp;
        initComponents();
        populateCourseTable();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        titlePanel = new javax.swing.JPanel();
        titleLabel = new javax.swing.JLabel();
        mainPanel = new javax.swing.JPanel();
        scrollPane = new javax.swing.JScrollPane();
        courseTable = new javax.swing.JTable();
        buttonPanel = new javax.swing.JPanel();
        editCourseBtn = new javax.swing.JButton();
        uploadSyllabusBtn = new javax.swing.JButton();
        toggleEnrollmentBtn = new javax.swing.JButton();
        viewStudentsBtn = new javax.swing.JButton();
        backBtn = new javax.swing.JButton();

        setBackground(new java.awt.Color(240, 240, 240));
        setLayout(new java.awt.BorderLayout());

        titlePanel.setBackground(new java.awt.Color(102, 153, 255));

        titleLabel.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        titleLabel.setForeground(new java.awt.Color(255, 255, 255));
        titleLabel.setText("Course Management");

        javax.swing.GroupLayout titlePanelLayout = new javax.swing.GroupLayout(titlePanel);
        titlePanel.setLayout(titlePanelLayout);
        titlePanelLayout.setHorizontalGroup(
            titlePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(titlePanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(titleLabel)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        titlePanelLayout.setVerticalGroup(
            titlePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(titlePanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(titleLabel)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        add(titlePanel, java.awt.BorderLayout.NORTH);

        mainPanel.setBorder(javax.swing.BorderFactory.createEmptyBorder(20, 20, 20, 20));

        courseTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Course ID", "Course Name", "Credits", "Schedule", "Capacity", "Enrolled", "Status"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.Integer.class, java.lang.String.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        courseTable.setRowHeight(25);
        courseTable.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        scrollPane.setViewportView(courseTable);

        buttonPanel.setBackground(new java.awt.Color(240, 240, 240));
        buttonPanel.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 20, 10));

        editCourseBtn.setBackground(new java.awt.Color(102, 153, 255));
        editCourseBtn.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        editCourseBtn.setForeground(new java.awt.Color(255, 255, 255));
        editCourseBtn.setText("Edit Course Details");
        editCourseBtn.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        editCourseBtn.setPreferredSize(new java.awt.Dimension(160, 35));
        editCourseBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editCourseActionPerformed(evt);
            }
        });
        buttonPanel.add(editCourseBtn);

        uploadSyllabusBtn.setBackground(new java.awt.Color(102, 153, 255));
        uploadSyllabusBtn.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        uploadSyllabusBtn.setForeground(new java.awt.Color(255, 255, 255));
        uploadSyllabusBtn.setText("Upload Syllabus");
        uploadSyllabusBtn.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        uploadSyllabusBtn.setPreferredSize(new java.awt.Dimension(160, 35));
        uploadSyllabusBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                uploadSyllabusActionPerformed(evt);
            }
        });
        buttonPanel.add(uploadSyllabusBtn);

        toggleEnrollmentBtn.setBackground(new java.awt.Color(102, 153, 255));
        toggleEnrollmentBtn.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        toggleEnrollmentBtn.setForeground(new java.awt.Color(255, 255, 255));
        toggleEnrollmentBtn.setText("Toggle Enrollment");
        toggleEnrollmentBtn.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        toggleEnrollmentBtn.setPreferredSize(new java.awt.Dimension(160, 35));
        toggleEnrollmentBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                toggleEnrollmentActionPerformed(evt);
            }
        });
        buttonPanel.add(toggleEnrollmentBtn);

        viewStudentsBtn.setBackground(new java.awt.Color(102, 153, 255));
        viewStudentsBtn.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        viewStudentsBtn.setForeground(new java.awt.Color(255, 255, 255));
        viewStudentsBtn.setText("View Students");
        viewStudentsBtn.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        viewStudentsBtn.setPreferredSize(new java.awt.Dimension(160, 35));
        viewStudentsBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                viewStudentsActionPerformed(evt);
            }
        });
        buttonPanel.add(viewStudentsBtn);

        backBtn.setBackground(new java.awt.Color(102, 153, 255));
        backBtn.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        backBtn.setForeground(new java.awt.Color(255, 255, 255));
        backBtn.setText("Back");
        backBtn.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        backBtn.setPreferredSize(new java.awt.Dimension(160, 35));
        backBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout mainPanelLayout = new javax.swing.GroupLayout(mainPanel);
        mainPanel.setLayout(mainPanelLayout);
        mainPanelLayout.setHorizontalGroup(
            mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(scrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 860, Short.MAX_VALUE)
            .addComponent(buttonPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(mainPanelLayout.createSequentialGroup()
                .addGap(346, 346, 346)
                .addComponent(backBtn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        mainPanelLayout.setVerticalGroup(
            mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mainPanelLayout.createSequentialGroup()
                .addComponent(scrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 400, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(buttonPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(backBtn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        add(mainPanel, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents

    private void populateCourseTable() {
        courseTableModel = (DefaultTableModel) courseTable.getModel();
        courseTableModel.setRowCount(0);

        if (facultyProfile != null) {
            ArrayList<FacultyAssignment> assignments = facultyProfile.getFacultyAssignments();
            if (assignments != null) {
                for (FacultyAssignment fa : assignments) {
                    CourseOffer co = fa.getCourseOffer();
                    if (co != null) {
                        Course course = co.getSubjectCourse();
                        Object[] row = {
                            course.getCOurseNumber(),
                            course.getCredits(),
                            "MWF 10:00-11:00",
                            co.getSeatList().size(),
                            co.getEnrolledCount(),
                            co.isEnrollmentOpen() ? "Open" : "Closed"
                        };
                        courseTableModel.addRow(row);
                    }
                }
            }
        }
    }

    private void editCourseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editCourseActionPerformed
        int selectedRow = courseTable.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Please select a course to edit!", "No Selection", JOptionPane.WARNING_MESSAGE);
            return;
        }

        String courseId = (String) courseTableModel.getValueAt(selectedRow, 0);
        String courseName = (String) courseTableModel.getValueAt(selectedRow, 1);

        JPanel editPanel = new JPanel(new GridLayout(4, 2, 10, 10));
        editPanel.add(new JLabel("Course ID:"));
        JTextField idField = new JTextField(courseId);
        idField.setEditable(false);
        editPanel.add(idField);

        editPanel.add(new JLabel("Course Name:"));
        JTextField nameField = new JTextField(courseName);
        editPanel.add(nameField);

        editPanel.add(new JLabel("Schedule:"));
        JTextField scheduleField = new JTextField("MWF 10:00-11:00");
        editPanel.add(scheduleField);

        editPanel.add(new JLabel("Capacity:"));
        JTextField capacityField = new JTextField(String.valueOf(courseTableModel.getValueAt(selectedRow, 4)));
        editPanel.add(capacityField);

        int result = JOptionPane.showConfirmDialog(this, editPanel, "Edit Course Details",
                                                   JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
        if (result == JOptionPane.OK_OPTION) {
            JOptionPane.showMessageDialog(this, "Course details updated successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
            populateCourseTable();
        }
    }//GEN-LAST:event_editCourseActionPerformed

    private void uploadSyllabusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_uploadSyllabusActionPerformed
        int selectedRow = courseTable.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Please select a course!", "No Selection", JOptionPane.WARNING_MESSAGE);
            return;
        }

        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Upload Syllabus");
        fileChooser.setFileFilter(new javax.swing.filechooser.FileNameExtensionFilter("PDF Files", "pdf"));

        int result = fileChooser.showOpenDialog(this);
        if (result == JFileChooser.APPROVE_OPTION) {
            String fileName = fileChooser.getSelectedFile().getName();
            JOptionPane.showMessageDialog(this, "Syllabus '" + fileName + "' uploaded successfully!",
                                        "Success", JOptionPane.INFORMATION_MESSAGE);
        }
    }//GEN-LAST:event_uploadSyllabusActionPerformed

    private void toggleEnrollmentActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_toggleEnrollmentActionPerformed
        int selectedRow = courseTable.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Please select a course!", "No Selection", JOptionPane.WARNING_MESSAGE);
            return;
        }

        String currentStatus = (String) courseTableModel.getValueAt(selectedRow, 6);
        String newStatus = currentStatus.equals("Open") ? "Closed" : "Open";

        int confirm = JOptionPane.showConfirmDialog(this,
            "Change enrollment status to " + newStatus + "?",
            "Confirm Status Change",
            JOptionPane.YES_NO_OPTION);

        if (confirm == JOptionPane.YES_OPTION) {
            courseTableModel.setValueAt(newStatus, selectedRow, 6);
            JOptionPane.showMessageDialog(this, "Enrollment status changed to " + newStatus,
                                        "Success", JOptionPane.INFORMATION_MESSAGE);
        }
    }//GEN-LAST:event_toggleEnrollmentActionPerformed

    private void viewStudentsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_viewStudentsActionPerformed
        int selectedRow = courseTable.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Please select a course!", "No Selection", JOptionPane.WARNING_MESSAGE);
            return;
        }

        String courseId = (String) courseTableModel.getValueAt(selectedRow, 0);

        CardSequencePanel.removeAll();
        StudentManagementJPanel studentPanel = new StudentManagementJPanel(department, facultyProfile, CardSequencePanel, courseId);
        CardSequencePanel.add("StudentManagement", studentPanel);
        ((CardLayout) CardSequencePanel.getLayout()).next(CardSequencePanel);
    }//GEN-LAST:event_viewStudentsActionPerformed

    private void backActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backActionPerformed
        CardSequencePanel.removeAll();
        FacultyWorkAreaJPanel facultyPanel = new FacultyWorkAreaJPanel(department, facultyProfile, CardSequencePanel);
        CardSequencePanel.add("FacultyWorkArea", facultyPanel);
        ((CardLayout) CardSequencePanel.getLayout()).next(CardSequencePanel);
    }//GEN-LAST:event_backActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton backBtn;
    private javax.swing.JPanel buttonPanel;
    private javax.swing.JTable courseTable;
    private javax.swing.JButton editCourseBtn;
    private javax.swing.JPanel mainPanel;
    private javax.swing.JScrollPane scrollPane;
    private javax.swing.JLabel titleLabel;
    private javax.swing.JPanel titlePanel;
    private javax.swing.JButton toggleEnrollmentBtn;
    private javax.swing.JButton uploadSyllabusBtn;
    private javax.swing.JButton viewStudentsBtn;
    // End of variables declaration//GEN-END:variables
}