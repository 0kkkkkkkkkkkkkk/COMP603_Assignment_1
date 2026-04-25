package Persistence;

import Model.User;
import Model.QuizSession;
import Question.Question;
import Question.QuestionPool;

import java.io.*;
import java.util.*;

public class UserRecordFileIO implements UserRecord {

    private final String FOLDER = "saves/";

    public UserRecordFileIO() {
        new File(FOLDER).mkdirs(); // create saves folder
    }

    @Override
    public void saveRecord(User user) {

        String username = user.getUsername().trim();

        File file = new File(FOLDER + username + "_user.txt");

        try (FileWriter fw = new FileWriter(file, false)) {

            fw.write(user.getUsername() + "\n");
            fw.write(user.getPetName() + "\n");
            fw.write(user.getHighScore() + "\n");

            System.out.println("User record saved for: " + username);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public User loadRecord(String username) {
        try {
            File file = new File(FOLDER + username + "_user.txt");

            if (!file.exists()) return null;

            Scanner sc = new Scanner(file);

            String name = sc.nextLine();
            String pet = sc.nextLine();
            int highScore = Integer.parseInt(sc.nextLine());

            sc.close();

            return new User(name, pet, highScore);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public void saveGame(QuizSession session) {

        String username = session.getUser().getUsername().trim();

        File file = new File(FOLDER + username + "_game.txt");

        try (FileWriter fw = new FileWriter(file, false)) {
            // false = overwrite existing file

            fw.write(session.getUser().getUsername() + "\n");
            fw.write(session.getUser().getPetName() + "\n");
            fw.write(session.getCurrentQuestionIndex() + "\n");
            fw.write(session.getNumCorrectAnswers() + "\n");

            for (Question q : session.getQuestions()) {
                fw.write(q.getQuestionID() + ",");
            }

            System.out.println("Game saved for user: " + username);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public QuizSession loadGame(String username) {
        try {
            File file = new File(FOLDER + username.trim() + "_game.txt");

            if (!file.exists()) {
                return null;
            }

            Scanner sc = new Scanner(file);

            String name = sc.nextLine();          // username
            String petName = sc.nextLine();      // pet name
            int index = Integer.parseInt(sc.nextLine());
            int score = Integer.parseInt(sc.nextLine());
            String idsLine = sc.nextLine();

            sc.close();

            // load user
            User user = new User(name, petName, 0);

            QuestionPool pool = new QuestionPool();
            List<Question> questions = new ArrayList<>();

            String[] ids = idsLine.split(",");

            for (String id : ids) {
                if (!id.isBlank()) {
                    questions.add(
                        pool.getQuestionByID(Integer.parseInt(id))
                    );
                }
            }

            return new QuizSession(index, questions, score, user);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
}