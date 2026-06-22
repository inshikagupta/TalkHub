/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.talkhub.gui;


import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.text.DefaultCaret;
import javax.swing.JList;
import javax.swing.DefaultListModel;
import javax.swing.JPanel;

import java.awt.Color;
import java.awt.Font;
import java.awt.BorderLayout;
import java.awt.Image;
import com.mycompany.talkhub.model.User;
import com.mycompany.talkhub.database.UserAction;

public class ChatGUI extends JFrame {
    private String username;
    public JLabel heading;
    public JTextArea messageArea;
    public JTextField messageInput;
    private final Font font = new Font("Roboto", Font.PLAIN, 20);
    private final Font titleFont = new Font("Arial", Font.BOLD, 20);
    
    private String selectedUser;
    private JList<String> userList;
    private DefaultListModel<String> userModel; 
    
    public ChatGUI() {
        this("TalkHub");
    }
    
    public ChatGUI(String username) {
        this.username = username;
        createGUI();
        setVisible(true);
    }

    public JTextArea getMessageArea(){
        return messageArea;
    }   
    public JTextField getMessageInput(){
        return messageInput;
    }
    
    public String getSelectedUser() {
        return selectedUser;
        
    }
    private void loadUsers() {
        UserAction ua = new UserAction();
        for(User u : ua.getAllUsers()) {
            if(!u.getUsername().equals(username)) {
                userModel.addElement(u.getUsername());
            }
        }
    }
    private void createGUI() {
        heading = new JLabel(username);
        messageArea = new JTextArea();
        messageInput = new JTextField();

        userModel = new DefaultListModel<>();
        userList = new JList<>(userModel);

        loadUsers();

        userList.addListSelectionListener(e -> {
            selectedUser = userList.getSelectedValue();
            messageArea.setText("");
            messageArea.append(
                "Chatting with "
                + selectedUser
                + "\n\n"
            );
        });

        userList.setFont(titleFont);


        DefaultCaret caret =(DefaultCaret) messageArea.getCaret();
        caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);

        setTitle("TalkHub");
        setSize(600, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        heading.setFont(font);
        messageArea.setFont(font);
        messageInput.setFont(font);

        messageArea.setEditable(false);

        ImageIcon icon = new ImageIcon(
                getClass().getResource("/images/chatLogo.jpeg")
        );

        Image img = icon.getImage().getScaledInstance(
                70, 70, Image.SCALE_SMOOTH
        );

        heading.setIcon(new ImageIcon(img));
        heading.setHorizontalAlignment(SwingConstants.CENTER);
        heading.setHorizontalTextPosition(SwingConstants.CENTER);
        heading.setVerticalTextPosition(SwingConstants.BOTTOM);
        heading.setBorder(
                BorderFactory.createEmptyBorder(15, 15, 15, 15)
        );

        // ======================
        // Online Users Panel
        // ======================

        JLabel usersLabel =new JLabel("Users");

        usersLabel.setHorizontalAlignment(SwingConstants.CENTER);

        usersLabel.setFont(titleFont);

        JScrollPane userScroll =new JScrollPane(userList);

        JPanel userPanel =new JPanel(new BorderLayout());

        userPanel.add(usersLabel,BorderLayout.NORTH);

        userPanel.add(userScroll,BorderLayout.CENTER);

        userPanel.setPreferredSize(
                new java.awt.Dimension(220,0));

        userPanel.setBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createMatteBorder(0,0,0,1,Color.LIGHT_GRAY),
                        BorderFactory.createEmptyBorder(10,10,10,10)
                )
        );
        

        // ======================
        // Chat Panel
        // ======================

        JLabel chatLabel =new JLabel("Chat");

        chatLabel.setFont(titleFont);

        chatLabel.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));

        JPanel chatPanel = new JPanel(new BorderLayout());

        chatPanel.add(chatLabel,BorderLayout.NORTH);

        chatPanel.add(new JScrollPane(messageArea),BorderLayout.CENTER);

        // ======================
        // Main Layout
        // ======================

        setLayout(new BorderLayout());

        add(heading, BorderLayout.NORTH);
        add(userPanel, BorderLayout.WEST);
        add(chatPanel, BorderLayout.CENTER);
        add(messageInput, BorderLayout.SOUTH);
    }
}
