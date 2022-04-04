package util;

import pojo.*;

import java.util.ArrayList;
import java.util.Scanner;

public abstract class ELearningPlatformService implements AdminInterface {

    public static User getByUserId(int userId) {
        for (User user : users) {
            if (user.getId() == userId)
                return user;
        }
        return null;
    }

    public static Course getCourseById(int courseId) {
        for (Course course : courses) {
            if (course.getId() == courseId)
                return course;
        }
        return null;
    }

    public static Quiz getQuizById(int quizId) {
        for (Quiz quiz : quizzes) {
            if (quiz.getId() == quizId)
                return quiz;
        }
        return null;
    }

    public static Material getMaterialById(int materialId) {
        for (Material material : materials) {
            if (material.getId() == materialId)
                return material;
        }
        return null;
    }

    public static CourseFeedback getCourseFeedBackById(int courseFeedbackId) {
        for (CourseFeedback courseFeedback : courseFeedbacks) {
            if (courseFeedback.getId() == courseFeedbackId)
                return courseFeedback;
        }
        return null;
    }

    public static ArrayList<CourseFeedback> getFeedbacksByCourseId(int courseId) {
        ArrayList<CourseFeedback> result = new ArrayList<>();
        for (CourseFeedback courseFeedback : courseFeedbacks) {
            if (courseFeedback.getCourse().getId() == courseId)
                result.add(courseFeedback);
        }
        return result;
    }

    public static ArrayList<CourseMaterials> getMaterialsByCourseId(int courseId) {
        ArrayList<CourseMaterials> result = new ArrayList<>();
        for (CourseMaterials courseMaterials : courseMaterials) {
            if(courseMaterials.getCourse().getId() == courseId)
                result.add(courseMaterials);
        }
        return result;
    }

    private String verifyDateFormat(Scanner in) {
        String date = null;
        while (true) {
            date = in.nextLine();
            if (date.matches("^(0[1-9]|[12][0-9]|3[01])[- /.](0[1-9]|1[012])[- /.](19|20)\\d\\d$")) {
                break;
            } else {
                System.out.println("Invalid date format for: " + date + ". Format must be: dd-MM-YYYY! Please try again now!");
            }
        }
        return date;
    }

    private void addUser(String username, String phoneNumber, String adress, String nationality, String gender, String birthdate) {
        User user = new User(username, phoneNumber, adress, nationality, gender, birthdate);
    }

    @Override
    public void addCourse(Scanner in) {
        System.out.println("Teacher ID:");
        int teacherId = in.nextInt();

        in.nextLine();

        User teacher = getByUserId(teacherId);

        System.out.println("Course name:");
        String courseName = in.nextLine();

        System.out.println("Course description:");
        String courseDescription = in.nextLine();

        Course course = new Course(teacher, courseName, courseDescription);
    }

    @Override
    public void addCourseFeedback(Scanner in) {
        System.out.println("Course ID:");
        int courseId = in.nextInt();

        in.nextLine();

        Course course = getCourseById(courseId);

        System.out.println("Number of stars:");
        float stars = in.nextFloat();

        in.nextLine();

        System.out.println("Feedback description:");
        String feedbackDescription = in.nextLine();

        CourseFeedback courseFeedback = new CourseFeedback(course, stars, feedbackDescription);
    }

    @Override
    public void addCourseRepartition(Scanner in) {
        System.out.println("Course id:");
        int courseId = in.nextInt();

        in.nextLine();

        Course course = getCourseById(courseId);
        System.out.println("User ID:");
        int userId = in.nextInt();

        in.nextLine();

        User user = getByUserId(userId);

        System.out.println("Start Date (Format must be: dd-MM-YYYY):");
        String startDate = verifyDateFormat(in);

        System.out.println("End Date:");
        String endDate = verifyDateFormat(in);

        UserCourseRepartition courseRepartition = new UserCourseRepartition(course, user, startDate, endDate);
    }

