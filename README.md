# Smart-Student-Management-Task-Tracking-System

# 🎓 Smart Student Task & Assignment Tracking System

> A simple and user-friendly software system designed to help students manage, organize, and track their academic tasks, assignments, deadlines, and overall progress.

---

👨‍🎓 ##Student Information

| Information | Details |
|---|---|
| **Name** | Md. Mashrafi Rahman |
| **Student ID** | IT-24034 |
| **Course** | SEPM-ICT-3107 |
| **Project Type** | Software Engineering Project |
| **Development Model** | Agile Scrum |

---

📌 ##1. Project Overview

**Smart Student Task & Assignment Tracking System** is an academic productivity and task-management application designed for students.

The system provides a centralized platform where students can manage their courses, assignments, academic tasks, deadlines, priorities, and completion status.

Instead of maintaining academic activities across notebooks, messaging applications, or different productivity tools, students can manage everything from a single system.

### Main Concept

```text
                    SMART STUDENT
                         │
                         ▼
                       LOGIN
                         │
                         ▼
                     DASHBOARD
                         │
          ┌──────────────┼──────────────┐
          ▼              ▼              ▼
       COURSES         TASKS       ASSIGNMENTS
          │              │              │
          └──────────────┼──────────────┘
                         ▼
                      DEADLINES
                         │
                         ▼
                       PROGRESS



    ## 2. Problem Statement
Students often have to manage multiple courses, assignments, projects, laboratory tasks, and deadlines simultaneously.
Academic information may be stored in different places such as:
- Notebook
- Mobile notes
- Calendar
- Messaging applications
- Personal reminders
This can make it difficult to:
- Track pending tasks
- Remember deadlines
- Identify high-priority work
- Monitor completed assignments
- Understand overall academic progress
The proposed system solves this problem by providing a centralized academic task and assignment tracking platform.


## 3. Project Objectives
The main objectives of the project are:
- Create a centralized academic task-management system.
- Allow students to manage their courses.
- Allow students to create and manage tasks.
- Allow students to manage assignments.
- Track assignment and task deadlines.
- Set task priorities.
- Track task completion status.
- Display upcoming deadlines.
- Provide an overview of academic progress.
- Reduce missed deadlines.
- Provide a simple and user-friendly interface.


## 4. Target Users
The system can support three main types of users.
👨‍🎓 Student
Students can:
- Login to the system.
- View their dashboard.
- Manage courses.
- Create tasks.
- Update tasks.
- Delete tasks.
- Set task priority.
- Set deadlines.
- Mark tasks as completed.
- View assignments.
- Track academic progress.
👨‍🏫 Teacher
Teachers can:
- Login to the system.
- Manage courses.
- Create assignments.
- Set assignment deadlines.
- Update assignments.
- View student progress.
🛠️ Administrator
Administrators can:
- Manage users.
- Manage students.
- Manage teachers.
- Manage courses.
- Manage system information.


## 5. Core Features
🔐 Authentication
- User login
- Logout
- Basic role-based access
📚 Course Management
- Add course
- View courses
- Update course
- Delete course
📝 Task Management
- Create task
- View tasks
- Update task
- Delete task
- Set priority
- Set deadline
- Mark task as completed
📋 Assignment Management
- Create assignment
- View assignment
- Update assignment
- Delete assignment
- Set deadline
- Track assignment status
📅 Deadline Tracking
The system displays upcoming deadlines so students can identify important tasks.
📊 Progress Tracking
Students can see:
- Total tasks
- Completed tasks
- Pending tasks
- Overdue tasks
- Overall completion percentage


##6. System Workflow
The general workflow of the system is:
Start
  │
  ▼
Login
  │
  ▼
Dashboard
  │
  ├──────────────► Courses
  │
  ├──────────────► Tasks
  │
  ├──────────────► Assignments
  │
  └──────────────► Progress
                         │
                         ▼
                       Logout
                         │
                         ▼
                        End

##7. Proposed Classes
The initial Java implementation will use at least five classes from the class design.
Core Classes
User
Student
Teacher
Course
Task
Assignment
Progress
Notification

Main Class Responsibilities
Class	Responsibility
User	Common user information
Student	Student information and actions
Teacher	Teacher information and assignment management
Course	Course information
Task	Academic task information
Assignment	Assignment information and deadline
Progress	Student task completion progress
Notification	Deadline/reminder information


##8. User Interface
The initial prototype will use a simple and clean interface.
Main Screens
Login
  ↓
Dashboard
  ↓
Courses
  ↓
Tasks
  ↓
Assignments
  ↓
Progress

Proposed UI Classes
LoginFrame
DashboardFrame
CoursePanel
TaskPanel
AssignmentPanel
ProgressPanel

##9. Dashboard Concept
The dashboard will provide a quick overview of the student's academic activities.
┌───────────────────────────────────────────────┐
│        SMART STUDENT                         │
├──────────────┬────────────────────────────────┤
│ Dashboard    │ Welcome, Mashrafi             │
│              │                                │
│ Courses      │ Total Courses       5         │
│ Tasks        │ Pending Tasks        8         │
│ Assignments  │ Completed Tasks     12         │
│ Progress     │ Overdue Tasks        2         │
│              │                                │
│              │ Progress:                     │
│              │ ███████████████░░░ 75%        │
│              │                                │
│ Logout       │ Upcoming Deadlines             │
└──────────────┴────────────────────────────────┘



##10. Software Development Process
The project will follow the Agile Scrum Software Process Model.
Development Process
Product Backlog
       ↓
Sprint Planning
       ↓
Development
       ↓
Testing
       ↓
Sprint Review
       ↓
Feedback
       ↓
Improvement
       ↓
Next Sprint

Agile allows the project to be developed incrementally and makes it easier to improve the system based on feedback.


##11. Project Schedule
Week	Activity
Week 1	Requirement Analysis
Week 2	Project Proposal
Week 3	UML & System Design
Week 4	Database / ER Design
Week 5	UI Design
Week 6	Core Java Development
Week 7	Feature Development
Week 8	Integration & Testing
Week 9	Bug Fixing & Improvements
Week 10	Documentation & Final Submission


## 12. Project Milestones
Milestone	Description
M1	Requirements Completed
M2	Project Proposal Completed
M3	UML Design Completed
M4	Database Design Completed
M5	UI Prototype Completed
M6	Core Development Completed
M7	Testing Completed
M8	Documentation Completed
M9	Final Submission


##13. Gantt Chart
Task                    W1 W2 W3 W4 W5 W6 W7 W8 W9 W10
--------------------------------------------------------
Requirements            ■  ■
Proposal                   ■  ■
UML Design                    ■  ■
Database Design                 ■  ■
UI Design                         ■  ■
Development                           ■  ■
Testing                                    ■  ■
Documentation                                  ■  ■

## 14. UML & System Diagrams
The project documentation will include the following diagrams:
1. Data Flow Diagram (DFD)
Shows how data moves between users, system processes, and stored information.
Student
   │
   ▼
Task Management
   │
   ▼
Database
   │
   ▼
Task Information
   │
   ▼
Student

2. Use Case Diagram
Main student use cases:
Student
 ├── Login
 ├── View Dashboard
 ├── Manage Courses
 ├── Create Task
 ├── Update Task
 ├── Delete Task
 ├── Complete Task
 ├── View Assignment
 ├── View Deadline
 └── View Progress

Teacher:
Teacher
 ├── Login
 ├── Manage Course
 ├── Create Assignment
 ├── Set Deadline
 └── View Student Progress

3. Activity Diagram
Example: Creating a Task
START
  ↓
Login
  ↓
Dashboard
  ↓
Select "Add Task"
  ↓
Enter Task Information
  ↓
Validate Information
  ↓
Information Valid?
  │
  ├── NO → Show Error → Enter Again
  │
  └── YES
        ↓
     Save Task
        ↓
    Show Success
        ↓
       END

4. Entity Relationship Diagram (ERD)
Main entities:
USER
 │
 ├── STUDENT
 │
 └── TEACHER

STUDENT
   │
   ├── TASK
   │
   ├── ASSIGNMENT
   │
   └── PROGRESS

TEACHER
   │
   └── ASSIGNMENT

COURSE
   │
   ├── TASK
   └── ASSIGNMENT

5. Class Diagram
Proposed relationship:
                 User
                /    \
               /      \
          Student      Teacher
             │            │
             │            │
             ▼            ▼
           Task       Assignment
             │            │
             └─────┬──────┘
                   │
                 Course
                   │
                Progress

6. Object Diagram
Example runtime objects:
student01 : Student
-------------------------
studentId = IT-24034
name = Md. Mashrafi Rahman


course01 : Course
-------------------------
courseId = SEPM-ICT-3107
courseName = Software Engineering


task01 : Task
-------------------------
title = UML Diagram
priority = HIGH
status = PENDING

##15. Database Concept
The proposed database may contain:
Users
Students
Teachers
Courses
Tasks
Assignments
Progress
Notifications

Example relationship:
Student
   │
   ├──── has ────► Tasks
   │
   └──── enrolls ─► Courses

Teacher
   │
   └──── creates ─► Assignments

Course
   │
   ├──── contains ─► Tasks
   │
   └──── contains ─► Assignments



## 16. Technology Stack
Programming Language
Java

User Interface
Java Swing

Development Environment
IntelliJ IDEA

Version Control
Git & GitHub

Documentation
LaTeX
Markdown

Database
Initial Prototype → Java Objects / Simple Data Storage

Future Version → MySQL / PostgreSQL



## 17. Testing Plan
The system will be tested using different functional test cases.
Test Case	Expected Result
Valid Login	Dashboard opens
Invalid Login	Error message appears
Create Task	Task appears in task list
Update Task	Updated information is displayed
Delete Task	Task is removed
Complete Task	Status changes to Completed
Create Assignment	Assignment appears
Set Deadline	Deadline is displayed
View Progress	Correct progress is displayed



## 18. Project Deliverables
The final project will contain:
- Project Proposal
- Requirement Specification
- Software Process Model
- Project Schedule
- Gantt Chart
- DFD
- Use Case Diagram
- Activity Diagram
- ER Diagram
- Class Diagram
- Object Diagram
- Java Source Code
- GUI Prototype
- Testing Documentation
- Screenshots
- Final Documentation
- GitHub Repository



## 19. GitHub Repository Structure
Smart-Student-Task-Tracking-System/
│
├── README.md
│
├── Proposal/
│   ├── Project-Proposal.pdf
│   └── Project-Proposal.tex
│
├── UML/
│   ├── DFD.png
│   ├── Use-Case-Diagram.png
│   ├── Activity-Diagram.png
│   ├── ER-Diagram.png
│   ├── Class-Diagram.png
│   └── Object-Diagram.png
│
├── Gantt/
│   └── Gantt-Chart.png
│
├── src/
│   ├── User.java
│   ├── Student.java
│   ├── Teacher.java
│   ├── Course.java
│   ├── Task.java
│   ├── Assignment.java
│   ├── Progress.java
│   └── Notification.java
│
├── ui/
│   ├── LoginFrame.java
│   ├── DashboardFrame.java
│   ├── CoursePanel.java
│   ├── TaskPanel.java
│   ├── AssignmentPanel.java
│   └── ProgressPanel.java
│
├── screenshots/
│
└── docs/
    └── Testing.md



##20. Future Improvements
The initial version will focus on the core requirements.
Future versions may include:
- Email notifications
- Automatic deadline reminders
- Calendar integration
- Mobile application
- Cloud database
- Online synchronization
- Teacher-student communication
- File attachment for assignments
- Advanced analytics
- AI-based study recommendations


##21. Expected Outcome
At the end of the project, the system will provide students with a simple platform for managing their academic activities.
The initial prototype will demonstrate:
Login
  ↓
Dashboard
  ↓
Course Management
  ↓
Task Management
  ↓
Assignment Tracking
  ↓
Deadline Tracking
  ↓
Progress Tracking

The project will demonstrate the complete software development process from requirement analysis to system design, implementation, testing, documentation, and GitHub submission.

⭐ Project Status
Project Planning     ████████████████████ 100%
Requirements         ███████████████░░░░░  75%
System Design        ████████░░░░░░░░░░░  40%
Development          ███░░░░░░░░░░░░░░░░  15%
Testing              ░░░░░░░░░░░░░░░░░░░   0%
Documentation        █████░░░░░░░░░░░░░░  25%

Smart Student — Plan your work. Track your progress. Achieve your goals.
