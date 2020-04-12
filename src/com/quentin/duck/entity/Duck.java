package com.quentin.duck.entity;

import com.quentin.duck.Game;

import java.util.ArrayList;

public class Duck {
    public int PosX;
    public int PosY;
    public int State; // (0: baby , 1: child etc..)
    private int Target_posX = 0;
    private int Target_posY = 0;
    private final int MOVE_SPEED;

    private double Weight;
    private double Critical_Weight;
    private boolean isLeader;

    public Duck() { // Constructor
        PosX = (int) (Math.random() * ((750) + 1));
        PosY = (int) (Math.random() * ((550) + 1));
        MOVE_SPEED = 5;
        Weight = 10.0 ; // TODO: check real duck weight
        Critical_Weight = Weight / 2 ;
        State = 1;
        isLeader = false;
    }

    public void move() {
        if (Game.NumberOfLily != 0 ) {
            LilyHunting();
            GoToTarget();
        } else {
            StationaryMove();
        }
    }

    public void Check_Weight() {
        Weight-= 1.0  /100;
        System.out.println(Weight);
        if(Weight < Critical_Weight){
            System.out.println("Dead");
        }
        else if (Weight > 15 && State == 0){
            Critical_Weight = Weight / 2;
            State = 1;
        }
        else if (Weight > 20 && State == 1){
            Critical_Weight = Weight / 2;
            State = 2;
        }
        else if (Weight > 25 && State == 2){
            Critical_Weight = Weight / 2;
            State = 3;
            isLeader = true;
        }
    }

    private void GoToTarget() {

        if(Target_posX <= PosX){
            PosX -= MOVE_SPEED;
        }
        else{
            PosX += MOVE_SPEED;
        }

        if(Target_posY <= PosY){
            PosY -= MOVE_SPEED;
        }
        else{
            PosY += MOVE_SPEED;
        }
    }

    private void StationaryMove() {
        int nb_random = (-MOVE_SPEED) + (int) (Math.random() * ((MOVE_SPEED - (-MOVE_SPEED)) + 1));
        PosX += nb_random;
        nb_random = (-MOVE_SPEED) + (int) (Math.random() * ((MOVE_SPEED - (-MOVE_SPEED)) + 1));
        PosY += nb_random;
    }

    private void LilyHunting() {
        ArrayList<Integer> ShortestTarget = new ArrayList<>();
        int ShortestTarget_distance = 0;
        int ShortestTarget_distance_index = 0;
        for (int i = 0; i < Game.NumberOfLily; i++) {
            if (ShortestTarget.size() == 0) { // if is first lily
                ShortestTarget.add(Game.LilyArray.get(i).PosX);
                ShortestTarget.add(Game.LilyArray.get(i).PosY);
                ShortestTarget_distance = ((ShortestTarget.get(0) - PosX)) + ((ShortestTarget.get(1) - PosY));
                if (ShortestTarget_distance < 0) {
                    ShortestTarget_distance *= -1;
                }
            } else {
                int targetDistance = ((Game.LilyArray.get(i).PosX - PosX)) + ((Game.LilyArray.get(i).PosY - PosY));
                if (targetDistance < 0) {
                    targetDistance *= -1;
                }
                if (ShortestTarget_distance > targetDistance) {
                    ShortestTarget.set(0, Game.LilyArray.get(i).PosX);
                    ShortestTarget.set(1, Game.LilyArray.get(i).PosY);
                    ShortestTarget_distance = targetDistance;
                    ShortestTarget_distance_index = i;
                }
            }
        }
        Target_posX = Game.LilyArray.get(ShortestTarget_distance_index).PosX;
        Target_posY = Game.LilyArray.get(ShortestTarget_distance_index).PosY;
    }

    private void FollowLeader() {
        //TODO;
    }

    private void Whistling() {
        //TODO;
    }

}
