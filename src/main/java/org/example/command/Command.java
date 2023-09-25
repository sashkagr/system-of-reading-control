package org.example.command;

public interface Command {
    /**
     * Method is used to execute client's request
     *
     * @param requestContext is a client's request
     * @return ResponseContext of executed command
     */
    ResponseContext execute(RequestContext requestContext);

    /**
     * Method is used to parse command by command name
     *
     * @param commandName is a name of command
     * @return Command by given name or default command
     */
    static Command of(String commandName) {
        return CommandManager.of(commandName);
    }
}
