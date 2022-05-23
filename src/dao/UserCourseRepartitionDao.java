package dao;

import pojo.Course;
import pojo.User;
import pojo.UserCourseRepartition;
import util.AdminInterface;
import util.Audit;
import util.Dao;
import util.ELearningPlatformService;

import java.sql.*;
import java.text.Format;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public final class UserCourseRepartitionDao extends Dao {
    private static UserCourseRepartitionDao userCourseRepartitionDao;

    private UserCourseRepartitionDao() {
        super();
    }

    public static UserCourseRepartitionDao getUserCourseRepartitionDao() {
        if (userCourseRepartitionDao == null) {
            userCourseRepartitionDao = new UserCourseRepartitionDao();
        }
        return userCourseRepartitionDao;
    }

    @Override
    protected void createTable() {
        final String query = "CREATE TABLE IF NOT EXISTS UserCourseRepartition (\n" +
                "id_course INT NOT NULL,\n" +
                "id_user INT NOT NULL,\n" +
                "startDate Date NOT NULL,\n" +
                "endDate Date NOT NULL,\n" +
                "FOREIGN KEY (id_course) REFERENCES Course(id_course) ON DELETE CASCADE,\n" +
                "FOREIGN KEY (id_user) REFERENCES User(id_user) ON DELETE CASCADE)";

        try {
            Statement statement = databaseConnection.createStatement();
            statement.execute(query);
        } catch (SQLException e) {
            Audit.logAction("Exception in UserCourseRepartitionDao for create table: " + e);
        }
    }

    public void insertUserCourseRepartition(UserCourseRepartition userCourseRepartition) {
        try {
            final String query = "INSERT INTO UserCourseRepartition(id_course, id_user, startDate, endDate) values(?, ?, ?, ?)";
            PreparedStatement preparedStatement = databaseConnection.prepareStatement(query, Statement.NO_GENERATED_KEYS);
            preparedStatement.setInt(1, userCourseRepartition.getCourse().getId());
            preparedStatement.setInt(2, userCourseRepartition.getUser().getId());
            Date startDate = new java.sql.Date((new SimpleDateFormat("dd-MM-yyyy").parse(userCourseRepartition.getStartDate()).getTime()));
            preparedStatement.setDate(3, startDate);
            Date endDate = new java.sql.Date((new SimpleDateFormat("dd-MM-yyyy").parse(userCourseRepartition.getEndDate()).getTime()));
            preparedStatement.setDate(4, endDate);
            preparedStatement.execute();
        } catch (SQLException e) {
            Audit.logAction("Exception in UserCourseRepartitionDao for insert: " + e);
        } catch (ParseException e) {
            Audit.logAction("ParseException in UserCourseRepartitionDao for insert: " + e);
        }
    }

    public void deleteUserCourseRepartition(int courseId, int userId) {
        try {
            final String query = "DELETE FROM UserCourseRepartition WHERE id_course = ? and id_user = ?";
            PreparedStatement preparedStatement = databaseConnection.prepareStatement(query, Statement.NO_GENERATED_KEYS);
            preparedStatement.setInt(1, courseId);
            preparedStatement.setInt(2, userId);
            preparedStatement.execute();

            AdminInterface.userCourseRepartitions.removeAll(ELearningPlatformService.getUserCourseRepartitionByCourseIdAndUserId(courseId, userId));
        } catch (SQLException e) {
            Audit.logAction("Exception in UserCourseRepartitionDao for delete: " + e);
        }
    }

    public void updateUserCourseRepartition(int courseId, int userId, String startDate, String endDate) {
        try {
            final String query = "UPDATE UserCourseRepartition SET startDate = ?, endDate = ? WHERE id_course = ? and id_user = ?";
            PreparedStatement preparedStatement = databaseConnection.prepareStatement(query, Statement.NO_GENERATED_KEYS);

            Date startDate1 = new java.sql.Date((new SimpleDateFormat("dd-MM-yyyy").parse(startDate).getTime()));
            Date endDate1 = new java.sql.Date((new SimpleDateFormat("dd-MM-yyyy").parse(endDate).getTime()));

            preparedStatement.setDate(1, startDate1);
            preparedStatement.setDate(2, endDate1);
            preparedStatement.setInt(3, courseId);
            preparedStatement.setInt(4, userId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            Audit.logAction("Exception in UserCourseRepartition for updateUserCourseRepartition: " + e);
        } catch (ParseException e) {
            Audit.logAction("ParseException in UserCourseRepartition for updateUserCourseRepartition: " + e);
        }
    }

    private UserCourseRepartition mapToUserCourseRepartition(ResultSet resultSet) throws SQLException {
        Course course = ELearningPlatformService.getCourseById(resultSet.getInt(1));
        User user = ELearningPlatformService.getByUserId(resultSet.getInt(2));
        Format formatter = new SimpleDateFormat("dd-MM-yyyy");
        String startDate = formatter.format(resultSet.getDate(3));
        String endDate = formatter.format(resultSet.getDate(4));
        return new UserCourseRepartition(course, user, startDate, endDate);
    }

    public List<UserCourseRepartition> getAllUserCourseRepartitions() {
        List<UserCourseRepartition> userCourseRepartitions = new ArrayList<>();
        try {
            final String query = "SELECT * FROM UserCourseRepartition";
            Statement statement = databaseConnection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                UserCourseRepartition userCourseRepartition = mapToUserCourseRepartition(resultSet);
                userCourseRepartitions.add(userCourseRepartition);
            }
        } catch (SQLException e) {
            Audit.logAction("Exception in UserCourseRepartition for getAllUserCourseRepartitions: " + e);
        }
        return userCourseRepartitions;
    }
}
