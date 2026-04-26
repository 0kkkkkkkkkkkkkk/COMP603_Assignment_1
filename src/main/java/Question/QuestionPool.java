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
    //hashMap to keep track of question index and question object
    private Map<Integer, Question> questionPool = new HashMap<>();
//    private Random random = new Random();

    public QuestionPool() {
        questionPool = new HashMap<>();
        loadQuestions();
    }

    //return question pool if needed
    public Map<Integer, Question> getQuestionPool() {
        return questionPool;
    }

    // get random questions based on numQuestions size
    public List<Question> getRandomQuestions(int numQuestions) {
    List<Question> allQuestions = new ArrayList<>(questionPool.values());
    //randomise question order
    Collections.shuffle(allQuestions);
    //get list of first numQuestions of questions
    return allQuestions.subList(0, numQuestions);
    }

    public Question getQuestionByID(int id) {
        return questionPool.get(id);
    }
    
    //method to insert questions into questionPool map
    //note: question attribute format (questionText, explanation, answer, id)
    private void loadQuestions() {
        questionPool.put(1, new MultiChoiceQuestion(
            """
            Student student1;
            What is the term called for creating this object?
            1) Declare
            2) Define
            2) Initialise
            """,
            """
            Declaring an object or variable does not assign a value to it yet. 
            It introduces this object to the program and the value is assigned 
            later.
            """,
            1, 1));

        questionPool.put(2, new MultiChoiceQuestion(
            """
            What is encapsulation?
            1) Passing on code from a parent to child class for reusability
            2) Restricting access to fields in a class. Access the fields 
            through public methods
            3) An object being able to take on multiple forms, such as a parent 
            class being used to refer to a child class object
            """,
            """
            One of the reasons encapsulation is used is for making sure data 
            does not get altered as easily, especially if it is important. 
            Example: a medicine dosage is accidentally changed from 10 to 100mg 
            in another part of the program.
            """,
            2, 2));

        questionPool.put(3, new MultiChoiceQuestion(
            """
            When a field is set to default accessibility (no accessibility 
            defined), how can the field be accessed?
            1) Only methods in the same class can access the field
            2) Any method from any class can access the field
            3) Only methods in the same package can access the field
            """,
            """
            Default accessibility is similar to protected accessibility, except 
            that protected accepts subclasses, even if they are in different 
            packages. Default is limited to classes and methods the same package
            only.
            """,
            3, 3));

        questionPool.put(4, new MultiChoiceQuestion(
            """
            Which collection interface has no order and no duplicate elements?
            1) List
            2) Map
            3) Set
            """,
            """
            Lists allow duplicate elements and indexing (order). Sets are good
            for storing unique elements and when indexing does not matter. Maps
            link keys to values like a dictionary, but there cannot be duplicate
            keys.
            """,
            3, 4));

        questionPool.put(5, new MultiChoiceQuestion(
            """
            animals.put('cat', 'meow');
            Which collection interface works with this command?
            1) Set
            2) Map
            3) List
            """,
            """
            To insert an element to a collection, sets and lists use add() while
            maps use put(). The parameters for put() are (key, value).
            """,
            2, 5));

        questionPool.put(6, new MultiChoiceQuestion(
            """
            myList.get(3);
            What does this command do?
            1) Get the element at index 3 in the list
            2) Get the element 3 in the list
            3) Get the first 3 elements in the list
            """,
            """
            The parameter for get() is index of integer type (index: integer). 
            It retrieves the element at that index.
            """,
            1, 6));

        questionPool.put(7, new MultiChoiceQuestion(
            """
            Which command is the proper way to create a generic ArrayList 
            collection?
            1) ArrayList<Integer> list = new ArrayList<>();
            2) ArrayList list<Integer> = new ArrayList();
            3) ArrayList list = new ArrayList();
            """,
            """
            A generic collection requires specifying an Object data type 
            (reference type). In this case, it is Integer, which means that the
            ArrayList can only hold Integers. The full command for creating the
            ArrayList is actually: 
            ArrayList<Integer> list = new ArrayList<Integer>();
            However, Java developers are lazy, so the second diamond operator 
            (<>) can be left empty. The program already knows what the data type
            will be based on the assignment on the left side. This is known as 
            type inference.
            """,
            1, 7));

        questionPool.put(8, new MultiChoiceQuestion(
            """
            What is buffering in File Input/Output (File I/O)?
            1) Each byte of information is read from disk or written to disk as 
            soon as possible.
            2) Each byte is stored until the storage that holds these bytes is 
            full, then it is read from or written to disk.
            3) Each byte is read from disk or written to disk after a 10 
            milisecond delay.
            """,
            """
            A buffer is a block of RAM that stores these bytes. It causes 
            reading and writing in batches. Buffering is used to reduce the 
            overhead (resources, processing power, and time required) of the 
            disk operation. Buffering is more efficient than no buffering, the 
            first option in this question.
            """,
            2, 8));

        questionPool.put(9, new MultiChoiceQuestion(
            """
            pw = new PrintWriter(new FileOutputStream('out.txt', true));
            What happens to the file when pw is used to write to it?
            1) pw appends text to the beginning of out.txt
            2) pw overwrites the old text in out.txt
            3) pw appends text to the end of out.txt
            """,
            """
            'true' is an optional argument used to enable appending. Without it,
            pw will overwrite the existing text in out.txt. Appending occurs at
            the end of the existing text.
            """,
            3, 9));
        
        questionPool.put(10, new MultiChoiceQuestion(
            """
            read()
            What does this method do for a BufferedReader?
            1) Read file line-by-line
            2) Read file character-by-character 
            3) Read file character-by-character through their ASCII code
            """,
            """
            readLine() is used to read text line-by-line into a String. read() 
            is used to read each character into its ASCII code (integer). It 
            returns -1 if it has reached the end of the file.
            """,
            3, 10));
                
        questionPool.put(11, new MultiChoiceQuestion(
            """
            Autonomous AI that sets its own goals and decisions with little to 
            no guidance.
            Which term suits this definition?
            1) Generative AI
            2) Agentic AI
            3) AI agent
            """,
            """
            An Agentic AI is an advanced system that can identify opportunities
            in its environment without much prompting. It is good at adapting to
            new situations and is equipped with appropriate tools to help 
            accomplish tasks. It is sometimes used interchangably with AI agent,
            but AI agents make up the agentic AI system. AI agents have less
            autonomy and are designed for less complex tasks. 
            """,
            2, 11));
        
        questionPool.put(12, new MultiChoiceQuestion(
            """
            In structured prompting's essential elements, what does System 
            Context refer to?
            1) Broadening the AI's context by describing what the output is 
            being applied towards in real life
            2) Giving context on what programming language and framework the AI
            should use
            3) Describing restrictions and limitations for the AI's output
            """,
            """
            System Context is one of the elements used to make the AI's output
            cater more towards your situation, rather than a generic answer. An
            example of system context is informing the AI that they are writing
            code to make a university enrollment system. 
            """,
            1, 12));

        questionPool.put(13, new MultiChoiceQuestion(
            """
            What type of prompting involves providing at least one example to 
            guide the AI what the output should look like?
            1) Few-shot prompting
            2) Problem decomposition
            3) Zero-shot prompting
            """,
            """
            Few-shot prompting is used when AI is expected to follow a strict 
            format, including a consistent coding style. It is often used for 
            large projects.
            """,
            1, 13));

        questionPool.put(14, new MultiChoiceQuestion(
            """
            Which Apache Project is used to manage dependencies easier?
            1) Tika
            2) Maven
            3) Ant
            """,
            """
            Apache Maven Project was created to resolve the difficulty of 
            managing dependencies in Java projects. Dependencies are external
            libraries and frameworks, and they can have their own dependencies.
            """,
            2, 14));

        questionPool.put(15, new MultiChoiceQuestion(
            """
            Which Apache project is best for using Tika?
            1) Tika
            2) Ant
            3) Maven
            """,
            """
            There is no Tika project because it is a toolkit. Tika can be used
            through an Ant or Maven project, but it should be used through Maven
            because it has lots of dependencies.
            """,
            3, 15));

        questionPool.put(16, new MultiChoiceQuestion(
            """
            Which of these diagrams is a structure diagram?
            1) Activity diagram
            2) Use case diagram
            3) Class diagram
            """,
            """
            Structure diagrams describe static aspects of the system (parts of
            the system that do not change during runtime) and its software
            architecture. Class diagram is a structure diagram. The other
            diagrams mentioned are behaviour diagrams.
            """,
            3, 16));
        
        questionPool.put(17, new MultiChoiceQuestion(
            """
            In PlantUML, for class diagrams, what does this arrow represent?
            <|..
            1) Association
            2) Inheritance
            3) Implementation
            """,
            """
            As defined by UML rules, implementation (also known as realisation)
            is represented by a hollow arrowhead with broken line. Use this
            arrow between an interface or abstract class and their subclasses.
            """,
            3, 17));
       
        questionPool.put(18, new MultiChoiceQuestion(
            """
            In PlantUML, for use case diagrams, what does this arrow represent?
            -->
            1) Include
            2) Association
            3) Exclude
            """,
            """
            In PlantUML, association is represented by a solid arrow. The number
            of hyphens in the arrow increases the arrow's length in the diagram.
            Use this arrow to show communication between an actor and usecase.
            """,
            2, 18));
        
        questionPool.put(19, new MultiChoiceQuestion(
            """
            In SOLID design principles, what does the I stand for?
            1) Interface Segregation Principle
            2) Inheritance Separation Principle
            3) Independent Segregation Principle
            """,
            """
            For good design of code, one of the principles to follow is 
            Interface Segregation Principle (ISP). It states that 'A client
            should never be forced to implement an interface that it doesn't
            use or clients shouldn't be forced to depend on methods they don't
            use'.
            """,
            1, 19));
       
        questionPool.put(20, new MultiChoiceQuestion(
            """
            In SOLID design, which principle is this?
            'Subclasses should behave nicely when used in place of their base
            class'
            1) Liskov Substitution Principle
            2) Dependency Inversion Principle
            3) Open/Closed Principle
            """,
            """
            Liskov Substitution Principle (LSP) requires that objects in
            subclasses can replace their parent class without breaking
            the program.
            """,
            1, 20));
        
        questionPool.put(21, new MultiChoiceQuestion(
            """
            In SOLID design principles, what does the S stand for?
            1) Simple Responsibility Principle
            2) Single Responsibility Principle
            3) Single Substitution Principle
            """,
            """
            Single Responsibility Principle (SRP) states that 'A class should
            have only one reason to change'. It means that each class should
            not have to juggle multiple responsibilities because it could lead
            to messier code.
            """,
            2, 21));
       
        questionPool.put(22, new MultiChoiceQuestion(
            """
            What is the library required to use threads?
            1) java.util.Thread
            2) java.io.Thread
            3) java.lang.Thread
            """,
            """
            Threads are the smallest unit of execution in a program. Processes
            (actively running program) run at least one thread. Use 
            java.lang.Thread to use the thread library.
            """,
            3, 22));
        
        questionPool.put(23, new MultiChoiceQuestion(
            """
            Which one of these methods is not part of the Thread library?
            1) start()
            2) yield()
            3) stop()
            """,
            """
            stop() does not exist in the Thread library. A thread stops when it
            finishes running its code or gets blocked (paused). start() is used
            to run a thread. yield() is used to block a thread to let other
            threads execute. The thread scheduler will decide when the yielded
            thread runs again.
            """,
            3, 23));
        
        questionPool.put(24, new MultiChoiceQuestion(
            """
            What is the synchronized keyword used for with threads?
            1) Allow multiple threads to access a class or method at the same
            time.
            2) Block other threads from accessing a class or method while one
            thread is accessing it.
            3) Make multiple threads execute directly after each other in a
            sequence.
            """,
            """
            Multiple threads can already access the same class or method. That
            is why synchronized keyword (synchronisation) is used to block other
            threads out so that race conditions do not occur. Race conditions
            occur when multiple threads try to update information at the same
            time, causing unexpected program results.
            """,
            2, 24));
        
        questionPool.put(25, new MultiChoiceQuestion(
            """
            What is a 'branch' in Git?
            1) A synonym for another contributor's channel in your project
            2) A separate workspace for you to make changes to master code
            3) The location of the current folder is called the 'root' files.
            Within the folder are called 'branches'
            """,
            """
            Branches allow you to edit and test various parts of your code, such
            as new features, or bug fixes, without affecting the main code which
            is in a working state. This is helpful because you have freedom to
            experiment; a key part of programming.
            """,
            2, 25));
        
        questionPool.put(26, new MultiChoiceQuestion(
            """
            What does the command <git status> do in GitCMD?
            1) Check the branch you are currently using, along with if you need
            to commit anything, and your working tree
            2) Check the log of commits so far, lists each commit's description
            and who did it
            3) Check the status of other contributors for your project
            """,
            """
            <git status> gives an overall idea of your current workspace, which
            is your branch, pending commits, and the state of your working tree. 
            """,
            1, 26));
        
        questionPool.put(27, new MultiChoiceQuestion(
            """
            Which command is used to create a new folder/directory in GitCMD?
            1) cd
            2) dir/a
            3) md
            """,
            """
            Md, or 'Make Directory' is the command to make a new directory in
            Git CMD (Git Command Prompt)
            """,
            3, 27));
        

    }
}