/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.talkhub.client;

/**
 *
 * @author shubham
 */

import com.mycompany.talkhub.gui.ChatGUI;

import java.net.Socket;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.IOException;

public class Client {
    private String username;
    private Socket socket;
    private BufferedReader br;
    private PrintWriter out;
    private ChatGUI gui;

    public Client(String username, ChatGUI gui) {
        this.username = username;
        this.gui = gui;

        try {
            socket = new Socket("127.0.0.1", 12345);
            System.out.println("Connected to server: " + socket.getInetAddress());

            br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(socket.getOutputStream(), true);

            gui.heading.setText(username);

            handleEvents();
            startReading();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Send message to server
    private void handleEvents() {
        gui.getMessageInput().addActionListener(e -> {

            String msg = gui.getMessageInput().getText();

            if (msg == null || msg.trim().isEmpty())
                return;

            out.println(username + " : " + msg);
            gui.getMessageArea().append("Me : " + msg + "\n");

            gui.getMessageInput().setText("");

            if (msg.equalsIgnoreCase("exit")) {
                try {
                    socket.close();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        });
    }

    // Read messages from server
    public void startReading() {

        Runnable r1 = () -> {
            try {
                String msg;

                while ((msg = br.readLine()) != null) {

                    if (msg.equalsIgnoreCase("exit")) {
                        System.out.println("Server closed connection");
                        socket.close();
                        break;
                    }

                    gui.getMessageArea().append(msg + "\n");
                }

            } catch (Exception e) {
                System.out.println("Connection closed");
            }
        };

        new Thread(r1).start();
    }
}