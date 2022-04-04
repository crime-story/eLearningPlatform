package pojo;

public class Teacher extends User {
    private String rank;
    private int yearsOfExperience;
    private float salary;

    public Teacher(int id, String username, String phoneNumber, String address, String nationality, String gender, String birthdate, String rank, int yearsOfExperience, float salary) {
        super(id, username, phoneNumber, address, nationality, gender, birthdate);
        this.rank = rank;
        this.yearsOfExperience = yearsOfExperience;
        this.salary = salary;
    }

    public Teacher(String username, String phoneNumber, String address, String nationality, String gender, String birthdate, String rank, int yearsOfExperience, float salary) {
        super(username, phoneNumber, address, nationality, gender, birthdate);
        this.rank = rank;
        this.yearsOfExperience = yearsOfExperience;
        this.salary = salary;
    }

    public String getRank() {
        return rank;
    }

    public int getYearsOfExperience() {
        return yearsOfExperience;
    }

    public float getSalary() {
        return salary;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }

    public void setYearsOfExperience(int yearsOfExperience) {
        this.yearsOfExperience = yearsOfExperience;
    }

    public void setSalary(float salary) {
        this.salary = salary;
    }

    @Override
    public String toString() {
        return "Teacher{" +
                "rank='" + rank + '\'' +
                ", yearsOfExperience=" + yearsOfExperience +
                ", salary=" + salary +
                "} " + super.toString();
    }
}
