import java.util.Scanner;
import java.awt.Color;

/** Rectangle object to be drawn using the StdDraw libary.
    EN.500.112 Gateway Computing: Java, Project 4
*/
public class Rectangle {
   
   //color values in the range [0,255]
   private int red;
   private int green;
   private int blue;
   
   //size variables
   private double halfWidth; //half the rectangle width
   private double halfHeight; //half the rectangle height
   
   //center coordinates
   private double xCenter;
   private double yCenter;
   
   //whether or not rectangle is filled when displayed
   private boolean filled;
  
   /** Create a rectangle based on values for the color, width and height,
       filled or not, and center coordinates.
       @param color the Color value to use
       @param width the rectangle's width [0, 1]
       @param height the rectangle's height [0, 1]
       @param fill true if rectangle is solid
       @param xcoord the rectangle's center x coordinate
       @param ycoord the rectangle's center y coordinate
   */
   public Rectangle(Color color, double width, double height,
         boolean fill, double xcoord, double ycoord) {
      red = color.getRed();
      green = color.getGreen();
      blue = color.getBlue();
      halfWidth = width / 2.0;
      halfHeight = height / 2.0;
      xCenter = xcoord;
      yCenter = ycoord;
      filled = fill;
   }
   
   /** Create a rectangle based on values in a string representation.
       Expected order is R G B values, then width and height, 
       then x and y center coordinates, lastly T/F for filled.
       @param line the String with the initial values
   */
   public Rectangle(String line) {
      Scanner scan = new Scanner(line);
      red = scan.nextInt();
      green = scan.nextInt();
      blue = scan.nextInt();
      halfWidth = scan.nextDouble();
      halfHeight = scan.nextDouble();
      xCenter = scan.nextDouble();
      yCenter = scan.nextDouble();
      filled = true;
      if (scan.next().charAt(0) == 'F') {
         filled = false;
      }
   }
   
   /** Get a string representation of the Rectangle values.
       Order is R G B values, then width and height, 
       then x and y center coordinates, lastly T/F for filled.
       @return the string generated
   */
   public String toString() {
      String result = "";
      result += red + " " + green + " " + blue + " ";  // color
      result += halfWidth + " " + halfHeight + " "; // size
      result += xCenter + " " + yCenter + " "; // center coordinates
      result += filled ? 'T' : 'F';  // filled or not
      return result;
   }
   
   /** Return the Color setting of this Rectangle, using the Java
       API Color class.
       (https://docs.oracle.com/javase/8/docs/api/java/awt/Color.html)
       @return the full color
   */
   public Color getColor() {
      return new Color(red, green, blue);
   }
   
   /** Find out if the object is filled or not.
       @return true if filled, false otherwise
   */
   public boolean isSolid() {
      return filled;
   }
   
   /** Draw this rectangle with the drawing library at the indicated location.
   */
   public void draw() {
      StdDraw.setPenColor(getColor());
      if (filled) {
         StdDraw.filledRectangle(xCenter, yCenter, halfWidth, halfHeight);
      } else {
         StdDraw.rectangle(xCenter, yCenter, halfWidth, halfHeight);
      }
   }
   
   
}
