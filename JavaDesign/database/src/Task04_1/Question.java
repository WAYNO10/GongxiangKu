/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Task04_1;

/**
 *
 * @author 20281
 */
public class Question {
    private int num1;
    private int num2;
    private char operator;
    private int answer;

    public Question(int num1, int num2, char operator) {
        this.num1 = num1;
        this.num2 = num2;
        this.operator = operator;
        this.answer = calculateAnswer();
    }

    private int calculateAnswer() {
        switch (operator) {
            case '+':
                return num1 + num2;
            case '-':
                return num1 - num2;
            case '*':
                return num1 * num2;
            case '/':
                return (num2 != 0) ? num1 / num2 : 0; // Avoid division by zero
            default:
                throw new IllegalArgumentException("Invalid operator");
        }
    }

    public String getQuestionString() {
        return String.format("%d %c %d", num1, operator, num2);
    }

    public int getAnswer() {
        return answer;
    }
}
