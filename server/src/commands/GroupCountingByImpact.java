package commands;


import managers.CollectionManager;
import managers.CommandReceiver;
import managers.Console;
/**
 * group counting by impact speed
 */
public class GroupCountingByImpact extends AbstractCommand {
    private final CollectionManager collectionManager;
    private final Console console;
    private final CommandReceiver commandReceiver;

    public GroupCountingByImpact(Console console, CollectionManager collectionManager, CommandReceiver commandReceiver) {
        super("group_counting_by_impact", "group counting by impact speed");
        this.collectionManager = collectionManager;
        this.console = console;
        this.commandReceiver = commandReceiver;
    }


    @Override
    public boolean execute(String[] args) {
        return commandReceiver.groupCountingByImpact(args);

    }

}
