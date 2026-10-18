package com.mashrafi.smartstudent.data;

import java.sql.*;
import java.time.LocalDate;
import java.util.*;

public final class Database {
    private static final String URL = "jdbc:sqlite:smart_student.db";
    private Database() {}

    private static Connection open() throws SQLException {
        Connection c = DriverManager.getConnection(URL);
        try (Statement s = c.createStatement()) { s.execute("PRAGMA foreign_keys=ON"); }
        return c;
    }

    public static void initialize() throws SQLException {
        try (Connection c = open(); Statement s = c.createStatement()) {
            s.executeUpdate("CREATE TABLE IF NOT EXISTS courses(id INTEGER PRIMARY KEY AUTOINCREMENT, code TEXT NOT NULL UNIQUE, name TEXT NOT NULL, semester TEXT NOT NULL DEFAULT '')");
            s.executeUpdate("CREATE TABLE IF NOT EXISTS tasks(id INTEGER PRIMARY KEY AUTOINCREMENT, title TEXT NOT NULL, description TEXT DEFAULT '', course_id INTEGER, priority TEXT NOT NULL, status TEXT NOT NULL, due_date TEXT NOT NULL, FOREIGN KEY(course_id) REFERENCES courses(id) ON DELETE SET NULL)");
            s.executeUpdate("CREATE TABLE IF NOT EXISTS assignments(id INTEGER PRIMARY KEY AUTOINCREMENT, title TEXT NOT NULL, description TEXT DEFAULT '', course_id INTEGER, due_date TEXT NOT NULL, status TEXT NOT NULL, FOREIGN KEY(course_id) REFERENCES courses(id) ON DELETE SET NULL)");
        }
        if (courses().length == 0) {
            save("courses", 0, new String[]{"SEPM-ICT-3107", "Software Engineering & Project Management", "Current"});
            save("courses", 0, new String[]{"JAVA-2101", "Java Programming", "Current"});
            save("courses", 0, new String[]{"DB-2102", "Database Systems", "Current"});
        }
    }

    public static Object[][] rows(String type) throws SQLException {
        String sql = switch (type) {
            case "courses" -> "SELECT id,code,name,semester FROM courses ORDER BY code";
            case "tasks" -> "SELECT t.id,t.title,COALESCE(c.code,'No course'),t.priority,t.status,t.due_date FROM tasks t LEFT JOIN courses c ON c.id=t.course_id ORDER BY t.due_date";
            case "assignments" -> "SELECT a.id,a.title,COALESCE(c.code,'No course'),a.due_date,a.status FROM assignments a LEFT JOIN courses c ON c.id=a.course_id ORDER BY a.due_date";
            default -> throw new IllegalArgumentException("Unknown data type");
        };
        List<Object[]> out = new ArrayList<>();
        try (Connection c = open(); Statement s = c.createStatement(); ResultSet r = s.executeQuery(sql)) {
            int n = r.getMetaData().getColumnCount();
            while (r.next()) { Object[] row = new Object[n]; for (int i=1;i<=n;i++) row[i-1]=r.getObject(i); out.add(row); }
        }
        return out.toArray(new Object[0][]);
    }

    public static Object[][] courses() throws SQLException { return rows("courses"); }

