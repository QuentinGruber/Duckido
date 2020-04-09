package com.quentin.duck.entity;

public class Rocks {
    public int PosX;
    public int PosY;

    public Rocks() { // Constructor
        PosX = (int) (Math.random() * ((600) + 1));
        PosY = (int) (Math.random() * ((800) + 1));
    }
}
