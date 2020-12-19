package com.company.view;

import com.company.model.AdminModel;
import com.company.model.ProfessorModel;
import com.company.model.StudentModel;
import com.company.utils.*;
import javafx.scene.control.cell.CheckBoxListCell;

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

public class StudentView {
    private JFrame studentJframe;
    private JMenuBar studentjMenuBar;

    private JMenu myProfile;

    private JMenu setting;
    private JMenuItem changeUserPass;

    private JMenu weeklySchedule;

    private JMenu classes;
    private JMenuItem myClasses;
    private JMenuItem addClass;
    private JMenuItem deleteClass;

    private JMenu food;
    private JMenuItem foodreservation;
    private JMenuItem balance;

    private JMenu classNotes;

    private JMenu exit;


    private  JPanel studentPanel;
    private JTabbedPane studentTabbedPane;
    private JList<File> foodList;
    private JList<File> classList;
    private JList<File> noteList;

    public StudentView(){
        this.studentJframe = new JFrame();
        studentJframe.setTitle("Student");
        studentJframe.setSize(1000, 1000);
        studentJframe.setLocation(10, 10);
        studentJframe.setLayout(null);
        studentJframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        this.studentjMenuBar = new JMenuBar();
        studentjMenuBar.setPreferredSize(new Dimension(1000, 50));
        studentJframe.setJMenuBar(studentjMenuBar);
        studentjMenuBar.setOpaque(true);
        studentjMenuBar.setBackground(Color.PINK);

        this.myProfile = new JMenu("my profile");
        studentjMenuBar.add(myProfile);

        this.setting = new JMenu("setting");
        studentjMenuBar.add(setting);
        this.changeUserPass = new JMenuItem("change username and password");
        changeUserPass.setBackground(Color.PINK);
        setting.add(changeUserPass);

        this.food = new JMenu("food");
        studentjMenuBar.add(food);
        this.foodreservation = new JMenuItem("food reservation");
        this.balance = new JMenuItem("balance");
        foodreservation.setBackground(Color.pink);
        balance.setBackground(Color.pink);
        food.add(foodreservation);
        food.add(balance);


        this.weeklySchedule = new JMenu("weekly schedule");
        studentjMenuBar.add(weeklySchedule);

        this.classes = new JMenu("classes");
        studentjMenuBar.add(classes);
        this.addClass = new JMenuItem("add class");
        this.myClasses = new JMenuItem("my classes");
        this.deleteClass = new JMenuItem("delete class");
        addClass.setBackground(Color.pink);
        myClasses.setBackground(Color.pink);
        deleteClass.setBackground(Color.pink);
        classes.add(addClass);
        classes.add(deleteClass);
        classes.add(myClasses);

        this.classNotes = new JMenu("class notes");
        studentjMenuBar.add(classNotes);


        this.exit = new JMenu("Exit");
        studentjMenuBar.add(exit);


        this.studentPanel = new JPanel();
        studentPanel.setLayout(new BorderLayout());
        studentPanel.setBounds(0, 0, 1000, 900);
        //adminPanel.setBackground(Color.GRAY);
        studentJframe.add(studentPanel);

        initTabbedPane();
        initPage();
        //studentJframe.pack();
        studentJframe.setVisible(true);
    }

