/**
 * Unit tests for the card class.
 */
public class TestCard {
 
   public static void main(String[] args) {
      
      // Creates new cards
      Card kingofClubs = new Card(13, 1);
      Card aceofDiamonds = new Card(1, 2);
      Card queenofClubs = new Card(12, 1);
      
      // Tests valid input in the toString method
      assertMatches("King of Clubs", kingofClubs.toString(), "Name is correctly assigned to string.");
      
      // Tests the rank getter method
      assertEquals(13, kingofClubs.getRank(), "Rank is correctly assigned.");
      
      // Tests the suit getter method
      assertEquals(1, kingofClubs.getSuit(), "Suit is correctly assigned.");
      
      // Tests invalid input in the toString Method and the getters
      Card invalid1 = new Card(0, 0);
      assertEquals(0, invalid1.getRank(), "Rank is correctly assigned.");
      assertMatches("Invalid Card", invalid1.toString(), "Name is correctly assigned to string.");
      
      Card invalid2 = new Card(1, 15);
      assertEquals(0, invalid2.getSuit(), "Suit is correctly assigned.");
      assertMatches("Invalid Card", invalid2.toString(), "Name is correctly assigned to string.");
      
      Card invalid3 = new Card(29, 1);
      assertMatches("Invalid Card", invalid3.toString(), "Name is correctly assigned to string.");
      
      // Tests the equals method
      assertTrue(false, kingofClubs.equals(invalid1), "The King of Clubs is not equal to an invalid card.");
      assertTrue(true, kingofClubs.equals(kingofClubs), "The King of Clubs is equal to itself.");
      assertTrue(false, kingofClubs.equals(aceofDiamonds), "The King of Clubs is not equal to the Ace of Diamonds.");
      
      // Tests the compareTo method
      assertEquals(-1, kingofClubs.compareTo(aceofDiamonds), "The King of Clubs is one less than the Ace of Diamonds.");
      assertEquals(1, kingofClubs.compareTo(queenofClubs), "The King of Clubs is one more than the Queen of Clubs.");
      assertEquals(0, kingofClubs.compareTo(kingofClubs), "The King of Clubs equal to itself.");
   }
   
   /**
    * A helper method to asserts that two integers are equal.
    * @param exp expected integer value.
    * @param act actual integer value.
    * @param dsc description of the test.
    */
   public static void assertEquals(int exp, int act, String dsc) {
      if (exp == act) {
         System.out.println("PASS: " + dsc);
      } else {
         System.out.println("FAIL: " + dsc);
      }
   }
   
   /**
    * A helper method to asserts that two booleans are equal.
    * @param exp expected boolean value.
    * @param act actual boolean value.
    * @param dsc description of the test.
    */
   public static void assertTrue(boolean exp, boolean act, String dsc) {
      if (exp == act) {
         System.out.println("PASS: " + dsc);
      } else {
         System.out.println("FAIL: " + dsc);
      }
   }
   
   /**
    * A helper method to asserts that two strings are equal.
    * @param exp expected string value.
    * @param act actual string value.
    * @param dsc description of the test.
    */
   public static void assertMatches(String exp, String act, String dsc) {
      if (exp.equals(act)) {
         System.out.println("PASS: " + dsc);
      } else {
         System.out.println("FAIL: " + dsc);
      }
   }
 
 
}