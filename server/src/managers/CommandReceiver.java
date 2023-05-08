package managers;


import collections.Car;
import collections.HumanBeing;
import collections.askers.CarAsker;
import collections.askers.HumanBeingAsker;
import commands.AbstractCommand;
import exceptions.EmptyFieldException;
import exceptions.IncorrectScriptInputException;
import exceptions.InvalidObjectException;
import exceptions.WrongArgsAmount;

import java.time.LocalDateTime;
import java.util.*;


/**
 * Receiver for commands in Command pattern
 */
public class CommandReceiver {
    private final Console console;
    private final CollectionManager collectionManager;

    public CommandReceiver(Console console, CollectionManager collectionManager) {
        this.console = console;
        this.collectionManager = collectionManager;
    }

    /**
     * Clear command realization
     *
     * @return true if collection was cleared
     */
    public boolean Clear() {
        collectionManager.setHumanBeingCollection(new TreeMap<>());
        console.println("Collection was cleared");
        return true;
    }


    /**
     * CountGreaterThanCar command realization
     *
     * @param args arguments for command
     */
    public boolean countGreaterThanCar(String[] args) {
        try {
            if (args.length != 0) {
                console.println("Command doesn't need any arguments");
                return false;
            }
            int count = 0;
            Car car = new CarAsker(console).askCar();
            for (HumanBeing humanBeing : collectionManager.getHumanBeingCollection().values()) {
                if (humanBeing.getCar().compareTo(car) > 0) count++;
            }
            console.println("There are " + count + " elements whose car field value is greater than the specified one");
            return true;
        } catch (IncorrectScriptInputException e) {
            console.println("Incorrect input.");
        }
        return false;
    }

    /**
     * exit command realization
     */
    public boolean exit(String[] args) {
        //if len of args > 0 print error in console by user console
        //else exit
        if (args.length > 0) {
            return false;
        } else {
            console.println("Saving collection to file...");
            System.exit(0);
            return true;
        }
    }

    /**
     * group_counting_by_impact command realization
     */
    public boolean groupCountingByImpact(String[] args) {
        try {
            if (args.length != 0) {
                console.println("Wrong amount of arguments.");
                return false;
            }
            HumanBeing[] elements = collectionManager.getArray();
            int[] impactSpeed = new int[elements.length];
            for (int i = 0; i < elements.length; i++) {
                impactSpeed[i] = elements[i].getImpactSpeed().intValue();
            }
            Map<Integer, Integer> counter = new HashMap<>();

            for (int i = 0; i < impactSpeed.length; i++) {
                if (counter.containsKey(impactSpeed[i])) {
                    counter.put(impactSpeed[i], counter.get(impactSpeed[i]) + 1);
                } else {
                    counter.put(impactSpeed[i], 1);
                }
            }
            for (Map.Entry<Integer, Integer> entry : counter.entrySet()) {
                console.println("Impact speed: " + entry.getKey() + " - " + entry.getValue() + " elements");
            }
            return true;
        } catch (NumberFormatException e) {
            console.println("Wrong type of arguments.");
            return false;
        }
    }


    /**
     * help command realization
     *
     * @param args arguments for command
     * @return true if help was printed
     */
    public boolean help(String[] args, AbstractCommand[] commands) {
        try {
            if (args.length != 0) {
                console.println("Command doesn't take arguments");
                return false;
            }
            for (AbstractCommand command : commands) {
                console.println(command.getName() + " - " + command.describe());
            }
            console.println("help - " + "shows help for available commands");
            return true;
        } catch (Exception e) {
            console.println("Something went wrong");
            return false;
        }
    }

    /**
     * history command realization
     */
    public boolean history(String[] args, CommandManager commandManager) {
        if (args.length > 0) {
            console.printErr("The command doesn't take any arguments, usage: history");
            return false;
        }
        console.println("Last 13 commands:");
        for (String command : commandManager.getCommandHistory()) {
            console.println(command);
        }
        return true;
    }

    /**
     * info command realization
     */
    public boolean info(String[] args) {
        if (args.length > 0) {
            console.printErr("The command doesn't take any arguments, usage: info");
            return false;
        }
        LocalDateTime initTime = collectionManager.getCreationTime();
        console.println("Collection info:");
        console.println("Collection type: " + collectionManager.getHumanBeingCollection().getClass().getName());
        console.println("Collection size: " + collectionManager.getHumanBeingCollection().size());
        console.println("Initialization time: " + initTime);
        return true;
    }

