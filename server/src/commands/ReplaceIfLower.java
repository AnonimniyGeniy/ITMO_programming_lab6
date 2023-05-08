package commands;

import managers.CollectionManager;
import managers.CommandReceiver;


/**
 * command for replacing value by key if new value is lower
 */
public class ReplaceIfLower extends AbstractCommand {
    private final CollectionManager collectionManager;
    private final Console console;
    private final CommandReceiver commandReceiver;

    public ReplaceIfLower(Console console, CollectionManager collectionManager, CommandReceiver commandReceiver) {
        super("replace_if_lower", "replace value by key if new value is lower");
        this.collectionManager = collectionManager;
        this.console = console;
        this.commandReceiver = commandReceiver;
    }


    @Override
    public boolean execute(String[] args) {
        return commandReceiver.replaceIfLower(args);
    }

}
