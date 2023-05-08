package commands;


import collections.HumanBeing;
import managers.CollectionManager;
import managers.Console;

/**
 * command to update element by id
 */
@CommandInfo(name = "update", description = "update element by id", argsCount = 1,
        requiredObjectType = HumanBeing.class, argumentTypes = {Integer.class})
public class Update extends AbstractCommand {
    private final CollectionManager collectionManager;
    private final Console console;

    public Update(Console console, CollectionManager collectionManager) {
        super("update", "update element by id");
        this.collectionManager = collectionManager;
        this.console = console;
    }

    @Override
    public boolean execute(String[] args, Object obj) {
        int key = Integer.parseInt(args[0]);
        if (!collectionManager.getHumanBeingCollection().containsKey(key)) {
            console.println("Element with this key doesn't exist.");
            return false;
        }
        HumanBeing humanBeing = (HumanBeing) obj;
        humanBeing.setId(key);
        console.println(humanBeing);
        collectionManager.removeById(key);
        collectionManager.insert(key, humanBeing);

        console.println("Element updated successfully.");
        return true;
    }
}
