package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Gui extends JFrame {

    private JLabel clock;

    private ParkingVisualizer parkingVisualizer;
    private HistoryVisualizer historyVisualizer;
    private FeeVisualizer feeVisualizer;

    private JButton parkingVisualizerButton;
    private JButton historyVisualizerButton;
    private JButton feeVisualizerButton;

    /** Creates a new Gui <br>
     * Contains a ParkingVisualizer, a HistoryVisualizer, a FeeVisualizer and the clock
     * @param parkingLot Parking Lot to be viewed
     */
    public Gui(ParkingLot parkingLot) {

        setTitle("Parking Lot");
        setLayout(null);
        setSize(new Dimension(1200, 800));
        setPreferredSize(getSize());
        getContentPane().setBackground(GuiPreferences.background);

        clock = new JLabel();
        clock.setForeground(GuiPreferences.textColor);
        clock.setBackground(GuiPreferences.border);
        clock.setFont(GuiPreferences.parkingFont);
        clock.setHorizontalAlignment(JLabel.CENTER);
        clock.setVerticalAlignment(JLabel.CENTER);

        parkingVisualizer = new ParkingVisualizer(parkingLot);
        parkingVisualizer.setVisible(true);
        historyVisualizer = new HistoryVisualizer();
        historyVisualizer.setVisible(false);
        feeVisualizer = new FeeVisualizer();
        feeVisualizer.setVisible(false);

        parkingVisualizerButton = new JButton("Parking Lot");
        parkingVisualizerButton.setFont(GuiPreferences.parkingFont);
        parkingVisualizerButton.setBackground(GuiPreferences.button);
        parkingVisualizerButton.setForeground(GuiPreferences.textColor);
        parkingVisualizerButton. addActionListener(e -> {

            parkingVisualizer.setVisible(true);
            historyVisualizer.setVisible(false);
            feeVisualizer.setVisible(false);

            parkingVisualizerButton.setBackground(GuiPreferences.buttonPressed);
            historyVisualizerButton.setBackground(GuiPreferences.button);
            feeVisualizerButton.setBackground(GuiPreferences.button);

            repaint();
        });

        historyVisualizerButton = new JButton("History");
        historyVisualizerButton.setFont(GuiPreferences.parkingFont);
        historyVisualizerButton.setBackground(GuiPreferences.button);
        historyVisualizerButton.setForeground(GuiPreferences.textColor);
        historyVisualizerButton.addActionListener(e -> {

            parkingVisualizer.setVisible(false);
            historyVisualizer.setVisible(true);
            feeVisualizer.setVisible(false);

            parkingVisualizerButton.setBackground(GuiPreferences.button);
            historyVisualizerButton.setBackground(GuiPreferences.buttonPressed);
            feeVisualizerButton.setBackground(GuiPreferences.button);

            repaint();
        });

        feeVisualizerButton = new JButton("Fees");
        feeVisualizerButton.setFont(GuiPreferences.parkingFont);
        feeVisualizerButton.setBackground(GuiPreferences.button);
        feeVisualizerButton.setForeground(GuiPreferences.textColor);
        feeVisualizerButton. addActionListener(e -> {

            parkingVisualizer.setVisible(false);
            historyVisualizer.setVisible(false);
            feeVisualizer.setVisible(true);

            parkingVisualizerButton.setBackground(GuiPreferences.button);
            historyVisualizerButton.setBackground(GuiPreferences.button);
            feeVisualizerButton.setBackground(GuiPreferences.buttonPressed);

            repaint();
        });

        add(clock);
        add(parkingVisualizerButton);
        add(historyVisualizerButton);
        add(feeVisualizerButton);
        add(parkingVisualizer);
        add(historyVisualizer);
        add(feeVisualizer);

        pack();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);

        resize();

        ScaledTime.onTimeChanged.addListener(() -> clock.setText(ScaledTime.getTime()));

        addComponentListener(new ComponentAdapter() {
            public void componentResized(ComponentEvent componentEvent) {
                resize();
            }
        });

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                SaveManager.saveState(parkingLot);
                SaveManager.saveTime();
                System.exit(0);
            }
        });

        parkingVisualizerButton.doClick();
    }

    private void resize() {

        int widthDivision =  getWidth() / 64;
        int heightDivision = getHeight() / 64;

        clock.setLocation(0, heightDivision * 2);
        clock.setSize(getWidth(), heightDivision * 4);

        parkingVisualizerButton.setLocation(widthDivision * 2, heightDivision * 12);
        parkingVisualizerButton.setSize(widthDivision * 12, heightDivision * 4);

        historyVisualizerButton.setLocation(widthDivision * 2, heightDivision * 20);
        historyVisualizerButton.setSize(widthDivision * 12, heightDivision * 4);

        feeVisualizerButton.setLocation(widthDivision * 2, heightDivision * 28);
        feeVisualizerButton.setSize(widthDivision * 12, heightDivision * 4);

        GuiPreferences.scaleFont(parkingVisualizerButton, 20);
        GuiPreferences.scaleFont(historyVisualizerButton, 20);
        GuiPreferences.scaleFont(feeVisualizerButton, 20);
        GuiPreferences.scaleFont(clock, 30);

        parkingVisualizer.setLocation(widthDivision * 16, heightDivision * 12);
        parkingVisualizer.setSize(widthDivision * 46, heightDivision * 48);

        historyVisualizer.setLocation(widthDivision * 16, heightDivision * 12);
        historyVisualizer.setSize(widthDivision * 46, heightDivision * 48);

        feeVisualizer.setLocation(widthDivision * 16, heightDivision * 12);
        feeVisualizer.setSize(widthDivision * 46, heightDivision * 48);
    }

    /** Shows the current scaled time
     */
    @Override
    public void paint(Graphics g) {
        super.paint(g);
        clock.setText(ScaledTime.getTime());
    }
}
