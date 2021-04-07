package com.company;

import javax.swing.*;
import java.util.ArrayList;

public class ParkingVisualizer extends GuiComponent  {

    private ParkingDrawing parkingDrawing;
    private ParkingLot parkingLot;

    private ArrayList<JRadioButton> sectorButtons = new ArrayList();
    private ButtonGroup buttonGroup;

    /** Creates a new ParkingVisualizer <br>
     * Contains a ParkingDrawing and 6 sector buttons that allow switching the ParkingDrawing's sector
     */
    public ParkingVisualizer(ParkingLot parkingLot) {

        super();

        this.parkingLot = parkingLot;

        setBackground(GuiPreferences.panel);

        parkingDrawing = new ParkingDrawing(parkingLot);
        add(parkingDrawing);

        createSectorButtons();
    }

    private void createSectorButtons() {

        buttonGroup = new ButtonGroup();

        for (String sector: parkingLot.getSectors()) {

            JRadioButton sectorButton = new JRadioButton("Sector " + sector);

            sectorButton.setBackground(GuiPreferences.button);
            sectorButton.setForeground(GuiPreferences.textColor);
            sectorButton.setFont(GuiPreferences.parkingFont);

            sectorButton.addActionListener(e -> {

                for (JRadioButton button : sectorButtons)
                    button.setBackground(GuiPreferences.button);

                sectorButton.setBackground(GuiPreferences.buttonPressed);
                parkingDrawing.setSector(sector);
                repaint();
            });

            buttonGroup.add(sectorButton);
            sectorButtons.add(sectorButton);

            add(sectorButton);
        }

        sectorButtons.get(0).doClick();
    }

    @Override
    protected void resize() {

        int widthDivision =  getWidth() / 5;
        int heightDivision = getHeight() / 64;

        int x = 0;

        for (JRadioButton sectorButton: sectorButtons) {

            sectorButton.setLocation(x, 0);
            sectorButton.setSize(widthDivision, heightDivision * 6);
            GuiPreferences.scaleFont(sectorButton, 20);

            x += widthDivision;
        }

        parkingDrawing.setLocation(0, heightDivision * 6);
        parkingDrawing.setSize(getWidth(), heightDivision * 60);
    }
}
