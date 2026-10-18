package com.mashrafi.smartstudent.ui;

import com.mashrafi.smartstudent.data.Database;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.SQLException;

public class MainFrame extends JFrame {
    private final CardLayout layout = new CardLayout();
    private final JPanel pages = new JPanel(layout);
    private final DataPage coursePage = new DataPage("courses");
    private final DataPage taskPage = new DataPage("tasks");
    private final DataPage assignmentPage = new DataPage("assignments");
    private final Dashboard dashboard = new Dashboard();

    public MainFrame() {
        setTitle("Smart Student | Academic Planner");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(1150, 720);
        setMinimumSize(new Dimension(950, 620));
        setLocationRelativeTo(null);
        getContentPane().setLayout(new BorderLayout());

        JPanel sidebar = new JPanel();
        sidebar.setBackground(new Color(20,35,67));
        sidebar.setPreferredSize(new Dimension(215,0));
        sidebar.setBorder(new EmptyBorder(25,14,20,14));
        sidebar.setLayout(new BoxLayout(sidebar,BoxLayout.Y_AXIS));
        JLabel brand = new JLabel("<html><font color='white' size='5'><b>✦ SMART STUDENT</b></font><br><font color='#AFC1E7' size='2'>ACADEMIC PLANNER</font></html>");
        brand.setBorder(new EmptyBorder(0,8,30,0)); sidebar.add(brand);
        nav(sidebar,"▦   Dashboard","dashboard");
        nav(sidebar,"✓   My Tasks","tasks");
        nav(sidebar,"▤   Courses","courses");
        nav(sidebar,"▣   Assignments","assignments");
        sidebar.add(Box.createVerticalGlue());
        JLabel foot = new JLabel("<html><font color='#AFC1E7' size='2'>JAVA • SQLITE • SEPM<br>Version 1.0</font></html>");
        foot.setBorder(new EmptyBorder(0,8,4,0)); sidebar.add(foot);

        pages.add(dashboard,"dashboard"); pages.add(taskPage,"tasks");
        pages.add(coursePage,"courses"); pages.add(assignmentPage,"assignments");
        add(sidebar,BorderLayout.WEST); add(pages,BorderLayout.CENTER);
        showPage("dashboard");
    }

    private void nav(JPanel panel,String text,String key) {
        JButton b = new JButton(text);
        b.setAlignmentX(Component.LEFT_ALIGNMENT); b.setMaximumSize(new Dimension(190,44));
        b.setPreferredSize(new Dimension(190,44)); b.setHorizontalAlignment(SwingConstants.LEFT);
        b.setForeground(Color.WHITE); b.setBackground(new Color(20,35,67));
        b.setBorder(new EmptyBorder(0,12,0,5)); b.setFocusPainted(false);
        b.addActionListener(e -> showPage(key)); panel.add(b); panel.add(Box.createVerticalStrut(7));
    }

    private void showPage(String key) {
        switch(key) {
            case "dashboard" -> dashboard.refresh();
            case "tasks" -> taskPage.refresh();
            case "courses" -> coursePage.refresh();
            case "assignments" -> assignmentPage.refresh();
        }
        layout.show(pages,key);
    }

