/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.talkhub.server;

/**
 *
 * @author shubham
 */
import java.net.*;
import java.io.*;
public class Server {
    ServerSocket serverSocket = null;
    Socket clientSocket = null;

    BufferedReader br = null;
    PrintWriter out = null;

    public Server() {
        try {
            serverSocket = new ServerSocket(12345);
            System.out.println("Server is listening on port 12345...");
            clientSocket = serverSocket.accept();
            System.out.println("Client connected: " + clientSocket.getInetAddress().getHostAddress());
            br = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            out = new PrintWriter(clientSocket.getOutputStream(), true);
            startReading();
            startWriting();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void startReading() {
        Runnable r1 = () -> {
            System.out.println("Reader started...");
            while (true) {
                try {
                    String msg = br.readLine();
                    if (msg.equals("exit")) {
                        System.out.println("Client terminated the chat");
                        clientSocket.close();
                        break;
                    }
                    System.out.println("Client: " + msg);
                } catch (Exception e) {
                    // e.printStackTrace();
                    System.out.println("Connection closed");
                }
            }
        };
        Thread readThread = new Thread(r1);
        readThread.start();
    }

    public void startWriting() {
        Runnable r2 = () -> {
            System.out.println("Writer started...");
            BufferedReader br1 = new BufferedReader(new InputStreamReader(System.in));
            while (!clientSocket.isClosed()) {
                try {
                    String content = br1.readLine();
                    out.println(content);
                    if (content.equals("exit")) {
                        clientSocket.close();
                        break;
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };
        Thread writeThread = new Thread(r2);
        writeThread.start();
    }

    public static void main(String[] args) {
        System.out.println("Server is running...");
        new Server();
    }
}
