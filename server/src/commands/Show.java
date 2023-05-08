package commands;

import managers.CollectionManager;

/**
 * Show command
 * Shows all elements of collection
 */
@CommandInfo(name = "show", description = "Show all elements of collection")
public class Show extends AbstractCommand {
    final CollectionManager collectionManager;

    public Show(CollectionManager collectionManager) {
        super("show", "Show all elements of collection");
        this.collectionManager = collectionManager;
    }

    @Override
    public CommandResponce execute(String[] args, Object obj) {
        return new CommandResponce("Showed all elements of collection", collectionManager.getArray());
    }

}
