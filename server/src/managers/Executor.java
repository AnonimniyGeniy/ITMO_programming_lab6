package managers;

import commands.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Class that controls the command input mode and manages Scanner and handling whole execution.
 * (receiver)
 */
public class Executor {
    private final List<String> recursionStack = new ArrayList<>();
    private final CommandManager commandManager;
    private CommandReceiver commandReceiver;
    /**
     * constructor for Executor
     *
     * @param collectionManager - CommandManager
     *                          for managing commands
     *                          and command history
     */
    public Executor(CollectionManager collectionManager) {
        List<AbstractCommand> commands = new ArrayList<>();
        commandReceiver = new CommandReceiver(collectionManager);
        commands.add(new Info(collectionManager,commandReceiver));
        commands.add(new Insert(collectionManager,commandReceiver));
        //commands.add(new Exit(collectionManager));
        commands.add(new Save(collectionManager,commandReceiver));
        commands.add(new Show(collectionManager,commandReceiver));
        commands.add(new Remove(collectionManager,commandReceiver));
        commands.add(new Update(collectionManager,commandReceiver));
        commands.add(new Clear(collectionManager,commandReceiver));
        commands.add(new RemoveGreater(collectionManager,commandReceiver));
        commands.add(new ReplaceIfLower(collectionManager,commandReceiver));
        commands.add(new GroupCountingByImpact(collectionManager,commandReceiver));
        commands.add(new CountGreaterThanCar(collectionManager,commandReceiver));
        commands.add(new PrintDescending(collectionManager,commandReceiver));
        //commands.add(new ExecuteScript);
        var commandManager = new CommandManager(commands);
        commandManager.addCommand(new History(commandManager,commandReceiver));
        commandManager.addCommand(new Help(commandManager.getCommandsArray(),commandReceiver));
        commandManager.addCommand(new Connect(commandManager,commandReceiver));
        this.commandManager = commandManager;
        this.commandReceiver = new CommandReceiver(collectionManager);

    }

    public AbstractCommand[] getCommandsArray() {
        return commandManager.getCommandsArray();
    }
    /**
     * method for executing commands in cli mode
     *
     * @param userCommand - command to execute
     * @return response of command execution
     */
    public CommandResponse executeCommand(CommandRequest userCommand) {
        Command command = commandManager.getCommands().get(userCommand.getCommandName());
        CommandResponse response = new CommandResponse("OK", null);
        try {
            if (userCommand.getElement() == null) {
                response = command.execute((String[]) userCommand.getArguments(), null);
                commandManager.addHistory(userCommand.getCommandName());
            } else {
                response = command.execute((String[]) userCommand.getArguments(), userCommand.getElement());
                commandManager.addHistory(userCommand.getCommandName());
            }
            return response;
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        return response;
    }
    /**
     * enum for status of execution
     */
    public enum Status {
        OK,
        ERROR,
        EXIT,
    }


}
