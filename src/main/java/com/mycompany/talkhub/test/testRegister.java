/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.talkhub.test;

/**
 *
 * @author inshika
 */
import com.mycompany.talkhub.database.UserAction;

public class testRegister {
    public static void main(String[] args) {

        UserAction act = new UserAction();

        boolean success =
                act.registerUser(
                        "inshika",
                        "12345"
                );

        if(success) {
            System.out.println("User Registered!");
        } else {
            System.out.println("Registration Failed!");
        }
    }
}
