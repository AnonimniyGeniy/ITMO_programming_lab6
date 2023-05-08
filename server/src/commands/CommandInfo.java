package commands;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface CommandInfo {
    String name();
    String description();
    int argsCount();
    Class<?>[] argumentTypes();
    Class<?> requiredObjectType() default Void.class;
}
