# Online-quiz-application-java
A Java-based Online Quiz Application designed to provide users with an interactive quiz-taking experience. This application includes secure user authentication, quiz management for administrators, scoring, and persistent data storage using file handling or a database.

 Features

User Authentication
Sign up and login functionality
Password protection
Role-based access (Admin/User)

Quiz Management (Admin)
Add, update, and delete quiz questions
Set quiz categories and difficulty levels

Quiz Taking (User)
Attempt quizzes from available categories
Timed quizzes (optional)
Score calculation and display

Result Management
View scores after each quiz
Maintain a history of attempts (optional)

Data Persistence
Save user data, questions, and scores
Implemented via file storage or database (e.g., SQLite/MySQL)

Tech Stack
Language: Java
UI: Console-based
Database:  MySQL

To compile this code : javac -cp "lib/mysql-connector-j-9.3.0.jar" -d out src\main\Main.java src\dao\UserDAO.java src\dao\QuizDAO.java src\util\DBUtil.java
To run the code : java -cp "lib/mysql-connector-j-9.3.0.jar;out" main.Main
