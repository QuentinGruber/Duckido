package com.quentin.duck.graphics;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class Draw {
    public void background(Graphics g, JPanel GamePanel) {
        try {
            Image bg = ImageIO.read(new File("assets/bg.png"));
            g.drawImage(bg, 0, 0, GamePanel);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void duck(Graphics g, JPanel GamePanel, int x, int y, int Duck_state, boolean lookingRight) {
        try {
            Image duck = null;
            if (Duck_state == 0) {
                if (lookingRight) {
                    duck = ImageIO.read(new File("assets/duck/duck0/duck_r.png"));
                } else {
                    duck = ImageIO.read(new File("assets/duck/duck0/duck_l.png"));
                }
            }
            if (Duck_state == 1) {
                if (lookingRight) {
                    duck = ImageIO.read(new File("assets/duck/duck1/duck_r.png"));
                } else {
                    duck = ImageIO.read(new File("assets/duck/duck1/duck_l.png"));
                }
            }
            if (Duck_state == 2) {
                if (lookingRight) {
                    duck = ImageIO.read(new File("assets/duck/duck2/duck_r.png"));
                } else {
                    duck = ImageIO.read(new File("assets/duck/duck2/duck_l.png"));
                }
            }
            if (Duck_state == 3) {
                if (lookingRight) {
                    duck = ImageIO.read(new File("assets/duck/duckleader/duck_r.png"));
                } else {
                    duck = ImageIO.read(new File("assets/duck/duckleader/duck_l.png"));
                }
            }
            g.drawImage(duck, x, y, GamePanel);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void waterLily(Graphics g, JPanel GamePanel, int x, int y) {
        try {
            Image waterlily = ImageIO.read(new File("assets/waterlily.png"));
            g.drawImage(waterlily, x, y, GamePanel);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void rock(Graphics g, JPanel GamePanel, int x, int y) {
        try {
            Image rock = ImageIO.read(new File("assets/rock.png"));
            g.drawImage(rock, x, y, GamePanel);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
