/** A class to represent Time. */
public class Time {
   /** Store the value of hours (0 through 23). */
   private int hour;
   /** Store the value of minutes (0 through 59). */
   private int minute;

   /** Initializes the time to midnight 00:00. */
   public Time() {
      hour = 0;
      minute = 0;
   }

   /**
   * Initializes the time to the specified hours and minutes values.
   * Precondition: 0 <= hour < 24 and 0 <= minute < 60.
   * @param hour the value of hours.
   * @param minute the value of the minutes.
   */
   public Time(int hour, int minute) {
      this();
      if (isValid(hour, minute)) {
         this.hour = hour;
         this.minute = minute;
      }
   }

   /**
   * Sets the hour of this Time object to the specified value.
   * Precondition: 0 <= h < 24
   * @param h the hour value
   */
   public void setHours(int h) {
      hour = h;
   }

   /**
   * Sets the minutes of this Time object to the specified value.
   * Precondition: 0 <= m < 60
   * @param m the value of the minutes.
   */
   public void setMinutes(int m) {
      minute = m;
   }

   /**
   * Returns the hour represented by this Time object.
   * @return an integer (0 through 23) representing
   * the hour within the day.
   */
   public int getHours() {
      return hour;
   }

   /**
   * Returns the number of minutes past the hour
   * represented by this time.
   * @return an integer (0 through 59) representing the number
   * of minutes past the hour represented by this time.
   */
   public int getMinutes() {
      return minute;
   }

   /**
    * Create a string that "textually represents" this object.
    * @return  a string representation of the object in HH:MM format.
    */
   @Override
   public String toString() {
      return String.format("%02d:%02d", hour, getMinutes());
   }
   
   private static boolean isValid(int hour, int minute) {
      if (hour >= 0 && hour <= 23 && minute >= 0 && minute <= 59) {
         return true;
      }
      else {
         return false;
      }
   }

}
