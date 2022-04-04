package pojo;

public class UserCourseRepartition {
    private Course course;
    private User user;
    private String startDate;
    private String endDate;

    public UserCourseRepartition(Course course, User user, String startDate, String endDate) {
        this.course = course;
        this.user = user;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public Course getCourse() {
        return course;
    }

    public User getUser() {
        return user;
    }

    public String getStartDate() {
        return startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    @Override
    public String toString() {
        return "CourseRepartition{" +
                "course=" + course +
                ", user=" + user +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                '}';
    }
}
