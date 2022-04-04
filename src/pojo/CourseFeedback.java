package pojo;

public class CourseFeedback {
    private int id;
    private Course course;
    private float stars = 0;
    private String feedback;
    private static int count = 0;

    public CourseFeedback(int id, Course course, float stars, String feedback) {
        if (stars > 5 || stars < 0) {
            System.out.println("Invalid number of stars! Must be between 0 and 5!");
        } else {
            this.id = id;
            this.course = course;
            this.stars = stars;
            this.feedback = feedback;
            if (id > count) {
                count = id;
            }
        }
    }

    public CourseFeedback(Course course, float stars, String feedback) {
        this.course = course;
        this.stars = stars;
        this.feedback = feedback;
        this.id = count;
        count++;
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
