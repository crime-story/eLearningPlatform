package util;

import dao.*;
import pojo.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.TreeSet;

public class ELearningPlatformService implements AdminInterface {
    private static ELearningPlatformService instance = null;
    private static CourseDao courseDao = null;
    private static CourseFeedbackDao courseFeedbackDao = null;
    private static CourseMaterialDao courseMaterialDao = null;
    private static MaterialDao materialDao = null;
    private static QuizDao quizDao = null;
    private static StudentDao studentDao = null;
    private static TeacherDao teacherDao = null;
    private static TeachingAssistantDao teachingAssistantDao = null;
    private static UserCourseRepartitionDao userCourseRepartitionDao = null;
    private static UserDao userDao = null;

    private ELearningPlatformService() {
        teacherDao = TeacherDao.getTeacherDao();
        List<Teacher> teacherList = teacherDao.getAllTeachers();
        users.addAll(teacherList);

        courseDao = CourseDao.getCourseDao();
        List<Course> courseList = courseDao.getAllCourses();
        courses.addAll(courseList);

        userDao = teacherDao;

        courseFeedbackDao = CourseFeedbackDao.getCourseFeedbackDao();
        List<CourseFeedback> courseFeedbackList = courseFeedbackDao.getAllFeedbacks();
        courseFeedbacks.addAll(courseFeedbackList);

        userCourseRepartitionDao = UserCourseRepartitionDao.getUserCourseRepartitionDao();
        List<UserCourseRepartition> userCourseRepartitionList = userCourseRepartitionDao.getAllUserCourseRepartitions();
        userCourseRepartitions.addAll(userCourseRepartitionList);

        teachingAssistantDao = TeachingAssistantDao.getTeachingAssistantDao();
        List<TeachingAssistant> teachingAssistantList = teachingAssistantDao.getAllTeachingAssistants();
        users.addAll(teachingAssistantList);

        studentDao = StudentDao.getStudentDao();
        List<Student> studentList = studentDao.getAllStudents();
        users.addAll(studentList);

        courseMaterialDao = CourseMaterialDao.getCourseMaterialDao();
        List<CourseMaterial> courseMaterialList = courseMaterialDao.getAllCourseMaterials();
        courseMaterials.addAll(courseMaterialList);

        materialDao = MaterialDao.getMaterialDao();
        List<Material> materialList = materialDao.getAllMaterials();
        materials.addAll(materialList);

        quizDao = QuizDao.getQuizDao();
        List<Quiz> quizList = quizDao.getAllQuizes();
        quizzes.addAll(quizList);

    }

    public static ELearningPlatformService getInstance() {
        if (instance == null) {
            instance = new ELearningPlatformService();
        }
        return instance;
    }

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

    public static ArrayList<CourseMaterial> getCourseMaterials(int courseId, int materialId) {
        ArrayList<CourseMaterial> result = new ArrayList<>();
        for (CourseMaterial courseMaterial : courseMaterials) {
            if (courseMaterial.getCourse().getId() == courseId && courseMaterial.getMaterial().getId() == materialId)
                result.add(courseMaterial);
        }
        return result;
    }

    public static CourseFeedback getCourseFeedBackById(int courseFeedbackId) {
        for (CourseFeedback courseFeedback : courseFeedbacks) {
            if (courseFeedback.getId() == courseFeedbackId)
                return courseFeedback;
        }
        return null;
    }

    private static String verifyDateFormat(Scanner in) {
        String date;
        while (true) {
            date = in.nextLine();
            if (date.matches("^(0[1-9]|[12]\\d|3[01])[- /.](0[1-9]|1[012])[- /.](19|20)\\d\\d$")) {
                break;
            } else {
                System.out.println("Invalid date format for: " + date + ". Format must be: dd-MM-YYYY! Please try again now!");
            }
        }
        return date;
    }

