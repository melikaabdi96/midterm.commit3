package com.company;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

/**
 * This class hold log in to the system form and information
 * @author MElika
 * @since 2020
 */
public class LogInForm {

    private JFrame loginForm;
    private JButton loginButton;
    private JTextField usernameField;
    private JPasswordField passwordField;

    /**
     * Create a log in frame with jframe that contains a panel
     * this panel contains of a a panel and a lable and a button
     */
    public LogInForm() {
        //log in frame
        this.loginForm = new JFrame("Log in");
        this.loginForm.setLocationRelativeTo(null);
        this.loginForm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //log in panel
        JPanel panel = new JPanel(new BorderLayout(5, 5));
        panel.setBorder(new EmptyBorder(5, 5, 5, 5));
        loginForm.setContentPane(panel);

        //log in lable
        JLabel label = new JLabel(" Enter your information ");
        label.setBackground(Color.YELLOW);
        label.setHorizontalAlignment(SwingConstants.CENTER);
        label.setOpaque(true);

        //setborder for lable
        Border border = BorderFactory.createLineBorder(Color.BLACK, 2);
        label.setBorder(border);
        label.setPreferredSize(new Dimension(50, 50));

        JLabel unameLabel = new JLabel(" Username : ");
        usernameField = new JTextField();
        usernameField.addActionListener(new ButtonHandler());

        JLabel psswdLabel = new JLabel(" Password : ");
        passwordField = new JPasswordField();
        passwordField.addActionListener(new ButtonHandler());

        JPanel fieldsPanel = new JPanel(new GridLayout(2, 2, 50, 5));
        fieldsPanel.add(unameLabel);
        fieldsPanel.add(usernameField);
        fieldsPanel.add(psswdLabel);
        fieldsPanel.add(passwordField);


        //log in button
        loginButton = new JButton("Login");

        loginButton.addActionListener(new ButtonHandler());

        loginButton.setPreferredSize(new Dimension(50, 50));

        //makes everything together
        panel.add(label, BorderLayout.NORTH);
        panel.add(fieldsPanel, BorderLayout.CENTER);
        panel.add(loginButton, BorderLayout.SOUTH);

        loginForm.pack();
        loginForm.setVisible(true);
    }

    /**
     * this class handle events of button
     */
    private class ButtonHandler implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource().equals(loginButton)) {
                System.out.println("Button");
            } else if (e.getSource().equals(usernameField)) {
                System.out.println("User Field");
            } else if (e.getSource().equals(passwordField)) {
                System.out.println("Password Field");
            }

            String user = usernameField.getText();
            String pwd = new String(passwordField.getPassword());
            if (user.length() == 5) {
                JOptionPane.showMessageDialog(loginForm, "admin!", "Result", JOptionPane.INFORMATION_MESSAGE);
            } else if (user.length() == 6){
                JOptionPane.showMessageDialog(loginForm, "student!", "Result", JOptionPane.ERROR_MESSAGE);
            }else if (user.length() == 7){
                JOptionPane.showMessageDialog(loginForm, "professor!", "Result", JOptionPane.ERROR_MESSAGE);
            }
        }

    }

}
