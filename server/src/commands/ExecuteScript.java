package commands;


import managers.CommandReceiver;

/**
 * Command to execute script from file
 */
public class ExecuteScript extends AbstractCommand {
    private final CommandReceiver commandReceiver;

    public ExecuteScript(CommandReceiver commandReceiver) {
        super("execute_script", "Execute script from file");
        this.commandReceiver = commandReceiver;
    }

    @Override
    public boolean execute(String[] args) {
        return commandReceiver.executeScript(args);
    }
}
