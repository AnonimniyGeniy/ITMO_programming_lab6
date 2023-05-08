package commands;


import managers.CommandReceiver;

/**
 * Command to execute script from file
 */
@CommandInfo(name = "execute_script", description = "Execute script from file", argsCount = 1, argumentTypes = {String.class}, requiredObjectType = Void.class)
public class ExecuteScript extends AbstractCommand {

    public ExecuteScript( ) {
        super("execute_script", "Execute script from file");
    }

    @Override
    public boolean execute(String[] args, Object obj) {
        if (args.length == 0) {
            console.printErr("No file name specified, usage: execute_script <file_name>");
            return false;
        }
        if (args.length > 1) {
            console.printErr("Too many arguments, usage: execute_script <file_name>");
            return false;
        }
        String fileName = args[0];
        console.println("Executing script from file " + fileName);
        return true;
    }
}
