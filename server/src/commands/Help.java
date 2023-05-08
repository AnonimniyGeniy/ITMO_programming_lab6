package commands;


import managers.CommandReceiver;

/**
 * shows help for available commands
 */
public class Help extends AbstractCommand {
    private final AbstractCommand[] commands;
    private final Console console;
    private CommandReceiver commandReceiver;

    public Help(Console console, AbstractCommand[] commands, CommandReceiver commandReceiver) {
        super("help", "shows help for available commands");
        this.commands = commands;
        this.console = console;
        this.commandReceiver = commandReceiver;
    }

    @Override
    public boolean execute(String[] args) {
        return commandReceiver.help(args, commands);
    }
}
