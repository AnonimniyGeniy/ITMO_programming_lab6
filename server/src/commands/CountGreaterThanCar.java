package commands;


import collections.Car;
import collections.HumanBeing;
import managers.CollectionManager;

/**
 * show all elements whose car field value is greater than the specified one
 */
@CommandInfo(name = "count_greater_than_car", description = "show all elements whose car field value is greater than the specified one",
        requiredObjectType = Car.class)
public class CountGreaterThanCar extends AbstractCommand {
    private final CollectionManager collectionManager;

    public CountGreaterThanCar(CollectionManager collectionManager) {
        super("count_greater_than_car", "show all elements whose car field value is greater than the specified one");
        this.collectionManager = collectionManager;
    }

    /**
     * CountGreaterThanCar command realization
     *
     * @param args arguments for command
     */
    @Override
    public CommandResponse execute(String[] args, Object obj) {
        int count = 0;
        Car car = (Car) obj;
        for (HumanBeing humanBeing : collectionManager.getHumanBeingCollection().values()) {
            if (humanBeing.getCar().compareTo(car) > 0) count++;
        }
        return new CommandResponse("There are " + count + " elements whose car field value is greater than the specified one", null);
    }


}
