

/**
 * Represents a driver in the system.
 * This class stores information about a driver, including their national ID,
 * first name, last name, and year of birth.
 */
public class Driver {
    // Private instance variables to store driver details
    private String nationalID; // Stores the national ID of the driver
    private String firstName;  // Stores the first name of the driver
    private String lastName;   // Stores the last name of the driver
    private int yearOfBirth;   // Stores the year of birth of the driver

     // Constructor to initialize a Driver object with given details.
    public Driver(String newNationalID, String newFirstName, String newLastName, int newYearOfBirth) {

        setNationalID(newNationalID);  // Calls setter method to assign national ID
        setFirstName(newFirstName);    // Calls setter method to assign first name
        setLastName(newLastName);      // Calls setter method to assign last name
        setYearOfBirth(newYearOfBirth); // Calls setter method to assign year of birth
    }

    // Setter methods to update driver attributes


     // Sets the national ID of the driver.
    public void setNationalID(String newNationalID) {
        nationalID = newNationalID;
    }


     // Sets the first name of the driver.
    public void setFirstName(String newFirstName) {
        firstName = newFirstName;
    }


     // Sets the last name of the driver.
    public void setLastName(String newLastName) {
        lastName = newLastName;
    }


     // Sets the year of birth of the driver.
    public void setYearOfBirth(int newYearOfBirth) {
        yearOfBirth = newYearOfBirth;
    }

    // Getter methods to retrieve driver attributes

    // Gets the national ID of the driver.
    public String getNationalID() {
        return nationalID;
    }

     // Gets the year of birth of the driver.
    public int getYearOfBirth() {
        return yearOfBirth;
    }

    // Gets the full name of the driver by combining first and last names.
    public String getName() {
        return firstName + " " + lastName;
    }

    // Returns a formatted string representation of the driver object.
    public String toString() {
        return "Driver Information\n "
                + "\t\tnationalID= " + getNationalID()
                + "\n\t\tName= " + getName()
                + "\n\t\tYearOfBirth= " + getYearOfBirth();
    }
}

