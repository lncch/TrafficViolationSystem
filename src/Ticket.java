/**
 * Represents a traffic ticket in the system.
 * This class stores information about a traffic violation,
 * including details of the violating driver, the vehicle involved,
 * the violation code, fine amount, and penalties.
 */
public class Ticket {
    private static int ticketCounter = 1; // Static variable to generate unique ticket numbers
    private int ticketNo; // Unique ticket number for each instance
    private int degree; // Represents the severity level of the violation
    private Vehicle vehicle; // The vehicle involved in the violation
    private Driver violator; // The driver responsible for the violation
    private String violationCode; // Code identifying the type of violation
    private double violationAmount; // Base fine amount for the violation
    private boolean hasValidInsurance; // Indicates if the vehicle has valid insurance

    // Constructor to initialize a Ticket object
    public Ticket(Vehicle newVehicle, Driver newViolator, String newViolationCode, int newDegree, double newViolationAmount, boolean newHasValidInsurance) {
        ticketNo = ticketCounter++; // Assigns a unique ticket number and increments the counter
        setVehicle(newVehicle);
        setViolator(newViolator);
        setViolationCode(newViolationCode);
        setDegree(newDegree);
        setViolationAmount(newViolationAmount);
        setHasValidInsurance(newHasValidInsurance);
    }

    // Sets the vehicle involved in the violation
    public void setVehicle(Vehicle newVehicle) { vehicle = newVehicle; }

    // Sets the driver responsible for the violation
    public void setViolator(Driver newViolator) { violator = newViolator; }

    // Sets the violation code
    public void setViolationCode(String newViolationCode) { violationCode = newViolationCode; }

    // Sets the severity degree of the violation
    public void setDegree(int newDegree) { degree = newDegree; }

    // Sets the base fine amount for the violation
    public void setViolationAmount(double newViolationAmount) { violationAmount = newViolationAmount; }

    // Sets whether the driver had valid insurance
    public void setHasValidInsurance(boolean newHasValidInsurance) { hasValidInsurance = newHasValidInsurance; }

    // Gets the vehicle involved in the violation
    public Vehicle getVehicle() { return vehicle; }

    // Gets the driver responsible for the violation
    public Driver getViolator() { return violator; }

    // Gets the violation code
    public String getViolationCode() { return violationCode; }

    // Gets the severity degree of the violation
    public int getDegree() { return degree; }

    // Gets the base fine amount for the violation
    public double getViolationAmount() { return violationAmount; }

    // Checks whether the driver had valid insurance
    public boolean getHasValidInsurance() { return hasValidInsurance; }

    // Calculates the final fine amount after applying penalties
    public double calculateFinalFine() {
        double totalFine = violationAmount; // Start with base fine

        // Add penalty for invalid insurance
        if (!hasValidInsurance) {
            totalFine += 500;
        }

        // Add penalty for underage driver (age < 21)
        int driverAge = 2025 - violator.getYearOfBirth();
        if (driverAge < 21) {
            totalFine += 1000;
        }

        return totalFine;
    }

    // Returns a formatted string representation of the ticket details
    public String toString() {
        return
                "\t\tTicket No= " + ticketNo
                        + "\n\t\tViolation Code=" + getViolationCode()
                        + "\n\t\tDegree= " + getDegree()
                        + "\n\t\tViolation Amount=" + getViolationAmount()
                        + "\n\t\tHas Valid Insurance= " + (getHasValidInsurance() ? "Valid" : "Not Valid") + "\n"
                        + "\n" + vehicle.toString() + "\n" // Calls vehicle's toString() method
                        + "\n" + violator.toString() + "\n" // Calls violator's toString() method
                        + "\nTotal Penalty: " + calculateFinalFine() + "\n";
    }
}
