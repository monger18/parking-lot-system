package org.example;

import org.example.util.DBConnection;

import java.sql.Connection;

public class TestConnection {
    public static void main(String[] args) {
        try (Connection connection = DBConnection.getConnection()) {
            System.out.println("Connected to database.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
