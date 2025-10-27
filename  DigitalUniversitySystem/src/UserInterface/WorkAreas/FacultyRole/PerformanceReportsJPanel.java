package UserInterface.WorkAreas.FacultyRole;

import info5100.university.example.Department.Department;
import info5100.university.example.Persona.Faculty.FacultyProfile;
import info5100.university.example.CourseSchedule.CourseSchedule;
import info5100.university.example.CourseSchedule.CourseOffer;
import info5100.university.example.CourseSchedule.Seat;
import info5100.university.example.CourseSchedule.SeatAssignment;
import info5100.university.example.CourseCatalog.Course;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.CardLayout;
import java.util.HashMap;
import java.util.Map;

public class PerformanceReportsJPanel extends javax.swing.JPanel {

    private JPanel CardSequencePanel;
    private Department department;
    private FacultyProfile facultyProfile;
    private DefaultTableModel tableModel;

    public PerformanceReportsJPanel(Department d, FacultyProfile fp, JPanel clp) {
        department = d;
        facultyProfile = fp;
        CardSequencePanel = clp;
        initComponents();
        populateTable();
    }

    private void populateTable() {
        tableModel = (DefaultTableModel) reportTable.getModel();
        tableModel.setRowCount(0);
        
        // Set fixed columns for course performance report
        tableModel.setColumnIdentifiers(new String[]{
            "Course Code", "Course Name", "Enrolled", "Avg Grade", "Grade Distribution"
        });
        
        String semester = (String) semesterComboBox.getSelectedItem();
        
        if (semester != null && department != null) {
            CourseSchedule schedule = department.getCourseSchedule(semester);
            if (schedule != null) {
                populateCoursePerformanceReport(schedule);
            }
        }
    }
    
    private void populateCoursePerformanceReport(CourseSchedule schedule) {
        for (CourseOffer co : schedule.getAllCourseOffers()) {
            if (co != null && co.getFacultyProfile() == facultyProfile) {
                Course course = co.getSubjectCourse();
                if (course != null) {
                    String courseCode = co.getCourseNumber();
                    String courseName = course.getName();
                    int enrolled = co.getEnrolledCount();
                    
                    // Calculate average grade and grade distribution
                    double totalGrade = 0;
                    int graded = 0;
                    Map<String, Integer> gradeDistribution = new HashMap<>();
                    
                    if (co.getSeatList() != null) {
                        for (Seat seat : co.getSeatList()) {
                            if (seat.isOccupied()) {
                                SeatAssignment sa = seat.getSeatassignment();
                                if (sa != null) {
                                    String grade = sa.getGrade();
                                    float gradePoint = sa.getGradePoint();
                                    if (gradePoint > 0) {
                                        totalGrade += gradePoint;
                                        graded++;
                                        if (grade != null && !grade.isEmpty()) {
                                            gradeDistribution.put(grade, gradeDistribution.getOrDefault(grade, 0) + 1);
                                        }
                                    }
                                }
                            }
                        }
                    }
                    
                    String avgGrade = graded > 0 ? String.format("%.2f", totalGrade / graded) : "N/A";
                    
                    // Format grade distribution as string
                    StringBuilder distribution = new StringBuilder();
                    for (String grade : new String[]{"A", "A-", "B+", "B", "B-", "C+", "C", "C-", "F"}) {
                        int count = gradeDistribution.getOrDefault(grade, 0);
                        if (count > 0) {
                            if (distribution.length() > 0) distribution.append(", ");
                            distribution.append(grade).append(":").append(count);
                        }
                    }
                    String distString = distribution.length() > 0 ? distribution.toString() : "No grades";
                    
                    Object[] row = {courseCode, courseName, enrolled, avgGrade, distString};
                    tableModel.addRow(row);
                }
            }
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        titlePanel = new javax.swing.JPanel();
        titleLabel = new javax.swing.JLabel();
        mainPanel = new javax.swing.JPanel();
        controlPanel = new javax.swing.JPanel();
        semesterLabel = new javax.swing.JLabel();
        semesterComboBox = new javax.swing.JComboBox();
        refreshBtn = new javax.swing.JButton();
        scrollPane = new javax.swing.JScrollPane();
        reportTable = new javax.swing.JTable();
        buttonPanel = new javax.swing.JPanel();
        backBtn = new javax.swing.JButton();

        setBackground(new java.awt.Color(240, 240, 240));
        setLayout(new java.awt.BorderLayout());

        titlePanel.setBackground(new java.awt.Color(102, 153, 255));

        titleLabel.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        titleLabel.setForeground(new java.awt.Color(255, 255, 255));
        titleLabel.setText("Performance Reports");

        javax.swing.GroupLayout titlePanelLayout = new javax.swing.GroupLayout(titlePanel);
        titlePanel.setLayout(titlePanelLayout);
        titlePanelLayout.setHorizontalGroup(
            titlePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(titlePanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(titleLabel)
                .addContainerGap(800, Short.MAX_VALUE))
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

        semesterLabel.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        semesterLabel.setText("Semester:");

        semesterComboBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Fall2025", "Spring2025" }));
        semesterComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                semesterComboBoxActionPerformed(evt);
            }
        });

