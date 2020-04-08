package com.quentin.duck;
import com.quentin.duck.entity.Duck;
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
    private Timer timer;
    public int NumberOfDucks = 0;
    public Duck[] DuckArray = new Duck[100000];


    private Thread thread;

    public GamePanel(int width , int height) {

        setPreferredSize(new Dimension(width, height));
        setFocusable(true);
        requestFocus();
        timer = new Timer(100, this);
        timer.start();
    }
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        var draw = new Draw();
        draw.background(g,this);
        for(int i = 1;i <= NumberOfDucks;i++ ){
            draw.duck(g,this,DuckArray[i].PosX,DuckArray[i].PosY);
        }

    }
    @Override
    public void actionPerformed(ActionEvent e) {
        int nombreAleatoire = (int) (Math.random() * ((1000) + 1));
        // TODO: make game loop
        if(nombreAleatoire<1000){
            System.out.println("nouvo canard");
            var NewDuck = new Duck();
            NumberOfDucks++;
            DuckArray[NumberOfDucks] = NewDuck;
        }
       // System.out.println(DuckArray[3]);
        repaint();
    }

}

