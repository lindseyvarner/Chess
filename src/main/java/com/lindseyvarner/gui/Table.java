package com.lindseyvarner.gui;

import javax.swing.*;
import java.awt.*;

public class Table {
    private final JFrame gameFrame;
    private static Dimension OUTER_FRAME = new Dimension(600, 600);

    public Table() {
        this.gameFrame = new JFrame("Driver");
        this.gameFrame.setSize(OUTER_FRAME);
        this.gameFrame.setVisible(true);
    }
}