    private static boolean verifyYesOrNo(Scanner in) {
        String ans;
        while (true) {
            ans = in.nextLine();
            if (ans.matches("^(?:yes|no)$")) {
                break;
            } else {
                System.out.println("Invalid answear! Please type yes or no.");
            }
        }
        return ans.matches("yes");
    }

    private static float verifyNumberOfStars(Scanner in) {
        float numberOfStars;
        while (true) {
            numberOfStars = in.nextFloat();
            in.nextLine();
            if (numberOfStars > 5 || numberOfStars < 0) {
                System.out.println("Invalid number of stars! Must be between 0 and 5!");
            } else {
                break;
            }
        }
        return numberOfStars;
    }

    public static ArrayList<UserCourseRepartition> getUserCourseRepartitionByCourseIdAndUserId(int courseId, int userId) {
        ArrayList<UserCourseRepartition> repartitions = new ArrayList<>();
        for (UserCourseRepartition userCourseRepartition : userCourseRepartitions) {
            if (userCourseRepartition.getCourse().getId() == courseId && userCourseRepartition.getUser().getId() == userId)
                repartitions.add(userCourseRepartition);
        }
        return repartitions;
    }

    public static TreeSet<UserCourseRepartition> getAllCoursesRepartitionsForAnStudentByStudentId(int userId) {
        TreeSet<UserCourseRepartition> repartitionsForStudent = new TreeSet<>();
        for (UserCourseRepartition userCourseRepartition : userCourseRepartitions) {
            if (userCourseRepartition.getUser().getId() == userId)
                repartitionsForStudent.add(userCourseRepartition);
        }
        return repartitionsForStudent;
    }

    public static TreeSet<CourseMaterial> getAllMaterialsUsedForAnCourseByCourseId(int courseId) {
        TreeSet<CourseMaterial> courseMaterialsByCourseId = new TreeSet<>();
        for (CourseMaterial courseMaterial : courseMaterials) {
            if (courseMaterial.getCourse().getId() == courseId)
                courseMaterialsByCourseId.add(courseMaterial);
        }
        return courseMaterialsByCourseId;
    }

    // for Course
    @Override
    public Course addCourse(Scanner in) {
        System.out.println("Teacher ID:");
        int teacherId = in.nextInt();

        in.nextLine();

        Teacher teacher = (Teacher) getByUserId(teacherId);
        if (teacher == null)
            throw new NullPointerException("teacher not found");
        System.out.println("Course name:");
        String courseName = in.nextLine();

        System.out.println("Course description:");
        String courseDescription = in.nextLine();

        Course course = new Course(teacher, courseName, courseDescription);

        courses.add(course);
        courseDao.insertCourse(course);

        return course;
    }

    public static void deleteAnCourse(Scanner in) {
        System.out.println("Enter courseId:");
        int courseId = in.nextInt();
        in.nextLine();
        courseDao.deleteCourse(courseId);
    }

    public static void updateAnCourseDescription(Scanner in) {
        System.out.println("Enter courseId:");
        int courseId = in.nextInt();
        in.nextLine();

        System.out.println("Enter new description: ");
        String newDescription = in.nextLine();
        in.nextLine();

        courseDao.updateCourseDescription(courseId, newDescription);
    }

    public static void displayAllCourses() {
        List<Course> courseList = courseDao.getAllCourses();
        for (Course course : courseList) {
            System.out.println(course);
        }
    }

    // for CourseFeedback
    @Override
    public CourseFeedback addCourseFeedback(Scanner in) {
        System.out.println("Course ID:");
        int courseId = in.nextInt();

        in.nextLine();

        Course course = getCourseById(courseId);

        System.out.println("Number of stars:");
        float numberOfStars = verifyNumberOfStars(in);

        in.nextLine();

        System.out.println("Feedback description:");
        String feedbackDescription = in.nextLine();

        CourseFeedback courseFeedback = new CourseFeedback(course, numberOfStars, feedbackDescription);

        courseFeedbacks.add(courseFeedback);
        courseFeedbackDao.insertCourseFeedback(courseFeedback);

        return courseFeedback;
    }