    private static class Dashboard extends JPanel {
        private final JLabel courses = value(), tasks = value(), completed = value(), overdue = value();
        private final JProgressBar bar = new JProgressBar(0,100);
        private final DefaultTableModel model = new DefaultTableModel(new Object[]{"Task","Course","Priority","Status","Due date"},0) {
            public boolean isCellEditable(int r,int c){return false;}
        };
        Dashboard() {
            setLayout(new BorderLayout(0,18)); setBackground(new Color(246,248,252));
            setBorder(new EmptyBorder(28,28,25,28));
            JLabel title = new JLabel("<html><font size='6' color='#142343'><b>Good to see you, Mashrafi 👋</b></font><br><font color='#67748B'>Here's your academic progress at a glance.</font></html>");
            add(title,BorderLayout.NORTH);
            JPanel body = new JPanel(new BorderLayout(0,16)); body.setOpaque(false);
            JPanel metrics = new JPanel(new GridLayout(1,4,12,0)); metrics.setOpaque(false);
            metrics.add(metric("COURSES",courses,new Color(47,107,255)));
            metrics.add(metric("TOTAL TASKS",tasks,new Color(14,165,164)));
            metrics.add(metric("COMPLETED",completed,new Color(22,163,74)));
            metrics.add(metric("OVERDUE",overdue,new Color(225,89,89)));
            JPanel lower = new JPanel(new BorderLayout(0,12)); lower.setOpaque(false);
            JPanel progress = card(); progress.setLayout(new BorderLayout(0,12));
            JLabel ptitle = new JLabel("Overall task completion"); ptitle.setFont(ptitle.getFont().deriveFont(Font.BOLD,16f));
            progress.add(ptitle,BorderLayout.NORTH); bar.setForeground(new Color(14,165,164)); bar.setStringPainted(true); progress.add(bar,BorderLayout.CENTER);
            JPanel recent = card(); recent.setLayout(new BorderLayout(0,10));
            JLabel rtitle = new JLabel("Your tasks"); rtitle.setFont(rtitle.getFont().deriveFont(Font.BOLD,16f));
            JTable table = new JTable(model); table.setFillsViewportHeight(true); table.setRowHeight(28);
            recent.add(rtitle,BorderLayout.NORTH); recent.add(new JScrollPane(table),BorderLayout.CENTER);
            lower.add(progress,BorderLayout.NORTH); lower.add(recent,BorderLayout.CENTER);
            body.add(metrics,BorderLayout.NORTH); body.add(lower,BorderLayout.CENTER); add(body,BorderLayout.CENTER);
        }
        void refresh() {
            try {
                int total=Database.count("tasks",null), done=Database.count("tasks","status='Completed'");
                courses.setText(""+Database.count("courses",null)); tasks.setText(""+total);
                completed.setText(""+done);
                overdue.setText(""+Database.count("tasks","status<>'Completed' AND due_date < date('now','localtime')"));
                int pct=total==0?0:(int)Math.round(done*100.0/total); bar.setValue(pct); bar.setString(pct+"%");
                model.setRowCount(0);
                Object[][] rows=Database.rows("tasks");
                for(int i=0;i<Math.min(8,rows.length);i++) {
                    Object[] r=rows[i]; model.addRow(new Object[]{r[1],r[2],r[3],r[4],r[5]});
                }
            } catch(SQLException e) { error(this,e); }
        }
        private static JLabel value(){ JLabel l=new JLabel("0"); l.setFont(l.getFont().deriveFont(Font.BOLD,28f)); l.setForeground(new Color(20,35,67)); return l; }
        private static JPanel metric(String name,JLabel value,Color accent) {
            JPanel p=card(); p.setLayout(new BorderLayout(0,12));
            JLabel n=new JLabel(name); n.setForeground(new Color(103,116,139)); n.setFont(n.getFont().deriveFont(Font.BOLD,11f));
            JPanel stripe=new JPanel(); stripe.setBackground(accent); stripe.setPreferredSize(new Dimension(2,5));
            p.add(n,BorderLayout.NORTH); p.add(value,BorderLayout.CENTER); p.add(stripe,BorderLayout.SOUTH); return p;
        }
    }

