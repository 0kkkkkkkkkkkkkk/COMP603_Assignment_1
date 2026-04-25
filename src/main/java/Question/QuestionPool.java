/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Question;

/**
 *
 * @author hmarl
 */
import java.util.*;

public class QuestionPool {
    
    private Map<Integer, Question> questionPool = new HashMap<>();
    private Random random = new Random();

    public QuestionPool() {
        questionPool = new HashMap<>();
        loadQuestions();
    }

    public Map<Integer, Question> getQuestionPool() {
        return questionPool;
    }

    /**
     * Returns N random unique questions
     */
    public List<Question> getRandomQuestions(int numQuestions) {
    List<Question> allQuestions = new ArrayList<>(questionPool.values());
    Collections.shuffle(allQuestions);
    return allQuestions.subList(0, numQuestions);
    }

    public Question getQuestionByID(int id) {
        return questionPool.get(id);
    }
    
    private void loadQuestions() {
        questionPool.put(1, new MultiChoiceQuestion(
            "What does JVM stand for?\n1. Java Variable Machine\n2. Java Virtual Machine\n3. Joint Vector Model",
            2, 1));

        questionPool.put(2, new MultiChoiceQuestion(
            "Which symbol ends a Java statement?\n1. ;\n2. :\n3. .",
            1, 2));

        questionPool.put(3, new MultiChoiceQuestion(
            "Which keyword creates an object?\n1. make\n2. new\n3. class",
            2, 3));

        questionPool.put(4, new MultiChoiceQuestion(
            "Which data type stores whole numbers?\n1. int\n2. double\n3. String",
            1, 4));

        questionPool.put(5, new MultiChoiceQuestion(
            "Which class reads keyboard input?\n1. Scanner\n2. Reader\n3. Keyboard",
            1, 5));

        questionPool.put(6, new MultiChoiceQuestion(
            "Which principle means hiding internal details?\n1. Inheritance\n2. Encapsulation\n3. Looping",
            2, 6));

        questionPool.put(7, new MultiChoiceQuestion(
            "Which keyword is used for selection?\n1. if\n2. loop\n3. choose",
            1, 7));

        questionPool.put(8, new MultiChoiceQuestion(
            "Which principle allows many forms of a method?\n1. Polymorphism\n2. Compilation\n3. Selection",
            3, 8));

        questionPool.put(9, new MultiChoiceQuestion(
            "Which principle allows one class to use another class's features?\n1. Inheritance\n2. Casting\n3. Printing",
            2, 9));
        
        questionPool.put(10, new MultiChoiceQuestion(
            "Which package must be imported to use Scanner?\n1. java.util\n2. java.io\n3. java.lang",
            1, 10));
        
        questionPool.put(11, new MultiChoiceQuestion(
            "What does JDK stand for?\n1. Java Development Kit\n2. Java Design Kernel\n3. Joint Data Kit",
            1, 11));
        
        questionPool.put(12, new MultiChoiceQuestion(
            "Which keyword prevents inheritance of a class?\n1. static\n2. final\n3. private",
            2, 12));

        
    }
}