/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package UserInterface.WorkAreas.RegistrarRole.StudentRegistrationManagement;

import info5100.university.example.Department.Department;
import info5100.university.example.CourseSchedule.CourseOffer;
import info5100.university.example.CourseSchedule.CourseSchedule;
import info5100.university.example.CourseSchedule.CourseLoad;
import info5100.university.example.CourseSchedule.SeatAssignment;
import info5100.university.example.CourseSchedule.Seat;
import info5100.university.example.CourseCatalog.Course;
import info5100.university.example.CourseCatalog.CourseCatalog;
import info5100.university.example.Persona.StudentProfile;
import info5100.university.example.Persona.StudentDirectory;
import info5100.university.example.Persona.Person;
import info5100.university.example.Persona.Faculty.FacultyProfile;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.lang.reflect.Field;
import java.util.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 *
 * @author chris
 */
public class ManageStudentRegistrationJPanel extends javax.swing.JPanel {
    
    private JPanel CardSequencePanel;
    private Department department;
    private DefaultTableModel studentTableModel;
    private DefaultTableModel courseTableModel;
    private DefaultTableModel enrolledTableModel;
    private StudentProfile selectedStudent;
    private String currentSemester = "Fall2025"; 
    
    public ManageStudentRegistrationJPanel(Department department, JPanel jpanel) {
        this.CardSequencePanel = jpanel;
        this.department = department;
        initComponents();
        
        // Initialize table models
        studentTableModel = (DefaultTableModel) tblStudents.getModel();
        courseTableModel = (DefaultTableModel) tblCourses.getModel();
        enrolledTableModel = (DefaultTableModel) tblEnrolled.getModel();
        
        // Try to determine current semester from available data
        determineCurrentSemester();
        
        // Load initial data
        loadStudentData();
        loadCourseData();
        
        // Add selection listener to student table
        tblStudents.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    studentTableSelectionChanged();
                }
            }
        });
        
        // Set text fields as non-editable
        txtStudentID.setEditable(false);
        txtStudentName.setEditable(false);
        txtDepartment.setEditable(false);
        txtCurrentCredits.setEditable(false);
    }
    
    // Determine the current semester from available data
    private void determineCurrentSemester() {
        if (department != null && department.getMastercoursecatalog() != null) {
            HashMap<String, CourseSchedule> catalog = department.getMastercoursecatalog();
            
            // Try common semester formats
            String[] possibleSemesters = {"Fall2025", "Fall 2025", currentSemester};
            
            for (String semester : possibleSemesters) {
                if (catalog.containsKey(semester)) {
                    CourseSchedule schedule = catalog.get(semester);
                    if (schedule != null && schedule.getSchedule() != null && !schedule.getSchedule().isEmpty()) {
                        currentSemester = semester;
                        System.out.println("Using semester: " + currentSemester);
                        return;
                    }
                }
            }
            
            // If not found, check all available semesters
            for (String semester : catalog.keySet()) {
                CourseSchedule schedule = catalog.get(semester);
                if (schedule != null && schedule.getSchedule() != null && !schedule.getSchedule().isEmpty()) {
                    currentSemester = semester;
                    System.out.println("Using first available semester: " + currentSemester);
                    return;
                }
            }
        }
    }
    
    // Load all students into the table
    private void loadStudentData() {
        studentTableModel.setRowCount(0);
        
        if (department != null && department.getStudentDirectory() != null) {
            StudentDirectory studentDirectory = department.getStudentDirectory();
            
            for (StudentProfile student : studentDirectory.getStudentList()) {
                if (student != null && student.getPerson() != null) {
                    Object[] row = new Object[4];
                    row[0] = student.getPerson().getName();
                    row[1] = student.getPersonId();
                    row[2] = department.getName();
                    row[3] = calculateCurrentCredits(student);
                    studentTableModel.addRow(row);
                }
            }
        }
    }
    
    // Load available courses for current semester
    private void loadCourseData() {
        courseTableModel.setRowCount(0);
        
        if (department == null) {
            System.out.println("Department is null");
            return;
        }
        
        // Direct approach - get the course schedule for the current semester
        CourseSchedule courseSchedule = department.getCourseSchedule(currentSemester);
        
        if (courseSchedule != null && courseSchedule.getSchedule() != null) {
            ArrayList<CourseOffer> offers = courseSchedule.getSchedule();
            System.out.println("Found " + offers.size() + " course offers in " + currentSemester);
            
            for (CourseOffer courseOffer : offers) {
                if (courseOffer != null && courseOffer.getCourse() != null) {
                    Course course = courseOffer.getCourse();
                    Object[] row = new Object[5];
                    
                    // Course ID
                    row[0] = course.getCOurseNumber();
                    
                    // Course Name  
                    row[1] = course.getName();
                    
                    // Faculty
                    FacultyProfile faculty = courseOffer.getFacultyProfile();
                    if (faculty != null && faculty.getPerson() != null) {
                        row[2] = faculty.getPerson().getName();
                    } else {
                        row[2] = "TBA";
                    }
                    
                    // Credits
                    row[3] = course.getCredits();
                    
                    // Available Seats
                    row[4] = courseOffer.getAvailableSeats();
                    
                    System.out.println("Adding course: " + row[0] + " - " + row[1] + " (Available: " + row[4] + ")");
                    courseTableModel.addRow(row);
                }
            }
        } else {
            System.out.println("No course schedule found for " + currentSemester);
        }
    }
    
    // Handle student selection from table
    private void studentTableSelectionChanged() {
        int selectedRow = tblStudents.getSelectedRow();
        
        if (selectedRow >= 0) {
            // Get student ID from selected row
            String studentId = (String) studentTableModel.getValueAt(selectedRow, 1);
            
            // Find the student profile
            StudentDirectory studentDirectory = department.getStudentDirectory();
            selectedStudent = studentDirectory.findStudent(studentId);
            
            if (selectedStudent != null) {
                // Update text fields with student information
                txtStudentID.setText(selectedStudent.getPersonId());
                txtStudentName.setText(selectedStudent.getPerson().getName());
                txtDepartment.setText(department.getName());
                txtCurrentCredits.setText(String.valueOf(calculateCurrentCredits(selectedStudent)));
                
                // Load enrolled courses for selected student
                loadEnrolledCourses();
            }
        } else {
            // Clear fields if no selection
            clearStudentFields();
            selectedStudent = null;
        }
    }
    
    // Load enrolled courses for selected student
    private void loadEnrolledCourses() {
        enrolledTableModel.setRowCount(0);
        
        if (selectedStudent != null) {
            try {
                // Use getCurrentCourseLoad instead of getCourseLoadBySemester
                CourseLoad courseLoad = selectedStudent.getCurrentCourseLoad();
                
                if (courseLoad != null && courseLoad.getSeatAssignments() != null) {
                    for (SeatAssignment seatAssignment : courseLoad.getSeatAssignments()) {
                        if (seatAssignment != null && seatAssignment.getSeat() != null) {
                            CourseOffer courseOffer = seatAssignment.getSeat().getCourseOffer();
                            if (courseOffer != null && courseOffer.getCourse() != null) {
                                Object[] row = new Object[4];
                                Course course = courseOffer.getCourse();
                                
                                row[0] = course.getCOurseNumber();
                                row[1] = course.getName();
                                row[2] = course.getCredits();
                                row[3] = "Enrolled"; // Status
                                
                                enrolledTableModel.addRow(row);
                            }
                        }
                    }
                }
            } catch (Exception e) {
                // Handle any exceptions silently
                System.err.println("Error loading enrolled courses: " + e.getMessage());
            }
        }
    }
    
    // Calculate current credits for a student
    private int calculateCurrentCredits(StudentProfile student) {
        int totalCredits = 0;
        
        try {
            // Try to get current course load first
            CourseLoad courseLoad = student.getCurrentCourseLoad();
            
            if (courseLoad != null && courseLoad.getSeatAssignments() != null) {
                for (SeatAssignment sa : courseLoad.getSeatAssignments()) {
                    if (sa != null) {
                        totalCredits += sa.getCreditHours();
                    }
                }
            }
        } catch (Exception e) {
            // If any error occurs, return 0
            totalCredits = 0;
        }
        
        return totalCredits;
    }
    
    // Clear student information fields
    private void clearStudentFields() {
        txtStudentID.setText("");
        txtStudentName.setText("");
        txtDepartment.setText("");
        txtCurrentCredits.setText("");
        enrolledTableModel.setRowCount(0);
    }
    
    // Search student by ID
    private void searchStudentById() {
        String searchId = txtSearchID.getText().trim();
        
        if (searchId.isEmpty()) {
            // If search is empty, show all students
            loadStudentData();
            return;
        }
        
        // Clear current table
        studentTableModel.setRowCount(0);
        
        // Search for student
        StudentDirectory studentDirectory = department.getStudentDirectory();
        StudentProfile student = studentDirectory.findStudent(searchId);
        
        if (student != null) {
            // Add found student to table
            Object[] row = new Object[4];
            row[0] = student.getPerson().getName();
            row[1] = student.getPersonId();
            row[2] = department.getName();
            row[3] = calculateCurrentCredits(student);
            studentTableModel.addRow(row);
        } else {
            JOptionPane.showMessageDialog(this, 
                "No student found with ID: " + searchId, 
                "Search Result", 
                JOptionPane.INFORMATION_MESSAGE);
        }
    }
    
    // Enroll student in selected course
    private void enrollStudent() {
        if (selectedStudent == null) {
            JOptionPane.showMessageDialog(this, 
                "Please select a student first!", 
                "No Student Selected", 
                JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        int selectedRow = tblCourses.getSelectedRow();
        if (selectedRow < 0) {
            JOptionPane.showMessageDialog(this, 
                "Please select a course to enroll!", 
                "No Course Selected", 
                JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        // Get course information
        String courseId = (String) courseTableModel.getValueAt(selectedRow, 0);
        int availableSeats = (int) courseTableModel.getValueAt(selectedRow, 4);
        
        // Check if seats are available
        if (availableSeats <= 0) {
            JOptionPane.showMessageDialog(this, 
                "No seats available for this course!", 
                "Enrollment Failed", 
                JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        try {
            // Get or create course load
            CourseLoad courseLoad = selectedStudent.getCurrentCourseLoad();
            if (courseLoad == null) {
                courseLoad = selectedStudent.newCourseLoad(currentSemester);
            }
            
            // Check if already enrolled in this course
            for (SeatAssignment sa : courseLoad.getSeatAssignments()) {
                if (sa.getSeat().getCourseOffer().getCourse().getCOurseNumber().equals(courseId)) {
                    JOptionPane.showMessageDialog(this, 
                        "Student is already enrolled in this course!", 
                        "Already Enrolled", 
                        JOptionPane.WARNING_MESSAGE);
                    return;
                }
            }
            
            // Find the course offer
            CourseSchedule courseSchedule = department.getCourseSchedule(currentSemester);
            CourseOffer courseOffer = courseSchedule.getCourseOfferByNumber(courseId);
            
            if (courseOffer != null) {
                // Assign seat to student
                SeatAssignment seatAssignment = courseOffer.assignEmptySeat(courseLoad);
                
                if (seatAssignment != null) {
                    // Update enrolled courses table
                    loadEnrolledCourses();
                    
                    // Update course table (decrease available seats)
                    courseTableModel.setValueAt(courseOffer.getAvailableSeats(), selectedRow, 4);
                    
                    // Update student's current credits
                    txtCurrentCredits.setText(String.valueOf(calculateCurrentCredits(selectedStudent)));
                    
                    // Update student table
                    updateStudentTableRow(selectedStudent);
                    
                    JOptionPane.showMessageDialog(this, 
                        "Student successfully enrolled in the course!", 
                        "Enrollment Successful", 
                        JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(this, 
                        "Failed to enroll student. Please try again.", 
                        "Enrollment Failed", 
                        JOptionPane.ERROR_MESSAGE);
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, 
                "Error enrolling student: " + e.getMessage(), 
                "Enrollment Error", 
                JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }
    
    // Drop selected course for student
    private void dropCourse() {
        if (selectedStudent == null) {
            JOptionPane.showMessageDialog(this, 
                "Please select a student first!", 
                "No Student Selected", 
                JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        int selectedRow = tblEnrolled.getSelectedRow();
        if (selectedRow < 0) {
            JOptionPane.showMessageDialog(this, 
                "Please select a course to drop!", 
                "No Course Selected", 
                JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        try {
            // Get course ID to drop
            String courseId = (String) enrolledTableModel.getValueAt(selectedRow, 0);
            
            // Get student's current course load
            CourseLoad courseLoad = selectedStudent.getCurrentCourseLoad();
            
            if (courseLoad != null) {
                // Find and remove the seat assignment
                SeatAssignment toRemove = null;
                for (SeatAssignment sa : courseLoad.getSeatAssignments()) {
                    if (sa.getSeat().getCourseOffer().getCourse().getCOurseNumber().equals(courseId)) {
                        toRemove = sa;
                        break;
                    }
                }
                
                if (toRemove != null) {
                    // Release the seat
                    Seat seat = toRemove.getSeat();
                    seat.releaseSeat();
                    courseLoad.getSeatAssignments().remove(toRemove);
                    
                    // Update enrolled courses table
                    loadEnrolledCourses();
                    
                    // Update course table (increase available seats)
                    updateCourseTableSeats(courseId);
                    
                    // Update student's current credits
                    txtCurrentCredits.setText(String.valueOf(calculateCurrentCredits(selectedStudent)));
                    
                    // Update student table
                    updateStudentTableRow(selectedStudent);
                    
                    JOptionPane.showMessageDialog(this, 
                        "Course successfully dropped!", 
                        "Course Dropped", 
                        JOptionPane.INFORMATION_MESSAGE);
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, 
                "Error dropping course: " + e.getMessage(), 
                "Drop Course Error", 
                JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }
    
    // Update course table to reflect seat changes
    private void updateCourseTableSeats(String courseId) {
        for (int i = 0; i < courseTableModel.getRowCount(); i++) {
            if (courseTableModel.getValueAt(i, 0).equals(courseId)) {
                CourseSchedule courseSchedule = department.getCourseSchedule(currentSemester);
                CourseOffer courseOffer = courseSchedule.getCourseOfferByNumber(courseId);
                if (courseOffer != null) {
                    courseTableModel.setValueAt(courseOffer.getAvailableSeats(), i, 4);
                }
                break;
            }
        }
    }
    
    // Update student table row with current information
    private void updateStudentTableRow(StudentProfile student) {
        for (int i = 0; i < studentTableModel.getRowCount(); i++) {
            if (studentTableModel.getValueAt(i, 1).equals(student.getPersonId())) {
                studentTableModel.setValueAt(calculateCurrentCredits(student), i, 3);
                break;
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
        lblSearchID = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblStudents = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblCourses = new javax.swing.JTable();
        lblEnrolledCourse = new javax.swing.JLabel();
        btnEnrollStudent = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblEnrolled = new javax.swing.JTable();
        lblCourseTable = new javax.swing.JLabel();
        btnDropCourse = new javax.swing.JButton();
        txtSearchID = new javax.swing.JTextField();
        btnSearchByID = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtStudentID = new javax.swing.JTextField();
        txtDepartment = new javax.swing.JTextField();
        txtStudentName = new javax.swing.JTextField();
        txtCurrentCredits = new javax.swing.JTextField();
        Back3 = new javax.swing.JButton();

        lblTitle.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        lblTitle.setText("Student Registration Management");

        lblSearchID.setText("Student ID Search");

        tblStudents.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Student Name", "Student ID", "Department", "Current Credits"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tblStudents);

        tblCourses.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Course ID", "Course Name", "Faculty", "Credits", "Available Seats"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane2.setViewportView(tblCourses);

        lblEnrolledCourse.setText("Enrolled Course Table");

        btnEnrollStudent.setText("Enroll Student");
        btnEnrollStudent.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEnrollStudentActionPerformed(evt);
            }
        });

        tblEnrolled.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Course ID", "Course Name", "Credits", "Status"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane3.setViewportView(tblEnrolled);

        lblCourseTable.setText("Course Table");

        btnDropCourse.setText("Drop Course");
        btnDropCourse.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDropCourseActionPerformed(evt);
            }
        });

        txtSearchID.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSearchIDActionPerformed(evt);
            }
        });

        btnSearchByID.setText("Search ID");
        btnSearchByID.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSearchByIDActionPerformed(evt);
            }
        });

        jLabel1.setText("Student ID");

        jLabel2.setText("Student Name");

        jLabel3.setText("Department");

        jLabel4.setText("Current Credits");

        txtStudentName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtStudentNameActionPerformed(evt);
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
                .addGap(47, 47, 47)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 494, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblCourseTable)
                            .addComponent(btnEnrollStudent))
                        .addGap(28, 28, 28)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblEnrolledCourse)
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 369, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnDropCourse)))
                    .addComponent(lblTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 495, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 671, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblSearchID)
                        .addGap(18, 18, 18)
                        .addComponent(txtSearchID, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnSearchByID, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(txtDepartment, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(txtStudentID, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addComponent(jLabel2))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtStudentName, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtCurrentCredits, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(Back3, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(419, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addComponent(lblTitle)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblSearchID)
                    .addComponent(txtSearchID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSearchByID))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel1)
                    .addComponent(txtStudentID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtStudentName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4)
                    .addComponent(txtDepartment, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtCurrentCredits, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblCourseTable)
                    .addComponent(lblEnrolledCourse))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 278, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 278, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnDropCourse)
                    .addComponent(btnEnrollStudent))
                .addGap(18, 18, 18)
                .addComponent(Back3)
                .addContainerGap(79, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnDropCourseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDropCourseActionPerformed
        // TODO add your handling code here:
        dropCourse();
    }//GEN-LAST:event_btnDropCourseActionPerformed

    private void txtSearchIDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSearchIDActionPerformed
        // TODO add your handling code here:
        searchStudentById();
    }//GEN-LAST:event_txtSearchIDActionPerformed

    private void txtStudentNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtStudentNameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtStudentNameActionPerformed

    private void btnSearchByIDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSearchByIDActionPerformed
        // TODO add your handling code here:
        searchStudentById();
    }//GEN-LAST:event_btnSearchByIDActionPerformed

    private void btnEnrollStudentActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEnrollStudentActionPerformed
        // TODO add your handling code here:
        enrollStudent();
    }//GEN-LAST:event_btnEnrollStudentActionPerformed

    private void Back3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Back3ActionPerformed
        // TODO add your handling code here:
        CardSequencePanel.remove(this);
        CardLayout cardLayout = (CardLayout) CardSequencePanel.getLayout();
        cardLayout.previous(CardSequencePanel);
    }//GEN-LAST:event_Back3ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Back3;
    private javax.swing.JButton btnDropCourse;
    private javax.swing.JButton btnEnrollStudent;
    private javax.swing.JButton btnSearchByID;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JLabel lblCourseTable;
    private javax.swing.JLabel lblEnrolledCourse;
    private javax.swing.JLabel lblSearchID;
    private javax.swing.JLabel lblTitle;
    private javax.swing.JTable tblCourses;
    private javax.swing.JTable tblEnrolled;
    private javax.swing.JTable tblStudents;
    private javax.swing.JTextField txtCurrentCredits;
    private javax.swing.JTextField txtDepartment;
    private javax.swing.JTextField txtSearchID;
    private javax.swing.JTextField txtStudentID;
    private javax.swing.JTextField txtStudentName;
    // End of variables declaration//GEN-END:variables
}
