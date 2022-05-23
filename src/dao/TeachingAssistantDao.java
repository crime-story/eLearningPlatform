package dao;

import pojo.Teacher;
import pojo.TeachingAssistant;
import util.Audit;
import util.ELearningPlatformService;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TeachingAssistantDao extends UserDao {
    private static TeachingAssistantDao teachingAssistantDao;

    private TeachingAssistantDao() {
        super();
    }

    public static TeachingAssistantDao getTeachingAssistantDao() {
        if (teachingAssistantDao == null) {
            teachingAssistantDao = new TeachingAssistantDao();
        }
        return teachingAssistantDao;
    }

    @Override
    protected void createTable() {
        super.createTable();
        final String query = "CREATE TABLE IF NOT EXISTS TeachingAssistant (\n" +
                "id_teachingAssistant INT PRIMARY KEY,\n" +
                "id_teacher INT NOT NULL,\n" +
                "salary FLOAT NOT NULL,\n" +
                "FOREIGN KEY (id_teachingAssistant) REFERENCES User(id_user),\n" +
                "FOREIGN KEY (id_teacher) REFERENCES Teacher (id_teacher) ON DELETE CASCADE)";
        try {
            Statement statement = databaseConnection.createStatement();
            statement.execute(query);
        } catch (SQLException e) {
            Audit.logAction("Exception in TeachingAssistantDao for create table: " + e);
        }
    }

    public void insertTeachingAssistant(TeachingAssistant teachingAssistant) {
        try {
            final String query = "INSERT INTO TeachingAssistant(id_teachingAssistant, id_teacher, salary) values(?, ?, ?)";
            insertUser(teachingAssistant);

            PreparedStatement preparedStatement = databaseConnection.prepareStatement(query, Statement.NO_GENERATED_KEYS);
            preparedStatement.setInt(1, teachingAssistant.getId());
            preparedStatement.setInt(2, teachingAssistant.getBoss().getId());
            preparedStatement.setFloat(3, teachingAssistant.getSalary());
            preparedStatement.execute();
        } catch (SQLException e) {
            Audit.logAction("Exception in TeachingAssistantDao for insert: " + e);
        }
    }

    public void deleteTeachingAssistant(int teachingAssistantId) {
        try {
            final String query = "DELETE FROM TeachingAssistant WHERE id_teachingAssistant = ?";
            deleteUser(teachingAssistantId);
            PreparedStatement preparedStatement = databaseConnection.prepareStatement(query, Statement.NO_GENERATED_KEYS);
            preparedStatement.setInt(1, teachingAssistantId);
            preparedStatement.execute();
        } catch (SQLException e) {
            Audit.logAction("Exception in TeachingAssistantDao for delete: " + e);
        }
    }

    public void updateTeachingAssistantsSalary(int teachingAssistantId, int procent) {
        try {
            final String query = "UPDATE TeachingAssistant SET salary = salary + (salary * ?) / 100 WHERE id_teachingAssistant = ?";
            PreparedStatement preparedStatement = databaseConnection.prepareStatement(query, Statement.NO_GENERATED_KEYS);
            preparedStatement.setInt(1, procent);
            preparedStatement.setInt(2, teachingAssistantId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            Audit.logAction("Exception in TeachingAssistantDao for updateTeachingAssistantSalary: " + e);
        }
    }

    private TeachingAssistant mapToTeachingAssistant(ResultSet resultSet) throws SQLException {
        Teacher teacher = (Teacher) ELearningPlatformService.getByUserId(resultSet.getInt(8));
        return new TeachingAssistant(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3), resultSet.getString(4), resultSet.getString(5), resultSet.getString(6), resultSet.getString(7), teacher ,resultSet.getFloat(9));
    }

    public List<TeachingAssistant> getAllTeachingAssistants() {
        List<TeachingAssistant> teachingAssistants = new ArrayList<>();
        try {
            final String query = "SELECT ta.id_teachingAssistant, u.username, u.phoneNumber, u.address, u.nationality, u.gender, u.birthdate, ta.id_teacher, ta.salary FROM TeachingAssistant ta, User u WHERE ta.id_teachingAssistant = u.id_user";
            Statement statement = databaseConnection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                TeachingAssistant teachingAssistant = mapToTeachingAssistant(resultSet);
                teachingAssistants.add(teachingAssistant);
            }
        } catch (SQLException e) {
            Audit.logAction("Exception in TeachingAssistantDao for getAllTeachingAssistants: " + e);
        }
        return teachingAssistants;
    }
}
