package commands;

import managers.CollectionManager;
import managers.CommandReceiver;
import managers.Console;

/**
 * Command that prints info about collection
 */
public class Info extends AbstractCommand {
    private final Console console;
    private final CollectionManager collectionManager;
    private final CommandReceiver commandReceiver;

    public Info(Console console, CollectionManager collectionManager, CommandReceiver commandReceiver) {
        super("info", "Get info about collection.");
        this.collectionManager = collectionManager;
        this.console = console;
        this.commandReceiver = commandReceiver;
    }

    @Override
    public String describe() {
        return "Get info about collection.";
    }

    @Override
    public String getName() {
        return "info";
    }

    @Override
    public boolean execute(String[] args) {
        return commandReceiver.info(args);
    }
}
