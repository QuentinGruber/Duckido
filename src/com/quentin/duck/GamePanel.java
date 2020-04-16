package com.quentin.duck;

import com.quentin.duck.graphics.Draw;
import com.quentin.duck.utils.FpsCounter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class GamePanel extends JPanel implements ActionListener {


    public static Game Game = new Game();
    public static int MaxFPS;
    public static int CurrentFPS;
    FpsCounter fpsCounter;
    Draw draw;
    Timer timer;
    public GamePanel(int width, int height) {
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
        super.paintComponent(g);

        draw.background(g, this);

        for (int i = 0; i < Game.NumberOfRocks; i++) { // draw all rocks
            draw.rock(g, this, com.quentin.duck.Game.RocksArray.get(i).PosX, com.quentin.duck.Game.RocksArray.get(i).PosY);
        }

        for (int i = 0; i < com.quentin.duck.Game.LilyArray.size(); i++) { // draw all waterlily
            if(!com.quentin.duck.Game.LilyArray.get(i).deleted){
                draw.waterLily(g, this, com.quentin.duck.Game.LilyArray.get(i).PosX, com.quentin.duck.Game.LilyArray.get(i).PosY);
            }
        }

        for (int i = 0; i < com.quentin.duck.Game.NumberOfDucks; i++) { // draw all ducks
            draw.duck(g, this, com.quentin.duck.Game.DuckArray.get(i).PosX,
                    com.quentin.duck.Game.DuckArray.get(i).PosY,com.quentin.duck.Game.DuckArray.get(i).State,
                    com.quentin.duck.Game.DuckArray.get(i).isLookingRight);
        }
        
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        // Calculate & display FPS
        fpsCounter.AddCalls();

        CurrentFPS = fpsCounter.GetFPS();

        // Run main loop
        Game.MainLoop();
        repaint(); // and repaint all element
    }

}

