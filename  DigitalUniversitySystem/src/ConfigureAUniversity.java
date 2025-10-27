
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

        if (co5100 != null) {
            co5100.generatSeats(30);
        }
        if (co5200 != null) {
            co5200.generatSeats(30);
        }
        if (co6150 != null) {
            co6150.generatSeats(30);
        }
        if (co6250 != null) {
            co6250.generatSeats(30);
        }
        if (co7390 != null) {
            co7390.generatSeats(30);
        }

        PersonDirectory personDirectory = department.getPersonDirectory();
        StudentDirectory studentDirectory = department.getStudentDirectory();
        FacultyDirectory facultyDirectory = department.getFacultyDirectory();
        UserAccountDirectory userAccountDirectory = department.getUserAccountDirectory();
        RegistrarDirectory registrarDirectory = department.getRegistrardirectory();

        System.out.println("Creating admin...");

        Person adminPerson = personDirectory.newPerson("ADMIN001");
        if (adminPerson != null) {
            adminPerson.setEmail("admin@university.edu");
            adminPerson.setPhone("617-555-0001");
        }

        UserAccount adminAccount = userAccountDirectory.newUserAccount(
                adminPerson, "admin", "admin123", "Admin"
        );

        String[] facultyNames = {
            "Dr. Alice Carter",
            "Dr. Benjamin Lee",
            "Dr. Cynthia Patel",
            "Dr. Daniel Garcia",
            "Dr. Evelyn Brooks",
            "Dr. Farid Khan",
            "Dr. Hannah O'Neil",
            "Dr. Isaac Chen",
            "Dr. Julia Romero",
            "Dr. Kevin Wright"
        };

        String[] facultyDepartments = {
            "Information Systems",
            "Information Systems",
            "Computer Science",
            "Computer Science",
            "Data Analytics",
            "Data Analytics",
            "Cybersecurity",
            "Software Engineering",
            "AI & ML",
            "Data Analytics"
        };

        String[] facultyTitles = {
            "Professor",
            "Associate Professor",
            "Assistant Professor",
            "Senior Lecturer",
            "Lecturer",
            "Professor of Practice",
            "Adjunct Professor",
            "Teaching Fellow",
            "Visiting Scholar",
            "Assistant Professor"
        };

        System.out.println("Creating 10 faculty members...");

        for (int i = 1; i <= 10; i++) {
            Person facultyPerson = personDirectory.newPerson("FAC00" + i);
            if (facultyPerson != null) {

                facultyPerson.setName(facultyNames[i - 1]);

                facultyPerson.setEmail("faculty" + i + "@university.edu");
                facultyPerson.setPhone("617-555-10" + String.format("%02d", i));
            }

            FacultyProfile facultyProfile = facultyDirectory.newFacultyProfile(facultyPerson);
            facultyProfile.setDepartment(facultyDepartments[i - 1]);
            facultyProfile.setTitle(facultyTitles[i - 1]);
            facultyProfile.setFacultyName(facultyNames[i - 1]);

            userAccountDirectory.newUserAccount(
                    facultyPerson, "faculty" + i, "pass" + i, "Faculty"
            );

            if (i == 1 && co5100 != null) {
                co5100.AssignAsTeacher(facultyProfile);
            }
            if (i == 2 && co5200 != null) {
                co5200.AssignAsTeacher(facultyProfile);
            }
            if (i == 3 && co6150 != null) {
                co6150.AssignAsTeacher(facultyProfile);
            }
            if (i == 4 && co6250 != null) {
                co6250.AssignAsTeacher(facultyProfile);
            }
            if (i == 5 && co7390 != null) {
                co7390.AssignAsTeacher(facultyProfile);
            }

        }

        System.out.println("Creating 10 students with course registrations...");

        String[] studentNames = {
            "Alice Johnson", "Bob Smith", "Carol White", "David Brown",
            "Emma Davis", "Frank Miller", "Grace Wilson", "Henry Moore",
            "Iris Taylor", "Jack Anderson"
        };

        String[] studentDepartments = {
            "Information Systems",
            "Information Systems",
            "Computer Science",
            "Information Systems",
            "Data Analytics",
            "Computer Science",
            "Cybersecurity",
            "Information Systems",
            "AI & ML",
            "Software Engineering"
        };

        String[] grades = {"A", "A-", "B+", "B", "B-", "A", "B+", "A-", "B", "B+"};

        for (int i = 1;
                i <= 10; i++) {
            Person studentPerson = personDirectory.newPerson("STU00" + i);
            if (studentPerson != null) {
                studentPerson.setName(studentNames[i - 1]);
                studentPerson.setEmail("student" + i + "@university.edu");
                studentPerson.setPhone("617-555-20" + String.format("%02d", i));
            }

            StudentProfile studentProfile = studentDirectory.newStudentProfile(studentPerson);
            studentProfile.setStudentName(studentNames[i - 1]);              
            studentProfile.setDepartment(studentDepartments[i - 1]);          
            studentProfile.setAcademicStatus("Active");

            userAccountDirectory.newUserAccount(
                    studentPerson, "student" + i, "pass" + i, "Student"
            );

            CourseLoad courseLoad = studentProfile.newCourseLoad("Fall2025");

            if (co5100 != null) {
                SeatAssignment sa1 = courseLoad.newSeatAssignment(co5100);
                if (sa1 != null) {
                    sa1.setGrade(grades[i - 1]);

                    if (studentProfile.getTuitionAccount() != null) {
                        studentProfile.getTuitionAccount().charge(4);
                    }
                }
            }

            if (i <= 5 && co5200 != null) {
                SeatAssignment sa2 = courseLoad.newSeatAssignment(co5200);
                if (sa2 != null) {
                    sa2.setGrade(grades[9 - i]);

                    if (studentProfile.getTuitionAccount() != null) {
                        studentProfile.getTuitionAccount().charge(4);
                    }
                }
            } else if (i > 5 && co6150 != null) {
                SeatAssignment sa2 = courseLoad.newSeatAssignment(co6150);
                if (sa2 != null) {
                    sa2.setGrade(grades[i - 1]);

                    if (studentProfile.getTuitionAccount() != null) {
                        studentProfile.getTuitionAccount().charge(4);
                    }
                }
            }

            if (i <= 3 && studentProfile.getTuitionAccount() != null) {
                double tuition = studentProfile.getTuitionAccount().getBalance();
                studentProfile.getTuitionAccount().pay(tuition);
                System.out.println("Student " + i + " has paid tuition: $" + tuition);
            }
        }

        System.out.println(
                "Creating registrar...");

        Person registrarPerson = personDirectory.newPerson("REG001");
        if (registrarPerson
                != null) {
            registrarPerson.setName("Laura Simmons"); 
            registrarPerson.setEmail("registrar@university.edu");
            registrarPerson.setPhone("617-555-0002");
        }

        userAccountDirectory.newUserAccount(
                registrarPerson,
                "registrar", "reg123", "Registrar"
        );

        System.out.println(
                "Creating additional persons to meet 30 person requirement...");

        for (int i = 11;
                i <= 30; i++) {
            Person person = personDirectory.newPerson("PER00" + i);
            if (person != null) {
                person.setEmail("person" + i + "@university.edu");
                person.setPhone("617-555-30" + String.format("%02d", i));
            }
        }

        System.out.println(
                "\n========== Configuration Summary ==========");
        System.out.println(
                "Department: " + department.getName());
        System.out.println(
                "Total Persons: 30+");
        System.out.println(
                "Total Students: 10");
        System.out.println(
                "Total Faculty: 10");
        System.out.println(
                "Admin: 1");
        System.out.println(
                "Registrar: 1");
        System.out.println(
                "Courses Offered: 5");
        System.out.println(
                "Semester: Fall2025");
        System.out.println(
                "\n========== Login Credentials ==========");
        System.out.println(
                "Admin: username='admin', password='admin123'");
        System.out.println(
                "Student 1: username='student1', password='pass1'");
        System.out.println(
                "Student 2: username='student2', password='pass2'");
        System.out.println(
                "Faculty 1: username='faculty1', password='pass1'");
        System.out.println(
                "Registrar: username='registrar', password='reg123'");
        System.out.println(
                "=========================================\n");

        return department;
    }

    public static void main(String[] args) {
        System.out.println("Starting University Configuration...\n");
        Department dept = setupTestData();
        System.out.println("\nConfiguration completed successfully!");

        UserAccountDirectory uad = dept.getUserAccountDirectory();
        UserAccount testAccount = uad.authenticate("admin", "admin123");
        if (testAccount != null) {
            System.out.println("Login test successful: " + testAccount.getRole());
        } else {
            System.out.println("Login test failed!");
        }
    }
}
