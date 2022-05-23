package dao;

import pojo.User;
import util.AdminInterface;
import util.Audit;
import util.Dao;
import util.ELearningPlatformService;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.sql.Date;

public abstract class UserDao extends Dao {
    public UserDao() {
        super();
    }

    @Override
    protected void createTable() {
        final String query = "CREATE TABLE IF NOT EXISTS User (\n" +
                "id_user INT PRIMARY KEY,\n" +
                "username VARCHAR(64) NOT NULL,\n" +
                "phoneNumber VARCHAR(10) NOT NULL,\n" +
                "address VARCHAR(256) NOT NULL,\n" +
                "nationality VARCHAR(20) NOT NULL,\n" +
                "gender VARCHAR(15) NOT NULL,\n" +
                "birthdate DATE NOT NULL)";
        try {
            Statement statement = databaseConnection.createStatement();
            statement.execute(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void insertUser(User user) {
        final String query = "INSERT INTO User(id_user, username, phoneNumber, address, nationality, gender, birthdate) values (?, ?, ?, ?, ?, ?, ?)";
        try {
            PreparedStatement preparedStatement = databaseConnection.prepareStatement(query, Statement.NO_GENERATED_KEYS);
            preparedStatement.setInt(1, user.getId());
            preparedStatement.setString(2, user.getUsername());
            preparedStatement.setString(3, user.getPhoneNumber());
            preparedStatement.setString(4, user.getAddress());
            preparedStatement.setString(5, user.getNationality());
            preparedStatement.setString(6, user.getGender());
            Date date = new java.sql.Date((new SimpleDateFormat("dd-MM-yyyy").parse(user.getBirthdate()).getTime()));
            preparedStatement.setDate(7, date);
            preparedStatement.execute();

        } catch (SQLException e) {
            Audit.logAction("Exception in UserDao for insert: " + e);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public void deleteUser(int userId) {
        final String query = "DELETE FROM User WHERE id_user = ?";
        try {
            PreparedStatement preparedStatement = databaseConnection.prepareStatement(query, Statement.NO_GENERATED_KEYS);
            // no generated keys = impiedica mySql sa creeze un primary key al lui deoarece il gestionez manual la nivelul Pojo-urilor
            preparedStatement.setInt(1, userId);
            preparedStatement.execute();

            AdminInterface.users.remove(ELearningPlatformService.getByUserId(userId));
        } catch (SQLException e) {
            Audit.logAction("Exception in UserDao for delete: " + e);
        }
    }

    public void updateUserPhoneNumber(int userId, String newPhoneNumber) {
        try {
            final String query = "UPDATE User SET phoneNumber = ? WHERE id_user = ?";
            PreparedStatement preparedStatement = databaseConnection.prepareStatement(query, Statement.NO_GENERATED_KEYS);
            preparedStatement.setString(1, newPhoneNumber);
            preparedStatement.setInt(2, userId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            Audit.logAction("Exception in UserDao for updateUserPhoneNumber: " + e);
        }
    }

    public void updateUserAddress(int userId, String newAddress) {
        try {
            final String query = "UPDATE User SET address = ? WHERE id_user = ?";
            PreparedStatement preparedStatement = databaseConnection.prepareStatement(query, Statement.NO_GENERATED_KEYS);
            preparedStatement.setString(1, newAddress);
            preparedStatement.setInt(2, userId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            Audit.logAction("Exception in UserDao for updateUserAddress: " + e);
        }
    }

    public void updateUserNationality(int userId, String newNationality) {
        try {
            final String query = "UPDATE User SET nationality = ? WHERE id_user = ?";
            PreparedStatement preparedStatement = databaseConnection.prepareStatement(query, Statement.NO_GENERATED_KEYS);
            preparedStatement.setString(1, newNationality);
            preparedStatement.setInt(2, userId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            Audit.logAction("Exception in UserDao for updateUserNationality: " + e);
        }
    }

    public void updateUserGender(int userId, String newGender) {
        try {
            final String query = "UPDATE User SET gender = ? WHERE id_user = ?";
            PreparedStatement preparedStatement = databaseConnection.prepareStatement(query, Statement.NO_GENERATED_KEYS);
            preparedStatement.setString(1, newGender);
            preparedStatement.setInt(2, userId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            Audit.logAction("Exception in UserDao for updateUserGender: " + e);
        }
    }

}
