package jdbc.entity;

import java.util.Date;
import java.util.Objects;

public class Course {
    private int id;
    private String name;
    private Date start_datetime;
    private Date end_datetime;
    private Status status;
    private int teacher_id;
    private int created_id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getStart_datetime() {
        return start_datetime;
    }

    public void setStart_datetime(Date start_datetime) {
        this.start_datetime = start_datetime;
    }

    public Date getEnd_datetime() {
        return end_datetime;
    }

    public void setEnd_datetime(Date end_datetime) {
        this.end_datetime = end_datetime;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public int getTeacher_id() {
        return teacher_id;
    }

    public void setTeacher_id(int teacher_id) {
        this.teacher_id = teacher_id;
    }

    public int getCreated_id() {
        return created_id;
    }

    public void setCreated_id(int created_id) {
        this.created_id = created_id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Course course = (Course) o;
        return id == course.id &&
                teacher_id == course.teacher_id &&
                created_id == course.created_id &&
                name.equals(course.name) &&
                start_datetime.equals(course.start_datetime) &&
                end_datetime.equals(course.end_datetime) &&
                status == course.status;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, start_datetime, end_datetime, status, teacher_id, created_id);
    }

    @Override
    public String toString() {
        return "Course{" +
                "jdbc.bl=" + id +
                ", name='" + name + '\'' +
                ", start_datetime=" + start_datetime +
                ", end_datetime=" + end_datetime +
                ", status=" + status +
                ", teacher_id=" + teacher_id +
                ", created_id=" + created_id +
                '}';
    }
}
