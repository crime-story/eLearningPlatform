package pojo;

public class Teacher extends User {
    private String rank;
    private String hireDate;
    private float salary;

    public Teacher(int id, String username, String phoneNumber, String address, String nationality, String gender, String birthdate, String rank, String hireDate, float salary) {
        super(id, username, phoneNumber, address, nationality, gender, birthdate);
        this.rank = rank;
        this.hireDate = hireDate;
        this.salary = salary;
    }

    public Teacher(String username, String phoneNumber, String address, String nationality, String gender, String birthdate, String rank, String hireDate, float salary) {
        super(username, phoneNumber, address, nationality, gender, birthdate);
        this.rank = rank;
        this.hireDate = hireDate;
        this.salary = salary;
    }

    public String getRank() {
        return rank;
    }

    public String getHireDate() {
        return hireDate;
    }

    public float getSalary() {
        return salary;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }

    public void setHireDate(String hireDate) {
        this.hireDate = hireDate;
    }

    public void setSalary(float salary) {
        this.salary = salary;
    }

    @Override
    public String toString() {
        return "Teacher{" +
                "rank='" + rank + '\'' +
                ", hireDate=" + hireDate +
                ", salary=" + salary +
                "} " + super.toString();
    }
}
