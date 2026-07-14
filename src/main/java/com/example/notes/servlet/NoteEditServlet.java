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

@WebServlet(urlPatterns = {"/notes/edit", "/notes/update"})
public class NoteEditServlet extends HttpServlet {

    private final NoteDao noteDao = new NoteDao();

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {

        int id;

        try {
            id = Integer.parseInt(request.getParameter("id"));
        } catch (NumberFormatException e) {
            response.sendError(
                    HttpServletResponse.SC_BAD_REQUEST,
                    "Invalid note ID"
            );
            return;
        }

        try {
            Note note = noteDao.findById(id);

            if (note == null) {
                response.sendError(
                        HttpServletResponse.SC_NOT_FOUND,
                        "Note not found"
                );
                return;
            }

            request.setAttribute("note", note);
            request.setAttribute("title", note.getTitle());
            request.setAttribute("content", note.getContent());
            request.setAttribute(
                    "formAction",
                    request.getContextPath() + "/notes/update"
            );
            request.setAttribute("pageTitle", "Edit note");

            request.getRequestDispatcher("/WEB-INF/views/note-form.jsp")
                    .forward(request, response);

        } catch (SQLException e) {
            throw new ServletException("Unable to load note", e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");

        int id;

        try {
            id = Integer.parseInt(request.getParameter("id"));
        } catch (NumberFormatException e) {
            response.sendError(
                    HttpServletResponse.SC_BAD_REQUEST,
                    "Invalid note ID"
            );
            return;
        }

        String title = request.getParameter("title");
        String content = request.getParameter("content");

        if (title == null || title.isBlank()) {
            Note note = new Note();
            note.setId(id);

            request.setAttribute("note", note);
            request.setAttribute("title", title == null ? "" : title);
            request.setAttribute("content", content == null ? "" : content);
            request.setAttribute("error", "Title cannot be blank");
            request.setAttribute(
                    "formAction",
                    request.getContextPath() + "/notes/update"
            );
            request.setAttribute("pageTitle", "Edit note");

            request.getRequestDispatcher("/WEB-INF/views/note-form.jsp")
                    .forward(request, response);
            return;
        }

        Note note = new Note();
        note.setId(id);
        note.setTitle(title.trim());
        note.setContent(content == null ? "" : content.trim());

        try {
            noteDao.update(note);
            response.sendRedirect(request.getContextPath() + "/notes");
        } catch (SQLException e) {
            throw new ServletException("Unable to update note", e);
        }
    }
}
