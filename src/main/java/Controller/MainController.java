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
    //added variable to easily adjust question size
    int numQuestions = 10;
            
    private UI ui;
    private Menu menu;
    private UserRecord userRecord;
    private QuestionPool questionPool;
    private QuizSession quiz;

    //define/initialise objects in constructor
    public MainController() {
        ui = new CUI();
        //pass on ui object to be used in menu
        menu = new Menu(ui);
        userRecord = new UserRecordFileIO();
        questionPool = new QuestionPool();
    }

    public static void main(String[] args) {
        new MainController().start();
    }

    //display menu
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

        //create new User object with highScore of 0
        User user = new User(username, petName, 0);
        
        //ask to display introduction
        String introChoice = ui.getUserInput("Enter anything to view "
                + "introduction, or type \"s\" to skip: ");

        if (!introChoice.equalsIgnoreCase("s")) {
            ui.displayText("\n=== INTRODUCTION ===");
            slowPrint("I was about to relax at home when " + petName + " squeezed through the window.");
            slowPrint("\"Where did you go, buddy?\" I pulled " + petName + " into my arms.");
            slowPrint("\"You're always so adventurous.\"");
            slowPrint("\"I passed by the Animal University of Technology today.");
            slowPrint("There's a Java competition going on,\" " + petName + " announced.");
            slowPrint("\"I have enrolled you, " + username + ".\"");
            slowPrint("\"Why!? I don't know much Java,\" " + "I protested.");
            slowPrint("\"Because first place wins a lifetime supply of pet food.");
            slowPrint("And I plan to FEAST!\" " + petName + " cackled.");
            slowPrint("I paused.");
            slowPrint("\"Well, it would spare my wallet...\"");
            ui.displayText("=================");
        }
        
        //fetch generated questions with size as arg
        List<Question> questions = questionPool.getRandomQuestions(numQuestions);
        ui.displayText("\nQuest: You will now attempt the Java competition!");
        //ui.displayText("DEBUG: Number of questions = " + questions.size() + "\n");
        ui.getUserInput("Enter anything to continue...");
        ui.displayText("");
        
        //create QuizSession object
        quiz = new QuizSession(0, questions, 0, user);
        runQuiz();
    }
    
    // print with delays for introduction
    private void slowPrint(String text) {
        ui.displayText(text);

        try {
            Thread.sleep(4000); // 4000 ms = 4 seconds
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    public void loadGame() {
        String username = ui.getUserInput("Enter username: ");
        //attempt to load save file
        quiz = userRecord.loadGame(username);
        //return if no save file found corresponding to user
        if (quiz == null) {
            ui.displayError("No saved game found.\n");
            return;
        }

        ui.displayText("Saved File Found!!");
        slowPrint("Returning where you left off...\n");
        
        runQuiz();
    }

    private void runQuiz() {
        //fetch generated questions
        List<Question> questions = quiz.getQuestions();

        //iterate through questions list, starting at CurrentQuestionIndex
        //suitable for continuing off a save file and starting new game
        for (int i = quiz.getCurrentQuestionIndex(); i < questions.size(); i++) {
            //get Question object (element) at index i in questions list
            Question q = questions.get(i);
            int questionNumber = i + 1;
            //display current question number starting at 1
            ui.displayText("\n=== QUESTION: "+ questionNumber + " ===");
            //get question text
            ui.displayText(q.getQuestionText());

            String input;

            while (true) {
                //get user input
                input = ui.getUserInput("Answer (1-3 or type 'quit'): ");
                
                //check if user wants to quit
                if (input.equalsIgnoreCase("quit")) {
                    //---why?
                    quiz = new QuizSession(i, questions,
                            quiz.getNumCorrectAnswers(),
                            quiz.getUser());

                    handleExitDuringGame();
                    return;
                }

                try {
                    //parse string for int
                    int choice = Integer.parseInt(input);

                    if (choice >= 1 && choice <= 3) {
                        break;
                    } else {
                        ui.displayError("Please enter 1, 2 or 3.");
                    }

                } catch (NumberFormatException e) {
                    ui.displayError("Please enter a number.");
                }
            }
            
            //check if user answers correctly
            if (q.checkAnswer(input)) {
                int newScore = quiz.getNumCorrectAnswers() + 1;
                //--why creating so many QuizSession objects?
                quiz = new QuizSession(i + 1, questions,
                        newScore, quiz.getUser());

                ui.displayText("Correct!");

            } else {
                quiz = new QuizSession(i + 1, questions,
                        quiz.getNumCorrectAnswers(),
                        quiz.getUser());

                ui.displayText("Incorrect.");
            }
            
            //display question explanation
            ui.displayText(questions.get(i).getExplanation());
            //display current score
            ui.displayText("Score: " + quiz.getNumCorrectAnswers()+ "/"
                    + questions.size());
            ui.getUserInput("Enter anything to continue:");
        }

        finishQuiz();   // occurs after all questions
    }

    private void finishQuiz() {

        int score = quiz.getNumCorrectAnswers();
        int totalQuestions = quiz.getQuestions().size();
        //get trophy type
        String result = quiz.calculateResult();

        ui.displayText("\n=== RESULTS ===");
        // 1. Show score + result
        ui.displayText("You got " + score + "/" + totalQuestions + " questions correct.");
        ui.displayText("Result: " + result + "\n");

        // 3. Ask to continue
        String continueChoice = ui.getUserInput("Would you like to like to return to menu? (y/n): ");

        if (continueChoice.equalsIgnoreCase("n")) {
            exit();
        }

        // if yes -> returns to menu automatically because of while true loop
    }

    private void handleExitDuringGame() {
        String save = ui.getUserInput("Save progress? (y/n): ");
        if (save.equalsIgnoreCase("y")) {
            userRecord.saveGame(quiz);
        }
        
        //add exception here
    }

    public void exit() {
        ui.displayText("Thanks for playing!");
        System.exit(0);
    }
}
