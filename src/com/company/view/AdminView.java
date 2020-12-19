package com.company.view;

import com.company.LogInForm;
import com.company.model.AdminModel;
import com.company.model.FoodModel;
import com.company.model.ProfessorModel;
import com.company.model.StudentModel;
import com.company.utils.*;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileSystemView;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.Timer;

/**
 * This class makes admin frame
 * @author Melika
 * @since 2020
 */
public class AdminView {

    private JFrame adminJframe;
    private JMenuBar adminjMenuBar;

    private JMenu myProfile;

    private JMenu setting;
    private JMenuItem changeUserPass;

    private JMenu students;
    private JMenuItem addStudent;
    private JMenuItem studentsProfiles;

    private JMenu professors;
    private JMenuItem addProfessor;
    private JMenuItem professorsProfiles;

    private JMenu classes;

    private JMenu foodSchedule;

    private JMenu exit;


    private  JPanel adminPanel;
    private JTabbedPane adminTabbedPane;
    private JList<File> studentList;
    private JList<File> professorList;
    private JList<File> classList;

    public AdminView(){
        this.adminJframe = new JFrame();
        adminJframe.setTitle("Admin");
        adminJframe.setSize(1000, 1000);
        adminJframe.setLocation(10, 10);
        adminJframe.setLayout(null);
        adminJframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        this.adminjMenuBar = new JMenuBar();
        adminjMenuBar.setPreferredSize(new Dimension(1000, 50));
        adminJframe.setJMenuBar(adminjMenuBar);
        adminjMenuBar.setOpaque(true);
        adminjMenuBar.setBackground(Color.YELLOW);

        this.myProfile = new JMenu("my profile");
        adminjMenuBar.add(myProfile);
        myProfile.addActionListener(new MenuHandler());
        myProfile.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy_MM_dd");
                LocalDateTime now = LocalDateTime.now();
                AdminModel adminModel = new AdminModel("admin","admin", dtf.format(now));
                AdminFile adminFile = new AdminFile();
                File[] admin = AdminFile.getAdminFilesInDirectory();
                String content = AdminFile.objectFileReader(admin[0]).toString();
                initmyProfile(content);
            }
        });

        this.setting = new JMenu("setting");
        adminjMenuBar.add(setting);
        this.changeUserPass = new JMenuItem("change username and password");
        changeUserPass.setBackground(Color.yellow);
        setting.add(changeUserPass);
        changeUserPass.addActionListener(new MenuHandler());


        this.students = new JMenu("students");
        adminjMenuBar.add(students);
        this.addStudent = new JMenuItem("add student");
        this.studentsProfiles = new JMenuItem("students profiles");
        addStudent.setBackground(Color.yellow);
        studentsProfiles.setBackground(Color.yellow);
        students.add(addStudent);
        students.add(studentsProfiles);
        addStudent.addActionListener(new MenuHandler());
        studentsProfiles.addActionListener(new MenuHandler());

        this.professors = new JMenu("professors");
        adminjMenuBar.add(professors);
        this.addProfessor = new JMenuItem("add professor");
        this.professorsProfiles = new JMenuItem("professors profiles");
        addProfessor.setBackground(Color.yellow);
        professorsProfiles.setBackground(Color.yellow);
        professors.add(addProfessor);
        professors.add(professorsProfiles);
        addProfessor.addActionListener(new MenuHandler());
        professorsProfiles.addActionListener(new MenuHandler());

        this.classes = new JMenu("classes");
        adminjMenuBar.add(classes);
        classes.addActionListener(new MenuHandler());

        this.foodSchedule = new JMenu("food schedule");
        adminjMenuBar.add(foodSchedule);
        foodSchedule.addActionListener(new MenuHandler());
        foodSchedule.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                setFoodSchedulegui();
            }
        });

        this.exit = new JMenu("Exit");
        adminjMenuBar.add(exit);
        exit.addActionListener(new MenuHandler());
        exit.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                System.exit(0);
            }
        });


        this.adminPanel = new JPanel();
        adminPanel.setLayout(new BorderLayout());
        adminPanel.setBounds(0, 0, 1000, 900);
        //adminPanel.setBackground(Color.GRAY);
        adminJframe.add(adminPanel);

        initTabbedPane();
        initPage();
        adminJframe.setVisible(true);
    }

    /**
     * add tabbedpane to panel
     */
    private void initTabbedPane(){
        adminTabbedPane = new JTabbedPane();
        adminPanel.add(adminTabbedPane, BorderLayout.CENTER);
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

        adminTabbedPane.addTab("Home", timePanel);

    }


    /**
     * @param content admin profile
     */
    private void initmyProfile(String content){
        JTextArea existPanel = new JTextArea();
        existPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        existPanel.setText(content);
        adminTabbedPane.addTab("my profile", existPanel);
    }

    /**
     * Change username and password of admin
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
        setUname.setBackground(Color.yellow);
        setUname.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                String username = unameField.getText();
                String password = passField.getText();
                DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy_MM_dd");
                LocalDateTime now = LocalDateTime.now();
                AdminModel adminModel = new AdminModel(username, password, dtf.format(now));
                if (!(username.isEmpty() & password.isEmpty())){
                    AdminFile.objectFileWriter(adminModel);
                }
                //updatemyprofile();
            }
        });

        changeuname.add(label, BorderLayout.NORTH);
        changeuname.add(userPanel, BorderLayout.CENTER);
        changeuname.add(setUname, BorderLayout.SOUTH);

        adminTabbedPane.addTab("change username", changeuname);
    }


    /**
     * @param content of students or professors
     */
    public void openExistingProfile(String content) {
        JTextArea existPanel = new JTextArea();
        existPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        existPanel.setText(content);
        int tabIndex = adminTabbedPane.getTabCount() + 1;
        adminTabbedPane.addTab("profile" + tabIndex, existPanel);
        adminTabbedPane.setSelectedIndex(tabIndex - 1);
    }

    /**
     * make a list of student files
     */
    private void initStudentList(){
        File[] files = StudentFile.getFilesInDirectory();
        studentList = new JList<>(files);
        studentList.setBackground(Color.GRAY);
        Border border = BorderFactory.createLineBorder(Color.YELLOW, 2);
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

        adminPanel.add(new JScrollPane(studentList), BorderLayout.WEST);
    }

    /**
     * update list of students
     */
    private void updateStudentList() {
        File[] newFiles = StudentFile.getFilesInDirectory();
        studentList.setListData(newFiles);
    }

    /**
     * GUI for adding a new Student
     */
    private void addStudentgui(){
        JPanel addstu = new JPanel(new BorderLayout(10, 10));
        //changeuname.setLayout(new GridLayout(3,1, 200,0));
        addstu.setBorder(new EmptyBorder(10, 10, 10, 10));

        JLabel label = new JLabel(" Enter username and password of new student ");
        label.setBackground(Color.RED);
        label.setHorizontalAlignment(SwingConstants.CENTER);
        label.setOpaque(true);
        Border border = BorderFactory.createLineBorder(Color.BLACK, 2);
        label.setBorder(border);
        label.setPreferredSize(new Dimension(500, 100));


        JLabel unameLabel = new JLabel(" username : ");

        JTextField unameField = new JTextField();
        unameField.setPreferredSize(new Dimension(100, 10));

        JLabel passLabel = new JLabel(" password :");

        JTextField passField = new JTextField();
        passField.setPreferredSize(new Dimension(100, 10));

        JPanel userPanel = new JPanel(new GridLayout(2,2));
        userPanel.add(unameLabel);
        userPanel.add(unameField);
        userPanel.add(passLabel);
        userPanel.add(passField);
        JButton addStudentButton = new JButton("add student");
        addStudentButton.setBackground(Color.yellow);
        addStudentButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                String username = unameField.getText();
                String password = passField.getText();
                DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy_MM_dd");
                LocalDateTime now = LocalDateTime.now();
                StudentModel studentModel = new StudentModel(username, password, dtf.format(now));
                if (!(username.isEmpty() & password.isEmpty())){
                    StudentFile.objectFileWriter(studentModel);
                }
                updateStudentList();
            }
        });


        addstu.add(label, BorderLayout.NORTH);
        addstu.add(userPanel, BorderLayout.CENTER);
        addstu.add(addStudentButton, BorderLayout.SOUTH);

        adminTabbedPane.addTab("add student", addstu);
    }


    /**
     * Create a list of professors
     */
    private void initProfessorList(){
        File[] files = ProfessorFile.getFilesInDirectory();
        professorList = new JList<>(files);
        professorList.setBackground(Color.GRAY);
        Border border = BorderFactory.createLineBorder(Color.YELLOW, 5);
        professorList.setBorder(border);
        professorList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        professorList.setVisibleRowCount(-1);
        professorList.setMaximumSize(new Dimension(130, 100));
        professorList.setFixedCellWidth(130);
        professorList.setCellRenderer(new MyCellRenderer());
        professorList.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    int index = professorList.locationToIndex(e.getPoint());
                    System.out.println("Item " + index + " is clicked...");
                    String content = ProfessorFile.objectFileReader(files[index]).toString();
                    System.out.println(content);
                    openExistingProfile(content);
                }
            }
        });
        adminPanel.add(new JScrollPane(professorList), BorderLayout.WEST);
    }

    /**
     * update list of professors
     */
    private void updateProfessorList() {
        File[] newFiles = ProfessorFile.getFilesInDirectory();
        professorList.setListData(newFiles);
    }

    /**
     * GUI for adding a new professor
     */
    private void addProfessorgui(){
        JPanel addpfr = new JPanel(new BorderLayout(10, 10));
        //changeuname.setLayout(new GridLayout(3,1, 200,0));
        addpfr.setBorder(new EmptyBorder(10, 10, 10, 10));

        JLabel label = new JLabel(" Enter username and password of new professor ");
        label.setBackground(Color.RED);
        label.setHorizontalAlignment(SwingConstants.CENTER);
        label.setOpaque(true);
        Border border = BorderFactory.createLineBorder(Color.BLACK, 2);
        label.setBorder(border);
        label.setPreferredSize(new Dimension(500, 100));


        JLabel unameLabel = new JLabel(" username : ");

        JTextField unameField = new JTextField();
        unameField.setPreferredSize(new Dimension(100, 10));

        JLabel passLabel = new JLabel(" password :");

        JTextField passField = new JTextField();
        passField.setPreferredSize(new Dimension(100, 10));

        JPanel userPanel = new JPanel(new GridLayout(2,2));
        userPanel.add(unameLabel);
        userPanel.add(unameField);
        userPanel.add(passLabel);
        userPanel.add(passField);
        JButton setUname = new JButton("add professor");
        setUname.setBackground(Color.yellow);
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
                updateStudentList();
            }
        });

        addpfr.add(label, BorderLayout.NORTH);
        addpfr.add(userPanel, BorderLayout.CENTER);
        addpfr.add(setUname, BorderLayout.SOUTH);

        adminTabbedPane.addTab("add professor", addpfr);
    }

    /**
     * create a list of classes
     */
    private void initClassList(){
        File[] files = ClassFile.getFilesInDirectory();
         classList = new JList<>(files);
        classList.setBackground(Color.GRAY);
        Border border = BorderFactory.createLineBorder(Color.YELLOW, 5);
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
        adminPanel.add(new JScrollPane(classList), BorderLayout.WEST);
    }

    /**
     * @param content information of class
     */
    public void openExistingClass(String content) {
        JTextArea existPanel = new JTextArea();
        existPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        existPanel.setText(content);
        int tabIndex = adminTabbedPane.getTabCount() + 1;
        adminTabbedPane.addTab("class" + tabIndex, existPanel);
        adminTabbedPane.setSelectedIndex(tabIndex - 1);
    }

    /**
     * GUI model for adding foods and their informations to the schedule
     */
    private void setFoodSchedulegui(){
        JPanel addFood = new JPanel(new BorderLayout(10, 10));
        //changeuname.setLayout(new GridLayout(3,1, 200,0));
        addFood.setBorder(new EmptyBorder(10, 10, 10, 10));

        JLabel label = new JLabel(" Enter name and price of food ");
        label.setBackground(Color.RED);
        label.setHorizontalAlignment(SwingConstants.CENTER);
        label.setOpaque(true);
        Border border = BorderFactory.createLineBorder(Color.BLACK, 2);
        label.setBorder(border);
        label.setPreferredSize(new Dimension(500, 100));


        JLabel monLabel = new JLabel(" Monday : ");

        JTextField monField = new JTextField();
        monField.setPreferredSize(new Dimension(50, 10));

        JTextField monPrice = new JTextField();
        monPrice.setPreferredSize(new Dimension(50, 10));

        JButton setMon = new JButton("set");
        setMon.setBackground(Color.yellow);
        setMon.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                String name = monField.getText();
                String cost = monPrice.getText();
                DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy_MM_dd");
                LocalDateTime now = LocalDateTime.now();
                FoodModel foodModel = new FoodModel(name,"Monday", cost, dtf.format(now));
                if (!(name.isEmpty() & cost.isEmpty())){
                    FoodFile.objectFileWriter(foodModel);
                }
            }
        });


        JLabel tuesLabel = new JLabel(" Tuesday :");

        JTextField tuesField = new JTextField();
        tuesField.setPreferredSize(new Dimension(50, 10));

        JTextField tuesPrice = new JTextField();
        tuesPrice.setPreferredSize(new Dimension(50, 10));

        JButton setTue = new JButton("set");
        setTue.setBackground(Color.yellow);
        setTue.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                String name = tuesField.getText();
                String cost = tuesPrice.getText();
                DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy_MM_dd");
                LocalDateTime now = LocalDateTime.now();
                FoodModel foodModel = new FoodModel(name,"Tuesday", cost, dtf.format(now));
                if (!(name.isEmpty() & cost.isEmpty())){
                    FoodFile.objectFileWriter(foodModel);
                }
            }
        });

        JLabel wednLabel = new JLabel(" Wednesday :");

        JTextField wednField = new JTextField();
        wednField.setPreferredSize(new Dimension(100, 10));

        JTextField wednPrice = new JTextField();
        wednPrice.setPreferredSize(new Dimension(50, 10));

        JButton setWedn = new JButton("set");
        setWedn.setBackground(Color.yellow);
        setWedn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                String name = wednField.getText();
                String cost = wednPrice.getText();
                DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy_MM_dd");
                LocalDateTime now = LocalDateTime.now();
                FoodModel foodModel = new FoodModel(name,"Wednesday", cost, dtf.format(now));
                if (!(name.isEmpty() & cost.isEmpty())){
                    FoodFile.objectFileWriter(foodModel);
                }
            }
        });

        JLabel thurLabel = new JLabel(" Thursday :");

        JTextField thurField = new JTextField();
        thurField.setPreferredSize(new Dimension(50, 10));

        JTextField thurPrice = new JTextField();
        thurPrice.setPreferredSize(new Dimension(50, 10));

        JButton setThur = new JButton("set");
        setThur.setBackground(Color.yellow);
        setThur.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                String name = thurField.getText();
                String cost = thurPrice.getText();
                DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy_MM_dd");
                LocalDateTime now = LocalDateTime.now();
                FoodModel foodModel = new FoodModel(name,"Thursday", cost, dtf.format(now));
                if (!(name.isEmpty() & cost.isEmpty())){
                    FoodFile.objectFileWriter(foodModel);
                }
            }
        });

        JLabel friLabel = new JLabel(" Friday :");

        JTextField friField = new JTextField();
        friField.setPreferredSize(new Dimension(50, 10));

        JTextField friPrice = new JTextField();
        friPrice.setPreferredSize(new Dimension(50, 10));


        JButton setFri = new JButton("set");
        setFri.setBackground(Color.yellow);
        setFri.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                String name = friField.getText();
                String cost = friPrice.getText();
                DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy_MM_dd");
                LocalDateTime now = LocalDateTime.now();
                FoodModel foodModel = new FoodModel(name,"Friday", cost, dtf.format(now));
                if (!(name.isEmpty() & cost.isEmpty())){
                    FoodFile.objectFileWriter(foodModel);
                }
            }
        });

        JPanel foodPanel = new JPanel(new GridLayout(5,4));
        foodPanel.add(monLabel);
        foodPanel.add(monField);
        foodPanel.add(monPrice);
        foodPanel.add(setMon);
        foodPanel.add(tuesLabel);
        foodPanel.add(tuesField);
        foodPanel.add(tuesPrice);
        foodPanel.add(setTue);
        foodPanel.add(wednLabel);
        foodPanel.add(wednField);
        foodPanel.add(wednPrice);
        foodPanel.add(setWedn);
        foodPanel.add(thurLabel);
        foodPanel.add(thurField);
        foodPanel.add(thurPrice);
        foodPanel.add(setThur);
        foodPanel.add(friLabel);
        foodPanel.add(friField);
        foodPanel.add(friPrice);
        foodPanel.add(setFri);


        addFood.add(label, BorderLayout.NORTH);
        addFood.add(foodPanel, BorderLayout.CENTER);

        adminTabbedPane.add("food schedule", addFood);
    }

    /**
     * This class holds action events of menuitmes
     */
    private class MenuHandler implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource().equals(myProfile)) {
                DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy_MM_dd");
                LocalDateTime now = LocalDateTime.now();
                AdminModel adminModel = new AdminModel("admin","admin", dtf.format(now));
                AdminFile adminFile = new AdminFile();
                File[] admin = AdminFile.getAdminFilesInDirectory();
                String content = AdminFile.objectFileReader(admin[0]).toString();
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
    }

    /**
     * This class helps to list files
     */
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
