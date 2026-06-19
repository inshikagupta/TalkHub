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

import java.awt.Font;
import java.awt.BorderLayout;
import java.awt.Image;


public class ChatGUI extends JFrame {
    public JLabel heading;
    public JTextArea messageArea;
    public JTextField messageInput;

    Font font = new Font("Roboto", Font.PLAIN, 20);
    
    public ChatGUI() {

        createGUI();

        setVisible(true);
    }

    public JTextArea getMessageArea(){
        return messageArea;
    }   
    public JTextField getMessageInput(){
        return messageInput;
    }
    private void createGUI(){
        heading = new JLabel("Client Area");
        messageArea = new JTextArea();
        messageInput = new JTextField();
        DefaultCaret caret =
        (DefaultCaret) messageArea.getCaret();
        caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);
        
        setTitle("TalkHub");
        setSize(500, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    
        heading.setFont(font);
        messageArea.setFont(font);
        messageInput.setFont(font);
        ImageIcon icon = new ImageIcon(
        getClass().getResource("/images/chatLogo.jpeg")
        );

        Image img = icon.getImage().getScaledInstance(
            50, 50, Image.SCALE_SMOOTH
        );
        
        heading.setIcon(new ImageIcon(img));
        heading.setHorizontalAlignment(SwingConstants.CENTER);
        heading.setHorizontalTextPosition(SwingConstants.CENTER);
        heading.setVerticalTextPosition(SwingConstants.BOTTOM);

        
        heading.setHorizontalAlignment(SwingConstants.CENTER);
        heading.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        messageArea.setEditable(false);
        //messageInput.setHorizontalAlignment(swingConstants.CENTER);
        setLayout(new BorderLayout());
        add(heading, BorderLayout.NORTH);
        add(new JScrollPane(messageArea), BorderLayout.CENTER);
        add(messageInput, BorderLayout.SOUTH);
        heading.setBorder(
            BorderFactory.createEmptyBorder(10, 10, 10, 10)
        );

    }
}
