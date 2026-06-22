/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.talkhub.database;

import com.mycompany.talkhub.model.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;


/**
 *
 * @author inshika
 */

public class UserAction {
    public boolean registerUser(User user) {
        String sql ="INSERT INTO users(username,password) VALUES(?,?)";
        try (Connection con=DBConnection.getConnection();
            PreparedStatement pst =con.prepareStatement(sql)){
            pst.setString(1, user.getUsername());
            pst.setString(2, user.getPassword());
            int rows = pst.executeUpdate();
            return rows > 0;
        }catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    
     public boolean loginUser(User user) {
        String sql ="SELECT 1 FROM users WHERE username = ? AND password = ?";
        try (Connection con = DBConnection.getConnection();
                PreparedStatement pst =con.prepareStatement(sql)){
            pst.setString(1, user.getUsername());
            pst.setString(2, user.getPassword());
            ResultSet rs = pst.executeQuery();
            return rs.next();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
     public ArrayList<User> getAllUsers() {
        ArrayList<User> users = new ArrayList<>();
        try {
            Connection con = DBConnection.getConnection();
            String sql = "SELECT * FROM users";
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                User user = new User();
                user.setId(rs.getInt("id"));
                user.setUsername(rs.getString("username"));
                users.add(user);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return users;
    }
}
