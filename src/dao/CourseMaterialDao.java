package dao;

import pojo.Course;
import pojo.CourseMaterial;
import pojo.Material;
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

public class CourseMaterialDao extends Dao {

    private static CourseMaterialDao courseMaterialDao;

    private CourseMaterialDao() {
        super();
    }

    public static CourseMaterialDao getCourseMaterialDao() {
        if (courseMaterialDao == null) {
            courseMaterialDao = new CourseMaterialDao();
        }
        return courseMaterialDao;
    }

    @Override
    protected void createTable() {
        final String query = "CREATE TABLE IF NOT EXISTS CourseMaterial (\n" +
                "id_course INT NOT NULL,\n" +
                "id_material INT NOT NULL,\n" +
                "FOREIGN KEY (id_course) REFERENCES Course(id_course) ON DELETE CASCADE,\n" +
                "FOREIGN KEY (id_material) REFERENCES Material(id_material) ON DELETE CASCADE)";

        try {
            Statement statement = databaseConnection.createStatement();
            statement.execute(query);
        } catch (SQLException e) {
            Audit.logAction("Exception in CourseMaterialDao for create table: " + e);
        }
    }

    public void insertCourseMaterial(CourseMaterial courseMaterial) {
        try {
            final String query = "INSERT INTO CourseMaterial(id_course, id_material) values(?, ?)";
            PreparedStatement preparedStatement = databaseConnection.prepareStatement(query, Statement.NO_GENERATED_KEYS);
            preparedStatement.setInt(1, courseMaterial.getCourse().getId());
            preparedStatement.setInt(2, courseMaterial.getMaterial().getId());
            preparedStatement.execute();
        } catch (SQLException e) {
            Audit.logAction("Exception in CourseMaterialDao for insert: " + e);
        }
    }

    public void deleteCourseMaterial(int courseId, int materialId) {
        try {
            final String query = "DELETE FROM CourseMaterial WHERE id_course = ? and id_material = ?";
            PreparedStatement preparedStatement = databaseConnection.prepareStatement(query, Statement.NO_GENERATED_KEYS);
            preparedStatement.setInt(1, courseId);
            preparedStatement.setInt(2, materialId);
            preparedStatement.execute();

            AdminInterface.courseMaterials.removeAll(ELearningPlatformService.getCourseMaterials(courseId, materialId));
        } catch (SQLException e) {
            Audit.logAction("Exception in CourseMaterialDao for delete: " + e);
        }
    }

    public void updateCourseMaterial(int courseId, int materialId, int newCourseId, int newMaterialId) {
        try {
            final String query = "UPDATE CourseMaterial SET id_course = ?, id_material = ? WHERE id_course = ? and id_material = ?";
            PreparedStatement preparedStatement = databaseConnection.prepareStatement(query, Statement.NO_GENERATED_KEYS);
            preparedStatement.setInt(1, newCourseId);
            preparedStatement.setInt(2, newMaterialId);
            preparedStatement.setInt(3, courseId);
            preparedStatement.setInt(4, materialId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            Audit.logAction("Exception in CourseMaterialDao for updateCourseMaterial: " + e);
        }
    }

    private CourseMaterial mapToCourseMaterial(ResultSet resultSet) throws SQLException {
        Course course = ELearningPlatformService.getCourseById(resultSet.getInt(1));
        Material material = ELearningPlatformService.getMaterialById(resultSet.getInt(2));
        return new CourseMaterial(course, material);
    }

    public List<CourseMaterial> getAllCourseMaterials() {
        List<CourseMaterial> courseMaterials = new ArrayList<>();
        try {
            final String query = "SELECT * FROM CourseMaterial";
            Statement statement = databaseConnection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                CourseMaterial courseMaterial = mapToCourseMaterial(resultSet);
                courseMaterials.add(courseMaterial);
            }
        } catch (SQLException e) {
            Audit.logAction("Exception in CourseMaterialDao for getAllCourseMaterials: " + e);
        }
        return courseMaterials;
    }
}
