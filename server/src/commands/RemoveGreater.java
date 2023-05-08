package commands;


import collections.HumanBeing;
import managers.CollectionManager;
import managers.Console;


/**
 * command for removing all elements from collection that are greater than the specified one
 */
@CommandInfo(name = "remove_greater", description = "remove all elements from collection that are greater than the specified one", requiredObjectType = HumanBeing.class)
public class RemoveGreater extends AbstractCommand {
    private final CollectionManager collectionManager;
    private final Console console;

    public RemoveGreater(Console console, CollectionManager collectionManager) {
        super("remove_greater", "remove all elements from collection that are greater than the specified one");
        this.collectionManager = collectionManager;
        this.console = console;

    }

    @Override
    public boolean execute(String[] args, Object obj) {
        collectionManager.removeGreater((HumanBeing) obj);
        return true;
    }


}
