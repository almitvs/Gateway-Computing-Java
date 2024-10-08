import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Scanner;
import java.io.PrintWriter;

/**
 * Car Data Analysis: Project 3
 *
 * This program allows the user analyze data on used cars for sale in the US. 
 * After inputting a CSV file with the data the user can select 5 options: 
 * 1) Find the average price of a brand inputted by the user and create a CSV  
 *    file of all entries with this brand.
 * 2) Find the 2 highest prices in the enter dataset.
 * 3) Find the average price within ranges for user inputted year and mileage.
 * 4) Find the best value for cars above the user inputted mileage and price.
 * 5) Exit the program.
 *
 * Gateway Programming: Java
 * Johns Hopkins University
 * Spring 2023
 * Author: Aidan Alme
 * JHED: aalme2
 * Date: 02/25/2023
 */
public class CarDataAnalysis {

   // menu options
   static final int BRAND_QUERY = 1;
   static final int TWO_HIGHEST_PRICES_QUERY = 2;
   static final int RANGE_QUERY = 3;
   static final int BEST_VALUE_QUERY = 4;
   static final int QUIT = 5;

   // column index constants for car data file
   static final int BRAND = 2;
   static final int YEAR = 4;
   static final int MILEAGE = 6;
   static final int PRICE = 1;

   /**
    * Counts the number of lines in a given plain-text file.
    * @param filename The file whose lines are to be counted.
    * @return the number of lines in the file.
    * @throws IOException
    */
   public static int countFileLines(String filename)
                                    throws IOException {
      
      // Creates an input stream and scanner from the user's file
      FileInputStream file = new FileInputStream(filename);
      Scanner fileScanner = new Scanner(file);
      String dummy;
      int lineNum = 0;
      
      // Scans each line while counting the total scanned
      while (fileScanner.hasNext()) {
         dummy = fileScanner.nextLine();
         lineNum++;
      }
      
      return lineNum;
   
   }


   /**
    * Print the program menu to the console.
    */
   public static void printMenu() {
   
      System.out.printf("[%d]: Average price of brand.\n", BRAND_QUERY);
      System.out.printf("[%d]: Two highest prices.\n",
             TWO_HIGHEST_PRICES_QUERY);
      System.out.printf("[%d]: Average price in year and mileage range.\n",
             RANGE_QUERY);
      System.out.printf("[%d]: Best value.\n", BEST_VALUE_QUERY);
      System.out.printf("[%d]: Quit.\n", QUIT);
      System.out.print("Please select an option: ");
   
   }
   
   /**
    * Stores data from a given CSV file into arrays.
    * @param filename The file from which the data is extracted.
    * @param rowCount The number of elements in the arrays.
    * @param brands The array which stores the car brands.
    * @param years The array which stores the car years.
    * @param mileages The array which stores the car mileages.
    * @param prices The array which stores the car prices.
    * @throws IOException
    */
   public static void loadArrays(String filename, int rowCount, 
                                 String[] brands, int[] years, 
                                 double[] mileages, int[] prices) 
                                                   throws IOException {
      
      // Creates an input stream and scanner from the user's file
      FileInputStream file = new FileInputStream(filename);
      Scanner fileScanner = new Scanner(file);
      
      // Skips the first line in the file with column labels
      String line = fileScanner.nextLine();
      String[] lineArray = line.split(",");
      
      // Scans each line and adds the relevant values to the arrays
      for (int i = 0; i < rowCount; i++) {
         if (fileScanner.hasNext()) {
            // Scans the next line
            line = fileScanner.nextLine();
            // Converts the line into a string with the commas
            lineArray = line.split(",");
            // Stores relevant data in the corresponding array based on
            // in which column it is found in the original file
            brands[i] = lineArray[BRAND];
            years[i] = Integer.parseInt(lineArray[YEAR]);
            mileages[i] = Double.parseDouble(lineArray[MILEAGE]);
            prices[i] = Integer.parseInt(lineArray[PRICE]);
         }
      }
   }
   
