import java.util.Scanner; 
import java.io.PrintWriter; 
import java.io.IOException; 
import java.io.FileInputStream;
import java.io.FileOutputStream; 
import java.awt.Color;

/**
 * Rectangle of Rectangles: Project 4
 *
 * This program has 3 functions:
 * 1) Draw a grid of squares in a checkerboard pattern, with the number of
 *    squares per row and their color input by the user. The information
 *    need to draw the rectangles on the grid is stored to a text file. The
 *    grid is drawn with a delay so that the squares are drawn from left to
 *    right from top to bottom.
 * 2) Draw a grid of rectangles based on information from a text file input
 *    by the user, with a delay so that the squares appear to be drawn in a
 *    snake pattern from top to bottom and left to right.
 * 3) Draw a grind of rectangles based on information for a text file input
 *    by the user, with delay so that the squares appear to be drawn from
 *    left to right and top to bottom by diagonals, starting with the main
 *    diagonal and spreading out.
 *
 * Gateway Computing: JAVA
 * Johns Hopkins University
 * Spring 2023
 * Author: Aidan Alme
 * JHED: aalme2
 * Date: 03/10/2023
 */

public class Project4 {
   
   /**
    * The entry point for the progam.
    * @param args This program does not take commandline arguments.
    * @throws IOException
    */
   public static void main(String[] args) throws IOException {
      
      // Creates a scanner to gather user input
      Scanner scnr = new Scanner(System.in);
      
      // Prompts the user for the number of squares per row of the checkerboard
      System.out.print("Enter Checkerboard size: ");
      int size = scnr.nextInt();
      
      // Prompts the user for the RGB values of the checkerboard squares
      System.out.print("Enter RGB values, each [0,255]: ");
      int red = scnr.nextInt();
      int green = scnr.nextInt();
      int blue = scnr.nextInt();
      
      // Creates an array to store the rectangle information for the grid
      Rectangle[][] checkerGrid = createCheckerboardGrid(size, red, green, 
                                                         blue);
                                                         
      // Outputs the array to a text file
      String filename = "checkerboard" + size + ".txt";
      createFileFromGrid(checkerGrid, filename, size);
      
      // Draws the grid in a row patter
      displayRowPattern(checkerGrid, size, size);
      
      // Prompts the user for a file
      System.out.print("Enter Snake input filename: ");
      String snakeFile = scnr.next();
      
      // Inputs the information from the file into an array
      Rectangle[][] snakeGrid = createGridFromFile(snakeFile);
      
      // Draws the grid in a snake pattern
      displaySnakePattern(snakeGrid, snakeGrid.length, snakeGrid[0].length);
      
      // Prompts the user for a file
      System.out.print("Enter Diagonals input filename: ");
      String diagonalFile = scnr.next();
      
      // Inputs the information from the file into an array
      Rectangle[][] diagonalGrid = createGridFromFile(diagonalFile);
      
      // Draws the grid in a diagonal pattern
      displayDiagonalPattern(diagonalGrid, diagonalGrid.length, 
                             diagonalGrid[0].length);
   } 
   
   /**
    * Creates an array of rectangles in a checkerboard format based on the 
    * size input by the user.
    *
    * @param size The number of squares per row in the grid
    * @param red The reds value
    * @param green The green value
    * @param blue The blue value
    * @return grid A rectangle array for the checkerboard grid
    */
   public static Rectangle[][] createCheckerboardGrid(int size, int red, 
                                                      int green, int blue) {    
      
      // The position and size of the fist square in the top left corner
      double width = 1.0 / size;
      double height = 1.0 / size;
      double centerX = width / 2.0;
      double centerY = 1.0 - height / 2.0;
      
      // An array to store the information for the rectangles
      Rectangle[][] grid = new Rectangle[size][size];
      
      // Inputs the information into the arrays for each rectangle moving left
      // to right, top to bottom
      for (int i = 0; i < size; i++) {
         for (int j = 0; j < size; j++) {
         
            // The first rectangle is white and every other one is colored 
            if ((i + j) % 2 == 1) {
               grid[i][j] = new Rectangle(new Color(red, green, blue), width,
                                          height, true, centerX + j * width, 
                                          centerY - i * height);
            }
            else {
               grid[i][j] = new Rectangle(new Color(255, 255, 255), width, 
                                          height, true, centerX + j * width, 
                                          centerY - i * height);
            }
         }
      }
      return grid; 
   }
   
