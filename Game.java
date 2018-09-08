
import game.HardDifficultyRule;
import game.EasyDifficultyRule;
import game.DifficultyRule;
import game.MediumDifficultyRule;
import java.util.Scanner;

public class Game {

    private String name;
    private int age;
    private Difficulty difficulty;
    private DifficultyRule difficultyRule;
    private int points;
    private int attempts;
    private final int NUM_QUESTIONS = 12;

    private Scanner scanner = new Scanner(System.in);

    public void start() {

        // set initial config such as name, age and difficulty
        this.setInitialConfig();

        // runs the game rules
        this.play();

    }

    private void play() {

        while (true) {

            int currentQuestion = 0;
            int chances = 4;
            String nextQuestion = "";
            boolean isCorrect = true;
            this.attempts = 0;

            while (currentQuestion < NUM_QUESTIONS) {

                // set the end of the game when player lose
                if (chances <= 0) {
                    this.showLostReport();
                    return;
                }

                // increases one question
                if (isCorrect) {
                    attempts++;
                    nextQuestion = difficultyRule.nextQuestion();
                }

                int answer = this.getAnswer(nextQuestion, chances);

                // verify answer
                if (difficultyRule.checkAnswer(answer)) {
                    points += 10;
                    currentQuestion++;
                    isCorrect = true;
                } else {
                    chances--;
                    isCorrect = false;
                }

            }

            this.showWinReport();

            if (this.difficulty == Difficulty.HARD || !this.moveToNextLevel()) {
                return;
            }

        }

    }

    private boolean moveToNextLevel() {
        System.out.println("Do you want to proceed to the next level? (Y or N)");
        char response = scanner.next().toLowerCase().charAt(0);

        if (response != 'y') {
            return false;
        }

        if (this.difficulty == Difficulty.EASY) {
            this.difficulty = Difficulty.MEDIUM;
        } else if (this.difficulty == Difficulty.MEDIUM) {
            this.difficulty = Difficulty.HARD;
        }

        this.configDifficultyRule();

        return true;
    }

    private void showWinReport() {
        System.out.println("--------------------");
        System.out.println("=== Win Report ===");
        System.out.println("Name : " + this.name);
        System.out.println("Age : " + this.age);
        System.out.println("Score : " + this.points);
        System.out.println("Level : " + this.difficulty);
        System.out.println("Total attempts : " + this.attempts);
        System.out.println("--------------------");

    }

    private void showLostReport() {

        System.out.println("--------------------");
        System.out.println("=== Lost Report ===");
        System.out.println("Name : " + this.name);
        System.out.println("Age : " + this.age);
        System.out.println("Score : " + this.points);
        System.out.println("Level : " + this.difficulty);
        System.out.println("Total attempts : " + this.attempts);
        System.out.println("Last Question : " + difficultyRule.getCurrentQuestion());
        System.out.println("Last Round : " + difficultyRule.getCurrentRound());
        System.out.println("--------------------");

    }

    private int getAnswer(String nextQuestion, int attempts) {
        System.out.print("===");
        System.out.print(" Question: " + difficultyRule.getCurrentQuestion());
        System.out.print(" | Round: " + difficultyRule.getCurrentRound());
        System.out.print(" | Attempts remaining: " + attempts);
        System.out.print(" | Points: " + this.points);
        System.out.println(" ===");

        System.out.println(this.name + ", what is the answer for " + nextQuestion);

        int answer = -1;
        try {
            answer = scanner.nextInt();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return answer;
    }

    private void configDifficultyRule() {
        switch (this.difficulty) {
            case EASY:
                difficultyRule = new EasyDifficultyRule();
                break;
            case MEDIUM:
                difficultyRule = new MediumDifficultyRule();
                break;
            case HARD:
                difficultyRule = new HardDifficultyRule();
                break;
        }

        difficultyRule.setQuestions(NUM_QUESTIONS);

    }

    private void setInitialConfig() {

        System.out.println("=== The multiplication table math game ===");
        try {
            System.out.println("Please type your name to begin");
            this.name = scanner.next();

            System.out.println("Please type your age");
            this.age = scanner.nextInt();

            System.out.println("Please choose the difficulty level");
            this.difficulty = this.validateDifficulty();

            // config difficulty level
            this.configDifficultyRule();

        } catch (Exception e) {
            System.out.printf(e.getMessage());
        }
    }

    private Difficulty validateDifficulty() throws Exception {
        char difficulty = scanner.next().toLowerCase().charAt(0);

        if (!(difficulty == 'e' || difficulty == 'm' || difficulty == 'h')) {
            throw new Exception("This option of difficulty is invalid");
        }

        return difficulty == 'e' ? Difficulty.EASY
                : difficulty == 'm' ? Difficulty.MEDIUM
                        : Difficulty.HARD;

    }

}
