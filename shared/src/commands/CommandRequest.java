package commands;

import java.io.Serializable;

public class CommandRequest implements Serializable {
    private final String commandName;
    private final Object[] arguments;
    private final Object element;

    public CommandRequest(String commandName, Object[] arguments, Object element) {
        this.commandName = commandName;
        this.arguments = arguments;
        this.element = element;
    }


    public String getCommandName() {
        return commandName;
    }

    public Object[] getArguments() {
        return arguments;
    }

    public Object getElement() {
        return element;
    }
}
