package com.company;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;

public class ParkingDrawing extends GuiComponent {

    private Image bg;
    private Image car;

    private ParkingLot parkingLot;
    private String sector = "A";

    /** Creates a new ParkingDrawing <br>
     * The parking view updates in real time <br>
     * Shows the cars' positioning in the parking lot
     * @param parkingLot The parking lot to be viewed
     */
    public ParkingDrawing(ParkingLot parkingLot) {

        super();

        this.parkingLot = parkingLot;

        bg = new ImageIcon("parking_lot.jpg").getImage();
        car = new ImageIcon("car.png").getImage();

        ScaledTime.onTimeChanged.addListener(() -> repaint());
    }

    /** Sets the parking view's sector
     * @param sector Sector to set
     */
    public void setSector(String sector) {
        this.sector = sector;
    };

    /** Draws the view on any parking lot change
     */
    @Override
    public void paintComponent(Graphics g) {

        super.paintComponent(g);

        g.drawImage(bg, 0, 0, getWidth(), getHeight(),0, 0, bg.getWidth(null), bg.getHeight(null), null);

        HashMap<String, Car> cars = parkingLot.getParkingSpots();

        int offset = getWidth() / 22;
        int space = (getWidth() - offset * 2) / 5;

        int row1 = getHeight() / 10;
        int row2 = getHeight() / 2 + row1 / 2;

        for (int i = 0; i < 10; i++) {

            int pos = i < 5 ? i : i - 5;
            int row = i < 5 ? row1 : row2;

            GuiPreferences.scaleFont(g, this, 20);
            g.setColor(Color.WHITE);
            g.drawString(sector + i, offset + space / 2 + pos * space, row);

            if (cars.get(sector + i) != null)
                drawCar(g, offset + pos * space + space / 4, row, cars.get(sector + i).getLicensePlate());
        }
    }

    private void drawCar(Graphics g, int x, int y, String licensePlate) {

        int offset = getHeight() / 3;
        int space = (getWidth() - offset * 2) / 5;

        g.drawImage(car, x, y, x + space, y + offset,0, 0, car.getWidth(null), car.getHeight(null), null);
        g.setColor(Color.WHITE);
        g.fillRect(x + space / 6, y + offset, space / 6 * 4, getHeight() / 32);

        GuiPreferences.scaleFont(g, this, 10);
        g.setColor(Color.BLACK);
        g.drawString(licensePlate, x + space / 6, y + offset + getHeight() / 42);
    }
}
