/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.talkhub.server;

import com.mycompany.talkhub.gui.ChatGUI;

import java.net.ServerSocket;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;


public class Server {
    ServerSocket serverSocket = null;
    Socket clientSocket = null;

    BufferedReader br = null;
    PrintWriter out = null;
    ChatGUI gui;
    
    public Server() {
        try {
            serverSocket = new ServerSocket(12345);
            System.out.println("Server is listening on port 12345...");
            clientSocket = serverSocket.accept();
            System.out.println("Client connected: " + clientSocket.getInetAddress().getHostAddress());
            br = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            out = new PrintWriter(clientSocket.getOutputStream(), true);
            
            gui = new ChatGUI();
            gui.heading.setText("TalkHub Server");
            
            handleEvents();
            startReading();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    private void handleEvents(){
        gui.messageInput.addActionListener(e->{
            String msg = gui.messageInput.getText();

            if(msg.trim().isEmpty())
                return;

            out.println(msg);

            gui.messageArea.append("Me : " + msg + "\n");

            gui.messageInput.setText("");

            if(msg.equalsIgnoreCase("exit")) {
                try {
                    clientSocket.close();
                } catch(Exception ex) {
                    ex.printStackTrace();
                }
            }
        });
        
    }
    
    public void startReading() {
        Runnable r1 = () -> {
            System.out.println("Reader started...");
            while (true) {
                try {
                    String msg = br.readLine();
                    if(msg == null)
                        break;
    
                    if (msg.equals("exit")) {
                        System.out.println("Client terminated the chat");
                        clientSocket.close();
                        break;
                    }
                    gui.messageArea.append("Client : " + msg + "\n");
                } catch (Exception e) {
                    // e.printStackTrace();
                    System.out.println("Connection closed");
                }
            }
        };
        Thread readThread = new Thread(r1);
        readThread.start();
    }

    public static void main(String[] args) {
        System.out.println("Server is running...");
        new Server();
    }
}
