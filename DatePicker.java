package com.company;

import javafx.util.Pair;
import javax.swing.*;
import java.util.*;
import java.util.List;

public class DatePicker extends GuiComponent {

    private HashMap<String, Pair<String, Integer>> months = new HashMap() {{
         put("January", new Pair("01", 31));
         put("February", new Pair("02", 28));
         put("March", new Pair("03", 31));
         put("April", new Pair("04", 30));
         put("May", new Pair("05", 31));
         put("June", new Pair("06", 30));
         put("July", new Pair("07", 31));
         put("August", new Pair("08", 31));
         put("September", new Pair("09", 30));
         put("October", new Pair("10", 31));
         put("November", new Pair("11", 30));
         put("December", new Pair("12", 31));
    }};

    private String[] getYears() {

        List<String> years = new LinkedList();

        for (int i = 2010; i <= Integer.parseInt(ScaledTime.getTime().split(" ")[0].split("-")[0]); i++)
            years.add(i + "");

        return years.toArray(new String[years.size()]);
    }

    private String[] getMonths() {
        return new String[] {
            "January",
            "February",
            "March",
            "April",
            "May",
            "June",
            "July",
            "August",
            "September",
            "October",
            "November",
            "December"
        };
    }

    private String[] getDays(String month) {

        List<String> days = new LinkedList();

        for (int i = 1; i <= months.get(month).getValue(); i++)
            if (i < 10)
                days.add("0" + i);
            else
                days.add("" + i);

        return days.toArray(new String[days.size()]);
    }
    
    private JLabel yearLabel;
    private JLabel monthLabel;
    private JLabel dayLabel;

    private JComboBox yearSelect;
    private JComboBox monthSelect;
    private JComboBox daySelect;

    public Event onSelect = new Event();

    /** Creates a new DatePicker
     */
    public DatePicker() {

        super();

        setBackground(GuiPreferences.buttonPressed);
        
        yearLabel = new JLabel("Year");
        yearLabel.setForeground(GuiPreferences.textColor); 
        yearLabel.setFont(GuiPreferences.parkingFont);

        monthLabel = new JLabel("Month");
        monthLabel.setForeground(GuiPreferences.textColor);
        monthLabel.setFont(GuiPreferences.parkingFont);

        dayLabel = new JLabel("Day");
        dayLabel.setForeground(GuiPreferences.textColor);
        dayLabel.setFont(GuiPreferences.parkingFont);
        
        yearSelect = new JComboBox(getYears());
        yearSelect.setBackground(GuiPreferences.button);
        yearSelect.setForeground(GuiPreferences.textColor);
        yearSelect.setFont(GuiPreferences.parkingFont);
        yearSelect.addActionListener(e -> onSelect.callListeners());

        monthSelect = new JComboBox(getMonths());
        monthSelect.setBackground(GuiPreferences.button);
        monthSelect.setForeground(GuiPreferences.textColor);
        monthSelect.setFont(GuiPreferences.parkingFont);
        monthSelect.addActionListener(e -> {
            daySelect.setModel(new DefaultComboBoxModel(getDays(monthSelect.getSelectedItem().toString())));
            onSelect.callListeners();
        });

        daySelect = new JComboBox(getDays(monthSelect.getSelectedItem().toString()));
        daySelect.setBackground(GuiPreferences.button);
        daySelect.setForeground(GuiPreferences.textColor);
        daySelect.setFont(GuiPreferences.parkingFont);
        daySelect.addActionListener(e -> onSelect.callListeners());
        
        add(yearLabel);
        add(monthLabel);
        add(dayLabel);
        
        add(yearSelect);
        add(monthSelect);
        add(daySelect);
    }

    /** Returns the selected date from the DatePicker
     * @return Date selected as a string in the format "yyyy-MM-dd HH:mm:ss"
     */
    public String getSelectedDate() {
        return yearSelect.getSelectedItem() + "-" + months.get(monthSelect.getSelectedItem()).getKey() + "-" + daySelect.getSelectedItem();
    }

    @Override
    protected void resize() {

        int widthDivision = getWidth() / 16;
        int heightDivision = getHeight() / 2;

        yearLabel.setLocation(0, 0);
        yearLabel.setSize(widthDivision * 4, heightDivision);
        GuiPreferences.scaleFont(yearLabel, 15);

        monthLabel.setLocation(widthDivision * 4, 0);
        monthLabel.setSize(widthDivision * 8, heightDivision);
        GuiPreferences.scaleFont(monthLabel, 15);

        dayLabel.setLocation(widthDivision * 12, 0);
        dayLabel.setSize(widthDivision * 4, heightDivision);
        GuiPreferences.scaleFont(dayLabel,15);
        
        yearSelect.setLocation(0, heightDivision);
        yearSelect.setSize(widthDivision * 4, heightDivision);
        GuiPreferences.scaleFont(yearSelect,15);

        monthSelect.setLocation(widthDivision * 4, heightDivision);
        monthSelect.setSize(widthDivision * 8, heightDivision);
        GuiPreferences.scaleFont(monthSelect, 15);

        daySelect.setLocation(widthDivision * 12, heightDivision);
        daySelect.setSize(widthDivision * 4, heightDivision);
        GuiPreferences.scaleFont(daySelect, 15);
    }
}
