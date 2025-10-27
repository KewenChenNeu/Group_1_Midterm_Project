/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package UserInterface.WorkAreas.RegistrarRole.ReportsAnalytics;

import info5100.university.example.Department.Department;
import info5100.university.example.CourseSchedule.CourseSchedule;
import info5100.university.example.CourseSchedule.CourseOffer;
import info5100.university.example.CourseSchedule.CourseLoad;
import info5100.university.example.CourseSchedule.SeatAssignment;
import info5100.university.example.CourseSchedule.Seat;
import info5100.university.example.CourseCatalog.Course;
import info5100.university.example.Persona.StudentProfile;
import info5100.university.example.Persona.StudentDirectory;
import info5100.university.example.Persona.Faculty.FacultyProfile;
import java.awt.CardLayout;
import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;
import java.util.*;
import java.text.DecimalFormat;

/**
 *
 * @author chris
 */
public class InstitutionalReportsJPanel extends javax.swing.JPanel {
    
    JPanel CardSequencePanel;
    Department department;
    private DefaultTableModel departmentTableModel;
    private DefaultTableModel courseTableModel;
    private DefaultTableModel gpaTableModel;
    private String currentSemester = "Fall2025"; // Default semester

    /**
     * Creates new form InstitutionalReportsJPanel
     */
    public InstitutionalReportsJPanel(Department department, JPanel jpanel) {
        this.CardSequencePanel = jpanel;
        this.department = department;
        initComponents();
        
        // Initialize table models
        departmentTableModel = (DefaultTableModel) tblDepartment.getModel();
        courseTableModel = (DefaultTableModel) tblCourses.getModel();
        gpaTableModel = (DefaultTableModel) tblGPA.getModel();
        
        // Load all report data
        loadDepartmentEnrollmentReport();
        loadCourseEnrollmentReport();
        loadGPADistributionReport();
    }
    
    // Load Enrollment By Department Report
    private void loadDepartmentEnrollmentReport() {
        departmentTableModel.setRowCount(0);
        
        if (department == null) return;
        
        // Map to track department statistics
        Map<String, Integer> departmentCourseCount = new HashMap<>();
        Map<String, Set<String>> departmentStudents = new HashMap<>();
        
        // Get current semester course schedule
        CourseSchedule courseSchedule = department.getCourseSchedule(currentSemester);
        
        if (courseSchedule != null && courseSchedule.getSchedule() != null) {
            // Count courses per department
            for (CourseOffer courseOffer : courseSchedule.getSchedule()) {
                String deptName = department.getName();
                departmentCourseCount.put(deptName, 
                    departmentCourseCount.getOrDefault(deptName, 0) + 1);
                
                // Count enrolled students per department
                if (!departmentStudents.containsKey(deptName)) {
                    departmentStudents.put(deptName, new HashSet<>());
                }
                
                // Add students enrolled in this course
                for (Seat seat : courseOffer.getSeatList()) {
                    if (seat.isOccupied() && seat.getSeatassignment() != null) {
                        // Try to identify the student from the seat assignment
                        CourseLoad cl = seat.getSeatassignment().getCourseLoad();
                        if (cl != null) {
                            // Find student by matching course load
                            StudentDirectory sd = department.getStudentDirectory();
                            for (StudentProfile student : sd.getStudentList()) {
                                if (student.getCurrentCourseLoad() == cl) {
                                    departmentStudents.get(deptName).add(student.getPersonId());
                                    break;
                                }
                            }
                        }
                    }
                }
            }
        }
        
        // Also count students by their department affiliation
        StudentDirectory studentDirectory = department.getStudentDirectory();
        if (studentDirectory != null) {
            for (StudentProfile student : studentDirectory.getStudentList()) {
                String studentDept = student.getDepartment();
                if (studentDept == null || studentDept.isEmpty()) {
                    studentDept = department.getName(); // Default to main department
                }
                
                if (!departmentStudents.containsKey(studentDept)) {
                    departmentStudents.put(studentDept, new HashSet<>());
                }
                departmentStudents.get(studentDept).add(student.getPersonId());
                
                // Ensure department is in course count map
                if (!departmentCourseCount.containsKey(studentDept)) {
                    departmentCourseCount.put(studentDept, 0);
                }
            }
        }
        
        // Add data to table
        for (String deptName : departmentCourseCount.keySet()) {
            Object[] row = new Object[3];
            row[0] = deptName;
            row[1] = departmentCourseCount.get(deptName);
            row[2] = departmentStudents.containsKey(deptName) ? 
                     departmentStudents.get(deptName).size() : 0;
            departmentTableModel.addRow(row);
        }
        
        // If no data found, add default department
        if (departmentTableModel.getRowCount() == 0) {
            Object[] row = new Object[3];
            row[0] = department.getName();
            row[1] = department.getCourseCatalog().getCourseList().size();
            row[2] = department.getStudentDirectory().getStudentList().size();
            departmentTableModel.addRow(row);
        }
    }
    
