package commands;

import collections.HumanBeing;
import managers.CollectionManager;
import managers.Console;

/**
 * command for replacing value by key if new value is lower
 */
@CommandInfo(name = "replace_if_lower", description = "replace value by key if new value is lower", argsCount = 1,
        argumentTypes = {Integer.class} , requiredObjectType = HumanBeing.class)
public class ReplaceIfLower extends AbstractCommand {
    private final CollectionManager collectionManager;
    private final Console console;

    public ReplaceIfLower(Console console, CollectionManager collectionManager) {
        super("replace_if_lower", "replace value by key if new value is lower");
        this.collectionManager = collectionManager;
        this.console = console;
    }


    @Override
    public boolean execute(String[] args, Object obj) {
        //check if key exists
        int key = Integer.parseInt(args[0]);
        if (!collectionManager.getHumanBeingCollection().containsKey(key)) {
            console.println("Key doesn't exist.");
            return false;
        }
        HumanBeing humanBeing = (HumanBeing) obj;
        humanBeing.setId(key);

        console.println(humanBeing);

        if (collectionManager.getHumanBeingCollection().get(key).compareTo(humanBeing) > 0) {
            collectionManager.insert(key, humanBeing);
            console.println("Element changed successfully.");
        } else {
            console.println("Element is not lower.");
        }
        return true;
    }

}