    /**
     * print_field_descending_impact_speed command realization
     */
    public boolean printDescending(String[] args) {
        try {
            if (args.length != 0) {
                console.println("Command doesn't take arguments");
                return false;
            }

            ArrayList<HumanBeing> elements = new ArrayList<>();
            elements.addAll(List.of(collectionManager.getArray()));
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

    /**
     * remove_by_id command realization
     *
     * @param args arguments for command
     * @return true if element was removed
     * @throws WrongArgsAmount if amount of arguments is wrong
     */
    public boolean remove(String[] args) {
        try {
            if (args.length != 1) {
                console.printErr("This command requires 1 argument(id)");
                throw new WrongArgsAmount();
            }
            int id = Integer.parseInt(args[0]);
            if (collectionManager.removeById(id)) {
                console.println("Element with id " + id + " was removed");
                return true;
            } else {
                console.println("Element with id " + id + " was not found");
                return false;
            }
        } catch (NumberFormatException e) {
            console.println("Id must be integer");

        } catch (WrongArgsAmount wrongArgsAmount) {
            console.println("Wrong amount of arguments");
        }
        return false;
    }

    /**
     * remove_greater command realization
     *
     * @param args arguments for command
     * @return true if elements were removed
     * @throws WrongArgsAmount if amount of arguments is wrong
     */
    public boolean removeGreater(String[] args) {
        try {
            if (args.length != 1) {
                console.printErr("This command requires 1 argument(key).");
                throw new WrongArgsAmount();
            }
            int id = Integer.parseInt(args[0]);
            if (collectionManager.removeById(id)) {
                console.println("Element with id " + id + " was removed");
                return true;
            } else {
                console.println("Element with id " + id + " was not found");
                return false;
            }
        } catch (NumberFormatException e) {
            console.println("Id must be integer");

        } catch (WrongArgsAmount wrongArgsAmount) {
            console.println("Wrong amount of arguments");
        }
        return false;
    }

    /**
     * replace if lower command realization
     */
    public boolean replaceIfLower(String[] args) {
        try {
            if (args.length != 1) {
                console.printErr("This command requires 1 argument(key).");
                throw new WrongArgsAmount();
            }
            //check if key exists
            int key = Integer.parseInt(args[0]);
            if (!collectionManager.getHumanBeingCollection().containsKey(key)) {
                console.println("Key doesn't exist.");
                return false;
            }
            HumanBeing humanBeing = null;
            try {
                humanBeing = new HumanBeingAsker(console, collectionManager).build();
            } catch (IncorrectScriptInputException e) {
                console.println("Incorrect input.");
            } catch (EmptyFieldException e) {
                console.println("Field can't be empty.");
            } catch (InvalidObjectException e) {
                console.println("Invalid object.");
            }
            humanBeing.setId(key);
            console.println(humanBeing);
            if (collectionManager.getHumanBeingCollection().get(key).compareTo(humanBeing) > 0) {
                collectionManager.insert(key, humanBeing);
                console.println("Element changed successfully.");
            }else{
                console.println("Element is not lower.");
            }


            return true;

        } catch (WrongArgsAmount e) {
            console.println("Wrong amount of arguments.");
        }
        return false;
    }


    /**
     * save command realization
     */
    public boolean save(String[] args) {
        if (args.length > 0) {
            console.printErr("The command doesn't take any arguments, usage: save");
            return false;
        }
        collectionManager.saveCollection();
        return true;
    }

    /**
     * show command realization
     */
    public boolean show(String[] args) {
        if (args.length != 0) {
            console.println("Command doesn't take any arguments");
            return false;
        }
        collectionManager.getHumanBeingCollection().forEach((key, value) -> console.println(value.toString()));
        return true;
    }

    /**
     * update command realization
     */
    public boolean update(String[] args) {
        try {
            if (args.length != 1) {
                console.printErr("This command requires 1 argument(key).");
                throw new WrongArgsAmount();
            }
            int key = Integer.parseInt(args[0]);
            if (!collectionManager.getHumanBeingCollection().containsKey(key)) {
                console.println("Element with this key doesn't exist.");
                return false;
            }
            HumanBeing humanBeing = null;
            try {
                humanBeing = new HumanBeingAsker(console, collectionManager).build();
            } catch (IncorrectScriptInputException e) {
                console.println("Incorrect input.");
            } catch (EmptyFieldException e) {
                console.println("Field can't be empty.");
            } catch (InvalidObjectException e) {
                console.println("Invalid object.");
            }
            humanBeing.setId(key);
            console.println(humanBeing);
            collectionManager.removeById(key);
            collectionManager.insert(key, humanBeing);

            console.println("Element updated successfully.");
            return true;

        } catch (WrongArgsAmount e) {
            console.println("Wrong amount of arguments.");
        }
        return false;
    }


    /**
     * insert command realization
     */
    public boolean insert(String[] args) {
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
            try {
                humanBeing = new HumanBeingAsker(console, collectionManager).build();
            } catch (IncorrectScriptInputException e) {
                console.println("Incorrect input.");
            } catch (EmptyFieldException e) {
                console.println("Field can't be empty.");
            } catch (InvalidObjectException e) {
                console.println("Invalid object.");
            }
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

    /**
     * execute_script command realization
     */
    public boolean executeScript(String[] args) {
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
