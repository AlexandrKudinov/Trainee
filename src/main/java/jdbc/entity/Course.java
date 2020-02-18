package jdbc.entity;

import lombok.Builder;
import lombok.NonNull;
import lombok.Value;

import java.sql.Date;

@Value
@Builder
public class Course {
    @NonNull
    private int id;
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
