package commands;


import collections.HumanBeing;
import managers.CollectionManager;

/**
 * command to update element by id
 */
@CommandInfo(name = "update", description = "update element by id", argsCount = 1,
        requiredObjectType = HumanBeing.class, argumentTypes = {Integer.class})
public class Update extends AbstractCommand {
    private final CollectionManager collectionManager;

    public Update(CollectionManager collectionManager) {
        super("update", "update element by id");
        this.collectionManager = collectionManager;
    }

    @Override
    public CommandResponce execute(String[] args, Object obj) {
        int key = Integer.parseInt(args[0]);
        if (!collectionManager.getHumanBeingCollection().containsKey(key)) {
            return new CommandResponce("Element with this key doesn't exist.", null);
        }
        HumanBeing humanBeing = (HumanBeing) obj;
        humanBeing.setId(key);
        collectionManager.removeById(key);
        collectionManager.insert(key, humanBeing);
        return new CommandResponce("Element updated successfully.", null);
    }
}