        refreshBtn.setBackground(new java.awt.Color(102, 153, 255));
        refreshBtn.setForeground(new java.awt.Color(255, 255, 255));
        refreshBtn.setText("Refresh");
        refreshBtn.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        refreshBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                refreshBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout controlPanelLayout = new javax.swing.GroupLayout(controlPanel);
        controlPanel.setLayout(controlPanelLayout);
        controlPanelLayout.setHorizontalGroup(
            controlPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(controlPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(semesterLabel)
                .addGap(18, 18, 18)
                .addComponent(semesterComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addComponent(refreshBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        controlPanelLayout.setVerticalGroup(
            controlPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
            .addComponent(semesterLabel)
            .addComponent(semesterComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(refreshBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        reportTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Column 1", "Column 2", "Column 3", "Column 4", "Column 5"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        reportTable.setRowHeight(25);
        reportTable.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        scrollPane.setViewportView(reportTable);

        backBtn.setBackground(new java.awt.Color(102, 153, 255));
        backBtn.setForeground(new java.awt.Color(255, 255, 255));
        backBtn.setText("Back");
        backBtn.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        backBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout buttonPanelLayout = new javax.swing.GroupLayout(buttonPanel);
        buttonPanel.setLayout(buttonPanelLayout);
        buttonPanelLayout.setHorizontalGroup(
            buttonPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(buttonPanelLayout.createSequentialGroup()
                .addComponent(backBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        buttonPanelLayout.setVerticalGroup(
            buttonPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(buttonPanelLayout.createSequentialGroup()
                .addComponent(backBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout mainPanelLayout = new javax.swing.GroupLayout(mainPanel);
        mainPanel.setLayout(mainPanelLayout);
        mainPanelLayout.setHorizontalGroup(
            mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mainPanelLayout.createSequentialGroup()
                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(controlPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(scrollPane)
                    .addComponent(buttonPanel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        mainPanelLayout.setVerticalGroup(
            mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mainPanelLayout.createSequentialGroup()
                .addComponent(controlPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(scrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 431, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(buttonPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        add(mainPanel, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents

    private void semesterComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_semesterComboBoxActionPerformed
        populateTable();
    }//GEN-LAST:event_semesterComboBoxActionPerformed

    private void refreshBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_refreshBtnActionPerformed
        populateTable();
    }//GEN-LAST:event_refreshBtnActionPerformed

    private void backBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backBtnActionPerformed
        // Navigate back to Faculty Work Area
        CardSequencePanel.removeAll();
        FacultyWorkAreaJPanel facultyPanel = new FacultyWorkAreaJPanel(department, facultyProfile, CardSequencePanel);
        CardSequencePanel.add("FacultyWorkArea", facultyPanel);
        ((java.awt.CardLayout) CardSequencePanel.getLayout()).next(CardSequencePanel);
    }//GEN-LAST:event_backBtnActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton backBtn;
    private javax.swing.JPanel buttonPanel;
    private javax.swing.JPanel controlPanel;
    private javax.swing.JPanel mainPanel;
    private javax.swing.JButton refreshBtn;
    private javax.swing.JTable reportTable;
    private javax.swing.JScrollPane scrollPane;
    private javax.swing.JComboBox semesterComboBox;
    private javax.swing.JLabel semesterLabel;
    private javax.swing.JLabel titleLabel;
    private javax.swing.JPanel titlePanel;
    // End of variables declaration//GEN-END:variables
}