    @Override
    public void addQuiz(Scanner in) {
        System.out.println("Course ID:");
        int courseId = in.nextInt();

        in.nextLine();

        Course course = getCourseById(courseId);

        System.out.println("Name of Quiz:");
        String quizName = in.nextLine();

        Quiz quiz = new Quiz(course, quizName);
    }

    @Override
    public void addStudent(Scanner in) {
        System.out.println("Enter username:");
        String username = in.nextLine();

        System.out.println("Enter phone number:");
        String phoneNumber = in.nextLine();

        System.out.println("Enter adress:");
        String adress = in.nextLine();

        System.out.println("Enter nationality:");
        String nationality = in.nextLine();

        System.out.println("Enter gender:");
        String gender = in.nextLine();

        System.out.println("Enter birthdate (Format must be: dd-MM-YYYY):");
        String birthdate = verifyDateFormat(in);

        System.out.println("Has scholarship? yes/no");
        String hasScholarShip = in.nextLine();

        addUser(username, phoneNumber, adress, nationality, gender, birthdate);

        if (hasScholarShip.toLowerCase().equals("yes")) {
            Student student = new Student(username, phoneNumber, adress, nationality, gender, birthdate, true);
        } else {
            Student student = new Student(username, phoneNumber, adress, nationality, gender, birthdate, false);
        }
    }

    @Override
    public void addTeacher(Scanner in) {
        System.out.println("Enter username:");
        String username = in.nextLine();

        System.out.println("Enter phone number:");
        String phoneNumber = in.nextLine();

        System.out.println("Enter adress:");
        String adress = in.nextLine();

        System.out.println("Enter nationality:");
        String nationality = in.nextLine();

        System.out.println("Enter gender:");
        String gender = in.nextLine();

        System.out.println("Enter birthdate (Format must be: dd-MM-YYYY):");
        String birthdate = verifyDateFormat(in);

        System.out.println("Enter rank:");
        String rank = in.nextLine();

        System.out.println("Enter years of experience:");
        int yearsOfExperience = in.nextInt();

        in.nextLine();

        System.out.println("Enter salary:");
        float salary = in.nextFloat();

        in.nextLine();

        addUser(username, phoneNumber, adress, nationality, gender, birthdate);

        Teacher teacher = new Teacher(username, phoneNumber, adress, nationality, gender, birthdate, rank, yearsOfExperience, salary);
    }

    @Override
    public void addTeachingAssistant(Scanner in) {
        System.out.println("Enter username:");
        String username = in.nextLine();

        System.out.println("Enter phone number:");
        String phoneNumber = in.nextLine();

        System.out.println("Enter adress:");
        String adress = in.nextLine();

        System.out.println("Enter nationality:");
        String nationality = in.nextLine();

        System.out.println("Enter gender:");
        String gender = in.nextLine();

        System.out.println("Enter birthdate (Format must be: dd-MM-YYYY):");
        String birthdate = verifyDateFormat(in);

        System.out.println("Enter boss ID:");
        int bossId = in.nextInt();

        in.nextLine();

        Teacher boss = (Teacher) getByUserId(bossId);

        System.out.println("Enter salary:");
        float salary = in.nextFloat();

        in.nextLine();

        addUser(username, phoneNumber, adress, nationality, gender, birthdate);

        TeachingAssistant teachingAssistant = new TeachingAssistant(username, phoneNumber, adress, nationality, gender, birthdate, boss, salary);
    }

    @Override
    public void addMaterial(Scanner in) {
        System.out.println("Material ID:");
        int materialId = in.nextInt();

        in.nextLine();

        System.out.println("Material name:");
        String materialName = in.nextLine();

        System.out.println("Material description:");
        String materialDescription = in.nextLine();

        System.out.println("Is available? yes/no");
        String isAvailable = in.nextLine();

        if (isAvailable.toLowerCase().equals("yes")) {
            Material material = new Material(materialId, materialName, materialDescription, true);
        } else {
            Material material = new Material(materialId, materialName, materialDescription, false);
        }
    }
}
