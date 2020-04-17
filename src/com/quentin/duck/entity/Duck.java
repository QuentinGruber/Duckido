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
    public  boolean isAlive;
    private int Target_posX = 0;
    private int Target_posY = 0;
    private final int MOVE_SPEED;

    private double Weight;
    private double Critical_Weight;
    private boolean isLeader;

    public Duck() { // Constructor
        PosX = (int) (Math.random() * ((750) + 1));
        PosY = (int) (Math.random() * ((550) + 1));
        MOVE_SPEED = 1; // TODO: remove it DEBUG only
        Weight = 0.72 ; // Weight is in kg
        Critical_Weight = Weight / 1.2 ;
        State = 0;
        isLookingRight = true;
        isAlive = true;
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
        Weight += 0.1;
        Sound.playSound("assets/sound/miam.wav");
    }

    public void Check_Weight() throws UnsupportedAudioFileException, IOException, LineUnavailableException {
        Weight-= 0.005; // lose every fixed time
        //System.out.println(Weight);
        if(Weight < Critical_Weight){
            isAlive = false;
            Game.NumberOfDucks--;
            Sound.playSound("assets/sound/dead.wav");
        }
        else if (Weight > 0.90 && State == 0){
            Critical_Weight = Weight / 1.3;
            State = 1;
        }
        else if (Weight > 1.3 && State == 1){
            Critical_Weight = Weight / 1.4;
            State = 2;
        }
        else if (Weight > 1.6 && State == 2){
            Critical_Weight = Weight / 1.5;
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
