package com.quentin.duck.entity;

import com.quentin.duck.Game;

import java.util.ArrayList;

public class Duck {
    public int PosX;
    public int PosY;
    private int Target[];
    private int MoveLength;
    private float Weight;
    private int State; // (0: baby , 1: child etc..)
    private boolean isLeader;

    public Duck(){ // Constructor
        PosX = (int) (Math.random() * ((600) + 1));
        PosY = (int) (Math.random() * ((800) + 1));
        MoveLength = 5;
        isLeader = false;
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
        ArrayList<Integer> ShortestTarget = null;
        for (int i = 0 ;i< Game.NumberOfLily;i++){
            if(ShortestTarget == null ) { // if is first lily
                ShortestTarget.add(Game.LilyArray.get(i).PosX);
                ShortestTarget.add(Game.LilyArray.get(i).PosY);
            }
            else{
                ShortestTarget.set(0,Game.LilyArray.get(i).PosX);
                ShortestTarget.set(0,Game.LilyArray.get(i).PosY);
            }
        }
        //TODO;
    }

    public void FollowLeader(){
        //TODO;
    }

    public void Whistling(){
        //TODO;
    }

}
