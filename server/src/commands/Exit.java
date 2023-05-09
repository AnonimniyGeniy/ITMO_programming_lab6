package commands;


import managers.CollectionManager;
/**
 * Command for exit from program
 *
 * @author AnonimniyGeniy
 */


//public class Exit extends AbstractCommand {
//    private final Console console;
//    private final CollectionManager collectionManager;
//
//    public Exit(Console console, CollectionManager collectionManager) {
//
//        super("exit", "Stop the program.");
//        this.console = console;
//        this.collectionManager = collectionManager;
//    }
//
//    /**
//     * Execute command
//     *
//     * @param args
//     * @return Execution result
//     */
//    @Override
//    public boolean execute(String[] args, Object commandObjectArgument) {
//        if (args.length > 0) {
//            return false;
//        } else {
//            console.println("Saving collection to file...");
//            System.exit(0);
//            return true;
//        }
//    }
//}
