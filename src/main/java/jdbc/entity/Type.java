package jdbc.entity;

public enum Type {
    student, teacher;

    public String getTableName() {
        return  (this.equals(student))
        ? "students"
        : "teachers";
    }
}
