package commands;

import managers.CollectionManager;
import managers.Console;

import java.util.TreeMap;

/**
 * command for clearing collection
 */
@CommandInfo(name = "clear", description = "clear collection", argsCount = 0, argumentTypes = {}, requiredObjectType = Void.class)
public class Clear extends AbstractCommand {
    private final CollectionManager collectionManager;
    private final Console console;

    public Clear(Console console, CollectionManager collectionManager) {
        super("clear", "clear collection");
        this.collectionManager = collectionManager;
        this.console = console;
    }


    /**
     * Execute command clear
     *
     * @param args
     * @param object
     * @return Execution result
     */
    @Override
    public boolean execute(String[] args, Object object) {
        collectionManager.setHumanBeingCollection(new TreeMap<>());
        console.println("Collection was cleared");
        return true;
    }
}
