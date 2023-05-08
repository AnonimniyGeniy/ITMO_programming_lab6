package commands;

import java.io.Serializable;

/**
 * Class that holds command name, description, count of arguments, type of arguments, and object info.
 * Used to send command from client to server.
 */

public class CommandRequest implements Serializable {
    private final String commandName;
    private final Object[] arguments;
    private final Object object;

    public CommandRequest(String commandName, Object[] arguments, Object object) {
        this.commandName = commandName;
        this.arguments = arguments;
        this.object = object;
    }


    public String getCommandName() {
        return commandName;
    }

    public Object[] getArguments() {
        return arguments;
    }

    public Object getElement() {
        return object;
    }
}
