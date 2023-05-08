package commands;

import managers.CollectionManager;

import java.time.LocalDateTime;

/**
 * Command that prints info about collection
 */
@CommandInfo(name = "info", description = "Prints info about collection")
public class Info extends AbstractCommand {
    private final CollectionManager collectionManager;

    public Info(CollectionManager collectionManager) {
        super("info", "Get info about collection.");
        this.collectionManager = collectionManager;
    }

    @Override
    public String describe() {
        return "Get info about collection.";
    }

    @Override
    public String getName() {
        return "info";
    }

    @Override
    public CommandResponse execute(String[] args, Object obj) {
        LocalDateTime initTime = collectionManager.getCreationTime();
        String message = "Collection info:\n" +
                "Collection type: " + collectionManager.getHumanBeingCollection().getClass().getName() + "\n" +
                "Collection size: " + collectionManager.getHumanBeingCollection().size() + "\n" +
                "Initialization time: " + initTime;
        return new CommandResponse(message, null);
    }
}
