package commands;


import exceptions.WrongArgsAmount;
import managers.CollectionManager;
import managers.Console;

/**
 * command for removing element from collection by id
 */
@CommandInfo(name = "remove", description = "remove element from collection by id", argsCount = 1, argumentTypes = {int.class}, requiredObjectType = Void.class)
public class Remove extends AbstractCommand {
    private final CollectionManager collectionManager;
    private final Console console;

    public Remove(Console console, CollectionManager collectionManager) {
        super("remove", "remove element from collection by id");
        this.collectionManager = collectionManager;
        this.console = console;
    }

    @Override
    public boolean execute(String[] args, Object obj) {
        try {
            if (args.length != 1) {
                console.printErr("This command requires 1 argument(id)");
                throw new WrongArgsAmount();
            }
            int id = Integer.parseInt(args[0]);
            if (collectionManager.removeById(id)) {
                console.println("Element with id " + id + " was removed");
                return true;
            } else {
                console.println("Element with id " + id + " was not found");
                return false;
            }
        } catch (NumberFormatException e) {
            console.println("Id must be integer");

        } catch (WrongArgsAmount wrongArgsAmount) {
            console.println("Wrong amount of arguments");
        }
        return false;
    }
}
