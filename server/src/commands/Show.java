package commands;

import managers.CollectionManager;
import managers.Console;

/**
 * Show command
 * Shows all elements of collection
 */
@CommandInfo(name = "show", description = "Show all elements of collection")
public class Show extends AbstractCommand {
    final Console console;
    final CollectionManager collectionManager;

    public Show(Console console, CollectionManager collectionManager) {
        super("show", "Show all elements of collection");
        this.console = console;
        this.collectionManager = collectionManager;
    }

    @Override
    public boolean execute(String[] args, Object obj) {
        collectionManager.getHumanBeingCollection().forEach((key, value) -> console.println(value.toString()));
        return true;
    }

}
