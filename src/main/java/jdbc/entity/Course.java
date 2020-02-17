package jdbc.entity;

import java.sql.Date;
import java.util.Objects;

public class Course {
    private int id;
    private String title;
    private Date startDatetime;
    private Date endDatetime;
    private Status status;
    private int teacherId;
    private String createdAt;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String name) {
        this.title = name;
    }

    public Date getStartDatetime() {
        return startDatetime;
    }

    public void setStartDatetime(Date start_datetime) {
        this.startDatetime = start_datetime;
    }

    public Date getEndDatetime() {
        return endDatetime;
    }

    public void setEndDatetime(Date end_datetime) {
        this.endDatetime = end_datetime;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public int getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(int teacherId) {
        this.teacherId = teacherId;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdId) {
        this.createdAt = createdId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Course course = (Course) o;
        return id == course.id &&
                teacherId == course.teacherId &&
                createdAt == course.createdAt &&
                title.equals(course.title) &&
                startDatetime.equals(course.startDatetime) &&
                endDatetime.equals(course.endDatetime) &&
                status == course.status;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, startDatetime, endDatetime, status, teacherId, createdAt);
    }

    @Override
    public String toString() {
        return "Course{" +
                "jdbc.bl=" + id +
                ", title='" + title + '\'' +
                ", start_datetime=" + startDatetime +
                ", end_datetime=" + endDatetime +
                ", status=" + status +
                ", teacher_id=" + teacherId +
                ", created_id=" + createdAt +
                '}';
    }
}
