# Digital University System – Access-Controlled Use Cases

## Team Information

| Name          | Role / Use Case Responsibility                                          | NUID        |
|---------------|-------------------------------------------------------------------------|-------------|
| Noor Andre    | Administrator Use Case                                                  | 002594734      |
| Zhifei Ye     | Faculty Use Case                                                        | 003156944      |
| Jinkun Zhao   | Student Use Case – Coursework Management, Course Registration, Graduation Audit; System Configuration/Sturcture Design; README/Main Maintenance | 002502631      |
| Kara Payne    | Student Use Case – Transcript Review, Profile Management, Financial Management | 000540472 |
| Kewen Chen    | Registrar Use Case; ystem Configuration/Sturcture Design                               | 002593051     |

---

##  Project Overview

This project implements an **Access-Controlled Digital University System** in Java.  
Based on the provided architecture, we integrated a role-based authentication & authorization layer and completed the required use cases for **Administrator, Faculty, Student, and Registrar**.

**Key Features Implemented:**
- Secure login with role-based access (Admin / Faculty / Student / Registrar)
- Course creation, enrollment, grading, and tuition workflows
- Graduation audit (auto-credit tracking for MSIS – 32 credits rule)
- Transcript generation with GPA calculation and academic standing
- Student tuition payment and financial records
- Profile management for all roles
- System configuration, data initialization, and conflict handling

---

## Installation & Setup Instructions

### 1. Prerequisites
| Requirement      | Version / Description                    |
|------------------|------------------------------------------|
| Java JDK         | JDK 17 or above                         |
| IDE              | NetBeans       |
| Build Tool       | Maven / Manual Compilation (if needed)   |
| Libraries        | Included in `/lib` folder of repository  |
| GUI Framework    | Java Swing (already integrated)          |

---

### 2. Clone the Repository

```bash
git clone https://github.com/KewenChenNeu/Group_1_Midterm_Project.git
cd digital-university-system
