package com.quentin.duck.graphics;

import sun.print.PathGraphics;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.ImageObserver;
import java.io.File;
import java.io.IOException;

public class Draw {
    public void background(Graphics g, JPanel GamePanel){
        try {
            Image bg = ImageIO.read(new File("assets/bg.png"));
            g.drawImage(bg, 0, 0, GamePanel);
            //Pour une image de fond
            //g.drawImage(img, 0, 0, this.getWidth(), this.getHeight(), this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void duck(Graphics g, JPanel GamePanel,int x,int y){
        try {
            Image duck = ImageIO.read(new File("assets/duck.png"));
            g.drawImage(duck, x, y, GamePanel);
            //Pour une image de fond
            //g.drawImage(img, 0, 0, this.getWidth(), this.getHeight(), this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