   /**
    * Creates a text file with the information from the checkerboard rectangle
    * array created based on input for the user.
    *
    * @param grid The rectangle array to be output on the text file
    * @param filename The name for the text file
    * @param size The number of rows and columns
    * @throws IOException
    */
   public static void createFileFromGrid(Rectangle[][] grid, String filename,
                                         int size) throws IOException {
      
      // Creates an output stream for the file
      FileOutputStream fileOS = new FileOutputStream(filename);
      PrintWriter outFS = new PrintWriter(fileOS);
      
      // Prints the number of rows and columns
      outFS.println(size + " " + size);
      
      // Prints the array information onto the file
      for (int i = 0; i < size; i++) {
         for (int j = 0; j < size; j++) {
            outFS.println(grid[i][j]);
         }
      }
      outFS.flush();
      outFS.close();
   }
   
   /**
    * Creates a rectangle array from information from a text fgile input
    * by the user.
    *
    * @param filename The name for the text file
    * @return grid A rectangle array based on the information from the text
    * @throws IOException
    */
   public static Rectangle[][] createGridFromFile(String filename) 
                                                  throws IOException {
      
      // Creates an input stream from the file
      FileInputStream inFS = new FileInputStream(filename);
      Scanner fileScan = new Scanner(inFS);
      
      // Reads the number of rows and columns
      int rows = fileScan.nextInt();
      int columns = fileScan.nextInt();
      fileScan.nextLine();
      
      // Creates an array for the information
      Rectangle[][] grid = new Rectangle[rows][columns];
      
      // Inputs the information from the file into the array
      for (int i = 0; i < rows; i++) {
         for (int j = 0; j < columns; j++) {
            if (fileScan.hasNext()) {
               grid[i][j] = new Rectangle(fileScan.nextLine());
            }
         }
      }
      inFS.close();
      return grid;
   }
   
   /**
    * Draws the checkerboard grid in a row pattern.
    *
    * @param grid The rectangle array for the checkerboard
    * @param rows The number of rows in the array
    * @param columns The number of columns in the array
    */
   public static void displayRowPattern(Rectangle[][] grid, int rows, 
                                        int columns) {
      StdDraw.clear(StdDraw.LIGHT_GRAY);
      for (int i = 0; i < rows; i++) {
         for (int j = 0; j < columns; j++) {
            grid[i][j].draw();
            StdDraw.pause(200);
         }
      }
   }
   
   /**
    * Draws the a grid inpout by the user in a snake pattern.
    *
    * @param grid The rectangle array for the checkerboard
    * @param rows The number of rows in the array
    * @param columns The number of columns in the array
    */
   public static void displaySnakePattern(Rectangle[][] grid, int rows, 
                                          int columns) {
      StdDraw.clear(StdDraw.LIGHT_GRAY);
      for (int j = 0; j < columns; j++) {
      
         // Moves up for odd columns
         if (j % 2 == 1) {
            for (int i = rows - 1; i >= 0; i--) {
               grid[i][j].draw();
               StdDraw.pause(200);
            }
         }
         // Moves down for even columns
         else {
            for (int i = 0; i < rows; i++) {
               grid[i][j].draw();
               StdDraw.pause(200);
            }
         }
      }
   }
   
   /**
    * Draws a grid input by the user in a diagonal pattern.
    *
    * @param grid The rectangle array for the checkerboard
    * @param rows The number of rows in the array
    * @param columns The number of columns in the array
    */
   public static void displayDiagonalPattern(Rectangle[][] grid, int rows, 
                                             int columns) {
      if (rows == columns) {
         StdDraw.clear(StdDraw.LIGHT_GRAY);
         
         // Draws the main diagonal first
         for (int i = 0; i < rows; i++) {
            grid[i][i].draw();
            StdDraw.pause(200);
         }
         // Draws the next diagonals, top first then bottom, alternating
         for (int j = 1; j < rows; j++) {
            for (int k = 0; k + j < rows; k++) {
               grid[k][k + j].draw();
               StdDraw.pause(200);
            }
            for (int l = 0; l + j < rows; l++) {
               grid[l + j][l].draw();
               StdDraw.pause(200);
            }
         }
      }
      else {
         System.out.println("Specified pattern is not square so cannot be " +
                            "displayed by Diagonals operation.");
      }
   }
}