package com.example.notes.servlet;

import com.example.notes.dao.NoteDao;
import com.example.notes.model.Note;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/notes")
public class NoteListServlet extends HttpServlet {

    private final NoteDao noteDao = new NoteDao();

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {

        try {
            List<Note> notes = noteDao.findAll();

            request.setAttribute("notes", notes);

            request.getRequestDispatcher("/WEB-INF/views/notes.jsp")
                    .forward(request, response);

        } catch (SQLException e) {
            throw new ServletException("Unable to load notes", e);
        }
    }
}
