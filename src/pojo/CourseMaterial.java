package pojo;

public class CourseMaterial {
    private Course course;
    private Material material;

    public CourseMaterial(Course course, Material material) {
        this.course = course;
        this.material = material;
    }

    public Course getCourse() {
        return course;
    }

    public Material getMaterial() {
        return material;
    }

    @Override
    public String toString() {
        return "CourseMaterial{" +
                "course=" + course +
                ", material=" + material +
                '}';
    }
}
