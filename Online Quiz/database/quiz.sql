-- File: database/schema.sql
CREATE DATABASE online_quiz;
USE online_quiz;

CREATE TABLE users (
    id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(100) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL
);

CREATE TABLE quizzes (
    id INT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(255) NOT NULL
);

CREATE TABLE attempts (
    id INT AUTO_INCREMENT PRIMARY KEY,
    user_id INT,
    quiz_id INT,
    score INT,
    attempt_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (user_id) REFERENCES users(id),
    FOREIGN KEY (quiz_id) REFERENCES quizzes(id)
);

INSERT INTO quizzes (title) VALUES ('Java Basics');
SET @quizId = LAST_INSERT_ID();

CREATE TABLE questions (
    id INT AUTO_INCREMENT PRIMARY KEY,
    quiz_id INT NOT NULL,
    question VARCHAR(255) NOT NULL,
    option1 VARCHAR(255),
    option2 VARCHAR(255),
    option3 VARCHAR(255),
    option4 VARCHAR(255),
    correct_answer VARCHAR(255),
    FOREIGN KEY (quiz_id) REFERENCES quizzes(id)
);


CREATE TABLE quiz_scores (
    id INT AUTO_INCREMENT PRIMARY KEY,
    user_id INT NOT NULL,
    quiz_id INT NOT NULL,
    score INT NOT NULL,
    FOREIGN KEY (user_id) REFERENCES users(id),
    FOREIGN KEY (quiz_id) REFERENCES quizzes(id)
);

-- Adding some sample questions for quiz_id = 1
INSERT INTO questions (quiz_id, question, option1, option2, option3, option4, correct_answer)
VALUES
(1, 'What is the capital of France?', 'Berlin', 'Madrid', 'Paris', 'Rome', 'Paris'),
(1, 'What is the largest planet in our solar system?', 'Earth', 'Jupiter', 'Mars', 'Venus', 'Jupiter'),
(1, 'Which programming language is known as the "mother of all languages"?', 'Python', 'C', 'Java', 'Fortran', 'Fortran'),
(1, 'Which country is known as the Land of the Rising Sun?', 'China', 'Japan', 'India', 'Korea', 'Japan'),
(1, 'Which of the following is a prime number?', '4', '6', '9', '11', '11');

INSERT INTO quizzes (title) VALUES ('General Knowledge');
INSERT INTO quizzes (title) VALUES ('Science');

INSERT INTO questions (quiz_id, question, option1, option2, option3, option4, correct_answer)
VALUES
(2, 'Who developed the theory of relativity?', 'Isaac Newton', 'Nikola Tesla', 'Albert Einstein', 'Galileo Galilei', 'Albert Einstein'),
(2, 'What is the hardest natural substance on Earth?', 'Gold', 'Diamond', 'Iron', 'Platinum', 'Diamond'),
(2, 'Which element has the chemical symbol "Au"?', 'Silver', 'Aluminum', 'Gold', 'Uranium', 'Gold'),
(2, 'In which year did the Titanic sink?', '1900', '1912', '1920', '1932', '1912'),
(2, 'Who was the first woman to win a Nobel Prize?', 'Marie Curie', 'Rosalind Franklin', 'Ada Lovelace', 'Dorothy Crowfoot Hodgkin', 'Marie Curie');

INSERT INTO questions (quiz_id, question, option1, option2, option3, option4, correct_answer)
VALUES
(3, 'What is the most abundant element in the Earth\'s crust?', 'Oxygen', 'Silicon', 'Iron', 'Aluminum', 'Oxygen'),
(3, 'What is the process by which plants make their own food using sunlight?', 'Respiration', 'Transpiration', 'Photosynthesis', 'Fermentation', 'Photosynthesis'),
(3, 'Which scientist is known for developing the first periodic table of elements?', 'Marie Curie', 'Albert Einstein', 'Dmitri Mendeleev', 'Isaac Newton', 'Dmitri Mendeleev'),
(3, 'What is the atomic number of carbon?', '6', '8', '12', '14', '6'),
(3, 'What is the name of the galaxy that contains our Solar System?', 'Andromeda', 'Milky Way', 'Sombrero', 'Whirlpool', 'Milky Way');

