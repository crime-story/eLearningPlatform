package dao;

import pojo.Course;
import pojo.Quiz;
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

public class QuizDao extends Dao {
    private static QuizDao quizDao;

    private QuizDao() {
        super();
    }

    public static QuizDao getQuizDao() {
        if (quizDao == null) {
            quizDao = new QuizDao();
        }
        return quizDao;
    }

    @Override
    protected void createTable() {
        final String query = "CREATE TABLE IF NOT EXISTS Quiz (\n" +
                "id_quiz INT PRIMARY KEY,\n" +
                "id_course INT NOT NULL,\n" +
                "quizName VARCHAR(128) NOT NULL,\n" +
                "FOREIGN KEY (id_course) REFERENCES Course(id_course) ON DELETE CASCADE)";

        try {
            Statement statement = databaseConnection.createStatement();
            statement.execute(query);
        } catch (SQLException e) {
            Audit.logAction("Exception in QuizDao for create table: " + e);
        }
    }

    public void insertQuiz(Quiz quiz) {
        try {
            final String query = "INSERT INTO Quiz(id_quiz, id_course, quizName) values(?,?,?)";
            PreparedStatement preparedStatement = databaseConnection.prepareStatement(query, Statement.NO_GENERATED_KEYS);
            preparedStatement.setInt(1, quiz.getId());
            preparedStatement.setInt(2, quiz.getCourse().getId());
            preparedStatement.setString(3, quiz.getQuizName());
            preparedStatement.execute();
        } catch (SQLException e) {
            Audit.logAction("Exception in QuizDao for insert: " + e);
        }
    }

    public void deleteQuiz(int quizId) {
        try {
            final String query = "DELETE FROM Quiz WHERE id_quiz = ?";
            PreparedStatement preparedStatement = databaseConnection.prepareStatement(query, Statement.NO_GENERATED_KEYS);
            preparedStatement.setInt(1, quizId);
            preparedStatement.execute();

            AdminInterface.quizzes.remove(ELearningPlatformService.getQuizById(quizId));
        } catch (SQLException e) {
            Audit.logAction("Exception in QuizDao for delete: " + e);
        }
    }

    public void updateQuizName(int quizId, String newQuizName) {
        try {
            final String query = "UPDATE Quiz SET quizName = ? WHERE id_quiz = ?";
            PreparedStatement preparedStatement = databaseConnection.prepareStatement(query, Statement.NO_GENERATED_KEYS);
            preparedStatement.setString(1, newQuizName);
            preparedStatement.setInt(2, quizId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            Audit.logAction("Exception in QuizDao for updateQuizName: " + e);
        }
    }

    private Quiz mapToQuiz(ResultSet resultSet) throws SQLException {
        Course course = ELearningPlatformService.getCourseById(resultSet.getInt(2));
        return new Quiz(resultSet.getInt(1), course, resultSet.getString(3));
    }

    public List<Quiz> getAllQuizes() {
        List<Quiz> quizzes = new ArrayList<>();
        try {
            final String query = "SELECT * FROM Quiz";
            Statement statement = databaseConnection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                Quiz quiz = mapToQuiz(resultSet);
                quizzes.add(quiz);
            }
        } catch (SQLException e) {
            Audit.logAction("Exception in QuizDao for getAllQuizzes: " + e);
        }
        return quizzes;
    }
}
