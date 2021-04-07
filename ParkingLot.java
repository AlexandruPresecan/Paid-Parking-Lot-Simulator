package com.company;

import java.util.*;
import java.util.concurrent.TimeUnit;

public class ParkingLot implements Runnable {

    private String[] sectors = { "A", "B", "C", "D", "E" };

    private Queue<Car> carsAtEntrance = new LinkedList();
    private HashMap<String, Car> parkingSpots = initParkingSpots();
    private ArrayList<String> emptySpots = initEmptySpots();
    private ArrayList<String> occupiedSpots = new ArrayList();

    private Random random = new Random();

    private HashMap<String, Car> initParkingSpots() {

        HashMap<String, Car> parkingSpots = new HashMap();

        for (String sector : sectors)
            for (int i = 0; i < 10; i++)
                parkingSpots.put(sector + i, null);

        return parkingSpots;
    }

    private ArrayList<String> initEmptySpots() {

        ArrayList<String> emptySpots = new ArrayList<>();

        for (String sector : sectors)
            for (int i = 0; i < 10; i++)
                emptySpots.add(sector + i);

        return emptySpots;
    }

    private void enqueueCar() {
        Car car = new Car();
        carsAtEntrance.add(new Car());
        System.out.println(car.getLicensePlate() + " showed at the entrance");
    }

    private void addCarsFromEntrance() {

        while (emptySpots.size() > 0 && !carsAtEntrance.isEmpty()) {

            String emptySpot = emptySpots.get(random.nextInt(emptySpots.size()));

            Car car = carsAtEntrance.poll();
            car.setTicket(new Ticket(emptySpot));

            parkingSpots.put(emptySpot, car);
            emptySpots.remove(emptySpot);
            occupiedSpots.add(emptySpot);

            System.out.println(car.getLicensePlate() + " entered the parking lot");
        }
    }

    private void removeCar() {

        if (occupiedSpots.size() <= 0)
            return;

        String occupiedSpot = occupiedSpots.get(random.nextInt(occupiedSpots.size()));

        Car car = parkingSpots.get(occupiedSpot);
        car.getTicket().calculateCharge();
        SaveManager.saveHistoryEntry(car);

        System.out.println(car.getLicensePlate() + " left the parking lot");
        System.out.println(car.getTicket().getCharge() + "$ fee applied");

        parkingSpots.put(occupiedSpot, null);
        emptySpots.add(occupiedSpot);
        occupiedSpots.remove(occupiedSpot);
    }

    /** Starts the parking lot on another thread
     */
    public void startPerform() {
        Thread thread = new Thread(this);
        thread.start();
    }

    /** Runs pseudo random parking actions like enqueueing, adding and removing of the cars and increments the time
     */
    @Override
    public void run() {

        while (true) {

            if (emptySpots.size() > 0 && random.nextInt(parkingSpots.size()) <= emptySpots.size())
                enqueueCar();

            addCarsFromEntrance();

            if (occupiedSpots.size() > 0 && random.nextInt(parkingSpots.size()) <= occupiedSpots.size())
                removeCar();

            try {
                TimeUnit.SECONDS.sleep(1);
                ScaledTime.increment();
                System.out.println("Current time: " + ScaledTime.getTime());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    /** Returns the parking lot's sectors
     * @return String array contains sectors
     */
    public String[] getSectors() { return sectors; }

    /** Returns the parking lot's spots and cars
     * @return HashMap containing the parking spots and the corresponding car for each spot
     */
    public HashMap<String, Car> getParkingSpots() { return parkingSpots; }
}
