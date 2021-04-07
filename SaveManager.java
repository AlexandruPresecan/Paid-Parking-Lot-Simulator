package com.company;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class SaveManager {

    /** Saves the specified ParkingLot's current state to the file state.txt
     * @param parkingLot The ParkingLot to be saved
     */
    public static void saveState(ParkingLot parkingLot) {

        FileWriter state;

        try {

            state = new FileWriter("state.txt");

            HashMap<String, Car> cars = parkingLot.getParkingSpots();

            for (Car car: cars.values())
                if (car == null)
                    state.write("null\n");
                else
                    state.write(car.getLicensePlate() + ";" + car.getTicket().getEnterTime() + ";" + car.getTicket().getParkingSpot() + "\n");

            state.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /** Loads the state of the specified ParkingLot from the file state.txt
     * @param parkingLot The ParkingLot to be loaded
     */
    public static void loadState(ParkingLot parkingLot) {

        try {

            List<String> lines = Files.readAllLines(new File("state.txt").toPath());

            if (lines.size() <= 0)
                return;

            HashMap<String, Car> cars = parkingLot.getParkingSpots();

            for (String line : lines)
                if (!line.equals("null")) {
                    String[] data = line.split(";");
                    Car car = new Car(data[0]);
                    car.setTicket(new Ticket(data[1], data[2]));
                    cars.put(car.getTicket().getParkingSpot(), car);
                }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /** Appends a new history entry obtained a car in one of the history files <br>
     * The file in which the car is saved will depend on the car's exit year
     * @param car Car to be converted to a HistoryEntry and saved
     */
    public static void saveHistoryEntry(Car car) {

        FileWriter state;

        try {

            int year = Integer.parseInt(car.getTicket().getExitTime().split(" ")[0].split("-")[0]);

            state = new FileWriter(year + "history.txt", true);
            state.write(new HistoryEntry(car).toString());
            state.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /** Loads all the history entries that are found in a time interval <br>
     * startDate and endDate must be in the format "yyyy-MM-dd HH:mm:ss"
     * @param startDate Starting date of the interval
     * @param endDate Ending date of the interval
     * @return A list of history entries found in the specified interval
     */
    public static List<HistoryEntry> loadHistoryEntries(String startDate, String endDate) {

        try {

            List<HistoryEntry> entries = new ArrayList<>();

            int startYear = Integer.parseInt(startDate.split(" ")[0].split("-")[0]);
            int endYear = Integer.parseInt(endDate.split(" ")[0].split("-")[0]);

            for (int i = startYear; i <= endYear; i++) {

                List<String> lines = Files.readAllLines(new File(i + "history.txt").toPath());

                for (String line : lines) {

                    HistoryEntry entry = new HistoryEntry(line);

                    String enterDate = null;
                    String exitDate = null;

                    try {
                        Date enter = new SimpleDateFormat("yyyy-MM-dd").parse(entry.getEnterTime());
                        enterDate = new SimpleDateFormat("yyyy-MM-dd").format(enter);
                        Date exit = new SimpleDateFormat("yyyy-MM-dd").parse(entry.getExitTime());
                        exitDate = new SimpleDateFormat("yyyy-MM-dd").format(exit);
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }

                    if (enterDate.compareTo(startDate) >= 0 && exitDate.compareTo(endDate) <= 0)
                        entries.add(entry);
                }
            }

            return entries;

        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    /** Loads all the fees/date found in a time interval <br>
     * startDate and endDate must be in the format "yyyy-MM-dd HH:mm:ss"
     * @param startDate Starting date of the interval
     * @param endDate Ending date of the interval
     * @return A list of fees/date entries found in the specified interval
     */
    public static HashMap<String, Float> loadFees(String startDate, String endDate) {

        try {

            HashMap<String, Float> fees = new HashMap();

            int startYear = Integer.parseInt(startDate.split(" ")[0].split("-")[0]);
            int endYear = Integer.parseInt(endDate.split(" ")[0].split("-")[0]);

            for (int i = startYear; i <= endYear; i++) {

                List<String> lines = Files.readAllLines(new File(i + "history.txt").toPath());

                for (String line : lines) {

                    HistoryEntry entry = new HistoryEntry(line);

                    String enterDate = null;
                    String exitDate = null;

                    try {
                        Date enter = new SimpleDateFormat("yyyy-MM-dd").parse(entry.getEnterTime());
                        enterDate = new SimpleDateFormat("yyyy-MM-dd").format(enter);
                        Date exit = new SimpleDateFormat("yyyy-MM-dd").parse(entry.getExitTime());
                        exitDate = new SimpleDateFormat("yyyy-MM-dd").format(exit);
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }

                    if (enterDate.compareTo(startDate) >= 0 && exitDate.compareTo(endDate) <= 0) {

                        if (fees.get(exitDate) == null)
                            fees.put(exitDate, Float.parseFloat(entry.getCharge()));
                        else
                            fees.put(exitDate, fees.get(exitDate) + Float.parseFloat(entry.getCharge()));
                    }
                }
            }

            return fees;

        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    /** Saves the current time in the file time.txt
     */
    public static void saveTime() {

        FileWriter timeFile;

        try {

            timeFile = new FileWriter("time.txt");
            timeFile.write(ScaledTime.getTime() + "\n");
            timeFile.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /** Loads the time from the file time.txt and sets it to the ScaledTime
     */
    public static void loadTime() {

        try {

            List<String> lines = Files.readAllLines(new File("time.txt").toPath());
            ScaledTime.setTime(lines.get(0));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
