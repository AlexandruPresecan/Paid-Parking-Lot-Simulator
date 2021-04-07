package com.company;

public class HistoryEntry {

    private String licensePlate;
    private String parkingSpot;
    private String enterTime;
    private String exitTime;
    private String charge;

    /** Creates a new HistoryEntry from a car's data
     * @param car Car to be transformed in a history entry
     */
    public HistoryEntry(Car car) {
        licensePlate = car.getLicensePlate();
        enterTime = car.getTicket().getEnterTime();
        exitTime = car.getTicket().getExitTime();
        parkingSpot = car.getTicket().getParkingSpot();
        charge = car.getTicket().getCharge() + "";
    }

    /** Creates a new HistoryEntry from a car's data in the form of a string
     * @param car Car to be transformed into a history entry
     */
    public HistoryEntry(String car) {

        String[] data = car.split(";");

        licensePlate = data[0];
        parkingSpot = data[1];
        enterTime = data[2];
        exitTime = data[3];
        charge = data[4];
    }

    /** Transforms the history entry into a string
     * @return String containing all the history entry's fields separated by ;
     */
    public String toString() {
        return licensePlate + ";" + parkingSpot + ";"+ enterTime + ";" + exitTime + ";" + charge + "\n";
    }

    /** Returns the history entry's license plate
     * @return License plate
     */
    public String getLicensePlate() { return licensePlate; }

    /** Returns the history entry's parking spot
     * @return Parking spot
     */
    public String getParkingSpot() { return parkingSpot; }

    /** Returns the history entry's enter time
     * @return Enter time
     */
    public String getEnterTime() { return enterTime; }

    /** Returns the history entry's exit time
     * @return Exit time
     */
    public String getExitTime() { return exitTime; }

    /** Returns the history entry's charge
     * @return Charge
     */
    public String getCharge() { return charge; }
}
