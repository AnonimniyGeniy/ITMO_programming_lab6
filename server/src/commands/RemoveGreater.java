package commands;


import managers.CollectionManager;
import managers.CommandReceiver;
import managers.Console;


/**
 * command for removing all elements from collection that are greater than the specified one
 */

public class RemoveGreater extends AbstractCommand {
    private final CollectionManager collectionManager;
    private final Console console;
    private final CommandReceiver commandReceiver;

    public RemoveGreater(Console console, CollectionManager collectionManager, CommandReceiver commandReceiver) {
        super("remove_greater", "remove all elements from collection that are greater than the specified one");
        this.collectionManager = collectionManager;
        this.console = console;
        this.commandReceiver = commandReceiver;
    }

    @Override
    public boolean execute(String[] args) {
        return commandReceiver.removeGreater(args);
    }


}
