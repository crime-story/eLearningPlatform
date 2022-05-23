package dao;

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

public class MaterialDao extends Dao {

    private static MaterialDao materialDao;

    private MaterialDao() {
        super();
    }

    public static MaterialDao getMaterialDao() {
        if (materialDao == null) {
            materialDao = new MaterialDao();
        }
        return materialDao;
    }

    @Override
    protected void createTable() {
        final String query = "CREATE TABLE IF NOT EXISTS Material (\n" +
                "id_material INT PRIMARY KEY,\n" +
                "materialName VARCHAR(128) NOT NULL,\n" +
                "description VARCHAR(2048) NOT NULL,\n" +
                "available VARCHAR(4) NOT NULL)";
        try {
            Statement statement = databaseConnection.createStatement();
            statement.execute(query);
        } catch (SQLException e) {
            Audit.logAction("Exception in MaterialDao for create table: " + e);
        }
    }

    public void insertMaterial(Material material) {
        try {
            final String query = "INSERT INTO Material(id_material, materialName, description, available) values(?, ?, ?, ?)";
            PreparedStatement preparedStatement = databaseConnection.prepareStatement(query, Statement.NO_GENERATED_KEYS);
            preparedStatement.setInt(1, material.getId());
            preparedStatement.setString(2, material.getMaterialName());
            preparedStatement.setString(3, material.getDescription());
            String available;
            if (material.isAvailable()) {
                available = "Yes";
            } else {
                available = "No";
            }
            preparedStatement.setString(4, available);
            preparedStatement.execute();
        } catch (SQLException e) {
            Audit.logAction("Exception in MaterialDao for insert: " + e);
        }
    }

    public void deleteMaterial(int materialId) {
        try {
            final String query = "DELETE FROM Material WHERE id_material = ?";
            PreparedStatement preparedStatement = databaseConnection.prepareStatement(query, Statement.NO_GENERATED_KEYS);
            preparedStatement.setInt(1, materialId);
            preparedStatement.execute();

            AdminInterface.materials.remove(ELearningPlatformService.getMaterialById(materialId));
        } catch (SQLException e) {
            Audit.logAction("Exception in MaterialDao deleteCourse: " + e);
        }
    }

    public void updateMaterialAvailability(int materialId, boolean materialStatus) {
        try {
            final String query = "UPDATE Material SET available = ? WHERE id_material = ?";
            PreparedStatement preparedStatement = databaseConnection.prepareStatement(query, Statement.NO_GENERATED_KEYS);
            String available;
            if (materialStatus) {
                available = "Yes";
            } else {
                available = "No";
            }
            preparedStatement.setString(1, available);
            preparedStatement.setInt(2, materialId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            Audit.logAction("Exception in MaterialDao for updateMaterialAvailability: " + e);
        }
    }

    public void updateMaterialDescription(int materialId, String newDescription) {
        try {
            final String query = "UPDATE Material SET description = ? WHERE id_material = ?";
            PreparedStatement preparedStatement = databaseConnection.prepareStatement(query, Statement.NO_GENERATED_KEYS);
            preparedStatement.setString(1, newDescription);
            preparedStatement.setInt(2, materialId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            Audit.logAction("Exception in MaterialDao for updateMaterialDescription: " + e);
        }
    }

    public void updateMaterialName(int materialId, String newMaterialName) {
        try {
            final String query = "UPDATE Material SET materialName = ? WHERE id_material = ?";
            PreparedStatement preparedStatement = databaseConnection.prepareStatement(query, Statement.NO_GENERATED_KEYS);
            preparedStatement.setString(1, newMaterialName);
            preparedStatement.setInt(2, materialId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            Audit.logAction("Exception in MaterialDao for updateMaterialName: " + e);
        }
    }

    private Material mapToMaterial(ResultSet resultSet) throws SQLException {
        boolean available;
        if (resultSet.getString(4).matches("Yes")) {
            available = true;
        } else {
            available = false;
        }
        return new Material(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3), available);
    }

    public List<Material> getAllMaterials() {
        List<Material> materials = new ArrayList<>();
        try {
            final String query = "SELECT * FROM Material";
            Statement statement = databaseConnection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                Material material = mapToMaterial(resultSet);
                materials.add(material);
            }
        } catch (SQLException e) {
            Audit.logAction("Exception in MaterialDao for getAllMaterials: " + e);
        }
        return materials;
    }
}
