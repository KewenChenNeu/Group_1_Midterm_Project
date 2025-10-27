package UserInterface.WorkAreas.FacultyRole;

import info5100.university.example.Department.Department;
import info5100.university.example.Persona.Faculty.FacultyProfile;
import info5100.university.example.Persona.Faculty.FacultyAssignment;
import info5100.university.example.Persona.StudentProfile;
import info5100.university.example.Persona.Person;
import info5100.university.example.CourseSchedule.CourseOffer;
import info5100.university.example.CourseSchedule.CourseSchedule;
import info5100.university.example.CourseSchedule.SeatAssignment;
import info5100.university.example.CourseSchedule.CourseLoad;
import info5100.university.example.CourseSchedule.Seat;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

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
        
        if (department != null) {
            // Get all semesters from department - use default for now
            // In future, could iterate through all possible semesters
            String[] semesters = {"Fall2025", "Spring2025"};
            for (String semester : semesters) {
                CourseSchedule schedule = department.getCourseSchedule(semester);
                if (schedule != null) {
                    for (CourseOffer co : schedule.getAllCourseOffers()) {
                        if (co != null) {
                            FacultyProfile coFaculty = co.getFacultyProfile();
                            if (coFaculty != null && coFaculty == facultyProfile) {
                                String courseItem = co.getCourseNumber() + " - " + co.getCourseName();
                                courseComboBox.addItem(courseItem);
                            }
                        }
                    }
                }
            }
        }
    }

    private void courseComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_courseComboBoxActionPerformed
        if (courseComboBox.getSelectedIndex() > 0) {
            populateStudentTable();
        }
    }//GEN-LAST:event_courseComboBoxActionPerformed
    
    private void populateStudentTable() {
        studentTableModel = (DefaultTableModel) studentTable.getModel();
        studentTableModel.setRowCount(0);
        
        String selectedCourse = (String) courseComboBox.getSelectedItem();
        if (selectedCourse == null || selectedCourse.equals("-- Select Course --")) {
            return;
        }
        
        String courseId = selectedCourse.split(" - ")[0];
        List<StudentGradeInfo> studentGrades = new ArrayList<>();
        double totalGradePoints = 0;
        int studentCount = 0;
        
        if (department != null) {
            // Find the course offer
            CourseOffer targetCourse = null;
            // Get semesters - use default for now
            String[] semesters = {"Fall2025", "Spring2025"};
            for (String semester : semesters) {
                CourseSchedule schedule = department.getCourseSchedule(semester);
                if (schedule != null) {
                    for (CourseOffer co : schedule.getAllCourseOffers()) {
                        if (co != null && co.getCourseNumber().equals(courseId)) {
                            FacultyProfile coFaculty = co.getFacultyProfile();
                            if (coFaculty != null && coFaculty == facultyProfile) {
                                targetCourse = co;
                                break;
                            }
                        }
                    }
                }
                if (targetCourse != null) break;
            }
            
            // Get enrolled students
            if (targetCourse != null && targetCourse.getSeatList() != null) {
                for (Seat seat : targetCourse.getSeatList()) {
                    if (seat.isOccupied()) {
                        SeatAssignment sa = seat.getSeatassignment();
                        if (sa != null) {
                            // Find the student who owns this seat assignment
                            StudentProfile student = findStudentBySeatAssignment(sa);
                            if (student != null && student.getPerson() != null) {
                                Person person = student.getPerson();
                                String grade = sa.getGrade();
                                float gradePoint = sa.getGradePoint();
                                
                                // Calculate percentage based on grade
                                String percentage = calculatePercentage(gradePoint);
                                
                                StudentGradeInfo info = new StudentGradeInfo(
                                    person.getPersonId(),
                                    person.getName() != null ? person.getName() : "Student",
                                    person.getEmail() != null ? person.getEmail() : "",
                                    gradePoint + "",
                                    grade,
                                    percentage
                                );
                                studentGrades.add(info);
                                
                                totalGradePoints += gradePoint;
                                studentCount++;
                            }
                        }
                    }
                }
            }
        }
        
        // Sort by grade points for ranking
        studentGrades.sort((a, b) -> Float.compare(
            Float.parseFloat(b.gradePoints), 
            Float.parseFloat(a.gradePoints)
        ));
        
        // Add to table with rankings
        int rank = 1;
        for (StudentGradeInfo info : studentGrades) {
            Object[] row = {
                info.studentId,
                info.studentName,
                info.email,
                info.gradePoints,
                info.letterGrade,
                info.percentage,
                rank++
            };
            studentTableModel.addRow(row);
        }
        
        // Calculate and display class GPA
        if (studentCount > 0) {
            double classGPA = totalGradePoints / studentCount;
            gpaLabel.setText("    Class GPA: " + String.format("%.2f", classGPA));
        } else {
            gpaLabel.setText("    Class GPA: N/A");
        }
    }
    
    private String calculatePercentage(float gradePoint) {
        // Convert grade point to percentage (4.0 = 100%)
        float percentage = (gradePoint / 4.0f) * 100;
        return String.format("%.1f%%", percentage);
    }
    
    private StudentProfile findStudentBySeatAssignment(SeatAssignment sa) {
        if (department != null && department.getStudentDirectory() != null) {
            for (StudentProfile student : department.getStudentDirectory().getStudentList()) {
                if (student != null && student.getTranscript() != null) {
                    for (CourseLoad cl : student.getTranscript().getCourseloadlist()) {
                        if (cl != null && cl.getSeatAssignments() != null) {
                            for (SeatAssignment studentSA : cl.getSeatAssignments()) {
                                if (studentSA == sa) {
                                    return student;
                                }
                            }
                        }
                    }
                }
            }
        }
        return null;
    }
    
    private StudentProfile findStudentById(String studentId) {
        if (department != null && department.getStudentDirectory() != null) {
            for (StudentProfile student : department.getStudentDirectory().getStudentList()) {
                if (student != null && student.getPerson() != null) {
                    Person person = student.getPerson();
                    if (person.getPersonId() != null && person.getPersonId().equals(studentId)) {
                        return student;
                    }
                }
            }
        }
        return null;
    }
    
    private class StudentGradeInfo {
        String studentId, studentName, email, gradePoints, letterGrade, percentage;
        
        StudentGradeInfo(String id, String name, String email, String points, String letter, String pct) {
            this.studentId = id;
            this.studentName = name;
            this.email = email;
            this.gradePoints = points;
            this.letterGrade = letter;
            this.percentage = pct;
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
        progressArea.append("- No assignment details available\n");
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
        // Get actual transcript data for the student
        StudentProfile student = findStudentById(studentId);
        if (student != null && student.getTranscript() != null) {
            transcriptArea.append("Transcript details:\n");
            transcriptArea.append("-" .repeat(30) + "\n");
            // Display available student info
            transcriptArea.append("Student ID: " + studentId + "\n");
            transcriptArea.append("Total Credits Earned: Available in transcript\n");
        } else {
            transcriptArea.append("No transcript data available\n");
        }
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
        
        String studentId = (String) studentTableModel.getValueAt(selectedRow, 0);
        String studentName = (String) studentTableModel.getValueAt(selectedRow, 1);
        String currentGrade = (String) studentTableModel.getValueAt(selectedRow, 4);
        
        // Create grade input panel with multiple assignments
        JPanel gradePanel = new JPanel(new GridLayout(7, 2, 10, 10));
        gradePanel.add(new JLabel("Student:"));
        gradePanel.add(new JLabel(studentName));
        
        gradePanel.add(new JLabel("Assignment 1 (0-100):"));
        JTextField assign1Field = new JTextField();
        gradePanel.add(assign1Field);
        
        gradePanel.add(new JLabel("Assignment 2 (0-100):"));
        JTextField assign2Field = new JTextField();
        gradePanel.add(assign2Field);
        
        gradePanel.add(new JLabel("Midterm Exam (0-100):"));
        JTextField midtermField = new JTextField();
        gradePanel.add(midtermField);
        
        gradePanel.add(new JLabel("Final Exam (0-100):"));
        JTextField finalField = new JTextField();
        gradePanel.add(finalField);
        
        gradePanel.add(new JLabel("Participation (0-100):"));
        JTextField participationField = new JTextField();
        gradePanel.add(participationField);
        
        gradePanel.add(new JLabel("Current Grade:"));
        gradePanel.add(new JLabel(currentGrade != null ? currentGrade : "Not Graded"));
        
        int result = JOptionPane.showConfirmDialog(this, gradePanel, "Grade Assignments",
                                                   JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
        
        if (result == JOptionPane.OK_OPTION) {
            try {
                // Calculate weighted average with input validation
                double assign1 = Double.parseDouble(assign1Field.getText());
                double assign2 = Double.parseDouble(assign2Field.getText());
                double midterm = Double.parseDouble(midtermField.getText());
                double finalExam = Double.parseDouble(finalField.getText());
                double participation = Double.parseDouble(participationField.getText());
                
                // Validate that all scores are between 0 and 100
                if (assign1 < 0 || assign1 > 100 || assign2 < 0 || assign2 > 100 ||
                    midterm < 0 || midterm > 100 || finalExam < 0 || finalExam > 100 ||
                    participation < 0 || participation > 100) {
                    JOptionPane.showMessageDialog(this, 
                        "All scores must be between 0 and 100!", 
                        "Invalid Input", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                
                // Weights: Assignments 30%, Midterm 25%, Final 35%, Participation 10%
                double totalScore = (assign1 * 0.15 + assign2 * 0.15 + midterm * 0.25 + 
                                   finalExam * 0.35 + participation * 0.10);
                
                // Convert to letter grade
                String letterGrade = calculateLetterGrade(totalScore);
                
                // Update the grade in the seat assignment
                updateStudentGrade(studentId, letterGrade);
                
                JOptionPane.showMessageDialog(this, 
                    "Grade calculated: " + String.format("%.1f", totalScore) + " (" + letterGrade + ")\nGrade updated successfully!", 
                    "Success", JOptionPane.INFORMATION_MESSAGE);
                    
                populateStudentTable();
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "Please enter valid numbers for all scores!", 
                                            "Invalid Input", JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_gradeAssignmentActionPerformed
    
    private String calculateLetterGrade(double score) {
        if (score >= 93) return "A";
        else if (score >= 90) return "A-";
        else if (score >= 87) return "B+";
        else if (score >= 83) return "B";
        else if (score >= 80) return "B-";
        else if (score >= 77) return "C+";
        else if (score >= 73) return "C";
        else if (score >= 70) return "C-";
        else if (score >= 60) return "D";
        else return "F";
    }
    
    private void updateStudentGrade(String studentId, String newGrade) {
        String selectedCourse = (String) courseComboBox.getSelectedItem();
        if (selectedCourse != null && !selectedCourse.equals("-- Select Course --")) {
            String courseId = selectedCourse.split(" - ")[0];
            
            if (department != null) {
                String[] semesters = {"Fall2025", "Spring2025"};
                for (String semester : semesters) {
                    CourseSchedule schedule = department.getCourseSchedule(semester);
                    if (schedule != null) {
                        for (CourseOffer co : schedule.getAllCourseOffers()) {
                            if (co != null && co.getCourseNumber().equals(courseId) && 
                                co.getFacultyProfile() == facultyProfile) {
                                if (co.getSeatList() != null) {
                                    // For MVP, match by seat number in student ID
                                    for (Seat seat : co.getSeatList()) {
                                        if (seat.isOccupied() && studentId.endsWith(String.valueOf(seat.getNumber()))) {
                                            SeatAssignment sa = seat.getSeatassignment();
                                            if (sa != null) {
                                                sa.setGrade(newGrade);
                                                return;
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }//GEN-LAST:event_gradeAssignmentActionPerformed

    private void computeFinalGradeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_computeFinalGradeActionPerformed
        int selectedRow = studentTable.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Please select a student!", "No Selection", JOptionPane.WARNING_MESSAGE);
            return;
        }

        String studentId = (String) studentTableModel.getValueAt(selectedRow, 0);
        String studentName = (String) studentTableModel.getValueAt(selectedRow, 1);

        // Input panel for scores
        JPanel inputPanel = new JPanel(new GridLayout(6, 2, 10, 10));
        inputPanel.add(new JLabel("Student:"));
        inputPanel.add(new JLabel(studentName));
        
        inputPanel.add(new JLabel("Assignments Average (0-100):"));
        JTextField assignField = new JTextField();
        inputPanel.add(assignField);
        
        inputPanel.add(new JLabel("Midterm Score (0-100):"));
        JTextField midtermField = new JTextField();
        inputPanel.add(midtermField);
        
        inputPanel.add(new JLabel("Final Exam Score (0-100):"));
        JTextField finalField = new JTextField();
        inputPanel.add(finalField);
        
        inputPanel.add(new JLabel("Participation (0-100):"));
        JTextField participationField = new JTextField();
        inputPanel.add(participationField);
        
        inputPanel.add(new JLabel("Weights:"));
        inputPanel.add(new JLabel("Assign:30%, Mid:25%, Final:35%, Part:10%"));
        
        int result = JOptionPane.showConfirmDialog(this, inputPanel, "Compute Final Grade",
                                                   JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
        
        if (result == JOptionPane.OK_OPTION) {
            try {
                double assignments = Double.parseDouble(assignField.getText());
                double midterm = Double.parseDouble(midtermField.getText());
                double finalExam = Double.parseDouble(finalField.getText());
                double participation = Double.parseDouble(participationField.getText());
                
                // Validate that all scores are between 0 and 100
                if (assignments < 0 || assignments > 100 || midterm < 0 || midterm > 100 ||
                    finalExam < 0 || finalExam > 100 || participation < 0 || participation > 100) {
                    JOptionPane.showMessageDialog(this, 
                        "All scores must be between 0 and 100!", 
                        "Invalid Input", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                
                // Calculate weighted average
                double finalScore = (assignments * 0.30 + midterm * 0.25 + 
                                   finalExam * 0.35 + participation * 0.10);
                
                String letterGrade = calculateLetterGrade(finalScore);
                
                // Update the grade
                updateStudentGrade(studentId, letterGrade);
                
                // Show result
                JPanel resultPanel = new JPanel(new GridLayout(7, 2, 10, 10));
                resultPanel.add(new JLabel("Student:"));
                resultPanel.add(new JLabel(studentName));
                resultPanel.add(new JLabel("Assignments (30%):"));
                resultPanel.add(new JLabel(String.format("%.1f", assignments)));
                resultPanel.add(new JLabel("Midterm (25%):"));
                resultPanel.add(new JLabel(String.format("%.1f", midterm)));
                resultPanel.add(new JLabel("Final Exam (35%):"));
                resultPanel.add(new JLabel(String.format("%.1f", finalExam)));
                resultPanel.add(new JLabel("Participation (10%):"));
                resultPanel.add(new JLabel(String.format("%.1f", participation)));
                resultPanel.add(new JLabel("Final Score:"));
                resultPanel.add(new JLabel(String.format("%.2f", finalScore)));
                resultPanel.add(new JLabel("Final Grade:"));
                resultPanel.add(new JLabel(letterGrade));
                
                JOptionPane.showMessageDialog(this, resultPanel, "Final Grade Computed", JOptionPane.INFORMATION_MESSAGE);
                populateStudentTable();
                
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "Please enter valid numbers for all scores!", 
                                            "Invalid Input", JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_computeFinalGradeActionPerformed

    private void rankStudentsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rankStudentsActionPerformed
        if (studentTableModel.getRowCount() == 0) {
            JOptionPane.showMessageDialog(this, "No students to rank!", "No Data", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        // Re-populate the table which automatically ranks students
        populateStudentTable();
        JOptionPane.showMessageDialog(this, "Students have been ranked by grade percentage!", 
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

    
}