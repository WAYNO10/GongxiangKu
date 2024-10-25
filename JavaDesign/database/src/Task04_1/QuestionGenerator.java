/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Task04_1;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
/**
 *
 * @author 20281
 */
public class QuestionGenerator {
    private Random random = new Random();
    private HashSet<String> generatedQuestions = new HashSet<>();

    public List<Question> generateQuestions(int numberOfQuestions) {
        List<Question> questions = new ArrayList<>();
        while (questions.size() < numberOfQuestions) {
            int num1 = random.nextInt(101);
            int num2 = random.nextInt(101);
            char operator = getRandomOperator();
            String questionString = String.format("%d %c %d", num1, operator, num2);

            if (!generatedQuestions.contains(questionString)) {
                generatedQuestions.add(questionString);
                questions.add(new Question(num1, num2, operator));
            }
        }
        return questions;
    }

    private char getRandomOperator() {
        char[] operators = {'+', '-', '*', '/'};
        return operators[random.nextInt(operators.length)];
    }
}
