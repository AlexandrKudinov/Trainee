package jdbc.entity;

import java.util.Objects;

public class StudentCourse {
    private int id;
    private int studentId;
    private int courseId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StudentCourse that = (StudentCourse) o;
        return id == that.id &&
                studentId == that.studentId &&
                courseId == that.courseId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, studentId, courseId);
    }

    @Override
    public String toString() {
        return "StudentCourse{" +
                "jdbc.bl=" + id +
                ", studentId=" + studentId +
                ", courseId=" + courseId +
                '}';
    }
}
