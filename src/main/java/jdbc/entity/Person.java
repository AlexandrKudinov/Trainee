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

    @Override
    public String toString() {
        return "Person{" +
                "id=" +getId()+
                ", type=" + type +
                ", name='" + name + '\'' +
                '}';
    }
}
