package org.example.command.impl;

import org.example.command.Command;
import org.example.command.RequestContext;
import org.example.command.ResponseContext;
import org.example.command.impl.page.ShowMainPageCommand;
import org.example.command.impl.page.ShowSignUpPageCommand;
import org.example.model.User;
import org.example.service.UserService;
import org.example.service.impl.UserServiceImpl;

import javax.servlet.http.HttpSession;

public enum SignUpCommand implements Command {
    INSTANCE;

    private final UserService service = UserServiceImpl.INSTANCE;

    @Override
    public ResponseContext execute(RequestContext requestContext) {
        final HttpSession session = requestContext.getSession();
        if (session.getAttribute("login") != null) {
            return ShowMainPageCommand.INSTANCE.execute(requestContext);
        }

        final String login = String.valueOf(requestContext.getParameter("login"));
        final String password = String.valueOf(requestContext.getParameter("password"));
        final User user = new User(login, password);
        ResponseContext response;
        if (password.length() >= 6 && password.length() <= 30 && login.length() <= 15 && login.matches("[A-Za-z0-9_]{1,15}")) {
            if (service.create(user)) {
                response = LoginCommand.INSTANCE.execute(requestContext);
            } else {
                requestContext.setAttribute("signupError", "login");
                response = ShowSignUpPageCommand.INSTANCE.execute(requestContext);
            }
        } else {
            requestContext.setAttribute("signupError", "password");
            response = ShowSignUpPageCommand.INSTANCE.execute(requestContext);
        }
        return response;
    }
}
