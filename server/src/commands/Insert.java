package commands;

import collections.HumanBeing;
import exceptions.EmptyFieldException;
import exceptions.IncorrectScriptInputException;
import exceptions.InvalidObjectException;
import exceptions.WrongArgsAmount;
import managers.CollectionManager;
import managers.Console;

/**
 * Command that inserts element in the collection
 */
@CommandInfo(name = "insert", description = "Adds element to the collection by id. Syntax: insert id {element}",
        argsCount = 1, requiredObjectType = HumanBeing.class, argumentTypes = {Integer.class})
public class Insert extends AbstractCommand {

    private final Console console;
    private final CollectionManager collectionManager;

    public Insert(Console console, CollectionManager collectionManager) {
        super("Insert", "Add element to the collection by id. Syntax: insert id {element}");
        this.collectionManager = collectionManager;
        this.console = console;
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
    public boolean execute(String[] args, Object obj) {
        try {
            if (args.length != 1) {
                console.printErr("This command requires 1 argument(key).");
                throw new WrongArgsAmount();
            }
            //console.println(args[0] + args[1]);
            int key = Integer.parseInt(args[0]);
            if (collectionManager.getHumanBeingCollection().containsKey(key)) {
                console.println("Element with this key already exists.");
                return false;
            }
            HumanBeing humanBeing = null;
            humanBeing = (HumanBeing) obj;
            humanBeing.setId(key);
            console.println(humanBeing);
            collectionManager.insert(key, humanBeing);

            console.println("Element added successfully.");
            return true;

        } catch (WrongArgsAmount e) {
            console.println("Wrong amount of arguments.");
        }
        return false;
    }

}
