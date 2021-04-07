package com.company;

import javax.swing.*;
import java.awt.*;

public class GuiPreferences {

    public final static Color background = new Color(50, 50, 50, 255);
    public final static Color button = new Color(75, 75, 75, 255);
    public final static Color buttonPressed = new Color(100, 100, 100, 255);
    public final static Color panel = new Color(100, 100, 100, 255);
    public final static Color border = new Color(0, 0, 0, 255);
    public final static Color textColor = new Color(255, 255, 255, 255);

    public final static Font parkingFont = new Font("serif", Font.BOLD, 24);

    /** Scales the font of a JComponent by its JFrame root
     * @param component Component that will get its font scaled
     * @param size The initial font size that fits a 1200 * 800 frame
     */
    public static void scaleFont(JComponent component, float size) {
        JFrame frame = (JFrame)SwingUtilities.getRoot(component);
        component.setFont(component.getFont().deriveFont(size * frame.getHeight() * frame.getWidth() / (1200 * 800)));
    }

    /** Scales the font of a Graphics objects by its JFrame root
     * @param g Graphics that will get their font scaled
     * @param component Component on which the Graphics are painted
     * @param size The initial font size that fits a 1200 * 800 frame
     */
    public static void scaleFont(Graphics g, JComponent component, float size) {
        JFrame frame = (JFrame)SwingUtilities.getRoot(component);
        g.setFont(g.getFont().deriveFont(size * frame.getHeight() * frame.getWidth() / (1200 * 800)));
    }
}
