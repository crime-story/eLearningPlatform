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
    List<CourseMaterial> courseMaterials = new ArrayList<>();

    Course addCourse(Scanner in);

    CourseMaterial addCourseMaterial(Scanner in);

    CourseFeedback addCourseFeedback(Scanner in);

    UserCourseRepartition addCourseRepartition(Scanner in);

    Material addMaterial(Scanner in);

    Quiz addQuiz(Scanner in);

    Student addStudent(Scanner in);

    Teacher addTeacher(Scanner in);

    TeachingAssistant addTeachingAssistant(Scanner in);
}
