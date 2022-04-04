package pojo;

public class Material {
    private int id;
    private String materialName;
    private String description;
    private boolean available;
    private static int count = 0;

    public Material(int id, String materialName, String description, boolean available) {
        this.id = id;
        this.materialName = materialName;
        this.description = description;
        this.available = available;
        if (id > count) {
            count = id;
        }
    }

    public Material(String materialName, String description, boolean available) {
        this.materialName = materialName;
        this.description = description;
        this.available = available;
        this.id = count;
        count++;
    }

    public int getId() {
        return id;
    }

    public String getMaterialName() {
        return materialName;
    }

    public String getDescription() {
        return description;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setMaterialName(String materialName) {
        this.materialName = materialName;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    @Override
    public String toString() {
        return "Material{" +
                "id=" + id +
                ", materialName='" + materialName + '\'' +
                ", description='" + description + '\'' +
                ", available=" + available +
                '}';
    }
}
