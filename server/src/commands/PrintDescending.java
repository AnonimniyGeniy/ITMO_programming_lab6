package commands;


import collections.HumanBeing;
import managers.CollectionManager;
import managers.Console;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * prints all elements in descending order
 */
@CommandInfo(name = "print_descending", description = "print all elements in descending order")
public class PrintDescending extends AbstractCommand {
    private final CollectionManager collectionManager;
    private final Console console;

    public PrintDescending(Console console, CollectionManager collectionManager) {
        super("print_descending", "print all elements in descending order");
        this.collectionManager = collectionManager;
        this.console = console;
    }

    @Override
    public boolean execute(String[] args, Object obj) {
        try {
            if (args.length != 0) {
                console.println("Command doesn't take arguments");
                return false;
            }

            ArrayList<HumanBeing> elements = new ArrayList<>(List.of(collectionManager.getArray()));
            elements.sort(Comparator.reverseOrder());
            for (HumanBeing element : elements) {
                console.println(element.toString());
            }
            return true;
        } catch (Exception e) {
            console.println("Something went wrong");
            return false;
        }
    }

}
