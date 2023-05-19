package managers;

import collections.Car;
import collections.HumanBeing;
import commands.AbstractCommand;
import commands.CommandDescription;
import commands.CommandDescriptionFactory;
import commands.CommandResponse;

import java.time.LocalDateTime;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class CommandReceiver {
    private final CollectionManager collectionManager;

    public CommandReceiver(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }


    public CommandResponse help(String[] args, Object obj) {
        StringBuilder stringBuilder = new StringBuilder();
        AbstractCommand[] commands = (AbstractCommand[]) obj;
        Arrays.stream(commands)
                .filter(command -> !command.getName().equals("save"))
                .forEach(command -> stringBuilder.append(command.getName()).append(" - ").append(command.describe()).append("\n"));

        stringBuilder.append("help - ").append("shows help for available commands");
        return new CommandResponse(stringBuilder.toString(), null);
    }

    public CommandResponse update(String[] args, Object obj) {
        int key = Integer.parseInt(args[0]);
        Optional<HumanBeing> optionalHumanBeing = collectionManager.getHumanBeingCollection().values().stream()
                .filter(human -> human.getId() == key)
                .findFirst();

        if (optionalHumanBeing.isEmpty()) {
            return new CommandResponse("Element with this key doesn't exist.", null);
        }
        HumanBeing humanBeing = (HumanBeing) obj;
        humanBeing.setId(key);
        collectionManager.removeById(key);
        collectionManager.insert(key, humanBeing);
        return new CommandResponse("Element updated successfully.", null);
    }

    public CommandResponse save(String[] args, Object obj) {
        collectionManager.saveCollection();
        return new CommandResponse("Collection saved", null);
    }


    public CommandResponse replaceIfLower(String[] args, Object obj) {
        //check if key exists
        int key = Integer.parseInt(args[0]);
//
//        HumanBeing humanBeing = (HumanBeing) obj;
//        humanBeing.setId(key);
//        if (collectionManager.getHumanBeingCollection().get(key).compareTo(humanBeing) > 0) {
//            collectionManager.insert(key, humanBeing);
//            return new CommandResponse("Element changed successfully.", null);
//        } else {
//            return new CommandResponse("Element is not lower.", null);
//        }
        return collectionManager.getHumanBeingCollection().containsKey(key)
                ? Optional.ofNullable((HumanBeing) obj)
                .map(humanBeing -> {
                    humanBeing.setId(key);
                    if (collectionManager.getHumanBeingCollection().get(key).compareTo(humanBeing) > 0) {
                        collectionManager.insert(key, humanBeing);
                        return new CommandResponse("Element changed successfully.", null);
                    } else {
                        return new CommandResponse("Element is not lower.", null);
                    }
                })
                .orElse(new CommandResponse("Invalid object.", null))
                : new CommandResponse("Key doesn't exist.", null);

    }

    public CommandResponse removeGreater(String[] args, Object obj) {
        collectionManager.removeGreater((HumanBeing) obj);
        return new CommandResponse("All greater elements was successfully removed", null);
    }

    public CommandResponse remove(String[] args, Object obj) {
        int id = Integer.parseInt(args[0]);
        boolean isRemoved = collectionManager.removeById(id);

        String message = isRemoved ? "Element with id " + id + " was removed" : "Element with id " + id + " wasn't found";

        return new CommandResponse(message, null);

    }

    public CommandResponse printDesc(String[] args, Object obj) {
//        ArrayList<HumanBeing> elements = new ArrayList<>(List.of(collectionManager.getArray()));
//        elements.sort(Comparator.reverseOrder());
//        return new CommandResponse("Elements in descending order", elements);
        List<HumanBeing> elements = Arrays.stream(collectionManager.getArray())
                .sorted(Comparator.reverseOrder())
                .collect(Collectors.toList());
        return new CommandResponse("Elements in descending order", elements);
    }

    public CommandResponse insert(String[] args, Object obj) {
        //console.println(args[0] + args[1]);
        int key = Integer.parseInt(args[0]);
//        if (collectionManager.getHumanBeingCollection().containsKey(key)) {
//            return new CommandResponse("Element with this key already exists.", null);
//        }
        Optional<HumanBeing> optionalHumanBeing = collectionManager.getHumanBeingCollection().values().stream()
                .filter(human -> human.getId() == key)
                .findFirst();

        if (optionalHumanBeing.isPresent()) {
            return new CommandResponse("Element with this key already exists.", null);
        }

        HumanBeing humanBeing = null;
        humanBeing = (HumanBeing) obj;
        humanBeing.setId(key);
        collectionManager.insert(key, humanBeing);
        return new CommandResponse("Element added successfully.", null);

    }


    public CommandResponse info(String[] args, Object obj) {
        LocalDateTime initTime = collectionManager.getCreationTime();
        String message = "Collection info:\n" + "Collection type: " + collectionManager.getHumanBeingCollection().getClass().getName() + "\n" + "Collection size: " + collectionManager.getHumanBeingCollection().size() + "\n" + "Initialization time: " + initTime;
        return new CommandResponse(message, null);
    }

    public CommandResponse show(String[] args, Object obj) {
        //ArrayList<HumanBeing> coll = new ArrayList<>(List.of(collectionManager.getArray()));
        List<HumanBeing> elements = Arrays.stream(collectionManager.getArray())
                .sorted(Comparator.reverseOrder())
                .toList();

        return new CommandResponse("Showed all elements of collection", elements);
    }

    public CommandResponse clear(String[] args, Object object) {
        collectionManager.setHumanBeingCollection(new TreeMap<>());
        return new CommandResponse("Collection was cleared", null);
    }

    public CommandResponse countGreaterThanCar(String[] args, Object obj) {
        Car car = (Car) obj;
        int count = (int) Arrays.stream(collectionManager.getArray())
                .filter(humanBeing -> humanBeing.getCar().compareTo(car) > 0)
                .count();
        return new CommandResponse("There are " + count + " elements whose car field value is greater than the specified one", null);
    }

    public CommandResponse groupCountingByImpact(String[] args, Object obj) {
//        HumanBeing[] elements = collectionManager.getArray();
//        int[] impactSpeed = new int[elements.length];
//        for (int i = 0; i < elements.length; i++) {
//            impactSpeed[i] = elements[i].getImpactSpeed().intValue();
//        }
//        Map<Integer, Integer> counter = new HashMap<>();
//
//        for (int j : impactSpeed) {
//            if (counter.containsKey(j)) {
//                counter.replace(j, counter.get(j) + 1);
//            } else {
//                counter.put(j, 1);
//            }
//        }
//
//        StringBuilder stringBuilder = new StringBuilder();
//
//        for (Map.Entry<Integer, Integer> entry : counter.entrySet()) {
//            stringBuilder.append("Impact speed: ")
//                    .append(entry.getKey())
//                    .append(" - ")
//                    .append(entry.getValue())
//                    .append(" elements")
//                    .append(System.lineSeparator());
//        }
//
//        String result = stringBuilder.toString();
        HumanBeing[] elements = collectionManager.getArray();
        int[] impactSpeed = Arrays.stream(elements)
                .mapToInt(humanBeing -> humanBeing.getImpactSpeed().intValue())
                .toArray();

        Map<Integer, Integer> counter = Arrays.stream(impactSpeed)
                .boxed()
                .collect(Collectors.toMap(
                        Function.identity(),
                        value -> 1,
                        Integer::sum
                ));

        String result = counter.entrySet().stream()
                .map(entry -> "Impact speed: " + entry.getKey() + " - " + entry.getValue() + " elements")
                .collect(Collectors.joining(System.lineSeparator()));
        return new CommandResponse("Grouped counting by impact speed entries", result);
    }

    public CommandResponse history(String[] args, Object obj) {
//        StringBuilder commands = new StringBuilder();
//        String message = "Last 13 commands:";
        CommandManager commandManager = (CommandManager) obj;
//        for (String command : commandManager.getCommandHistory()) {
//            commands.append(command).append("\n");
//        }

        String commands = Arrays.stream(commandManager.getCommandHistory().toArray())
                .map(Object::toString)
                .collect(Collectors.joining(System.lineSeparator()));
        return new CommandResponse("Last 13 commands: ", commands);
    }

    public CommandResponse connect(String[] args, Object obj) {
        CommandManager commandManager = (CommandManager) obj;
        AbstractCommand[] commands = commandManager.getCommandsArray();
//        ArrayList<CommandDescription> commandDescriptions = new ArrayList<>();
//        for (AbstractCommand command : commands) {
//            if (!command.getName().equals("save")) {
//                commandDescriptions.add(CommandDescriptionFactory.createCommandDescription(command.getClass()));
//            }
//        }
        List<CommandDescription> commandDescriptions = Arrays.stream(commands)
                .filter(command -> !command.getName().equals("save"))
                .map(command -> CommandDescriptionFactory.createCommandDescription(command.getClass()))
                .collect(Collectors.toList());

        return new CommandResponse("Got commands", commandDescriptions);
    }
}