   /**
    * Prompts the user to input a car brand then outputs
    * a CSV file with all car listings of that brand.
    * Finds and prints the number of entries for that brand
    * and their average price.
    * @param scnr The scanner to gather user input
    * @param brands The array which stores the car brands.
    * @param years The array which stores the car years.
    * @param mileages The array which stores the car mileages.
    * @param prices The array which stores the car prices.
    * @throws IOException
    */
   public static void calculateBrandAverage(Scanner scnr, String[] brands, 
                                            int[] years, double[] mileages, 
                                            int[] prices) throws IOException {
      
      // Prompts the user to enter a car brand
      System.out.print("Please enter a car brand: ");
      String brand = scnr.next();
      
      // Prompts the user for an output filename and makes the output stream
      System.out.print("Please enter an output filename: ");
      String fileName = scnr.next();
      FileOutputStream fileOut = new FileOutputStream(fileName);
      PrintWriter outFS = new PrintWriter(fileOut);
      
      // Searchs for entries with the brand and counts them
      int numBrand = 0;
      double averagePrice = 0;
      for (int i = 0; i < brands.length; i++) {
         if (brands[i].equalsIgnoreCase(brand)) {
            // Adds to the number of cars for this brand
            numBrand++;
            // Adds the price of the car, to be averaged after the total
            // cars in this brand is found
            averagePrice += prices[i];
            // Prints all information on the entry to the output file
            outFS.printf("%d, %s, %d, %.1f, %d\n", i, 
                         brands[i], years[i], mileages[i], prices[i]);
            outFS.flush();
         }
      }
      
      // Prints the number of entries for this brand and their average price
      if (numBrand > 0) {
         // Computes the average price
         averagePrice /= numBrand;
         System.out.printf("There are %d matching entries for brand %s " + 
                           "with an average\n", numBrand, brand);
         System.out.printf("price of $%.2f.\n", averagePrice);
      }
      
      // Informs the user if there are no matches found
      else {
         System.out.printf("There are no matching entries for brand %s.\n",
                                                                     brand);
      }
   }
   
   /**
    * Finds and prints the 2 highest prices.
    * @param prices The array which stores the car prices.
    */
   public static void findTwoHighestPrices(int[] prices) {
      double highestPrice = 0;
      double secondHighestPrice = 0;
      for (int i = 0; i < prices.length; i++) {
         // Determines whether a given price is the highest
         if (prices[i] > highestPrice) {
            highestPrice = prices[i];
         }
         // Determines whether a given price is the second highest
         else if (prices[i] < highestPrice && prices[i] > secondHighestPrice) {
            secondHighestPrice = prices[i];
         }
      }
      System.out.printf("The two highest prices are $%.2f and $%.2f.\n", 
                        highestPrice, secondHighestPrice);
   }
   
   
   /**
    * Calculates and prints the number of matching entries and 
    * their average price within the user given bounds for year and mileage.
    * @param scnr The scanner to collect user input.
    * @param years The array which stores the car years.
    * @param prices The array which stores the car prices.
    * @param mileages The array which stores the car mileages.
    */
   public static void calculateAveragePrice(Scanner scnr, int[] years, 
                                             int[] prices, double[] mileages) {
     
      // Prompts the user for year and mileage bounds
      System.out.print("Please enter the year lower bound: ");
      int yearLower = scnr.nextInt();
      System.out.print("Please enter the year upper bound: ");
      int yearUpper = scnr.nextInt();
      System.out.print("Please enter the mileage lower bound: ");
      double mileageLower = scnr.nextDouble();
      System.out.print("Please enter the mileage upper bound: ");
      double mileageUpper = scnr.nextDouble();
      int matches = 0;
      double averagePrice = 0;
      
      for (int i = 0; i < years.length; i++) {
         if (years[i] >= yearLower && years[i] <= yearUpper && 
             mileages[i] >= mileageLower && mileages[i] <= mileageUpper) {
            // Adds to the number of matches
            matches++;
            // Adds the price of the car, to be averaged after the total
            // cars in these bounds is found
            averagePrice += prices[i];
         }
      }
      
      // Computes the average price
      averagePrice /= matches;
      // Prints the number of entries and their average price
      System.out.printf("There are %d matching entries for year range [%d," + 
                        " %d] and\n", matches, yearLower, yearUpper);
      System.out.printf("mileage range [%.0f, %.0f] with an average price of" + 
                        " $%.2f.\n", mileageLower, mileageUpper, averagePrice);
   
   }
   
