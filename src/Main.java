import pojo.CourseMaterial;
import pojo.UserCourseRepartition;
import util.Audit;
import util.ELearningPlatformService;

import java.util.Scanner;
import java.util.TreeSet;

public class Main {
    public static void main(String[] args) {
        final Scanner cin = new Scanner(System.in);
        final ELearningPlatformService eLearningPlatformService = ELearningPlatformService.getInstance();

        System.out.println("Welcome to eLearningPlatform by Robertto!");

        System.out.println("Type option");
        int option;
        do {
            try {
                displayMenuOptions();
                option = cin.nextInt();
                cin.nextLine();
                switch (option) {
                    case 0:
                        break;
                    case 1:
                        System.out.println("Add Student");
                        System.out.println(eLearningPlatformService.addStudent(cin));
                        Audit.logAction("added-student");
                        break;
                    case 2:
                        System.out.println("Delete Student");
                        ELearningPlatformService.deleteAnStudent(cin);
                        Audit.logAction("deleted-student");
                        break;
                    case 3:
                        System.out.println("Update Student ScholarShip Status");
                        ELearningPlatformService.updateAnStudentScholarShipStatus(cin);
                        Audit.logAction("updated-student-scholarship-status");
                        break;
                    case 4:
                        System.out.println("Display all Students");
                        ELearningPlatformService.displayAllStudents();
                        Audit.logAction("displayed-all-students");
                        break;
                    case 5:
                        System.out.println("Add Teacher");
                        System.out.println(eLearningPlatformService.addTeacher(cin));
                        Audit.logAction("added-teacher");
                        break;
                    case 6:
                        System.out.println("Delete Teacher");
                        ELearningPlatformService.deleteAnTeacher(cin);
                        Audit.logAction("deleted-teacher");
                        break;
                    case 7:
                        System.out.println("Increase Teacher Salary by procent");
                        ELearningPlatformService.updateAnTeacherSalaryByProcent(cin);
                        Audit.logAction("updated-teacher-salary");
                        break;
                    case 8:
                        System.out.println("Display all Teachers");
                        ELearningPlatformService.displayAllTeachers();
                        Audit.logAction("displayed-all-teachers");
                        break;
                    case 9:
                        System.out.println("Add Teaching Assistant");
                        System.out.println(eLearningPlatformService.addTeachingAssistant(cin));
                        Audit.logAction("added-teaching-assistant");
                        break;
                    case 10:
                        System.out.println("Delete Teaching Assistant");
                        ELearningPlatformService.deleteAnTeachingAssistant(cin);
                        Audit.logAction("deleted-teaching-assistant");
                        break;
                    case 11:
                        System.out.println("Increase Teaching Assistant Salary by procent");
                        ELearningPlatformService.updateAnTeachingAssistantSalaryByProcent(cin);
                        Audit.logAction("updated-teaching-assistant-salary");
                        break;
                    case 12:
                        System.out.println("Display all Teaching Assistants");
                        ELearningPlatformService.displayAllTeachingAssistants();
                        Audit.logAction("displayed-all-teaching-assistants");
                        break;
                    case 13:
                        System.out.println("Delete User");
                        ELearningPlatformService.deleteAnUser(cin);
                        Audit.logAction("deleted-user");
                        break;
                    case 14:
                        System.out.println("Update User Gender");
                        ELearningPlatformService.updateAnUserGender(cin);
                        Audit.logAction("updated-user-gender");
                        break;
                    case 15:
                        System.out.println("Update User Address");
                        ELearningPlatformService.updateAnUserAddress(cin);
                        Audit.logAction("updated-user-address");
                        break;
                    case 16:
                        System.out.println("Update User Phone Number");
                        ELearningPlatformService.updateAnUserPhoneNumber(cin);
                        Audit.logAction("updated-user-phone-number");
                        break;
                    case 17:
                        System.out.println("Update User Nationality");
                        ELearningPlatformService.updateAnUserNationality(cin);
                        Audit.logAction("updated-user-nationality");
                        break;
                    case 18:
                        System.out.println("Find User By Id");
                        System.out.println("Enter userId:");
                        int userId = cin.nextInt();
                        cin.nextLine();
                        System.out.println(ELearningPlatformService.getByUserId(userId));
                        Audit.logAction("found-user-by-id");
                        break;
                    case 19:
                        System.out.println("Display all Users");
                        ELearningPlatformService.displayAllUsers();
                        Audit.logAction("displayed-all-users");
                        break;
                    case 20:
                        System.out.println("Add Course");
                        System.out.println(eLearningPlatformService.addCourse(cin));
                        Audit.logAction("added-course");
                        break;
                    case 21:
                        System.out.println("Delete Course");
                        ELearningPlatformService.deleteAnCourse(cin);
                        Audit.logAction("deleted-course");
                        break;
                    case 22:
                        System.out.println("Update Course description");
                        ELearningPlatformService.updateAnCourseDescription(cin);
                        Audit.logAction("updated-course-description");
                        break;
                    case 23:
                        System.out.println("Display all Courses");
                        ELearningPlatformService.displayAllCourses();
                        Audit.logAction("displayed-all-courses");
                        break;
                    case 24:
                        System.out.println("Add Course Feedback");
                        System.out.println(eLearningPlatformService.addCourseFeedback(cin));
                        Audit.logAction("added-course-feedback");
                        break;
                    case 25:
                        System.out.println("Delete Course Feedback");
                        ELearningPlatformService.deleteAnCourseFeedback(cin);
                        Audit.logAction("deleted-course-feedback");
                        break;
                    case 26:
                        System.out.println("Update Course Feedback (number of stars, feedback)");
                        ELearningPlatformService.updateAnCourseFeedback(cin);
                        Audit.logAction("updated-course-feedback-numberOfStars-feedback");
                        break;
                    case 27:
                        System.out.println("Update Course Feedback (only number of stars)");
                        ELearningPlatformService.updateAnCourseFeedbackStars(cin);
                        Audit.logAction("updated-course-feedback-numberOfStars");
                        break;
                    case 28:
                        System.out.println("Update Course Feedback (only feedback)");
                        ELearningPlatformService.updateAnCourseFeedbackFeedbackMessage(cin);
                        Audit.logAction("updated-course-feedback-numberOfStars");
                        break;
                    case 29:
                        System.out.println("Display all CourseFeedbacks");
                        ELearningPlatformService.displayAllCourseFeedbacks();
                        Audit.logAction("displayed-all-course-feedbacks");
                        break;
                    case 30:
                        System.out.println(("Display all CourseFeedbacks for an specific Course"));
                        ELearningPlatformService.displayAllCourseFeedbacksForAnSpecificCourse(cin);
                        Audit.logAction("displayed-course-feedbacks-for-an-specific-course");
                        break;
                    case 31:
                        System.out.println("Add Quiz");
                        System.out.println(eLearningPlatformService.addQuiz(cin));
                        Audit.logAction("added-quiz");
                        break;
                    case 32:
                        System.out.println("Delete Quiz");
                        ELearningPlatformService.deleteAnQuiz(cin);
                        Audit.logAction("deleted-quiz");
                        break;
                    case 33:
                        System.out.println("Update Quiz Name");
                        ELearningPlatformService.updateAnQuizName(cin);
                        Audit.logAction("updated-quiz-name");
                        break;
                    case 34:
                        System.out.println("Display all Quizzes");
                        ELearningPlatformService.displayAllQuizzes();
                        Audit.logAction("displayed-all-quizzes");
                        break;
                    case 35:
                        System.out.println("Add Material");
                        System.out.println(eLearningPlatformService.addMaterial(cin));
                        Audit.logAction("added-material");
                        break;
                    case 36:
                        System.out.println("Delete Material");
                        ELearningPlatformService.deleteAnMaterial(cin);
                        Audit.logAction("deleted-material");
                        break;
                    case 37:
                        System.out.println("Update Material Availability Status");
                        ELearningPlatformService.updateAnMaterialAvailabilityStatus(cin);
                        Audit.logAction("updated-course-material");
                        break;
                    case 38:
                        System.out.println("Update Material Description");
                        ELearningPlatformService.updateAnMaterialDescription(cin);
                        Audit.logAction("updated-material-description");
                        break;
                    case 39:
                        System.out.println("Update Material Name");
                        ELearningPlatformService.updateAnMaterialName(cin);
                        Audit.logAction("updated-material-name");
                        break;
                    case 40:
                        System.out.println("Display all Materials used for an Course by Course ID");
                        System.out.println("Enter courseId:");
                        int courseId = cin.nextInt();
                        cin.nextLine();
                        TreeSet<CourseMaterial> result = ELearningPlatformService.getAllMaterialsUsedForAnCourseByCourseId(courseId);
                        for (CourseMaterial courseMaterial : result) {
                            System.out.println(courseMaterial);
                        }
                        Audit.logAction("displayed-all-materials-used-for-an-course");
                        break;
                    case 41:
                        System.out.println("Display all Materials");
                        ELearningPlatformService.displayAllMaterials();
                        Audit.logAction("displayed-all-materials");
                        break;
                    case 42:
                        System.out.println("Add Course Material");
                        eLearningPlatformService.addCourseMaterial(cin);
                        Audit.logAction("added-course-material");
                        break;
                    case 43:
                        System.out.println("Delete Course Material");
                        ELearningPlatformService.deleteAnCourseMaterial(cin);
                        Audit.logAction("deleted-course-feedback");
                        break;
                    case 44:
                        System.out.println("Update Course Material");
                        ELearningPlatformService.updateAnCourseMaterial(cin);
                        Audit.logAction("updated-course-material");
                        break;
                    case 45:
                        System.out.println("Display all Course Materials");
                        ELearningPlatformService.displayAllCourseMaterials();
                        Audit.logAction("displayed-all-course-materials");
                        break;
                    case 46:
                        System.out.println("Add User Course Repartition");
                        System.out.println(eLearningPlatformService.addCourseRepartition(cin));
                        Audit.logAction("added-user-course-repartition");
                        break;
                    case 47:
                        System.out.println("Delete User Course Repartition");
                        ELearningPlatformService.deleteAnUserCourseRepartition(cin);
                        Audit.logAction("deleted-user-course-repartition");
                        break;
                    case 48:
                        System.out.println("Update User Course Repartition");
                        ELearningPlatformService.updateAnUserCourseRepartition(cin);
                        Audit.logAction("updated-user-course-repartition");
                        break;
                    case 49:
                        System.out.println("Display all Course Repartitions for an Specific Student");
                        System.out.println("Enter studentId:");
                        int studentId = cin.nextInt();
                        cin.nextLine();
                        TreeSet<UserCourseRepartition> result1 = ELearningPlatformService.getAllCoursesRepartitionsForAnStudentByStudentId(studentId);
                        for (UserCourseRepartition userCourseRepartition : result1) {
                            System.out.println(userCourseRepartition);
                        }
                        Audit.logAction("displayed-all-course-repartitions-for-an-specific-student");
                        break;
                    case 50:
                        System.out.println("Display all User Course Repartitions");
                        ELearningPlatformService.displayAllUserCourseRepartitions();
                        Audit.logAction("displayed-all-user-course-repartitions");
                        break;

                    default:
                        System.out.println("Invalid option!");
                        break;
                }
            } finally {
                System.out.println("Please press enter");
                cin.nextLine();
            }
        } while (option != 0);
        System.out.println("Good Bye!");
    }

