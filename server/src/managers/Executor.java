package managers;

import commands.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Class that controls the command input mode and manages Scanner and handling whole execution.
 * (receiver)
 */
public class Executor {
    private final List<String> recursionStack = new ArrayList<>();
    private final CommandManager commandManager;
    /**
     * constructor for Executor
     *
     * @param collectionManager - CommandManager
     *                          for managing commands
     *                          and command history
     */
    public Executor(CollectionManager collectionManager) {
        List<AbstractCommand> commands = new ArrayList<>();
        commands.add(new Info(collectionManager));
        commands.add(new Insert(collectionManager));
        //commands.add(new Exit(collectionManager));
        commands.add(new Save(collectionManager));
        commands.add(new Show(collectionManager));
        commands.add(new Remove(collectionManager));
        commands.add(new Update(collectionManager));
        commands.add(new Clear(collectionManager));
        commands.add(new RemoveGreater(collectionManager));
        commands.add(new ReplaceIfLower(collectionManager));
        commands.add(new GroupCountingByImpact(collectionManager));
        commands.add(new CountGreaterThanCar(collectionManager));
        commands.add(new PrintDescending(collectionManager));
        //commands.add(new ExecuteScript);
        var commandManager = new CommandManager(commands);
        commandManager.addCommand(new History(commandManager));
        commandManager.addCommand(new Help(commandManager.getCommandsArray()));
        this.commandManager = commandManager;
    }
    /**
     * method for executing commands in cli mode
     *
     * @param userCommand - command to execute
     * @return status of execution
     */
    public CommandResponce executeCommand(CommandRequest userCommand) {
        Command command = commandManager.getCommands().get(userCommand.getCommandName());
        CommandResponce response = new CommandResponce("OK", null);
        try {
            if (userCommand.getArguments().length == 0) {
                response = command.execute((String[]) userCommand.getArguments(), null);
                commandManager.addHistory(userCommand.getCommandName());
            } else {
                response = command.execute((String[]) userCommand.getArguments(), userCommand.getElement());
                commandManager.addHistory(userCommand.getCommandName());
            }
            return response;
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        return response;
    }
//    public Status executeCommand(CommandRequest userCommand){
//        Command command = commandManager.getCommands().get(userCommand.getCommandName());
//        try {
//            /*
//            if (userCommand[0].equals("exit")) {
//                return Status.EXIT;
//            } else if (userCommand[0].equals("execute_script")) {
//                if (!commandManager.getCommands().get("execute_script").execute(userCommand[1].trim().split(" ")))
//                    return Status.ERROR;
//                return scriptMode(userCommand[1]);
//            }
//            */
//
//            if (userCommand.getArguments().length == 0) {
//                command.execute((String[]) userCommand.getArguments(), null);
//                commandManager.addHistory(userCommand.getCommandName());
//            } else {
//                command.execute((String[]) userCommand.getArguments(), userCommand.getElement());
//                commandManager.addHistory(userCommand.getCommandName());
//            }
//            return Status.OK;
//        } catch (Exception e) {
//            console.println("Error: " + e.getMessage());
//            return Status.ERROR;
//        }
//    }

/**
 * method for executing commands in console mode
 */
    /*
    public void consoleMode() {
        Scanner userScanner = CommandParser.getScanner();
        try {
            Status status;
            String[] command;
            do {
                console.print("Enter command: ");
                status = Status.OK;
                try {
                    command = userScanner.nextLine().trim().split(" ", 2);
                    status = executeCommand(command);
                } catch (NoSuchElementException e) {
                    console.println("Ctrl-D pressed, finishing program...");
                    status = Status.EXIT;
                }

            } while (status != Status.EXIT);
        } catch (Exception e) {
            console.println(e.getMessage());
        }
    }
*/
    /**
     * method for executing commands in script mode
     *
     * @param arg - argument of command
     * @return status of execution
     */

    /*
    public Status scriptMode(String arg) {
        String[] command = new String[2];
        Status status;
        recursionStack.add(arg);
        if (!new File(arg).exists()) {
            arg = "../" + arg;
        }
        Scanner defaultScanner = CommandParser.getScanner();
        try (Scanner scanner = new Scanner(new File(arg))) {
            if (!scanner.hasNextLine()) {
                console.println("File is empty");
                return Status.ERROR;
            }

            CommandParser.setScanner(scanner);
            CommandParser.setFileMode();
            //do while loop for executing commands from file
            do {
                command = scanner.nextLine().trim().split(" ", 2);
                while (scanner.hasNextLine() && command[0].isEmpty()) {
                    command = scanner.nextLine().trim().split(" ", 2);
                }
                console.println("Executing command: " + command[0]);

                if (command[0].equals("execute_script")) {
                    for (String s : recursionStack) {
                        if (s.equals(command[1])) throw new RecursionInScriptRecursion();
                    }
                }
                status = executeCommand(command);


            } while (status == Status.OK && scanner.hasNextLine());

            CommandParser.setScanner(defaultScanner);
            CommandParser.setConsoleMode();
            if (status == Status.ERROR && !(command[0].equals("execute_script")) && !(command[1].isEmpty())) {
                console.println("Error in file " + arg);
            }
            return status;

        } catch (FileNotFoundException e) {
            console.println("File not found");
        } catch (RecursionInScriptRecursion e) {
            console.println("Recursion in script");
        } finally {
            CommandParser.setScanner(defaultScanner);
            CommandParser.setConsoleMode();
        }

        return Status.ERROR;
    }
    */


    /**
     * enum for status of execution
     */
    public enum Status {
        OK,
        ERROR,
        EXIT,
    }


}
