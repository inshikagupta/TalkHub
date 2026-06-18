/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.talkhub.client;

/**
 *
 * @author shubham
 */
import java.net.*;
import java.io.*;

public class client {
    Socket socket = null;
    BufferedReader br = null;
    PrintWriter out = null;

    public client() {
        try {
            socket = new Socket("127.0.0.1", 12345);
            System.out.println("Connected to server: " + socket.getInetAddress().getHostAddress());
            br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(socket.getOutputStream(), true);
            startReading();
            startWriting();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void startReading() {
        Runnable r1 = () -> {
            System.out.println("Reader started...");
            try {
                while (true) {
                    String msg = br.readLine();
                    if (msg.equals("exit")) {
                        System.out.println("Client terminated the chat");
                        socket.close();
                        break;
                    }
                    System.out.println("Server: " + msg);
                }
            } catch (Exception e) {
                // e.printStackTrace();
                System.out.println("Connection closed");
            }
        };
        Thread readThread = new Thread(r1);
        readThread.start();
    }

    public void startWriting() {
        Runnable r2 = () -> {
            System.out.println("Writer started...");
            try {
                while (!socket.isClosed()) {
                    BufferedReader br1 = new BufferedReader(new InputStreamReader(System.in));
                    String content = br1.readLine();
                    out.println(content);
                    if (content.equals("exit")) {
                        socket.close();
                        break;
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        };
        Thread writeThread = new Thread(r2);
        writeThread.start();
    }

    public static void main(String[] args) {
        System.out.println("Client is running...");
        new client();
    }
}