   /**
    * Calculates the best-value car with mileage and price above
    * bounds input by the user and prints the row number,
    * year, brand, mileage, and price of this car.  
    * @param scnr The scanner to collect user input.
    * @param years The array which stores the car years.
    * @param prices The array which stores the car prices.
    * @param mileages The array which stores the car mileages.
    * @param brands The array which stores the car brands.
    */
   public static void findBestValue(Scanner scnr, int[] years, int[] prices, 
                                    double[] mileages, String[] brands) {
      
      // Prompts the user for mileage and price thresholds
      System.out.print("Please enter lower mileage threshold: ");
      double lowestMileage = scnr.nextDouble();
      System.out.print("Please enter lower price threshold: ");
      int lowestPrice = scnr.nextInt();
      
      int bestValIndex = 0;
      double bestVal = 0;
      double currentVal = 0;
      for (int i = 0; i < mileages.length; i++) {
         // Checks if an entry is within the bounds
         if (mileages[i] > lowestMileage && prices[i] > lowestPrice) {
            // Computes the value of the entry
            currentVal = years[i] - mileages[i] / 13500.0 - prices[i] / 1900.0;
            // Checks if the current value is the highest
            if (currentVal > bestVal) {
               bestVal = currentVal;
                 // Stores the index of the highest value
               bestValIndex = i;
            }
         }
      }
      
      // Prints the entry for the best value car
      System.out.printf("The best-value entry with more than %.1f miles and " + 
                        "a price\n", lowestMileage); 
      System.out.printf("higher than $%d is a %d %s with %.1f miles for a" + 
                        " price\n", lowestPrice, years[bestValIndex],
                        brands[bestValIndex], mileages[bestValIndex]);
      System.out.printf("of $%d.\n", prices[bestValIndex]);
   }

   /**
    * Drive the Car Data Analysis program.
    * @param args This program does not take commandline arguments.
    * @throws IOException
    */
   public static void main(String[] args) throws IOException {
   
      // output purpose
      System.out.println("Welcome to the car dataset analysis program.");
   
      // get input filename (e.g. "USA_cars_datasets.csv")
      System.out.print("Please enter input csv filename: ");
      Scanner keyboard = new Scanner(System.in);
      String filename = keyboard.nextLine();
   
      // count the number of rows in the file (ignore headers line)
      int rowCount = countFileLines(filename) - 1;
      System.out.println("File has " + rowCount + " entries.");
      System.out.println();
   
      // declare and allocate parallel arrays for each column of interest
      String[] brands = new String[rowCount];
      int[] years = new int[rowCount];
      double[] mileages = new double[rowCount];
      int[] prices = new int[rowCount];
   
      // load columns from file
      loadArrays(filename, rowCount, brands, years, mileages, prices);
   
      // while the user doesn't choose to quit...
      int option = 0;
      while (option != QUIT) {
      
         // display the menu and get an option
         printMenu();
         option = keyboard.nextInt();
      
         // handle chosen option
         switch (option) {
            case BRAND_QUERY:
               calculateBrandAverage(keyboard, brands, years, mileages, prices);
               break;
            case TWO_HIGHEST_PRICES_QUERY:
               findTwoHighestPrices(prices);
               break;
            case RANGE_QUERY:
               calculateAveragePrice(keyboard, years, prices, mileages);
               break;
            case BEST_VALUE_QUERY:
               findBestValue(keyboard, years, prices, mileages, brands);
               break;
            case QUIT:
               System.out.println("Thank you for using the program!");
               break;
            default:
               System.out.println("Invalid option.");
         
         }
      
         // leave empty line for next printing of menu
         System.out.println();
      
      }
   
   }

}
