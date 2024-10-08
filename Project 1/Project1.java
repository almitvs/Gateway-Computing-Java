import java.util.Scanner;

/** Project 1: A program which generates a customizable piece of 
*   Mondrian-inspired art based on user inputs for the proportions 
*   of three of the shapes.
*
*   Author: Aidan Alme 
*   JHED: aalme2
*   Date: 02/04/2023
*/
public class Project1 {

   /** Main method.
   * @param args not used
   */
   public static void main(String[] args) {
   
      Scanner kb = new Scanner(System.in);  // This allows input collection
            
      //Variable declarations for each of the parameters the user inputs
      double blueWidth;
      double blueHeight;
      double topLeftHeight;
      double bottomRightWidth;
   
      // Fixed-value "constants" needed by the program
      final int SIZE = 512;  // can be increased
      StdDraw.setCanvasSize(SIZE, SIZE);
   
      // Prompts for and collects parameters from the user
      /* The domain and range of the canvas span from 0 to 1.0 respectively,
      *  and the draw functions use half the width and height of rectangles as 
      *  input, thus the user input in interger percentages must be converted 
      *  to a double and divided by 200.0.
      */
      
      System.out.print("Enter percent for blue block width: ");
      blueWidth = (double) kb.nextInt() / 200.0;
      
      System.out.print("Enter percent for blue block height: ");
      blueHeight = (double) kb.nextInt() / 200.0;
      
      System.out.print("Enter percent for top left block height: ");
      topLeftHeight = (double) kb.nextInt() / 200.0;
      
      System.out.print("Enter percent for bottom right block width: ");
      bottomRightWidth = (double) kb.nextInt() / 200.0;
   
      // Draws the shapes based on the input
      
      // Draws the blue rectangle in the bottom left
      StdDraw.setPenColor(StdDraw.BLUE);
      StdDraw.filledRectangle(blueWidth, blueHeight, blueWidth, blueHeight);
      
      // Draws the yellow rectangle in the bottom right
      StdDraw.setPenColor(StdDraw.YELLOW);
      StdDraw.filledRectangle(1.0 - bottomRightWidth, blueHeight / 2.0, 
         bottomRightWidth, blueHeight / 2.0);
         
      // Draws the red rectangle in the top right
      StdDraw.setPenColor(StdDraw.RED);
      StdDraw.filledRectangle(0.5 + blueWidth, 0.5 + blueHeight, 
         0.5 - blueWidth, 0.5 - blueHeight);
      
      // Draws the white rectangle in the top right
      StdDraw.setPenColor(StdDraw.WHITE);
      StdDraw.rectangle(blueWidth, 1.0 - topLeftHeight, 
         blueWidth, topLeftHeight);
         
      // Draws the white circle in the bottom left corner of the white rectangle
      StdDraw.filledCircle(2.0 * blueWidth, 1.0 - 2.0 * topLeftHeight, 
         2.0 * topLeftHeight / 3.0);
      
      // Draws the farthest right vertical line
      StdDraw.setPenColor(StdDraw.BLACK);
      StdDraw.setPenRadius(0.02);
      StdDraw.line(1.0 - 2.0 * bottomRightWidth, 0, 
         1.0 - 2.0 * bottomRightWidth, 2.0 * blueHeight);
         
      // Draws the lowest horizontal line
      StdDraw.line(1.0 - 2.0 * bottomRightWidth, blueHeight, 1.0, blueHeight);
      
      // Draws a rectangle to cover the stub of the farthest right vertical line
      StdDraw.setPenColor(StdDraw.RED);
      StdDraw.filledRectangle(1.0 - 2.0 * bottomRightWidth, 
         2.0 * blueHeight + 0.01, 0.02, 0.01);
      
      // Draws the black arc
      StdDraw.setPenRadius(0.01);
      StdDraw.setPenColor(StdDraw.BLACK);
      StdDraw.arc(2.0 * blueWidth, 1.0 - 2.0 * topLeftHeight, 
         2.0 * topLeftHeight / 3.0, 270.0, 90.0);
      
      // Draws the blue arc
      StdDraw.setPenColor(StdDraw.BLUE);
      StdDraw.arc(2.0 * blueWidth, 1.0 - 2.0 * topLeftHeight, 
         2.0 * topLeftHeight / 3.0, 90.0, 180.0);
      
      // Draws the yellow arc
      StdDraw.setPenColor(StdDraw.YELLOW);
      StdDraw.arc(2.0 * blueWidth, 1.0 - 2.0 * topLeftHeight, 
         2.0 * topLeftHeight / 3.0, 180.0, 270.0);
      
      // Draws the highest horizontal line
      StdDraw.setPenColor(StdDraw.BLACK);
      StdDraw.setPenRadius(0.03);
      StdDraw.line(0, 1.0 - 2.0 * topLeftHeight, 
         2.0 * blueWidth, 1.0 - 2.0 * topLeftHeight);
      
      // Draws a rectangle to cover the stub of the highest horizontal line
      StdDraw.setPenColor(StdDraw.WHITE);
      StdDraw.filledRectangle(2.0 * blueWidth + 0.015, 
         1.0 - 2.0 * topLeftHeight, 0.015, 0.015);
      
      // Draws the farthest left vertical line
      StdDraw.setPenRadius(0.01); 
      StdDraw.setPenColor(StdDraw.BLACK);
      StdDraw.line(2.0 * blueWidth, 0, 2.0 * blueWidth, 1.0);
      
      // Draws the middle horizontal line
      StdDraw.line(0, 2.0 * blueHeight, 1.0, 2.0 * blueHeight);
     
   }
}
