package commands;


import managers.CollectionManager;
import managers.CommandReceiver;

/**
 * comamnd that saves collection to file
 */
public class Save extends AbstractCommand {
    private final Console console;
    private final CollectionManager collectionManager;
    private final CommandReceiver commandReceiver;

    public Save(Console console, CollectionManager collectionManager, CommandReceiver commandReceiver) {
        super("save", "Save collection to file.");
        this.console = console;
        this.collectionManager = collectionManager;
        this.commandReceiver = commandReceiver;
    }

    @Override
    public boolean execute(String[] args) {
        return commandReceiver.save(args);
    }

}
