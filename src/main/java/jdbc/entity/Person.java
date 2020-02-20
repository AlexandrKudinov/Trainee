package jdbc.entity;


import lombok.NonNull;
import lombok.Value;
import lombok.experimental.SuperBuilder;

@Value
@SuperBuilder
public class Person extends Entity {
    @NonNull
    private Type type;

    @NonNull
    private String name;
}
