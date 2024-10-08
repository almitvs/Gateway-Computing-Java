import java.util.Scanner;
import java.util.Random;

/**
 * Project 2: Wordle
 *
 * This project has you create a text based version of Wordle
 * (https://www.nytimes.com/games/wordle/index.html). Wordle is a word guessing
 * game in which you have 6 tries to guess a 5-letter word. You are told whether
 * each letter of your guess is in the word and in the right position, in the
 * word but in the wrong position, or not in the word at all.
 *
 * Some key differences in our version are:
 *
 * - Text menu based with no grid. Players have to scroll up to see their
 * previous guesses.
 *
 * - Support for 4, 5, or 6 letter words
 *
 * - We don't check for whether a guess is a valid word or not. Players can
 * guess anything they want (of the correct length).
 *
 * Fun facts: The original Wordle was developed by Josh Wardle. Wardle's wife
 * chose the official word list for the game.
 *
 * 500.112 Gateway Computing: Java Spring 2023
 * Author: Aidan Alme
 * JHED: aalme2
 * Date: 02/13/2023
 */
public class Wordle {
   /**
    * The number of guesses the player starts with.
    */
   static final int MAX_GUESSES = 6;
   
   /**
    * The maximum number of hints available to the player.
    */
   static final int MAX_HINTS = 2;

   /**
    * The entry point for the program.
    *
    * @param args commandline args
    */
   public static void main(String[] args) {
      printIntro();
      
      //Initialize the game state.
      Random random = new Random();
      String word = newWord(random);
      String[] guesses = new String[MAX_GUESSES];
      for (int i = 0; i < guesses.length; i++) {
         guesses[i] = "";
      }
      char lastHint = '\0';
      int numGuesses = 0;
      int numHints = 0;
      Scanner scanner = new Scanner(System.in);
      boolean gameOver = false;
   
      //Run the game loop.
      while (!gameOver) {
         String command = printMenu(guesses, word, scanner);
         if ("h".equalsIgnoreCase(command)) {
            //Check whether the player has any hints left.
            if (numHints < MAX_HINTS) {
               lastHint = giveHint(word, lastHint, random);
               numHints++;
               printNumHintsRemaining(numHints);
            } else {
               System.out.println("Sorry, you're out of hints!");
            }
         } else if ("g".equalsIgnoreCase(command)) {
            printNumGuessesRemaining(numGuesses);
            String guess = getGuess(scanner);
            //Ensure the player's guess was valid and should count 
            //against the number of guesses taken.
            if (validateGuess(guess, word.length())) {
               String result = processGuess(word, guess);
               guesses[numGuesses++] = result;
               gameOver = checkGameOver(guess, word, numGuesses);
            }
         } else if ("e".equalsIgnoreCase(command)) {
            gameOver = true;
         } else {
            System.out.println("Invalid option! Try again!");
         }
      }
   }
   
   /**
    * Prints the introduction.
    */
   public static void printIntro() {
      System.out.println("Welcome to Wordle! Menu options:");
      System.out.println("g/G: Make a guess");
      System.out.println("h/H: Get a hint");
      System.out.println("e/E: Exit");
   }
      
   /**
    * Prints the current game state and menu options, and prompts the
    * player for their choice.
    * 
    * @param guesses An array of the player's guesses.
    * @param word The correct word.
    * @param scanner a Scanner used to prompt the player for a menu choice.
    * @return The player's menu choice.
    */
   public static String printMenu(String[] guesses, String word,
                                  Scanner scanner) {
      //Iterate through all of the user's guess "slots" and print them.
      for (int i = 0; i < guesses.length; i++) {
         printGuess(guesses[i], word.length());
      }
      System.out.print("Enter a choice (G/H/E): ");
      return scanner.nextLine();
   }
   
      
   /**
    * Prints:
    * 
    * "Enter a guess: " 
    * 
    * (NOT followed by a newline) and reads the guess from a scanner.
    *
    * @param scanner The Scanner used to read the player's guess.
    * @return The player's guess.
    * 
    * [2 points]
    */
   public static String getGuess(Scanner scanner) {
      System.out.print("Enter a guess: ");
      String guess = scanner.nextLine();
      return guess;
   }
   
