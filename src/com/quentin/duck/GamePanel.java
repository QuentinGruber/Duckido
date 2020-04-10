package com.quentin.duck;

import com.quentin.duck.graphics.Draw;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class GamePanel extends JPanel implements ActionListener {


    public static Game Game = new Game();
    public static int MaxFPS;
    public static int CurrentFPS;

    // FPS DEBUG
    private final long StartTime;
    private long numberOfCall;

    public GamePanel(int width, int height) {
        StartTime = System.currentTimeMillis() / 1000;
        numberOfCall = 0;
        setPreferredSize(new Dimension(width, height));
        setFocusable(true);
        requestFocus();
        MaxFPS = 10;
        Timer timer = new Timer(1000 / MaxFPS, this);
        timer.start();
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        var draw = new Draw();

        draw.background(g, this);

        for (int i = 0; i < Game.NumberOfRocks; i++) { // draw all rocks
            draw.rock(g, this, com.quentin.duck.Game.RocksArray.get(i).PosX, com.quentin.duck.Game.RocksArray.get(i).PosY);
        }

        for (int i = 0; i < com.quentin.duck.Game.NumberOfDucks; i++) { // draw all ducks
            draw.duck(g, this, com.quentin.duck.Game.DuckArray.get(i).PosX,
                    com.quentin.duck.Game.DuckArray.get(i).PosY,com.quentin.duck.Game.DuckArray.get(i).State);
        }

        for (int i = 0; i < com.quentin.duck.Game.NumberOfLily; i++) { // draw all waterlily
            draw.waterLily(g, this, com.quentin.duck.Game.LilyArray.get(i).PosX, com.quentin.duck.Game.LilyArray.get(i).PosY);
        }

    }

    @Override
    public void actionPerformed(ActionEvent e) {

        // Calculate & display FPS
        long TimePass = (System.currentTimeMillis() / 1000) - StartTime;
        numberOfCall++;
        if (TimePass > 0) {
            CurrentFPS = (int) (numberOfCall / (TimePass));
            if(CurrentFPS < 1){
                CurrentFPS = 1;
            }
            System.out.println("FPS:" + (int) (numberOfCall / (TimePass)));
        }

        // Run main loop
        Game.MainLoop();
        repaint(); // and repaint all element
    }

}

