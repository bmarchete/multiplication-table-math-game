package game;

public interface DifficultyRule {

    void setQuestions(int questions);
    String nextQuestion();
    int getCurrentQuestion();
    int getCurrentRound();
    boolean checkAnswer(int answer);
}
