package commands;

/**
 * shows help for available commands
 */
@CommandInfo(name = "help", description = "shows help for available commands")
public class Help extends AbstractCommand {
    private final AbstractCommand[] commands;

    public Help(AbstractCommand[] commands) {
        super("help", "shows help for available commands");
        this.commands = commands;
    }

    @Override
    public CommandResponse execute(String[] args, Object obj) {
        StringBuilder stringBuilder = new StringBuilder();
        for (AbstractCommand command : commands) {
            stringBuilder.append(command.getName()).append(" - ").append(command.describe()).append("\n");
        }
        stringBuilder.append("help - ").append("shows help for available commands");
        return new CommandResponse(stringBuilder.toString(), null);
    }
}
