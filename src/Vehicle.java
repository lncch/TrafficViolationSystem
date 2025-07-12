/**
 Name: Al-Moayyad Abuljdail
 ID: 2435891
 Section: C11
 Assignment No.: 1
 **/

/**
 * Represents a vehicle in the system.
 * This class stores information about a vehicle,
 * including its plate number, brand, model, and manufacturing year.
 */
public class Vehicle {
    private String plateNo, brand, model; // Vehicle attributes
    private int year; // Year the vehicle was manufactured

    // Constructor to initialize a Vehicle object
    public Vehicle(String newPlateNo, String newBrand, String newModel, int newYear) {
        setPlateNo(newPlateNo);
        setBrand(newBrand);
        setModel(newModel);
        setYear(newYear);
    }

    // Sets the model of the vehicle
    public void setModel(String newModel) { model = newModel; }

    // Sets the manufacturing year of the vehicle
    public void setYear(int newYear) { year = newYear; }

    // Sets the brand of the vehicle
    public void setBrand(String newBrand) { brand = newBrand; }

    // Sets the plate number of the vehicle
    public void setPlateNo(String newPlateNo) { plateNo = newPlateNo; }

    // Gets the plate number of the vehicle
    public String getPlateNo() { return plateNo; }

    // Gets the brand of the vehicle
    public String getBrand() { return brand; }

    // Gets the model of the vehicle
    public String getModel() { return model; }

    // Gets the manufacturing year of the vehicle
    public int getYear() { return year; }

    // Returns a formatted string representation of the vehicle details
    public String toString() {
        return "Vehicle Information"
                + "\n\t\tVehiclePlateNo= " + getPlateNo()
                + "\n\t\tBrand= " + getBrand()
                + "\n\t\tVehicleModel= " + getModel()
                + "\n\t\tBuiltYear= " + getYear();
    }
}
