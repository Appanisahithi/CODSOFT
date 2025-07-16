import java.util.Scanner;
import java.util.Random;

public class NumberGuessingGame {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();

        final int MIN = 1;
        final int MAX = 100;
        final int MAX_ATTEMPTS = 7;

        int score = 0;
        int round = 1;
        boolean playAgain = true;

        System.out.println("🎯 Welcome to the Number Guessing Game!");

        while (playAgain) {
            int numberToGuess = random.nextInt(MAX - MIN + 1) + MIN;
            int attempts = 0;
            boolean guessedCorrectly = false;

            System.out.println("\nRound " + round + " begins!");
            System.out.println("I'm thinking of a number between " + MIN + " and " + MAX + ".");

            while (attempts < MAX_ATTEMPTS) {
                System.out.print("Attempt " + (attempts + 1) + "/" + MAX_ATTEMPTS + " - Enter your guess: ");
                int guess;

                // Input validation
                if (!scanner.hasNextInt()) {
                    System.out.println("⚠️ Invalid input. Please enter a valid number.");
                    scanner.next(); // consume invalid input
                    continue;
                }

                guess = scanner.nextInt();
                attempts++;

                if (guess < MIN || guess > MAX) {
                    System.out.println("⚠️ Please guess a number between " + MIN + " and " + MAX + ".");
                    continue;
                }

                if (guess < numberToGuess) {
                    System.out.println("🔽 Too low!");
                } else if (guess > numberToGuess) {
                    System.out.println("🔼 Too high!");
                } else {
                    System.out.println("✅ Correct! You guessed it in " + attempts + " attempts.");
                    score += (MAX_ATTEMPTS - attempts + 1); // scoring: fewer attempts = higher score
                    guessedCorrectly = true;
                    break;
                }
            }

            if (!guessedCorrectly) {
                System.out.println("❌ You've run out of attempts. The number was: " + numberToGuess);
            }

            System.out.println("🏆 Current Score: " + score);

            // Play again?
            System.out.print("🔁 Do you want to play another round? (y/n): ");
            String response = scanner.next().toLowerCase();
            playAgain = response.equals("y");

            round++;
        }

        System.out.println("\n🎉 Thanks for playing! Final Score: " + score);
        scanner.close();
    }
}
