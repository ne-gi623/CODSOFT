import java.util.Random;
import java.util.Scanner;

public class NumberGame {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        Random random = new Random();

        int totalScore = 0;
        int roundsWon = 0;

        boolean playAgain = true;

        System.out.println(" WELCOME TO THE NUMBER GUESSING GAME ");

        while (playAgain) {

            int numberToGuess = random.nextInt(100) + 1; 
            int maxAttempts = 5;
            int attempts = 0;
            boolean guessedCorrectly = false;

            System.out.println("\nI have generated a number between 1 and 100.");
            System.out.println("You have " + maxAttempts + " attempts. Start guessing!");

            while (attempts < maxAttempts) {
                System.out.print("Enter your guess: ");
                int guess = scanner.nextInt();
                attempts++;

                if (guess == numberToGuess) {
                    System.out.println(" Correct! You guessed the number.");
                    guessedCorrectly = true;

                    int score = (maxAttempts - attempts + 1) * 10;
                    totalScore += score;
                    roundsWon++;

                    System.out.println("Score this round: " + score);
                    break;
                } else if (guess < numberToGuess) {
                    System.out.println(" Too low!");
                } else {
                    System.out.println(" Too high!");
                }
            }

            if (!guessedCorrectly) {
                System.out.println(" You ran out of attempts!");
                System.out.println("The correct number was: " + numberToGuess);
            }

            System.out.print("\nDo you want to play again? (yes/no): ");
            scanner.nextLine(); 
            String choice = scanner.nextLine();

            if (!choice.equalsIgnoreCase("yes")) {
                playAgain = false;
            }
        }

        System.out.println("\n GAME OVER ");
        System.out.println("Rounds Won: " + roundsWon);
        System.out.println("Total Score: " + totalScore);

        scanner.close();
    }
}
