package commands;


import collections.Car;
import collections.HumanBeing;
import managers.CollectionManager;
import managers.Console;

/**
 * show all elements whose car field value is greater than the specified one
 */
@CommandInfo(name = "count_greater_than_car", description = "show all elements whose car field value is greater than the specified one",
        requiredObjectType = Car.class)
public class CountGreaterThanCar extends AbstractCommand {
    private final CollectionManager collectionManager;
    private final Console console;

    public CountGreaterThanCar(Console console, CollectionManager collectionManager) {
        super("count_greater_than_car", "show all elements whose car field value is greater than the specified one");
        this.collectionManager = collectionManager;
        this.console = console;
    }

    /**
     * CountGreaterThanCar command realization
     *
     * @param args arguments for command
     */
    @Override
    public boolean execute(String[] args, Object obj) {
        console.println("Command doesn't need any arguments");
        int count = 0;
        Car car = (Car) obj;
        for (HumanBeing humanBeing : collectionManager.getHumanBeingCollection().values()) {
            if (humanBeing.getCar().compareTo(car) > 0) count++;
        }
        console.println("There are " + count + " elements whose car field value is greater than the specified one");
        return true;
    }


}
