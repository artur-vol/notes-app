package com.example.notes.servlet;

import com.example.notes.dao.NoteDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/notes/delete")
public class NoteDeleteServlet extends HttpServlet {

    private final NoteDao noteDao = new NoteDao();

    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {

        int id;

        try {
            id = Integer.parseInt(request.getParameter("id"));
        } catch (NumberFormatException e) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid note ID");
            return;
        }

        try {
            noteDao.delete(id);
            response.sendRedirect(request.getContextPath() + "/notes");
        } catch (SQLException e) {
            throw new ServletException("Unable to delete note", e);
        }
    }
}
