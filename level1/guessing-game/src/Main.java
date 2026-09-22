import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();
        System.out.println("Number Guessing Game");
        System.out.println("I am thinking of a number from 1 to 100.");
        boolean playing = true;
        while (playing) {
            playRound(scanner, new GuessingGame(random));
            playing = askToPlayAgain(scanner);
        }
        System.out.println("Goodbye.");
        scanner.close();
    }

    private static void playRound(Scanner scanner, GuessingGame game) {
        System.out.println("You have " + game.getAttemptsLeft() + " attempts.");
        while (game.hasAttemptsLeft()) {
            System.out.print("Your guess: ");
            System.out.flush();
            if (!scanner.hasNext()) {
                System.out.println();
                System.out.println("Input ended.");
                return;
            }
            if (!scanner.hasNextInt()) {
                System.out.println("Enter a whole number.");
                scanner.next();
                continue;
            }
            int guess = scanner.nextInt();
            switch (game.guess(guess)) {
                case CORRECT -> {
                    System.out.println("Correct. The number was " + game.getSecret() + ".");
                    return;
                }
                case TOO_HIGH ->
                    System.out.println("Too high. Attempts left: " + game.getAttemptsLeft());
                case TOO_LOW ->
                    System.out.println("Too low. Attempts left: " + game.getAttemptsLeft());
                case OUT_OF_RANGE ->
                    System.out.println("Enter a number from 1 to 100. That guess was not counted.");
            }
        }
        System.out.println("Out of attempts. The number was " + game.getSecret() + ".");
    }

    private static boolean askToPlayAgain(Scanner scanner) {
        System.out.print("Play again? (y/n): ");
        System.out.flush();
        if (!scanner.hasNext()) {
            System.out.println();
            return false;
        }
        String answer = scanner.next().trim();
        return answer.equalsIgnoreCase("y") || answer.equalsIgnoreCase("yes");
    }
}
