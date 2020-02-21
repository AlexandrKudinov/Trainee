package jdbc.entity;

import lombok.NonNull;
import lombok.Value;
import lombok.experimental.SuperBuilder;

import java.sql.Date;

@Value
@SuperBuilder
public class Course extends Entity {
    @NonNull
    private String title;
    @NonNull
    private Date startDatetime;
    @NonNull
    private Date endDatetime;
    @NonNull
    private Status status;
    @NonNull
    private int teacherId;
    @NonNull
    private String createdAt;


}
