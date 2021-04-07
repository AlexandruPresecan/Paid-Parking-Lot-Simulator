package com.company;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class FeeVisualizer extends GuiComponent {

    private JScrollPane scrollPane;
    private JTable scrollContent;
    private DefaultTableModel tableModel;

    private JButton refreshButton;
    private JLabel dateStartLabel;
    private JLabel dateEndLabel;
    private DatePicker datePickerStart;
    private DatePicker datePickerEnd;
    private Chart chart;

    /** Creates a new FeeVisualizer <br>
     * Contains: <br>
     * - 2 DatePickers that allow selecting fees from a time interval <br>
     * - a JTable to visualize all the fees obtained in the selected interval <br>
     * - a Chart to visualize the fees' growth in the selected interval <br>
     * - a refresh button for real time refreshing <br>
     */
    public FeeVisualizer() {

        super();

        setBackground(GuiPreferences.panel);

        chart = new Chart();

        refreshButton = new JButton("Refresh");
        refreshButton.setBackground(GuiPreferences.button);
        refreshButton.setForeground(GuiPreferences.textColor);
        refreshButton.addActionListener(e -> loadEntries());

        datePickerStart = new DatePicker();
        datePickerStart.onSelect.addListener(() -> loadEntries());

        datePickerEnd = new DatePicker();
        datePickerEnd.onSelect.addListener(() -> loadEntries());

        dateStartLabel = new JLabel("Start Date");
        dateStartLabel.setForeground(GuiPreferences.textColor);
        dateStartLabel.setFont(GuiPreferences.parkingFont);

        dateEndLabel = new JLabel("End Date");
        dateEndLabel.setForeground(GuiPreferences.textColor);
        dateEndLabel.setFont(GuiPreferences.parkingFont);

        tableModel = new DefaultTableModel();
        tableModel.addColumn("Date");
        tableModel.addColumn("Fee");

        scrollContent = new JTable(tableModel);
        scrollContent.setLayout(null);
        scrollContent.getTableHeader().setBackground(GuiPreferences.panel);
        scrollContent.getTableHeader().setFont(GuiPreferences.parkingFont);
        scrollContent.getTableHeader().setForeground(GuiPreferences.textColor);
        scrollContent.setBackground(GuiPreferences.background);
        scrollContent.setFont(GuiPreferences.parkingFont);
        scrollContent.setForeground(GuiPreferences.textColor);

        scrollPane = new JScrollPane(scrollContent);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.getVerticalScrollBar().setBackground(GuiPreferences.panel);

        add(datePickerStart);
        add(datePickerEnd);
        add(dateStartLabel);
        add(dateEndLabel);
        add(refreshButton);
        add(scrollPane);
        add(chart);

        loadEntries();
    }

    private void loadEntries() {

        for (int i = tableModel.getRowCount() - 1; i >= 0; i--)
            tableModel.removeRow(i);

        HashMap<String, Float> fees = SaveManager.loadFees(datePickerStart.getSelectedDate(), datePickerEnd.getSelectedDate());
        ArrayList<String> keyList = new ArrayList(fees.keySet());
        Collections.sort(keyList);

        for (String key : keyList)
            tableModel.addRow(new Object[] { key, fees.get(key) + "$" });

        chart.setData(fees);
        chart.repaint();
    }

    @Override
    protected void resize() {

        int widthDivision = getWidth() / 64;
        int heightDivision = getHeight() / 64;

        dateStartLabel.setLocation(0, heightDivision * 3);
        dateStartLabel.setSize(widthDivision * 8, heightDivision * 3);
        GuiPreferences.scaleFont(dateStartLabel, 15);

        dateEndLabel.setLocation(widthDivision * 32, heightDivision * 3);
        dateEndLabel.setSize(widthDivision * 8, heightDivision * 3);
        GuiPreferences.scaleFont(dateEndLabel, 15);

        datePickerStart.setLocation(widthDivision * 8, 0);
        datePickerStart.setSize(widthDivision * 20, heightDivision * 6);

        datePickerEnd.setLocation(widthDivision * 40, 0);
        datePickerEnd.setSize(widthDivision * 20, heightDivision * 6);

        refreshButton.setLocation(widthDivision * 24, heightDivision * 61);
        refreshButton.setSize(widthDivision * 16, heightDivision * 3);
        GuiPreferences.scaleFont(refreshButton, 15);

        scrollPane.setLocation(0, heightDivision * 9);
        scrollPane.setSize(getWidth() / 4, heightDivision * 50);

        scrollContent.setLocation(0, 0);
        scrollContent.setSize(scrollPane.getSize());
        scrollContent.getTableHeader().setPreferredSize(new Dimension(0, heightDivision * 4));
        scrollContent.setRowHeight(heightDivision * 4);
        GuiPreferences.scaleFont(scrollContent.getTableHeader(), 15);
        GuiPreferences.scaleFont(scrollContent, 12);

        chart.setLocation(getWidth() / 4, heightDivision * 9);
        chart.setSize(getWidth() / 4 * 3, heightDivision * 50);
    }
}