    public static void deleteAnCourseFeedback(Scanner in) {
        System.out.println("Enter courseFeedbackId:");
        int courseFeedbackId = in.nextInt();
        in.nextLine();
        courseFeedbackDao.deleteCourseFeedback(courseFeedbackId);
    }

    public static void updateAnCourseFeedback(Scanner in) {
        System.out.println("Enter courseFeedbackId:");
        int courseFeedbackId = in.nextInt();
        in.nextLine();

        System.out.println("Enter new number of stars:");
        float newNumberOfStars = verifyNumberOfStars(in);

        System.out.println("Enter new feedback message:");
        String newFeedback = in.nextLine();

        courseFeedbackDao.updateCourseFeedback(courseFeedbackId, newNumberOfStars, newFeedback);
    }

    public static void updateAnCourseFeedbackStars(Scanner in) {
        System.out.println("Enter courseFeedbackId:");
        int courseFeedbackId = in.nextInt();
        in.nextLine();

        System.out.println("Enter new number of stars:");
        float newNumberOfStars = verifyNumberOfStars(in);

        courseFeedbackDao.updateCourseFeedbackStars(courseFeedbackId, newNumberOfStars);
    }

    public static void updateAnCourseFeedbackFeedbackMessage(Scanner in) {
        System.out.println("Enter courseFeedbackId:");
        int courseFeedbackId = in.nextInt();
        in.nextLine();

        System.out.println("Enter new feedback message:");
        String newFeedback = in.nextLine();
        in.nextLine();

        courseFeedbackDao.updateCourseFeedbackFeedback(courseFeedbackId, newFeedback);
    }

    public static void displayAllCourseFeedbacks() {
        List<CourseFeedback> courseFeedbacks = courseFeedbackDao.getAllFeedbacks();
        for (CourseFeedback courseFeedback : courseFeedbacks) {
            System.out.println(courseFeedback);
        }
    }

    public static void displayAllCourseFeedbacksForAnSpecificCourse(Scanner in) {
        System.out.println("Enter courseId:");
        int courseId = in.nextInt();
        in.nextLine();

        List<CourseFeedback> courseFeedbacks = courseFeedbackDao.getAllFeedbacksForAnSpecificCourse(courseId);
        for (CourseFeedback courseFeedback : courseFeedbacks) {
            System.out.println(courseFeedback);
        }
    }

    // for Material
    @Override
    public Material addMaterial(Scanner in) {
        System.out.println("Material name:");
        String materialName = in.nextLine();

        System.out.println("Material description:");
        String materialDescription = in.nextLine();

        System.out.println("Is available? yes/no");
        String isAvailable = in.nextLine();

        Material material;
        if (isAvailable.equalsIgnoreCase("yes")) {
            material = new Material(materialName, materialDescription, true);
        } else {
            material = new Material(materialName, materialDescription, false);
        }
        materials.add(material);
        materialDao.insertMaterial(material);
        return material;
    }

    public static void deleteAnMaterial(Scanner in) {
        System.out.println("Enter materialId:");
        int materialId = in.nextInt();
        in.nextLine();
        materialDao.deleteMaterial(materialId);
    }

    public static void updateAnMaterialAvailabilityStatus(Scanner in) {
        System.out.println("Enter materialId:");
        int materialId = in.nextInt();
        in.nextLine();

        System.out.println("Material is now available? (yes/no)");
        boolean available = verifyYesOrNo(in);

        materialDao.updateMaterialAvailability(materialId, available);
    }

    public static void updateAnMaterialDescription(Scanner in) {
        System.out.println("Enter materialId:");
        int materialId = in.nextInt();
        in.nextLine();

        System.out.println("Enter new description:");
        String newDescription = in.nextLine();

        materialDao.updateMaterialDescription(materialId, newDescription);
    }

    public static void updateAnMaterialName(Scanner in) {
        System.out.println("Enter materialId:");
        int materialId = in.nextInt();
        in.nextLine();

        System.out.println("Enter new name:");
        String newName = in.nextLine();

        materialDao.updateMaterialName(materialId, newName);
    }

