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
        if(Game.NumberOfLily != 0) {
            LilyHunting();
        }
        else{
            StationaryMove();
        }
    }
    public void StationaryMove(){
        int nb_random = (-MoveLength) + (int)(Math.random() * ((MoveLength - (-MoveLength)) + 1));
        PosX += nb_random;
        nb_random = (-MoveLength) + (int)(Math.random() * ((MoveLength - (-MoveLength)) + 1));
        PosY += nb_random;
    }
    public void LilyHunting(){
        ArrayList<Integer> ShortestTarget = new ArrayList<>();
        int ShortestTarget_distance = 0;
        for (int i = 0 ;i < Game.NumberOfLily;i++){
            if(ShortestTarget.size() == 0 ) { // if is first lily
                System.out.println(("if"));
                ShortestTarget.add(Game.LilyArray.get(i).PosX);
                ShortestTarget.add(Game.LilyArray.get(i).PosY);
                ShortestTarget_distance = ((ShortestTarget.get(0) - PosX)) + ((ShortestTarget.get(1) - PosY)) ;
                if(ShortestTarget_distance < 0 ){
                    ShortestTarget_distance *= -1;
                }
            }
            else{
                System.out.println(("else"));
                int targetDistance = ((Game.LilyArray.get(i).PosX - PosX)) + ((Game.LilyArray.get(i).PosY - PosY));
                if(targetDistance < 0 ){
                    targetDistance *= -1;
                }
                System.out.println("shortest: "+ShortestTarget_distance+" target: "+targetDistance);
                if(ShortestTarget_distance > targetDistance) {
                    ShortestTarget.set(0, Game.LilyArray.get(i).PosX);
                    ShortestTarget.set(1, Game.LilyArray.get(i).PosY);
                    ShortestTarget_distance = targetDistance;
                }
            }
            System.out.println("shortestfinal: "+ShortestTarget_distance);
        }
    }

    public void FollowLeader(){
        //TODO;
    }

    public void Whistling(){
        //TODO;
    }

}
