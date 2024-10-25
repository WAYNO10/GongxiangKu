/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Task03_1;
import java.util.Random;
import java.util.Scanner;
/**
 *
 * @author 20281
 */
public class MathQuiz {
    private static int score = 0;
    private static final Random random = new Random();
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            int num1 = random.nextInt(101); // Random number between 0 and 100
            int num2 = random.nextInt(101); // Random number between 0 and 100
            char operator = getRandomOperator();
            String question = String.format("%d %c %d", num1, operator, num2);
            System.out.print("What is " + question + "? (Enter 'x' to quit): ");

            String userInput = scanner.nextLine();
            if (userInput.equalsIgnoreCase("x")) {
                break;
            }

            try {
                int userAnswer = Integer.parseInt(userInput);
                int correctAnswer = calculateAnswer(num1, num2, operator);

                if (userAnswer == correctAnswer) {
                    score += 10;
                    System.out.println("Correct! Your score is now: " + score);
                } else {
                    score -= 10;
                    System.out.println("Wrong! The correct answer was " + correctAnswer + ". Your score is now: " + score);
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number or 'x' to quit.");
            }
        }

        System.out.println("Your total score is: " + score);
        scanner.close();
    }

    private static char getRandomOperator() {
        char[] operators = {'+', '-', '*', '/'};
        return operators[random.nextInt(operators.length)];
    }

    private static int calculateAnswer(int num1, int num2, char operator) {
        switch (operator) {
            case '+':
                return num1 + num2;
            case '-':
                return num1 - num2;
            case '*':
                return num1 * num2;
            case '/':
                // Avoid division by zero
                return num2 != 0 ? num1 / num2 : 0;
            default:
                throw new IllegalArgumentException("Invalid operator");
        }
    }
}

