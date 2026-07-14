package com.example.notes.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public final class DatabaseConfig {

    private static final String DB_URL = System.getenv("DB_URL");
    private static final String DB_USER = System.getenv("DB_USER");
    private static final String DB_PASSWORD = System.getenv("DB_PASSWORD");

    private DatabaseConfig() {
    }

    public static Connection getConnection() throws SQLException {
        validateConfiguration();

        return DriverManager.getConnection(
                DB_URL,
                DB_USER,
                DB_PASSWORD
        );
    }

    private static void validateConfiguration() {
        if (isBlank(DB_URL) || isBlank(DB_USER) || isBlank(DB_PASSWORD)) {
            throw new IllegalStateException(
                    "Database environment variables DB_URL, DB_USER and DB_PASSWORD must be configured"
            );
        }
    }

    private static boolean isBlank(String value) {
        return value == null || value.isBlank();
    }
}
