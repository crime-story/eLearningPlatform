package pojo;

public class Student extends User {
    private final boolean hasScholarShip;

    public Student(int id, String username, String phoneNumber, String address, String nationality, String gender, String birthdate, boolean hasScholarShip) {
        super(id, username, phoneNumber, address, nationality, gender, birthdate);
        this.hasScholarShip = hasScholarShip;
    }

    public Student(String username, String phoneNumber, String address, String nationality, String gender, String birthdate, boolean hasScholarShip) {
        super(username, phoneNumber, address, nationality, gender, birthdate);
        this.hasScholarShip = hasScholarShip;
    }

    public boolean isHasScholarShip() {
        return hasScholarShip;
    }

    @Override
    public String toString() {
        return "Student{" +
                "hasScholarShip=" + hasScholarShip +
                "} " + super.toString();
    }
}
