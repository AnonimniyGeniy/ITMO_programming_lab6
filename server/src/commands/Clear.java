package commands;

import managers.CollectionManager;
import managers.CommandReceiver;

/**
 * command for clearing collection
 */
public class Clear extends AbstractCommand {
    private final CollectionManager collectionManager;
    private final Console console;
    private final CommandReceiver commandReceiver;

    public Clear(Console console, CollectionManager collectionManager, CommandReceiver commandReceiver) {
        super("clear", "clear collection");
        this.collectionManager = collectionManager;
        this.console = console;
        this.commandReceiver = commandReceiver;
    }


    @Override
    public boolean execute(String[] args) {
        return commandReceiver.Clear();
    }
}
