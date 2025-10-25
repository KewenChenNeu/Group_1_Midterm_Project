package UserInterface.WorkAreas.FacultyRole;

import info5100.university.example.Department.Department;
import info5100.university.example.Persona.Faculty.FacultyProfile;
import info5100.university.example.Persona.Faculty.FacultyAssignment;
import info5100.university.example.Persona.StudentProfile;
import info5100.university.example.Persona.Person;
import info5100.university.example.CourseSchedule.CourseOffer;
import info5100.university.example.CourseSchedule.SeatAssignment;
import info5100.university.example.CourseSchedule.CourseLoad;
import info5100.university.example.CourseSchedule.Seat;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.*;
import java.util.List;

public class StudentManagementJPanel extends javax.swing.JPanel {

    private JPanel CardSequencePanel;
    private Department department;
    private FacultyProfile facultyProfile;
    private DefaultTableModel studentTableModel;
    private String selectedCourseId;

    public StudentManagementJPanel(Department d, FacultyProfile fp, JPanel clp) {
        this(d, fp, clp, null);
    }

    public StudentManagementJPanel(Department d, FacultyProfile fp, JPanel clp, String courseId) {
        department = d;
        facultyProfile = fp;
        CardSequencePanel = clp;
        selectedCourseId = courseId;
        initComponents();
        populateCourseComboBox();
        if (courseId != null) {
            courseComboBox.setSelectedItem(courseId);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        titlePanel = new javax.swing.JPanel();
        titleLabel = new javax.swing.JLabel();
        mainPanel = new javax.swing.JPanel();
        coursePanel = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        courseComboBox = new javax.swing.JComboBox();
        gpaLabel = new javax.swing.JLabel();
        scrollPane = new javax.swing.JScrollPane();
        studentTable = new javax.swing.JTable();
        buttonPanel = new javax.swing.JPanel();
        viewProgressBtn = new javax.swing.JButton();
        viewTranscriptBtn = new javax.swing.JButton();
        gradeAssignmentBtn = new javax.swing.JButton();
        computeFinalGradeBtn = new javax.swing.JButton();
        rankStudentsBtn = new javax.swing.JButton();
        backBtn = new javax.swing.JButton();

        setBackground(new java.awt.Color(240, 240, 240));
        setLayout(new java.awt.BorderLayout());

        titlePanel.setBackground(new java.awt.Color(102, 153, 255));

        titleLabel.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        titleLabel.setForeground(new java.awt.Color(255, 255, 255));
        titleLabel.setText("Student Management");

        javax.swing.GroupLayout titlePanelLayout = new javax.swing.GroupLayout(titlePanel);
        titlePanel.setLayout(titlePanelLayout);
        titlePanelLayout.setHorizontalGroup(
            titlePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(titlePanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(titleLabel)
                .addContainerGap(744, Short.MAX_VALUE))
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

        coursePanel.setBackground(new java.awt.Color(255, 255, 255));
        coursePanel.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

        jLabel1.setText("Select Course:");
        coursePanel.add(jLabel1);

        courseComboBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-- Select Course --" }));
        courseComboBox.setPreferredSize(new java.awt.Dimension(300, 30));
        courseComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                courseComboBoxActionPerformed(evt);
            }
        });
        coursePanel.add(courseComboBox);

        gpaLabel.setFont(new java.awt.Font("Dialog", 1, 16)); // NOI18N
        gpaLabel.setForeground(new java.awt.Color(102, 153, 255));
        gpaLabel.setText("    Class GPA: N/A");
        coursePanel.add(gpaLabel);

        studentTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Student ID", "Student Name", "Email", "Current Grade", "Letter Grade", "Percentage", "Rank"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Integer.class
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
        studentTable.setRowHeight(25);
        studentTable.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        scrollPane.setViewportView(studentTable);

        buttonPanel.setBackground(new java.awt.Color(240, 240, 240));

