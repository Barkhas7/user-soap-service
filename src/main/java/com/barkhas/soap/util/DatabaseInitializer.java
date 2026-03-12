package com.barkhas.soap.util;

import java.sql.Connection;
import java.sql.Statement;

public class DatabaseInitializer {

    public static void initialize() {
        String createUsersTable =
                "CREATE TABLE IF NOT EXISTS users (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "username TEXT NOT NULL UNIQUE, " +
                "password TEXT NOT NULL, " +
                "token TEXT" +
                ")";

        String createProfilesTable =
                "CREATE TABLE IF NOT EXISTS profiles (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "user_id INTEGER NOT NULL, " +
                "name TEXT, " +
                "email TEXT, " +
                "bio TEXT, " +
                "phone TEXT" +
                ")";

        try (Connection conn = DBConnection.getConnection();
             Statement stmt = conn.createStatement()) {

            stmt.execute(createUsersTable);
            stmt.execute(createProfilesTable);

            System.out.println("Database tables created successfully.");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}