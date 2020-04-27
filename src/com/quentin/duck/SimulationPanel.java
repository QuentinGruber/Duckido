package com.quentin.duck;

import com.quentin.duck.graphics.Draw;
import com.quentin.duck.utils.FpsCounter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class SimulationPanel extends JPanel implements ActionListener {

    /// Create a new simulation
    public static final Simulation simulation = new Simulation();
    /// Defines the maximum display update rate per second.
    public static int MaxFPS;
    /// Current frame per second
    public static int CurrentFPS;
    /// Init FpsCounter ( used for counting frame per second )
    final FpsCounter fpsCounter;
    /// Init draw class ( used for drawing component )
    final Draw draw;
    /// Init timer
    final Timer timer;

    public SimulationPanel(int width, int height) {
        /// Create Simulation Panel
        setPreferredSize(new Dimension(width, height));
        setFocusable(true);
        requestFocus();
        MaxFPS = 60;
        CurrentFPS = 1;
        fpsCounter = new FpsCounter();
        draw = new Draw();
        timer = new Timer(1000 / MaxFPS, this);
        timer.setInitialDelay(100);
        timer.start();
    }

    public void paintComponent(Graphics g) {
        /// Paint simulation component on the window
        super.paintComponent(g);

        draw.background(g, this);

        for (int i = 0; i < simulation.NumberOfRocks; i++) { // draw all rocks
            draw.rock(g, this, Simulation.RocksArray.get(i).PosX, Simulation.RocksArray.get(i).PosY);
        }

        for (int i = 0; i < Simulation.LilyArray.size(); i++) { // draw all waterlily
            if (!Simulation.LilyArray.get(i).deleted) {
                draw.waterLily(g, this, Simulation.LilyArray.get(i).PosX, Simulation.LilyArray.get(i).PosY);
            }
        }

        for (int i = 0; i < Simulation.DuckArray.size(); i++) { // draw all ducks
            if (Simulation.DuckArray.get(i).isAlive) {
                draw.duck(g, this, Simulation.DuckArray.get(i).PosX,
                        Simulation.DuckArray.get(i).PosY, Simulation.DuckArray.get(i).State,
                        Simulation.DuckArray.get(i).isLookingRight);
            }
        }

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        /// Called "$MaxFPS" per second

        // Calculate & display FPS
        fpsCounter.AddCalls();

        CurrentFPS = fpsCounter.GetFPS();

        // Run main loop
        simulation.MainLoop();
        repaint(); // and repaint all element
    }

}

