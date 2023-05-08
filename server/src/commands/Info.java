package commands;

import managers.CollectionManager;
import managers.Console;

import java.time.LocalDateTime;

/**
 * Command that prints info about collection
 */
@CommandInfo(name = "info", description = "Prints info about collection")
public class Info extends AbstractCommand {
    private final Console console;
    private final CollectionManager collectionManager;

    public Info(Console console, CollectionManager collectionManager) {
        super("info", "Get info about collection.");
        this.collectionManager = collectionManager;
        this.console = console;
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
    public boolean execute(String[] args, Object obj) {
        LocalDateTime initTime = collectionManager.getCreationTime();
        console.println("Collection info:");
        console.println("Collection type: " + collectionManager.getHumanBeingCollection().getClass().getName());
        console.println("Collection size: " + collectionManager.getHumanBeingCollection().size());
        console.println("Initialization time: " + initTime);
        return true;
    }
}
