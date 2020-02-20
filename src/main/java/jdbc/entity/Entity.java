package jdbc.entity;

import lombok.Getter;
import lombok.NonNull;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@Getter
public  abstract class Entity {
    @NonNull
   private int id;
}
