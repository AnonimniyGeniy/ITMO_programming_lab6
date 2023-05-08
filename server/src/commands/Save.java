package commands;


import managers.CollectionManager;
/**
 * comamnd that saves collection to file
 */
@CommandInfo(name = "save", description = "Save collection to file.")
public class Save extends AbstractCommand {
    private final CollectionManager collectionManager;

    public Save(CollectionManager collectionManager) {
        super("save", "Save collection to file.");
        this.collectionManager = collectionManager;
    }

    @Override
    public CommandResponce execute(String[] args, Object obj) {
        collectionManager.saveCollection();
        return new CommandResponce("Collection saved", null);
    }

}
