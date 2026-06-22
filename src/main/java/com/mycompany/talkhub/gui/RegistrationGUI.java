/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.talkhub.gui;

import com.mycompany.talkhub.database.UserAction;
import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import com.mycompany.talkhub.model.User;

/**
 *
 * @author inshika
 */

public class RegistrationGUI extends JFrame implements ActionListener {

    private JTextField usernameField;

    private JPasswordField passwordField;
    private JPasswordField confirmPasswordField;

    private JButton registerButton;
    private JButton backButton;
    
    public RegistrationGUI() {

        setTitle("TalkHub Register");
        setSize(450, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));

        // Title
        JLabel titleLabel = new JLabel("TalkHub Register", JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 22));
        add(titleLabel, BorderLayout.NORTH);

        // Center Panel
        JPanel centerPanel = new JPanel(new GridLayout(3, 2, 10, 10));

        centerPanel.add(new JLabel("Username:"));
        usernameField = new JTextField();
        centerPanel.add(usernameField);

        centerPanel.add(new JLabel("Password:"));
        passwordField = new JPasswordField();
        centerPanel.add(passwordField);

        centerPanel.add(new JLabel("Confirm Password:"));
        confirmPasswordField = new JPasswordField();
        centerPanel.add(confirmPasswordField);

        add(centerPanel, BorderLayout.CENTER);

        // Button Panel
        JPanel buttonPanel = new JPanel();

        registerButton = new JButton("Register");
        backButton = new JButton("Back");

        registerButton.addActionListener(this);
        backButton.addActionListener(this);

        buttonPanel.add(registerButton);
        buttonPanel.add(backButton);

        add(buttonPanel, BorderLayout.SOUTH);

        setVisible(true);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == registerButton) {

            String username = usernameField.getText().trim();

            String password =
                    String.valueOf(passwordField.getPassword());

            String confirmPassword =
                    String.valueOf(confirmPasswordField.getPassword());

            if (username.isEmpty()
                    || password.isEmpty()
                    || confirmPassword.isEmpty()) {

                JOptionPane.showMessageDialog(
                        this,
                        "All fields are required!"
                );
                return;
            }

            if (!password.equals(confirmPassword)) {

                JOptionPane.showMessageDialog(
                        this,
                        "Passwords do not match!"
                );
                return;
            }

            UserAction act = new UserAction();

            User user = new User(username, password);
            boolean success = act.registerUser(user);

            if(success){
                JOptionPane.showMessageDialog(
                        this,
                        "Registration Successful!"
                );
                new LoginGUI();
                dispose();

            }else{
                JOptionPane.showMessageDialog(
                        this,
                        "Registration Failed!"
                );
            }
        }

        if (e.getSource() == backButton) {

            new LoginGUI();
            dispose();
        }
    }
}
