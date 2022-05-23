package pojo;

public class Quiz {
    private int id;
    private Course course;
    private String quizName;
    private static int count = 0;

    public int getId() {
        return id;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public String getQuizName() {
        return quizName;
    }

    public void setQuizName(String quizName) {
        this.quizName = quizName;
    }

    public Quiz(int id, Course course, String name) {
        this.id = id;
        if (id > count) {
            count = id;
        }
        this.course = course;
        this.quizName = name;
    }

    public Quiz(Course course, String name) {
        this.course = course;
        this.quizName = name;
        count++;
        this.id = count;
    }

    @Override
    public String toString() {
        return "Quiz{" +
                "id=" + id +
                ", course=" + course +
                ", name='" + quizName + '\'' +
                '}';
    }
}
