package org.example.command.impl;

import org.example.command.Command;
import org.example.command.RequestContext;
import org.example.command.ResponseContext;
import org.example.command.impl.page.ShowLoginPageCommand;
import org.example.command.impl.page.ShowMainPageCommand;
import org.example.model.UserDto;
import org.example.service.UserService;
import org.example.service.impl.UserServiceImpl;

import javax.servlet.http.HttpSession;
import java.util.Optional;

public enum LoginCommand implements Command {
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
        final Optional<UserDto> userDto = service.loginUser(login, password);
        ResponseContext response;
        if (userDto.isPresent()) {
            UserDto user = userDto.get();
            requestContext.setSessionAttribute("id", user.getId());
            requestContext.setSessionAttribute("login", user.getLogin());
            requestContext.setAttribute("redirect", true);
            response = ShowMainPageCommand.INSTANCE.execute(requestContext);
        } else {
            requestContext.setAttribute("loginError", "Not found and wrong password");
            response = ShowLoginPageCommand.INSTANCE.execute(requestContext);
        }
        return response;
    }
}
