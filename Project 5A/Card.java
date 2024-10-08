/**
 * A class to represent a playing card in a standard 52 card deck.
 */

public class Card {
   
   /**
    * Stores the suit of the playing card.
    * There are 4 suits, represented as numbers:
    * Clubs    = 1
    * Diamonds = 2
    * Hearts   = 3
    * Spades   = 4
    */
   private int suit;
   
   /** 
    * Stores the rank of the playing card.
    * The ranks are ace, the numbers 2-10, jack, queen, and king.
    * Ace   = 1
    * 2-10  = 2-10
    * Jack  = 11
    * Queen = 12
    * King  = 13
    */
   private int rank;
   
   /**
    * Sets the card's rank and suit to the given values.
    * @param rank the rank of the card
    * @param suit the suit of the card
    */
   public Card(int rank, int suit) {
      // Initializes suit and rank to 0
      this.suit = 0;
      this.rank = 0;
      
      // Sets suit and rank if the input is valid
      if (suit < 5 && suit > 0 && rank < 14 && rank > 0) {
         this.suit = suit;
         this.rank = rank;
      }
   }
   
   /** 
    * A method to get the the card's suit.
    * @return suit the card's suit
    */
   public int getSuit() {
      return suit;
   }
   
   /** 
    * A method to get the the card's rank.
    * @return rank the card's rank
    */
   public int getRank() {
      return rank;
   }
   
   /**
    * A method to create a string with the name of the card 
    * based on its rank and suit.
    * @Override overides the default toString method for this class
    * @return string the name of the card
    */
   @Override
   public String toString() {
      
      // Creates a new empty string
      String string = "";
      
      // Adds the rank of the card to the string
      // Uses special cases for strings whose names are not their number
      switch (rank) {
         case 0:
            string += "";
            break;
         
         case 1:
            string += "Ace";
            break;
            
         case 11:
            string += "Jack";
            break;
            
         case 12:
            string += "Queen";
            break;
            
         case 13:
            string += "King";
            break;
         
         // The default case for ranks 2-10   
         default:
            string += rank;
            break;
            
      }
      
      // Adds the suit of the card to the string
      // There is a case for each number corresponding to a suit
      // Invalid cards are printed as "Invalid Card"
      switch (suit) {
         case 1:
            string += " of Clubs";
            break;
            
         case 2:
            string += " of Diamonds";
            break;
            
         case 3:
            string += " of Hearts";
            break;
            
         case 4:
            string += " of Spades";
            break;
            
         default:
            string += "Invalid Card";
            break;
      }
      return string;
   }
   
   /**
    * A method to compare the values of 2 cards.
    * Uses a hierarchy of suits corresponding to their numerical value.
    * For example, a King of Spades is the highest card
    * and the Ace of Clubs is the lowest card.
    * @Override overides the default equals method for this class
    */
   @Override 
   public boolean equals(Object other) {
      return ((Card) other).getSuit() == suit && 
                ((Card) other).getRank() == rank;
   }
   
   /**
    * Compare this Card with the specified otherCard for order.
    * @param otherCard the other Card object to be compared.
    * @return a negative integer, zero, or a positive integer as     
    * this object is less than, equal to, or greater than the otherCard.
    */ 
   public int compareTo(Card otherCard) { 
      if ((suit - 1) * 13 + rank != (otherCard.getSuit() - 1) 
                                 * 13 + otherCard.getRank()) {
         return (suit - 1) * 13 + rank - ((otherCard.getSuit() - 1) 
                                        * 13 + otherCard.getRank());
      }
      else {
         return 0;
      }
   }
}