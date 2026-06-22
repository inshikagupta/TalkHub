/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.talkhub.server;

/**
 *
 * @author Shubham Prajapati <shubhamprajapati511@gmail.com>
 */
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientHandler extends Thread {

    private Socket socket;
    private BufferedReader br;
    private PrintWriter out;
    private String username;

    public ClientHandler(Socket socket) {
        this.socket = socket;
        try {
            br = new BufferedReader(
                    new InputStreamReader(
                            socket.getInputStream()));

            out = new PrintWriter(
                    socket.getOutputStream(),
                    true);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        try {
            String firstMsg = br.readLine();

            if (firstMsg != null && firstMsg.startsWith("USERNAME:")) {
                username = firstMsg.substring(9);
                Server.onlineUsers.put(username,this);
                System.out.println(username + " joined");
            }

            String msg;

            while ((msg = br.readLine()) != null) {
                System.out.println(username + " -> " + msg);
                if (msg.startsWith("MSG:")) {
                    String[] parts =msg.split(":", 3);
                    String receiver =parts[1];
                    String message =parts[2];
                    ClientHandler target = Server.onlineUsers.get(receiver);
                    if (target != null) {
                        target.sendMessage(username+ " : "+message);
                    } else {
                        out.println(
                                "SYSTEM: User is offline");
                    }
                }
            }
        } catch (Exception e) {
            System.out.println(username + " disconnected");
        } finally {
            try {
                Server.onlineUsers.remove(username);
                socket.close();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    public void sendMessage(String msg) {
        out.println(msg);
    }
}
