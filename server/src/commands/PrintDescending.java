package commands;


import collections.HumanBeing;
import managers.CollectionManager;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * prints all elements in descending order
 */
@CommandInfo(name = "print_descending", description = "print all elements in descending order")
public class PrintDescending extends AbstractCommand {
    private final CollectionManager collectionManager;

    public PrintDescending(CollectionManager collectionManager) {
        super("print_descending", "print all elements in descending order");
        this.collectionManager = collectionManager;
    }

    @Override
    public CommandResponce execute(String[] args, Object obj) {
        try {
            ArrayList<HumanBeing> elements = new ArrayList<>(List.of(collectionManager.getArray()));
            elements.sort(Comparator.reverseOrder());
            return new CommandResponce("Elements in descending order", elements.toArray());

        } catch (Exception e) {
            return new CommandResponce("Something went wrong", null);
        }
    }

}
