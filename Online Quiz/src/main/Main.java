package main;

import dao.UserDAO;
import dao.QuizDAO;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        UserDAO userDAO = new UserDAO();
        QuizDAO quizDAO = new QuizDAO();

        System.out.println("Welcome to the Online Quiz System");
        System.out.print("Do you want to (1) Login or (2) Register? ");
        int choice = Integer.parseInt(scanner.nextLine());

        String username, password;
        boolean loggedIn = false;

        if (choice == 1) {
            System.out.print("Enter username: ");
            username = scanner.nextLine();
            System.out.print("Enter password: ");
            password = scanner.nextLine();

            loggedIn = userDAO.login(username, password);
            if (!loggedIn) {
                System.out.println("Login failed.");
                scanner.close();
                return;
            } else {
                System.out.println("Login successful.");
            }

        } else if (choice == 2) {
            System.out.print("Enter username: ");
            username = scanner.nextLine();
            System.out.print("Enter password: ");
            password = scanner.nextLine();

            boolean registered = userDAO.register(username, password);
            if (registered) {
                System.out.println("Registration successful. Please login to continue.");
                scanner.close();
                return;
            } else {
                System.out.println("Registration failed.");
                scanner.close();
                return;
            }
        } else {
            System.out.println("Invalid option.");
            scanner.close();
            return;
        }

        int quizId;
        while (true) {
            System.out.println("Enter 1 : Random");
            System.out.println("Enter 2 : General Knowledge");
            System.out.println("Enter 3 : Science");
            System.out.print("Enter quiz ID to take: ");
            try {
                quizId = Integer.parseInt(scanner.nextLine());
                if (quizDAO.quizExists(quizId)) {
                    break;
                } else {
                    System.out.println("Invalid quiz ID. Please try again.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number.");
            }
        }

        int userId = userDAO.getUserId(username);
        quizDAO.takeQuiz(userId, quizId);

        scanner.close();
    }
}
