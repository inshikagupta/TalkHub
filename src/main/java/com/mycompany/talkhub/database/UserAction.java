/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.talkhub.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author inshika
 */

public class UserAction {
    public boolean registerUser(
            String username,
            String password) {

        String sql =
                "INSERT INTO users(username,password) VALUES(?,?)";

        try (
                Connection con =
                        DBConnection.getConnection();

                PreparedStatement pst =
                        con.prepareStatement(sql)
        ) {

            pst.setString(1, username);
            pst.setString(2, password);

            int rows = pst.executeUpdate();

            return rows > 0;

        } catch (Exception e) {

            e.printStackTrace();
            return false;
        }
    }
    
     public boolean loginUser(String username, String password) {

        String sql =
                "SELECT * FROM users WHERE username = ? AND password = ?";

        try (
                Connection con = DBConnection.getConnection();

                PreparedStatement pst =
                        con.prepareStatement(sql)
        ) {

            pst.setString(1, username);
            pst.setString(2, password);

            ResultSet rs = pst.executeQuery();

            return rs.next();

        } catch (Exception e) {

            e.printStackTrace();
            return false;
        }
    }
}
