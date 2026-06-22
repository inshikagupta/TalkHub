/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.talkhub.server;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;

public class Server {
    public static final HashMap<String, ClientHandler> onlineUsers =new HashMap<>();
    private ServerSocket serverSocket;

    public Server() {
        try {
            serverSocket = new ServerSocket(12345);
            System.out.println("Server Started...");
            while (true) {
                Socket socket = serverSocket.accept();
                System.out.println("Client Connected : "+ socket.getInetAddress());
                ClientHandler ch =new ClientHandler(socket);
                ch.start();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new Server();
    }
}
