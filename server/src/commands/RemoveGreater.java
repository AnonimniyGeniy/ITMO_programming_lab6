package commands;

import collections.HumanBeing;
import managers.CollectionManager;

/**
 * command for removing all elements from collection that are greater than the specified one
 */
@CommandInfo(name = "remove_greater", description = "remove all elements from collection that are greater than the specified one", requiredObjectType = HumanBeing.class)
public class RemoveGreater extends AbstractCommand {
    private final CollectionManager collectionManager;

    public RemoveGreater(CollectionManager collectionManager) {
        super("remove_greater", "remove all elements from collection that are greater than the specified one");
        this.collectionManager = collectionManager;
    }

    @Override
    public CommandResponce execute(String[] args, Object obj) {
        collectionManager.removeGreater((HumanBeing) obj);
        return new CommandResponce("All greater elements was successfully removed", null);
    }
}
