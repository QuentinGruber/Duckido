package com.quentin.duck.entity;

import java.awt.*;

public class Rocks {
    public int PosX;
    public int PosY;

    public Rocks() { // Constructor
        PosX = (int) (Math.random() * ((750) + 1));
        PosY = (int) (Math.random() * ((550) + 1));
    }

    public Rectangle bounds() {
        return (new Rectangle(PosX, PosY, 40, 30));
    }
}
