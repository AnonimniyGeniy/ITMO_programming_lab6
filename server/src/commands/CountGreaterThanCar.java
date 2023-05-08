package commands;


import managers.CollectionManager;
import managers.CommandReceiver;
import managers.Console;

/**
 * show all elements whose car field value is greater than the specified one
 */
public class CountGreaterThanCar extends AbstractCommand {
    private final CollectionManager collectionManager;
    private final Console console;
    private final CommandReceiver commandReceiver;

    public CountGreaterThanCar(Console console, CollectionManager collectionManager, CommandReceiver commandReceiver) {
        super("count_greater_than_car", "show all elements whose car field value is greater than the specified one");
        this.collectionManager = collectionManager;
        this.console = console;
        this.commandReceiver = commandReceiver;
    }

    @Override
    public boolean execute(String[] args) {
        return commandReceiver.countGreaterThanCar(args);
    }


}
