package commands;


import managers.CommandManager;
/**
 * Command that shows last 13 commands (without their arguments)
 */
@CommandInfo(name = "history", description = "Shows last 13 commands (without their arguments)")
public class History extends AbstractCommand {
    private final CommandManager commandManager;

    /**
     * Constructor for History
     */
    public History(CommandManager commandManager) {
        super("history", "Shows last 13 commands (without their arguments)");
        this.commandManager = commandManager;
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
    public CommandResponce execute(String[] args, Object obj) {
        StringBuilder commands = new StringBuilder();
        String message = "Last 13 commands:";
        for (String command : commandManager.getCommandHistory()) {
            commands.append(command).append("\n");
        }
        return new CommandResponce(message, new Object[]{commands.toString()});
    }
}
