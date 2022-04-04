package pojo;

public class TeachingAssistant extends User {
    private Teacher boss;
    private float salary;

    public TeachingAssistant(int id, String username, String phoneNumber, String address, String nationality, String gender, String birthdate, Teacher boss, float salary) {
        super(id, username, phoneNumber, address, nationality, gender, birthdate);
        this.boss = boss;
        this.salary = salary;
    }

    public TeachingAssistant(String username, String phoneNumber, String address, String nationality, String gender, String birthdate, Teacher boss, float salary) {
        super(username, phoneNumber, address, nationality, gender, birthdate);
        this.boss = boss;
        this.salary = salary;
    }

    public Teacher getBoss() {
        return boss;
    }

    public float getSalary() {
        return salary;
    }

    public void setSalary(float salary) {
        this.salary = salary;
    }

    @Override
    public String toString() {
        return "TeachingAssistant{" +
                "boss=" + boss +
                ", salary=" + salary +
                "} " + super.toString();
    }
}
