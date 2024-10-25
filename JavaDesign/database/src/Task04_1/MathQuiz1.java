package Task04_1;

import java.util.Random;
import java.util.Scanner;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author Shiqing Wu
 */
public class MathQuiz1 {
    private static final int QUESTIONS_PER_GAME = 10;
    private static Scanner scanner = new Scanner(System.in);
    private static UserManager userManager = new UserManager();

    public static void main(String[] args) {
        userManager.loadUsers();
        System.out.print("Enter your username: ");
        String username = scanner.nextLine();
        User user = userManager.getUser(username);
        if (user == null) {
            user = new User(username);
            userManager.saveUser(user);
        }

        QuestionGenerator questionGenerator = new QuestionGenerator();
        List<Question> questions = questionGenerator.generateQuestions(QUESTIONS_PER_GAME);

        for (Question question : questions) {
            System.out.print("What is " + question.getQuestionString() + "? (Enter 'x' to quit): ");
            String userInput = scanner.nextLine();
            if (userInput.equalsIgnoreCase("x")) {
                break;
            }

            try {
                int userAnswer = Integer.parseInt(userInput);
                if (userAnswer == question.getAnswer()) {
                    user.updateScore(10);
                    System.out.println("Correct! Your score is now: " + user.getScore());
                } else {
                    user.updateScore(-10);
                    System.out.println("Wrong! The correct answer was " + question.getAnswer() + ". Your score is now: " + user.getScore());
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number or 'x' to quit.");
            }
        }

        userManager.updateUserScore(user);
        System.out.println("Your total score is: " + user.getScore());
        scanner.close();
    }
}
