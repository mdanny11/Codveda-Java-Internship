import java.util.Random;

public class GuessingGame {
    public static final int MIN = 1;
    public static final int MAX = 100;
    public static final int MAX_ATTEMPTS = 7;

    public enum Outcome {
        TOO_HIGH,
        TOO_LOW,
        CORRECT,
        OUT_OF_RANGE
    }

    private final int secret;
    private int attemptsLeft;

    public GuessingGame(Random random) {
        this(random.nextInt(MAX) + MIN, MAX_ATTEMPTS);
    }

    public GuessingGame(int secret, int attempts) {
        if (secret < MIN || secret > MAX) {
            throw new IllegalArgumentException("Secret must be between 1 and 100.");
        }
        if (attempts < 1) {
            throw new IllegalArgumentException("At least one attempt is required.");
        }
        this.secret = secret;
        this.attemptsLeft = attempts;
    }

    public int getAttemptsLeft() {
        return attemptsLeft;
    }

    public int getSecret() {
        return secret;
    }

    public boolean hasAttemptsLeft() {
        return attemptsLeft > 0;
    }

    public Outcome guess(int value) {
        if (value < MIN || value > MAX) {
            return Outcome.OUT_OF_RANGE;
        }
        attemptsLeft--;
        if (value == secret) {
            return Outcome.CORRECT;
        }
        if (value > secret) {
            return Outcome.TOO_HIGH;
        }
        return Outcome.TOO_LOW;
    }
}
