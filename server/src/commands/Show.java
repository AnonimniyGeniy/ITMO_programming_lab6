package commands;

import managers.CollectionManager;
import managers.CommandReceiver;
import managers.Console;

/**
 * Show command
 * Shows all elements of collection
 */
public class Show extends AbstractCommand {
    final Console console;
    final CollectionManager collectionManager;
    private final CommandReceiver commandReceiver;

    public Show(Console console, CollectionManager collectionManager, CommandReceiver commandReceiver) {
        super("show", "Show all elements of collection");
        this.console = console;
        this.collectionManager = collectionManager;
        this.commandReceiver = commandReceiver;
    }

    @Override
    public boolean execute(String[] args) {
        return commandReceiver.show(args);
    }

}