    public static void displayAllMaterials() {
        List<Material> materialList = materialDao.getAllMaterials();
        for (Material material : materialList) {
            System.out.println(material);
        }
    }

    // for CourseMaterial
    @Override
    public CourseMaterial addCourseMaterial(Scanner in) {
        System.out.println("Enter courseId:");
        int courseId = in.nextInt();

        in.nextLine();

        Course course = getCourseById(courseId);
        System.out.println("Enter materialId:");
        int materialId = in.nextInt();

        in.nextLine();

        Material material = getMaterialById(materialId);

        if (material == null) {
            System.out.println("Couldn't find any material with that id!");
            boolean ok = false;
            while (true) {
                System.out.println("Would you like to try another materialId? (yes/no)");
                String ans = in.nextLine();

                if (ans.matches("yes")) {
                    materialId = in.nextInt();
                    in.nextLine();
                } else if (ans.matches("no")) {
                    break;
                }
                if (getMaterialById(materialId) != null) {
                    ok = true;
                    break;
                }
            }
            if (!ok) {
                System.out.println("So, if you dont want to try another materialId, you need to make an Material!");
                material = addMaterial(in);
            }
        }
        CourseMaterial courseMaterial = new CourseMaterial(course, material);
        courseMaterials.add(courseMaterial);
        courseMaterialDao.insertCourseMaterial(courseMaterial);
        return courseMaterial;
    }

    public static void deleteAnCourseMaterial(Scanner in) {
        System.out.println("Enter courseId:");
        int courseId = in.nextInt();
        in.nextLine();

        System.out.println("Enter materialId:");
        int materialId = in.nextInt();
        in.nextLine();
        courseMaterialDao.deleteCourseMaterial(courseId, materialId);
    }

    public static void updateAnCourseMaterial(Scanner in) {
        System.out.println("Enter courseId");
        int courseId = in.nextInt();
        in.nextLine();

        System.out.println("Enter new courseId");
        int newCourseId = in.nextInt();
        in.nextLine();

        System.out.println("Enter materialId");
        int materialId = in.nextInt();
        in.nextLine();

        System.out.println("Enter new materialId");
        int newMaterialId = in.nextInt();
        in.nextLine();

        courseMaterialDao.updateCourseMaterial(courseId, materialId, newCourseId, newMaterialId);
    }

    public static void displayAllCourseMaterials() {
        List<CourseMaterial> courseMaterials = courseMaterialDao.getAllCourseMaterials();
        for (CourseMaterial courseMaterial : courseMaterials) {
            System.out.println(courseMaterial);
        }
    }

    // for Quiz
    @Override
    public Quiz addQuiz(Scanner in) {
        System.out.println("Course ID:");
        int courseId = in.nextInt();

        in.nextLine();

        Course course = getCourseById(courseId);

        while (true) {
            if (course != null) {
                break;
            } else {
                System.out.println("Couldn't find an course with that id! Please enter below an valid id:");
                courseId = in.nextInt();
                course = getCourseById(courseId);
                in.nextLine();
            }
        }

        System.out.println("Name of Quiz:");
        String quizName = in.nextLine();

        Quiz quiz = new Quiz(course, quizName);

        quizzes.add(quiz);
        quizDao.insertQuiz(quiz);

        return quiz;
    }

    public static void deleteAnQuiz(Scanner in) {
        System.out.println("Enter quizId:");
        int quizId = in.nextInt();
        in.nextLine();
        quizDao.deleteQuiz(quizId);
    }

    public static void updateAnQuizName(Scanner in) {
        System.out.println("Enter quizId:");
        int quizId = in.nextInt();
        in.nextLine();

        System.out.println("Enter new Quiz name:");
        String newQuizName = in.nextLine();

        quizDao.updateQuizName(quizId, newQuizName);
    }