        viewProgressBtn.setBackground(new java.awt.Color(102, 153, 255));
        viewProgressBtn.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        viewProgressBtn.setForeground(new java.awt.Color(255, 255, 255));
        viewProgressBtn.setText("View Progress");
        viewProgressBtn.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        viewProgressBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                viewProgressActionPerformed(evt);
            }
        });

        viewTranscriptBtn.setBackground(new java.awt.Color(102, 153, 255));
        viewTranscriptBtn.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        viewTranscriptBtn.setForeground(new java.awt.Color(255, 255, 255));
        viewTranscriptBtn.setText("View Transcript");
        viewTranscriptBtn.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        viewTranscriptBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                viewTranscriptActionPerformed(evt);
            }
        });

        gradeAssignmentBtn.setBackground(new java.awt.Color(102, 153, 255));
        gradeAssignmentBtn.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        gradeAssignmentBtn.setForeground(new java.awt.Color(255, 255, 255));
        gradeAssignmentBtn.setText("Grade Assignment");
        gradeAssignmentBtn.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        gradeAssignmentBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                gradeAssignmentActionPerformed(evt);
            }
        });

        computeFinalGradeBtn.setBackground(new java.awt.Color(102, 153, 255));
        computeFinalGradeBtn.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        computeFinalGradeBtn.setForeground(new java.awt.Color(255, 255, 255));
        computeFinalGradeBtn.setText("Compute Final Grade");
        computeFinalGradeBtn.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        computeFinalGradeBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                computeFinalGradeActionPerformed(evt);
            }
        });

        rankStudentsBtn.setBackground(new java.awt.Color(102, 153, 255));
        rankStudentsBtn.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        rankStudentsBtn.setForeground(new java.awt.Color(255, 255, 255));
        rankStudentsBtn.setText("Rank Students");
        rankStudentsBtn.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        rankStudentsBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rankStudentsActionPerformed(evt);
            }
        });

        backBtn.setBackground(new java.awt.Color(102, 153, 255));
        backBtn.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        backBtn.setForeground(new java.awt.Color(255, 255, 255));
        backBtn.setText("Back");
        backBtn.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        backBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout buttonPanelLayout = new javax.swing.GroupLayout(buttonPanel);
        buttonPanel.setLayout(buttonPanelLayout);
        buttonPanelLayout.setHorizontalGroup(
            buttonPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(buttonPanelLayout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addGroup(buttonPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(buttonPanelLayout.createSequentialGroup()
                        .addComponent(viewProgressBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(20, 20, 20)
                        .addComponent(viewTranscriptBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(20, 20, 20)
                        .addComponent(gradeAssignmentBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(buttonPanelLayout.createSequentialGroup()
                        .addComponent(computeFinalGradeBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(20, 20, 20)
                        .addComponent(rankStudentsBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(20, 20, 20)
                        .addComponent(backBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(50, 50, 50))
        );
        buttonPanelLayout.setVerticalGroup(
            buttonPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(buttonPanelLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(buttonPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(viewProgressBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(viewTranscriptBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(gradeAssignmentBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addGroup(buttonPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(computeFinalGradeBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(rankStudentsBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(backBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20))
        );

        javax.swing.GroupLayout mainPanelLayout = new javax.swing.GroupLayout(mainPanel);
        mainPanel.setLayout(mainPanelLayout);
        mainPanelLayout.setHorizontalGroup(
            mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(coursePanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(scrollPane)
            .addComponent(buttonPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        mainPanelLayout.setVerticalGroup(
            mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mainPanelLayout.createSequentialGroup()
                .addComponent(coursePanel, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(scrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(buttonPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        add(mainPanel, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents

    private void populateCourseComboBox() {
        courseComboBox.removeAllItems();
        courseComboBox.addItem("-- Select Course --");

        if (facultyProfile != null) {
            ArrayList<FacultyAssignment> assignments = facultyProfile.getFacultyAssignments();
            if (assignments != null) {
                for (FacultyAssignment fa : assignments) {
                    CourseOffer co = fa.getCourseOffer();
                    if (co != null) {
                        String courseInfo = co.getCourseNumber() + " - " + co.getSubjectCourse().getCourseName();
                        courseComboBox.addItem(courseInfo);
                    }
                }
            }
        }
    }

    private void courseComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_courseComboBoxActionPerformed
        loadStudents();
    }//GEN-LAST:event_courseComboBoxActionPerformed

    private void loadStudents() {
        studentTableModel = (DefaultTableModel) studentTable.getModel();
        studentTableModel.setRowCount(0);

        String selectedCourse = (String) courseComboBox.getSelectedItem();
        if (selectedCourse == null || selectedCourse.equals("-- Select Course --")) {
            gpaLabel.setText("    Class GPA: N/A");
            return;
        }

        String courseId = selectedCourse.split(" - ")[0];
        List<StudentInfo> studentInfoList = new ArrayList<>();

        if (facultyProfile != null) {
            ArrayList<FacultyAssignment> assignments = facultyProfile.getFacultyAssignments();
            for (FacultyAssignment fa : assignments) {
                CourseOffer co = fa.getCourseOffer();
                if (co != null && co.getCourseNumber().equals(courseId)) {
                    ArrayList<Seat> seats = co.getSeatList();
                    if (seats != null) {
                        for (Seat seat : seats) {
                            if (seat.isOccupied()) {
                                SeatAssignment sa = seat.getSeatAssignment();
                                if (sa != null) {
                                    CourseLoad cl = sa.getCourseLoad();
                                    if (cl != null) {
                                        StudentProfile sp = cl.getStudentProfile();
                                        if (sp != null) {
                                            Person person = sp.getPerson();
                                            StudentInfo info = new StudentInfo();
                                            info.studentId = person.getPersonId();
                                            info.name = person.getName() != null ? person.getName() : "Student " + info.studentId;
                                            info.email = person.getEmail() != null ? person.getEmail() : "N/A";
                                            info.gradePoint = sa.getGradePoint();
                                            info.letterGrade = sa.getLetterGrade();
                                            info.percentage = calculatePercentage(info.letterGrade);
                                            studentInfoList.add(info);
                                        }
                                    }
                                }
                            }
                        }
                    }
                    break;
                }
            }
        }

        studentInfoList.sort((s1, s2) -> Double.compare(s2.percentage, s1.percentage));

        double totalGPA = 0;
        int rank = 1;
        for (StudentInfo info : studentInfoList) {
            info.rank = rank++;
            totalGPA += info.gradePoint;

            Object[] row = {
                info.studentId,
                info.name,
                info.email,
                String.format("%.2f", info.gradePoint),
                info.letterGrade,
                String.format("%.1f%%", info.percentage),
                info.rank
            };
            studentTableModel.addRow(row);
        }

        if (!studentInfoList.isEmpty()) {
            double classGPA = totalGPA / studentInfoList.size();
            gpaLabel.setText(String.format("    Class GPA: %.2f", classGPA));
        } else {
            gpaLabel.setText("    Class GPA: N/A");
        }
    }

    private double calculatePercentage(String letterGrade) {
        switch (letterGrade) {
            case "A": return 95;
            case "A-": return 92;
            case "B+": return 88;
            case "B": return 85;
            case "B-": return 82;
            case "C+": return 78;
            case "C": return 75;
            case "C-": return 72;
            case "D": return 65;
            case "F": return 50;
            default: return 0;
        }
    }

    private void viewProgressActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_viewProgressActionPerformed
        int selectedRow = studentTable.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Please select a student!", "No Selection", JOptionPane.WARNING_MESSAGE);
            return;
        }

        String studentId = (String) studentTableModel.getValueAt(selectedRow, 0);
        String studentName = (String) studentTableModel.getValueAt(selectedRow, 1);

        JTextArea progressArea = new JTextArea(15, 50);
        progressArea.setText("Progress Report for " + studentName + " (" + studentId + ")\n");
        progressArea.append("=" .repeat(60) + "\n\n");
        progressArea.append("Current Grade: " + studentTableModel.getValueAt(selectedRow, 4) + "\n");
        progressArea.append("Percentage: " + studentTableModel.getValueAt(selectedRow, 5) + "\n");
        progressArea.append("Class Rank: " + studentTableModel.getValueAt(selectedRow, 6) + "\n\n");
        progressArea.append("Assignments:\n");
        progressArea.append("- Assignment 1: 90/100\n");
        progressArea.append("- Assignment 2: 85/100\n");
        progressArea.append("- Midterm Exam: 88/100\n");
        progressArea.append("- Project: 92/100\n");
        progressArea.setEditable(false);

        JScrollPane scrollPane = new JScrollPane(progressArea);
        JOptionPane.showMessageDialog(this, scrollPane, "Student Progress Report", JOptionPane.INFORMATION_MESSAGE);
    }//GEN-LAST:event_viewProgressActionPerformed

    private void viewTranscriptActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_viewTranscriptActionPerformed
        int selectedRow = studentTable.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Please select a student!", "No Selection", JOptionPane.WARNING_MESSAGE);
            return;
        }

        String studentId = (String) studentTableModel.getValueAt(selectedRow, 0);
        String studentName = (String) studentTableModel.getValueAt(selectedRow, 1);

        JTextArea transcriptArea = new JTextArea(15, 50);
        transcriptArea.setText("Transcript Summary for " + studentName + " (" + studentId + ")\n");
        transcriptArea.append("=" .repeat(60) + "\n\n");
        transcriptArea.append("Fall 2025 Semester:\n");
        transcriptArea.append("-" .repeat(30) + "\n");
        transcriptArea.append("INFO 5100 - Application Engineering: A-\n");
        transcriptArea.append("INFO 5200 - Data Structures: B+\n");
        transcriptArea.append("INFO 6150 - Web Design: A\n");
        transcriptArea.append("\nSemester GPA: 3.67\n");
        transcriptArea.append("Cumulative GPA: 3.50\n");
        transcriptArea.append("Total Credits: 12\n");
        transcriptArea.setEditable(false);

        JScrollPane scrollPane = new JScrollPane(transcriptArea);
        JOptionPane.showMessageDialog(this, scrollPane, "Transcript Summary", JOptionPane.INFORMATION_MESSAGE);
    }//GEN-LAST:event_viewTranscriptActionPerformed

    private void gradeAssignmentActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_gradeAssignmentActionPerformed
        int selectedRow = studentTable.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Please select a student!", "No Selection", JOptionPane.WARNING_MESSAGE);
            return;
        }

        String studentName = (String) studentTableModel.getValueAt(selectedRow, 1);

        CardSequencePanel.removeAll();
        GradingJPanel gradingPanel = new GradingJPanel(department, facultyProfile, CardSequencePanel,
                                                       (String) courseComboBox.getSelectedItem(), studentName);
        CardSequencePanel.add("Grading", gradingPanel);
        ((CardLayout) CardSequencePanel.getLayout()).next(CardSequencePanel);
    }//GEN-LAST:event_gradeAssignmentActionPerformed

    private void computeFinalGradeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_computeFinalGradeActionPerformed
        int selectedRow = studentTable.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Please select a student!", "No Selection", JOptionPane.WARNING_MESSAGE);
            return;
        }

        String studentName = (String) studentTableModel.getValueAt(selectedRow, 1);

        JPanel gradePanel = new JPanel(new GridLayout(6, 2, 10, 10));
        gradePanel.add(new JLabel("Student:"));
        gradePanel.add(new JLabel(studentName));
        gradePanel.add(new JLabel("Assignments (30%):"));
        gradePanel.add(new JLabel("88.5"));
        gradePanel.add(new JLabel("Midterm (30%):"));
        gradePanel.add(new JLabel("85.0"));
        gradePanel.add(new JLabel("Final (30%):"));
        gradePanel.add(new JLabel("90.0"));
        gradePanel.add(new JLabel("Participation (10%):"));
        gradePanel.add(new JLabel("95.0"));
        gradePanel.add(new JLabel("Final Grade:"));
        gradePanel.add(new JLabel("88.55 (B+)"));

        JOptionPane.showMessageDialog(this, gradePanel, "Final Grade Calculation", JOptionPane.INFORMATION_MESSAGE);
    }//GEN-LAST:event_computeFinalGradeActionPerformed

    private void rankStudentsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rankStudentsActionPerformed
        loadStudents();
        JOptionPane.showMessageDialog(this, "Students have been ranked by total grade percentage!",
                                    "Ranking Complete", JOptionPane.INFORMATION_MESSAGE);
    }//GEN-LAST:event_rankStudentsActionPerformed

    private void backActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backActionPerformed
        CardSequencePanel.removeAll();
        FacultyWorkAreaJPanel facultyPanel = new FacultyWorkAreaJPanel(department, facultyProfile, CardSequencePanel);
        CardSequencePanel.add("FacultyWorkArea", facultyPanel);
        ((CardLayout) CardSequencePanel.getLayout()).next(CardSequencePanel);
    }//GEN-LAST:event_backActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton backBtn;
    private javax.swing.JPanel buttonPanel;
    private javax.swing.JButton computeFinalGradeBtn;
    private javax.swing.JComboBox courseComboBox;
    private javax.swing.JPanel coursePanel;
    private javax.swing.JLabel gpaLabel;
    private javax.swing.JButton gradeAssignmentBtn;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel mainPanel;
    private javax.swing.JButton rankStudentsBtn;
    private javax.swing.JScrollPane scrollPane;
    private javax.swing.JTable studentTable;
    private javax.swing.JLabel titleLabel;
    private javax.swing.JPanel titlePanel;
    private javax.swing.JButton viewProgressBtn;
    private javax.swing.JButton viewTranscriptBtn;
    // End of variables declaration//GEN-END:variables

    private class StudentInfo {
        String studentId;
        String name;
        String email;
        double gradePoint;
        String letterGrade;
        double percentage;
        int rank;
    }
}