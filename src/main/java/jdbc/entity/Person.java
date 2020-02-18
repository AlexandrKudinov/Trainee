package jdbc.entity;

import lombok.Builder;
import lombok.NonNull;
import lombok.Value;

import java.util.Objects;

@Value
@Builder
public class Person {
    @NonNull
    private Type type;
    @NonNull
    private int id;
    @NonNull
    private String name;
}
