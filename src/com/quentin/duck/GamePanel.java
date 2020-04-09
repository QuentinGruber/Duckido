package com.quentin.duck;
import com.quentin.duck.graphics.Draw;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class GamePanel extends JPanel implements ActionListener {

    private static final int INITIAL_DELAY = 200;
    private static final int PERIOD_INTERVAL = 10;
    public static int x = 0;
    public static int width;
    public static int height;
    public static Game Game = new Game();

    // FPS DEBUG
    private int MaxFPS;
    private long StartTime;
    private long numberOfCall;

    public GamePanel(int width , int height) {
        StartTime = System.currentTimeMillis() / 1000;
        numberOfCall = 0;
        setPreferredSize(new Dimension(width, height));
        setFocusable(true);
        requestFocus();
        MaxFPS = 30;
        Timer timer = new Timer(1000/MaxFPS, this);
        timer.start();
    }
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        var draw = new Draw();

        draw.background(g,this);

        for(int i = 0;i < Game.NumberOfRocks;i++ ){ // draw all rocks
            draw.rock(g,this, Game.RocksArray.get(i).PosX, Game.RocksArray.get(i).PosY);
        }

        for(int i = 0;i < Game.NumberOfDucks;i++ ){ // draw all ducks
            draw.duck(g,this, Game.DuckArray.get(i).PosX, Game.DuckArray.get(i).PosY);
        }

        for(int i = 0;i < Game.NumberOfLily;i++ ){ // draw all waterlily
            draw.waterLily(g,this, Game.LilyArray.get(i).PosX, Game.LilyArray.get(i).PosY);
        }

    }
    @Override
    public void actionPerformed(ActionEvent e) {


        long TimePass = (System.currentTimeMillis() / 1000) - StartTime;
        numberOfCall++;
        if(TimePass>0) {
            System.out.println("FPS:"+numberOfCall / (TimePass));
        }
        Game.MainLoop();
        repaint();
    }

}