    public static void save(String type, int id, String[] v) throws SQLException {
        String sql;
        switch (type) {
            case "courses" -> {
                require(v[0], "Course code"); require(v[1], "Course name");
                sql = id == 0 ? "INSERT INTO courses(code,name,semester) VALUES(?,?,?)" :
                        "UPDATE courses SET code=?,name=?,semester=? WHERE id=?";
                try (Connection c=open(); PreparedStatement p=c.prepareStatement(sql)) {
                    p.setString(1,v[0].trim()); p.setString(2,v[1].trim()); p.setString(3,v[2].trim());
                    if (id!=0) p.setInt(4,id); p.executeUpdate();
                }
            }
            case "tasks" -> {
                require(v[0], "Task title"); validDate(v[4]);
                sql = id == 0 ? "INSERT INTO tasks(title,description,course_id,priority,status,due_date) VALUES(?,?,?,?,?,?)" :
                        "UPDATE tasks SET title=?,description=?,course_id=?,priority=?,status=?,due_date=? WHERE id=?";
                try (Connection c=open(); PreparedStatement p=c.prepareStatement(sql)) {
                    p.setString(1,v[0].trim()); p.setString(2,v[1].trim()); setCourse(p,3,v[2]);
                    p.setString(4,v[3]); p.setString(5,v[5]); p.setString(6,v[4].trim());
                    if (id!=0) p.setInt(7,id); p.executeUpdate();
                }
            }
            case "assignments" -> {
                require(v[0], "Assignment title"); validDate(v[3]);
                sql = id == 0 ? "INSERT INTO assignments(title,description,course_id,due_date,status) VALUES(?,?,?,?,?)" :
                        "UPDATE assignments SET title=?,description=?,course_id=?,due_date=?,status=? WHERE id=?";
                try (Connection c=open(); PreparedStatement p=c.prepareStatement(sql)) {
                    p.setString(1,v[0].trim()); p.setString(2,v[1].trim()); setCourse(p,3,v[2]);
                    p.setString(4,v[3].trim()); p.setString(5,v[4]);
                    if (id!=0) p.setInt(6,id); p.executeUpdate();
                }
            }
            default -> throw new IllegalArgumentException("Unknown data type");
        }
    }

    private static void setCourse(PreparedStatement p, int index, String selected) throws SQLException {
        if (selected == null || selected.startsWith("No course")) p.setNull(index, Types.INTEGER);
        else p.setInt(index, Integer.parseInt(selected.substring(0, selected.indexOf(" | "))));
    }

    public static String description(String type, int id) throws SQLException {
        if (!type.equals("tasks") && !type.equals("assignments")) return "";
        String table = type.equals("tasks") ? "tasks" : "assignments";
        try (Connection c = open();
             PreparedStatement p = c.prepareStatement("SELECT description FROM " + table + " WHERE id=?")) {
            p.setInt(1, id);
            try (ResultSet r = p.executeQuery()) {
                return r.next() ? r.getString(1) : "";
            }
        }
    }

    public static String[] courseOptions() throws SQLException {
        Object[][] cs = courses();
        String[] result = new String[cs.length];
        for (int i=0;i<cs.length;i++) result[i] = cs[i][0] + " | " + cs[i][1] + " | " + cs[i][2];
        return result;
    }

    public static void delete(String type, int id) throws SQLException {
        String table = switch (type) { case "courses" -> "courses"; case "tasks" -> "tasks"; case "assignments" -> "assignments"; default -> throw new IllegalArgumentException(); };
        try (Connection c=open(); PreparedStatement p=c.prepareStatement("DELETE FROM "+table+" WHERE id=?")) { p.setInt(1,id); p.executeUpdate(); }
    }

    public static int count(String type, String condition) throws SQLException {
        String table = switch(type) { case "courses" -> "courses"; case "tasks" -> "tasks"; case "assignments" -> "assignments"; default -> throw new IllegalArgumentException(); };
        String sql = "SELECT COUNT(*) FROM " + table + (condition == null ? "" : " WHERE " + condition);
        try (Connection c=open(); Statement s=c.createStatement(); ResultSet r=s.executeQuery(sql)) { return r.next()?r.getInt(1):0; }
    }

    private static void require(String value, String field) {
        if (value == null || value.isBlank()) throw new IllegalArgumentException(field + " is required.");
    }
    private static void validDate(String value) {
        try { LocalDate.parse(value.trim()); }
        catch (Exception e) { throw new IllegalArgumentException("Date must use YYYY-MM-DD format."); }
    }
}
