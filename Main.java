package com.company;

public class Main {

    public static void main(String[] args) {

        SaveManager.loadTime();

        ParkingLot parkingLot = new ParkingLot();
        SaveManager.loadState(parkingLot);
        parkingLot.startPerform();

        Gui gui = new Gui(parkingLot);
    }
}
