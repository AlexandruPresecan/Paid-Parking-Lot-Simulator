package com.company;

import javax.swing.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

public class GuiComponent extends JComponent {

    /** Create a new GuiComponent
     * Just a JComponent with a null layout that allows resize overriding
     */
    public GuiComponent() {

        super();

        setLayout(null);

        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                resize();
            }
        });
    }

    protected void resize() {

    }
}
