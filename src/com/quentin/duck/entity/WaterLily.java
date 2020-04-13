package com.quentin.duck.entity;

import java.awt.*;

public class WaterLily {
    public int PosX;
    public int PosY;

    public WaterLily() { // Constructor
        PosX = (int) (Math.random() * ((750) + 1));
        PosY = (int) (Math.random() * ((550) + 1));
    }

    public Rectangle bounds() {
        return (new Rectangle(PosX,PosY,30,30));
    }
}
