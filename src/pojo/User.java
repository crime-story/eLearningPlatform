package pojo;

public class User {
    private int id;
    private String username;
    private String phoneNumber;
    private String address;
    private String nationality;
    private String gender;
    private String birthdate;
    private static int count = 0;

    public User(int id, String username, String phoneNumber, String address, String nationality, String gender, String birthdate) {
        this.id = id;
        if (id > count) {
            count = id;
        }
        this.username = username;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.nationality = nationality;
        this.gender = gender;
        this.birthdate = birthdate;
    }

    public User(String username, String phoneNumber, String address, String nationality, String gender, String birthdate) {
        this.username = username;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.nationality = nationality;
        this.gender = gender;
        this.birthdate = birthdate;
        count++;
        this.id = count;
    }

    public int getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public String getNationality() {
        return nationality;
    }

    public String getGender() {
        return gender;
    }

    public String getBirthdate() {
        return birthdate;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public boolean setBirthdate(String birthdate) {
        if (birthdate.matches("^(0[1-9]|[12][0-9]|3[01])[- /.](0[1-9]|1[012])[- /.](19|20)\\d\\d$")) {
            this.birthdate = birthdate;
            return true;
        } else {
            System.out.println("Invalid date format for: " + birthdate + ". Format must be: dd-MM-YYYY!");
            return false;
        }
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", address='" + address + '\'' +
                ", nationality='" + nationality + '\'' +
                ", gender='" + gender + '\'' +
                ", birthdate=" + birthdate +
                '}';
    }
}
