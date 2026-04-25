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
        
        String introChoice = ui.getUserInput("Press ENTER to continue, or type \"s\" to skip opening: ");

        if (!introChoice.equalsIgnoreCase("s")) {

        ui.displayText("\n=== OPENING ===");

        slowPrint("I was about to relax at home when " + petName + " squeezed through the window.");
        slowPrint("\"Where did you go, buddy?\" " + username + " pulled " + petName + " into my arms.");
        slowPrint("\"You're always so adventurous.\"");

        slowPrint("\"I passed by the Animal University of Technology today.");
        slowPrint("There's a Java competition going on,\" " + petName + " announced.");
        slowPrint("\"I have enrolled you, " + username + ".\"");

        slowPrint("\"Why!? I don't know much Java,\" " + username + " protested.");

        slowPrint("\"Because first place wins a lifetime supply of pet food.");
        slowPrint("And I plan to FEAST!\" " + petName + " cackled.");

        slowPrint(username + " paused.");
        slowPrint("\"Well, it would spare my wallet...\"");
    }
        
        List<Question> questions = questionPool.getRandomQuestions(10);
        ui.displayText("Quest: You will now attempt the Java competition! Answer all 10 quiz questions correctly to win.\n"
                + "You can type \"quit\" at any time to leave and save your game\n");
        //ui.displayText("DEBUG: Number of questions = " + questions.size() + "\n");
        ui.getUserInput("\nPress ENTER to continue...");
        
        
        quiz = new QuizSession(0, questions, 0, user);
        runQuiz();
    }
    
    private void slowPrint(String text) {
        ui.displayText(text);

        try {
            Thread.sleep(4000); // 2000 ms = 2 seconds
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    public void loadGame() {
        String username = ui.getUserInput("Enter username: ");
        quiz = userRecord.loadGame(username);

        if (quiz == null) {
            ui.displayError("No saved game found.\n");
            return;
        }

        runQuiz();
    }

    private void runQuiz() {
        List<Question> questions = quiz.getQuestions();

        for (int i = quiz.getCurrentQuestionIndex(); i < questions.size(); i++) {
            Question q = questions.get(i);

            ui.displayText(q.getQuestionText());
            
            String input;

            while (true) {
                input = ui.getUserInput("Answer (1-3 or type 'quit'): ");

                if (input.equalsIgnoreCase("quit")) {
                    quiz = new QuizSession(i, questions, quiz.getNumCorrectAnswers(), quiz.getUser());
                    handleExitDuringGame();
                    return;
                }

                try {
                    int choice = Integer.parseInt(input);

                    if (choice >= 1 && choice <= 3) {
                        break;
                    } else {
                        ui.displayError("Please enter 1, 2, or 3.");
                    }

                } catch (NumberFormatException e) {
                    ui.displayError("Please enter a number.");
                }
            }

            // Check answer
           
            if (q.checkAnswer(input)){
                // increment score manually since no method exists
                int newScore = quiz.getNumCorrectAnswers() + 1;
                quiz = new QuizSession(i + 1, questions, newScore, quiz.getUser());

                ui.displayText("Correct!\n");
            } else {
                quiz = new QuizSession(i + 1, questions, quiz.getNumCorrectAnswers(), quiz.getUser());

                ui.displayText("Incorrect.\n");
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
        /*String saveChoice = ui.getUserInput("Would you like to save your score? (y/n): ");
        if (saveChoice.equalsIgnoreCase("y")) {

            // update high score if better
            User user = quiz.getUser();

            if (score > user.getHighScore()) {
                user.setHighScore(score);
            }

            userRecord.saveRecord(user);
            userRecord.saveGame(quiz);

            ui.displayText("Progress saved.");
        }*/

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
        
        //add exception here
    }

    public void exit() {
        ui.displayText("Goodbye!");
        System.exit(0);
    }
}
