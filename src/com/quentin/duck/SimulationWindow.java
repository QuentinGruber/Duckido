package com.quentin.duck;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class SimulationWindow extends JFrame {
    final Image img = ImageIO.read(new File("assets/icon.png"));

    public SimulationWindow() throws IOException {
        /// Create simulation windows
        setTitle("Duckido");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setContentPane(new SimulationPanel(800, 600));
        pack();
        setResizable(false);
        setLocationRelativeTo(null);
        setIconImage(img);
        setVisible(true);
    }
}
