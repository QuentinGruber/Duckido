package com.quentin.duck.entity;

public class WaterLily {
    public int PosX;
    public int PosY;

    public WaterLily() { // Constructor
        PosX = (int) (Math.random() * ((600) + 1));
        PosY = (int) (Math.random() * ((800) + 1));
    }
}
