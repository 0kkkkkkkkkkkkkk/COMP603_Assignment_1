/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package Controller;

/**
 *
 * @author hmarl
 */

import User_Interface.CUI;
import User_Interface.UI;
import User_Interface.Menu;
import Question.Question;
import Question.QuestionPool;
import Persistence.UserRecordFileIO;
import Persistence.UserRecord;
import Model.User;
import Model.QuizSession;

import java.util.List;

public class MainController {

    private UI ui;
    private Menu menu;
    private UserRecord userRecord;
    private QuestionPool questionPool;

    private QuizSession quiz;

    public MainController() {
        ui = new CUI();
        menu = new Menu(ui);
        userRecord = new UserRecordFileIO();
        questionPool = new QuestionPool();
    }

    public static void main(String[] args) {
        new MainController().start();
    }

    public void start() {
        while (true) {
            int choice = menu.displayMenu();

            switch (choice) {
                case 1 -> startNewGame();
                case 2 -> loadGame();
                case 3 -> exit();
                default -> ui.displayError("Invalid option.");
            }
        }
    }

    public void startNewGame() {
        String username = ui.getUserInput("Enter username: ");
        String petName = ui.getUserInput("Enter pet name: ");

        User user = new User(username, petName, 0);
        
        List<Question> questions = questionPool.getRandomQuestions(10);
        ui.displayText("You are enrolled in the Java competition! Answer all 10 quiz questions correctly to win."
                + "\nNote! Please enter the number of the option you think is correct");
        ui.displayText("DEBUG: Number of questions = " + questions.size() + "\n");
        quiz = new QuizSession(0, questions, 0, user);
        runQuiz();
    }

    public void loadGame() {
        String username = ui.getUserInput("Enter username: ");
        quiz = userRecord.loadGame(username);

        if (quiz == null) {
            ui.displayError("No saved game found.");
            return;
        }

        runQuiz();
    }

    private void runQuiz() {
        List<Question> questions = quiz.getQuestions();

        for (int i = quiz.getCurrentQuestionIndex(); i < questions.size(); i++) {
            Question q = questions.get(i);

            ui.displayText(q.getQuestionText());
            String input = ui.getUserInput("Answer (or type 'quit'): ");

            // Handle quit
            if (input.equalsIgnoreCase("quit")) {
                quiz = new QuizSession(i, questions, quiz.getNumCorrectAnswers(), quiz.getUser());
                handleExitDuringGame();
                return;
            }

            // Check answer
            if (q.checkAnswer(input)) {
                // increment score manually since no method exists
                int newScore = quiz.getNumCorrectAnswers() + 1;
                quiz = new QuizSession(i + 1, questions, newScore, quiz.getUser());

                ui.displayText("Correct!");
            } else {
                quiz = new QuizSession(i + 1, questions, quiz.getNumCorrectAnswers(), quiz.getUser());

                ui.displayText("Incorrect.");
            }

            // TODO: show explanation
        }

        finishQuiz();
    }

    private void finishQuiz() {

        int score = quiz.getNumCorrectAnswers();
        int totalQuestions = quiz.getQuestions().size();

        String result = quiz.calculateResult();

        // 1. Show score + result
        ui.displayText("You got " + score + "/" + totalQuestions + " questions correct.");
        ui.displayText("Result: " + result);
        ui.displayText("");

        // 2. Ask to save score
        String saveChoice = ui.getUserInput("Would you like to save your score? (y/n): ");

        if (saveChoice.equalsIgnoreCase("y")) {

            // update high score if better
            User user = quiz.getUser();

            if (score > user.getHighScore()) {
                user.setHighScore(score);
            }

            userRecord.saveRecord(user);

            ui.displayText("Score saved.");
        }

        // 3. Ask to continue
        String continueChoice = ui.getUserInput("Would you like to like to return to title? (y/n): ");

        if (continueChoice.equalsIgnoreCase("n")) {
            exit();
        }

        // if yes -> returns to menu automatically
    }

    private void handleExitDuringGame() {
        String save = ui.getUserInput("Save progress? (yes/no): ");
        if (save.equalsIgnoreCase("yes")) {
            userRecord.saveGame(quiz);
        }
    }

    public void exit() {
        ui.displayText("Goodbye!");
        System.exit(0);
    }
}
