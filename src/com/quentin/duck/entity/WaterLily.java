package com.quentin.duck.entity;

import java.awt.*;

public class WaterLily {
    /// WaterLily position on axis X
    public final int PosX;
    /// WaterLily position on axis y
    public final int PosY;
    /// Is WaterLily deleted?
    public boolean deleted;

    public WaterLily() { // Constructor
        PosX = (int) (Math.random() * ((750) + 1));
        PosY = (int) (Math.random() * ((550) + 1));
        deleted = false;
    }

    public Rectangle bounds() {
        /// return WaterLily "hitbox"
        return (new Rectangle(PosX, PosY, 20, 20));
    } // lower bounds to make the eating visual process better
}
