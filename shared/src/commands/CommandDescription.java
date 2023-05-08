package commands;

import java.io.Serializable;

public class CommandDescription implements Serializable {
    private final String name;
    private final String description;

    public CommandDescription(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

}
