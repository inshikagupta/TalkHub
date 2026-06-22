/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.talkhub.gui;

import com.mycompany.talkhub.client.Client;
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

public class LoginGUI extends JFrame implements ActionListener {

    private JLabel titleLabel;

    private JTextField usernameField;
    private JPasswordField passwordField;

    private JButton loginButton;
    private JButton registerButton;

    public LoginGUI() {
       setTitle("TalkHub Login");
        setSize(400, 250);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));

        // Title
        titleLabel = new JLabel("TalkHub Login", JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 22));
        add(titleLabel, BorderLayout.NORTH);

        // Center Panel
        JPanel centerPanel = new JPanel(new GridLayout(2, 2, 10, 10));

        centerPanel.add(new JLabel("Username:"));
        usernameField = new JTextField();
        centerPanel.add(usernameField);

        centerPanel.add(new JLabel("Password:"));
        passwordField = new JPasswordField();
        centerPanel.add(passwordField);

        add(centerPanel, BorderLayout.CENTER);

        // Button Panel
        JPanel buttonPanel = new JPanel();

        loginButton = new JButton("Login");
        registerButton = new JButton("Register");

        loginButton.addActionListener(this);
        registerButton.addActionListener(this);

        buttonPanel.add(loginButton);
        buttonPanel.add(registerButton);

        add(buttonPanel, BorderLayout.SOUTH);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
         if (e.getSource() == loginButton) {
            String username = usernameField.getText().trim();
            String password = String.valueOf(passwordField.getPassword());

            if (username.isEmpty() || password.isEmpty()) {
                JOptionPane.showMessageDialog(
                        this,
                        "Please enter username and password."
                );
                return;
            }
            UserAction act = new UserAction();

            User user = new User(username, password);
            boolean success = act.loginUser(user);

            if (success) {
                JOptionPane.showMessageDialog(
                        this,
                        "Login Successful!"
                );

                ChatGUI gui = new ChatGUI(username);
                gui.setVisible(true);

                new Client(username, gui);

                dispose();

            } else {
                JOptionPane.showMessageDialog(
                        this,
                        "Invalid Username or Password!"
                );
            }
        }

        if (e.getSource() == registerButton) {
            new RegistrationGUI().setVisible(true);
                this.dispose();
        }
    }
}