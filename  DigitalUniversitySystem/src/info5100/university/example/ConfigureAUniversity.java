package info5100.university.example;

import info5100.university.example.Department.Department;
import info5100.university.example.CourseCatalog.Course;
import info5100.university.example.CourseSchedule.CourseSchedule;
import info5100.university.example.CourseSchedule.CourseOffer;
import info5100.university.example.CourseSchedule.CourseLoad;
import info5100.university.example.CourseSchedule.SeatAssignment;
import info5100.university.example.Persona.*;
import info5100.university.example.Persona.Faculty.FacultyProfile;
import info5100.university.example.Persona.Faculty.FacultyDirectory;

public class ConfigureAUniversity {
    
    public static Department setupTestData() {
        // 创建部门
        Department department = new Department("Information Systems");
        
        // ============ 1. 创建课程 ============
        System.out.println("Creating courses...");
        
        // 创建课程目录中的课程
        Course info5100 = department.newCourse("INFO 5100", "Application Engineering", 4);
        Course info5200 = department.newCourse("INFO 5200", "Data Structures", 4);
        Course info6150 = department.newCourse("INFO 6150", "Web Design", 4);
        Course info6250 = department.newCourse("INFO 6250", "Web Tools", 4);
        Course info7390 = department.newCourse("INFO 7390", "Advanced Data Science", 4);
        
        // 设置核心课程和选修课程
        department.addCoreCourse(info5100);  // INFO 5100是核心课程
        department.addElectiveCourse(info5200);
        department.addElectiveCourse(info6150);
        department.addElectiveCourse(info6250);
        department.addElectiveCourse(info7390);
        
        // ============ 2. 创建学期和课程安排 ============
        System.out.println("Creating course schedule for Fall2025...");
        
        CourseSchedule fall2025 = department.newCourseSchedule("Fall2025");
        
        // 创建5个课程提供
        CourseOffer co5100 = fall2025.newCourseOffer("INFO 5100");
        CourseOffer co5200 = fall2025.newCourseOffer("INFO 5200");
        CourseOffer co6150 = fall2025.newCourseOffer("INFO 6150");
        CourseOffer co6250 = fall2025.newCourseOffer("INFO 6250");
        CourseOffer co7390 = fall2025.newCourseOffer("INFO 7390");
        
        // 为每个课程生成座位
        if(co5100 != null) co5100.generatSeats(30);
        if(co5200 != null) co5200.generatSeats(30);
        if(co6150 != null) co6150.generatSeats(30);
        if(co6250 != null) co6250.generatSeats(30);
        if(co7390 != null) co7390.generatSeats(30);
        
        // ============ 3. 创建人员目录 ============
        PersonDirectory personDirectory = department.getPersonDirectory();
        StudentDirectory studentDirectory = department.getStudentDirectory();
        FacultyDirectory facultyDirectory = department.getFacultyDirectory();
        UserAccountDirectory userAccountDirectory = department.getUserAccountDirectory();
        
        // ============ 4. 创建管理员 ============
        System.out.println("Creating admin...");
        
        Person adminPerson = personDirectory.newPerson("ADMIN001");
        if(adminPerson != null) {
            adminPerson.setEmail("admin@university.edu");
            adminPerson.setPhone("617-555-0001");
        }
        
        // 创建管理员账户 - 用于登录测试
        UserAccount adminAccount = userAccountDirectory.newUserAccount(
            adminPerson, "admin", "admin123", "Admin"
        );
        
        // ============ 5. 创建10个教师 ============
        System.out.println("Creating 10 faculty members...");
        
        for(int i = 1; i <= 10; i++) {
            Person facultyPerson = personDirectory.newPerson("FAC00" + i);
            if(facultyPerson != null) {
                facultyPerson.setEmail("faculty" + i + "@university.edu");
                facultyPerson.setPhone("617-555-10" + String.format("%02d", i));
            }
            
            // 创建教师档案
            FacultyProfile facultyProfile = facultyDirectory.newFacultyProfile(facultyPerson);
            
            // 创建教师账户
            userAccountDirectory.newUserAccount(
                facultyPerson, "faculty" + i, "pass" + i, "Faculty"
            );
            
            // 分配前5个教师到课程
            if(i == 1 && co5100 != null) co5100.AssignAsTeacher(facultyProfile);
            if(i == 2 && co5200 != null) co5200.AssignAsTeacher(facultyProfile);
            if(i == 3 && co6150 != null) co6150.AssignAsTeacher(facultyProfile);
            if(i == 4 && co6250 != null) co6250.AssignAsTeacher(facultyProfile);
            if(i == 5 && co7390 != null) co7390.AssignAsTeacher(facultyProfile);
        }
        
        // ============ 6. 创建10个学生 ============
        System.out.println("Creating 10 students with course registrations...");
        
        String[] studentNames = {
            "Alice Johnson", "Bob Smith", "Carol White", "David Brown", 
            "Emma Davis", "Frank Miller", "Grace Wilson", "Henry Moore",
            "Iris Taylor", "Jack Anderson"
        };
        
        String[] grades = {"A", "A-", "B+", "B", "B-", "A", "B+", "A-", "B", "B+"};
        
        for(int i = 1; i <= 10; i++) {
            // 创建Person
            Person studentPerson = personDirectory.newPerson("STU00" + i);
            if(studentPerson != null) {
                studentPerson.setEmail("student" + i + "@university.edu");
                studentPerson.setPhone("617-555-20" + String.format("%02d", i));
            }
            
            // 创建学生档案
            StudentProfile studentProfile = studentDirectory.newStudentProfile(studentPerson);
            
            // 创建学生账户
            userAccountDirectory.newUserAccount(
                studentPerson, "student" + i, "pass" + i, "Student"
            );
            
            // 为学生注册课程
            CourseLoad courseLoad = studentProfile.newCourseLoad("Fall2025");
            
            // 注册策略：
            // - 所有学生都注册核心课程 INFO 5100
            // - 前5个学生额外注册 INFO 5200（总共8学分）
            // - 后5个学生额外注册 INFO 6150（总共8学分）
            
            // 注册核心课程
            if(co5100 != null) {
                SeatAssignment sa1 = courseLoad.newSeatAssignment(co5100);
                if(sa1 != null) {
                    sa1.setGrade(grades[i-1]);  // 设置成绩
                    
                    // 为学生账户添加学费
                    if(studentProfile.getTuitionAccount() != null) {
                        studentProfile.getTuitionAccount().charge(4);  // 4学分的费用
                    }
                }
            }
            
            // 注册第二门课程
            if(i <= 5 && co5200 != null) {
                SeatAssignment sa2 = courseLoad.newSeatAssignment(co5200);
                if(sa2 != null) {
                    sa2.setGrade(grades[9-i]);  // 设置不同的成绩
                    
                    // 添加学费
                    if(studentProfile.getTuitionAccount() != null) {
                        studentProfile.getTuitionAccount().charge(4);
                    }
                }
            } else if(i > 5 && co6150 != null) {
                SeatAssignment sa2 = courseLoad.newSeatAssignment(co6150);
                if(sa2 != null) {
                    sa2.setGrade(grades[i-1]);
                    
                    // 添加学费
                    if(studentProfile.getTuitionAccount() != null) {
                        studentProfile.getTuitionAccount().charge(4);
                    }
                }
            }
            
            // 前3个学生已付学费
            if(i <= 3 && studentProfile.getTuitionAccount() != null) {
                double tuition = studentProfile.getTuitionAccount().getBalance();
                studentProfile.getTuitionAccount().pay(tuition);
                System.out.println("Student " + i + " has paid tuition: $" + tuition);
            }
        }
        
        // ============ 7. 创建注册官（如果是4人组）============
        System.out.println("Creating registrar...");
        
        Person registrarPerson = personDirectory.newPerson("REG001");
        if(registrarPerson != null) {
            registrarPerson.setEmail("registrar@university.edu");
            registrarPerson.setPhone("617-555-0002");
        }
        
        userAccountDirectory.newUserAccount(
            registrarPerson, "registrar", "reg123", "Registrar"
        );
        
        // ============ 8. 创建额外的测试人员（达到30人要求）============
        System.out.println("Creating additional persons to meet 30 person requirement...");
        
        for(int i = 11; i <= 30; i++) {
            Person person = personDirectory.newPerson("PER00" + i);
            if(person != null) {
                person.setEmail("person" + i + "@university.edu");
                person.setPhone("617-555-30" + String.format("%02d", i));
            }
        }
        
        // ============ 9. 打印统计信息 ============
        System.out.println("\n========== Configuration Summary ==========");
        System.out.println("Department: " + department.getName());
        System.out.println("Total Persons: 30+");
        System.out.println("Total Students: 10");
        System.out.println("Total Faculty: 10");
        System.out.println("Admin: 1");
        System.out.println("Registrar: 1");
        System.out.println("Courses Offered: 5");
        System.out.println("Semester: Fall2025");
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