    public static void displayAllQuizzes() {
        List<Quiz> quizList = quizDao.getAllQuizes();
        for (Quiz quiz : quizList) {
            System.out.println(quiz);
        }
    }

    // for Student
    @Override
    public Student addStudent(Scanner in) {
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

        Student student;
        if (hasScholarShip.equalsIgnoreCase("yes")) {
            student = new Student(username, phoneNumber, adress, nationality, gender, birthdate, true);
        } else {
            student = new Student(username, phoneNumber, adress, nationality, gender, birthdate, false);
        }
        users.add(student);
        studentDao.insertStudent(student);
        return student;
    }

    public static void deleteAnStudent(Scanner in) {
        System.out.println("Enter studentId:");
        int studentId = in.nextInt();
        in.nextLine();
        studentDao.deleteStudent(studentId);
    }

    public static void updateAnStudentScholarShipStatus(Scanner in) {
        System.out.println("Enter studentId:");
        int studentId = in.nextInt();
        in.nextLine();

        System.out.println("Student has ScholarShip now? (yes/no)");
        boolean scholarShipStatus = verifyYesOrNo(in);

        studentDao.updateStudentScholarShipAvailability(studentId, scholarShipStatus);
    }

    public static void displayAllStudents() {
        List<Student> studentList = studentDao.getAllStudents();
        for (Student student : studentList) {
            System.out.println(student);
        }
    }

    // for Teacher
    @Override
    public Teacher addTeacher(Scanner in) {
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

        System.out.println("Enter hire date (Format must be: dd-MM-YYYY):");
        String hireDate = verifyDateFormat(in);

        System.out.println("Enter salary:");
        float salary = in.nextFloat();

        in.nextLine();

        Teacher teacher = new Teacher(username, phoneNumber, adress, nationality, gender, birthdate, rank, hireDate, salary);

        users.add(teacher);
        teacherDao.insertTeacher(teacher);

        return teacher;
    }

    public static void deleteAnTeacher(Scanner in) {
        System.out.println("Enter teacherId:");
        int teacherId = in.nextInt();
        in.nextLine();
        teacherDao.deleteTeacher(teacherId);
    }

    public static void updateAnTeacherSalaryByProcent(Scanner in) {
        System.out.println("Enter teacherId:");
        int teacherId = in.nextInt();
        in.nextLine();

        System.out.println("Enter the procent for increase the salary:");
        int procent = in.nextInt();
        in.nextLine();

        teacherDao.updateTeacherSalary(teacherId, procent);
    }

    public static void displayAllTeachers() {
        List<Teacher> teacherList = teacherDao.getAllTeachers();
        for (Teacher teacher : teacherList) {
            System.out.println(teacher);
        }
    }

    // for TeachingAssistant
    @Override
    public TeachingAssistant addTeachingAssistant(Scanner in) {
        System.out.println("Enter username:");
        String username = in.nextLine();

        System.out.println("Enter phone number:");
        String phoneNumber = in.nextLine();

        System.out.println("Enter address:");
        String address = in.nextLine();

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

        TeachingAssistant teachingAssistant = new TeachingAssistant(username, phoneNumber, address, nationality, gender, birthdate, boss, salary);

        users.add(teachingAssistant);
        teachingAssistantDao.insertTeachingAssistant(teachingAssistant);

        return teachingAssistant;
    }

    public static void deleteAnTeachingAssistant(Scanner in) {
        System.out.println("Enter teachingAssistantId:");
        int teachingAssistantId = in.nextInt();
        in.nextLine();
        teachingAssistantDao.deleteTeachingAssistant(teachingAssistantId);
    }

    public static void updateAnTeachingAssistantSalaryByProcent(Scanner in) {
        System.out.println("Enter teachingAssistantId:");
        int teachingAssistantId = in.nextInt();
        in.nextLine();

        System.out.println("Enter the procent for increase the salary:");
        int procent = in.nextInt();
        in.nextLine();

        teachingAssistantDao.updateTeachingAssistantsSalary(teachingAssistantId, procent);
    }