    // Load Enrollment By Course Report
    private void loadCourseEnrollmentReport() {
        courseTableModel.setRowCount(0);
        
        if (department == null) return;
        
        // Get all semesters and their courses
        HashMap<String, CourseSchedule> allSchedules = department.getMastercoursecatalog();
        
        // Focus on current semester first
        CourseSchedule currentSchedule = allSchedules.get(currentSemester);
        
        if (currentSchedule != null && currentSchedule.getSchedule() != null) {
            for (CourseOffer courseOffer : currentSchedule.getSchedule()) {
                if (courseOffer != null && courseOffer.getCourse() != null) {
                    Object[] row = new Object[5];
                    Course course = courseOffer.getCourse();
                    
                    row[0] = course.getCOurseNumber();
                    row[1] = course.getName();
                    row[2] = department.getName();
                    
                    // Get faculty name
                    FacultyProfile faculty = courseOffer.getFacultyProfile();
                    if (faculty != null && faculty.getPerson() != null) {
                        row[3] = faculty.getPerson().getName();
                    } else {
                        row[3] = "TBA";
                    }
                    
                    // Count enrolled students
                    int enrolledCount = 0;
                    for (Seat seat : courseOffer.getSeatList()) {
                        if (seat.isOccupied()) {
                            enrolledCount++;
                        }
                    }
                    row[4] = enrolledCount;
                    
                    courseTableModel.addRow(row);
                }
            }
        }
        
        // If current semester has no courses, try other semesters
        if (courseTableModel.getRowCount() == 0) {
            for (Map.Entry<String, CourseSchedule> entry : allSchedules.entrySet()) {
                CourseSchedule schedule = entry.getValue();
                if (schedule != null && schedule.getSchedule() != null) {
                    for (CourseOffer courseOffer : schedule.getSchedule()) {
                        if (courseOffer != null && courseOffer.getCourse() != null) {
                            Object[] row = new Object[5];
                            Course course = courseOffer.getCourse();
                            
                            row[0] = course.getCOurseNumber();
                            row[1] = course.getName();
                            row[2] = department.getName();
                            
                            FacultyProfile faculty = courseOffer.getFacultyProfile();
                            row[3] = (faculty != null && faculty.getPerson() != null) ? 
                                     faculty.getPerson().getName() : "TBA";
                            
                            int enrolledCount = 0;
                            for (Seat seat : courseOffer.getSeatList()) {
                                if (seat.isOccupied()) {
                                    enrolledCount++;
                                }
                            }
                            row[4] = enrolledCount;
                            
                            courseTableModel.addRow(row);
                        }
                    }
                    if (courseTableModel.getRowCount() > 0) {
                        break; // Found data, stop searching
                    }
                }
            }
        }
    }
    
