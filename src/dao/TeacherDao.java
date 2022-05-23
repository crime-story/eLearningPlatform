package dao;

import pojo.Teacher;
import util.Audit;

import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public final class TeacherDao extends UserDao {
    private static TeacherDao teacherDao;

    private TeacherDao() {
        super();
    }

    public static TeacherDao getTeacherDao() {
        if (teacherDao == null) {
            teacherDao = new TeacherDao();
        }
        return teacherDao;
    }

    @Override
    protected void createTable() {
        super.createTable();
        final String query = "CREATE TABLE IF NOT EXISTS Teacher (\n" +
                "id_teacher INT PRIMARY KEY,\n" +
                "ranking VARCHAR(32) NOT NULL,\n" +
                "hireDate DATE NOT NULL,\n" +
                "salary FLOAT NOT NULL,\n" +
                "FOREIGN KEY (id_teacher) REFERENCES User (id_user) ON DELETE CASCADE)";
        try {
            Statement statement = databaseConnection.createStatement();
            statement.execute(query);
        } catch (SQLException e) {
            Audit.logAction("Exception in TeacherDao for create table: " + e);
        }
    }

    public void insertTeacher(Teacher teacher) {
        try {
            final String query = "INSERT INTO Teacher(id_teacher, ranking, hireDate, salary) values(?, ?, ?, ?)";
            insertUser(teacher);

            PreparedStatement preparedStatement = databaseConnection.prepareStatement(query, Statement.NO_GENERATED_KEYS);
            preparedStatement.setInt(1, teacher.getId());
            preparedStatement.setString(2, teacher.getRank());
            Date date = new java.sql.Date((new SimpleDateFormat("dd-MM-yyyy").parse(teacher.getHireDate()).getTime()));
            preparedStatement.setDate(3, date);
            preparedStatement.setFloat(4, teacher.getSalary());
            preparedStatement.execute();
        } catch (SQLException e) {
            Audit.logAction("Exception in TeacherDao for insert: " + e);
        } catch (ParseException e) {
            Audit.logAction("ParseException in TeacherDao for insert: " + e);
        }
    }

    public void deleteTeacher(int teacherId) {
        try {
            final String query = "DELETE FROM Teacher WHERE id_teacher = ?";
            deleteUser(teacherId);
            PreparedStatement preparedStatement = databaseConnection.prepareStatement(query, Statement.NO_GENERATED_KEYS);
            preparedStatement.setInt(1, teacherId);
            preparedStatement.execute();
        } catch (SQLException e) {
            Audit.logAction("Exception in TeacherDao for delete: " + e);
        }
    }

    private Teacher mapToTeacher(ResultSet resultSet) throws SQLException {
        return new Teacher(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3), resultSet.getString(4), resultSet.getString(5), resultSet.getString(6), resultSet.getString(7), resultSet.getString(8), resultSet.getString(9), resultSet.getFloat(10));
    }

    public void updateTeacherSalary(int teacherId, int procent) {
        try {
            final String query = "UPDATE Teacher SET salary = salary + (salary * ?) / 100 WHERE id_teacher = ?";
            PreparedStatement preparedStatement = databaseConnection.prepareStatement(query, Statement.NO_GENERATED_KEYS);
            preparedStatement.setInt(1, procent);
            preparedStatement.setInt(2, teacherId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            Audit.logAction("Exception in TeacherDao for updateTeacherSalary: " + e);
        }
    }

    public List<Teacher> getAllTeachers() {
        List<Teacher> teachers = new ArrayList<>();
        try {
            final String query = "SELECT t.id_teacher, username, phoneNumber, address, nationality, gender, birthdate, ranking, hireDate, salary FROM Teacher t, User u WHERE t.id_teacher = u.id_user";
            Statement statement = databaseConnection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                Teacher teacher = mapToTeacher(resultSet);
                teachers.add(teacher);
            }
        } catch (SQLException e) {
            Audit.logAction("Exception in TeacherDao for getAllTeachers: " + e);
        }
        return teachers;
    }
}
