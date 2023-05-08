package commands;


import managers.CollectionManager;
import managers.CommandReceiver;
import managers.Console;
/**
 * Command for exit from program
 *
 * @author AnonimniyGeniy
 */
public class Exit extends AbstractCommand {
    private final Console console;
    private final CollectionManager collectionManager;
    private final CommandReceiver commandReceiver;

    public Exit(Console console, CollectionManager collectionManager, CommandReceiver commandReceiver) {

        super("exit", "Stop the program.");
        this.console = console;
        this.collectionManager = collectionManager;
        this.commandReceiver = commandReceiver;
    }

    /**
     * Execute command
     *
     * @param args
     * @return Execution result
     */
    @Override
    public boolean execute(String[] args) {
        return commandReceiver.exit(args);
    }
}
