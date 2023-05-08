package collections;

import other.Validatable;
import java.io.Serializable;

/**
 * Class for Coordinates
 */
public class Coordinates implements Validatable, Serializable{
    private final double x; //Максимальное значение поля: 180
    private final int y;

    public Coordinates(double x, int y) {
        this.x = x;
        this.y = y;

    }

    public boolean validate() {
        return x <= 180;
    }

    @Override
    public String toString() {
        return "Coordinates{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}