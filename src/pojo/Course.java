package pojo;

import org.jetbrains.annotations.NotNull;

public class Course {
    private int id;
    private @NotNull Teacher teacher;
    private String courseName;
    private String description;
    private static int count = 0;

    public Course(int id, @NotNull Teacher teacher, String courseName, String description) {
        this.id = id;
        this.teacher = teacher;
        this.courseName = courseName;
        this.description = description;
    }

    public Course(@NotNull Teacher teacher, String courseName, String description) {
        this.teacher = teacher;
        this.courseName = courseName;
        this.description = description;
        this.id = count;
        count++;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Course{" +
                "id=" + id +
                ", teacher=" + teacher +
                ", name='" + courseName + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
