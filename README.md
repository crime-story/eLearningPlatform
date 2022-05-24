# eLearningPlatform

Implemented an ElearningPlatform in Java 17.0.2 version and stored data with MySQL.

You can find Project Tasks [(here)](../main/Tasks.pdf)
### Structure of Project:
- Package dao which contains for each object DDL instructions (create table), DML instructions (insert, update, delete) and sql queries.
- Package pojo which contains all objects
- Package util which containts an main service called *ElearningPlatformService*, an interface called *AdmintInterface* which contains some lists of objects and some necessary methods that must be implemented and an dao (Data Access Object) class which is meant to connect to database or if the database isnt created to create one
- A Main class from which the User can call any of the above options 

### Objects:
- Course
- CourseFeedback
- CourseMaterial
- Material
- Quiz
- Student
- Teacher
- TeachingAssistant
- User
- UserCourseRepartition

### Options implemented:
- [x] Add Student
- [x] Delete Student
- [x] Update Student ScholarShip Status
- [x] Display all Students
- [x] Add Teacher
- [x] Delete Teacher
- [x] Increase Teacher Salary by procent
- [x] Display all Teachers
- [x] Add Teaching Assistant
- [x] Delete Teaching Assistant
- [x] Increase Teaching Assistant Salary by procent
- [x] Display all Teaching Assistants
- [x] Delete User
- [x] Update User Gender
- [x] Update User Phone Number
- [x] Update User Address
- [x] Update User Nationality
- [x] Find User By Id
- [x] Display all Users
- [x] Add Course
- [x] Delete Course
- [x] Update Course description
- [x] Display all Courses
- [x] Add Course Feedback
- [x] Delete Course Feedback
- [x] Update Course Feedback (number of stars, feedback)
- [x] Update Course Feedback (only number of stars)
- [x] Update Course Feedback (only feedback)
- [x] Display all CourseFeedbacks
- [x] Display all CourseFeedbacks for an specific Course"
- [x] Add Quiz
- [x] Delete Quiz
- [x] Update Quiz Name
- [x] Display all Quizzes
- [x] Add Material
- [x] Delete Material
- [x] Update Material Availability Status
- [x] Update Material Description
- [x] Update Material Name
- [x] Display all Materials used for an Course by Course ID
- [x] Display all Materials
- [x] Add Course Material
- [x] Delete Course Material
- [x] Update Course Material
- [x] Display all Course Materials
- [x] Add User Course Repartition
- [x] Delete User Course Repartition
- [x] Update User Course Repartition
- [x] Display all Course Repartitions for an Specific Student
- [x] Display all User Course Repartitions

### Additional:
- Audit Service where you can see logs of actions made by an User (name of action & timestamp)