    private static void displayMenuOptions() {
        System.out.println("0. Exit");
        System.out.println("1. Add Student");
        System.out.println("2. Delete Student");
        System.out.println("3. Update Student ScholarShip Status");
        System.out.println("4. Display all Students");
        System.out.println("5. Add Teacher");
        System.out.println("6. Delete Teacher");
        System.out.println("7. Increase Teacher Salary by procent");
        System.out.println("8. Display all Teachers");
        System.out.println("9. Add Teaching Assistant");
        System.out.println("10. Delete Teaching Assistant");
        System.out.println("11. Increase Teaching Assistant Salary by procent");
        System.out.println("12. Display all Teaching Assistants");
        System.out.println("13. Delete User");
        System.out.println("14. Update User Gender");
        System.out.println("15. Update User Phone Number");
        System.out.println("16. Update User Address");
        System.out.println("17. Update User Nationality");
        System.out.println("18. Find User By Id");
        System.out.println("19. Display all Users");
        System.out.println("20. Add Course");
        System.out.println("21. Delete Course");
        System.out.println("22. Update Course description");
        System.out.println("23. Display all Courses");
        System.out.println("24. Add Course Feedback");
        System.out.println("25. Delete Course Feedback");
        System.out.println("26. Update Course Feedback (number of stars, feedback)");
        System.out.println("27. Update Course Feedback (only number of stars)");
        System.out.println("28. Update Course Feedback (only feedback)");
        System.out.println("29. Display all CourseFeedbacks");
        System.out.println("30. Display all CourseFeedbacks for an specific Course");
        System.out.println("31. Add Quiz");
        System.out.println("32. Delete Quiz");
        System.out.println("33. Update Quiz Name");
        System.out.println("34. Display all Quizzes");
        System.out.println("35. Add Material");
        System.out.println("36. Delete Material");
        System.out.println("37. Update Material Availability Status");
        System.out.println("38. Update Material Description");
        System.out.println("39. Update Material Name");
        System.out.println("40. Display all Materials used for an Course by Course ID");
        System.out.println("41. Display all Materials");
        System.out.println("42. Add Course Material");
        System.out.println("43. Delete Course Material");
        System.out.println("44. Update Course Material");
        System.out.println("45. Display all Course Materials");
        System.out.println("46. Add User Course Repartition");
        System.out.println("47. Delete User Course Repartition");
        System.out.println("48. Update User Course Repartition");
        System.out.println("49. Display all Course Repartitions for an Specific Student");
        System.out.println("50. Display all User Course Repartitions");
        System.out.print("Your option is: ");
    }
}
