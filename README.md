# TalkHub

TalkHub is a desktop-based chat application developed using Java Swing, JDBC, Socket Programming, and Multithreading. It allows users to register, log in, view registered users, and send messages to online users in real time.

## Features

* User Registration & Login
* Real-Time Messaging
* Multiple Client Support
* User List Display
* Online User Detection
* MySQL Database Integration

## Technologies Used

* Java
* Swing
* JDBC
* MySQL
* Socket Programming
* Multithreading
* Maven

## Database Setup

```sql
CREATE DATABASE talkhub;
USE talkhub;

CREATE TABLE users(
    id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL
);
```

## How to Run

1. Start MySQL and create the database.
2. Run `Server.java`.
3. Run `TalkHub.java`.
4. Register/Login with different users.
5. Select a user and start chatting.

## Project Concepts

* OOP
* GUI Development
* JDBC Connectivity
* Client-Server Architecture
* Socket Programming
* Multithreading
