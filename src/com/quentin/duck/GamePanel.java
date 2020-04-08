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

    public GamePanel(int width , int height) {

        setPreferredSize(new Dimension(width, height));
        setFocusable(true);
        requestFocus();
        Timer timer = new Timer(1, this);
        timer.start();
    }
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        var draw = new Draw();
        draw.background(g,this);
        for(int i = 1;i <= Game.NumberOfDucks;i++ ){ // draw all ducks
            draw.duck(g,this,Game.DuckArray[i].PosX,Game.DuckArray[i].PosY);
        }

    }
    @Override
    public void actionPerformed(ActionEvent e) {
        Game.MainLoop();
        repaint();
    }

}

