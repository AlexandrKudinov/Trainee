package jdbc.entity;

import java.util.Objects;

public class StudentCourse {
    private int id;
    private int student_id;
    private int course_id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getStudent_id() {
        return student_id;
    }

    public void setStudent_id(int student_id) {
        this.student_id = student_id;
    }

    public int getCourse_id() {
        return course_id;
    }

    public void setCourse_id(int course_id) {
        this.course_id = course_id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StudentCourse that = (StudentCourse) o;
        return id == that.id &&
                student_id == that.student_id &&
                course_id == that.course_id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, student_id, course_id);
    }

    @Override
    public String toString() {
        return "StudentCourse{" +
                "jdbc.bl=" + id +
                ", student_id=" + student_id +
                ", course_id=" + course_id +
                '}';
    }
}
