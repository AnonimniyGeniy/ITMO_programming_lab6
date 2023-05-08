package commands;

import managers.Console;

/**
 * shows help for available commands
 */
@CommandInfo(name = "help", description = "shows help for available commands")
public class Help extends AbstractCommand {
    private final AbstractCommand[] commands;
    private final Console console;

    public Help(Console console, AbstractCommand[] commands) {
        super("help", "shows help for available commands");
        this.commands = commands;
        this.console = console;
    }

    @Override
    public boolean execute(String[] args, Object obj) {
        for (AbstractCommand command : commands) {
            console.println(command.getName() + " - " + command.describe());
        }
        console.println("help - " + "shows help for available commands");
        return true;

    }
}
