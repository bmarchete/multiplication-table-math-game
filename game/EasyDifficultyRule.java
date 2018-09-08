package game;

import java.util.stream.IntStream;
import java.util.stream.Stream;

public class EasyDifficultyRule implements DifficultyRule {

    private int currentRound = 1;
    private int currentQuestion = -1;
    private int[] questions;

    @Override
    public void setQuestions(int questions) {

        int[] round1 = RandomUtil.nextArrayInt(4, 11);
        int[] round2 = RandomUtil.nextArrayInt(4, 11);
        int[] round3 = RandomUtil.nextArrayInt(4, 11);

        this.questions = Stream.of(round1, round2, round3).flatMapToInt(IntStream::of).toArray();

    }

    @Override
    public String nextQuestion() {

        if (currentQuestion > this.questions.length) {
            throw new Error("Number of questions exceeded");
        }

        currentQuestion++;

        currentRound = currentQuestion <= 3 ? 1 : currentQuestion <= 7 ? 2 : 3;

        return currentRound == 1 ? "2 x " + questions[currentQuestion]
                : currentRound == 2 ? "3 x " + questions[currentQuestion]
                        : "4 x " + questions[currentQuestion];

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
        int multiplicationNumber = currentRound == 1 ? 2 : currentRound == 2 ? 3 : 4;
        return multiplicationNumber * questions[currentQuestion] == answer ? true : false;
    }
}