   /**
    * Chooses a random word of length 4, 5, or 6 using 
    * WordProvider.getWord() and the provided random number generator.
    *
    * @param random A random number generator.
    * @return The randomly chosen word.
    *
    * [3 points]
    */
   public static String newWord(Random random) {
      String word = WordProvider.getWord(random.nextInt(3) + 4);
      return word;
   }

   /**
   * Prints a player's guess, or, if the guess is the empty string, a series of
   * underscores (one per character in the correct word). All printed
   * characters are separated by a space. The very last thing printed should
   * be a newline.
   *
   * @param guess A player's guess or the empty string.
   * @param wordLength the number of characters in the correct word.
   * 
   * [6 points]
   */
   public static void printGuess(String guess, int wordLength) {
      int letter = 0;
      String blank = "";
      
      // Prints underscores if a guess has not been made.
      if ("".equals(guess)) {
         
         while (letter < wordLength) {
            blank = blank + "_";
            letter++;
         
            if (letter < wordLength) {
               blank = blank + " ";
            }
         }
         
         System.out.println(blank);
      }
      
      // Prints the player's guesses with correct formatting
      else {
      
         while (letter < wordLength) {
            blank = blank + guess.charAt(letter);
            letter++;
         
            if (letter < wordLength) {
               blank = blank + " ";
            }
         }
         
         System.out.println(blank);
      }
   }
   
   /**
    * Prints the number of guesses remaining using correct plurality. 
    * For example, if the player has 1 guesses remaining, this function 
    * prints:
    * 
    * "You have 1 guess remaining." 
    * 
    * If the player has 0 guesses remaining, this function prints:
    * 
    * "You have 0 guesses remaining."
    *
    * @param numGuesses the number of guesses that the player has made
    * so far.
    *
    * [4 points]
    */
   public static void printNumGuessesRemaining(int numGuesses) {
      
      // Prints the singular "guess" for 1 guess remaining
      if (6 - numGuesses == 1) {
         System.out.println("You have 1 guess remaining.");
      }
      
      // Prints the plural "guesses" for all other values
      else {
         System.out.println("You have " + (6 - numGuesses) + 
            " guesses remaining.");
      }
   }
   
   /**
    * Prints the number of hints remaining using correct plurality. 
    * For example, if the player has 1 hint remaining, this function 
    * prints:
    * 
    * "You have 1 hint remaining." 
    * 
    * If the player has 0 hints remaining, this function prints:
    * 
    * "You have 0 hints remaining."
    *
    * @param numHints the number of hints that the player has received 
    * so far.
    *
    * [4 points]
    */
   public static void printNumHintsRemaining(int numHints) {
      if (numHints == 1) {
         System.out.println("You have 1 hint remaining.");
      }
      
      else if (numHints == 2) {
         System.out.println("You have 0 hints remaining.");
      }
   }

  /**
    * Prints 
    * 
    * "Hint: the word contains the letter: _." 
    * 
    * followed by a newline, where _ is a randomly chosen letter in the word 
    * parameter. IMPORTANT: the hint that you provide must not be the same
    * as the previous hint given (if there is one).
    *
    * @param word The word to give a hint for.
    * @param lastHint The previous hint given to the player or '\0' (the null 
    *   character literal) if no hint was given yet.
    * @param random A random number generator.
    * @return The hint character.
    *
    * [5 points]
    */
   public static char giveHint(String word, char lastHint, Random random) {
      char hint = word.charAt(random.nextInt(word.length()));
      
      // Eliminates duplicate hints
      while (hint == lastHint) {
         hint = word.charAt(random.nextInt(word.length()));
      }
      
      System.out.println("Hint: the word contains the letter: " + hint + ".");
      
      return hint;
   }

