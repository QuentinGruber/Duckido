package com.quentin.duck.entity;

import com.quentin.duck.Game;

import java.awt.*;

public class WaterLily {
    public int PosX;
    public int PosY;
    public boolean deleted;

    public WaterLily() { // Constructor
        PosX = (int) (Math.random() * ((750) + 1));
        PosY = (int) (Math.random() * ((550) + 1));
        deleted = false;
    }

    public Rectangle bounds() {
        return (new Rectangle(PosX,PosY,20,20));
    } // lower bounds to make the eating visual process better
}
