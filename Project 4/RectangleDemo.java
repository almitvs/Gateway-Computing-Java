import java.util.Random;
import java.io.PrintWriter;
import java.io.IOException;
import java.awt.Color;

/** Rectangle class demonstration.
    EN.500.112 Gateway Computing: Java, Project 4
*/
public class RectangleDemo {

   /** Sample usage method for the Rectangle class. This creates a random
       4 x 6 grid of rectangles, saving to a file and drawing them.
       @param args not used
       @throws IOException
   */ 
   public static void main(String[] args) throws IOException {
   
      //initial values that we'll use for our 2D array
      int rows = 4;
      int cols = 6;
      Random gen = new Random(10); //seeded random value generator

      //initial values that will help us organize the Rectangles
      //on the drawing canvas
      double width = 1.0 / cols;  //width of grid cell
      double height = 1.0 / rows; //height of grid cell
      double xCenter = width / 2.0;   //x center offset
      double yCenter = height / 2.0;  //y center offset

      //connect to an output file where we'll save String 
      //representations of each Rectangle in our grid
      PrintWriter outfile = new PrintWriter("random.txt");

      //create a 2D array (grid) that can hold Rectangles
      Rectangle[][] grid = new Rectangle[rows][cols];
      
      //write the array dimensions into an ouput file
      outfile.println(rows + " " + cols);
      
      //iterate over the rows and columns of the 2D array, and
      //populate it with randomly-generated Rectangles      
      for (int r = 0; r < rows; r++) {

         for (int c = 0; c < cols; c++) {
         
            //generate random features for a new rectangle
            int red = gen.nextInt(256);
            int green = gen.nextInt(256);
            int blue = gen.nextInt(256);
            double rectWidth = gen.nextDouble() * width;
            double rectHeight = gen.nextDouble() * height;
            boolean filled = gen.nextBoolean();
            
            //create the rectangle using the values above, and store
            //it in the 2D array at the current location
            grid[r][c] = new Rectangle(new Color(red, green, blue), 
               rectWidth, rectHeight, filled,
               (c * width) + xCenter, 1 - ((r * height) + yCenter));
               
            //draw the current rectangle on the screen  
            grid[r][c].draw();
            StdDraw.pause(200);

            //write the current rectangle's info into the output file
            outfile.println(grid[r][c]);  //implicitly calls toString
         }
      }
      
      //end the connection to the output file      
      outfile.flush();
      outfile.close();
   }


}