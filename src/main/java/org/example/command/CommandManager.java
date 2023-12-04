package org.example.command;

import org.example.command.impl.LoginCommand;
import org.example.command.impl.LogoutCommand;
import org.example.command.impl.ManageBookCommand;
import org.example.command.impl.SignUpCommand;
import org.example.command.impl.page.ShowBookPageCommand;
import org.example.command.impl.page.ShowBookPlanPageCommand;
import org.example.command.impl.page.ShowLoginPageCommand;
import org.example.command.impl.page.ShowMainPageCommand;
import org.example.command.impl.page.ShowMyLibraryPageCommand;
import org.example.command.impl.page.ShowSearchPageCommand;
import org.example.command.impl.page.ShowSignUpPageCommand;

public enum CommandManager {
    LOGIN(LoginCommand.INSTANCE),
    LOGOUT(LogoutCommand.INSTANCE),
    SIGNUP(SignUpCommand.INSTANCE),
    SHOW_LOGIN_PAGE(ShowLoginPageCommand.INSTANCE),
    SHOW_SIGNUP_PAGE(ShowSignUpPageCommand.INSTANCE),
    SEARCH_BOOK(ShowSearchPageCommand.INSTANCE),
    SHOW_BOOK(ShowBookPageCommand.INSTANCE),
    MANAGE_BOOK(ManageBookCommand.INSTANCE),
    SHOW_MY_LIBRARY_PAGE(ShowMyLibraryPageCommand.INSTANCE),
    SHOW_BOOK_PLAN(ShowBookPlanPageCommand.INSTANCE),
    DEFAULT(ShowMainPageCommand.INSTANCE);

    private final Command command;

    CommandManager(Command command) {
        this.command = command;
    }

    static Command of(String commandName) {
        for (CommandManager action : values()) {
            if (action.name().equalsIgnoreCase(commandName)) {
                return action.command;
            }
        }
        return DEFAULT.command;
    }
}
