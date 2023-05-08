package commands;


import managers.CommandManager;
import managers.CommandReceiver;

/**
 * Command that shows last 13 commands (without their arguments)
 */
public class History extends AbstractCommand {
    private final CommandManager commandManager;
    private final Console console;
    private final CommandReceiver commandReceiver;

    /**
     * Constructor for History
     */
    public History(Console console, CommandManager commandManager, CommandReceiver commandReceiver) {
        super("history", "Shows last 13 commands (without their arguments)");
        this.commandManager = commandManager;
        this.console = console;
        this.commandReceiver = commandReceiver;
    }


    /**
     * Description of command
     */
    @Override
    public String describe() {
        return "Show last 13 commands (without their arguments)";
    }

    /**
     * Name of command
     */
    @Override
    public String getName() {
        return "history";
    }

    /**
     * Method for executing this command
     */
    @Override
    public boolean execute(String[] args) {
        return commandReceiver.history(args, commandManager);
    }
}