    public static void displayAllTeachingAssistants() {
        List<TeachingAssistant> teachingAssistants = teachingAssistantDao.getAllTeachingAssistants();
        for (TeachingAssistant teachingAssistant : teachingAssistants) {
            System.out.println(teachingAssistant);
        }
    }

    // for UserCourseRepartition
    @Override
    public UserCourseRepartition addCourseRepartition(Scanner in) {
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

        userCourseRepartitions.add(courseRepartition);
        userCourseRepartitionDao.insertUserCourseRepartition(courseRepartition);

        return courseRepartition;
    }

    public static void deleteAnUserCourseRepartition(Scanner in) {
        System.out.println("Enter courseId:");
        int courseId = in.nextInt();
        in.nextLine();

        System.out.println("Enter userId:");
        int userId = in.nextInt();
        in.nextLine();
        userCourseRepartitionDao.deleteUserCourseRepartition(courseId, userId);
    }

    public static void updateAnUserCourseRepartition(Scanner in) {
        System.out.println("Enter courseId");
        int courseId = in.nextInt();
        in.nextLine();

        System.out.println("Enter userId");
        int userId = in.nextInt();
        in.nextLine();

        System.out.println("Enter new startDate");
        String newStartDate = in.nextLine();

        System.out.println("Enter new endDate");
        String newEndDate = in.nextLine();

        userCourseRepartitionDao.updateUserCourseRepartition(courseId, userId, newStartDate, newEndDate);
    }

    public static void displayAllUserCourseRepartitions() {
        List<UserCourseRepartition> userCourseRepartitions = userCourseRepartitionDao.getAllUserCourseRepartitions();
        for (UserCourseRepartition userCourseRepartition : userCourseRepartitions) {
            System.out.println(userCourseRepartition);
        }
    }

    // for User
    public static void deleteAnUser(Scanner in) {
        System.out.println("Enter userId:");
        int userId = in.nextInt();
        in.nextLine();
        userDao.deleteUser(userId);
    }

    public static void updateAnUserGender(Scanner in) {
        System.out.println("Enter userId:");
        int userId = in.nextInt();
        in.nextLine();

        System.out.println("Enter new gender: ");
        String newGender = in.nextLine();

        userDao.updateUserGender(userId, newGender);

        User user = getByUserId(userId);
        if (user != null) {
            user.setGender(newGender);
        } else {
            System.out.println("Couldn't find any user with that id!");
        }
    }

    public static void updateAnUserPhoneNumber(Scanner in) {
        System.out.println("Enter userId:");
        int userId = in.nextInt();
        in.nextLine();

        System.out.println("Enter new phone number: ");
        String newPhoneNumber = in.nextLine();

        userDao.updateUserPhoneNumber(userId, newPhoneNumber);

        User user = getByUserId(userId);
        if (user != null) {
            user.setPhoneNumber(newPhoneNumber);
        } else {
            System.out.println("Couldn't find any user with that id!");
        }
    }

    public static void updateAnUserAddress(Scanner in) {
        System.out.println("Enter userId:");
        int userId = in.nextInt();
        in.nextLine();

        System.out.println("Enter new address: ");
        String newAddress = in.nextLine();

        userDao.updateUserAddress(userId, newAddress);

        User user = getByUserId(userId);
        if (user != null) {
            user.setAddress(newAddress);
        } else {
            System.out.println("Couldn't find any user with that id!");
        }
    }

    public static void updateAnUserNationality(Scanner in) {
        System.out.println("Enter userId:");
        int userId = in.nextInt();
        in.nextLine();

        System.out.println("Enter new address: ");
        String newNationality = in.nextLine();

        userDao.updateUserNationality(userId, newNationality);

        User user = getByUserId(userId);
        if (user != null) {
            user.setAddress(newNationality);
        } else {
            System.out.println("Couldn't find any user with that id!");
        }
    }

    public static void displayAllUsers() {
        for (User user : users)
            System.out.println(user);
    }
}
