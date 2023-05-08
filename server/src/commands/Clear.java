package commands;

import managers.CollectionManager;

import java.util.TreeMap;

/**
 * command for clearing collection
 */
@CommandInfo(name = "clear", description = "clear collection", argsCount = 0, argumentTypes = {}, requiredObjectType = Void.class)
public class Clear extends AbstractCommand {
    private final CollectionManager collectionManager;

    public Clear(CollectionManager collectionManager) {
        super("clear", "clear collection");
        this.collectionManager = collectionManager;
    }


    /**
     * Execute command clear
     *
     * @param args
     * @param object
     * @return Execution result
     */
    @Override
    public CommandResponse execute(String[] args, Object object) {
        collectionManager.setHumanBeingCollection(new TreeMap<>());
        return new CommandResponse("Collection was cleared", null);
    }
}