    /**
     * add tabbedpane to panel
     */
    private void initTabbedPane(){
         studentTabbedPane= new JTabbedPane();
        studentPanel.add(studentTabbedPane, BorderLayout.CENTER);
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

        studentTabbedPane.addTab("Home", timePanel);

    }
    /**
     * @param content student profile
     */
    private void initmyProfile(String content){
        JTextArea existPanel = new JTextArea();
        existPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        existPanel.setText(content);
        studentTabbedPane.addTab("my profile", existPanel);
    }
    /**
     * Change username and password of student
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
        setUname.setBackground(Color.pink);
        setUname.addMouseListener(new MouseAdapter() {
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
                //updatemyprofile();
            }
        });

        changeuname.add(label, BorderLayout.NORTH);
        changeuname.add(userPanel, BorderLayout.CENTER);
        changeuname.add(setUname, BorderLayout.SOUTH);
        studentTabbedPane.addTab("change username", changeuname);
    }

    /**
     * set balance of students account
     */
    private void setBalance(){
        JPanel addcash = new JPanel(new BorderLayout(10, 10));
        //changeuname.setLayout(new GridLayout(3,1, 200,0));
        addcash.setBorder(new EmptyBorder(10, 10, 10, 10));

        JLabel label = new JLabel(" Increase your account balance ");
        label.setBackground(Color.RED);
        label.setHorizontalAlignment(SwingConstants.CENTER);
        label.setOpaque(true);
        Border border = BorderFactory.createLineBorder(Color.BLACK, 2);
        label.setBorder(border);
        label.setPreferredSize(new Dimension(500, 100));

        JLabel cardLable = new JLabel("card number :");

        JPasswordField cardField = new JPasswordField();
        cardField.setPreferredSize(new Dimension(100, 10));

        JLabel amountLable = new JLabel("amount :");

        JTextField amountField = new JTextField();
        amountField.setPreferredSize(new Dimension(100, 10));

        JLabel unameLabel = new JLabel(" username : ");

        JTextField unameField = new JTextField();
        unameField.setPreferredSize(new Dimension(100, 10));

        JLabel passLabel = new JLabel(" password :");

        JPasswordField passField = new JPasswordField();
        passField.setPreferredSize(new Dimension(100, 10));

        JPanel userPanel = new JPanel(new GridLayout(4,2));
        userPanel.add(cardLable);
        userPanel.add(cardField);
        userPanel.add(amountLable);
        userPanel.add(amountField);
        userPanel.add(unameLabel);
        userPanel.add(unameField);
        userPanel.add(passLabel);
        userPanel.add(passField);
        JButton setUname = new JButton("increase account balance");
        setUname.setBackground(Color.pink);
        setUname.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                String username = unameField.getText();
                String password = passField.getText();
                DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy_MM_dd");
                LocalDateTime now = LocalDateTime.now();
                StudentModel studentModel = new StudentModel(username, password, dtf.format(now));
                int balance = Integer.parseInt(studentModel.getBalance());
                int newBalance = balance + Integer.parseInt(amountField.getText());
                studentModel.setBalance(String.valueOf(newBalance));
                if (!(username.isEmpty() & password.isEmpty())){
                    StudentFile.objectFileWriter(studentModel);
                }
            }
        });

        addcash.add(label, BorderLayout.NORTH);
        addcash.add(userPanel, BorderLayout.CENTER);
        addcash.add(setUname, BorderLayout.SOUTH);

        studentTabbedPane.addTab("increase amount", addcash);

    }

    /**
     * this method helps us to choose class
     */
    private void initClassChooser(){
        File[] files = ClassFile.getFilesInDirectory();
        DefaultListModel model = new DefaultListModel();
        classList = new JList<>(model);
        for (int x = 0; x < files.length; x++) {
            String myclass = ClassFile.objectFileReader(files[x]).toString();
            JCheckBox jCheckBox = new JCheckBox(myclass);
            //model.addElement(new CheckBoxListCell<String>(myclass ));
        }
        classList.setBackground(Color.GRAY);
        Border border = BorderFactory.createLineBorder(Color.pink, 5);
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
                }
            }
        });
        studentPanel.add(new JScrollPane(classList), BorderLayout.WEST);
    }

    /**
     * this method helps us to reserve food
     */
    private void initFoodChooser(){
        File[] files = FoodFile.getFilesInDirectory();
        DefaultListModel model = new DefaultListModel();
        foodList = new JList(model);
        for (int x = 0; x < files.length; x++) {
            String myclass = FoodFile.objectFileReader(files[x]).toString();
            JCheckBox jCheckBox = new JCheckBox(myclass);
            //model.addElement(new CheckBoxListCell<String>(jCheckBox + x));
        }
        foodList.setBackground(Color.GRAY);
        Border border = BorderFactory.createLineBorder(Color.pink, 5);
        foodList.setBorder(border);
        foodList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        foodList.setVisibleRowCount(-1);
        foodList.setMaximumSize(new Dimension(130, 100));
        foodList.setFixedCellWidth(130);
        foodList.setCellRenderer(new MyCellRenderer());
        foodList.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    int index = foodList.locationToIndex(e.getPoint());
                    System.out.println("Item " + index + " is clicked...");
                    String content = ClassFile.objectFileReader(files[index]).toString();
                    System.out.println(content);
                }
            }
        });
        studentPanel.add(new JScrollPane(foodList), BorderLayout.WEST);
    }

    /**
     * @param content of classnotes
     */
    public void openExistingNote(String content) {
        JTextArea existPanel = new JTextArea();
        existPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        existPanel.setText(content);
        int tabIndex = studentTabbedPane.getTabCount() + 1;
        studentTabbedPane.addTab("note" + tabIndex, existPanel);
        studentTabbedPane.setSelectedIndex(tabIndex - 1);
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

        studentPanel.add(new JScrollPane(noteList), BorderLayout.WEST);
    }


    /**
     * This class holds action events of menuitmes
     */
   /* private class MenuHandler implements ActionListener {
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
    }*/

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

