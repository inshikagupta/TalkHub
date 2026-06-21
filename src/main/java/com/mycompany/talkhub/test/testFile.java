/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.talkhub.test;

/**
 *
 * @author inshika
 */
import java.sql.Connection;
import com.mycompany.talkhub.database.DBConnection;

public class testFile {

    public static void main(String[] args) {

        try (Connection con = DBConnection.getConnection()) {

            System.out.println("Database Connected Successfully!");

        } catch (Exception e) {

            System.out.println("Connection Failed!");
            e.printStackTrace();
        }
    }
}