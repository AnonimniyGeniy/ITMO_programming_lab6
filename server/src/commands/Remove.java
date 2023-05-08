package commands;


import managers.CollectionManager;
import managers.CommandReceiver;
import managers.Console;

/**
 * command for removing element from collection by id
 */
public class Remove extends AbstractCommand {
    private final CollectionManager collectionManager;
    private final Console console;
    private CommandReceiver commandReceiver;

    public Remove(Console console, CollectionManager collectionManager, CommandReceiver commandReceiver) {
        super("remove", "remove element from collection by id");
        this.collectionManager = collectionManager;
        this.console = console;
        this.commandReceiver = commandReceiver;
    }

    @Override
    public boolean execute(String[] args) {
        return commandReceiver.remove(args);
    }
}
