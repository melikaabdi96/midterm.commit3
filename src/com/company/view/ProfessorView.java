package com.company.view;

import com.company.model.AdminModel;
import com.company.model.ClassModel;
import com.company.model.ProfessorModel;
import com.company.utils.*;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class ProfessorView {
    private JFrame professorJframe;
    private JMenuBar professorjMenuBar;

    private JMenu myProfile;

    private JMenu setting;
    private JMenuItem changeUserPass;

    private JMenu students;
    private JMenuItem scoring;
    private JMenuItem studentsProfiles;


    private JMenu classes;
    private JMenuItem viewClasses;
    private JMenuItem addClass;
    private JMenuItem deleteClass;

    private JMenu classNotes;
    private JMenuItem viewNotes;
    private JMenuItem addNote;
    private JMenuItem deleteNote;


    private JMenu exit;


    private  JPanel professorPanel;
    private JTabbedPane professorTabbedPane;
    private JList<File> studentList;
    private JList<File> classList;
    private JList<File> noteList;

    public ProfessorView(){
        this.professorJframe = new JFrame();
        professorJframe.setTitle("Professor");
        professorJframe.setSize(1000, 1000);
        professorJframe.setLocation(10, 10);
        professorJframe.setLayout(null);
        professorJframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        this.professorjMenuBar = new JMenuBar();
        professorjMenuBar.setPreferredSize(new Dimension(1000, 50));
        professorJframe.setJMenuBar(professorjMenuBar);
        professorjMenuBar.setOpaque(true);
        professorjMenuBar.setBackground(Color.CYAN);

        this.myProfile = new JMenu("my profile");
        professorjMenuBar.add(myProfile);

        this.setting = new JMenu("setting");
        professorjMenuBar.add(setting);
        this.changeUserPass = new JMenuItem("change username and password");
        changeUserPass.setBackground(Color.CYAN);
        setting.add(changeUserPass);


        this.students = new JMenu("students");
        professorjMenuBar.add(students);
        this.scoring = new JMenuItem("scoring");
        this.studentsProfiles = new JMenuItem("my students");
        scoring.setBackground(Color.cyan);
        studentsProfiles.setBackground(Color.cyan);
        students.add(scoring);
        students.add(studentsProfiles);

        this.classes = new JMenu("classes");
        professorjMenuBar.add(classes);
        this.addClass = new JMenuItem("add class");
        this.viewClasses = new JMenuItem("view classes");
        this.deleteClass = new JMenuItem("delete class");
        addClass.setBackground(Color.cyan);
        viewClasses.setBackground(Color.cyan);
        deleteClass.setBackground(Color.CYAN);
        classes.add(addClass);
        classes.add(deleteClass);
        classes.add(viewClasses);

        this.classNotes = new JMenu("class notes");
        professorjMenuBar.add(classNotes);
        this.addNote = new JMenuItem("add note");
        this.viewNotes = new JMenuItem("view notes");
        this.deleteNote = new JMenuItem("delet note");
        addNote.setBackground(Color.CYAN);
        viewNotes.setBackground(Color.CYAN);
        deleteNote.setBackground(Color.CYAN);
        classNotes.add(addNote);
        classNotes.add(deleteNote);
        classNotes.add(viewNotes);

        this.exit = new JMenu("Exit");
        professorjMenuBar.add(exit);


        this.professorPanel = new JPanel();
        professorPanel.setLayout(new BorderLayout());
        professorPanel.setBounds(0, 0, 1000, 900);
        //adminPanel.setBackground(Color.GRAY);
        professorJframe.add(professorPanel);
        initTabbedPane();
        initPage();
        //professorJframe.pack();
        professorJframe.setVisible(true);
    }

    /**
     * add tabbedpane to panel
     */
    private void initTabbedPane(){
        professorTabbedPane = new JTabbedPane();
        professorPanel.add(professorTabbedPane, BorderLayout.CENTER);
    }

    /**
     * create first page when we log in to frame
     */
    private void initPage(){
        JPanel timePanel = new JPanel(new BorderLayout());
        Font font = new Font("Arial", Font.BOLD, 24);
        timePanel.setFont(font);
        timePanel.setBounds(500, 500, 300, 300);
        timePanel.setPreferredSize(new Dimension(100, 300));

        JLabel timeLable = new JLabel();
        SimpleDateFormat sdf = new SimpleDateFormat("hh:mm:ss");
        LocalDate localDate = LocalDate.now();
        JLabel lableTry = new JLabel("Your last try :  " + String.valueOf(localDate) + "   " + sdf.format(new Date(System.currentTimeMillis())));

        timePanel.add(lableTry, BorderLayout.NORTH);

        professorTabbedPane.addTab("Home", timePanel);

    }

    /**
     * @param content professor profile
     */
    private void initmyProfile(String content){
        JTextArea existPanel = new JTextArea();
        existPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        existPanel.setText(content);
        professorTabbedPane.addTab("my profile", existPanel);
    }

    /**
     * Change username and password of professor
     */
    private void changeUserPassgui(){
        JPanel changeuname = new JPanel(new BorderLayout(10, 10));
        //changeuname.setLayout(new GridLayout(3,1, 200,0));
        changeuname.setBorder(new EmptyBorder(10, 10, 10, 10));

        JLabel label = new JLabel(" write your new username and password somewhere so you don't forget! ");
        label.setBackground(Color.RED);
        label.setHorizontalAlignment(SwingConstants.CENTER);
        label.setOpaque(true);
        Border border = BorderFactory.createLineBorder(Color.BLACK, 2);
        label.setBorder(border);
        label.setPreferredSize(new Dimension(500, 100));


        JLabel unameLabel = new JLabel(" new username : ");

        JTextField unameField = new JTextField();
        unameField.setPreferredSize(new Dimension(100, 10));

        JLabel passLabel = new JLabel("new password :");

        JTextField passField = new JTextField();
        passField.setPreferredSize(new Dimension(100, 10));

        JPanel userPanel = new JPanel(new GridLayout(2,2, 50, 5));
        //userPanel.setMaximumSize(new Dimension(1000, 200));
        userPanel.setPreferredSize(new Dimension(100, 200));
        userPanel.add(unameLabel);
        userPanel.add(unameField);
        userPanel.add(passLabel);
        userPanel.add(passField);
        JButton setUname = new JButton("set new username and password");
        setUname.setBackground(Color.cyan);
        setUname.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                String username = unameField.getText();
                String password = passField.getText();
                DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy_MM_dd");
                LocalDateTime now = LocalDateTime.now();
                ProfessorModel professorModel = new ProfessorModel(username, password, dtf.format(now));
                if (!(username.isEmpty() & password.isEmpty())){
                    ProfessorFile.objectFileWriter(professorModel);
                }
                //updatemyprofile();
            }
        });

        changeuname.add(label, BorderLayout.NORTH);
        changeuname.add(userPanel, BorderLayout.CENTER);
        changeuname.add(setUname, BorderLayout.SOUTH);

        professorTabbedPane.addTab("change username", changeuname);
    }

    /**
     * @param content profiles of professor's students
     */
    public void openExistingProfile(String content) {
        JTextArea existPanel = new JTextArea();
        existPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        existPanel.setText(content);
        int tabIndex = professorTabbedPane.getTabCount() + 1;
        professorTabbedPane.addTab("profile" + tabIndex, existPanel);
        professorTabbedPane.setSelectedIndex(tabIndex - 1);
    }


    /**
     * make a list of professor's classes files
     */
    private void initClasstudentList(ProfessorModel professorModel){
        File[] files = StudentFile.getFilterFiles(professorModel);
        classList = new JList<>(files);
        classList.setBackground(Color.GRAY);
        Border border = BorderFactory.createLineBorder(Color.cyan, 2);
        classList.setBorder(border);
        classList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        classList.setVisibleRowCount(-1);
        classList.setMaximumSize(new Dimension(130, 100));
        classList.setFixedCellWidth(130);
        classList.setCellRenderer(new MyCellRenderer());
        classList.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    int index = classList.locationToIndex(e.getPoint());
                    System.out.println("Item " + index + " is clicked...");
                    String content = ClassFile.objectFileReader(files[index]).toString();
                    System.out.println(content);
                    openExistingClass(content);
                }
            }
        });

        professorPanel.add(new JScrollPane(classList), BorderLayout.WEST);
    }

    /**
     * make a list of professor's students files
     */
    private void initStudentList(ProfessorModel professorModel){
        File[] files = ClassFile.getFilterProFiles(professorModel);
        studentList = new JList<>(files);
        studentList.setBackground(Color.GRAY);
        Border border = BorderFactory.createLineBorder(Color.cyan, 2);
        studentList.setBorder(border);
        studentList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        studentList.setVisibleRowCount(-1);
        studentList.setMaximumSize(new Dimension(130, 100));
        studentList.setFixedCellWidth(130);
        studentList.setCellRenderer(new MyCellRenderer());
        studentList.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    int index = studentList.locationToIndex(e.getPoint());
                    System.out.println("Item " + index + " is clicked...");
                    String content = StudentFile.objectFileReader(files[index]).toString();
                    System.out.println(content);
                    openExistingProfile(content);
                }
            }
        });

        professorPanel.add(new JScrollPane(studentList), BorderLayout.WEST);
    }

    /**
     * @param content information of class
     */
    public void openExistingClass(String content) {
        JTextArea existPanel = new JTextArea();
        existPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        existPanel.setText(content);
        int tabIndex = professorTabbedPane.getTabCount() + 1;
        professorTabbedPane.addTab("class" + tabIndex, existPanel);
        professorTabbedPane.setSelectedIndex(tabIndex - 1);
    }

    /**
     * GUI model for adding a class
     */
    public void setAddClassgui(){
        JPanel addClass = new JPanel(new BorderLayout(10, 10));
        //changeuname.setLayout(new GridLayout(3,1, 200,0));
        addClass.setBorder(new EmptyBorder(10, 10, 10, 10));

        JLabel label = new JLabel(" Enter information of new professor ");
        label.setBackground(Color.RED);
        label.setHorizontalAlignment(SwingConstants.CENTER);
        label.setOpaque(true);
        Border border = BorderFactory.createLineBorder(Color.BLACK, 2);
        label.setBorder(border);
        label.setPreferredSize(new Dimension(500, 100));


        JLabel nameLabel = new JLabel(" name : ");

        JTextField nameField = new JTextField();
        nameField.setPreferredSize(new Dimension(100, 10));

        JLabel unitLable = new JLabel("unit :");

        String units[] = {"1", "2", "3"};
        JComboBox unitBox = new JComboBox(units);
        unitBox.setBackground(Color.CYAN);
        unitBox.setBounds(50,50,50,50);

        JLabel timeLable = new JLabel("time :");

        String times[] = {"8-10", "10-12", "14-16"};
        JComboBox timeBox = new JComboBox(times);
        timeBox.setBackground(Color.CYAN);

        JLabel dayLable = new JLabel("day :");

        String days[] = {"Monday", "Tuesday", "Wednesday", "Friday"};
        JComboBox dayBox = new JComboBox(days);
        dayBox.setBackground(Color.CYAN);

        JLabel capacityLabel = new JLabel(" capacity : ");

        JTextField capacityField = new JTextField();
        capacityField.setPreferredSize(new Dimension(100, 10));

        JLabel unameLabel = new JLabel(" username :");

        JTextField userField = new JTextField();
        userField.setPreferredSize(new Dimension(100, 10));

        JLabel passLabel = new JLabel(" pass :");

        JTextField passField = new JTextField();
        passField.setPreferredSize(new Dimension(100, 10));

        JPanel classPanel = new JPanel(new GridLayout(7,2));
        classPanel.add(nameLabel);
        classPanel.add(nameField);
        classPanel.add(unitLable);
        classPanel.add(unitBox);
        classPanel.add(timeLable);
        classPanel.add(timeBox);
        classPanel.add(dayLable);
        classPanel.add(dayBox);
        classPanel.add(capacityLabel);
        classPanel.add(capacityField);
        classPanel.add(unameLabel);
        classPanel.add(userField);
        classPanel.add(passLabel);
        classPanel.add(passField);

        JButton setClass = new JButton("add class");
        setClass.setBackground(Color.cyan);
        setClass.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                String username = userField.getText();
                String password = passField.getText();
                DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy_MM_dd");
                LocalDateTime now = LocalDateTime.now();
                ProfessorModel professorModel = new ProfessorModel(username, password, dtf.format(now));
                if (!(username.isEmpty() & password.isEmpty())){
                    ProfessorFile.objectFileWriter(professorModel);
                }
                String name = nameField.getText();
                String unit = String.valueOf(unitBox.getSelectedItem());
                String time = String.valueOf(timeBox.getSelectedItem());
                String day = String.valueOf(dayBox.getSelectedItem());
                String capacity = capacityField.getText();
                ClassModel classModel = new ClassModel(name, unit, professorModel, time, day, capacity, dtf.format(now));
                ClassFile.objectFileWriter(classModel);
            }
        });

        addClass.add(label, BorderLayout.NORTH);
        addClass.add(classPanel, BorderLayout.CENTER);
        addClass.add(setClass, BorderLayout.SOUTH);

        professorTabbedPane.addTab("add class", addClass);
    }

    /**
     * GUI model for deleting a class
     */
    public void setDeleteClassgui(){
        JPanel addClass = new JPanel(new BorderLayout(10, 10));
        //changeuname.setLayout(new GridLayout(3,1, 200,0));
        addClass.setBorder(new EmptyBorder(10, 10, 10, 10));

        JLabel label = new JLabel(" Enter information of new professor ");
        label.setBackground(Color.RED);
        label.setHorizontalAlignment(SwingConstants.CENTER);
        label.setOpaque(true);
        Border border = BorderFactory.createLineBorder(Color.BLACK, 2);
        label.setBorder(border);
        label.setPreferredSize(new Dimension(500, 100));


        JLabel nameLabel = new JLabel(" name : ");

        JTextField nameField = new JTextField();
        nameField.setPreferredSize(new Dimension(100, 10));

        JLabel unitLable = new JLabel("unit :");

        String units[] = {"1", "2", "3"};
        JComboBox unitBox = new JComboBox(units);
        unitBox.setBackground(Color.CYAN);
        unitBox.setBounds(50,50,50,50);

        JLabel timeLable = new JLabel("time :");

        String times[] = {"8-10", "10-12", "14-16"};
        JComboBox timeBox = new JComboBox(times);
        timeBox.setBackground(Color.CYAN);

        JLabel dayLable = new JLabel("day :");

        String days[] = {"Monday", "Tuesday", "Wednesday", "Friday"};
        JComboBox dayBox = new JComboBox(days);
        dayBox.setBackground(Color.CYAN);

        JLabel capacityLabel = new JLabel(" capacity : ");

        JTextField capacityField = new JTextField();
        capacityField.setPreferredSize(new Dimension(100, 10));

        JLabel unameLabel = new JLabel(" username :");

        JTextField userField = new JTextField();
        userField.setPreferredSize(new Dimension(100, 10));

        JLabel passLabel = new JLabel(" pass :");

        JTextField passField = new JTextField();
        passField.setPreferredSize(new Dimension(100, 10));

        JPanel classPanel = new JPanel(new GridLayout(7,2));
        classPanel.add(nameLabel);
        classPanel.add(nameField);
        classPanel.add(unitLable);
        classPanel.add(unitBox);
        classPanel.add(timeLable);
        classPanel.add(timeBox);
        classPanel.add(dayLable);
        classPanel.add(dayBox);
        classPanel.add(capacityLabel);
        classPanel.add(capacityField);
        classPanel.add(unameLabel);
        classPanel.add(userField);
        classPanel.add(passLabel);
        classPanel.add(passField);

        JButton setClass = new JButton("delete class");
        setClass.setBackground(Color.cyan);
        setClass.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                String username = userField.getText();
                String password = passField.getText();
                DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy_MM_dd");
                LocalDateTime now = LocalDateTime.now();
                ProfessorModel professorModel = new ProfessorModel(username, password, dtf.format(now));
                if (!(username.isEmpty() & password.isEmpty())){
                    ProfessorFile.objectFileWriter(professorModel);
                }
                String name = nameField.getText();
                String unit = String.valueOf(unitBox.getSelectedItem());
                String time = String.valueOf(timeBox.getSelectedItem());
                String day = String.valueOf(dayBox.getSelectedItem());
                String capacity = capacityField.getText();
                ClassModel classModel = new ClassModel(name, unit, professorModel, time, day, capacity, dtf.format(now));
                ClassFile.deleteFile(classModel);
            }
        });

        addClass.add(label, BorderLayout.NORTH);
        addClass.add(classPanel, BorderLayout.CENTER);
        addClass.add(setClass, BorderLayout.SOUTH);

        professorTabbedPane.addTab("add class", addClass);
    }
    /**
     * @param content of classnotes
     */
    public void openExistingNote(String content) {
        JTextArea existPanel = new JTextArea();
        existPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        existPanel.setText(content);
        int tabIndex = professorTabbedPane.getTabCount() + 1;
        professorTabbedPane.addTab("note" + tabIndex, existPanel);
        professorTabbedPane.setSelectedIndex(tabIndex - 1);
    }

    /**
     * make a list of notes
     */
    private void initNoteList(){
        File[] files = NoteFile.getFilesInDirectory();
        noteList = new JList<>(files);
        noteList.setBackground(Color.GRAY);
        Border border = BorderFactory.createLineBorder(Color.YELLOW, 2);
        noteList.setBorder(border);
        noteList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        noteList.setVisibleRowCount(-1);
        noteList.setMaximumSize(new Dimension(130, 100));
        noteList.setFixedCellWidth(130);
        noteList.setCellRenderer(new MyCellRenderer());
        noteList.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    int index = noteList.locationToIndex(e.getPoint());
                    System.out.println("Item " + index + " is clicked...");
                    String content = NoteFile.objectFileReader(files[index]).toString();
                    System.out.println(content);
                    openExistingNote(content);
                }
            }
        });

        professorPanel.add(new JScrollPane(noteList), BorderLayout.WEST);
    }




   /* private class MenuHandler implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource().equals(myProfile)) {
                DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy_MM_dd");
                LocalDateTime now = LocalDateTime.now();
                File[] professor = AdminFile.getAdminFilesInDirectory();
                String content = AdminFile.objectFileReader(professor[0]).toString();
                initmyProfile(content);
            } else if (e.getSource().equals(changeUserPass)) {
                changeUserPassgui();
            } else if (e.getSource().equals(addStudent)) {
                addStudentgui();
            } else if (e.getSource().equals(studentsProfiles)) {
                JTextArea textPanel = new JTextArea();
                textPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
                adminTabbedPane.addTab("Tab ", textPanel);
                initStudentList();
            } else if (e.getSource().equals(addProfessor)) {
                addProfessorgui();
            }else if (e.getSource().equals(professorsProfiles)) {
                // initTabbedPane();
                JTextArea textPanel = new JTextArea();
                textPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
                adminTabbedPane.addTab("Tab ", textPanel);
                initProfessorList();
            }else if (e.getSource().equals(classes)) {
                initTabbedPane();
                initClassList();
            }else if (e.getSource().equals(foodSchedule)) {
                //initTabbedPane();
                setFoodSchedulegui();
            }else if (e.getSource().equals(exit)) {
                System.exit(0);
            } else {
                System.out.println("Nothing detected...");
            }
        }
    }*/

    private class MyCellRenderer extends DefaultListCellRenderer {

        @Override
        public Component getListCellRendererComponent(JList list, Object object, int index, boolean isSelected, boolean cellHasFocus) {
            if (object instanceof File) {
                File file = (File) object;
                setText(file.getName());
                setEnabled(list.isEnabled());
            }
            return this;
        }
    }

}
