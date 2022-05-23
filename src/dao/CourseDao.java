package dao;

import pojo.Course;
import pojo.Teacher;
import util.AdminInterface;
import util.Audit;
import util.Dao;
import util.ELearningPlatformService;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class CourseDao extends Dao {

    private static CourseDao courseDao;

    private CourseDao() {
        super();
    }

    public static CourseDao getCourseDao() {
        if (courseDao == null) {
            courseDao = new CourseDao();
        }
        return courseDao;
    }

    @Override
    protected void createTable() {
        final String query = "CREATE TABLE IF NOT EXISTS Course (\n" +
                "id_course INT PRIMARY KEY,\n" +
                "id_teacher INT NOT NULL,\n" +
                "courseName VARCHAR(128) NOT NULL,\n" +
                "description VARCHAR(2048) NOT NULL,\n" +
                "FOREIGN KEY (id_teacher) REFERENCES Teacher(id_teacher) ON DELETE RESTRICT)";

        try {
            Statement statement = databaseConnection.createStatement();
            statement.execute(query);
        } catch (SQLException e) {
            Audit.logAction("Exception in CourseDao for create table: " + e);
        }
    }

    public void insertCourse(Course course) {
        try {
            final String query = "INSERT INTO Course(id_course, id_teacher, courseName, description) values(?,?,?,?)";
            PreparedStatement preparedStatement = databaseConnection.prepareStatement(query, Statement.NO_GENERATED_KEYS);
            preparedStatement.setInt(1, course.getId());
            preparedStatement.setInt(2, course.getTeacher().getId());
            preparedStatement.setString(3, course.getCourseName());
            preparedStatement.setString(4, course.getDescription());
            preparedStatement.execute();
        } catch (SQLException e) {
            Audit.logAction("Exception in CourseDao for insert: " + e);
        }
    }

    public void deleteCourse(int courseId) {
        try {
            final String query = "DELETE FROM Course WHERE id_course = ?";
            PreparedStatement preparedStatement = databaseConnection.prepareStatement(query, Statement.NO_GENERATED_KEYS);
            preparedStatement.setInt(1, courseId);
            preparedStatement.execute();

            AdminInterface.courses.remove(ELearningPlatformService.getCourseById(courseId));
        } catch (SQLException e) {
            Audit.logAction("Exception in CourseDao deleteCourse: " + e);
        }
    }

    public void updateCourseDescription(int courseId, String newDescription) {
        try {
            final String query = "UPDATE Course SET description = ? WHERE id_course = ?";
            PreparedStatement preparedStatement = databaseConnection.prepareStatement(query, Statement.NO_GENERATED_KEYS);
            preparedStatement.setString(1, newDescription);
            preparedStatement.setInt(2, courseId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            Audit.logAction("Exception in UserDao for updateCourseDescription: " + e);
        }
    }

    private Course mapToCourse(ResultSet resultSet) throws SQLException {
        Teacher teacher = (Teacher) ELearningPlatformService.getByUserId(resultSet.getInt(2));
        return new Course(resultSet.getInt(1), teacher, resultSet.getString(3), resultSet.getString(4));
    }

    public List<Course> getAllCourses() {
        List<Course> courses = new ArrayList<>();
        try {
            final String query = "SELECT * FROM Course";
            Statement statement = databaseConnection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                Course course = mapToCourse(resultSet);
                courses.add(course);
            }
        } catch (SQLException e) {
            Audit.logAction("Exception in CourseDao for getAllCourses: " + e);
        }
        return courses;
    }
}
