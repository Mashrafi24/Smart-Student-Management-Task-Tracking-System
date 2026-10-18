# Smart Student Task & Assignment Tracking System

A Java desktop prototype for managing courses, academic tasks, assignment deadlines, and progress.

## Student Information
- **Name:** Md. Mashrafi Rahman
- **ID:** IT-24034
- **Course:** SEPM-ICT-3107
- **Process model:** Agile Scrum

## Features in version 1.0
- Modern Java Swing interface with FlatLaf
- Dashboard: courses, tasks, completed tasks, overdue tasks, completion percentage
- Add/edit/delete courses
- Add/edit/delete tasks, priority, status and due date
- Add/edit/delete assignments, status and due date
- SQLite persistence; starter courses are added on first launch
- Date validation (`YYYY-MM-DD`)

## Requirements
- JDK 26
- IntelliJ IDEA
- Maven (bundled Maven in IntelliJ is fine)
- Internet access on the first Maven import to download dependencies

## Run in IntelliJ IDEA
1. Extract the ZIP file.
2. Open IntelliJ IDEA and select **File → Open**.
3. Select the project folder that contains `pom.xml`.
4. Accept the Maven import and wait for dependencies to finish downloading.
5. Open `src/main/java/com/mashrafi/smartstudent/App.java`.
6. Click the green Run icon beside `main()`.

The app creates `smart_student.db` in its working directory. Keep this file to keep your data.

## Project structure
```text
Smart-Student-Task-Tracking-System/
├── pom.xml
├── README.md
└── src/main/java/com/mashrafi/smartstudent/
    ├── App.java
    ├── data/Database.java
    └── ui/MainFrame.java
```

## How to use
1. Open **Courses** and create or edit a course.
2. Open **My Tasks** to add a task, choose its course, priority, status and due date.
3. Open **Assignments** to add an assignment and deadline.
4. Mark a task as completed or an assignment as submitted.
5. Return to the dashboard to view updated totals.

Dates must be entered as `YYYY-MM-DD`, for example `2026-11-05`.

## Current limitations
This is the initial desktop prototype. It does not yet include user login/authentication, cloud synchronization, automatic notifications, teacher/admin roles, or automated unit tests. These can be added in later iterations.

## Next project deliverables
- Requirements specification and project proposal
- Gantt chart and milestones
- DFD, Use Case, Activity, ER, Class and Object diagrams
- Testing report and screenshots
- Final project documentation

**Plan your work. Track your progress. Achieve your goals.**


## JDK 26 Configuration

This version is configured specifically for JDK 26.

In IntelliJ IDEA:
1. Set **Project SDK** to JDK 26.
2. Set **Project language level** to 26.
3. Reload the Maven project.
4. Run `App.java`.

The `pom.xml` uses Maven compiler release **26**.
