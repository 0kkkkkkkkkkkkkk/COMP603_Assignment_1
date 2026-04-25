/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Question;

/**
 *
 * @author hmarl
 */

public class MultiChoiceQuestion implements Question {
    private String questionText;
    private int answer;
    private int id;

    public MultiChoiceQuestion(String questionText, int id, int answer) {
        this.id = id;
        this.questionText = questionText;
        this.answer = answer;
    }

    @Override
    public String getQuestionText() {
        return questionText;
    }

    @Override
    public boolean checkAnswer(String userAnswer) {
        try {
            int userChoice = Integer.parseInt(userAnswer.trim());
            return userChoice == answer;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    @Override
    public int getQuestionID() {
        return id;
    }
}