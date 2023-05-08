package collections;

import other.Validatable;


/**
 * Class for Car
 */
public class Car implements Validatable {
    private final String name; //Поле не может быть null

    public Car(String name) {
        this.name = name;
    }

    /**
     * @return validation result
     */
    @Override
    public boolean validate() {
        return name != null;
    }

    @Override
    public String toString() {
        return "Car " + name;
    }

    /**
     * compare two car objects
     */

    public int compareTo(Car o) {
        return name.length() - o.name.length();
    }
}