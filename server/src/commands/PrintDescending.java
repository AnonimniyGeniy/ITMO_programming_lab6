package commands;


import managers.CollectionManager;
import managers.CommandReceiver;

/**
 * prints all elements in descending order
 */
public class PrintDescending extends AbstractCommand {
    private final CollectionManager collectionManager;
    private final Console console;
    private final CommandReceiver commandReceiver;

    public PrintDescending(Console console, CollectionManager collectionManager, CommandReceiver commandReceiver) {
        super("print_descending", "print all elements in descending order");
        this.collectionManager = collectionManager;
        this.console = console;
        this.commandReceiver = commandReceiver;
    }

    @Override
    public boolean execute(String[] args) {
        return commandReceiver.printDescending(args);
    }

}
