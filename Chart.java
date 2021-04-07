package com.company;

import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class Chart extends GuiComponent {

    private HashMap<String, Float> data;

    /** Sets the data to be plotted on the chart
     * @param data HashMap with the keys on the Ox and values on the Oy
     */
    public void setData(HashMap<String, Float> data) {
        this.data = data;
    }

    private void drawChart(Graphics g) {

        int xMargin = getWidth() / 8;
        int yMargin = getHeight() - getHeight() / 8;

        g.setColor(GuiPreferences.textColor);
        g.drawLine(xMargin, 0, xMargin, getHeight());
        g.drawLine(getWidth() / 16, yMargin, getWidth(), yMargin);

        for (int i = 0; i < yMargin; i += getHeight() / 10) {

            for (int j = xMargin; j < getWidth(); j += 2) {

                g.drawLine(j, i, j + 2, i);

                if (g.getColor() == GuiPreferences.textColor)
                    g.setColor(GuiPreferences.background);
                else
                    g.setColor(GuiPreferences.textColor);
            }
        }
    }

    private void drawSeries(Graphics g) {

        ArrayList<String> keyList = new ArrayList(data.keySet());
        Collections.sort(keyList);

        int width = getWidth() / 8 * 7;
        int height = getHeight() / 8 * 7;
        int xIncrement = width / data.size();
        int yMax = 0;

        for (String key : keyList)
            if (data.get(key) > yMax)
                yMax = data.get(key).intValue();

        int x = getWidth() / 8;
        int y = height - data.get(keyList.get(0)).intValue() * height / yMax;

        g.setColor(GuiPreferences.textColor);
        GuiPreferences.scaleFont(g, this, 12);

        for (int i = 0; i < height; i += height / 10)
            g.drawString(yMax - i * yMax / getHeight() + "$", x / 4, i - getHeight() / 32);

        g.drawString(keyList.get(0), x, height + getHeight() / 16);
        g.drawString(keyList.get(keyList.size() - 1), width, height + getHeight() / 16);

        for (String key : keyList) {

            if (key == keyList.get(0))
                continue;

            g.drawLine(x, y, x + xIncrement, height - data.get(key).intValue() * height / yMax);
            x += xIncrement;
            y = height - data.get(key).intValue() * height / yMax;
        }
    }

    /** Draws the chart and the series using already set data
     */
    @Override
    public void paintComponent(Graphics g) {

        super.paintComponent(g);

        drawChart(g);

        if (data.size() > 0)
            drawSeries(g);
    }

}
