

import info5100.university.example.Department.Department;
import info5100.university.example.CourseCatalog.Course;
import info5100.university.example.CourseSchedule.CourseSchedule;
import info5100.university.example.CourseSchedule.CourseOffer;
import info5100.university.example.CourseSchedule.CourseLoad;
import info5100.university.example.CourseSchedule.SeatAssignment;
import info5100.university.example.Persona.*;
import info5100.university.example.Persona.Faculty.FacultyProfile;
import info5100.university.example.Persona.Faculty.FacultyDirectory;

import info5100.university.example.Persona.RegistrarProfile;
import info5100.university.example.Persona.RegistrarDirectory;

public class ConfigureAUniversity {
    
    public static Department setupTestData() {

        Department department = new Department("Information Systems");
        

        System.out.println("Creating courses...");
        

        Course info5100 = department.newCourse("Application Engineering", "INFO 5100", 4);
        Course info5200 = department.newCourse("Data Structures", "INFO 5200", 4);
        Course info6150 = department.newCourse("Web Design", "INFO 6150", 4);
        Course info6250 = department.newCourse("Web Tools", "INFO 6250", 4);
        Course info7390 = department.newCourse("Advanced Data Science", "INFO 7390", 4);
        

        department.addCoreCourse(info5100);  
        department.addElectiveCourse(info5200);
        department.addElectiveCourse(info6150);
        department.addElectiveCourse(info6250);
        department.addElectiveCourse(info7390);
        
        System.out.println("Creating course schedule for Fall2025...");
        
        CourseSchedule fall2025 = department.newCourseSchedule("Fall2025");
        
        CourseOffer co5100 = fall2025.newCourseOffer("INFO 5100");
        CourseOffer co5200 = fall2025.newCourseOffer("INFO 5200");
        CourseOffer co6150 = fall2025.newCourseOffer("INFO 6150");
        CourseOffer co6250 = fall2025.newCourseOffer("INFO 6250");
        CourseOffer co7390 = fall2025.newCourseOffer("INFO 7390");
        
        
        if(co5100 != null) {
            co5100.generatSeats(30);
            co5100.setRoom("Room 101");
            co5100.setSchedule("Mon/Wed 10:00-11:30");
        }
        if(co5200 != null) {
            co5200.generatSeats(30);
            co5200.setRoom("Room 102");
            co5200.setSchedule("Tue/Thu 14:00-15:30");
        }
        if(co6150 != null) {
            co6150.generatSeats(30);
            co6150.setRoom("Room 201");
            co6150.setSchedule("Mon/Wed 14:00-15:30");
        }
        if(co6250 != null) {
            co6250.generatSeats(30);
            co6250.setRoom("Room 202");
            co6250.setSchedule("Tue/Thu 10:00-11:30");
        }
        if(co7390 != null) {
            co7390.generatSeats(30);
            co7390.setRoom("Room 301");
            co7390.setSchedule("Fri 9:00-12:00");
        }
        

        PersonDirectory personDirectory = department.getPersonDirectory();
        StudentDirectory studentDirectory = department.getStudentDirectory();
        FacultyDirectory facultyDirectory = department.getFacultyDirectory();
        UserAccountDirectory userAccountDirectory = department.getUserAccountDirectory();
        RegistrarDirectory registrarDirectory = department.getRegistrarDirectory();
        
        System.out.println("Creating admin...");
        
        Person adminPerson = personDirectory.newPerson("ADMIN001");
        if(adminPerson != null) {
            adminPerson.setEmail("admin@university.edu");
            adminPerson.setPhone("617-555-0001");
        }

        UserAccount adminAccount = userAccountDirectory.newUserAccount(
            adminPerson, "admin", "admin123", "Admin"
        );
        
        System.out.println("Creating 10 faculty members...");
        
        for(int i = 1; i <= 10; i++) {
            Person facultyPerson = personDirectory.newPerson("FAC00" + i);
            if(facultyPerson != null) {
                facultyPerson.setEmail("faculty" + i + "@university.edu");
                facultyPerson.setPhone("617-555-10" + String.format("%02d", i));
            }
            
            FacultyProfile facultyProfile = facultyDirectory.newFacultyProfile(facultyPerson);
            
            userAccountDirectory.newUserAccount(
                facultyPerson, "faculty" + i, "pass" + i, "Faculty"
            );
            
            if(i == 1 && co5100 != null) co5100.AssignAsTeacher(facultyProfile);
            if(i == 2 && co5200 != null) co5200.AssignAsTeacher(facultyProfile);
            if(i == 3 && co6150 != null) co6150.AssignAsTeacher(facultyProfile);
            if(i == 4 && co6250 != null) co6250.AssignAsTeacher(facultyProfile);
            if(i == 5 && co7390 != null) co7390.AssignAsTeacher(facultyProfile);
        }
        
        System.out.println("Creating 10 students with course registrations...");
        
        String[] studentNames = {
            "Alice Johnson", "Bob Smith", "Carol White", "David Brown", 
            "Emma Davis", "Frank Miller", "Grace Wilson", "Henry Moore",
            "Iris Taylor", "Jack Anderson"
        };
        
        String[] grades = {"A", "A-", "B+", "B", "B-", "A", "B+", "A-", "B", "B+"};
        
        for(int i = 1; i <= 10; i++) {
            Person studentPerson = personDirectory.newPerson("STU00" + i);
            if(studentPerson != null) {
                studentPerson.setName(studentNames[i-1]);
                studentPerson.setEmail("student" + i + "@university.edu");
                studentPerson.setPhone("617-555-20" + String.format("%02d", i));
            }
            

            StudentProfile studentProfile = studentDirectory.newStudentProfile(studentPerson);
            
            userAccountDirectory.newUserAccount(
                studentPerson, "student" + i, "pass" + i, "Student"
            );
            
            CourseLoad courseLoad = studentProfile.newCourseLoad("Fall2025");
            

            if(co5100 != null) {
                SeatAssignment sa1 = courseLoad.newSeatAssignment(co5100);
                if(sa1 != null) {
                    sa1.setGrade(grades[i-1]);  
                    
                    if(studentProfile.getTuitionAccount() != null) {
                        studentProfile.getTuitionAccount().charge(4);  
                    }
                }
            }
            

            if(i <= 5 && co5200 != null) {
                SeatAssignment sa2 = courseLoad.newSeatAssignment(co5200);
                if(sa2 != null) {
                    sa2.setGrade(grades[9-i]);  
                    
                    if(studentProfile.getTuitionAccount() != null) {
                        studentProfile.getTuitionAccount().charge(4);
                    }
                }
            } else if(i > 5 && co6150 != null) {
                SeatAssignment sa2 = courseLoad.newSeatAssignment(co6150);
                if(sa2 != null) {
                    sa2.setGrade(grades[i-1]);
                    
                    if(studentProfile.getTuitionAccount() != null) {
                        studentProfile.getTuitionAccount().charge(4);
                    }
                }
            }
            
 
            if(i <= 3 && studentProfile.getTuitionAccount() != null) {
                double tuition = studentProfile.getTuitionAccount().getBalance();
                studentProfile.getTuitionAccount().pay(tuition);
                System.out.println("Student " + i + " has paid tuition: $" + tuition);
            }
        }
        
        System.out.println("Creating registrar...");
        
        Person registrarPerson = personDirectory.newPerson("REG001");
        if(registrarPerson != null) {
            registrarPerson.setEmail("registrar@university.edu");
            registrarPerson.setPhone("617-555-0002");
        }
        
        // Create RegistrarProfile
        RegistrarProfile registrarProfile = registrarDirectory.newRegistrarProfile(registrarPerson);
        if(registrarProfile != null) {
            registrarProfile.setOfficeLocation("Admin Building, Room 105");
            registrarProfile.setOfficeHours("Mon-Fri 9:00 AM - 5:00 PM");
            registrarProfile.setDepartment("Registrar's Office");
        }
        
        userAccountDirectory.newUserAccount(
            registrarPerson, "registrar", "reg123", "Registrar"
        );

        // Create some past semesters for testing reports
        System.out.println("Creating previous semester data for testing...");
        
        // Spring 2025 semester
        CourseSchedule spring2025 = department.newCourseSchedule("Spring2025");
        CourseOffer sp5100 = spring2025.newCourseOffer("INFO 5100");
        CourseOffer sp5200 = spring2025.newCourseOffer("INFO 5200");
        
        if(sp5100 != null) {
            sp5100.generatSeats(25);
            sp5100.setRoom("Room 101");
            sp5100.setSchedule("Mon/Wed 10:00-11:30");
        }
        if(sp5200 != null) {
            sp5200.generatSeats(25);
            sp5200.setRoom("Room 102");
            sp5200.setSchedule("Tue/Thu 14:00-15:30");
        }
        
        // Register some students in Spring 2025 for historical data
        for(int i = 1; i <= 5; i++) {
            StudentProfile student = studentDirectory.findStudent("STU00" + i);
            if(student != null) {
                CourseLoad springLoad = student.newCourseLoad("Spring2025");
                if(sp5100 != null) {
                    SeatAssignment sa = springLoad.newSeatAssignment(sp5100);
                    if(sa != null) {
                        sa.setGrade("B+");
                    }
                }
            }
        }

        // Initialize Financial Account for the department
        System.out.println("Initializing financial tracking...");
        department.initializeFinancialAccount();
        

        System.out.println("\n========== Configuration Summary ==========");
        System.out.println("Department: " + department.getName());
        System.out.println("Total Persons: 30+");
        System.out.println("Total Students: 10");
        System.out.println("Total Faculty: 10");
        System.out.println("Admin: 1");
        System.out.println("Registrar: 1 (with profile)");
        System.out.println("Courses Offered: 5 (with rooms and schedules)");
        System.out.println("Semesters: Fall2025, Spring2025");
        System.out.println("Financial Tracking: Initialized");
        System.out.println("\n========== Login Credentials ==========");
        System.out.println("Admin: username='admin', password='admin123'");
        System.out.println("Student 1: username='student1', password='pass1'");
        System.out.println("Student 2: username='student2', password='pass2'");
        System.out.println("Faculty 1: username='faculty1', password='pass1'");
        System.out.println("Registrar: username='registrar', password='reg123'");
        System.out.println("=========================================\n");
        
        return department;
    }
    
    public static void main(String[] args) {
        System.out.println("Starting University Configuration...\n");
        Department dept = setupTestData();
        System.out.println("\nConfiguration completed successfully!");
        

        UserAccountDirectory uad = dept.getUserAccountDirectory();
        UserAccount testAccount = uad.authenticate("admin", "admin123");
        if(testAccount != null) {
            System.out.println("Login test successful: " + testAccount.getRole());
        } else {
            System.out.println("Login test failed!");
        }
    }
}