package com.quentin.duck.entity;

public class Duck {
    public int PosX;
    public int PosY ;
    private int MoveLength;
    private float Weight;

    public Duck(){ // Constructor
        PosX = (int) (Math.random() * ((600) + 1));
        PosY = (int) (Math.random() * ((800) + 1));
        MoveLength = 5;
    }

    public void move(){
        StationaryMove();
    }
    public void StationaryMove(){
        int nb_random = (-MoveLength) + (int)(Math.random() * ((MoveLength - (-MoveLength)) + 1));
        PosX += nb_random;
        nb_random = (-MoveLength) + (int)(Math.random() * ((MoveLength - (-MoveLength)) + 1));
        PosY += nb_random;
    }
    public void LilyHunting(){
        //TODO;
    }

    public void FollowLeader(){
        //TODO;
    }

}