    private static class DataPage extends JPanel {
        private final String type;
        private final DefaultTableModel model;
        private final JTable table;
        private Object[][] data = new Object[0][];
        DataPage(String type) {
            this.type=type;
            String title=type.equals("courses")?"Courses":type.equals("tasks")?"My Tasks":"Assignments";
            String subtitle=type.equals("courses")?"Organise your subjects in one place.":type.equals("tasks")?"Create tasks, set priorities and track deadlines.":"Keep assignment deadlines and submission status organised.";
            setLayout(new BorderLayout()); setBackground(new Color(246,248,252)); setBorder(new EmptyBorder(25,26,25,26));
            JPanel head=new JPanel(); head.setOpaque(false); head.setLayout(new BoxLayout(head,BoxLayout.Y_AXIS));
            JLabel h=new JLabel(title); h.setFont(h.getFont().deriveFont(Font.BOLD,26f)); h.setForeground(new Color(20,35,67));
            JLabel sub=new JLabel(subtitle); sub.setForeground(new Color(103,116,139)); sub.setBorder(new EmptyBorder(5,0,0,0)); head.add(h);head.add(sub); add(head,BorderLayout.NORTH);
            String[] cols=type.equals("courses")?new String[]{"ID","Code","Course name","Semester"}:
                    type.equals("tasks")?new String[]{"ID","Task","Course","Priority","Status","Due date"}:new String[]{"ID","Assignment","Course","Due date","Status"};
            model=new DefaultTableModel(cols,0){public boolean isCellEditable(int r,int c){return false;}};
            table=new JTable(model); table.setRowHeight(30); table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION); table.setAutoCreateRowSorter(true);
            JPanel content=new JPanel(new BorderLayout(0,13)); content.setOpaque(false); content.setBorder(new EmptyBorder(22,0,0,0));
            JPanel buttons=new JPanel(new FlowLayout(FlowLayout.LEFT,8,0)); buttons.setOpaque(false);
            JButton add=button("+ Add "+singular(),true), edit=button("Edit",false), delete=button("Delete",false);
            buttons.add(add);buttons.add(edit);buttons.add(delete);
            if(!type.equals("courses")) { JButton done=button(type.equals("tasks")?"Mark completed":"Mark submitted",false); buttons.add(done);
                done.addActionListener(e -> markDone()); }
            JPanel c=card(); c.add(new JScrollPane(table),BorderLayout.CENTER);
            content.add(buttons,BorderLayout.NORTH);content.add(c,BorderLayout.CENTER);add(content,BorderLayout.CENTER);
            add.addActionListener(e->editRow(-1)); edit.addActionListener(e->{ int selected=selectedId(); if(selected>0) editRow(selected); }); delete.addActionListener(e->deleteRow());
        }
        private String singular(){return type.equals("courses")?"course":type.equals("tasks")?"task":"assignment";}
        void refresh(){
            try { data=Database.rows(type); model.setRowCount(0); for(Object[] r:data) model.addRow(r); }
            catch(SQLException e){error(this,e);}
        }
        private int selectedId(){
            int v=table.getSelectedRow(); if(v<0){JOptionPane.showMessageDialog(this,"Select a row first.");return -1;}
            return ((Number)model.getValueAt(table.convertRowIndexToModel(v),0)).intValue();
        }
        private void editRow(int id) {
            Object[] old=null; if(id>0) for(Object[] r:data) if(((Number)r[0]).intValue()==id) old=r;
            try {
                JTextField a,b,c;
                JComboBox<String> course=null, priority=null, status=null;
                JPanel form=new JPanel(new GridLayout(0,1,5,5));
                if(type.equals("courses")) {
                    a=field(old,1); b=field(old,2); c=field(old,3);
                    form.add(new JLabel("Course code"));form.add(a);form.add(new JLabel("Course name"));form.add(b);form.add(new JLabel("Semester"));form.add(c);
                } else {
                    a=field(old,1); b=new JTextField(old==null?"":Database.description(type,id),20);
                    form.add(new JLabel(type.equals("tasks")?"Task title":"Assignment title"));form.add(a);
                    form.add(new JLabel("Description"));form.add(b);
                    String[] options=Database.courseOptions();
                    course=new JComboBox<>(options);
                    if(old!=null) for(int i=0;i<options.length;i++) if(options[i].contains(String.valueOf(old[2]))) {course.setSelectedIndex(i);break;}
                    form.add(new JLabel("Course"));form.add(course);
                    if(type.equals("tasks")) {
                        priority=new JComboBox<>(new String[]{"Low","Medium","High"}); status=new JComboBox<>(new String[]{"Pending","In Progress","Completed"});
                        if(old!=null){priority.setSelectedItem(old[3]);status.setSelectedItem(old[4]);}
                        c=new JTextField(old==null?java.time.LocalDate.now().plusDays(7).toString():String.valueOf(old[5]),20);
                        form.add(new JLabel("Priority"));form.add(priority);form.add(new JLabel("Status"));form.add(status);form.add(new JLabel("Due date (YYYY-MM-DD)"));form.add(c);
                    } else {
                        status=new JComboBox<>(new String[]{"Pending","In Progress","Submitted"}); if(old!=null)status.setSelectedItem(old[4]);
                        c=new JTextField(old==null?java.time.LocalDate.now().plusDays(7).toString():String.valueOf(old[3]),20);
                        form.add(new JLabel("Due date (YYYY-MM-DD)"));form.add(c);form.add(new JLabel("Status"));form.add(status);
                    }
                    int result=JOptionPane.showConfirmDialog(this,form,id<0?"Add "+singular():"Edit "+singular(),JOptionPane.OK_CANCEL_OPTION,JOptionPane.PLAIN_MESSAGE);
                    if(result!=JOptionPane.OK_OPTION)return;
                    String courseText=(String)course.getSelectedItem();
                    if(type.equals("tasks")) Database.save(type,id<0?0:id,new String[]{a.getText(),b.getText(),courseText,(String)priority.getSelectedItem(),c.getText(),(String)status.getSelectedItem()});
                    else Database.save(type,id<0?0:id,new String[]{a.getText(),b.getText(),courseText,c.getText(),(String)status.getSelectedItem()});
                }
                if(type.equals("courses")) {
                    int result=JOptionPane.showConfirmDialog(this,form,id<0?"Add course":"Edit course",JOptionPane.OK_CANCEL_OPTION,JOptionPane.PLAIN_MESSAGE);
                    if(result!=JOptionPane.OK_OPTION)return;
                    Database.save(type,id<0?0:id,new String[]{a.getText(),b.getText(),c.getText()});
                }
                refresh();
            } catch(Exception e){error(this,e);}
        }
        private JTextField field(Object[] old,int index){return new JTextField(old==null?"":String.valueOf(old[index]),20);}
        private void deleteRow(){
            int id=selectedId();if(id<0)return;
            if(JOptionPane.showConfirmDialog(this,"Delete selected "+singular()+"?","Confirm",JOptionPane.YES_NO_OPTION)==JOptionPane.YES_OPTION)
                try{Database.delete(type,id);refresh();}catch(Exception e){error(this,e);}
        }
        private void markDone(){
            int id=selectedId();if(id<0)return;
            try {
                String sql=type.equals("tasks")?"UPDATE tasks SET status='Completed' WHERE id=?":"UPDATE assignments SET status='Submitted' WHERE id=?";
                try(java.sql.Connection c=java.sql.DriverManager.getConnection("jdbc:sqlite:smart_student.db");
                    java.sql.PreparedStatement p=c.prepareStatement(sql)){p.setInt(1,id);p.executeUpdate();}
                refresh();
            }catch(Exception e){error(this,e);}
        }
    }

    private static JPanel card(){
        JPanel p=new JPanel(new BorderLayout(0,8));p.setBackground(Color.WHITE);
        p.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(new Color(226,232,242)),new EmptyBorder(16,16,16,16)));return p;
    }
    private static JButton button(String text,boolean primary){
        JButton b=new JButton(text);b.setFocusPainted(false);b.setFont(b.getFont().deriveFont(Font.BOLD,13f));
        if(primary){b.setBackground(new Color(47,107,255));b.setForeground(Color.WHITE);}return b;
    }
    private static void error(Component parent,Exception e){
        JOptionPane.showMessageDialog(parent,e.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
    }
}
