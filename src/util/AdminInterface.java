package util;

import pojo.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public interface AdminInterface {
    List<Course> courses = new ArrayList<>();
    List<CourseFeedback> courseFeedbacks = new ArrayList<>();
    List<Quiz> quizzes = new ArrayList<>();
    List<UserCourseRepartition> userCourseRepartitions = new ArrayList<>();
    List<User> users = new ArrayList<>();
    List<Material> materials = new ArrayList<>();
    List<CourseMaterials> courseMaterials = new ArrayList<>();

    void addCourse(Scanner in);

    void addCourseFeedback(Scanner in);

    void addCourseRepartition(Scanner in);

    void addMaterial(Scanner in);

    void addQuiz(Scanner in);

    void addStudent(Scanner in);

    void addTeacher(Scanner in);

    void addTeachingAssistant(Scanner in);
}
