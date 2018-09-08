package game;

import java.util.stream.IntStream;
import java.util.stream.Stream;

public class MediumDifficultyRule implements DifficultyRule {

    private int currentRound = 1;
    private int currentQuestion = -1;
    private int[] questions;

    @Override
    public void setQuestions(int numOfQuestions) {
        int[] round1 = RandomUtil.nextArrayInt(3, 11);
        int[] round2 = RandomUtil.nextArrayInt(3, 11);
        int[] round3 = RandomUtil.nextArrayInt(3, 11);
        int[] round4 = RandomUtil.nextArrayInt(3, 11);

        this.questions = Stream.of(round1, round2, round3).flatMapToInt(IntStream::of).toArray();

    }

    @Override
    public String nextQuestion() {

        if (currentQuestion > this.questions.length) {
            throw new Error("Number of questions exceeded");
        }

        currentQuestion++;

        currentRound = currentQuestion <= 2 ? 1 : currentQuestion <= 5 ? 2 : currentQuestion <= 8 ? 3 : 4;

        return currentRound == 1 ? "5 x " + questions[currentQuestion]
                : currentRound == 2 ? "6 x " + questions[currentQuestion]
                        : currentRound == 3 ? "7 x " + questions[currentQuestion]
                                : "8 x " + questions[currentQuestion];

    }

    @Override
    public int getCurrentQuestion() {
        return currentQuestion + 1;
    }

    @Override
    public int getCurrentRound() {
        return currentRound;
    }

    @Override
    public boolean checkAnswer(int answer) {
        int multiplicationNumber = currentRound == 1 ? 5 : currentRound == 2 ? 6 : currentRound == 3 ? 7 : 8;
        return multiplicationNumber * questions[currentQuestion] == answer ? true : false;
    }
}
