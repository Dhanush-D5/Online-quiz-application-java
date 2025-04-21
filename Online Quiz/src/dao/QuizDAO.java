package dao;

import util.DBUtil;
import java.sql.*;
import java.util.Scanner;

public class QuizDAO {

    public boolean quizExists(int quizId) {
        try (Connection conn = DBUtil.getConnection()) {
            String sql = "SELECT id FROM quizzes WHERE id = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, quizId);
            ResultSet rs = ps.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public void takeQuiz(int userId, int quizId) {
        int score = 0;
        int totalQuestions = 0;

        try (Connection conn = DBUtil.getConnection();
             Scanner scanner = new Scanner(System.in)) {

            String sql = "SELECT * FROM questions WHERE quiz_id = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, quizId);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                totalQuestions++;
                rs.getInt("id");
                String question = rs.getString("question");
                String option1 = rs.getString("option1");
                String option2 = rs.getString("option2");
                String option3 = rs.getString("option3");
                String option4 = rs.getString("option4");
                String correctAnswer = rs.getString("correct_answer");

                System.out.println("Q: " + question);
                System.out.println("1. " + option1);
                System.out.println("2. " + option2);
                System.out.println("3. " + option3);
                System.out.println("4. " + option4);
                System.out.print("Your answer (1-4): ");
                int userChoice = Integer.parseInt(scanner.nextLine());

                String selectedAnswer = switch (userChoice) {
                    case 1 -> option1;
                    case 2 -> option2;
                    case 3 -> option3;
                    case 4 -> option4;
                    default -> "";
                };

                if (selectedAnswer.equalsIgnoreCase(correctAnswer)) {
                    System.out.println("✅ Correct!\n");
                    score++;
                } else {
                    System.out.println("❌ Incorrect! Correct answer: " + correctAnswer + "\n");
                }
            }

            System.out.println("Quiz completed. Your score: " + score + " out of " + totalQuestions);

            // Save score
            String insertScore = "INSERT INTO quiz_scores (user_id, quiz_id, score) VALUES (?, ?, ?)";
            PreparedStatement scoreStmt = conn.prepareStatement(insertScore);
            scoreStmt.setInt(1, userId);
            scoreStmt.setInt(2, quizId);
            scoreStmt.setInt(3, score);
            scoreStmt.executeUpdate();

        } catch (Exception e) {
            System.out.println("An error occurred while taking the quiz.");
            e.printStackTrace();
        }
    }
}