   /**
    * Checks the player's guess for validity. We define a valid guess as one 
    * that is the correct length and contains only lower case letters and 
    * upper case  letters. If the guess length is incorrect, this function
    * prints:
    * 
    * "You must enter a guess of length _." 
    * 
    * Where _ is the correct length. If the guess contains anything other 
    * than a lower or upper case  letter, this function prints:
    *
    * "Your guess must only contain upper case letters and lower case letters."
    *  
    * This function prints BOTH messages when appropriate. Each sentence 
    * printed by this funtion is terminated by a newline.
    *
    * @param guess  The guess that the player has entered.
    * @param correctLength The length of the correct word.
    * @return true if the guess is of the correct length and contains only valid
    * characters, otherwise false.
    *
    * [8 points]
    */
   public static boolean validateGuess(String guess, int correctLength) {
      boolean guessValidity = true;
      boolean lengthValidity = true;
      boolean characterValidity = true;
      
      // Checks if the guess is the correct length.
      if (guess.length() != correctLength) {
         lengthValidity = false;
         System.out.println("You must enter a guess of length " 
            + correctLength + ".");
      }
      
      // Checks that each character is a letter.
      int letter = 0;
      while (letter < guess.length()) {
         if (Character.isLetter(guess.charAt(letter))) {
            letter++;
         }
         else {
            guessValidity = false;
            letter = correctLength;
            System.out.println("Your guess must only contain " + 
               "upper case letters and lower case letters.");
         }
      }
      
      // Validates the guess if it is all letters and the length is correct.
      if (lengthValidity && guessValidity) {
         guessValidity = true;
      }
      
      else {
         guessValidity = false;
      }
      
      return guessValidity;
   }

   /**
    * Checks the player's guess against the current word (capitalization is
    * IGNORED for this comparison). Then returns a string corresponding to 
    * the player's guess. A ? indicates a letter that isn't in the word at
    * all. A lower case letter indicates that the letter is in the word but
    * not in the correct position. An upper case letter indicates a correct
    * letter in the correct position. Every letter in the returned string 
    * must be separated by a space. Example:
    *
    * SPLINE (the correct word)
    *
    * SPEARS (the player's guess)
    *
    * SPe??s (the resulting String returned by this function)
    *
    * @param word The correct word that the player is trying to guess.
    * @param guess The guess that a player has entered.
    * @return The formatted guess result (see above).
    *
    * [12 points]
    */
   public static String processGuess(String word, String guess) {
      int letterPlace = 0;
      String guessLetter = "";
      String wordLetter = "";
      String result = "";
      
      // Checks each letter in the guess against those of the word
      // and creates a new string with the formatted guess result.
      while (letterPlace < word.length()) {
         guessLetter = guessLetter + guess.charAt(letterPlace);
         wordLetter = wordLetter + word.charAt(letterPlace);
         
         // Capitalizes the guess letter within the guess result string 
         // if it matches the letter of the word in the correct place.
         if (guessLetter.equalsIgnoreCase(wordLetter)) {
            result = result + Character.toUpperCase(word.charAt(letterPlace));
            letterPlace++;
            guessLetter = "";
            wordLetter = "";
         }
         
         // Adds the guess letter in lowercase to the guess result string
         // if that letter is found in the word but not in the same place.
         else if (word.indexOf(Character.toUpperCase(
            guess.charAt(letterPlace))) >= 0) {
            result = result + Character.toLowerCase(guess.charAt(letterPlace));
            letterPlace++;
            guessLetter = "";
            wordLetter = "";
         }
         
         // Adds '?' to the guess result string if the guess letter
         // is not found in the word at all.
         else {
            result = result + '?';
            letterPlace++;
            guessLetter = "";
            wordLetter = "";
         }
         
      }
   
      return result;
   }
   
   /**
    * Checks whether the game is over due to the guess matching the word or 
    * the player being out of guesses. Prints either 
    * 
    * "Congrats, you guessed the word!"
    * 
    * or 
    * 
    * "Sorry, you ran out of guesses!" 
    * 
    * as appropriate.
    * 
    * @param guess The player's most recent guess.
    * @param word The correct word.
    * @param numGuesses The number of guesses the player has tried so far.
    * @return true if the game should end (due to a win or loss), otherwise
    * false.
    * 
    * [4 points]
    */
   public static boolean checkGameOver(String guess, String word, 
                                      int numGuesses) {
      boolean gameOver = false;
     
      if (guess.equalsIgnoreCase(word)) {
         System.out.println("Congrats, you guessed the word!");
         gameOver = true;
      }
      
      else if (numGuesses == 6) {
         System.out.println("Sorry, you ran out of guesses!");
         gameOver = true;
      }
     
      return gameOver;
   }
}
