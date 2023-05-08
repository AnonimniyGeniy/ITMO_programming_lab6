package commands;

import java.io.Serializable;

public class CommandResponce implements Serializable {
    private final String message;
    private final Object[] object;

    public CommandResponce(String message, Object[] object) {
        this.message = message;
        this.object = object;
    }

    public String getMessage() {
        return message;
    }

    public Object getObject() {
        return object;
    }
}
