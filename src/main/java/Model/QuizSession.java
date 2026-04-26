/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author hmarl
 */

import Question.Question;
import java.util.List;

public class QuizSession {
    private int currentQuestionIndex;
    private List<Question> questions;
    private int numCorrectAnswers;
    private User user;

    public QuizSession(int currentQuestionIndex, List<Question> questions,
                       int numCorrectAnswers, User user) 
    {
        this.currentQuestionIndex = currentQuestionIndex;
        this.questions = questions;
        this.numCorrectAnswers = numCorrectAnswers;
        this.user = user;
    }

    public int getCurrentQuestionIndex() {
        return currentQuestionIndex;
    }

    public List<Question> getQuestions() {
        return questions;
    }

    public int getNumCorrectAnswers() {
        return numCorrectAnswers;
    }

    public User getUser() {
        return user;
    }

    public void incrementScore() {
        numCorrectAnswers++;
    }


//    public void nextQuestion() {
//        currentQuestionIndex++;
//    }

//    public boolean isFinished() {
//        return currentQuestionIndex >= questions.size();
//    }

//    public Question getCurrentQuestion() {
//        return questions.get(currentQuestionIndex);
//    }

    /**
     * Determines trophy result
     */
    public String calculateResult() {
        double percentage = (double) numCorrectAnswers / questions.size() * 100;

        if (percentage >= 80) return "Gold";
        if (percentage >= 70) return "Silver";
        if (percentage >= 60) return "Bronze";
        return "Loss";
    }
}