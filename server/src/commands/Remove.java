package commands;


import managers.CollectionManager;

/**
 * command for removing element from collection by id
 */
@CommandInfo(name = "remove", description = "remove element from collection by id", argsCount = 1, argumentTypes = {int.class}, requiredObjectType = Void.class)
public class Remove extends AbstractCommand {
    private final CollectionManager collectionManager;

    public Remove(CollectionManager collectionManager) {
        super("remove", "remove element from collection by id");
        this.collectionManager = collectionManager;
    }

    @Override
    public CommandResponce execute(String[] args, Object obj) {

        int id = Integer.parseInt(args[0]);
        if (collectionManager.removeById(id)) {
            return new CommandResponce("Element with id " + id + " was removed", null);
        } else {
            return new CommandResponce("Element with id " + id + " wasn't found", null);
        }

    }
}
