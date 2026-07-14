package com.example.notes.dao;

import com.example.notes.config.DatabaseConfig;
import com.example.notes.model.Note;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class NoteDao {

    public List<Note> findAll() throws SQLException {
        String sql = """
                SELECT id, title, content, created_at
                FROM notes
                ORDER BY created_at DESC
                """;

        List<Note> notes = new ArrayList<>();

        try (
                Connection connection = DatabaseConfig.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql);
                ResultSet resultSet = statement.executeQuery()
        ) {
            while (resultSet.next()) {
                notes.add(mapRow(resultSet));
            }
        }

        return notes;
    }

    public Note findById(int id) throws SQLException {
        String sql = """
                SELECT id, title, content, created_at
                FROM notes
                WHERE id = ?
                """;

        try (
                Connection connection = DatabaseConfig.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql)
        ) {
            statement.setInt(1, id);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return mapRow(resultSet);
                }
            }
        }

        return null;
    }

    public void create(Note note) throws SQLException {
        String sql = """
                INSERT INTO notes (title, content)
                VALUES (?, ?)
                """;

        try (
                Connection connection = DatabaseConfig.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql)
        ) {
            statement.setString(1, note.getTitle());
            statement.setString(2, note.getContent());
            statement.executeUpdate();
        }
    }

    public void update(Note note) throws SQLException {
        String sql = """
                UPDATE notes
                SET title = ?, content = ?
                WHERE id = ?
                """;

        try (
                Connection connection = DatabaseConfig.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql)
        ) {
            statement.setString(1, note.getTitle());
            statement.setString(2, note.getContent());
            statement.setInt(3, note.getId());
            statement.executeUpdate();
        }
    }

    public void delete(int id) throws SQLException {
        String sql = "DELETE FROM notes WHERE id = ?";

        try (
                Connection connection = DatabaseConfig.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql)
        ) {
            statement.setInt(1, id);
            statement.executeUpdate();
        }
    }

    private Note mapRow(ResultSet resultSet) throws SQLException {
        Timestamp createdAt = resultSet.getTimestamp("created_at");

        return new Note(
                resultSet.getInt("id"),
                resultSet.getString("title"),
                resultSet.getString("content"),
                createdAt.toLocalDateTime()
        );
    }
}
