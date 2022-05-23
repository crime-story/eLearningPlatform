package dao;

import pojo.Course;
import pojo.CourseFeedback;
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

public final class CourseFeedbackDao extends Dao {
    private static CourseFeedbackDao courseFeedbackDao;

    private CourseFeedbackDao() {
        super();
    }

    public static CourseFeedbackDao getCourseFeedbackDao() {
        if (courseFeedbackDao == null) {
            courseFeedbackDao = new CourseFeedbackDao();
        }
        return courseFeedbackDao;
    }

    @Override
    protected void createTable() {
        final String query = "CREATE TABLE IF NOT EXISTS CourseFeedback (\n" +
                "id_courseFeedback INT PRIMARY KEY,\n" +
                "id_course INT NOT NULL,\n" +
                "stars FLOAT NOT NULL,\n" +
                "feedback VARCHAR(2048) NOT NULL,\n" +
                "FOREIGN KEY (id_course) REFERENCES Course(id_course) ON DELETE CASCADE)";
        try {
            Statement statement = databaseConnection.createStatement();
            statement.execute(query);
        } catch (SQLException e) {
            Audit.logAction("Exception in CourseFeedbackDao for create table: " + e);
        }
    }

    public void insertCourseFeedback(CourseFeedback courseFeedback) {
        final String query = "INSERT INTO CourseFeedback(id_courseFeedback, id_course, stars, feedback) values(?, ?, ?, ?)";
        try {
            PreparedStatement preparedStatement = databaseConnection.prepareStatement(query, Statement.NO_GENERATED_KEYS);
            preparedStatement.setInt(1, courseFeedback.getId());
            preparedStatement.setInt(2, courseFeedback.getCourse().getId());
            preparedStatement.setFloat(3, courseFeedback.getStars());
            preparedStatement.setString(4, courseFeedback.getFeedback());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            Audit.logAction("Exception in CourseFeedbackDao for insert: " + e);
        }
    }

    public void deleteCourseFeedback(int courseFeedbackId) {
        try {
            final String query = "DELETE FROM CourseFeedback WHERE id_courseFeedback = ?";
            PreparedStatement preparedStatement = databaseConnection.prepareStatement(query, Statement.NO_GENERATED_KEYS);
            preparedStatement.setInt(1, courseFeedbackId);
            preparedStatement.execute();

            AdminInterface.courseFeedbacks.remove(ELearningPlatformService.getCourseFeedBackById(courseFeedbackId));
        } catch (SQLException e) {
            Audit.logAction("Exception in CourseFeedbackDao for delete: " + e);
        }
    }

    public void updateCourseFeedback(int courseFeedbackId, Float newNumberOfStars, String newFeedback) {
        try {
            final String query = "UPDATE CourseFeedback SET stars = ?, feedback = ? WHERE id_courseFeedback = ?";
            PreparedStatement preparedStatement = databaseConnection.prepareStatement(query, Statement.NO_GENERATED_KEYS);
            preparedStatement.setFloat(1, newNumberOfStars);
            preparedStatement.setString(2, newFeedback);
            preparedStatement.setInt(3, courseFeedbackId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            Audit.logAction("Exception in CourseFeedbackDao for update: " + e);
        }
    }

    public void updateCourseFeedbackStars(int courseFeedbackId, Float newNumberOfStars) {
        try {
            final String query = "UPDATE CourseFeedback SET stars = ? WHERE id_courseFeedback = ?";
            PreparedStatement preparedStatement = databaseConnection.prepareStatement(query, Statement.NO_GENERATED_KEYS);
            preparedStatement.setFloat(1, newNumberOfStars);
            preparedStatement.setInt(2, courseFeedbackId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            Audit.logAction("Exception in CourseFeedbackDao for update number of stars: " + e);
        }
    }

    public void updateCourseFeedbackFeedback(int courseFeedbackId, String newFeedback) {
        try {
            final String query = "UPDATE CourseFeedback SET feedback = ? WHERE id_courseFeedback = ?";
            PreparedStatement preparedStatement = databaseConnection.prepareStatement(query, Statement.NO_GENERATED_KEYS);
            preparedStatement.setString(1, newFeedback);
            preparedStatement.setInt(2, courseFeedbackId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            Audit.logAction("Exception in CourseFeedbackDao for update feedback: " + e);
        }
    }

    private CourseFeedback mapToCourseFeedback(ResultSet resultSet) throws SQLException {
        Course course = ELearningPlatformService.getCourseById(resultSet.getInt(2));
        return new CourseFeedback(resultSet.getInt(1), course, resultSet.getFloat(3), resultSet.getString(4));
    }

    public List<CourseFeedback> getAllFeedbacks() {
        List<CourseFeedback> courseFeedbacks = new ArrayList<>();
        try {
            final String query = "SELECT * FROM CourseFeedback";
            Statement statement = databaseConnection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                CourseFeedback courseFeedback = mapToCourseFeedback(resultSet);
                courseFeedbacks.add(courseFeedback);
            }
        } catch (SQLException e) {
            Audit.logAction("Exception in CourseFeedback for getAllCourseFeedbacks: " + e);
        }
        return courseFeedbacks;
    }

    public List<CourseFeedback> getAllFeedbacksForAnSpecificCourse(int courseId) {
        List<CourseFeedback> courseFeedbacksSpecificCourse = new ArrayList<>();
        try {
            final String query = "SELECT * FROM CourseFeedback WHERE id_course = ?";
            PreparedStatement preparedStatement = databaseConnection.prepareStatement(query);
            preparedStatement.setInt(1, courseId);
            ResultSet resultSet = preparedStatement.executeQuery();;
            while (resultSet.next()) {
                CourseFeedback courseFeedback = mapToCourseFeedback(resultSet);
                courseFeedbacksSpecificCourse.add(courseFeedback);
            }
        } catch (SQLException e) {
            Audit.logAction("Exception in CourseFeedback for getAllFeedbacksForAnSpecificCourse: " + e);
        }
        return courseFeedbacksSpecificCourse;
    }
}
