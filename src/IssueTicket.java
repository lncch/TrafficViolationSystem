/**
 * Name: Al-Moayyad Abuljdail
 * ID: 2435891
 * Section: C11
 * Assignment No.: 1
 **/

import java.io.*;
import java.util.*;

/**
 * TrafficViolationSystem manages vehicle violations, driver records,
 * ticketing, and fine calculations.
 */
public class IssueTicket {
    private static Vehicle[] vehicles; // Array to store vehicles
    private static Driver[] drivers; // Array to store drivers
    private static Ticket[] tickets; // Array to store issued tickets
    private static double[][] violations; // Stores different levels of violations and their penalties
    private static int vehicleCount = 0, driverCount = 0, ticketCount = 0; // Counters for each entity
    private static String[] violationCodes = {"Red-light", "Speeding", "No-parking", "Wrong-way"}; // List of violation types

    /**
     * Main method that initializes the system, reads input data, processes commands, and writes output.
     */
    public static void main(String[] args) throws IOException {
        File inputfile = new File("input.txt"); // Input file containing system commands

        // Check whether input file exists
        if (!inputfile.exists()) {
            System.out.println("File does not exist");
            System.exit(0);
        }
        Scanner input = new Scanner(inputfile); //read from input.txt file
        PrintWriter output = new PrintWriter("TrafficDB.txt");

        // Read input parameters and initialize arrays
        int n = input.nextInt(); // Number of vehicles
        vehicles = new Vehicle[n];

        int m = input.nextInt(); // Number of drivers
        drivers = new Driver[m];

        int x = input.nextInt(); // Number of violation types
        violations = new double[x][];

        int z = input.nextInt(); // Number of tickets allowed
        tickets = new Ticket[z];

        output.println("--------------- Welcome to Traffic Ticketing System ---------------\n");

        // Process commands from input file
        while (input.hasNext()) {
            String command = input.next();
            switch (command) {
                case "AddVehicle":
                    vehicles[vehicleCount++] = addVehicle(input);
                    break;
                case "AddDriver":
                    drivers[driverCount++] = addDriver(input);
                    break;
                case "AddViolation":
                    addViolation(violations, input);
                    break;
                case "IssueTicket":
                        tickets[ticketCount++] = GenerateTicket(input, vehicles, drivers, violations, output);
                    break;
                case "Quit":
                    break;

            }
        }
        printViolationsPerDriver(drivers, tickets, output);
        output.close();
    }

    /**
     * Creates a Vehicle object using data from the input file.
     */
    public static Vehicle addVehicle(Scanner input) {
        return new Vehicle(input.next(), input.next(), input.next(), input.nextInt());
    }

    /**
     * Creates a Driver object using data from the input file.
     */
    public static Driver addDriver(Scanner input) {
        return new Driver(input.next(), input.next(), input.next(), input.nextInt());
    }

    /**
     * Registers a new violation type and its associated fine levels.
     * Each violation has different fine levels based on severity.
     */
    public static void addViolation(double[][] listviolation, Scanner input) {
        String code = input.next(); // Violation code
        int levels = input.nextInt(); // Number of fine levels
        int index = -1;

        // Find index of the code in violationCodes
        for (int i = 0; i < violationCodes.length; i++) {
            if (violationCodes[i].equals(code)) {
                index = i;
                break;
            }
        }

        // If code is not found, handle the case
        if (index == -1) {
            System.out.println("Violation code not found!");
            return;
        }

        // Allocate space for fine levels
        listviolation[index] = new double[levels];

        // Read fine levels
        for (int i = 0; i < levels; i++) {
            listviolation[index][i] = input.nextDouble();
        }
    }

    /**
     * Generates a ticket based on the provided data, checking for valid vehicle, driver, and violation.
     */
    public static Ticket GenerateTicket(Scanner input, Vehicle[] listVehicle, Driver[] listDriver, double[][] listViolation, PrintWriter fWrite) {
        String plateNo = input.next(); // Vehicle plate number
        String driverID = input.next(); // Driver's national ID
        String violationType = input.next(); // Violation type
        int degree = input.nextInt(); // Violation severity degree
        boolean validInsurance = input.nextBoolean(); // Insurance status

        Vehicle vehicle = null;
        Driver driver = null;
        double fineAmount = 0;
        boolean violationFound = false;

        // Find vehicle by plate number
        for (int i = 0; i < vehicleCount; i++) {
            if (listVehicle[i] != null && listVehicle[i].getPlateNo().equals(plateNo)) {
                vehicle = listVehicle[i];
                break;
            }
        }

        // Find driver by ID
        for (int i = 0; i < driverCount; i++) {
            if (listDriver[i] != null && listDriver[i].getNationalID().equals(driverID)) {
                driver = listDriver[i];
                break;
            }
        }

        // Find violation index and fine amount
        for (int i = 0; i < violationCodes.length; i++) {
            if (violationCodes[i].equals(violationType)) {
                fineAmount = listViolation[i][degree - 1]; // Retrieve fine based on degree
                    violationFound = true;
                break;
            }
        }


        // Handle errors if violation, vehicle, or driver not found
        if (!violationFound) {
            fWrite.println("Error: Violation not found.");
            return null;
        }
        if (vehicle == null || driver == null) {
            fWrite.println("Error: Vehicle or driver not found.");
            return null;
        }

        // Creates the Ticket and print it
        Ticket newTicket = new Ticket(vehicle, driver, violationType, degree, fineAmount,validInsurance);
        printTicket(newTicket, fWrite);



        return newTicket; // Return the new generated Ticket
    }

    /**
     * Prints the ticket details to the output file.
     */
    public static void printTicket(Ticket ticket, PrintWriter output) {
        output.println("--------------- Ticket Information ---------------");
        output.println(ticket.toString());
    }

    /**
     * Generates a report showing the number of violations per driver.
     */
    public static void printViolationsPerDriver(Driver[] allDrivers, Ticket[] allTickets, PrintWriter fWrite) {
        fWrite.println("--------Total Violation(s) Per Driver--------");
        fWrite.println("Driver ID           Driver Name        Total Violation(s)");

        // Count the number of tickets for each driver
        for (int i = 0; i < driverCount; i++) {
            int count = 0;

            for (int j = 0; j < ticketCount; j++) {
                if (allTickets[j] != null && allTickets[j].getViolator().getNationalID().equals(allDrivers[i].getNationalID())) {
                    count++;
                }
            }

            fWrite.println(allDrivers[i].getNationalID() + "\t\t\t" + allDrivers[i].getName() + "\t\t\t" + count); //writes Violations Per Driver to TrafficDB.txt
        }
    }
}
