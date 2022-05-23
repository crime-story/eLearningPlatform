package pojo;

public class CourseFeedback {
    private final int id;
    private final Course course;
    private float stars = 0;
    private final String feedback;
    private static int count = 0;

    public CourseFeedback(int id, Course course, float stars, String feedback) {
        this.id = id;
        if (id > count) {
            count = id;
        }
        this.course = course;
        this.stars = stars;
        this.feedback = feedback;
    }

    public CourseFeedback(Course course, float stars, String feedback) {
        this.course = course;
        this.stars = stars;
        this.feedback = feedback;
        count++;
        this.id = count;
    }

    public CourseFeedback(Course course, String feedback) {
        this.course = course;
        this.feedback = feedback;
        this.id = count;
        count++;
    }

    public int getId() {
        return id;
    }

    public Course getCourse() {
        return course;
    }

    public float getStars() {
        return stars;
    }

    public String getFeedback() {
        return feedback;
    }

    @Override
    public String toString() {
        return "CourseFeedback{" +
                "id=" + id +
                ", course=" + course +
                ", stars=" + stars +
                ", feedback='" + feedback + '\'' +
                '}';
    }
}
