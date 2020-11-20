package com.company;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import javax.swing.border.MatteBorder;


public class SignUpScreen extends JFrame {

    private JPanel contentPane;
    private JPanel startPanel;
    private JRadioButton student;
    private JRadioButton faculty;
    private JLayeredPane layeredPane;
    private JPanel studentPanel;
    private JPanel facultyPanel;
    private JLabel studName;
    private JLabel rollno;
    private JLabel studPswd;
    private JTextField Studentname;
    private JTextField studentRollno;
    private JPasswordField passwordStudent;
    private JLabel facName;
    private JLabel facMail;
    private JLabel facPswd;
    private JTextField facultyName;
    private JTextField facultyMail;
    private JPasswordField passwordFaculty;
    private String nameOfStudent;
    private String rollNoOfStudent;
    private String pswdStudent;
    private String nameOfFaculty;
    private String emailFaculty;
    private String pswdFaculty;
    private JTextField studentMessage;
    private JTextField facultyMessage;
    private JCheckBox isTutorSelector;
    private JComboBox subjectSelector;


    //Function to switch panels. between faculty and student
    public void switchpanel(JPanel panel) {
        layeredPane.removeAll();
        layeredPane.add(panel);
        layeredPane.repaint();
        layeredPane.revalidate();
    }

    /**
     * Create the frame.
     */
    public SignUpScreen() {
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 684, 468);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);


        //Welcome Page
        startPanel = new JPanel();
        startPanel.setBorder(null);
        startPanel.setBackground(new Color(0,0,0,200));
        startPanel.setBounds(0, 0, 304, 429);
        contentPane.add(startPanel);
        startPanel.setLayout(null);

        JLabel welcome = new JLabel("Welcome ");
        welcome.setFont(new Font("Showcard Gothic", Font.BOLD, 22));
        welcome.setForeground(new Color(65, 105, 225));
        welcome.setBounds(83, 155, 153, 55);
        startPanel.add(welcome);

        JLabel to = new JLabel("To");
        to.setForeground(new Color(65, 105, 225));
        to.setFont(new Font("Showcard Gothic", Font.PLAIN, 22));
        to.setBounds(121, 200, 65, 36);
        startPanel.add(to);

        JLabel atende = new JLabel("Attendee!");
        atende.setForeground(new Color(65, 105, 225));
        atende.setFont(new Font("Showcard Gothic", Font.PLAIN, 22));
        atende.setBounds(83, 233, 121, 36);
        startPanel.add(atende);


        //Radio buttons to choose between Student and Faculty.
        student = new JRadioButton("Student");
        student.setForeground(new Color(255, 255, 255));
        student.setSelected(true);

        student.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if(student.isSelected()) {
                    faculty.setSelected(false);
                    switchpanel(studentPanel);
                }
            }
        });
        student.setFont(new Font("Tahoma", Font.BOLD, 12));
        student.setBounds(6, 347, 109, 23);
        student.setOpaque(false);
        startPanel.add(student);

        faculty = new JRadioButton("Faculty");
        faculty.setForeground(new Color(255, 255, 255));
        faculty.setOpaque(false);

        //display faculty panel panel when pressed.
        faculty.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if(faculty.isSelected()) {
                    student.setSelected(false);
                    switchpanel(facultyPanel);
                }
            }
        });
        faculty.setFont(new Font("Tahoma", Font.BOLD, 12));
        faculty.setBounds(6, 373, 109, 23);
        faculty.setOpaque(false);
        startPanel.add(faculty);



        //For multiple panels
        layeredPane = new JLayeredPane();
        layeredPane.setBounds(304, 0, 364, 429);
        contentPane.add(layeredPane);
        layeredPane.setLayout(new CardLayout(0, 0));


        //Student Sign-Up Page
        studentPanel = new JPanel();
        studentPanel.setBorder(new MatteBorder(5, 5, 5, 5, (Color) new Color(0, 0, 0)));
        studentPanel.setBackground(new Color(20, 18, 18));
        layeredPane.add(studentPanel, "name_400247342847400");
        studentPanel.setLayout(null);

        studName = new JLabel("Name:");
        studName.setForeground(new Color(153, 50, 204));
        studName.setBackground(new Color(148, 0, 211));
        studName.setFont(new Font("Verdana", Font.BOLD, 11));
        studName.setBounds(40, 65, 72, 20);
        studentPanel.add(studName);


        //student name field
        Studentname = new JTextField();
        Studentname.setForeground(new Color(255, 255, 255));
        Studentname.setBackground(new Color(20, 18, 18));
        Studentname.setBounds(47, 90, 270, 20);
        Studentname.setBorder(null);
        studentPanel.add(Studentname);
        Studentname.setColumns(10);


        JSeparator separator = new JSeparator();
        separator.setBackground(new Color(138, 43, 226));
        separator.setForeground(new Color(0, 0, 0));
        separator.setBounds(47, 112, 270, 12);
        studentPanel.add(separator);

        rollno = new JLabel("Roll No:");
        rollno.setBackground(new Color(148, 0, 211));
        rollno.setForeground(new Color(153, 50, 204));
        rollno.setFont(new Font("Verdana", Font.BOLD, 11));
        rollno.setBounds(40, 147, 72, 20);
        studentPanel.add(rollno);

        //roll no field
        studentRollno = new JTextField();
        studentRollno.setForeground(new Color(255, 255, 255));
        studentRollno.setBackground(new Color(20, 18, 18));
        studentRollno.setBounds(47, 172, 270, 21);
        studentPanel.add(studentRollno);
        studentRollno.setColumns(10);
        studentRollno.setBorder(null);

        JSeparator separator_1 = new JSeparator();
        separator_1.setBackground(new Color(138, 43, 226));
        separator_1.setForeground(new Color(0, 0, 0));
        separator_1.setBounds(47, 195, 270, 14);
        studentPanel.add(separator_1);

        studPswd = new JLabel("Password:");
        studPswd.setForeground(new Color(153, 50, 204));
        studPswd.setFont(new Font("Verdana", Font.BOLD, 11));
        studPswd.setBounds(40, 220, 85, 20);
        studentPanel.add(studPswd);

        JSeparator separator_2 = new JSeparator();
        separator_2.setBackground(new Color(138, 43, 226));
        separator_2.setForeground(new Color(0, 0, 0));
        separator_2.setBounds(47, 265, 270, 31);
        studentPanel.add(separator_2);


        //student password field
        passwordStudent = new JPasswordField();
        passwordStudent.setForeground(new Color(255, 255, 255));
        passwordStudent.setBackground(new Color(20, 18, 18));
        passwordStudent.setBounds(47, 242, 270, 20);
        passwordStudent.setBorder(null);
        studentPanel.add(passwordStudent);


        //message box for student
        studentMessage = new JTextField();
        studentMessage.setFont(new Font("Microsoft JhengHei UI", Font.BOLD, 14));
        studentMessage.setForeground(new Color(194, 4, 42));
        studentMessage.setBackground(new Color(20, 18, 18));
        studentMessage.setBounds(10, 398, 344, 20);
        studentPanel.add(studentMessage);
        studentMessage.setColumns(10);
        studentMessage.setBorder(null);


        //Student signup button
        JButton letsGo = new JButton("Let's Go");
        letsGo.setForeground(new Color(0, 0, 0));
        letsGo.setBackground(new Color(224, 255, 255));
        letsGo.setFont(new Font("Microsoft New Tai Lue", Font.BOLD, 12));
        letsGo.setBounds(47, 307, 270, 23);
        letsGo.setBorder(null);

        //validation of student
        letsGo.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                nameOfStudent=Studentname.getText();
                rollNoOfStudent=studentRollno.getText();
                pswdStudent=String.valueOf(passwordStudent.getPassword());
                SignupController st=new SignupController(nameOfStudent,pswdStudent,rollNoOfStudent,'S');
                try {
                    User user = new User("ads@gmail.com",pswdStudent);
                    User.validatePassword(user);
                    st.storeData();
                    studentMessage.setForeground(new Color(0, 189, 76));
                    studentMessage.setText("Successful!!!");
                }
                catch(Exception e1) {
                    studentMessage.setText(e1.toString());
                }
            }
        });
        studentPanel.add(letsGo);


        //Faculty Sign-Up Page
        facultyPanel = new JPanel();
        facultyPanel.setBorder(new MatteBorder(5, 5, 5, 5, (Color) new Color(0, 0, 0)));
        facultyPanel.setBackground(new Color(20, 18, 18));
        layeredPane.add(facultyPanel, "name_400264219692300");
        facultyPanel.setLayout(null);

        facName = new JLabel("Name:");
        facName.setForeground(new Color(153, 50, 204));
        facName.setBackground(new Color(148, 0, 211));
        facName.setFont(new Font("Verdana", Font.BOLD, 11));
        facName.setBounds(40, 65, 72, 20);
        facultyPanel.add(facName);


        //name field for faculty
        facultyName = new JTextField();
        facultyName.setForeground(new Color(255, 255, 255));
        facultyName.setBackground(new Color(20, 18, 18));
        facultyName.setBounds(47, 90, 270, 20);
        facultyPanel.add(facultyName);
        facultyName.setColumns(10);
        facultyName.setBorder(null);

        JSeparator separator_3 = new JSeparator();
        separator_3.setBackground(new Color(138, 43, 226));
        separator_3.setForeground(new Color(0, 0, 0));
        separator_3.setBounds(47, 112, 270, 12);
        facultyPanel.add(separator_3);

        facMail = new JLabel("E-mail:");
        facMail.setBackground(new Color(148, 0, 211));
        facMail.setForeground(new Color(153, 50, 204));
        facMail.setFont(new Font("Verdana", Font.BOLD, 11));
        facMail.setBounds(40, 147, 72, 20);
        facultyPanel.add(facMail);


        //mail field for faculty
        facultyMail = new JTextField();
        facultyMail.setForeground(new Color(255, 255, 255));
        facultyMail.setBackground(new Color(20, 18, 18));
        facultyMail.setBounds(47, 172, 270, 21);
        facultyPanel.add(facultyMail);
        facultyMail.setColumns(10);
        facultyMail.setBorder(null);

        JSeparator separator_4 = new JSeparator();
        separator_4.setBackground(new Color(138, 43, 226));
        separator_4.setForeground(new Color(0, 0, 0));
        separator_4.setBounds(47, 195, 270, 14);
        facultyPanel.add(separator_4);

        facPswd = new JLabel("Password:");
        facPswd.setForeground(new Color(153, 50, 204));
        facPswd.setFont(new Font("Verdana", Font.BOLD, 11));
        facPswd.setBounds(40, 220, 85, 20);
        facultyPanel.add(facPswd);


        //password field for faculty
        passwordFaculty = new JPasswordField();
        passwordFaculty.setForeground(new Color(255, 255, 255));
        passwordFaculty.setBackground(new Color(20, 18, 18));
        passwordFaculty.setBounds(47, 242, 270, 20);
        passwordFaculty.setBorder(null);
        facultyPanel.add(passwordFaculty);

        JSeparator separator_5 = new JSeparator();
        separator_5.setBackground(new Color(138, 43, 226));
        separator_5.setForeground(new Color(0, 0, 0));
        separator_5.setBounds(47, 265, 270, 12);
        facultyPanel.add(separator_5);

        isTutorSelector = new JCheckBox("Tutor?");
        isTutorSelector.setFont(new Font("Verdana",Font.BOLD, 11));
        isTutorSelector.setForeground(new Color(70, 5, 181));
        isTutorSelector.setBackground(new Color(20, 18, 18));
        isTutorSelector.setBounds(47, 277, 97, 23);
        facultyPanel.add(isTutorSelector);

        subjectSelector = new JComboBox(StudentAttendance.subjects);
        subjectSelector.setFont(new Font("Verdana",Font.BOLD, 11));
        subjectSelector.setBounds(120, 277, 200,23);
        facultyPanel.add(subjectSelector);

        //Signup button
        JButton signUp = new JButton("Sign Up");
        signUp.setBackground(new Color(224, 255, 255));
        signUp.setFont(new Font("Microsoft Tai Le", Font.BOLD, 12));
        signUp.setBounds(47, 307, 270, 23);
        signUp.setBorder(null);
        facultyPanel.add(signUp);


        //Message-box for faculty
        facultyMessage = new JTextField();
        facultyMessage.setForeground(new Color(194, 4, 42));
        facultyMessage.setBackground(new Color(20, 18, 18));
        facultyMessage.setBounds(10, 384, 344, 34);
        facultyPanel.add(facultyMessage);
        facultyMessage.setColumns(10);
        facultyMessage.setBorder(null);


        //Validation of faculty
        signUp.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                nameOfFaculty=facultyName.getText();
                emailFaculty=facultyMail.getText();
                pswdFaculty=String.valueOf(passwordFaculty.getPassword());
                SignupController t;
                if(isTutorSelector.isSelected()) {
                    t = new SignupController(nameOfFaculty,pswdFaculty,emailFaculty,'T');
                }
                else {
                    t = new SignupController(nameOfFaculty,pswdFaculty,emailFaculty,'F');
                    t.setSubject(String.valueOf(subjectSelector.getSelectedItem()));
                }

                try {
                    User user = new User(emailFaculty, pswdFaculty);
                    User.validatePassword(user);
                    User.validateEmail(user);
                    t.storeData();
                    facultyMessage.setForeground(new Color(0, 189, 76));
                    facultyMessage.setText("Successful!!!");
                }
                catch(Exception e1) {
                    facultyMessage.setText(e1.toString());
                }
            }
        });

    }
}

