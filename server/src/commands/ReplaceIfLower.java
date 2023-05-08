package commands;

import collections.HumanBeing;
import managers.CollectionManager;

/**
 * command for replacing value by key if new value is lower
 */
@CommandInfo(name = "replace_if_lower", description = "replace value by key if new value is lower", argsCount = 1,
        argumentTypes = {Integer.class} , requiredObjectType = HumanBeing.class)
public class ReplaceIfLower extends AbstractCommand {
    private final CollectionManager collectionManager;

    public ReplaceIfLower(CollectionManager collectionManager) {
        super("replace_if_lower", "replace value by key if new value is lower");
        this.collectionManager = collectionManager;
    }


    @Override
    public CommandResponse execute(String[] args, Object obj) {
        //check if key exists
        int key = Integer.parseInt(args[0]);
        if (!collectionManager.getHumanBeingCollection().containsKey(key)) {
            return new CommandResponse("Key doesn't exist.", null);
        }
        HumanBeing humanBeing = (HumanBeing) obj;
        humanBeing.setId(key);
        if (collectionManager.getHumanBeingCollection().get(key).compareTo(humanBeing) > 0) {
            collectionManager.insert(key, humanBeing);
            return new CommandResponse("Element changed successfully.", null);
        } else {
            return new CommandResponse("Element is not lower.", null);
        }
    }
}
