package pojo;

public class CourseMaterials {
    private Course course;
    private Material material;

    public CourseMaterials(Course course, Material material) {
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
        return "CourseMaterials{" +
                "course=" + course +
                ", material=" + material +
                '}';
    }
}
