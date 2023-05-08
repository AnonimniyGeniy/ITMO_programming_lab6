package commands;

import collections.HumanBeing;
import managers.CollectionManager;

/**
 * Command that inserts element in the collection
 */
@CommandInfo(name = "insert", description = "Adds element to the collection by id. Syntax: insert id {element}", argsCount = 1, requiredObjectType = HumanBeing.class, argumentTypes = {Integer.class})
public class Insert extends AbstractCommand {

    private final CollectionManager collectionManager;

    public Insert(CollectionManager collectionManager) {
        super("Insert", "Add element to the collection by id. Syntax: insert id {element}");
        this.collectionManager = collectionManager;
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
    public CommandResponse execute(String[] args, Object obj) {
        //console.println(args[0] + args[1]);
        int key = Integer.parseInt(args[0]);
        if (collectionManager.getHumanBeingCollection().containsKey(key)) {
            return new CommandResponse("Element with this key already exists.", null);
        }
        HumanBeing humanBeing = null;
        humanBeing = (HumanBeing) obj;
        humanBeing.setId(key);
        collectionManager.insert(key, humanBeing);
        return new CommandResponse("Element added successfully.", null);

    }

}
