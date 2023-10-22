package org.example.controller;

import org.example.command.Command;
import org.example.command.ResponseContext;
import org.example.command.impl.WrappingRequestContext;
import org.example.helper.GoogleBooksApiHelper;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/controller")
public class ApplicationController extends HttpServlet {

    public static final String COMMAND_PARAMETER_NAME = "command";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        process(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        process(req, resp);
    }

    private void process(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String resultSecond = GoogleBooksApiHelper.getBookByISBN("0201558025");
        System.out.println(resultSecond);
        final String commandName = req.getParameter(COMMAND_PARAMETER_NAME);
        final Command businessCommand = Command.of(commandName);
        final ResponseContext result = businessCommand.execute(WrappingRequestContext.of(req));
        if (result.isRedirect()) {
            String urlToRedirect = result.getUrlToRedirect();
            resp.sendRedirect(urlToRedirect);
        } else {
            final RequestDispatcher dispatcher = req.getRequestDispatcher(result.getPage());
            dispatcher.forward(req, resp);
        }
    }
}
