package commands;

import managers.CollectionManager;
import managers.CommandReceiver;

/**
 * Command that inserts element in the collection
 */
public class Insert extends AbstractCommand {

    private final Console console;
    private final CollectionManager collectionManager;
    private final CommandReceiver commandReceiver;

    public Insert(Console console, CollectionManager collectionManager, CommandReceiver commandReceiver) {
        super("Insert", "Add element to the collection by id. Syntax: insert id {element}");
        this.collectionManager = collectionManager;
        this.console = console;
        this.commandReceiver = commandReceiver;
    }

    @Override
    public String describe() {
        return "Inserts element in the collection by id. Syntax: insert id {element}.";
    }

    @Override
    public String getName() {
        return "insert";
    }

    @Override
    public boolean execute(String[] args) {
        return commandReceiver.insert(args);
    }
}
