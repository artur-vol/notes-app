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

@WebServlet(urlPatterns = {"/notes/new", "/notes/create"})
public class NoteCreateServlet extends HttpServlet {

    private final NoteDao noteDao = new NoteDao();

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {

        request.setAttribute("formAction", request.getContextPath() + "/notes/create");
        request.setAttribute("pageTitle", "Create note");

        request.getRequestDispatcher("/WEB-INF/views/note-form.jsp")
                .forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");

        String title = request.getParameter("title");
        String content = request.getParameter("content");

        if (title == null || title.isBlank()) {
            request.setAttribute("error", "Title cannot be blank");
            request.setAttribute("title", title);
            request.setAttribute("content", content);
            request.setAttribute("formAction", request.getContextPath() + "/notes/create");
            request.setAttribute("pageTitle", "Create note");

            request.getRequestDispatcher("/WEB-INF/views/note-form.jsp")
                    .forward(request, response);
            return;
        }

        Note note = new Note();
        note.setTitle(title.trim());
        note.setContent(content == null ? "" : content.trim());

        try {
            noteDao.create(note);
            response.sendRedirect(request.getContextPath() + "/notes");
        } catch (SQLException e) {
            throw new ServletException("Unable to create note", e);
        }
    }
}
