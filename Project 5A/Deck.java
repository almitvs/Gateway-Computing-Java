import java.util.Random;

/**
 * A class to represent a standard 52 playing card deck using the card class.
 */

public class Deck {
   
   /**
    * Stores a card array with size 52.
    */
   private Card deck[] = new Card[52];
   
   /**
    * Fills the deck array with 52 cards-
    * One card of each of the 13 ranks for each of the 4 suits.
    * Uses the card class.
    */
   public Deck() {
      for (int i = 0; i < deck.length; i++) {
         deck[i] = new Card(i % 13 + 1, i / 13 + 1);
      }
   }
   
   /**
    * A method to create a string with the names every card
    * with each name on a new line in the order in which they are stored.
    * @Override overides the default toString method for this class
    * @return string the names of all the cards in the deck
    */
   @Override
   public String toString() {
      String string = "";
      for (int i = 0; i < deck.length; i++) {
         string += deck[i].toString() + "\n";
      }
      return string;
   }
   
   /**
    * A method to shuffle the deck of cards and store it in the array.
    */
   public void shuffle() {
      // Creates a new array
      Card copy[] = new Card[deck.length];
      
      // Copies the values of the deck into the copy array
      for (int i = 0; i < deck.length; i++) {
         copy[i] = deck[i];
      }
      
      // Generates a random number to make the shuffling random
      Random randGen = new Random();
      int k = 51;
      for (int i = 0; i < 52; i++) { 
         int j = randGen.nextInt(52);
         // Switches the indexes of the cards in the array
         deck[i] = copy[j]; 
         copy[j] = copy[k];
         k--;
      }
   }
   
   /**
    * A method to sort a shuffled deck back to standard order.
    */
   public void sort() { 
      // Starts at the card in index 0 of the array
      int pos = 0;
      // Goes through every card until they are in the right positions
      while (pos < deck.length) {
         if (pos == 0 || deck[pos].compareTo(deck[pos - 1]) >= 0) {
            pos++;
         }
         else {
            // Re-orders the cards if a higher card is before a lower card
            // Swaps the places of these two cards
            Card dummy = deck[pos];
            deck[pos] = deck[pos - 1];
            deck[pos - 1] = dummy;
            pos--;
         } 
      }
   } 
}