    // Load GPA Distribution By Program Report
    private void loadGPADistributionReport() {
        gpaTableModel.setRowCount(0);
        
        if (department == null) return;
        
        DecimalFormat df = new DecimalFormat("#.##");
        
        // Map to track GPA statistics by department/program
        Map<String, List<Double>> programGPAs = new HashMap<>();
        
        StudentDirectory studentDirectory = department.getStudentDirectory();
        if (studentDirectory != null) {
            for (StudentProfile student : studentDirectory.getStudentList()) {
                String program = student.getDepartment();
                if (program == null || program.isEmpty()) {
                    program = department.getName(); // Default to main department
                }
                
                // Calculate student's GPA
                double studentGPA = calculateStudentGPA(student);
                
                if (!programGPAs.containsKey(program)) {
                    programGPAs.put(program, new ArrayList<>());
                }
                
                if (studentGPA > 0) { // Only add if student has grades
                    programGPAs.get(program).add(studentGPA);
                }
            }
        }
        
        // Calculate statistics for each program
        for (Map.Entry<String, List<Double>> entry : programGPAs.entrySet()) {
            String program = entry.getKey();
            List<Double> gpas = entry.getValue();
            
            if (gpas.isEmpty()) {
                // No grades yet for this program
                Object[] row = new Object[5];
                row[0] = program;
                row[1] = "N/A";
                row[2] = "N/A";
                row[3] = "N/A";
                row[4] = studentDirectory.searchByDepartment(program).size();
                gpaTableModel.addRow(row);
            } else {
                // Calculate statistics
                double sum = 0;
                double highest = 0;
                double lowest = 4.0;
                
                for (Double gpa : gpas) {
                    sum += gpa;
                    if (gpa > highest) highest = gpa;
                    if (gpa < lowest) lowest = gpa;
                }
                
                double average = sum / gpas.size();
                
                Object[] row = new Object[5];
                row[0] = program;
                row[1] = df.format(average);
                row[2] = df.format(highest);
                row[3] = df.format(lowest);
                row[4] = gpas.size(); // Students with grades
                gpaTableModel.addRow(row);
            }
        }
        
        // If no data found, add default row
        if (gpaTableModel.getRowCount() == 0) {
            Object[] row = new Object[5];
            row[0] = department.getName();
            row[1] = "N/A";
            row[2] = "N/A";
            row[3] = "N/A";
            row[4] = studentDirectory.getStudentList().size();
            gpaTableModel.addRow(row);
        }
    }
    
    // Helper method to calculate student GPA
    private double calculateStudentGPA(StudentProfile student) {
        if (student == null) return 0.0;
        
        double totalPoints = 0;
        int totalCredits = 0;
        
        // Get all course loads (all semesters)
        ArrayList<SeatAssignment> allCourses = student.getCourseList();
        
        for (SeatAssignment sa : allCourses) {
            if (sa != null) {
                float gradePoint = sa.getGradePoint();
                int credits = sa.getCreditHours();
                
                if (gradePoint > 0 && credits > 0) {
                    totalPoints += gradePoint * credits;
                    totalCredits += credits;
                }
            }
        }
        
        if (totalCredits == 0) return 0.0;
        return totalPoints / totalCredits;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane3 = new javax.swing.JScrollPane();
        jTable3 = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblDepartment = new javax.swing.JTable();
        lblErollDepartment = new javax.swing.JLabel();
        lblEnrollCourses = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblCourses = new javax.swing.JTable();
        lblGPA = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        tblGPA = new javax.swing.JTable();
        Back3 = new javax.swing.JButton();

        jTable3.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane3.setViewportView(jTable3);

        jLabel1.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        jLabel1.setText("Institutional Reports & Analytics");

        tblDepartment.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Department", "Total Courses", "Enrolled Students"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tblDepartment);

        lblErollDepartment.setText("Enrollment By Department:");

        lblEnrollCourses.setText("Enrollment By Course:");

        tblCourses.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Course ID", "Course Name", "Department", "Faculty", "Enrolled Student"
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

        lblGPA.setText("GPA Distributed By Program");

        tblGPA.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Program", "Average GPA", "Highest GPA", "Lowest GPA", "Student Count"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, true, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane4.setViewportView(tblGPA);

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
                    .addComponent(Back3, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 619, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 619, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblEnrollCourses)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 474, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblErollDepartment)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 619, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblGPA))
                .addContainerGap(636, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(41, 41, 41)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblErollDepartment)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(24, 24, 24)
                .addComponent(lblEnrollCourses)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblGPA)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 207, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(Back3)
                .addContainerGap(38, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void Back3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Back3ActionPerformed
        // TODO add your handling code here:
        CardSequencePanel.remove(this);
        CardLayout cardLayout = (CardLayout) CardSequencePanel.getLayout();
        cardLayout.previous(CardSequencePanel);
    }//GEN-LAST:event_Back3ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Back3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTable jTable3;
    private javax.swing.JLabel lblEnrollCourses;
    private javax.swing.JLabel lblErollDepartment;
    private javax.swing.JLabel lblGPA;
    private javax.swing.JTable tblCourses;
    private javax.swing.JTable tblDepartment;
    private javax.swing.JTable tblGPA;
    // End of variables declaration//GEN-END:variables
}
