import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Question {
    String questionText;
    List<String> options;
    int correctOptionIndex;

    public Question(String questionText, List<String> options, int correctOptionIndex) {
        this.questionText = questionText;
        this.options = options;
        this.correctOptionIndex = correctOptionIndex;
    }

    public void displayQuestion() {
        System.out.println(questionText);
        for (int i = 0; i < options.size(); i++) {
            System.out.println((i + 1) + ". " + options.get(i));
        }
    }

    public boolean isCorrectAnswer(int userChoice) {
        return userChoice == (correctOptionIndex + 1); 
    }
}

public class onlineQuizApp {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Question> questions = new ArrayList<>();

        questions.add(new Question("Which language is platform independent?",
                List.of("Java", "C++", "Python", "C"), 1)); 
        questions.add(new Question("When was Java released?",
                List.of("1995", "2000", "2010", "1985"), 1)); 
        questions.add(new Question("Which allows multiple inheritance in Java?",
                List.of("interface", "class", "method", "package"), 1)); 

        int score = 0;
        System.out.println("Welcome to the Online Quiz App!");

        for (int i = 0; i < questions.size(); i++) {
            Question q = questions.get(i);
            q.displayQuestion();
            System.out.print("Enter your answer (1-" + q.options.size() + "): ");
            int userAnswer = scanner.nextInt();

            if (q.isCorrectAnswer(userAnswer)) {
                System.out.println("Correct!");
                score++;
            } else {
                System.out.println("Wrong! The correct answer was: " + q.options.get(q.correctOptionIndex));
            }
            System.out.println();
        }

        System.out.println("Quiz Completed!");
        System.out.println("Your score is: " + score + " out of " + questions.size());
        scanner.close();
    }
}

