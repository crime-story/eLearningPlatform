package dao;

import pojo.Student;
import util.Audit;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StudentDao extends UserDao {
    private static StudentDao studentDao;

    private StudentDao() {
        super();
    }

    public static StudentDao getStudentDao() {
        if (studentDao == null) {
            studentDao = new StudentDao();
        }
        return studentDao;
    }

    @Override
    protected void createTable() {
        super.createTable();
        final String query = "CREATE TABLE IF NOT EXISTS Student (\n" +
                "id_student INT PRIMARY KEY,\n" +
                "scholarShip VARCHAR(4) NOT NULL,\n" +
                "FOREIGN KEY (id_student) REFERENCES User (id_user) ON DELETE CASCADE)";
        try {
            Statement statement = databaseConnection.createStatement();
            statement.execute(query);
        } catch (SQLException e) {
            Audit.logAction("Exception in StudentDao for create table: " + e);
        }
    }

    public void insertStudent(Student student) {
        try {
            final String query = "INSERT INTO Student(id_student, scholarShip) values(?, ?)";
            insertUser(student);

            PreparedStatement preparedStatement = databaseConnection.prepareStatement(query, Statement.NO_GENERATED_KEYS);
            preparedStatement.setInt(1, student.getId());
            String scholarShip;
            if (student.isHasScholarShip()) {
                scholarShip = "Yes";
            } else {
                scholarShip = "No";
            }
            preparedStatement.setString(2, scholarShip);
            preparedStatement.execute();
        } catch (SQLException e) {
            Audit.logAction("Exception in StudentDao for insert: " + e);
        }
    }

    public void deleteStudent(int studentId) {
        try {
            final String query = "DELETE FROM Student WHERE id_student = ?";
            deleteUser(studentId);
            PreparedStatement preparedStatement = databaseConnection.prepareStatement(query, Statement.NO_GENERATED_KEYS);
            preparedStatement.setInt(1, studentId);
            preparedStatement.execute();
        } catch (SQLException e) {
            Audit.logAction("Exception in StudentDao for delete: " + e);
        }
    }

    public void updateStudentScholarShipAvailability(int studentId, boolean scholarShipStatus) {
        try {
            final String query = "UPDATE Student SET scholarShip = ? WHERE id_student = ?";
            PreparedStatement preparedStatement = databaseConnection.prepareStatement(query, Statement.NO_GENERATED_KEYS);
            String scholarShip;
            if (scholarShipStatus) {
                scholarShip = "Yes";
            } else {
                scholarShip = "No";
            }
            preparedStatement.setString(1, scholarShip);
            preparedStatement.setInt(2, studentId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            Audit.logAction("Exception in StudentDao for updateStudentScholarShipAvailability: " + e);
        }
    }

    private Student mapToStudent(ResultSet resultSet) throws SQLException {
        String scholarShip = resultSet.getString(8);
        boolean hasScholarShip;
        if (scholarShip.matches("Yes")) {
            hasScholarShip = true;
        } else {
            hasScholarShip = false;
        }
        return new Student(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3), resultSet.getString(4), resultSet.getString(5), resultSet.getString(6), resultSet.getString(7), hasScholarShip);
    }

    public List<Student> getAllStudents() {
        List<Student> students = new ArrayList<>();
        try {
            final String query = "SELECT s.id_student, u.username, u.phoneNumber, u.address, u.nationality, u.gender, u.birthdate, s.scholarShip FROM Student s, User u WHERE s.id_student = u.id_user";
            Statement statement = databaseConnection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                Student student = mapToStudent(resultSet);
                students.add(student);
            }
        } catch (SQLException e) {
            Audit.logAction("Exception in StudentDao for getAllStudents: " + e);
        }
        return students;
    }
}
