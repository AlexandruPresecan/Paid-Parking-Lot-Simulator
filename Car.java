package com.company;

import java.util.Random;

public class Car {

    private String licensePlate;
    private Ticket ticket;

    private Random random = new Random();

    private final String[] counties = {
        "B ",
        "AB",
        "AG",
        "AR",
        "BC",
        "BH",
        "BN",
        "BR",
        "BT",
        "BV",
        "BZ",
        "CJ",
        "CL",
        "CS",
        "CT",
        "CV",
        "DB",
        "DJ",
        "GJ",
        "GL",
        "GR",
        "HD",
        "HR",
        "IF",
        "IL",
        "IS",
        "MH",
        "MM",
        "MS",
        "NT",
        "OT",
        "PH",
        "SB",
        "SJ",
        "SM",
        "SV",
        "TL",
        "TM",
        "TR",
        "VL",
        "VN",
        "VS"
    };

    /** Creates a car with a specified license plate
     * @param licensePlate
     */
    public Car(String licensePlate) {
        this.licensePlate = licensePlate;
    }

    /** Creates a car with a random license plate
     */
    public Car() {
        licensePlate = counties[random.nextInt(counties.length)] + " "
                + random.nextInt(10) + random.nextInt(10) + " "
                + (char)(65 + random.nextInt(26)) + (char)(65 + random.nextInt(26)) + (char)(65 + random.nextInt(26));
    }

    /** Sets the car's ticket
     * @param ticket Ticket to set
     */
    public void setTicket(Ticket ticket) {
        this.ticket = ticket;
    }

    /** Returns the car's ticket
     * @return car's ticket
     */
    public Ticket getTicket() { return ticket; }

    /** Returns the car's license plate
     * @return car's license plate
     */
    public String getLicensePlate() { return licensePlate; }
}
