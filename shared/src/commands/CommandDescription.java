package commands;

import java.io.Serializable;
import java.util.List;


/**
 * Class that holds command name, description, count of arguments, type of arguments, and object info.
 */

public class CommandDescription implements Serializable {
    private final String name;
    private final String description;
    private final int argsCount;
    private final List<Class<?>> argumentTypes;
    private final Class<?> requiredObjectType;

    public CommandDescription(String name, String description, int argumentCount, List<Class<?>> argumentTypes, Class<?> requiredObjectType) {
        this.name = name;
        this.description = description;
        this.argsCount = argumentCount;
        this.argumentTypes = argumentTypes;
        this.requiredObjectType = requiredObjectType;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public int getArgsCount() {
        return argsCount;
    }

    public List<Class<?>> getArgumentTypes() {
        return argumentTypes;
    }

    public Class<?> getRequiredObjectType() {
        return requiredObjectType;
    }

}