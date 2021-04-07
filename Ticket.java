package com.company;

public class Ticket {

    private String enterTime;
    private String exitTime;
    private String parkingSpot;
    private float charge;

    /** Creates a new ticket setting its enter time and parking spot
     * @param enterTime must be in the format "yyyy-MM-dd HH:mm:ss"
     * @param parkingSpot
     */
    public Ticket(String enterTime, String parkingSpot) {
        this.enterTime = enterTime;
        this.parkingSpot = parkingSpot;
    }

    /** Creates a new ticket setting its parking spot <br>
     * The entering time is set to the current time
     * @param parkingSpot
     */
    public Ticket(String parkingSpot) {
        enterTime = ScaledTime.getTime();
        this.parkingSpot = parkingSpot;
    }

    /** Sets the ticket's exit time and calculates the charge <br>
     * The exit time will be set to the current time <br>
     * The charge is auto-calculated <br>
     * Call this on the car's ticket when it is leaving the parking spot
     */
    public void calculateCharge() {
        exitTime = ScaledTime.getTime();
        charge = Charges.getCharge(this);
    }

    /** Returns the ticket's enter time
     * @return Enter time as a string in the "yyyy-MM-dd HH:mm:ss" format
     */
    public String getEnterTime() {
        return enterTime;
    }

    /** Returns the ticket's exit time
     * @return Exit time as a string in the "yyyy-MM-dd HH:mm:ss" format
     */
    public String getExitTime() {
        return exitTime;
    }

    /** Returns the ticket's parking spot
     * @return Parking spot
     */
    public String getParkingSpot() {
        return parkingSpot;
    }

    /** Returns the ticket's charge
     * @return Charge
     */
    public float getCharge() {
        return charge;
    }
}
