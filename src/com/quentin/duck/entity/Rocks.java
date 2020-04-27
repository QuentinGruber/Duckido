package com.quentin.duck.entity;

import java.awt.*;

public class Rocks {
    /// Rock position on axis X
    public final int PosX;
    /// Rock position on axis Y
    public final int PosY;

    public Rocks() { // Constructor
        /// Generate random PosX & PosY
        PosX = (int) (Math.random() * ((750) + 1));
        PosY = (int) (Math.random() * ((550) + 1));
    }

    public Rectangle bounds() {
        /// return rock "hitbox"
        return (new Rectangle(PosX, PosY, 40, 30));
    }
}
