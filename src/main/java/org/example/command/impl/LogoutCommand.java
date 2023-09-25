package org.example.command.impl;

import org.example.command.Command;
import org.example.command.RequestContext;
import org.example.command.ResponseContext;
import org.example.command.impl.page.ShowMainPageCommand;

public enum LogoutCommand implements Command {
    INSTANCE;

    @Override
    public ResponseContext execute(RequestContext requestContext) {
        requestContext.invalidateSession();
        requestContext.setAttribute("redirect", true);
        return ShowMainPageCommand.INSTANCE.execute(requestContext);
    }
}
