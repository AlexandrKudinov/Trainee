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
    private Date startDateTime;
    @NonNull
    private Date endDateTime;
    @NonNull
    private Status status;
    @NonNull
    private int teacherId;
    @NonNull
    private String createdAt;

    @Override
    public String toString() {
        return "Course{" +
                "id ="+getId()+
                ", title='" + title + '\'' +
                ", startDatetime=" + startDateTime +
                ", endDatetime=" + endDateTime +
                ", status=" + status +
                ", teacherId=" + teacherId +
                ", createdAt='" + createdAt + '\'' +
                '}';
    }
}
