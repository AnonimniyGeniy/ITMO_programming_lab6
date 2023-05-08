package commands;


import managers.CollectionManager;
import managers.Console;
/**
 * comamnd that saves collection to file
 */
@CommandInfo(name = "save", description = "Save collection to file.")
public class Save extends AbstractCommand {
    private final Console console;
    private final CollectionManager collectionManager;

    public Save(Console console, CollectionManager collectionManager) {
        super("save", "Save collection to file.");
        this.console = console;
        this.collectionManager = collectionManager;
    }

    @Override
    public boolean execute(String[] args, Object obj) {
        collectionManager.saveCollection();
        return true;
    }

}
