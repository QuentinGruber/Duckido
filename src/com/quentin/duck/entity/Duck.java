package com.quentin.duck.entity;

import com.quentin.duck.Game;
import com.quentin.duck.utils.Sound;

import javax.sound.sampled.*;
import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;

public class Duck {
    public int PosX;
    public int PosY;
    public int State; // (0: baby , 1: child etc..)
    public boolean isLookingRight;
    private int Target_posX = 0;
    private int Target_posY = 0;
    private final int MOVE_SPEED;

    private double Weight;
    private double Critical_Weight;
    private boolean isLeader;

    public Duck() { // Constructor
        PosX = (int) (Math.random() * ((750) + 1));
        PosY = (int) (Math.random() * ((550) + 1));
        MOVE_SPEED = 1;
        Weight = 10.0 ; // TODO: check real duck weight
        Critical_Weight = Weight / 2 ;
        State = 0;
        isLookingRight = true;
        isLeader = false;
    }

    public void bornSound() throws UnsupportedAudioFileException, IOException, LineUnavailableException {
        Sound.playSound("assets/sound/coincoin.wav");
    }

    public Rectangle bounds(){
        if(State == 0) {
            return (new Rectangle(PosX, PosY, 40, 30));
        }
        else{
            return (new Rectangle(PosX, PosY, 80, 60));
        }
    }

    public void move() {
        //System.out.println(Game.NumberOfLily);
        if (Game.NumberOfLily != 0 ) {
            LilyHunting();
        } else {
            StationaryMove();
        }
    }

    public synchronized void Eat() throws UnsupportedAudioFileException, IOException, LineUnavailableException {
        Weight += 1;
        Sound.playSound("assets/sound/miam.wav");
    }

    public void Check_Weight() throws UnsupportedAudioFileException, IOException, LineUnavailableException {
        Weight-= 1.0  /100;
        //System.out.println(Weight);
        if(Weight < Critical_Weight){
            //System.out.println("Dead");
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
            Whistling();
            isLeader = true;
        }
    }

    private void GoToTarget() {

        if(Target_posX < PosX){
            isLookingRight = false;
            PosX -= MOVE_SPEED;
        }
        else if (Target_posX > PosX){
            isLookingRight = true;
            PosX += MOVE_SPEED;
        }

        if(Target_posY < PosY){
            PosY -= MOVE_SPEED;
        }
        else if (Target_posY > PosY){
            PosY += MOVE_SPEED;
        }
    }

    private void StationaryMove() {
        int nb_random = (-MOVE_SPEED) + (int) (Math.random() * ((MOVE_SPEED - (-MOVE_SPEED)) + 1));
        PosX += nb_random;
        nb_random = (-MOVE_SPEED) + (int) (Math.random() * ((MOVE_SPEED - (-MOVE_SPEED)) + 1));
        PosY += nb_random;
    }

    private int CalculateTargetDistance(int x,int y){
        int Target_distance_x = (x - PosX);
        int Target_distance_y = (y - PosY);
        if(Target_distance_x < 0){
            Target_distance_x *= -1;
        }
        if(Target_distance_y < 0){
            Target_distance_y *= -1;
        }
        return Target_distance_x + Target_distance_y;
    }
    private void LilyHunting() {
        ArrayList<Integer> ShortestTarget = new ArrayList<>();
        int ShortestTarget_distance = 0;
        for (int i = 0; i < Game.LilyArray.size(); i++) {
            if(!Game.LilyArray.get(i).deleted) {
                if (ShortestTarget.size() == 0) { // if is first lily
                    ShortestTarget.add(Game.LilyArray.get(i).PosX);
                    ShortestTarget.add(Game.LilyArray.get(i).PosY);
                    ShortestTarget_distance = CalculateTargetDistance(ShortestTarget.get(0), ShortestTarget.get(1));
                } else {
                    int targetDistance = CalculateTargetDistance(Game.LilyArray.get(i).PosX, Game.LilyArray.get(i).PosY);

                    if (ShortestTarget_distance > targetDistance) {
                        ShortestTarget.set(0, Game.LilyArray.get(i).PosX);
                        ShortestTarget.set(1, Game.LilyArray.get(i).PosY);
                        ShortestTarget_distance = CalculateTargetDistance(ShortestTarget.get(0), ShortestTarget.get(1));
                    }
                }
            }
        }
        Target_posX = ShortestTarget.get(0);
        Target_posY = ShortestTarget.get(1);
        GoToTarget();
    }

    private void FollowLeader() {
        //TODO;
    }

    private void Whistling() throws UnsupportedAudioFileException, IOException, LineUnavailableException {
        Sound.playSound("assets/sound/Whistling.wav");
        //TODO;
    }
}
